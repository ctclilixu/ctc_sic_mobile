package com.cn.common.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.RenderListener;
import com.itextpdf.text.pdf.parser.TextRenderInfo;

/**
 * PdfKeywordsUtil
 * @author Frank
 * @version 1.0
 * @create 2016-4-6下午3:24:20
 */
public class PdfKeywordsUtil {

	private static final Logger log = LogManager.getLogger(PdfKeywordsUtil.class);
	private static int i = 0;
	private static List<float[]> arrays = new ArrayList<float[]>();

	/**
	 * 获得PDF文件中关键字的坐标
	 * @param filePath
	 * @param keyword
	 * @return
	 */
	public List<float[]> getKeyWords(String filePath, final String keyword) {
		try {
			arrays = new ArrayList<float[]>();
			PdfReader pdfReader = new PdfReader(filePath);
			int pageNum = pdfReader.getNumberOfPages();
			PdfReaderContentParser pdfReaderContentParser = new PdfReaderContentParser(pdfReader);
			for (i = 1; i <= pageNum; i++) {
				pdfReaderContentParser.processContent(i, new RenderListener() {
					
					@Override
					public void renderText(TextRenderInfo textRenderInfo) {
						// 内容
						String text = textRenderInfo.getText();
						//log.info(text);
						//判断是否包含关键字
						if (null != text && text.contains(keyword)) {
							java.awt.geom.Rectangle2D.Float boundingRectange = textRenderInfo.getBaseline().getBoundingRectange();
							float[] resu = new float[3];
							//关键字坐标
							resu[0] = boundingRectange.x;
							resu[1] = boundingRectange.y;
							//关键字所在页码
							resu[2] = i;
							arrays.add(resu);
						}
					}

					@Override
					public void renderImage(ImageRenderInfo arg0) {
					}

					@Override
					public void endTextBlock() {
					}

					@Override
					public void beginTextBlock() {
					}
				});
			}
		} catch (IOException e) {
			log.error(e);
		}
		return arrays;
	}

	public static void main(String[] args) {
		String keyword = "ymb";
		String pdfFilePath = "D:/yppfff/GEA32118 1700V SiC Module Fact Sheet_R2.pdf";
//		String pdfFilePath = "D:/yppfff/GEA32120 SiC Mosfet GE12N20L Fact Sheet_R2.pdf";
//		String pdfFilePath = "D:/yppfff/GEA32121 SiC Mosfet GE12N45L Fact Sheet_R2.pdf";
//		String pdfFilePath = "D:/yppfff/GEA32158 1200V SiC Power Module Fact Sheet_R3.pdf";
		
//		String pdfFilePath = "D:/yppfff/GEA32119 SiC Power Block Fact Sheet_R3 (1).pdf";
//		String keyword = "las";
		
		PdfKeywordsUtil pdfKeywords = new PdfKeywordsUtil();
		List<float[]> ff = pdfKeywords.getKeyWords(pdfFilePath, keyword);
		for (float[] f : ff) {
			System.out.println(f[0] + "----" + f[1] + "-----" + f[2]);
		}
	}
}