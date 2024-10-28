<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>
window.onload=function(){
	var change=prompt("请输入你想转移的仓库","");
	if(change==""||change==null){
		window.history.back(-1);
	}else{
		document.getElementById("1").value="<%=request.getParameter("cstorageid") %>";
		document.getElementById("2").value="<%=request.getParameter("certificateid") %>";
		document.getElementById("3").value=change;
		document.form.submit();	
	}
}
</script>
</head>
<body>
<form id="form" name="form" action="CarStorageChange">
	<input id="1" name="cstorageid" type="hidden" />
	<input id="2" name="certificateid" type="hidden" />
	<input id="3" name="newStorage" type="hidden" />
</form>
</body>
</html>