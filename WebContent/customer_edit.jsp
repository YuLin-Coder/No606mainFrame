<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="scripts/jquery/jquery-1.7.1.js"></script>
<link href="style/authority/basic_layout.css" rel="stylesheet" type="text/css">
<link href="style/authority/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
window.onload=function()
{
   var sel=document.getElementById("customersex");
   var sex="<%=request.getParameter("customersex")==null?"":request.getParameter("customersex")%>";
   if(sex=="未知"||sex=="")
	   sel[0].selected="true";
   else if(sex=="男")
	   sel[1].selected="true";
   else
	   sel[2].selected="true";
}
</script>

</head>
<body>
<form id="customerForm" name="customerForm" action="InsertCustomer" method="post">
	<div id="container">
		<div id="nav_links">
			当前位置：基础数据&nbsp;>&nbsp;<span style="color: #1A5CC6;">客户信息添加</span>
		</div>
		<div class="ui_content">
			<table  cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
				<tr>
					<td width="15%" class="ui_text_rt" >客户姓名</td>
					<td width="32%" class="ui_text_lt">
						<input type="text" name="customername" value="<%=request.getParameter("customername")==null?"":request.getParameter("customername")%>"  class="ui_input_txt04"/>
					</td>
					
				</tr>
				<tr>
					<td class="ui_text_rt">身份证号</td>
					<td class="ui_text_lt">
						<input type="text" name="customerid" value="<%=request.getParameter("customerid")==null?"":request.getParameter("customerid")%>"  class="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">性别</td>
					<td class="ui_text_lt">
						<select id="customersex" name="customersex" class="ui_select01">
                            <option value="未知" >--请选择--</option>
                            <option value="男" >男</option>
                            <option value="女">女</option>
                        </select>
					</td>
				
				<tr>
					<td class="ui_text_rt">电话号码</td>
					<td class="ui_text_lt">
						<input type="text" name="customertel"  value="<%=request.getParameter("customertel")==null?"":request.getParameter("customertel")%>" class="ui_input_txt04"/></td>
				</tr>
				<tr>
					<td class="ui_text_rt">地址</td>
					<td class="ui_text_lt">
						<input type="text" name="customeraddress" value="<%=request.getParameter("customeraddress")==null?"":request.getParameter("customeraddress")%>" class="ui_input_txt05"/></td>
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