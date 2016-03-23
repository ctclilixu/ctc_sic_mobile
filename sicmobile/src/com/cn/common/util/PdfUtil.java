package com.cn.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;
import org.apache.pdfbox.util.PDFTextStripper;

/**
 * PDF文件操作工具类
 * @name Page.java
 * @author Frank
 * @time 2016-02-05下午8:58:02
 * @version 1.0
 */
public class PdfUtil {
	
	/**
	 * 获得PDF图片
	 * @param file
	 * @param id
	 * @param outPath
	 * @return
	 */
	public static List<String> getPicFromPdf(File file, String id, String outPath) {
		List<String> result = new ArrayList<String>();
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
						result.add(name + "." + image.getSuffix());
						//将图片输出到指定路径
						image.write2file(outPath + name);
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
			String ss[] = s[1].split("\r\n");
			int i = 0;
			for(String sss : ss) {
				if(!"".equals(sss.trim())) {
					if(!sss.startsWith("• ") && !sss.startsWith("? ")) {
						if(flag) {
							if(gePdfTextKeyCheck("Features", sss, i)) {
								break;
							} else {
								if(i > 0) {
									result = result.substring(0, result.lastIndexOf("\r\n"));
								}
								result += sss.replace("• ", "").replace("? ", "") + "\r\n";
							}
						} else {
							break;
						}
					} else {
						result += sss.replace("• ", "").replace("? ", "") + "\r\n";
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
			String ss[] = s[1].split("\r\n");
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

	public static void main(String[] args) {
		//String pdfFilePath = "d://GEA32118 SiC Module Fact Sheet_R2.pdf";
		//System.out.println(getAllFromPdf(pdfFilePath, "11"));
		
//		String pdfFilePath = "D:/yppfff/GEA32118 1700V SiC Module Fact Sheet_R2.pdf";
		String pdfFilePath = "D:/yppfff/GEA32119 SiC Power Block Fact Sheet_R3 (1).pdf";
//		String pdfFilePath = "D:/yppfff/GEA32120 SiC Mosfet GE12N20L Fact Sheet_R2.pdf";
//		String pdfFilePath = "D:/yppfff/GEA32121 SiC Mosfet GE12N45L Fact Sheet_R2.pdf";
//		String pdfFilePath = "D:/yppfff/GEA32158 1200V SiC Power Module Fact Sheet_R3.pdf";
		
		String text = getTextFromPdf(pdfFilePath);
//		System.out.println(text);
//		String text = "1200V SiC MOSFET\r\nGE’s Silicon Carbide MOSFET is the primary building block for \r\napplications below 200 kW. Our discrete solutions have an \r\noptimized design that provides improved efficiency, higher \r\nswitching frequency and an industry-first, 200°C maximum junction \r\ntemperature rating.\r\nManufactured with an ISO 9001-certified process, GE devices have \r\nbeen independently tested and demonstrate superior reliability and \r\nruggedness.\r\nFeatures\r\n? High voltage and low on-resistance up to 200°C\r\n? Fast switching, compatible with standard gate drives\r\n? Very low, temperature-invariant switching losses \r\n? Avalanche ruggedness superior to silicon\r\n? Fast body diode for synchronous rectification\r\nfact sheet\r\nThe next generation of power electronics\r\nBenefits\r\n? High reliability and ruggedness–ready for industry adoption\r\n? Current-scalable designs for a wide range of applications\r\n? Minimal de-rating required\r\n?  High quality delivered through ISO 9001 certified  \r\nmanufacturing process \r\n? Industry’s first AEC-Q101 qualified SiC MOSFET\r\nMOSFET DC Characteristics @ TJ=25°C (unless otherwise specified)\r\nSymbol Parameter Conditions Min Typ Max Units\r\nID Continuous drain current\r\nVGS=20 V, TC=25°C 67.5\r\nA\r\nVGS=20 V, TC=100°C 51\r\nVGS=20 V, TC=125°C 44\r\nID,pulse Pulsed drain current \r\n1) TC=25°C 120\r\nPtot Power dissipation TC=25°C 437 W\r\nVDS,max Drain-source breakdown voltage VGS=0 V, IDS=10 μA 1200 V\r\nIDSS Drain leakage current\r\nVDS=1200 V, VGS=0 V, Tj=25°C 20 μA\r\nTj=200°C 200\r\nIGSS Gate-source leakage current VGS= -15/+23 V 20 nA\r\nVGS Recommended gate-source voltage +20\r\nVVGS,max Maximum gate-source voltage VDS=0 V -15 +23\r\nVTH Gate-source threshold voltage VGS=VDS, IDS=10 mA 2.1 2.8 4\r\nRDS(ON) Drain-source on-resistance\r\nVGS=20 V, IDS=45 A, Tj=25°C 50 60 ?\r\nTj=200°C 78 96\r\nRGATE,ESR Gate-source series resistance VGS=0 V, f=1 MHz, drain floating 1 1.2 ?\r\nEAS Single pulse avalanche energy ID=20 A, L=32 mH 3 J\r\ntSC Short circuit withstand time\r\nVDS=960 V, VGS=20 V 3.5\r\nμs\r\nVDS=600 V, VGS=20 V 10\r\nRthJC Thermal resistance, junction-case 0.4 °C/?\r\nTstg Operating junction and storage temperature -55 200 °C\r\nTL Solder temperature 1.6 mm (0.063\") from case for 10s 260 °C\r\nProduct Code GE12N45L\r\nfact sheet\r\nMOSFET Dynamic Characteristics\r\nSymbol Parameter Conditions Min Typ Max Units\r\nCISS Input capacitance\r\nVGS=0 V, VDS=500 V, f=100 kHz\r\n2829\r\npFCOSS Output capacitance 198\r\nCRSS Reverse transfer capacitance 32\r\ntd(on) Turn-on delay time\r\nVDS=600 V, VGS=0/20 V, ID=45A, \r\nRG(ext)=0 ?\r\n9.5\r\nns\r\ntr Rise time 8.7\r\ntd(off) Turn-off delay time 15\r\ntf Fall time 8.5\r\nEON Turn-on energy VDS=600 V, VGS=0/20 V, ID=45A, \r\nRG(ext)=2.4 ?, L=4.57mH\r\nFWD=C2D20120D\r\n713\r\nμJEOFF Turn-off energy 150\r\nETOT Total switching energy 863\r\nQG Total gate charge VGS=0 to 20 V, IDS=23A, \r\nVDS=600 V\r\n161\r\nnCQGS Gate-source charge 38\r\nQGD Gate-drain charge 63\r\nDiode Characteristics 2)\r\nSymbol Parameter Conditions Min Typ Max Units\r\nISD Pulsed body diode current VGS=0 V 67.5 \r\n2) A\r\nVSD Diode forward voltage \r\n2) VGS=0 V, ISD=40 A 4.2 V\r\n1) Pulse width limited by by Tj,max\r\n2) Use of body diode is recommended in pulse mode only, with pulse duration up to 1 μs.\r\n? 2015 General Electric Company. All rights reserved.\r\nGEA32121 (09/2015)\r\nwww.GESiliconCarbide.com\r\nContact us: \r\nSiC.Products@ge.com";
		System.out.println(text);
		String title = "";
		String subtitle = "";
		String desc = "";
		
		//第一行数据为资料标题
		String s1[] = text.split("\r\n");
		title = s1[0];
		//副标题
		if(text.indexOf("fact sheet") >= 0) {
			String s[] = text.split("fact sheet");
			String ss[] = s[1].split("\r\n");
			subtitle = ss[1].trim();
		}
		
		if(text.indexOf("Features") >= 0) {
			//标准格式
			String productcode = "";
			String features = "";
			String benefits = "";
			String applications = "";
			
			//文件描述
			for(int i = 1; i < s1.length; i++) {
				if("Features".equals(s1[i])) {
					break;
				}
				desc += s1[i] + "\r\n";
			}
			
			//Product Code
			if(text.indexOf("Product Code") >= 0) {
				String s[] = text.split("Product Code");
				String ss[] = s[1].split("\r\n");
				productcode = ss[0].trim();
			}
			
			//Features
			features = PdfUtil.getContentByKeyword(text, "Features", true);
			//Benefits
			benefits = PdfUtil.getContentByKeyword(text, "Benefits", true);
			//Applications
			applications = PdfUtil.getContentByKeyword(text, "Applications", false);
			
			System.out.println("productcode=[" + productcode + "]");
			System.out.println("features=[" + features + "]");
			System.out.println("benefits=[" + benefits + "]");
			System.out.println("applications=[" + applications + "]");
		} else {
			//其他格式
			String lowInductanceModules = "Low-inductance modules  \r\nwith Intelligent Gate Drive";
			String controlsProtection = "Controls and Protection";
			String sicUserInterface = "SiC User Interface ";
			String powerCapabilityEfficiency = "Power Capability and Efficiency";
			//文件描述
			String tmp = text.replace(subtitle, "");
			tmp = tmp.replace("fact sheet", "");
			tmp = tmp.replace("\r\n\r\n", "\r\n");
			tmp = tmp.replace("\r\n\r\n", "\r\n");
			String s2[] = tmp.split("\r\n");
			//文件描述
			for(int i = 1; i < s2.length; i++) {
				if("Low-inductance modules".equals(s2[i].trim())) {
					break;
				}
				desc += s2[i] + "\r\n";
			}
			
			//Low-inductance modules
			//with Intelligent Gate Drive
			lowInductanceModules = PdfUtil.getContentByIndex(text, "Low-inductance modules  \r\nwith Intelligent Gate Drive", "Controls and Protection");
			
			//Controls and Protection
			controlsProtection = PdfUtil.getContentByIndex(text, "Controls and Protection", "SiC User Interface");
			
			//SiC User Interface
			sicUserInterface = PdfUtil.getContentByIndex(text, "SiC User Interface ", "fact sheet");
			
			//Power Capability and Efficiency
			powerCapabilityEfficiency = PdfUtil.getContentByIndex(text, "Power Capability and Efficiency", "www.GESiliconCarbide.com");
			
			System.out.println("lowInductanceModules=[" + lowInductanceModules + "]");
			System.out.println("controlsProtection=[" + controlsProtection + "]");
			System.out.println("sicUserInterface=[" + sicUserInterface + "]");
			System.out.println("powerCapabilityEfficiency=[" + powerCapabilityEfficiency + "]");
		}
		System.out.println("desc=[" + desc + "]");
		System.out.println("title=[" + title + "]");
		System.out.println("subtitle=[" + subtitle + "]");
	}
}
