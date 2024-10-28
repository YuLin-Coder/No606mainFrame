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
		document.getElementById("purchasetime").value=myYear + "-" + myMonth + "-" + myDay;
	}
</script>
</head>
<body>
<form id="submitForm" name="submitForm" action="/xngzf/archives/initFangyuan.action" method="post">
	<input type="hidden" name="fyID" value="14458625716623" id="fyID"/>
	<div id="container">
		<div id="nav_links">
			当前位置：基础数据&nbsp;>&nbsp;<span style="color: #1A5CC6;">汽车采购添加</span>
		</div>
		<div class="ui_content">
			<table  cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
				<tr>
					<td width="23%" class="ui_text_rt" ><span style="color:red">*</span>合格证号</td>
                    <td width="21%" class="ui_text_lt">
				
						<input readonly="readonly"  style="background-color:#eee" type="text" name="certificateid" value="<%=request.getParameter("certificateid")==null?"":request.getParameter("certificateid")%>"  class="ui_input_txt04"/>
                       
					</td>
					<td width="12%" class="ui_text_rt" ><span style="color:red">*</span>车牌号</td>
                    <td width="44%" class="ui_text_lt">
				
						<%String str = new String(request.getParameter("licenseid").getBytes("iso-8859-1"), "utf-8");   %>
						<input readonly="readonly"  style="background-color:#eee" type="text" name="licenseid" value="<%=str%>"  class="ui_input_txt04"/>
                       
					</td>
				</tr>
				
				<tr>
					<td class="ui_text_rt">客户ID</td>
					<td class="ui_text_lt">
						<input readonly="readonly"  style="background-color:#eee" type="text" name="customerid" value="<%=request.getParameter("customerid")==null?"":request.getParameter("customerid")%>"  class="ui_input_txt02"/>
					</td>
                        <td width="12%" class="ui_text_rt" ><span style="color:red">*</span>品牌</td>
                    <td class="ui_text_lt">
				
						<%str = new String(request.getParameter("brand").getBytes("iso-8859-1"), "utf-8");   %>
						<input readonly="readonly"  style="background-color:#eee" type="text" name="brand" value="<%=str%>"  class="ui_input_txt04"/>
                       
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">员工ID</td>
					<td class="ui_text_lt">
						<input readonly="readonly"  style="background-color:#eee" type="text" name="staffid" value="<%=request.getParameter("staffid")==null?"":request.getParameter("staffid")%>"  class="ui_input_txt02"/>
					</td>
                        <td width="12%" class="ui_text_rt" ><span style="color:red">*</span>车型</td>
                    <td class="ui_text_lt">
				
						<%str = new String(request.getParameter("cartype").getBytes("iso-8859-1"), "utf-8");   %>
						<input readonly="readonly"  style="background-color:#eee" type="text" name="cartype" value="<%=str%>"  class="ui_input_txt04"/>
                       
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">供应商编号</td>
					<td class="ui_text_lt">
						<input readonly="readonly"  style="background-color:#eee" type="text" name="supplierid" value="<%=request.getParameter("supplierid")==null?"":request.getParameter("supplierid")%>"  class="ui_input_txt02"/>
					</td>
                        <td width="12%" class="ui_text_rt" ><span style="color:red">*</span>售价</td>
                    <td class="ui_text_lt">
				
						<input readonly="readonly"  style="background-color:#eee" type="text" name="payprice" value="<%=request.getParameter("payprice")==null?"":request.getParameter("payprice")%>"  class="ui_input_txt04"/>
                       
					</td>
				</tr>
                <tr>
					<td class="ui_text_rt">销售日期</td>
					<td class="ui_text_lt">
						<input readonly="readonly"  style="background-color:#eee;font-family: 微软雅黑" type="date" id="time" name="salestime"  value="<%=request.getParameter("salestime")==null?"":request.getParameter("salestime")%>" class="ui_input_txt06"/>
					</td>
                    <td width="12%" class="ui_text_rt" ><span style="color:red">*</span>颜色</td>
                    <%str = new String(request.getParameter("color").getBytes("iso-8859-1"), "utf-8");   %>
                    <td class="ui_text_lt">
                    	<input readonly="readonly"  style="background-color:#eee" type="text" name="color" value="<%=str%>"  class="ui_input_txt04"/>
                       
					</td>
				</tr>
				
				<tr>
					<td class="ui_text_rt"></td>
					<td class="ui_text_lt"></td>
					
                     <td width="12%" class="ui_text_rt" ></td>
                     <td class="ui_text_lt"><input type="button" value="返回" onclick="javascript:window.history.back(-1);" class="ui_input_btn01"/>
					</td>
                   
				</tr>
			</table>
		</div>
	</div>
</form>
</body>
</html>