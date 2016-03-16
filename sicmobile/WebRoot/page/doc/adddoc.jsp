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
<title>PDF文件上传</title>
<script type="text/javascript">
	function add() {
		if(checkItem()) {
			if(confirm("确定提交吗？")) {
				document.mainform.action = "../doc/uploadDocAction.action";
				document.mainform.submit();
			}
		}
	}
	
	//验证数据格式
	function checkItem() {
		if($("#addPdfFile").val() == "") {
			alert("请选择PDF文件！");
			return false;
		}
		var path = $("#addPdfFile").val();
		var pos = path.lastIndexOf("\\");
		$("#filename").val(path.substring(pos + 1));
		return true;
	}
	
	function goUserList() {
		window.location.href = "../user/queryUserAction.action";
	}
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
							<font style="font-size: 20px;">请上传新的PDF文档，并生成二维码供使用！</font>
						</td>
					</tr>
					<tr style="height: 70px;">
						<td align="center">
							<font style="font-size: 20px; vertical-align: bottom;">上传文档：</font><input name="addPdfFile" id="addPdfFile" type="file" style="height: 25px;" size="60"/>
						</td>
					</tr>
					<tr>
						<td align="center">
							<input class="input80" style="text-align: center; width: 90px;" type="button" value="上传" onclick="add();"/>
						</td>
					</tr>
				</table>
			</s:form>
		</div>
	</div>
</body>
</html>
