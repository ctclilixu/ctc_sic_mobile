<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link href="<%=request.getContextPath()%>/css/skin.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	function logout() {
		if(confirm("确定退出吗？")) {
			window.parent.location.href = '<c:url value="/login/logoutAction.action"></c:url>';
		}
	}
	
	function goHome() {
		var urlList = parent.window.frames['leftFrame'].document.getElementsByName("urlList");
		for(var i = 0; i < urlList.length; i++) {
			urlList[i].style.color = "";
		}
		parent.window.frames['mainFrame'].location = '<%=request.getContextPath()%>/frame/showMainFrameAction.action';
	}
</script>
</head>
<body leftmargin="0" topmargin="0">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" class="admin_topbg">
	<tr>
		<td style="background-color: #4474BB;">
			<div style="margin-left: 5px;"><B>GE扫描读取后台管理系统</B></div>
		</td>
	</tr>
</table>
</body>
</html>