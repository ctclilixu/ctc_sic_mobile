<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/Calendar3.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.5.1.js"></script>
<title>PDF资料信息一览</title>
<script type="text/javascript">
	$(document).ready(function(){
		var h = screen.availHeight; 
		$("#container").height(h - 60);
	});

	function getSelectedID() {
		var id = "";
		var list = document.getElementsByName("radioKey");
		for(var i = 0; i < list.length; i++) {
			if(list[i].checked) {
				id = list[i].value;
				break;
			}
		}
		return id;
	}
	
	function queryList() {
		$("#queryCreatedatelow").val($("#tmpQueryCreatedatelow").val());
		$("#queryCreatedatehigh").val($("#tmpQueryCreatedatehigh").val());
		document.mainform.action = '../doc/queryDocAction.action';
		document.mainform.submit();
	}
	
	//翻页
	function changePage(pageNum) {
		document.getElementById("startIndex").value = pageNum;
		document.mainform.action = '../doc/turnDocAction.action';
		document.mainform.submit();
	}
	
	//页跳转
	function turnPage() {
		var totalPage = "${page.totalPage}";
		var turnPage = document.getElementById("pagenum").value;
		//判断是否输入页码
		if ('' != turnPage) {
			//判断页码是否是大于0的数字
			if(!iscInteger(turnPage)){
				alert("页码必须是大于0的整数！");
				return;
			}
			turnPage = parseInt(turnPage);
			if(turnPage < 1){
				alert("页码必须是大于0的整数！");
				return;
			}
			//判断页码大小是否正确
			if(turnPage > parseInt(totalPage)){
				alert("页码不能超过最大页数！");
				return;
			}
			//换页
			changePage(turnPage - 1);
		} else {
			alert("页码不能为空！");
			return;
		}	
	}
	
	function delDoc(id) {
		if(confirm("确定删除该文件吗？")) {
			$("#delDocId").val(id);
			document.mainform.action = '../doc/delDocAction.action';
			document.mainform.submit();
		}
	}
</script>
</head>
<body>
	<div id="containermain">
		<div class="content">
			<jsp:include page="../info.jsp" flush="true" />
			<div class="tittle">
				<div class="icons"><a class="home" href="#" onclick="goHome();">返回首页</a><a class="quit" href="#" onclick="logout();">退出</a></div>
				<div class="tittle_left">
				</div>
				<div class="tittle_center">
					PDF资料信息一览
				</div>
				<div class="tittle_right">
				</div>
			</div>
			<s:form id="mainform" name="mainform" method="POST">
				<s:hidden name="startIndex" id="startIndex"/>
				<s:hidden name="delDocId" id="delDocId"/>
				<s:hidden name="queryCreatedatelow" id="queryCreatedatelow"/>
				<s:hidden name="queryCreatedatehigh" id="queryCreatedatehigh"/>
				<div class="searchbox update">
					<div class="box1" >
						<label class="pdf10" style="width:60px">文件名</label>
						<div class="box1_left"></div>
						<div class="box1_center">
							<s:textfield name="queryDocname" id="queryDocname" cssStyle="width:235px;" maxlength="50" theme="simple"></s:textfield>
						</div>
						<div class="box1_right"></div>
					</div>
					<div class="box1" style="display: none;">
						<label class="pdf10" style="width:60px">创建时间</label>
						<div class="box1_left"></div>
						<div class="box1_center date_input">
							<input type="text" id="tmpQueryCreatedatelow" disabled="disabled" style="width:135px;" value="<s:property value="queryCreatedatelow"/>"/>
							<a class="date" href="javascript:;" onclick="new Calendar().show(document.getElementById('tmpQueryCreatedatelow'));"></a>
						</div>
						<div class="box1_right"></div>
					</div>
					<div class="box1" style="display: none;">
						<label class="pdf10" style="width:15px">-</label>
						<div class="box1_left"></div>
						<div class="box1_center date_input">
							<input type="text" id="tmpQueryCreatedatehigh" disabled="disabled" style="width:135px;" value="<s:property value="queryCreatedatehigh"/>"/>
							<a class="date" href="javascript:;" onclick="new Calendar().show(document.getElementById('tmpQueryCreatedatehigh'));"></a>
						</div>
						<div class="box1_right"></div>
					</div>
					<div class="btn">
						<div class="box1_left"></div>
						<div class="box1_center">
							<input type="button" class="input40" value="检索" onclick="queryList();"/>
						</div>
						<div class="box1_right"></div>
					</div>
					<div class="box1" style="margin-top:-45px; margin-left: -350px; color: red;">
						<s:actionmessage />
					</div>
				</div>
				<div class="data_table" style="padding:0px;">
					<div class="tab_tittle">
						<table width="100%" border="1" cellpadding="5" cellspacing="0">
						</table>
					</div>
					<div class="tab_content" style="height: 350px;">
						<table class="info_tab" width="100%" border="1" cellpadding="5" cellspacing="0">
							<tr class="tittle">
								<td width="5%"></td>
								<td width="5%">序号</td>
								<td width="15%">文件名</td>
								<td width="30%">上传PDF文件名</td>
								<td width="8%">文件类型</td>
								<!--
								<td width="6%">状态</td>
								-->
								<td width="6%">创建者</td>
								<td width="15%">创建时间</td>
								<td width="16%">操作</td>
							</tr>
							<s:iterator id="docList" value="docList" status="st1">
								<s:if test="#st1.odd==true">
									<tr class="tr_bg">
								</s:if>
								<s:else>
									<tr>							
								</s:else>
									<td><input name="radioKey" type="radio" value="<s:property value="id"/>"/></td>
									<td><s:property value="page.pageSize * (page.nextIndex - 1) + #st1.index + 1"/></td>
									<td>
										<div noWrap style="text-overflow:ellipsis;overflow:hidden">
											<s:property value="docname"/>
										</div>
									</td>
									<td>
										<a target="_blank" href="<s:property value="url_pre"/><s:property value="id"/>.pdf"><s:property value="filename"/></a>
									</td>
									<td>
										<s:if test="%{doctype == 20}">
											完整版
										</s:if>
										<s:else>
											概要版
										</s:else>
									</td>
									<!--
									<td>
										<s:if test="%{status == 1}">
											有效
										</s:if>
										<s:else>
											无效
										</s:else>
									</td>
									-->
									<td>
										<s:property value="createuser"/>
									</td>
									<td><s:property value="createdate"/></td>
									<td>
										<a href="../doc/downloadPic.action?downloadPicId=<s:property value="id"/>" target="_self">二维码下载</a>
										　　<a href="#" onclick="delDoc('<s:property value="id"/>');">删除</a>
									</td>
								</tr>
							</s:iterator>
						</table>
					</div>
					<div class="pages">
						<ul>
							<li>第<strong>${page.startIndex + 1}</strong>页/共<strong>${page.totalPage==0?1:page.totalPage}</strong>页/共<strong>${page.totalCount}</strong>条记录</li>
							<li class="mgl15">跳转到
								<input type="text" id="pagenum" class="text" maxlength="4" size="4"/>
								<input type="button" value="GO" onclick="javascript:turnPage();"/>
							</li>
							<li class="mgl15">
								<a class="first" href="#" onclick="changePage(0);">首页</a>
							</li>
							<li>
								<s:if test="%{page.startIndex <= 0}">
									<a class="last" href="#">上一页</a>
								</s:if>
								<s:else>
									<a class="next" href="#" onclick="changePage('${page.previousIndex}');">上一页</a>
								</s:else>
							</li>
							<li>
								<s:if test="%{page.nextIndex > page.totalPage - 1}">
									<a class="last" href="#">下一页</a>
								</s:if>
								<s:else>
									<a class="next" href="#" onclick="changePage('${page.nextIndex}');">下一页</a>
								</s:else>
							</li>
							<li>
								<a class="next" href="#" onclick="changePage('${page.totalPage - 1}');">末页</a>
							</li>
						</ul>
					</div>
				</div>
			</s:form>
		</div>
	</div>
</body>
</html>
