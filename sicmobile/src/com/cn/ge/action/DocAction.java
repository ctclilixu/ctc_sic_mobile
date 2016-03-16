package com.cn.ge.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.cn.common.action.BaseAction;
import com.cn.common.qrcode.QRCodeUtil;
import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.common.util.PdfUtil;
import com.cn.common.util.PropertiesConfig;
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
	private String qr_url;
	
	/**
	 * 显示文件上传页面
	 * @return
	 */
	public String showUploadDocAction() {
		try {
			this.clearMessages();
			addPdfFile = null;
			filename = "";
			qr_url = "";
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
			newdoc.setDocname(filename);
			//普通文档
			newdoc.setDoctype(10);
			
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
					desc += s1[i] + "\r\n";
				}
				
				//Product Code
				if(text.indexOf("Product Code") >= 0) {
					String s[] = text.split("Product Code");
					String ss[] = s[1].split("\r\n");
					productcode = ss[0].trim();
				}
				
				//Features
				if(text.indexOf("Features") >= 0) {
					String s[] = text.split("Features");
					String ss[] = s[1].split("\r\n");
					int i = 0;
					for(String sss : ss) {
						if(!"".equals(sss.trim())) {
							if(!sss.startsWith("• ") && !sss.startsWith("? ")) {
								if(PdfUtil.gePdfTextKeyCheck("Features", sss, i)) {
									break;
								} else {
									features += sss.replace("• ", "").replace("? ", "") + "\r\n";
								}
							} else {
								features += sss.replace("• ", "").replace("? ", "") + "\r\n";
							}
						}
						i++;
					}
				}
				
				//Benefits
				if(text.indexOf("Benefits") >= 0) {
					String s[] = text.split("Benefits");
					String ss[] = s[1].split("\r\n");
					int i = 0;
					for(String sss : ss) {
						if(!"".equals(sss.trim())) {
							if(!sss.startsWith("• ") && !sss.startsWith("? ")) {
								if(PdfUtil.gePdfTextKeyCheck("Benefits", sss, i)) {
									break;
								} else {
									benefits += sss.replace("• ", "").replace("? ", "") + "\r\n";
								}
							} else {
								benefits += sss.replace("• ", "").replace("? ", "") + "\r\n";
							}
						}
						i++;
					}
				}
				
				//Applications
				if(text.indexOf("Applications") >= 0) {
					String s[] = text.split("Applications");
					String ss[] = s[1].split("\r\n");
					for(String sss : ss) {
						if(!"".equals(sss.trim())) {
							if(!sss.startsWith("• ") && !sss.startsWith("? ")) {
								break;
							} else {
								applications += sss.replace("• ", "").replace("? ", "") + "\r\n";
							}
						}
					}
				}
				
				//productcode
				newdoc.setProductcode(productcode);
				
				//Features
				newdoc.setTitle2("Features");
				newdoc.setContent2(features);
				
				//Benefits
				newdoc.setTitle3("Benefits");
				newdoc.setContent3(benefits);
				
				//Applications
				newdoc.setTitle4("Applications");
				newdoc.setContent4(applications);
				
				log.info("productcode=[" + productcode + "]");
				log.info("features=[" + features + "]");
				log.info("benefits=[" + benefits + "]");
				log.info("applications=[" + applications + "]");
			} else {
				//其他格式
				newdoc.setShowtype(2);
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
			}
			log.info("title=[" + title + "]");
			log.info("desc=[" + desc + "]");
			log.info("subtitle=[" + subtitle + "]");
			//log.info(text);
			
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
			String imgType = "bmp";
			//二维码图片保存路径
			String qrPicPath = PropertiesConfig.getPropertiesValueByKey("PIC_PATH") + newdoc.getId() + "\\qrcode_" + newdoc.getId() + "." + imgType;
			//二维码内容=资料明细路径
			String content = PropertiesConfig.getPropertiesValueByKey("DOMAIN_URL") + PropertiesConfig.getPropertiesValueByKey("PROJECT_NAME") + "/docinfo_id" + newdoc.getId() + ".shtml";
			
			//二维码图片中间LOGO
			String iconPath = DocAction.class.getResource("/").toString();
			iconPath = iconPath.substring(0, iconPath.indexOf("WEB-INF"));
			iconPath += "qr/";
			iconPath = iconPath.replace("file:/", "/") + "qr.png";
			
			log.info("content=[" + content + "]");
			log.info("qrPicPath=[" + qrPicPath + "]");
			log.info("iconPath=[" + iconPath + "]");
			//生成二维码
			handler.encoderQRCode(content, qrPicPath, imgType, iconPath);
			qr_url = PropertiesConfig.getPropertiesValueByKey("DOMAIN_URL") + "ge_pic/" + newdoc.getId() + "/qrcode_" + newdoc.getId() + "." + imgType;
			
			newdoc.setQrcode("qrcode_" + newdoc.getId() + "." + imgType);
			
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
}
