<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="scripts/jquery/jquery-1.7.1.js"></script>
<link href="style/authority/basic_layout.css" rel="stylesheet" type="text/css">
<link href="style/authority/common_style.css" rel="stylesheet" type="text/css">
</head>
<body>
<form id="carPurchaseForm" name="carPurchaseForm" action="" method="post">
	<div id="container">
		<div id="nav_links">
			当前位置：基础数据&nbsp;>&nbsp;<span style="color: #1A5CC6;">汽车采购编辑</span>
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
					<td class="ui_text_rt">采购时间</td>
					<td class="ui_text_lt">
						<input readonly="readonly"  style="background-color:#eee;font-family: 微软雅黑" type="date" id="purchasetime" name="purtime"  value="<%=request.getParameter("purtime")==null?"":request.getParameter("purtime")%>" class="ui_input_txt06"/>
					</td>
                        <td width="12%" class="ui_text_rt" ><span style="color:red">*</span>品牌</td>
                    <td class="ui_text_lt">
						<%str = new String(request.getParameter("brand").getBytes("iso-8859-1"), "utf-8");   %>
						<input readonly="readonly"  style="background-color:#eee" type="text" name="brand" value="<%=str%>"  class="ui_input_txt04"/>
                       
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">供应商编号</td>
					<td class="ui_text_lt">
						<input readonly="readonly"  style="background-color:#eee" type="text" name="supplierid" value="<%=request.getParameter("supplierid")==null?"":request.getParameter("supplierid")%>" class="ui_input_txt04"/></td>
                        <td width="12%" class="ui_text_rt" ><span style="color:red">*</span>车型</td>
                    <td class="ui_text_lt">
						<%str = new String(request.getParameter("cartype").getBytes("iso-8859-1"), "utf-8");   %>
						<input readonly="readonly"  style="background-color:#eee" type="text" name="cartype" value="<%=str%>"  class="ui_input_txt04"/>
                       
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">员工ID</td>
					<td class="ui_text_lt">
						<input readonly="readonly"  style="background-color:#eee" type="text" name="staffid" value="<%=request.getParameter("staffid")==null?"":request.getParameter("staffid")%>"  class="ui_input_txt02"/>
					</td>
					<td width="12%" class="ui_text_rt" ><span style="color:red">*</span>含税成本</td>
                    <td class="ui_text_lt">
                    	<input readonly="readonly"  style="background-color:#eee" type="text" name="taxcost" value="<%=request.getParameter("taxcost")==null?"":request.getParameter("taxcost")%>"  class="ui_input_txt04"/>
            
					</td>
				</tr>
                <tr>
					<td class="ui_text_rt">车辆状态</td>
					<td class="ui_text_lt">
						<%str = new String(request.getParameter("carstate").getBytes("iso-8859-1"), "utf-8");   %>
						<input readonly="readonly"  style="background-color:#eee" type="text" name="carstate" value="<%=str%>"  class="ui_input_txt04"/>
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
                     <td class="ui_text_lt">
						
                      <input id="cancelbutton" type="button" value="返回" onclick="javascript:window.history.back(-1);" class="ui_input_btn01"/>
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
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>