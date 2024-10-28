<%@ page language="java" import="java.util.*,javaBean.CustomerBean,page.Pageable" pageEncoding="utf-8" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="style/authority/basic_layout.css" rel="stylesheet" type="text/css">
<link href="style/authority/common_style.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="style/authority/jquery.fancybox-1.3.4.css" media="screen"></link>
<script type="text/javascript">
window.onload=function()
{
   var sel=document.getElementById("customersex");
   var sex="<%=request.getParameter("customersex")==null?"":request.getParameter("customersex")%>";
   if(sex=="")
	   sel[0].selected="true";
   else if(sex=="男")
	   sel[1].selected="true";
   else
	   sel[2].selected="true";
}
</script>
<script type="text/javascript">
function page_nav(num){
	document.customerSelectForm.cp.value=num;
	document.customerSelectForm.submit();	 
}
function page_jump(){
	var p=document.getElementById("jump").value;
	var cp=document.getElementById("cp");
	if(p!=""){
		cp.value=p;
		document.customerSelectForm.submit();
	}
}
</script>

<style>
	.alt td{ background:black !important;}
</style>
</head>
<body>
	<form id="customerSelectForm" name="customerSelectForm" action="SelectCustomer" method="post">
		<input type="hidden" name="allIDCheck" value="" id="allIDCheck"/>
		<input type="hidden" name="fangyuanEntity.fyXqName" value="" id="fyXqName"/>
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
							姓名
                          <input name="customername"value="<%=request.getParameter("customername")==null?"":request.getParameter("customername")%>" class="ui_input_txt02"></input>
							性别
						  <select id="customersex" name="customersex" class="ui_select01">
                            <option id="1" value="">--请选择--</option>
       						<option id="2" value="男">男</option>
       						<option id="3" value="女">女</option>
                          </select>
							身份证号码
						  <input name="customerid" value="<%=request.getParameter("customerid")==null?"":request.getParameter("customerid")%>" class="ui_input_txt02"></input>
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
							<th><div align="center">客户姓名</div></th>
							<th><div align="center">身份证号码</div></th>
							<th><div align="center">性别</div></th>
							<th><div align="center">电话号码</div></th>
							<th><div align="center">地址</div></th>
							<th><div align="center">操作</div></th>
						</tr>
						<%
    						ArrayList<CustomerBean> customerlist=(ArrayList<CustomerBean>)request.getAttribute("customerlist");
   						%>
						

						<%
						Pageable pa=(Pageable)request.getAttribute("page");
						if(pa!=null||customerlist!=null){
							for(int i=0;i<pa.getCurrentPageRowsCount();i++){
								CustomerBean customer=(CustomerBean)customerlist.get(i);%>
        				<tr>
        					<td><input type="checkbox" id="all" onclick="selectOrClearAllCheckbox(this);" /></td>
							<td><%=customer.getCustomerName() %></td>
							<td><%=customer.getCustomerId() %></td>
							<td><%=customer.getCustomerSex() %></td>
							<td><%=customer.getCustomerTel() %></td>
							<td><%=customer.getCustomerAdd() %></td>
							<td><a href="customer_update.jsp?preid=<%=customer.getCustomerId() %>
							&customername=<%=customer.getCustomerName() %>
							&customerid=<%=customer.getCustomerId() %>
							&customersex=<%=customer.getCustomerSex() %>
							&customertel=<%=customer.getCustomerTel() %>
							&customeradd=<%=customer.getCustomerAdd() %>
							">修改</a> 
							<a href="deleteCustomer?dcustomerid=<%=customer.getCustomerId() %>" onclick="return confirm('确认删除');">删除</a></td>
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