<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="login-bg">
<head>
	<title>注册系统</title>
    
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
    <!-- bootstrap -->
    <link href="css/bootstrap/bootstrap.css" rel="stylesheet" />
    <link href="css/bootstrap/bootstrap-responsive.css" rel="stylesheet" />
    <link href="css/bootstrap/bootstrap-overrides.css" type="text/css" rel="stylesheet" />

    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="css/layout.css" />
    <link rel="stylesheet" type="text/css" href="css/elements.css" />
    <link rel="stylesheet" type="text/css" href="css/icons.css" />

    <!-- libraries -->
    <link rel="stylesheet" type="text/css" href="css/lib/font-awesome.css" />
    
    <!-- this page specific styles -->
    <link rel="stylesheet" href="css/compiled/signup.css" type="text/css" media="screen" />

    <!-- open sans font -->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css' />

    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
<body>
    <div class="header">
            </div>
    
    <div class="row-fluid login-wrapper">
        <div class="box">
            <div class="content-wrap">
                <h6>注册</h6>
                <p id="demo"><br></p>
                <form id="form2" name="form2" method="post" action="CheckSignup" onsubmit="return myc();">
                	<input name="username" id="username" class="span12" type="text" placeholder="用户名" value="<%=request.getParameter("username")==null?"":request.getParameter("username")%>" />
                	<input name="pswd" id="pswd" class="span12" type="password" placeholder="密码" value="<%=request.getParameter("pswd")==null?"":request.getParameter("pswd")%>" />
                	<input id="repswd" name="repswd" class="span12" type="password" placeholder="确认密码" value="<%=request.getParameter("repswd")==null?"":request.getParameter("repswd")%>" />
                	<div class="action">
                    	<input type="submit" class="btn-glow primary signup" value="注册" >
                	</div> 
                </form>            
            </div>
        </div>

        <div class="span4 already">
            <p>已经有了账户?</p>
            <a href="signin.jsp">登陆</a>
        </div>
    </div>
	<%
  		//从request域中取出信息
  		String msg = (String)request.getAttribute("demo");
		if(msg!=null){
			System.out.println(msg);
		}else{
			msg="\n";
		}
 	%>
	<!-- scripts -->
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/theme.js"></script>
    <script>
    	function myc(){
    		var z=document.getElementById("username").value
			var x=document.getElementById("pswd").value;
			var y=document.getElementById("repswd").value;
			if(z==""){
				document.getElementById("demo").innerHTML="请输入用户名";
				return false;
			}
			else if(x==""){
				document.getElementById("demo").innerHTML="密码不能为空";
				return false;
			}else if(x!=y){
				document.getElementById("demo").innerHTML="两次密码不一致";
				return false;
			}
			else{
				return true;
			}
		}
    </script>
    <script>
		var a = "<%=msg %>";
		document.getElementById("demo").innerHTML=a;
		
	</script>
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>