<%@ page language="java" import="java.util.*,javaBean.CarSalesBean,page.Pageable" pageEncoding="utf-8" %>
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
	document.carSalesSelectForm.cp.value=num;
	document.carSalesSelectForm.submit();	 
}
function page_jump(){
	var p=document.getElementById("jump").value;
	var cp=document.getElementById("cp");
	if(p!=""){
		cp.value=p;
		document.carSalesSelectForm.submit();
	}
}
window.onload=function()
{	
   var sales=document.getElementById("sales");
   var sa="<%=request.getParameter("salestime")==null?"":request.getParameter("salestime")%>";
   if(sa=="")
	   sales[0].selected="true";
   else if(sa=="1")
	   sales[1].selected="true";
   else if(sa=="2")
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
	<form id="carSalesSelectForm" name="carSalesSelectForm" action="SelectCarSales" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">合格证号
						  <input name="certificateid" class="ui_input_txt02">
						  	员工ID
						  <input name="staffid" class="ui_input_txt02">
						  	销售日期
						  <select id="sales" name="salestime" class="ui_select01">
                            <option value="" selected="selected">--请选择--</option>
                            <option value="1">近三个月</option>
                            <option value="2">近半年</option>
                            <option value="3">近一年</option>
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
							<th><div align="center">客户ID</div></th>
							<th><div align="center">销售员工ID</div></th>
							<th><div align="center">售价</div></th>
                            <th><div align="center">销售日期</div></th>
							<th><div align="center">详情</div></th>
						</tr>
						<%
    						ArrayList<CarSalesBean> carsaleslist=(ArrayList<CarSalesBean>)request.getAttribute("carsaleslist");
   						%>
						

						<%
						Pageable pa=(Pageable)request.getAttribute("page");
						if(pa!=null||carsaleslist!=null){
							for(int i=0;i<pa.getCurrentPageRowsCount();i++){
								CarSalesBean carsales=(CarSalesBean)carsaleslist.get(i);%>
						<tr>
							<td><input type="checkbox" id="all" onclick="selectOrClearAllCheckbox(this);" /></td>
							<td><%=carsales.getCertificateId() %></td>
							<td><%=carsales.getCustomerId() %></td>
							<td><%=carsales.getStaffId() %></td>
							<td><%=carsales.getPayPrice() %></td>
							<td><%=carsales.getSalesTime() %></td>							
							<td><a href="car_sales_update.jsp?
							certificateid=<%=carsales.getCertificateId() %>
							&licenseid=<%=carsales.getLicenseId() %>
							&customerid=<%=carsales.getCustomerId() %>
							&brand=<%=carsales.getBrand() %>
							&staffid=<%=carsales.getStaffId() %>
							&cartype=<%=carsales.getCarType() %>
							&supplierid=<%=carsales.getSupplierId() %>
							&payprice=<%=carsales.getPayPrice() %>
							&salestime=<%=carsales.getSalesTime() %>
							&color=<%=carsales.getColor() %>
							">查看详情</a> 
							</td>
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