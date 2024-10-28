<%@ page language="java" import="java.util.*,javaBean.CarPartsBean,page.Pageable" pageEncoding="utf-8" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="style/authority/basic_layout.css" rel="stylesheet" type="text/css">
<link href="style/authority/common_style.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="style/authority/jquery.fancybox-1.3.4.css" media="screen"></link>
<script type="text/javascript">
function page_nav(num){
	document.carPartsSelectForm.cp.value=num;
	document.carPartsSelectForm.submit();	 
}
function page_jump(){
	var p=document.getElementById("jump").value;
	var cp=document.getElementById("cp");
	if(p!=""){
		cp.value=p;
		document.carPartsSelectForm.submit();
	}
}
window.onload=function()
{	
   var sales=document.getElementById("sales");
   var sa="<%=request.getParameter("service")==null?"":request.getParameter("service")%>";
   if(sa=="")
	   sales[0].selected="true";
   else if(sa=="维修")
	   sales[1].selected="true";
   else if(sa=="保养")
	   sales[2].selected="true";
   else
	   sales[3].selected="true";
}
</script>
<style>
	.alt td{ background:black !important;}
</style>
</head>
<body>
	<form id="carPartsSelectForm" name="carPartsSelectForm" action="SelectCarParts" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">合格证号
						  <input name="certificateid" class="ui_input_txt02">
						  	员工ID
						  <input name="staffid" class="ui_input_txt02">
						  	类别
						  <select id="sales" name="service" class="ui_select01">
                            <option value="" selected="selected">--请选择--</option>
                            <option value="维修">维修</option>
                            <option value="保养">保养</option>
                            <option value="加装">加装</option>
                        </select>
						  </div>
						<div id="box_bottom">
							<input type="submit" value="查询" class="ui_input_btn01" /> 							

						</div>
					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th width="30"><input type="checkbox" id="all" onclick="selectOrClearAllCheckbox(this);" />
							</th>
							<th><div align="center">合格证号</div></th>
							<th><div align="center">配件名称</div></th>
							<th><div align="center">员工ID</div></th>
							<th><div align="center">服务类型</div></th>
							<th><div align="center">服务价格</div></th>
							<th><div align="center">数量</div></th>
                            <th><div align="center">日期</div></th>
						</tr>
						<%
    						ArrayList<CarPartsBean> carpartslist=(ArrayList<CarPartsBean>)request.getAttribute("carpartslist");
   						%>
						

						<%
						Pageable pa=(Pageable)request.getAttribute("page");
						if(pa!=null||carpartslist!=null){
							for(int i=0;i<pa.getCurrentPageRowsCount();i++){
								CarPartsBean carparts=(CarPartsBean)carpartslist.get(i);%>
						<tr>
							<td><input type="checkbox" id="all" onclick="selectOrClearAllCheckbox(this);" /></td>
							<td><%=carparts.getCertificateId() %></td>
							<td><%=carparts.getPartsName() %></td>
							<td><%=carparts.getStaffId() %></td>
							<td><%=carparts.getService() %></td>
							<td><%=carparts.getServicePrice() %></td>							
							<td><%=carparts.getPartsNum() %></td>
							<td><%=carparts.getServiceTime() %></td>
							<%} }%>
						</tr>
						<%
						  		//从request域中取出信息
						  		String msg = (String)request.getAttribute("msg");
								if(msg!=null){
									System.out.println(msg);
								}else{
									msg="\n";
								}
						 	%>
						<script type="text/javascript">
							var checkMsg= "<%=msg%>";
							if(checkMsg!=null||checkMsg!="")
								alert(checkMsg);
						</script>
					</table>
				</div>
				<div class="ui_tb_h30">
					<div class="ui_flt" style="height: 30px; line-height: 30px;">
						共有
						<span class="ui_txt_bold04"><%=(pa==null?0:pa.getTotalRows()) %></span>
						条记录，当前第
						<span class="ui_txt_bold04"><%=(pa==null?0:pa.getCurrentPage()) %>
						/
						<%=(pa==null?0:pa.getTotalPages()) %></span>
						页
					</div>
					<div class="ui_frt">
						<!--    如果是第一页，则只显示下一页、尾页 -->
							<input type="button" value="首页" onclick="page_nav(1);" class="ui_input_btn01" />
							<input type="button" value="上一页" onclick="page_nav(<%=(pa==null?0:pa.getCurrentPage()-1) %>)" class="ui_input_btn01" />
							<input type="button" value="下一页" onclick="page_nav(<%=(pa==null?1:pa.getCurrentPage()+1) %>)" class="ui_input_btn01" />
							<input type="button" value="尾页" onclick="page_nav(<%=(pa==null?1:pa.getTotalPages()) %>)" class="ui_input_btn01" />
							<input id="cp" name="cp" type="hidden" value="1" />
						
						<!--     如果是最后一页，则只显示首页、上一页 -->
						
						转到第<input type="text" id="jump" value="" class="ui_input_txt01" />页
							 <input type="button" class="ui_input_btn01" value="跳转" onclick="page_jump();"  />
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>