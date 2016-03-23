package com.cn.ge.action;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.cn.common.action.BaseAction;
import com.cn.common.qrcode.QRCodeUtil;
import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.common.util.PdfUtil;
import com.cn.common.util.PropertiesConfig;
import com.cn.common.util.StringUtil;
import com.cn.ge.dto.DocDto;
import com.cn.ge.service.DocService;
import com.opensymphony.xwork2.ActionContext;

/**
 * DocAction
 * @author Frank
 * @version 1.0
 * @create 2016-2-26下午2:06:13
 */
public class DocAction extends BaseAction {

	private static final long serialVersionUID = 4184038674769958248L;
	private static final Logger log = LogManager.getLogger(DocAction.class);
	
	private DocService docService;
	
	/**
	 * 页码
	 */
	private int startIndex;
	
	/**
	 * 翻页
	 */
	private Page page;
	
	private String queryDocname;
	
	private List<DocDto> docList;
	
	//文件上传
	private File addPdfFile;
	private String filename;
	private String docname;
	private String doctype;
	private String qr_url;
	
	//图片下载
	private String downloadPicId;
	
	/**
	 * 下载二维码Action
	 */
	public void downloadPic() {
		try {
			this.clearMessages();
			//图片地址
			String qrPicPath = PropertiesConfig.getPropertiesValueByKey("PIC_PATH") + downloadPicId + "\\qrcode_" + downloadPicId + "." + QRCodeUtil.imgType;
			
			response.setHeader("Content-Disposition", "attachment; filename=" + "qrcode_" + downloadPicId + "." + QRCodeUtil.imgType);
			OutputStream outStream = response.getOutputStream();
			response.setContentType("image/jped");
			
			File f = new File(qrPicPath);
			BufferedImage bi;
			byte[] bytes = null;
			try {
				bi = ImageIO.read(f);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(bi, "jpg", baos);
				bytes = baos.toByteArray();
			} catch (IOException e) {
				e.printStackTrace();
			}
			outStream.write(bytes, 0, bytes.length);
			outStream.flush();
			outStream.close();
		} catch(Exception e) {
			log.error("downloadPic error:" + e);
		}
	}
	
	/**
	 * 显示文件上传页面
	 * @return
	 */
	public String showUploadDocAction() {
		try {
			this.clearMessages();
			addPdfFile = null;
			filename = "";
			docname = "";
			qr_url = "";
			downloadPicId= "";
		} catch(Exception e) {
			log.error("showUploadDocAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 文件上传
	 * @return
	 */
	public String uploadDocAction() {
		try {
			this.clearMessages();
			qr_url = "";
			if(addPdfFile == null) {
				this.addActionMessage("请选择PDF文件！");
				return "checkerror";
			}

			//判断逻辑主键
			DocDto tmpdoc = docService.queryDocByLogicID(docname, doctype);
			if(tmpdoc != null) {
				if("20".equals(doctype)) {
					this.addActionMessage("该文档名对应的完整版已存在！");
				} else {
					this.addActionMessage("该文档名对应的概要版已存在！");
				}
				return "checkerror";
			}
			
//			Date date = new Date();
//			//文件后缀名
//			String s = filename.substring(filename.lastIndexOf("."), filename.length());
//			
//			//新的文件名
//			SimpleDateFormat sdff = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//			String random = UUID.randomUUID().toString();
//			String newfilename = sdff.format(date) + random.substring(random.length() - 5, random.length());
//			
//			String oldPath = addPdfFile.getAbsolutePath();
//			
//			//目标文件路径
//			String targetPath = DocAction.class.getResource("/").toString();
//			targetPath = targetPath.substring(0, targetPath.indexOf("WEB-INF"));
//			targetPath += "temp/";
//			targetPath = targetPath.replace("file:/", "/");
//			//将文件拷贝到指定路径
//			FileUtil.copyFile(oldPath, targetPath, newfilename + s);
			
			//当前操作用户ID
			String userid = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			
			DocDto newdoc = new DocDto();
			
			//资料名，手动输入，与doctype组成逻辑主键
			newdoc.setDocname(docname);
			
			//上传的PDF文件名
			newdoc.setFilename(filename);
			//普通文档
			newdoc.setDoctype(doctype);
			
			//解析PDF文件
			//String txt = PdfUtil.getTextFromPdf(targetPath + newfilename + s);
			String text = PdfUtil.getTextFromPdf(addPdfFile);
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
				newdoc.setShowtype(1);
				
				String productcode = "";
				String features = "";
				String benefits = "";
				String applications = "";
				
				//文件描述
				for(int i = 1; i < s1.length; i++) {
					if("Features".equals(s1[i])) {
						break;
					}
					desc += s1[i] + " ";
				}
				desc = desc.replace("  ", " ");
				
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
				
				//productcode
				newdoc.setProductcode(productcode);
				//Features
				if(StringUtil.isNotBlank(features)) {
					newdoc.setTitle2("Features");
					newdoc.setContent2(features);
				}
				//Benefits
				if(StringUtil.isNotBlank(benefits)) {
					newdoc.setTitle3("Benefits");
					newdoc.setContent3(benefits);
				}
				//Applications
				if(StringUtil.isNotBlank(applications)) {
					newdoc.setTitle4("Applications");
					newdoc.setContent4(applications);
				}
				
				log.info("productcode=[" + productcode + "]");
				log.info("features=[" + features + "]");
				log.info("benefits=[" + benefits + "]");
				log.info("applications=[" + applications + "]");
			} else {
				//其他格式
				newdoc.setShowtype(2);
				
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
					desc += s2[i] + " ";
				}
				desc = desc.replace("  ", " ");
				
				//Low-inductance modules
				//with Intelligent Gate Drive
				lowInductanceModules = PdfUtil.getContentByIndex(text, "Low-inductance modules  \r\nwith Intelligent Gate Drive", "Controls and Protection");
				//Controls and Protection
				controlsProtection = PdfUtil.getContentByIndex(text, "Controls and Protection", "SiC User Interface");
				//SiC User Interface
				sicUserInterface = PdfUtil.getContentByIndex(text, "SiC User Interface ", "fact sheet");
				//Power Capability and Efficiency
				powerCapabilityEfficiency = PdfUtil.getContentByIndex(text, "Power Capability and Efficiency", "www.GESiliconCarbide.com");
				
				//Low-inductance modules
				//with Intelligent Gate Drive
				if(StringUtil.isNotBlank(lowInductanceModules)) {
					newdoc.setTitle2("Low-inductance modules  \r\nwith Intelligent Gate Drive");
					newdoc.setContent2(lowInductanceModules);
				}
				//Controls and Protection
				if(StringUtil.isNotBlank(controlsProtection)) {
					newdoc.setTitle3("Controls and Protection");
					newdoc.setContent3(controlsProtection);
				}
				//SiC User Interface 
				if(StringUtil.isNotBlank(sicUserInterface)) {
					newdoc.setTitle4("SiC User Interface ");
					newdoc.setContent4(sicUserInterface);
				}
				//Power Capability and Efficiency
				if(StringUtil.isNotBlank(powerCapabilityEfficiency)) {
					newdoc.setTitle5("Power Capability and Efficiency");
					newdoc.setContent5(powerCapabilityEfficiency);
				}
				
				log.info("lowInductanceModules=[" + lowInductanceModules + "]");
				log.info("controlsProtection=[" + controlsProtection + "]");
				log.info("sicUserInterface=[" + sicUserInterface + "]");
				log.info("powerCapabilityEfficiency=[" + powerCapabilityEfficiency + "]");
			}
			log.info("desc=[" + desc + "]");
			log.info("title=[" + title + "]");
			log.info("subtitle=[" + subtitle + "]");
				
			//大标题
			newdoc.setTitle1(title);
			//资料描述
			newdoc.setContent1(desc);
			//副标题
			newdoc.setSubtitle(subtitle);
			
			newdoc.setStatus(Constants.STATUS_NORMAL);
			newdoc.setCreateuser(userid);
			newdoc.setUpdateuser(userid);
			docService.insertDoc(newdoc);
			
			//判断文件夹目录是否存在
			File pic_path = new File(PropertiesConfig.getPropertiesValueByKey("PIC_PATH"));
			if(!pic_path.exists()) {
				pic_path.mkdir();
			}
			
			//PDF图片
			List<String> piclist = PdfUtil.getPicFromPdf(addPdfFile, "" + newdoc.getId(),
					PropertiesConfig.getPropertiesValueByKey("PIC_PATH") + "\\" + newdoc.getId() + "\\");
			if(piclist != null) {
				if(piclist.size() > 1) {
					newdoc.setPicture1(piclist.get(0));
				}
				if(piclist.size() > 2) {
					newdoc.setPicture2(piclist.get(1));
				}
				if(piclist.size() > 3) {
					newdoc.setPicture3(piclist.get(2));
				}
				if(piclist.size() > 4) {
					newdoc.setPicture4(piclist.get(3));
				}
				if(piclist.size() > 5) {
					newdoc.setPicture5(piclist.get(4));
				}
			}
			
			//生成二维码qrcode
			QRCodeUtil handler = new QRCodeUtil();
			//二维码图片保存路径
			String qrPicPath = PropertiesConfig.getPropertiesValueByKey("PIC_PATH") + newdoc.getId() + "\\qrcode_" + newdoc.getId() + "." + QRCodeUtil.imgType;
			//二维码内容=资料明细路径
			//String content = PropertiesConfig.getPropertiesValueByKey("DOMAIN_URL") + PropertiesConfig.getPropertiesValueByKey("PROJECT_NAME") + "/docinfo_id" + newdoc.getId() + ".shtml";
			String content = PropertiesConfig.getPropertiesValueByKey("DOMAIN_URL") + PropertiesConfig.getPropertiesValueByKey("PROJECT_NAME") + "/docinfo_id" + docname + ".shtml";
			
			//二维码图片中间LOGO
			String iconPath = DocAction.class.getResource("/").toString();
			iconPath = iconPath.substring(0, iconPath.indexOf("WEB-INF"));
			iconPath += "qr/";
			iconPath = iconPath.replace("file:/", "/") + "qr.png";
			
			log.info("content=[" + content + "]");
			log.info("qrPicPath=[" + qrPicPath + "]");
			log.info("iconPath=[" + iconPath + "]");
			//生成二维码
			handler.encoderQRCode(content, qrPicPath, QRCodeUtil.imgType, iconPath);
			qr_url = PropertiesConfig.getPropertiesValueByKey("DOMAIN_URL") + "ge_pic/" + newdoc.getId() + "/qrcode_" + newdoc.getId() + "." + QRCodeUtil.imgType;
			
			downloadPicId = "" + newdoc.getId();
			newdoc.setQrcode("qrcode_" + newdoc.getId() + "." + QRCodeUtil.imgType);
			
			docService.updateDoc(newdoc);
		} catch(Exception e) {
			log.error("uploadDocAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 资料管理页面
	 * @return
	 */
	public String showDocManageAction() {
		try {
			this.clearMessages();
			queryDocname = "";
			docList = new ArrayList<DocDto>();
			page = new Page();
		} catch(Exception e) {
			log.error("showDocManageAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询资料信息
	 * @return
	 */
	public String queryDocAction() {
		try {
			this.clearMessages();
			startIndex = 0;
			page = new Page();
			queryData();
		} catch(Exception e) {
			log.error("queryDocAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页
	 * @return
	 */
	public String turnDocAction() {
		try {
			this.clearMessages();
			queryData();
		} catch(Exception e) {
			log.error("turnDocAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询数据
	 */
	@SuppressWarnings("unchecked")
	private void queryData() {
		docList = new ArrayList<DocDto>();
		if(page == null) {
			page = new Page();
		}
		//翻页查询所有委托公司
		this.page.setStartIndex(startIndex);
		page = docService.queryDocByPage(queryDocname, page);
		docList = (List<DocDto>) page.getItems();
		this.setStartIndex(page.getStartIndex());
	}

	public int getStartIndex() {
		return startIndex;
	}

	public Page getPage() {
		return page;
	}

	public String getQueryDocname() {
		return queryDocname;
	}

	public List<DocDto> getDocList() {
		return docList;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public void setQueryDocname(String queryDocname) {
		this.queryDocname = queryDocname;
	}

	public void setDocList(List<DocDto> docList) {
		this.docList = docList;
	}

	public DocService getDocService() {
		return docService;
	}

	public void setDocService(DocService docService) {
		this.docService = docService;
	}

	public File getAddPdfFile() {
		return addPdfFile;
	}

	public String getFilename() {
		return filename;
	}

	public void setAddPdfFile(File addPdfFile) {
		this.addPdfFile = addPdfFile;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getQr_url() {
		return qr_url;
	}

	public void setQr_url(String qr_url) {
		this.qr_url = qr_url;
	}

	public String getDownloadPicId() {
		return downloadPicId;
	}

	public void setDownloadPicId(String downloadPicId) {
		this.downloadPicId = downloadPicId;
	}

	public String getDocname() {
		return docname;
	}

	public void setDocname(String docname) {
		this.docname = docname;
	}

	public String getDoctype() {
		return doctype;
	}

	public void setDoctype(String doctype) {
		this.doctype = doctype;
	}
}
