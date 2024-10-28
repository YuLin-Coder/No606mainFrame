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
<form id="staffForm" name="staffForm" action="UpdateStaff" method="post">
	<div id="container">
		<div id="nav_links">
			当前位置：基础数据&nbsp;>&nbsp;<span style="color: #1A5CC6;">员工信息编辑</span>
		</div>
		<div class="ui_content">
			<table  cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
				<tr>
					<td width="15%" class="ui_text_rt" >姓名</td>
					<td width="32%" class="ui_text_lt">
					<%String str = new String(request.getParameter("staffname").getBytes("iso-8859-1"), "utf-8");   %>
					<input type="text" id="name" name="sname" value="<%=str %>"  class="ui_input_txt04"/>
					</td>
					
				</tr>
                </tr>
				<tr>
					<td class="ui_text_rt">身份证号</td>
					<td class="ui_text_lt">
						<input type="text" name="sid" value="<%=request.getParameter("staffid")%>"  class="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">性别</td>
					<td class="ui_text_lt">
					<%str = new String(request.getParameter("staffsex").getBytes("iso-8859-1"), "utf-8");   %>
						<input type="text" name="ssex" value="<%=str%>"  class="ui_input_txt04"/>
					</td>
				
				<tr>
					<td class="ui_text_rt">电话号码</td>
					<td class="ui_text_lt">
						<input type="text" name="stel"  value="<%=request.getParameter("stafftel") %>" class="ui_input_txt04"/></td>
				</tr>
				<tr>
					<td class="ui_text_rt">员工职务</td>
					<td class="ui_text_lt">
					<%str = new String(request.getParameter("staffjob").getBytes("iso-8859-1"), "utf-8");   %>
						<input type="text" name="sjob"  value="<%=str %>" class="ui_input_txt04"/></td>
				</tr>
				<tr>
					<td class="ui_text_rt">地址</td>
					<td class="ui_text_lt">
					<%str = new String(request.getParameter("staffaddress").getBytes("iso-8859-1"), "utf-8");   %>
						<input type="text" name="saddress" value="<%=str %>" class="ui_input_txt05"/></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td class="ui_text_lt">
						&nbsp;<input id="submitbutton" type="submit" onclick="" value="修改" class="ui_input_btn01"/>
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
 	
</body>
</html>