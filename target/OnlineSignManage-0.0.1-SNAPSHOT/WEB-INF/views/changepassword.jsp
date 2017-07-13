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
    <title>网签管理后台--修改密码</title>
    <script type="text/javascript" src="resources/js/jquery.min.js"></script>
    <script type="text/javascript" src="resources/js/jquery.placeholder.js"></script>

    <script type="text/javascript" src="resources/css/layui/layer.js"></script><!--弹层-->
    <link rel="stylesheet" href="resources/css/layer_Style.css"/><!--layer弹层样式修改-->
    <script type="text/javascript" src="resources/css/laydate/laydate.js"></script><!--时间插件-->

    <link rel="stylesheet" href="resources/css/common.css"/>
    <script type="text/javascript" src="resources/js/common.js"></script>

    <!--[if lt IE 9]>
    <script type="text/javascript" src="resources/js/html5shiv.min.js"></script>
    <script type="text/javascript" src="resources/js/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<!--顶部导航-->
<div id="header">
    <div class="content">
        <a id="logo" href="dashboard">点餐猫商家网签管理后台</a>
        <div id="header_nav">
            <ul class="nav">
                <li><a href="userManagement">用户管理</a></li>
                <li><a class="act" href="changePassword">修改密码</a></li>
                |
                <li><a href="">数据导出</a></li>
                <li><a href="">退出登录</a></li>
            </ul>
        </div>
    </div>
</div>
<!--主体内容-->
<div id="main">
    <form id="setPwd" action="changePasswordSubmit" method="post" enctype="multipart/form-data">
        <p class="tsT">密码由数字和英文字母组成，且最少6个字符，最多12个字符。</p>
        <label>原密码：</label>
        <input class="old_pwd" name="originalPwdTyped" type="text"/><br/>

        <label>新密码：</label>
        <input class="new_pwd" name="newPwdTyped" type="password"/><br/>

        <label>确认密码：</label>
        <input class="news_pwd" name="newPwdConfirmed" type="password"/><br/>

        <div class="PwdBtns">
            <a class="setPwdBtn">提交</a>
        </div>
    </form>
</div>

<script>

	//提交表单
	function submitform(){
		
		var params = {};  //params.XX必须与Spring Mvc controller中的参数名称一致   
		 		
			$.ajax({
		        type: "POST",
		        async:false,
		        data: params,
		        url: "changePasswordSubmit",
		        datatype:"json",  
		        success: function(data) {
		  alert("data="+data);
		        }	       	        
			})  
	}



    $(function () {

        function pwdtextPrFun(el){
            var str= $(el).val();
            if( str=="" || str == null ){
                layer.tips('不能为空！',el,{
                    tips: [3, '#d73938']
                });
                return false;
            }
            if(! (/^[A-Za-z0-9]{6,12}$/.test(str) )){
                layer.tips('请输入只包含字母和数字且最小6位最多12位的字符！',el,{
                    tips: [3, '#d73938']
                });
                return false;
            }
            return true;
        }
        var old_pwd="", new_pwd="", news_pwd="";
        function pwdAddFun(){
            old_pwd="";
            new_pwd="";
            news_pwd="";

            old_pwd = $('.old_pwd').val();
            new_pwd = $('.new_pwd').val();
            news_pwd = $('.news_pwd').val();

            if(pwdtextPrFun('.old_pwd') == false){ return false;}
            if(pwdtextPrFun('.new_pwd') == false){ return false;}
            if(pwdtextPrFun('.news_pwd') == false){ return false;}

            if( $('.new_pwd').val() === $('.old_pwd').val() ){
                layer.tips('输入的新密码与原密码不能相同！','.new_pwd',{
                    tips: [3, '#d73938']
                });
                return false;
            }

            if( $('.news_pwd').val() !== $('.new_pwd').val()){
                layer.tips('两次输入的新密码必须相同！','.news_pwd',{
                    tips: [3, '#d73938']
                });
                return false;
            }
            return true;
        }


//点击提交按钮 开始进行验证，验证通过则提交表单
        $('.setPwdBtn').click(function () {
            if(!pwdAddFun()){
                return false;
            }
            submitform();
            $('#setPwd').submit();//提交表单

        });


    });
</script>
</body>
</html>