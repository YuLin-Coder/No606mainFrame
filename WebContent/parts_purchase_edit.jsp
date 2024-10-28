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
<form id="submitForm" name="submitForm" action="PartsPurchase" method="post">
	<div id="container">
		<div id="nav_links">
			当前位置：基础数据&nbsp;>&nbsp;<span style="color: #1A5CC6;">配件采购添加</span>
		</div>
		<div class="ui_content">
			<table  cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
				<tr>
					<td width="23%" class="ui_text_rt" ><span style="color:red">*</span>配件采购单号</td>
                    <td width="21%" class="ui_text_lt">
				
						<input type="text" name="partspurid" value="<%=request.getParameter("partspurid")==null?"":request.getParameter("partspurid")%>"  class="ui_input_txt04"/>
                       
					</td>
					<td width="12%" class="ui_text_rt" ><span style="color:red">*</span>采购时间</td>
                    <td width="44%" class="ui_text_lt">
				
						<input type="date" id="purchasetime" name="purtime" style="font-family: 微软雅黑" class="ui_input_txt06"/>
                       
					</td>
				</tr>
				
				<tr>
				<td width="23%" class="ui_text_rt" ><span style="color:red">*</span>配件编号</td>
                    <td width="21%" class="ui_text_lt">
				
						<input type="text" name="partsid" value="<%=request.getParameter("partsid")==null?"":request.getParameter("partsid")%>"  class="ui_input_txt04"/>
                       
					</td>
					
                        <td width="12%" class="ui_text_rt" ><span style="color:red">*</span>采购数量</td>
                    <td class="ui_text_lt">
				
						<input type="text" name="puramount" value="<%=request.getParameter("puramount")==null?"":request.getParameter("puramount")%>"  class="ui_input_txt04"/>
                       
					</td>
				</tr>
				<tr>
				<td class="ui_text_rt">配件名称</td>
					<td class="ui_text_lt">
						<input type="text" name="partsname"  value="<%=request.getParameter("partsname")==null?"":request.getParameter("partsname")%>" class="ui_input_txt04"/>
					</td>
					 <td width="12%" class="ui_text_rt" ><span style="color:red">*</span>采购员工ID</td>
                    <td class="ui_text_lt">
				
						<input type="text" name="staffid" value="<%=request.getParameter("staffid")==null?"":request.getParameter("staffid")%>"  class="ui_input_txt02"/>
                       
					</td>
				</tr>
				<tr>
				<td class="ui_text_rt">配件单价</td>
					<td class="ui_text_lt">
					<input type="text" name="partsprice"  value="<%=request.getParameter("partsprice")==null?"":request.getParameter("partsprice")%>" class="ui_input_txt04"/></td>
                       
					<td width="12%" class="ui_text_rt" >仓库编号</td>
                     <td class="ui_text_lt">
						<input type="text" name="storageid" value="<%=request.getParameter("storageid")==null?"":request.getParameter("storageid")%>" class="ui_input_txt04"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">供应商编号</td>
					<td class="ui_text_lt">
						<input type="text" name="supplierid" value="<%=request.getParameter("supplierid")==null?"":request.getParameter("supplierid")%>" class="ui_input_txt04"/></td>
                     
                    <td width="12%" class="ui_text_rt" ></td>
                     <td class="ui_text_lt">
						<input id="submitbutton" type="submit" value="提交" class="ui_input_btn01"/>
                      <input id="cancelbutton" type="button" value="取消" class="ui_input_btn01"/>
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