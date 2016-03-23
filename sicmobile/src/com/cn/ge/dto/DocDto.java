package com.cn.ge.dto;

import java.util.ArrayList;
import java.util.List;

import com.cn.common.dto.BaseDto;
import com.cn.common.util.PropertiesConfig;

/**
 * 资料文档明细Dto
 * @author Frank
 * @version 1.0
 * @create 2016-2-18下午3:45:11
 */
public class DocDto extends BaseDto {

	private static final long serialVersionUID = 669040347585966866L;

	/**
	 * 资料ID
	 */
	private Integer id;

	/**
	 * 资料文件名
	 */
	private String docname;
	
	/**
	 * 上传的文件名
	 */
	private String filename;

	/**
	 * 类型：10概要版，20完整版，默认为10
	 */
	private String doctype;
	
	/**
	 * 类型：1为标准格式，2为特殊格式
	 */
	private Integer showtype;
	
	/**
	 * productcode
	 */
	private String productcode;
	
	/**
	 * subtitle
	 */
	private String subtitle;

	/**
	 * 标题1
	 */
	private String title1;

	/**
	 * 内容1
	 */
	private String content1;
	private List<String> content1List;

	/**
	 * 标题2
	 */
	private String title2;

	/**
	 * 内容2
	 */
	private String content2;
	private List<String> content2List;

	/**
	 * 标题3
	 */
	private String title3;

	/**
	 * 内容3
	 */
	private String content3;
	private List<String> content3List;

	/**
	 * 标题4
	 */
	private String title4;

	/**
	 * 内容4
	 */
	private String content4;
	private List<String> content4List;

	/**
	 * 标题5
	 */
	private String title5;

	/**
	 * 内容5
	 */
	private String content5;
	private List<String> content5List;
	
	/**
	 * 图片、二维码URL前缀
	 */
	private String url_pre;
	
	//图片列表
	private List<String> pictureList;
	
	/**
	 * PDF图片名1（初期显示用）
	 */
	private String picture1;

	/**
	 * PDF图片名2
	 */
	private String picture2;

	/**
	 * PDF图片名3
	 */
	private String picture3;

	/**
	 * PDF图片名4
	 */
	private String picture4;

	/**
	 * PDF图片名5
	 */
	private String picture5;
	
	/**
	 * PDF图片名6
	 */
	private String picture6;
	
	/**
	 * PDF图片名7
	 */
	private String picture7;
	
	/**
	 * PDF图片名8
	 */
	private String picture8;
	
	/**
	 * PDF图片名9
	 */
	private String picture9;
	
	/**
	 * PDF图片名10
	 */
	private String picture10;

	/**
	 * 二维码图片名
	 */
	private String qrcode;

	/**
	 * 备用字段1
	 */
	private String res01;

	/**
	 * 备用字段2
	 */
	private String res02;

	/**
	 * 备用字段3
	 */
	private String res03;

	/**
	 * 备用字段4
	 */
	private String res04;

	/**
	 * 备用字段5
	 */
	private String res05;

	/**
	 * 备用字段6
	 */
	private String res06;

	/**
	 * 备用字段7
	 */
	private String res07;

	/**
	 * 备用字段8
	 */
	private String res08;

	/**
	 * 备用字段9
	 */
	private String res09;

	/**
	 * 备用字段10
	 */
	private String res10;

	/**
	 * 备注
	 */
	private String note;

	/**
	 * 状态：0无效，1有效
	 */
	private Integer status;

	/**
	 * 数据创建者
	 */
	private String createuser;

	/**
	 * 创建时间
	 */
	private String createdate;

	/**
	 * 更新者
	 */
	private String updateuser;

	/**
	 * 更新时间
	 */
	private String updatedate;

	public Integer getId() {
		return id;
	}

	public String getDocname() {
		return docname;
	}

	public String getTitle1() {
		return title1;
	}

	public String getContent1() {
		return content1;
	}

	public String getTitle2() {
		return title2;
	}

	public String getContent2() {
		return content2;
	}

	public String getTitle3() {
		return title3;
	}

	public String getContent3() {
		return content3;
	}

	public String getTitle4() {
		return title4;
	}

	public String getContent4() {
		return content4;
	}

	public String getTitle5() {
		return title5;
	}

	public String getContent5() {
		return content5;
	}

	public String getPicture1() {
		return picture1;
	}

	public String getPicture2() {
		return picture2;
	}

	public String getPicture3() {
		return picture3;
	}

	public String getPicture4() {
		return picture4;
	}

	public String getPicture5() {
		return picture5;
	}

	public String getQrcode() {
		return qrcode;
	}

	public String getRes01() {
		return res01;
	}

	public String getRes02() {
		return res02;
	}

	public String getRes03() {
		return res03;
	}

	public String getRes04() {
		return res04;
	}

	public String getRes05() {
		return res05;
	}

	public String getRes06() {
		return res06;
	}

	public String getRes07() {
		return res07;
	}

	public String getRes08() {
		return res08;
	}

	public String getRes09() {
		return res09;
	}

	public String getRes10() {
		return res10;
	}

	public String getNote() {
		return note;
	}

	public Integer getStatus() {
		return status;
	}

	public String getCreateuser() {
		return createuser;
	}

	public String getCreatedate() {
		return createdate;
	}

	public String getUpdateuser() {
		return updateuser;
	}

	public String getUpdatedate() {
		return updatedate;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setDocname(String docname) {
		this.docname = docname;
	}

	public void setTitle1(String title1) {
		this.title1 = title1;
	}

	public void setContent1(String content1) {
		this.content1 = content1;
	}

	public void setTitle2(String title2) {
		this.title2 = title2;
	}

	public void setContent2(String content2) {
		this.content2 = content2;
	}

	public void setTitle3(String title3) {
		this.title3 = title3;
	}

	public void setContent3(String content3) {
		this.content3 = content3;
	}

	public void setTitle4(String title4) {
		this.title4 = title4;
	}

	public void setContent4(String content4) {
		this.content4 = content4;
	}

	public void setTitle5(String title5) {
		this.title5 = title5;
	}

	public void setContent5(String content5) {
		this.content5 = content5;
	}

	public void setPicture1(String picture1) {
		this.picture1 = picture1;
	}

	public void setPicture2(String picture2) {
		this.picture2 = picture2;
	}

	public void setPicture3(String picture3) {
		this.picture3 = picture3;
	}

	public void setPicture4(String picture4) {
		this.picture4 = picture4;
	}

	public void setPicture5(String picture5) {
		this.picture5 = picture5;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	public void setRes01(String res01) {
		this.res01 = res01;
	}

	public void setRes02(String res02) {
		this.res02 = res02;
	}

	public void setRes03(String res03) {
		this.res03 = res03;
	}

	public void setRes04(String res04) {
		this.res04 = res04;
	}

	public void setRes05(String res05) {
		this.res05 = res05;
	}

	public void setRes06(String res06) {
		this.res06 = res06;
	}

	public void setRes07(String res07) {
		this.res07 = res07;
	}

	public void setRes08(String res08) {
		this.res08 = res08;
	}

	public void setRes09(String res09) {
		this.res09 = res09;
	}

	public void setRes10(String res10) {
		this.res10 = res10;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

	public String getPicture6() {
		return picture6;
	}

	public void setPicture6(String picture6) {
		this.picture6 = picture6;
	}

	public String getPicture7() {
		return picture7;
	}

	public void setPicture7(String picture7) {
		this.picture7 = picture7;
	}

	public String getPicture8() {
		return picture8;
	}

	public void setPicture8(String picture8) {
		this.picture8 = picture8;
	}

	public String getPicture9() {
		return picture9;
	}

	public void setPicture9(String picture9) {
		this.picture9 = picture9;
	}

	public String getPicture10() {
		return picture10;
	}

	public void setPicture10(String picture10) {
		this.picture10 = picture10;
	}

	public String getUrl_pre() {
		url_pre = PropertiesConfig.getPropertiesValueByKey("DOMAIN_URL") + "ge_pic/" + id + "/";
		return url_pre;
	}

	public void setUrl_pre(String url_pre) {
		this.url_pre = url_pre;
	}

	public String getProductcode() {
		return productcode;
	}

	public void setProductcode(String productcode) {
		this.productcode = productcode;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public List<String> getContent1List() {
		List<String> list = new ArrayList<String>();
		if(content1 != null && !"".equals(content1)) {
			String s[] = content1.split("\r\n");
			for(String ss : s) {
				list.add(ss);
			}
		}
		content1List = list;
		return content1List;
	}

	public void setContent1List(List<String> content1List) {
		this.content1List = content1List;
	}

	public List<String> getContent2List() {
		List<String> list = new ArrayList<String>();
		if(content2 != null && !"".equals(content2)) {
			String s[] = content2.split("\r\n");
			for(String ss : s) {
				list.add(ss);
			}
		}
		content2List = list;
		return content2List;
	}

	public void setContent2List(List<String> content2List) {
		this.content2List = content2List;
	}

	public List<String> getContent3List() {
		List<String> list = new ArrayList<String>();
		if(content3 != null && !"".equals(content3)) {
			String s[] = content3.split("\r\n");
			for(String ss : s) {
				list.add(ss);
			}
		}
		content3List = list;
		return content3List;
	}

	public void setContent3List(List<String> content3List) {
		this.content3List = content3List;
	}

	public List<String> getContent4List() {
		List<String> list = new ArrayList<String>();
		if(content4 != null && !"".equals(content4)) {
			String s[] = content4.split("\r\n");
			for(String ss : s) {
				list.add(ss);
			}
		}
		content4List = list;
		return content4List;
	}

	public void setContent4List(List<String> content4List) {
		this.content4List = content4List;
	}

	public List<String> getContent5List() {
		List<String> list = new ArrayList<String>();
		if(content5 != null && !"".equals(content5)) {
			String s[] = content5.split("\r\n");
			for(String ss : s) {
				list.add(ss);
			}
		}
		content5List = list;
		return content5List;
	}

	public void setContent5List(List<String> content5List) {
		this.content5List = content5List;
	}

	public List<String> getPictureList() {
		List<String> list = new ArrayList<String>();
		if(picture1 != null && !"".equals(picture1)) {
			list.add(this.getUrl_pre() + picture1);
		}
		if(picture2 != null && !"".equals(picture2)) {
			list.add(this.getUrl_pre() + picture2);
		}
		if(picture3 != null && !"".equals(picture3)) {
			list.add(this.getUrl_pre() + picture3);
		}
		if(picture4 != null && !"".equals(picture4)) {
			list.add(this.getUrl_pre() + picture4);
		}
		if(picture5 != null && !"".equals(picture5)) {
			list.add(this.getUrl_pre() + picture5);
		}
		pictureList = list;
		return pictureList;
	}

	public void setPictureList(List<String> pictureList) {
		this.pictureList = pictureList;
	}

	public Integer getShowtype() {
		return showtype;
	}

	public void setShowtype(Integer showtype) {
		this.showtype = showtype;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getDoctype() {
		return doctype;
	}

	public void setDoctype(String doctype) {
		this.doctype = doctype;
	}
}
