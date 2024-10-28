<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>
window.onload=function(){
	var change=prompt("请输入新密码","");
	if(change==""||change==null){
		window.history.back(-1);
	}else{
		document.getElementById("1").value=change;
		document.form.submit();	
	}
}
</script>
</head>
<body>
<form id="form" name="form" action="PasswordChange">
	<input id="1" name="newPswd" type="hidden" />
</form>
</body>
</html>