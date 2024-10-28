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
		document.getElementById("servicetime").value=myYear + "-" + myMonth + "-" + myDay;
		var sel=document.getElementById("service");
		   var sex="<%=request.getParameter("service")==null?"":request.getParameter("service")%>";
		   if(sex=="加装")
			   sel[0].selected="true";
		   else if(sex=="维修")
			   sel[1].selected="true";
		   else
			   sel[2].selected="true";
	}
</script>
</head>
<body>
<form id="submitForm" name="submitForm" action="CarParts" method="post">
	<div id="container">
		<div id="nav_links">
			当前位置：基础数据&nbsp;>&nbsp;<span style="color: #1A5CC6;">车辆额外服务编辑</span>
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
					<td width="15%" class="ui_text_rt" >配件编号</td>
					<td width="32%" class="ui_text_lt">
						<input type="text" name="partsid" value="<%=request.getParameter("partsid")==null?"":request.getParameter("partsid")%>"  class="ui_input_txt04"/>
					</td>
					
				</tr>
				
				<tr>
					<td width="15%" class="ui_text_rt" >仓库编号</td>
					<td width="32%" class="ui_text_lt">
						<input type="text" name="storageid" value="<%=request.getParameter("storageid")==null?"":request.getParameter("storageid")%>"  class="ui_input_txt04"/>
					</td>
					
				</tr>
				
				<tr>
					<td class="ui_text_rt">服务时间</td>
					<td class="ui_text_lt">
						<input type="date" id="servicetime" name="time" style="font-family: 微软雅黑" class="ui_input_txt06"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">服务类型</td>
					<td class="ui_text_lt">
						<select id="service" name="service" class="ui_select01">
                            <option value="加装" >加装</option>
                            <option value="维修" >维修</option>
                            <option value="保养">保养</option>
                        </select>	
					</td>
				
				<tr>
					<td class="ui_text_rt">员工ID</td>
					<td class="ui_text_lt">
						<input type="text" name="staffid" value="<%=request.getParameter("staffid")==null?"":request.getParameter("staffid")%>"  class="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">服务金额</td>
					<td class="ui_text_lt">
						<input type="text" name="service_price" value="<%=request.getParameter("service_price")==null?"":request.getParameter("service_price")%>"  class="ui_input_txt04"/>
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