<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="scripts/jquery/jquery-1.7.1.js"></script>
<link href="style/authority/basic_layout.css" rel="stylesheet" type="text/css">
<link href="style/authority/common_style.css" rel="stylesheet" type="text/css">
</head>
<body>
<form id="supplierForm" name="supplierForm" action="UpdateSupplier" method="post">
	<div id="container">
		<div id="nav_links">
			当前位置：基础数据&nbsp;>&nbsp;<span style="color: #1A5CC6;">供应商信息编辑</span>
		</div>
		<div class="ui_content">
			<table  cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
				<tr>
					<td width="15%" class="ui_text_rt" >供应商编号</td>
					<td width="32%" class="ui_text_lt">
						<input type="text" name="sid" value="<%=request.getParameter("sid")==null?"":request.getParameter("sid")%>"  class="ui_input_txt04"/>
					</td>
					
				</tr>
				<tr>
					<td class="ui_text_rt">供应商名称</td>
					<td class="ui_text_lt">
					<%String str = new String(request.getParameter("sname").getBytes("iso-8859-1"), "utf-8");   %>
						<input type="text" name="sname" value="<%=str%>"  class="ui_input_txt04"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">供应商电话</td>
					<td class="ui_text_lt">
						<input type="text" name="suppliertel"  value="<%=request.getParameter("suppliertel")==null?"":request.getParameter("suppliertel")%>" class="ui_input_txt04" onkeyup="checkFyFh();"/>	
					</td>
				</tr>
				
				<tr>
					<td class="ui_text_rt">供应商地址</td>
					<td class="ui_text_lt">
					<%str = new String(request.getParameter("supplieradd").getBytes("iso-8859-1"), "utf-8");   %>
						<input type="text" name="supplieradd" value="<%=str %>" class="ui_input_txt05"/></td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td class="ui_text_lt">
						&nbsp;<input id="submitbutton" type="submit" value="提交" class="ui_input_btn01"/>
						&nbsp;<input id="cancelbutton" type="button" onclick="javascript:window.history.back(-1);" value="取消" class="ui_input_btn01"/>
					</td>
				</tr>
			</table>
	  </div>
	</div>
	<input name="preid" type="hidden" value="<%=request.getParameter("preid") %>"></input>
</form>
	<%
  		//从request域中取出信息
  		String msg = (String)request.getAttribute("msg");
		if(msg!=null){
			System.out.println(msg);
		}else{
			msg="\n";
		}
 	%>
<script type="text/javascript" language="javascript">
	var checkMsg= "<%=msg%>";
	if(checkMsg!=null||checkMsg!="")
		alert(checkMsg);
</script>
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>