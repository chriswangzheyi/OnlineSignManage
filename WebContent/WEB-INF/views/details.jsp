<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
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
    <title>网签管理后台--商家详情</title>
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
                <li><a class="act" href="userManagement">用户管理</a></li>
                <li><a href="changePassword">修改密码</a></li>
                |
                <li><a href="">数据导出</a></li>
                <li><a href="">退出登录</a></li>
            </ul>
        </div>
    </div>
</div>
<!--主体内容-->
<div id="main">
	<c:forEach var="detailform" items="${detailform}" >
    <div id="details_Content">

        <div class="det_item">
            <span class="det_label">餐厅名称：</span>
            <div class="det_text">
                <p>${detailform.restaurantName}</p>
            </div>
        </div>

        <div class="det_item">
            <span class="det_label">所在地区：</span>
            <div class="det_text">
                <p>${detailform.restaurantCity} ${detailform.restaurantDistrict} ${detailform.restaurantStreet}</p>
            </div>
        </div>

        <div class="det_item">
            <span class="det_label">餐厅类别：</span>
            <div class="det_text">
                <p>${detailform.restaurantType}</p>
            </div>
        </div>

        <hr class="det_hr"/>

        <div class="det_item">
            <span class="det_label">餐厅地址：</span>
            <div class="det_text">
                <p data-lng="106.576547" data-lat="29.646387" class="det_ip">${detailform.restaurantAddress}</p><a title="点击查看地图中所在位置" class="map_icon"></a>
                <div id="allMapMask">
                    <div id="allMap"><span class="map_loading">地图加载中…</span></div>
                    <div class="closeMap"></div>
                </div>
            </div>
        </div>

        <div class="det_item">
            <span class="det_label">餐厅电话：</span>
            <div class="det_text">
                <p>${detailform.restaurantAddress}</p>
            </div>
        </div>

        <div class="det_item">
            <span class="det_label">营业时间：</span>
            <div class="det_text">
                <p>${detailform.restaurantOpenTime} - ${detailform.restaurantCloseTime} </p>
            </div>
        </div>

        <div class="det_item">
            <span class="det_label">餐厅介绍：</span>
            <div class="det_text">
                <p>${detailform.restaurantIndroduction}</p>
            </div>
        </div>

        <div class="det_item">
            <span class="det_label">餐厅实拍：</span>  
            <div class="det_text">
                <div class="det_imgBox">
                    <img src="resources/img/images/IMG_3563.JPG"/>
                </div>
                <div class="det_imgBox">
                    <img src="resources/img/images/IMG_3564.JPG"/>
                </div>
                <div class="det_imgBox">
                    <img src="resources/img/images/news01.JPG"/>
                </div>
                <div class="det_imgBox">
                    <img src="resources/img/images/IMG_3854.PNG"/>
                </div>
                <div class="det_imgBox">
                    <img src="resources/img/icon_map_24px.png"/>
                </div>
            </div>
        </div>

        <hr class="det_hr"/>

        <div class="det_item">
            <span class="det_label">店长手机：</span>
            <div class="det_text">
                <p>${detailform.managerPhone}</p>
            </div>
        </div>

        <div class="det_item">
            <span class="det_label">老板手机：</span>
            <div class="det_text">
                <p>${detailform.bossPhone}</p>
            </div>
        </div>

        <hr class="det_hr"/>

        <div class="det_item labelLang">
            <span class="det_label">餐厅工商营业执照：</span>
            <div class="det_text">
                <div class="det_imgBox">
                    <img src="resources/img/images/IMG_3563.JPG"/>
                </div>
            </div>
        </div>

        <div class="det_item labelLang">
            <span class="det_label">餐厅卫生许可证：</span>
            <div class="det_text">
                <div class="det_imgBox">
                    <img src="resources/img/images/IMG_3563.JPG"/>
                </div>
            </div>
        </div>

        <hr class="det_hr"/>

        <div class="det_item">
            <span class="det_label">开户名称：</span>
            <div class="det_text">
                <p>${detailform.bankAccountName}</p>
            </div>
        </div>

        <div class="det_item">
            <span class="det_label">开户银行：</span>
            <div class="det_text">
                <p>${detailform.bankAccountBank}</p>
            </div>
        </div>

        <div class="det_item">
            <span class="det_label">银行账号：</span>
            <div class="det_text">
                <p>${detailform.bankAccountAccount}</p>
            </div>
        </div>

        <div class="det_item">
            <span class="det_label" style="width: 105px">授权委托书：</span>
            <div class="det_text" style="width: 695px">
                <div class="det_imgBox">
                    <img src="resources/img/images/IMG_3563.JPG"/>
                </div>
            </div>
        </div>
        <hr/>
    </div>
    <div style="width: 100%;text-align: center;">
        <a class="returnPageBtn" onclick="history.go(-1)">返回</a>
        <a class="hrefBtn" href="detailsModify">信息更正</a>
    </div>

</c:forEach>
</div>


<script>
$(function () {
//TODO 地图
/************************************************************************************/
    //动态引入百度地图api.js
    function loadScript() {
        var script = document.createElement("script");
        script.src = "http://api.map.baidu.com/api?v=2.0&ak=VUOoSQQRO6Asz3EO1P26STSGRsoZX0Fb&callback=initialize";
        document.body.appendChild(script);
        var script01 = document.createElement("script");
        script01.src = "http://developer.baidu.com/map/jsdemo/demo/convertor.js";
        document.body.appendChild(script01);

    }
    window.onload = loadScript;


    /**************************百度地图API************************/
    function initialize(){
        // 百度地图API功能
        var x = $('.det_ip').attr('data-lng');//经度：后台给data-lng赋值
        var y = $('.det_ip').attr('data-lat');//纬度：后台给data-lat赋值
        //地图初始化
        var map = new BMap.Map("allMap",{
            enableMapClick: false       //禁止百度自己的InfoWindow(信息窗口弹出)
        });
        var point = new BMap.Point(x,y);
        map.centerAndZoom(point, 15);

        map.addControl(new BMap.NavigationControl());

        //创建点餐猫地标
        var myIcon_dcm = new BMap.Icon("img/mark_b.png", new BMap.Size(19,31));

        // 将标注添加到地图中
        map.addOverlay(new BMap.Marker(
                new BMap.Point(x,y),
                {icon:myIcon_dcm,offset: new BMap.Size(-1, -13)})
        );
    }
    /****************************************************************/
    //打开地图
    $('.map_icon').click(function () {
        $('#allMapMask').show();
        setTimeout(function () {
            if($('#allMap:visible').size()>0){
                initialize();
            }
        },200);
    });

//关闭地图
    //点击其他部分关闭地图
    $('#allMapMask').click(function (event) {
        var e = event || window.event;
        var tar = e.target;
        var idName = $(tar).attr('id');
        if(idName ==='allMapMask'){
            $('#allMapMask').fadeOut();
        }
    });
    //点击右上角关闭 按钮关闭地图
    $('.closeMap').click(function () {
        $('#allMapMask').fadeOut();
    });
/************************************************************************************/

});
</script>
</body>
</html>