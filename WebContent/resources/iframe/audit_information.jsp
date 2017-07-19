<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>审核信息</title>
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../css/layui/layer.js"></script><!--弹层-->
    <link rel="stylesheet" href="../css/layer_Style.css"/><!--layer弹层样式修改-->
    
    <style>
        body{  margin: 0;}
        *{
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
        }
        #audit_information{
            width: 300px;
            height: 260px;
            padding: 10px;
            margin:0 auto;
        }
        h2{
            font-weight: normal;
            font-size: 18px;
            color: #333;
            text-align: center;
            margin: 0;
            padding: 10px 0;
        }
        textarea{
            border: 1px solid #ccc;
            resize:none;
            display: block;
            width: 95%;
            height: 132px;
            padding: 5px;
            margin: 0 auto;
            line-height: 20px;
            font-size: 14px;
            color: #333;
        }
        .iframe_Btns{
            width: 100%;
            padding: 20px 0;
            text-align: center;
        }
        .iframe_Btns a{
            display: inline-block; *display: inline;*zoom: 1;
            height: 28px;
            line-height: 28px;
            margin: 0 6px;
            padding: 0 15px;
            border: 1px #dedede solid;
            background-color: #f1f1f1;
            color: #333;
            border-radius: 2px;
            font-weight: 400;
            cursor: pointer;
            text-decoration: none;
            font-size: 0.75em;
        }
        .iframe_Btns a:active{
            opacity: 0.9;
        }
        .iframe_Btns .yesBtn{
            background-color: #d73938;
            border-color: #d73938;
            color: #fff;
        }
    </style>
</head>

<body>
<div id="audit_information">
    <h2>审核信息</h2>
    <textarea class="textareaT" rows="7" maxlength="120" placeholder="最多只能输入120字符"></textarea>
    <div class="iframe_Btns">
        <a class="noBtn">取消</a>
        <a class="yesBtn">确认</a>
    </div>
</div>


</body>
<script>
    $(function () {
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.iframeAuto(index);
        function testFun(){
            parent.textareaT="";
            parent.textareaT = $('.textareaT').val();
            if(parent.textareaT=="" || (/^\s+$/g).test(parent.textareaT)){
                layer.tips('不能为空！','.bzxNameInput',{
                    tips: [3, '#d73938']
                });
                return false;
            }
            return true;
        }
        //确定按钮
        $('.yesBtn').click(function () {
            if(testFun()){
                parent.layer.close(index);//关闭窗口
                return true;
            }else{
                return false;
            }
        });

        //点击取消按钮
        $('.noBtn').click(function () {
            parent.layer.close(index);//关闭窗口
            parent.textareaT="";
        });
    });
</script>
</html>