<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="scripts/jquery/jquery-1.7.1.js"></script>
<link href="style/authority/basic_layout.css" rel="stylesheet" type="text/css">
<link href="style/authority/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	window.onload=function(){
		var time = new Date();
		var myYear = time.getFullYear();
		var myMonth = ("0" + (time.getMonth() + 1)).slice(-2); 
		var myDay=("0" + time.getDate()).slice(-2);
		document.getElementById("salestime").value=myYear + "-" + myMonth + "-" + myDay;
	}
</script>
</head>
<body>
<form id="submitForm" name="submitForm" action="CarSales" method="post">
	<div id="container">
		<div id="nav_links">
			当前位置：基础数据&nbsp;>&nbsp;<span style="color: #1A5CC6;">车辆销售单编辑</span>
		</div>
		<div class="ui_content">
			<table  cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
				<tr>
					<td width="15%" class="ui_text_rt" >合格证号</td>
					<td width="32%" class="ui_text_lt">
						<input type="text" name="certificateid" value="<%=request.getParameter("certificateid")==null?"":request.getParameter("certificateid")%>"  class="ui_input_txt04"/>
					</td>
					
				</tr>
				
				<tr>
					<td class="ui_text_rt">销售日期</td>
					<td class="ui_text_lt">
						<input type="date" id="salestime" name="salestime" value="<%=request.getParameter("salestime")==null?"":request.getParameter("salestime")%>" style="font-family: 微软雅黑" class="ui_input_txt06"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">销售金额</td>
					<td class="ui_text_lt">
						<input type="text" name="payprice" value="<%=request.getParameter("payprice")==null?"":request.getParameter("payprice")%>" class="ui_input_txt04"/>
					</td>
				
				<tr>
					<td class="ui_text_rt">员工ID</td>
					<td class="ui_text_lt">
						<input type="text" name="staffid" value="<%=request.getParameter("staffid")==null?"":request.getParameter("staffid")%>"  class="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">客户ID</td>
					<td class="ui_text_lt">
						<input type="text" name="customerid" value="<%=request.getParameter("customerid")==null?"":request.getParameter("customerid")%>"  class="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td class="ui_text_lt">
						&nbsp;<input id="submitbutton" type="submit" value="提交" class="ui_input_btn01"/>
						&nbsp;<input id="cancelbutton" type="button" onclick="" value="取消" class="ui_input_btn01"/>
					</td>
				</tr>
			</table>
	  </div>
	</div>
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
</body>
</html>