<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>发送申请</title>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<meta http-equiv="Content-Language" content="zh-CN" />
<meta name="keywords" content="#" />
<meta name="description" content="#" />
<meta name="author" content="#" />
<link href="<%=request.getContextPath()%>/page/web/css/apply.css" type="text/css" rel="stylesheet" />
<!--下面代码代表在新页面打开窗口-->
<base target="_blank"  />
<script type="text/javascript">
function apply() {
	//数据CHECK
	document.mainform.action = '<c:url value="/web/applyAction.action"></c:url>';
	document.mainform.submit();
}
</script>
</head>
<body>
<div id="container">
	<div class="applytitle">
		<p>申请读取详情</p>
	</div>
	<s:form id="mainform" name="mainform" target="_blank" method="POST" cssClass="applyform">
		<ul>
			<li>
				<s:actionmessage />
			</li>
			<li>
				<label for="name">客户名称</label>
				<s:textfield name="addCustomerDto.customername" id="customername" cssClass="name" maxlength="64" theme="simple"></s:textfield>
			</li>
			<li>
				<label for="name">企业名称</label>
				<s:textfield name="addCustomerDto.companyname" id="companyname" cssClass="name" maxlength="64" theme="simple"></s:textfield>
			</li>
			<li>
				<label >手机号码</label>
				<s:textfield name="addCustomerDto.phone" id="phone" cssClass="number" maxlength="16" theme="simple"></s:textfield>
			</li>
			<li>
				<label for="password">邮箱</label>
				<s:textfield name="addCustomerDto.mail" id="mail" cssClass="email" maxlength="64" theme="simple"></s:textfield>
			</li>
		</ul>
		<div class="submitbox"><input type="button" value="申请" onclick="apply();" class="submit" /></div>
	</s:form>
</div>
<div class="clear"></div>
</body>
</html>
