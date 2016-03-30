<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><s:property value="docInfoDto.docname"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<meta http-equiv="Content-Language" content="zh-CN" />
<meta name="keywords" content="#" />
<meta name="description" content="#" />
<meta name="author" content="#" />
<link href="<%=request.getContextPath()%>/page/web/css/haocss.css" type="text/css" rel="stylesheet" />
<!--下面代码代表在新页面打开窗口-->
<base target="_blank"  />
</head>
<body>
<div id="container">
	<div class="top">
		<img src="<%=request.getContextPath()%>/page/web//image/ge.png" />
	</div>
	<s:if test="%{docInfoDto.showtype != 2}">
		<div class="product_code">
			<strong><p>Product Code&nbsp;</p><p><s:property value="docInfoDto.productcode"/></p></strong>
		</div>
	</s:if>
	<div class="box1">
		<div class="title1">
			<p><s:property value="docInfoDto.title1"/></p>
		</div>
		<div class="title2">
			<p><s:property value="docInfoDto.subtitle"/></p>
		</div>
	</div> 
	<div class="box2">
		<s:iterator id="content1List" value="docInfoDto.content1List" status="st1">
			<p><s:property /></p>
		</s:iterator>
	</div>
	<s:if test='%{docInfoDto.title2 != null && docInfoDto.title2 != ""}'>
		<s:if test="%{docInfoDto.showtype == 2}">
			<div class="samebox">
				<div class="title3"><s:property value="docInfoDto.title2"/></div>
			</div>
			<div class="box2">
				<p><s:property value="docInfoDto.content2"/></p>
			</div>
		</s:if>
		<s:else>
			<div class="samebox">
				<div class="title3"><s:property value="docInfoDto.title2"/></div>
				<ul>
					<s:iterator id="content2List" value="docInfoDto.content2List" status="st1">
						<li><s:property /></li>
					</s:iterator>
				</ul>
			</div>
		</s:else>
	</s:if>
	<s:if test='%{docInfoDto.title3 != null && docInfoDto.title3 != ""}'>
		<s:if test="%{docInfoDto.showtype == 2}">
			<div class="samebox">
				<div class="title3"><s:property value="docInfoDto.title3"/></div>
			</div>
			<div class="box2">
				<p><s:property value="docInfoDto.content3"/></p>
			</div>
		</s:if>
		<s:else>
			<div class="samebox">
				<div class="title3"><s:property value="docInfoDto.title3"/></div>
				<ul>
					<s:iterator id="content3List" value="docInfoDto.content3List" status="st1">
						<li><s:property /></li>
					</s:iterator>
				</ul>
			</div>
		</s:else>
	</s:if>
	<s:if test='%{docInfoDto.title4 != null && docInfoDto.title4 != ""}'>
		<s:if test="%{docInfoDto.showtype == 2}">
			<div class="samebox">
				<div class="title3"><s:property value="docInfoDto.title4"/></div>
			</div>
			<div class="box2">
				<p><s:property value="docInfoDto.content4"/></p>
			</div>
		</s:if>
		<s:else>
			<div class="samebox">
				<div class="title3"><s:property value="docInfoDto.title4"/></div>
				<ul>
					<s:iterator id="content4List" value="docInfoDto.content4List" status="st1">
						<li><s:property /></li>
					</s:iterator>
				</ul>
			</div>
		</s:else>
	</s:if>
	<s:if test='%{docInfoDto.title5 != null && docInfoDto.title5 != ""}'>
		<s:if test="%{docInfoDto.showtype == 2}">
			<div class="samebox">
				<div class="title3"><s:property value="docInfoDto.title5"/></div>
			</div>
			<div class="box2">
				<p><s:property value="docInfoDto.content5"/></p>
			</div>
		</s:if>
		<s:else>
			<div class="samebox">
				<div class="title3"><s:property value="docInfoDto.title5"/></div>
				<ul>
					<s:iterator id="content5List" value="docInfoDto.content5List" status="st1">
						<li><s:property /></li>
					</s:iterator>
				</ul>
			</div>
		</s:else>
	</s:if>
	<div class="box3">
		<ul>
			<s:iterator id="pictureList" value="docInfoDto.pictureList" status="st1">
				<li><img src="<s:property />" />
				</li>
			</s:iterator>
		</ul>
	</div>
	<div class="box4">
		<a class="botton" href='<%=request.getContextPath()%>/showapply.shtml?strApplyDocname=<s:property value="docInfoDto.docname"/>'>
			看全部内容请发送申请
		</a>
	</div>
</div>
<div class="clear"></div>
</body>
</html>
