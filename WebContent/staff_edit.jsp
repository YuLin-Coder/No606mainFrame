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
   var sel=document.getElementById("staffsex");
   var sex="<%=request.getParameter("staffsex")==null?"":request.getParameter("staffsex")%>";
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
<form id="staffForm" name="staffForm" action="InsertStaff" method="post">
	<div id="container">
		<div id="nav_links">
			当前位置：基础数据&nbsp;>&nbsp;<span style="color: #1A5CC6;">员工信息添加</span>
		</div>
		<div class="ui_content">
			<table  cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
				<tr>
					<td width="15%" class="ui_text_rt" ><span style="color:red">*</span>姓名</td>
					<td width="32%" class="ui_text_lt">
						<input type="text" name="staffname" value="<%=request.getParameter("staffname")==null?"":request.getParameter("staffname")%>"  class="ui_input_txt04"/>
					</td>
					
				</tr>

				<tr>
					<td class="ui_text_rt"><span style="color:red">*</span>身份证号</td>
					<td class="ui_text_lt">
						<input type="text" name="staffid" value="<%=request.getParameter("staffid")==null?"":request.getParameter("staffid")%>"  class="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">性别</td>
					<td class="ui_text_lt">
						<select id="staffsex" name="staffsex" class="ui_select01">
                            <option value="未知" >--请选择--</option>
                            <option value="男" >男</option>
                            <option value="女">女</option>
                        </select>
					</td>
				
				<tr>
					<td class="ui_text_rt"><span style="color:red">*</span>电话号码</td>
					<td class="ui_text_lt">
						<input type="text" name="stafftel"  value="<%=request.getParameter("stafftel")==null?"":request.getParameter("stafftel")%>" class="ui_input_txt04"/></td>
				</tr>
				<tr>
					<td class="ui_text_rt"><span style="color:red">*</span>员工职务</td>
					<td class="ui_text_lt">
						<input type="text" name="staffjob"  value="<%=request.getParameter("staffjob")==null?"":request.getParameter("staffjob")%>" class="ui_input_txt04"/></td>
				</tr>
				<tr>
					<td class="ui_text_rt"><span style="color:red">*</span>地址</td>
					<td class="ui_text_lt">
						<input type="text" name="staffaddress" value="<%=request.getParameter("staffaddress")==null?"":request.getParameter("staffaddress")%>" class="ui_input_txt05"/></td>
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