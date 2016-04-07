package com.cn.common.util;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.util.PDFTextStripper;
import org.jfree.util.Log;

import com.cn.ge.dto.DocDto;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;

/**
 * PdfToImgUtil
 * @author Frank
 * @version 1.0
 * @create 2016-3-31下午1:34:55
 */
public class PdfToImgUtil {

	private static final Logger log = LogManager.getLogger(PdfToImgUtil.class);
	private static int img_size = 2;
	private static int lastpage_height = 160;

	/**
	 * PDF转化为图片（一页一张图片）
	 * @param pdfPath
	 * @param imgPath
	 * @param imgPreName
	 */
	public static void pdfToImg(String pdfPath, String imgPath, String imgPreName) {
		try {
			File file = new File(pdfPath);
			RandomAccessFile raf = new RandomAccessFile(file, "r");
			FileChannel channel = raf.getChannel();
			MappedByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
			PDFFile pdffile = new PDFFile(buf);
			
			//按页转化图片
			for (int i = 1; i <= pdffile.getNumPages(); i++) {
				PDFPage page = pdffile.getPage(i);
				Rectangle rect = new Rectangle(0, 0,
						((int) page.getBBox().getWidth()), ((int) page.getBBox().getHeight()));
				Image img = page.getImage(rect.width * img_size, rect.height * img_size,
						rect, null, true, true);
				BufferedImage tag = new BufferedImage(
						rect.width * img_size, rect.height * img_size, BufferedImage.TYPE_INT_RGB);
				tag.getGraphics().drawImage(img, 0, 0,
						rect.width * img_size, rect.height * img_size, null);
				
//				Image img = page.getImage(rect.width, rect.height,
//						rect, null, true, true);
//				BufferedImage tag = new BufferedImage(
//						rect.width, rect.height, BufferedImage.TYPE_INT_RGB);
//				tag.getGraphics().drawImage(img, 0, 0,
//						rect.width, rect.height, null);
				
				// 输出到文件流
				FileOutputStream out = new FileOutputStream(imgPath + imgPreName + "_" + i + ".jpg");
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				JPEGEncodeParam param2 = encoder.getDefaultJPEGEncodeParam(tag);
				// 1f是提高生成的图片质量
				param2.setQuality(1f, false);
				encoder.setJPEGEncodeParam(param2);
				// JPEG编码
				encoder.encode(tag);
				out.close();
			}
			channel.close();
			raf.close();
			
			// 如果要在转图片之后删除pdf，就必须要这个关闭流和清空缓冲的方法
			//unmap(buf);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void unmap(final Object buffer) {
		// TODO Auto-generated method stub
		AccessController.doPrivileged(new PrivilegedAction() {
			public Object run() {
				try {
					Method getCleanerMethod = buffer.getClass().getMethod(
							"cleaner", new Class[0]);
					getCleanerMethod.setAccessible(true);
					sun.misc.Cleaner cleaner = (sun.misc.Cleaner) getCleanerMethod
							.invoke(buffer, new Object[0]);
					cleaner.clean();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		});
	}
	
	/**
	 * 表格转化为图片
	 * @param pdfFilePath
	 * @param imgPath
	 * @param imgPreName
	 * @param firstPageIndex 表格起始页索引
	 * @param firstHeight 表格起始页高度坐标
	 * @param lastPageIndex 表格底部页索引
	 * @param lastHeight 表格底部页高度坐标
	 * @return
	 * @throws Exception
	 */
	public String tableToImg(String pdfFilePath, String imgPath, String imgPreName,
			int firstPageIndex, int firstHeight, int lastPageIndex, int lastHeight) throws Exception {
		String result = "";
		int head = 32;
		int bottom = 65;
		
		//表格底部坐标页面索引比头部坐标页面索引小，则不处理
		if(lastPageIndex != 0 && lastPageIndex < firstPageIndex) {
			return result;
		}
		
		File file = new File(pdfFilePath);
		RandomAccessFile raf = new RandomAccessFile(file, "r");
		FileChannel channel = raf.getChannel();
		MappedByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
		PDFFile pdffile = new PDFFile(buf);
		
		//PDFBox
		FileInputStream is = null;
		PDDocument document = null;
		try {
			is = new FileInputStream(pdfFilePath);
			PDFParser parser = new PDFParser(is);
			parser.parse();
			document = parser.getPDDocument();
			List pages = document.getDocumentCatalog().getAllPages();
			
			//清晰度
			img_size = 2;
			int num = 1;
			for (int i = 1; i <= pdffile.getNumPages(); i++) {
				PDFPage page = pdffile.getPage(i);
				
				//默认去掉头部fact sheet
				int height = ((int) page.getBBox().getHeight()) - head * img_size;
				
				if(firstPageIndex <= i && lastPageIndex >= i) {
					//表格页，高度=表格坐标高度
					if(firstPageIndex == i) {
						height = firstHeight;
					}
					
					//判断是否有内容，没有内容则不转化图片
					String content = "";
					PDPage pdpage = (PDPage) pages.get(i - 1);
					if(pdpage != null) {
						StringWriter sw = new StringWriter();
						PDFTextStripper pst = new PDFTextStripper();
						pst.setStartPage(i);
						pst.setStartPage(i);
						pst.writeText(document, sw);
						content = sw.getBuffer().toString();
						//log.info("content====" + content);
					}
					if(StringUtil.isNotBlank(content)) {
						Rectangle rect = new Rectangle(0, 0, ((int) page.getBBox().getWidth()), height);
						Image img = null;
						try {
							img = page.getImage(rect.width * img_size, rect.height * img_size,
									rect, null, true, true);
						} catch (Exception e) {
						}
						
						//默认去掉底部fact sheet
						int lastPageHeight = rect.height * img_size - bottom * img_size;
						if(lastPageIndex == i) {
							//最后一页表格高度
							if(lastPageIndex == firstPageIndex) {
								//去掉页脚
								lastPageHeight = lastHeight - firstHeight;
							} else {
								//去掉页脚和头部去掉的高度
								lastPageHeight = rect.height * img_size - lastHeight - bottom * img_size - head * img_size;
							}
						}
						
						BufferedImage tag = new BufferedImage(
								rect.width * img_size, lastPageHeight, BufferedImage.TYPE_INT_RGB);
						tag.getGraphics().drawImage(img, 0, 0,
								rect.width * img_size, rect.height * img_size, null);
						
						// 输出到文件流
						String filename = imgPreName + "_" + num + ".jpg";
						FileOutputStream out = new FileOutputStream(imgPath + filename);
						result += filename + ",";
						num++;
						
						JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
						JPEGEncodeParam param2 = encoder.getDefaultJPEGEncodeParam(tag);
						// 1f是提高生成的图片质量
						param2.setQuality(1f, false);
						encoder.setJPEGEncodeParam(param2);
						// JPEG编码
						encoder.encode(tag);
						out.close();
					}
				}
			}
			channel.close();
			raf.close();
			// 如果要在转图片之后删除pdf，就必须要这个关闭流和清空缓冲的方法
			unmap(buf);
		} catch(Exception e) {
			Log.info(e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (document != null) {
				try {
					document.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		DocDto newdoc = new DocDto();
		
		newdoc.setShowtype(2);
		String pdfFilePath = "D:/yppfff/GEA32119 SiC Power Block Fact Sheet_R3 (1).pdf";
		String firstkeyword = "las";
		String lastkeyword = "";

//		newdoc.setShowtype(1);
//		String firstkeyword = "ymb";
//		String lastkeyword = "ecommended in pulse mode only";
//		String pdfFilePath = "D:/yppfff/GEA32118 1700V SiC Module Fact Sheet_R2.pdf";
//		String pdfFilePath = "D:/yppfff/GEA32120 SiC Mosfet GE12N20L Fact Sheet_R2.pdf";
//		String pdfFilePath = "D:/yppfff/GEA32121 SiC Mosfet GE12N45L Fact Sheet_R2.pdf";
//		String pdfFilePath = "D:/yppfff/GEA32158 1200V SiC Power Module Fact Sheet_R3.pdf";
		
//		pdfToImg(pdfFilePath, "D:/yppfff/", "img");
		
		//表格起始坐标
		PdfKeywordsUtil pdfKeywords = new PdfKeywordsUtil();
		List<float[]> firstPositions = pdfKeywords.getKeyWords(pdfFilePath, firstkeyword);
		if(firstPositions != null && firstPositions.size() > 0) {
			//默认+30
			int firstheight = (int) firstPositions.get(0)[1] + 30;
			int firstindex = (int) firstPositions.get(0)[2];
			log.info("firstheight=" + firstheight);
			log.info("firstindex=" + firstindex);
			
			//表格底部坐标
			int lastheight = 0;
			int lastindex = 0;
			if(newdoc.getShowtype() == 1) {
				List<float[]> lastPositions = pdfKeywords.getKeyWords(pdfFilePath, lastkeyword);
				if(lastPositions != null && lastPositions.size() > 0) {
					lastheight = (int) lastPositions.get(0)[1];
					lastindex = (int) lastPositions.get(0)[2];
				}
			} else {
				//其他格式，默认表格高度为440
				lastheight = firstheight + 440;
				lastindex = firstindex;
			}
			
			log.info("lastheight=" + lastheight);
			log.info("lastindex=" + lastindex);
			
			PdfToImgUtil util = new PdfToImgUtil();
			String result = util.tableToImg(pdfFilePath, "D:/yppfff/",
					"table", firstindex, firstheight, lastindex, lastheight);
			log.info("result=" + result);
		}
	}
}
