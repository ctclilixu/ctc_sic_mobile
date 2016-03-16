<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/Calendar3.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.5.1.js"></script>
<style type="text/css">
ul,li{ list-style:none;}
</style>
<title>PDF文件上传成功</title>
<script type="text/javascript">
</script>
</head>
<body>
	<div id="containermain">
		<div class="content">
			<s:form id="mainform" name="mainform" method="POST" enctype="multipart/form-data" cssStyle="height:100%;">
				<s:hidden name="filename" id="filename"></s:hidden>
				<table width="100%" style="height:100%;" border="0" cellpadding="5" cellspacing="0">
					<tr style="height: 120px;">
						<td style="color:red; vertical-align: bottom;" align="center"><s:actionmessage /></td>
					</tr>
					<tr>
						<td align="center">
							<font style="font-size: 20px;">已成功上传，并生成以下二维码！</font>
						</td>
					</tr>
					<tr style="height: 70px;">
						<td align="center">
							<font style="font-size: 16px;">需查看刚上传的文档，请去<a href="../doc/showDocManageAction.action">PDF资料管理</a>查看</font>
						</td>
					</tr>
					<tr>
						<td align="center">
							<img src="<s:property value="qr_url"/>" alt="" />
						</td>
					</tr>
					<tr>
						<td align="center">
							<a href="#">下载到本地</a>
						</td>
					</tr>
				</table>
			</s:form>
		</div>
	</div>
</body>
</html>
