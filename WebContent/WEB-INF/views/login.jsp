<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="0">
    <meta name="renderer" content="webkit"><!--360浏览器等设置为默认用极速核-->
    <meta http-equiv = "X-UA-Compatible" content ="IE=Edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">

    <meta http-equiv="keywords" content="点餐猫商家,点餐猫商家网签,点餐猫商家网签管理后台">
    <link rel="shortcut icon" href="resources/img/favicon.ico" type="image/x-icon" />
    <title>点餐猫商家网签管理后台</title>

    <script type="text/javascript" src="resources/js/jquery.min.js"></script>
    <script type="text/javascript" src="resources/js/jquery.placeholder.js"></script>

    <script type="text/javascript" src="resources/css/layui/layer.js"></script>

    <link rel="stylesheet" href="resources/css/common.css"/>
    <script type="text/javascript" src="resources/js/common.js"></script>

    <style>
    	body{	
    		height: 100%;
    		background: #fff;
    		position: absolute;
    		left: 0;
    		right: 0;
    		background-color: #f0ece7;
    		}
        #login{
            width: 680px;
            height: 430px;
            position: absolute;
            top: 0;
            bottom: 0;
            left: 0;
            right: 0;
            margin: auto;
            background-image: url("resources/img/login-bg.png");
            background-size: 680px 430px;
            background-repeat: no-repeat;
        }
        #loginBox{
            width: 280px;
            height: 250px;
            position: absolute;
            top: 50%;
            right: 60px;
            margin-top: -125px;
        }
        #loginBox>h2{
            height: 22px;
            margin: 0;
            padding: 0;
            padding-left: 18px;
            position: relative;
            font-size: 22px;
            line-height: 22px;
            color: #333;
        }
        #loginBox>h2:before{
            content: "";
            display: block;
            background-color: #333;
            width: 4px;
            height: 100%;
            position: absolute;
            top: 0;
            left: 0;
        }
        #user{
            font-size: 16px;
            outline:none;
            width: 100%;
            height: 30px;
            margin: 30px 0;
            border: 0;
            border-bottom: 1px solid #333;
        }
        #pwd{
            font-size: 16px;
            outline:none;
            width: 100%;
            height: 30px;
            border: 0;
            border-bottom: 1px solid #333;
        }
        #sub{
            outline:none;
            border: 0;
            width: 100%;
            height: 40px;
            border-radius: 20px;
            background-color: #333;
            color: #fff;
            text-align: center;
            line-height: 40px;
            font-size: 18px;
            font-weight: bold;
            position: absolute;
            bottom: 0;
            left: 0;
            cursor: pointer;
        }
        #sub:active {
            background-color: #d73938;
        }
    </style>

    <script type="text/javascript">
        function keyLogin(){
            if (event.keyCode==13)   //回车键的键值为13
                $('#sub').click();  //调用登录按钮的登录事件
        }

        function sumbit(){
            var user = $('#user').val();
            var pwd = $('#pwd').val();

            //用户名 验证
            if( user=='' || (/^\s+$/g).test(user)){
                layer.tips('不能为空！','#user',{
                    tips: [3, '#d73938']
                });
                return false;
            }
            if( !(/^[A-Za-z0-9]{2,18}$/).test(user)){
                layer.tips('请输入由数字和英文字母，且最小2位最多18位的字符组成！','#user',{
                    tips: [3, '#d73938']
                });
                return false;
            }

            //密码 验证
            if( pwd=='' || (/^\s+$/g).test(pwd)){
                layer.tips('不能为空！','#pwd',{
                    tips: [3, '#d73938']
                });
                return false;
            }
            if( !(/^[A-Za-z0-9]{2,18}$/).test(pwd)){
                layer.tips('请输入由数字和英文字母，且最小6位最多12位的字符组成！','#pwd',{
                    tips: [3, '#d73938']
                });
                return false;
            }
            
         
  			var form=document.getElementById("form");
            form.action="login";                //设置提交路径
            form.submit();          
            
            
        }
    </script>

</head>
<body onkeydown="keyLogin();">
    <div id="login">
        <div id="loginBox">
            <h2>登录</h2>
            <form id="form"  method="post" >
                <input id="user" type="text" placeholder="请输入用户名" name="username"/>
                <input id="pwd" type="password" placeholder="请输入密码" name="password"/>
                <input id="sub" type="button" value="登录" onclick="sumbit()"/>
            </form>           
		<font color="red">${loginfail}</font>
        </div>
    </div>
</body>
</html>