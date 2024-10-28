<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="login-bg">
<head>
	<title>汽车管理系统</title>
    
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
    <link rel="stylesheet" href="css/compiled/signin.css" type="text/css" media="screen" />

    <!-- open sans font -->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css' />

    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
<body>
	<div class="header">
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
	
    <!-- background switcher -->
    <div class="bg-switch visible-desktop">
        <div class="bgs">
            <a href="#" data-img="landscape.jpg" class="bg active">
                <img src="img/bgs/landscape.jpg" />
            </a>            
            <a href="#" data-img="7.jpg" class="bg">
                <img src="img/bgs/7.jpg" />
            </a>
            <a href="#" data-img="8.jpg" class="bg">
                <img src="img/bgs/8.jpg" />
            </a>
            <a href="#" data-img="9.jpg" class="bg">
                <img src="img/bgs/9.jpg" />
            </a>
            <a href="#" data-img="10.jpg" class="bg">
                <img src="img/bgs/10.jpg" />
            </a>
            <a href="#" data-img="11.jpg" class="bg">
                <img src="img/bgs/11.jpg" />
            </a>
        </div>
    </div>


    <div class="row-fluid login-wrapper">
        <a href="index.html">
           
        </a>

        <div class="span4 box">
            <div class="content-wrap">
                <h6>登陆界面</h6>
                <p id="demo"><br></p>
                <form id="form1" name="form1" method="post" action="CheckLogin" onsubmit="return my();">
                <input name="username" id="username" class="span12" type="text" placeholder="用户名" value="<%=request.getParameter("username")==null?"":request.getParameter("username")%>"/>
                <input name="password" id="password" class="span12" type="password" placeholder="密码" value="<%=request.getParameter("password")==null?"":request.getParameter("password")%>" />
                <a href="http://www.baidu.com" class="forgot">忘记密码?</a>
                <div class="remember">
                    <input id="remember-me" type="checkbox" />
                    <label for="remember-me">记住密码</label>
                </div>
                <input id="submit" type="submit" class="btn-glow primary login"  value="登陆"> 
                </form>
            </div>
        </div>

        <div class="span4 no-account">
            <p>还没有账户?</p>
            <a href="signup.jsp">注册</a>
        </div>
    </div>

	<!-- scripts -->
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/theme.js"></script>
    <script>
		function my(){
			var x=document.getElementById("username").value;
			var y=document.getElementById("password").value;
			if(x==""){
				document.getElementById("demo").innerHTML="请输入您的用户名";
				return false;
			}
			else if(y==""){
				document.getElementById("demo").innerHTML="请输入您的密码";
				return false;
			}else{
				return true;
			}
		}
    </script>
    <script>
		var a = "<%=msg %>";
		document.getElementById("demo").innerHTML=a;
		
	</script>
    <!-- pre load bg imgs -->
    <script type="text/javascript">
        $(function () {
            // bg switcher
            var $btns = $(".bg-switch .bg");
            $btns.click(function (e) {
                e.preventDefault();
                $btns.removeClass("active");
                $(this).addClass("active");
                var bg = $(this).data("img");

                $("html").css("background-image", "url('img/bgs/" + bg + "')");
            });

        });
    </script>
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>