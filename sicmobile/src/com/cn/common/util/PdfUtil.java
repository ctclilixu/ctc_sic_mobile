package com.cn.common.util;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.pdfbox.exceptions.InvalidPasswordException;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.PDFTextStripperByArea;

/**
 * PDF文件操作工具类
 * @name Page.java
 * @author Frank
 * @time 2016-02-05下午8:58:02
 * @version 1.0
 */
public class PdfUtil {
	
	private static final Logger log = LogManager.getLogger(PdfUtil.class);
	//换行符，linux换行符为\n
	public static String linefeed = "\n";
//	public static String linefeed = "\r\n";
	
	/**
	 * 获得PDF图片
	 * @param file
	 * @param id
	 * @param outPath
	 * @return
	 */
	public static List<String> getPicFromPdf(File file, String id, String outPath) {
		List<String> result = new ArrayList<String>();
		List<String> tmpresult = new ArrayList<String>();
		FileInputStream is = null;
		PDDocument document = null;
		try {
			File outPathFile = new File(outPath);
			if(!outPathFile.exists()) {
				//创建文件夹
				outPathFile.mkdir();
			}
			
			is = new FileInputStream(file);
			PDFParser parser = new PDFParser(is);
			parser.parse();
			document = parser.getPDDocument();
			
			//获取PDF文件内的图片
			List pages = document.getDocumentCatalog().getAllPages();
			Iterator iter = pages.iterator();
			int i = 1;
			while(iter.hasNext()) {
				PDPage page = (PDPage) iter.next();
				PDResources resources = page.getResources();
				Map images = resources.getImages();
				if(images != null) {
					Iterator imageIter = images.keySet().iterator();
					while(imageIter.hasNext()) {
						String key = (String) imageIter.next();
						PDXObjectImage image = (PDXObjectImage) images.get(key);
						//文件名
						String name = id + "_" + i;
						tmpresult.add(name + "." + image.getSuffix());
						//将图片输出到指定路径
						image.write2file(outPath + name);
						i++;
					}
				}
			}
			//对失真图片转化
			if(tmpresult != null && tmpresult.size() > 0) {
				Image img = null;
				String finalname = "";
				File tmpfile = null;
				for(String name : tmpresult) {
					finalname = "final" + name;
					img = Toolkit.getDefaultToolkit().getImage(outPath + name);
					BufferedImage bi_scale = Img2BufferImg.toBufferedImage(img);
					ImageIO.write(bi_scale, "jpg", new File(outPath + finalname));
					result.add(finalname);
					//删掉原图
					tmpfile = new File(outPath + name);
					tmpfile.delete();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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
	
	/**
	 * 获得PDF内容
	 * @param file
	 * @param id
	 * @return
	 */
	public static String getTextFromPdf(File file) {
		String result = null;
		FileInputStream is = null;
		PDDocument document = null;
		try {
			is = new FileInputStream(file);
			PDFParser parser = new PDFParser(is);
			parser.parse();
			document = parser.getPDDocument();
			PDFTextStripper stripper = new PDFTextStripper();
			//PDF内容
			result = stripper.getText(document);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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
	
	/**
	 * 获得PDF内容
	 * @param pdfFilePath
	 * @return
	 */
	public static String getTextFromPdf(String pdfFilePath) {
		String result = null;
		FileInputStream is = null;
		PDDocument document = null;
		try {
			is = new FileInputStream(pdfFilePath);
			PDFParser parser = new PDFParser(is);
			parser.parse();
			document = parser.getPDDocument();
			PDFTextStripper stripper = new PDFTextStripper();
			//PDF内容
			result = stripper.getText(document);
			//String result1 = new String(result.getBytes(), "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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
	
	
	public static String getAllFromPdf(String pdfFilePath, String id) {
		String result = null;
		FileInputStream is = null;
		PDDocument document = null;
		try {
			is = new FileInputStream(pdfFilePath);
			PDFParser parser = new PDFParser(is);
			parser.parse();
			document = parser.getPDDocument();
			PDFTextStripper stripper = new PDFTextStripper();
			//PDF内容
			result = stripper.getText(document);
			
			//获取PDF文件内的图片
			List pages = document.getDocumentCatalog().getAllPages();
			Iterator iter = pages.iterator();
			int i = 1;
			while(iter.hasNext()) {
				PDPage page = (PDPage) iter.next();
				PDResources resources = page.getResources();
				Map images = resources.getImages();
				if(images != null) {
					Iterator imageIter = images.keySet().iterator();
					while(imageIter.hasNext()) {
						String key = (String) imageIter.next();
						PDXObjectImage image = (PDXObjectImage) images.get(key);
						//文件名
						String name = id + "_" + i + "." + image.getSuffix();
						//将图片输出到指定路径
						image.write2file("D:\\" + name);
						i++;
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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
	
	/**
	 * 判断是否包含关键字符
	 * @param sss
	 * @param i
	 * @return
	 */
	public static boolean gePdfTextKeyCheck(String content, String sss, int i) {
		String[] keyList = {"Features", "Benefits", "Applications", "fact sheet", "sheet"};
		for(String s : keyList) {
			if(sss.indexOf(s) >= 0) {
				return true;
			}
		}
		if(i > 5) {
			return true;
		}
		return false;
	}
	
	/**
	 * 根据关键字，获取内容
	 * @param text
	 * @param key
	 * @param flag
	 * @return
	 */
	public static String getContentByKeyword(String text, String key, boolean flag) {
		String result = "";
		if(text.indexOf(key) >= 0) {
			String s[] = text.split(key);
			String ss[] = s[1].split(linefeed);
			int i = 0;
			for(String sss : ss) {
				if(!"".equals(sss.trim())) {
					if(!sss.startsWith("• ") && !sss.startsWith("? ")) {
						if(flag) {
							if(gePdfTextKeyCheck("Features", sss, i)) {
								break;
							} else {
								if(i > 0) {
									result = result.substring(0, result.lastIndexOf(linefeed));
								}
								result += sss.replace("• ", "").replace("? ", "") + linefeed;
							}
						} else {
							break;
						}
					} else {
						result += sss.replace("• ", "").replace("? ", "") + linefeed;
					}
				}
				i++;
			}
		}
		result = result.replace("  ", " ");
		return result;
	}
	
	/**
	 * 根据起始位置获取内容
	 * @param text
	 * @param start
	 * @param end
	 * @return
	 */
	public static String getContentByIndex(String text, String key, String end) {
		String result = "";
		if(text.indexOf(key) >= 0) {
			String s[] = text.split(key);
			String ss[] = s[1].split(linefeed);
			for(String sss : ss) {
				if(!"".equals(sss.trim())) {
					if(sss.indexOf(end) >= 0) {
						break;
					} else {
						result += sss + " ";
					}
				}
			}
		}
		result = result.replace("  ", " ");
		return result;
	}
	
	/**
	 * PDF纲要
	 * @param file
	 */
	public static void getPDFOutline(String file) {
		try {
			// 打开pdf文件流
			FileInputStream fis = new FileInputStream(file);
			// 加载 pdf 文档,获取PDDocument文档对象
			PDDocument document = PDDocument.load(fis);
			// 获取PDDocumentCatalog文档目录对象
			PDDocumentCatalog catalog = document.getDocumentCatalog();
			// 获取PDDocumentOutline文档纲要对象
			PDDocumentOutline outline = catalog.getDocumentOutline();
			// 获取第一个纲要条目（标题1）
			PDOutlineItem item = outline.getFirstChild();
			if (outline != null) {
				// 遍历每一个标题1
				while (item != null) {
					// 打印标题1的文本
					System.out.println("Item:" + item.getTitle());
					// 获取标题1下的第一个子标题（标题2）
					PDOutlineItem child = item.getFirstChild();
					// 遍历每一个标题2
					while (child != null) {
						// 打印标题2的文本
						System.out.println("    Child:" + child.getTitle());
						// 指向下一个标题2
						child = child.getNextSibling();
					}
					// 指向下一个标题1
					item = item.getNextSibling();
				}
			}
			// 关闭输入流
			document.close();
			fis.close();
		} catch (FileNotFoundException ex) {
			log.error(ex);
		} catch (IOException ex) {
			log.error(ex);
		}
	}
	
	public static void main1(String[] args) throws Exception {
//		String pdfFilePath = "D:/yppfff/GEA32118 1700V SiC Module Fact Sheet_R2.pdf";
//		String pdfFilePath = "D:/yppfff/GEA32119 SiC Power Block Fact Sheet_R3 (1).pdf";
//		String pdfFilePath = "D:/yppfff/GEA32120 SiC Mosfet GE12N20L Fact Sheet_R2.pdf";
		String pdfFilePath = "D:/yppfff/GEA32121 SiC Mosfet GE12N45L Fact Sheet_R2.pdf";
//		String pdfFilePath = "D:/yppfff/GEA32158 1200V SiC Power Module Fact Sheet_R3.pdf";
		
//		getPDFOutline(pdfFilePath);
		
		PDDocument document = null;
		try {
			document = PDDocument.load(pdfFilePath);
			if (document.isEncrypted()) {
				try {
					document.decrypt("");
				} catch (InvalidPasswordException e) {
					System.err.println("Error: Document is encrypted with a password.");
					System.exit(1);
				}
			}
			PDFTextStripperByArea stripper = new PDFTextStripperByArea();
			stripper.setSortByPosition(true);
			Rectangle rect = new Rectangle(10, 380, 275, 1360);
			stripper.addRegion("class1", rect);
			List allPages = document.getDocumentCatalog().getAllPages();
			PDPage firstPage = (PDPage) allPages.get(0);
			stripper.extractRegions(firstPage);
			System.out.println("Text in the area:" + rect);
			System.out.println(stripper.getTextForRegion("class1"));

		} finally {
			if (document != null) {
				document.close();
			}
		}
	}

	public static void main(String[] args) {
//		String pdfFilePath = "D:/yppfff/GEA32118 1700V SiC Module Fact Sheet_R2.pdf";
		String pdfFilePath = "D:/yppfff/GEA32119 SiC Power Block Fact Sheet_R3 (1).pdf";
//		String pdfFilePath = "D:/yppfff/GEA32120 SiC Mosfet GE12N20L Fact Sheet_R2.pdf";
//		String pdfFilePath = "D:/yppfff/GEA32121 SiC Mosfet GE12N45L Fact Sheet_R2.pdf";
//		String pdfFilePath = "D:/yppfff/GEA32158 1200V SiC Power Module Fact Sheet_R3.pdf";
		
		System.out.println(getPicFromPdf(new File(pdfFilePath), "1", "D:/yppfff/"));
		
//		String text = getTextFromPdf(pdfFilePath);
//		log.info(text);
//		String title = "";
//		String subtitle = "";
//		String desc = "";
//		
//		//本地测试用
//		PdfUtil.linefeed = "\r\n";
//		
//		//第一行数据为资料标题
//		String s1[] = text.split(PdfUtil.linefeed);
//		title = s1[0];
//		//副标题
//		if(text.indexOf("fact sheet") >= 0) {
//			String s[] = text.split("fact sheet");
//			String ss[] = s[1].split(PdfUtil.linefeed);
//			subtitle = ss[1].trim();
//		}
//		
//		if(text.indexOf("Features") >= 0) {
//			String productcode = "";
//			String features = "";
//			String benefits = "";
//			String applications = "";
//			
//			//文件描述
//			for(int i = 1; i < s1.length; i++) {
//				if("Features".equals(s1[i])) {
//					break;
//				}
//				desc += s1[i] + " ";
//			}
//			desc = desc.replace("  ", " ");
//			
//			//Product Code
//			if(text.indexOf("Product Code") >= 0) {
//				String s[] = text.split("Product Code");
//				String ss[] = s[1].split(PdfUtil.linefeed);
//				productcode = ss[0].trim();
//			}
//			
//			//Features
//			features = PdfUtil.getContentByKeyword(text, "Features", true);
//			log.info("features=[" + features + "]");
//			//Benefits
//			benefits = PdfUtil.getContentByKeyword(text, "Benefits", true);
//			//Applications
//			applications = PdfUtil.getContentByKeyword(text, "Applications", false);
//			
//			log.info("productcode=[" + productcode + "]");
//			log.info("features=[" + features + "]");
//			log.info("benefits=[" + benefits + "]");
//			log.info("applications=[" + applications + "]");
//		} else {
//			String lowInductanceModules = "Low-inductance modules  \r\nwith Intelligent Gate Drive";
//			String controlsProtection = "Controls and Protection";
//			String sicUserInterface = "SiC User Interface ";
//			String powerCapabilityEfficiency = "Power Capability and Efficiency";
//			//文件描述
//			String tmp = text.replace(subtitle, "");
//			tmp = tmp.replace("fact sheet", "");
//			tmp = tmp.replace(PdfUtil.linefeed + PdfUtil.linefeed, PdfUtil.linefeed);
//			tmp = tmp.replace(PdfUtil.linefeed + PdfUtil.linefeed, PdfUtil.linefeed);
//			String s2[] = tmp.split(PdfUtil.linefeed);
//			//文件描述
//			for(int i = 1; i < s2.length; i++) {
//				if("Low-inductance modules".equals(s2[i].trim())) {
//					break;
//				}
//				desc += s2[i] + " ";
//			}
//			desc = desc.replace("  ", " ");
//			
//			//Low-inductance modules
//			//with Intelligent Gate Drive
//			lowInductanceModules = PdfUtil.getContentByIndex(text, "Low-inductance modules  \r\nwith Intelligent Gate Drive", "Controls and Protection");
//			//Controls and Protection
//			controlsProtection = PdfUtil.getContentByIndex(text, "Controls and Protection", "SiC User Interface");
//			//SiC User Interface
//			sicUserInterface = PdfUtil.getContentByIndex(text, "SiC User Interface ", "fact sheet");
//			//Power Capability and Efficiency
//			powerCapabilityEfficiency = PdfUtil.getContentByIndex(text, "Power Capability and Efficiency", "www.GESiliconCarbide.com");
//			
//			log.info("lowInductanceModules=[" + lowInductanceModules + "]");
//			log.info("controlsProtection=[" + controlsProtection + "]");
//			log.info("sicUserInterface=[" + sicUserInterface + "]");
//			log.info("powerCapabilityEfficiency=[" + powerCapabilityEfficiency + "]");
//		}
//		log.info("desc=[" + desc + "]");
//		log.info("title=[" + title + "]");
//		log.info("subtitle=[" + subtitle + "]");
	}
}
