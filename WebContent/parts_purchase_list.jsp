<%@ page language="java" import="java.util.*,javaBean.PartPurchaseBean,page.Pageable" pageEncoding="utf-8" %>
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
	document.partPurchaseForm.cp.value=num;
	document.partPurchaseForm.submit();	 
}
function page_jump(){
	var p=document.getElementById("jump").value;
	var cp=document.getElementById("cp");
	if(p!=""){
		cp.value=p;
		document.partPurchaseForm.submit();
	}
}
</script>
<style>
	.alt td{ background:black !important;}
</style>
</head>
<body>
	<form id="partPurchaseForm" name="partPurchaseForm" action="SelectPartPurchase" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
						  	配件名称
						  <input name="partname" value="<%=request.getParameter("partname")==null?"":request.getParameter("partname")%>" class="ui_input_txt02">
						  	员工ID
						  <input name="staffid" value="<%=request.getParameter("staffid")==null?"":request.getParameter("staffid")%>" class="ui_input_txt02">
						  	供应商编号
						    <input name="supplierid" value="<%=request.getParameter("supplierid")==null?"":request.getParameter("supplierid")%>" class="ui_input_txt02"></input>
					  	</div>
						<div id="box_bottom">
							<input type="submit" value="查询" class="ui_input_btn01"/>							

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
							<th><div align="center">采购单号</div></th>
							<th><div align="center">配件名称</div></th>
							<th><div align="center">供应商ID</div></th>
							<th><div align="center">采购类型</div></th>
							<th><div align="center">采购时间</div></th>
							<th><div align="center">采购单价</div></th>
							<th><div align="center">采购数量</div></th>
							<th><div align="center">采购员工ID</div></th>
						</tr>
						<%
    						ArrayList<PartPurchaseBean> partpurchaselist=(ArrayList<PartPurchaseBean>)request.getAttribute("partpurchaselist");
   						%>
						

						<%
						Pageable pa=(Pageable)request.getAttribute("page");
						if(pa!=null||partpurchaselist!=null){
							for(int i=0;i<pa.getCurrentPageRowsCount();i++){
								PartPurchaseBean partpurchase=(PartPurchaseBean)partpurchaselist.get(i);%>
						<tr>
							<td><input type="checkbox" id="all" /></td>
							<td><%=partpurchase.getPartPurchaseId() %></td>
							<td><%=partpurchase.getPartName() %></td>
							<td><%=partpurchase.getSupplierId() %></td>
							<td>配件</td>
							<td><%=partpurchase.getPurTime() %></td>
							<td><%=partpurchase.getPartPrice() %></td>
							<td><%=partpurchase.getAmount() %></td>
							<td><%=partpurchase.getStaffId() %></td>
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
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>