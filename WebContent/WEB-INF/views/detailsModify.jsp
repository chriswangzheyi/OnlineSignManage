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
    <title>网签管理后台--商家信息更正</title>
    <script type="text/javascript" src="resources/js/jquery.min.js"></script>
    <script type="text/javascript" src="resources/js/jquery.nicescroll.min.js"></script><!--浏览器滚动条样式修改-->
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
            <c:if test="${authLevel == 2}">
                            <li><a href="userManagement">用户管理</a></li>
            </c:if>
                <li><a href="changePassword">修改密码</a></li>
                |
                <li><a href="export">数据导出</a></li>
                <li><a href="logout">退出登录</a></li>
            </ul>
        </div>
    </div>
</div>
<!--主体内容-->
<div id="main">
	<c:forEach var="detailform" items="${detailform}"  > 
    <form id="setDetails" action="updateSubmit" method = 'post' enctype="multipart/form-data"> <!-- 上传文件必须加enctype="multipart/form-data" -->
        <div id="details_Content">

            <div class="det_item">
                <span class="det_label">餐厅名称：</span>
                <div class="det_text">
                    <input class="input_set_text" type="text" value="${detailform.restaurantName}" name="restaurant_name" />
                </div>
            </div>

            <div class="det_item">
                <span class="det_label">所在地区：</span>
                <div class="det_text">
                    <select id="shopIp_SS" data-initVal ='0-2472,重庆市' name="restaurant_province">
                        <option value="-1">请选择省市</option>
                        <option data-id="2472" data-pid="0" value="0-2472,重庆市"  selected="selected">${detailform.restaurantProvince}</option>
                    </select>

                    <select id="shopIp_DQ" data-initVal ='2472-2473,重庆市' name="restaurant_city">
                        <option value="-1">全部</option>
                        <option data-id="2473" data-pid="2472" value="2472-2473,重庆市" selected="selected">${detailform.restaurantCity}</option>
                    </select>

                    <select id="shopIp_QX" data-initVal ='2473-2485,渝北区' name="restaurant_district">
                        <option value="-1">区/县</option>
                        <option data-id="2485" data-pid="2473" value="2473-2485,渝北区" selected="selected">${detailform.restaurantDistrict}</option>
                    </select>

                    <select id="shopIp_JD" data-initVal ='2485-3629,汽博中心' name="restaurant_street">
                        <option value="-1">街道</option>
                        <option data-id="3629" data-pid="2485" value="2485-3629,汽博中心" selected="selected">${detailform.restaurantStreet}</option>
                    </select>
                </div>
            </div>

            <div class="det_item">
                <span class="det_label">餐厅类别：</span>
                <div class="det_text">
                    <select id="shop_class" name="restaurant_type">
                        
                    <c:forEach var="value" items="${allRestaurantType}">  
                        <option  value="${value.name}" 
                        
                         <c:if test="${value.name==detailform.restaurantType}">
    
                          selected="selected" 
         
                        </c:if>
                        
                          >  
                        ${value.name}  
                        </option>  
                    </c:forEach> 
                        

                    </select>
                </div>
            </div>

            <hr class="det_hr"/>

            <div class="det_item">
                <span class="det_label">餐厅地址：</span>
                <div class="det_text">
                    <input id="mapInput" name="restaurant_address" class="input_set_text" type="text" data-lng="106.576547" 
                    data-lat="29.646387" value="${detailform.restaurantAddress}"/><a title="点击查看地图中所在位置" class="map_icon"></a>
                    <div id="allMapBox">
                        <div id="allMapSet"><span class="map_loading">地图加载中…</span></div>
                        <div class="closeSetMap"></div>
                    </div>
                </div>
            </div>

            <div class="det_item">
                <span class="det_label">餐厅电话：</span>
                <div class="det_text" >
                    <input type="text" class="input_set_text" value="${detailform.restaurantTel}" name="restaurant_tel"/>
                </div>
            </div>

            <div class="det_item">
                <span class="det_label">营业时间：</span>
                <div class="det_text shopHours">
                    <input type="text" readonly="readonly" name="restaurant_opentime" value="${detailform.restaurantOpenTime}" class="shopHours_start" placeholder="开始时间"/><i class="time_bar">—</i>
                    <input type="text" readonly="readonly" name="restaurant_closetime" value="${detailform.restaurantCloseTime}" class=" shopHours_end" placeholder="结束时间"/>

                    <div class="shopHours_List">
                        <div class="shopHours_timeBox">
                            <div class="timeBox_showtime"><span class="time_hh">00</span>:<span class="time_mm">00</span></div>
                            <ul class="hhLIsit"></ul>
                            <span class="shopHours_format">:</span>
                            <ul class="mmLIsit"></ul>
                            <div class="shopHours_timeBox_btns">
                                <a class="timeBox_btns_canc">
                                    取消
                                </a><a class="timeBox_btns_conf">
                                确定
                            </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="det_item">
                <span class="det_label">餐厅介绍：</span>
                <div class="det_text">
                    <textarea rows="5" class="testarea_set" name="restaurant_indroduction">${detailform.restaurantIndroduction}</textarea>
                </div>
            </div>

            <div class="det_item">
                <span class="det_label">餐厅实拍：</span>
                <div class="det_text">
                    <!--    照片添加    -->
                    <div class="z_photo">
                        <div class="z_file">
                            <input type="file" id="file" class="shopimg" multiple="multiple" name="viewfiles" />
                        </div>
                        <div class="z_addImg"><img src="img/images/IMG_3564.JPG"></div>
                        <div class="z_addImg"><img src="img/images/IMG_3564.JPG"></div>
                    </div>
                    <!--遮罩层-->
                    <div class="z_mask">
                        <!--弹出框-->
                        <div class="z_alert">
                            <p>确定要删除这张图片吗？</p>
                            <p>
                                <span class="z_cancel">取消</span>
                                <span class="z_sure">确定</span>
                            </p>
                        </div>
                    </div>
                </div>
            </div>

            <hr class="det_hr"/>

            <div class="det_item">
                <span class="det_label">店长手机：</span>
                <div class="det_text">
                    <input type="text" class="input_set_text" value="${detailform.managerPhone}" name="manager_phone"/>
                </div>
            </div>

            <div class="det_item">
                <span class="det_label">老板手机：</span>
                <div class="det_text">
                    <input type="text" class="input_set_text" value="${detailform.bossPhone}" name="boss_phone"/>
                </div>
            </div>

            <hr class="det_hr"/>

            <div class="det_item labelLang">
                <span class="det_label">餐厅工商营业执照：</span>
                <div class="det_text">
                        <span class="busLic_text">更改照片</span>
                        <input class="busLic" type="file" name="licensefile"/>
                        <span class="busLic_name"></span>
                        <div class="busLic_img">
                            <img src="img/images/IMG_3564.JPG"/>
                        </div>
                </div>
            </div>

            <div class="det_item labelLang">
                <span class="det_label">餐厅卫生许可证：</span>
                <div class="det_text">
                    <span class="DCMcontract_text">更改照片</span>
                    <input class="DCMcontract" type="file" name="contractfile"/>
                    <span class="DCMcontract_name"></span>
                    <div class="DCMcontract_img">
                    	<img src="img/images/IMG_3564.JPG"/>
                    </div>
                </div>
            </div>

            <hr class="det_hr"/>

            <div class="det_item">
                <span class="det_label">开户名称：</span>
                <div class="det_text">
                    <input type="text" class="input_set_text" value="${detailform.bankAccountName}" name="bankaccount_name"/>
                </div>
            </div>

            <div class="det_item">
                <span class="det_label">开户银行：</span>
                <div class="det_text">
                    <input type="text" class="input_set_text" value="${detailform.bankAccountBank}" name="bankaccount_bank"/>
                </div>
            </div>

            <div class="det_item">
                <span class="det_label">银行账号：</span>
                <div class="det_text">
                    <input type="text" class="input_set_text" value="${detailform.bankAccountAccount}" name="bankaccount_account"/>
                </div>
            </div>

            <div class="det_item">
                <span class="det_label" style="width: 105px">授权委托书：</span>
                <div class="det_text" style="width: 695px">
                    <span class="proxy_text">更改照片</span>
                    <input class="proxy" type="file" name="attorneyfile"/>
                    <span class="proxy_name"></span>
                    <div class="proxy_img">
                    	<img src="img/images/IMG_3564.JPG"/>
                    </div>
                </div>
            </div>
            <hr/>
        </div>
        <div style="width: 100%;text-align: center;">
            <a class="submit">提交</a>
               <input type="submit" value="测试提交"  />
        </div>
    </form>
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
    function initialize() {
        // 百度地图API功能
        var x = $('#mapInput').attr('data-lng');//经度：后台给data-lng赋值
        var y = $('#mapInput').attr('data-lat');//纬度：后台给data-lat赋值
        //地图初始化
        var map = new BMap.Map("allMapSet", {
            enableMapClick: false       //禁止百度自己的InfoWindow(信息窗口弹出)
        });
        var point = new BMap.Point(x, y);
        map.centerAndZoom(point, 15);

        map.addControl(new BMap.NavigationControl());

        //创建点餐猫地标
        var myIcon_dcm = new BMap.Icon("img/mark_b.png", new BMap.Size(19, 31));

        // 将标注添加到地图中
        map.addOverlay(new BMap.Marker(
                        new BMap.Point(x, y),
                        {icon: myIcon_dcm, offset: new BMap.Size(-1, -13)})
        );

        //点击地图获取当前地址名称给input
        var geoc = new BMap.Geocoder();
        map.addEventListener("click", function (e) {
            var pt = e.point;
            var lng = e.point.lng;
            var lat = e.point.lat;
            $("#mapInput").attr('data-lng', lng);
            $("#mapInput").attr('data-lat', lat);
            geoc.getLocation(pt, function (rs) {
                var addComp = rs.addressComponents;
                $('#mapInput').val(addComp.province + "" + addComp.city + "" + addComp.district + "" + addComp.street + "" + addComp.streetNumber);
                var allOverlay = map.getOverlays();
                for (var i = 0; i < allOverlay.length; i++) {
                    map.removeOverlay(allOverlay[i]); //删除标注点
                }

                var marker_DCM01 = new BMap.Marker(
                        new BMap.Point(lng, lat),
                        {icon: myIcon_dcm, offset: new BMap.Size(-1, -13)});  // 创建标注

                map.addOverlay(marker_DCM01);              // 将标注添加到地图中
                //marker_DCM01.enableDragging();          //可拖拽
            });
        });


        $("#mapInput").on('input propertychange', function () {
            var myGeo = new BMap.Geocoder();
            myGeo.getPoint($(this).val(), function (point) {
                if (point) {
                    var lng = point.lng;
                    var lat = point.lat;
                    map.centerAndZoom(point, 16);
                    var marker_DCM02 = new BMap.Marker(
                            new BMap.Point(lng, lat),
                            {icon: myIcon_dcm, offset: new BMap.Size(-1, -13)});  // 创建标注
                    map.clearOverlays();
                    map.addOverlay(marker_DCM02);

                    $("#mapInput").attr('data-lng', lng);
                    $("#mapInput").attr('data-lat', lat);
                } else {
                    //alert("您选择地址没有解析到结果!");
                }
            }, "重庆市");

        });

    }

    /****************************************************************/
    //打开地图
    $('.map_icon').click(function () {
        $('#allMapBox').slideDown(200);
        setTimeout(function () {
            if ($('#allMapSet:visible').size() > 0) {
                initialize();
            }
        }, 200);
    });

//关闭地图
    //点击右上角关闭 按钮关闭地图
    $('.closeSetMap').click(function () {
        $('#allMapBox').slideUp(200);
    });
    /************************************************************************************/
});
</script>


<script>
$(function () {


//TODO 地区三级联动 （数据来至“data/cityJson.json”）
    //ajax加载省市
    var SS_init =$('#shopIp_SS').attr('data-initVal');
    var DQ_init =$('#shopIp_DQ').attr('data-initVal');
    var QX_init =$('#shopIp_QX').attr('data-initVal');
    var JD_init =$('#shopIp_JD').attr('data-initVal');
    $.ajax({
        type: "POST",
        url: "resources/data/cityJson.json",
        data: "json",
        success: function(data){
            $.each(data, function(idx, obj) {
            //加载全国省市
                if(obj.regLevel == 1){
                    if((obj.pid+'-'+obj.id+','+obj.name) != SS_init ){
                        var optionEL_01 = $('<option data-id="'
                                +obj.id+'" '
                                +'data-pid="'+obj.pid +'"'
                                +'value="'+obj.pid+'-'+obj.id+','+obj.name+'">'
                                +obj.name+''
                                +'</option>');
                        $('#shopIp_SS').append(optionEL_01);
                    }
                }
            //市区级 根据其 data-initVal的值 确定加载的列表
                if(obj.regLevel == 2){
                    var datid_2 = DQ_init.substring(
                            0,
                            DQ_init.indexOf('-')
                    );
                    if(obj.pid.toString() == datid_2){
                        if((obj.pid+'-'+obj.id+','+obj.name) != DQ_init ){
                            var optionEL_02 = $('<option data-id="'
                                    +obj.id+'" '
                                    +'data-pid="'+obj.pid +'"'
                                    +'value="'+obj.pid+'-'+obj.id+','+obj.name+'">'
                                    +obj.name+''
                                    +'</option>');
                            $('#shopIp_DQ').append(optionEL_02);

                        }
                    }

                }
            //区县级 根据其 data-initVal的值 确定加载的列表
                if(obj.regLevel == 3){
                    var datPid_3 = QX_init.substring(
                            0,
                            QX_init.indexOf('-')
                    );
                    if(obj.pid.toString() == datPid_3){
                        if((obj.pid+'-'+obj.id+','+obj.name) != QX_init ){
                            var optionEL_03 = $('<option data-id="'
                                    +obj.id+'" '
                                    +'data-pid="'+obj.pid +'"'
                                    +'value="'+obj.pid+'-'+obj.id+','+obj.name+'">'
                                    +obj.name+''
                                    +'</option>');
                            $('#shopIp_QX').append(optionEL_03);

                        }
                    }

                }
            //街道级 根据其 data-initVal的值 确定加载的列表
                if(obj.regLevel == 4){
                    var datPid_4 = JD_init.substring(
                            0,
                            JD_init.indexOf('-')
                    );
                    if(obj.pid.toString() == datPid_4){
                        if((obj.pid+'-'+obj.id+','+obj.name) != JD_init ){
                            var optionEL_04 = $('<option data-id="'
                                    +obj.id+'" '
                                    +'data-pid="'+obj.pid +'"'
                                    +'value="'+obj.pid+'-'+obj.id+','+obj.name+'">'
                                    +obj.name+''
                                    +'</option>');
                            $('#shopIp_JD').append(optionEL_04);

                        }
                    }

                }
            });
        }
    });

    $('#shopIp_SS').on('change', function () {
        $('#shopIp_DQ').html('<option value="-1">全部</option>');//清空城市
        $('#shopIp_QX').html('<option value="-1">区/县</option>');//清空区县
        $('#shopIp_JD').html('<option value="-1">街道</option>');//清空街道

        if(this.value !=='-1'){
            var datPid = $(this).val().substring(
                    $(this).val().indexOf('-')+1,
                    $(this).val().indexOf(',')
            );
            var optionHTML = '<option value="-1">全部</option>';
            $.ajax({
                type: "POST",
                url: "resources/data/cityJson.json",
                data: "json",
                success: function(data){
                    $.each(data, function(idx, obj) {
                        if(obj.pid == datPid){
                            optionHTML +=
                                    '<option data-id="'
                                    +obj.id+'" '
                                    +'data-pid="'+obj.pid +'"'
                                    +'value="'+obj.pid+'-'+obj.id+','+obj.name+'">'
                                    +obj.name+''
                                    +'</option>';
                        }
                    });
                    $('#shopIp_DQ').html(optionHTML);
                }
            });
        }
    });

    //点击地区时加载区县
    $('#shopIp_DQ').on('change', function () {
        $('#shopIp_QX').html('<option value="-1">区/县</option>');//清空区县
        $('#shopIp_JD').html('<option value="-1">街道</option>');//清空街道

        if(this.value !=='-1'){
            var datPid = $(this).val().substring(
                    $(this).val().indexOf('-')+1,
                    $(this).val().indexOf(',')
            );
            var optionHTML = '<option value="-1">区/县</option>';
            $.ajax({
                type: "POST",
                url: "resources/data/cityJson.json",
                data: "json",
                success: function(data){
                    $.each(data, function(idx, obj) {
                        if(obj.pid == datPid){
                            optionHTML +=
                                    '<option data-id="'
                                    +obj.id+'" '
                                    +'data-pid="'+obj.pid +'"'
                                    +'value="'+obj.pid+'-'+obj.id+','+obj.name+'">'
                                    +obj.name+''
                                    +'</option>';
                        }
                    });
                    $('#shopIp_QX').html(optionHTML);
                }
            });
        }
    });


    //点击区县时加载街道
    $('#shopIp_QX').on('change', function () {
        $('#shopIp_JD').html('<option value="-1">街道</option>');//清空街道
        if(this.value !=='-1'){
            var datPid = $(this).val().substring(
                    $(this).val().indexOf('-')+1,
                    $(this).val().indexOf(',')
            );
            var optionHTML = '<option value="-1">街道</option>';
            $.ajax({
                type: "POST",
                url: "resources/data/cityJson.json",
                data: "json",
                success: function(data){
                    $.each(data, function(idx, obj) {
                        if(obj.pid == datPid){
                            optionHTML +=
                                    '<option data-id="'
                                    +obj.id+'" '
                                    +'data-pid="'+obj.pid +'"'
                                    +'value="'+obj.pid+'-'+obj.id+','+obj.name+'">'
                                    +obj.name+''
                                    +'</option>';
                        }
                    });
                    $('#shopIp_JD').html(optionHTML);
                }
            });
        }
    });

//TODO end----地区三级联动


    //TODO 营业时间
    //时间列表生成方法
    function listTimeFun(ul_elem/*添加的ul*/,min/*最小时间*/,max/*最大时间*/){
        if(ul_elem.size()>0){
            var _html='';
            for(var i=min;i<=max;i++){
                i = i<10?'0'+i:i;
                _html += ('<li>'+i+'</li>');
            }
            ul_elem.html(_html);
        }
    }

    //根据时间的传值再定格式的值
    function hhmmFun(str){
        if(str ==null ||str==""){
            return '00:00';
        }else{
            var arry = str.split(':');
            return arry[0]+':'+arry[1];
        }
    }

    //点击输入框弹出时间弹层
    var elem;
    $('.shopHours').on('click','input', function () {
        elem = $(this);
        var inputV = $(this).val();

        var input_start = $('.shopHours_start').val();//点击时 “开始”输入框里的值
        var input_end = $('.shopHours_end').val();//点击时 “结束”输入框里的值

        //分钟列表生成
        listTimeFun($('.mmLIsit'),0,59);
        listTimeFun($('.hhLIsit'),0,23);

        if(inputV ==null ||inputV==""){
            $('.time_hh').text($('.hhLIsit li:first-of-type').text());
            $('.time_mm').text($('.mmLIsit li:first-of-type').text());
        }else{
            var timeAR = inputV.split(':');
            $('.time_hh').text(timeAR[0]);
            $('.time_mm').text(timeAR[1]);
        }

        $('.hhLIsit li').each(function () {
            if($(this).text() == $('.time_hh').text()){
                $(this).addClass('active');
            }
        });
        $('.mmLIsit li').each(function () {
            if($(this).text() == $('.time_mm').text()){
                $(this).addClass('active');
            }
        })
        $('.shopHours_List').show();
        $('.hhLIsit').animate({
            scrollTop: $(".hhLIsit li.active").index()*30+'px'
        }, 500);
        $('.mmLIsit').animate({
            scrollTop: $(".mmLIsit li.active").index()*30+'px'
        }, 500);
    });

    //选择时间功能
    $('.shopHours_timeBox ul').on('click','li', function () {
        $(this).siblings('.active').removeClass('active');
        $(this).addClass('active');
        var t = $(this).text();
        if( $(this).parent()[0].className == 'hhLIsit' ){
            $('.time_hh').text(t);
            twinkleFun($('.time_hh'));
        }
        if( $(this).parent()[0].className == 'mmLIsit' ){
            $('.time_mm').text(t);
            twinkleFun($('.time_mm'));
        }
    });

    // 弹层退出后 传值
    function timeBoxExitFun(){
        return $('.time_hh').text()+':'+$('.time_mm').text();
    }
    //时间选择弹层取消 退出
    $('.shopHours_timeBox').on('click','.timeBox_btns_canc', function () {

        $('.shopHours_List').hide();
    });
    //时间选择弹层确定 退出
    $('.shopHours_timeBox').on('click','.timeBox_btns_conf', function () {
        elem.val(timeBoxExitFun());
        $('.shopHours_List').hide();
    });

    $('.shopHours_timeBox').drag();//可拖动


//TODO end 时间弹层

    //TODO 商家信息的所有input验证
    function inputTestFun(){
        var inputs = $('#details_Content').find('input');
        var inputsLen = inputs.length;
        for(var i=0;i<inputsLen;i++){
            var inp_value   = inputs[i].value;//input的值
            var inp_type    = $(inputs[i]).attr('type');//input的type
            var inp_parent  = $(inputs[i]).parents('.det_item');
            var inp_labelEl  =$(inputs[i]).parents('.det_item').find('.det_label');
            var inp_labels  = inp_labelEl[0].innerHTML;//input的指定Label
            var inp_label   = inp_labels.substring(0,inp_labels.indexOf('：'));//input的指定Label的文字

            //如果是文字输入框，必填
            if(inp_type == 'text'){
                if(inp_value ==""){
                    alertScrollFun(inp_parent,inp_labelEl);
                    return false;
                }
            }
        }

        //下拉菜单的验证
        var selects = $('#details_Content').find('select');
        var selectsLen = selects.length;
        for(var s=0;s<selectsLen;s++ ){
            var sel_value   = $(selects[s]).val();//select的值
            var sel_type    = $(selects[s]).attr('type');//select的type
            var sel_labelEl  = $(selects[s]).parents('.det_item').find('.det_label');
            var sel_labels  = sel_labelEl[0].innerHTML;//select的指定Label
            var sel_label   = sel_labels.substring(0,sel_labels.indexOf('：'));//select的指定Label的文字
            var sel_parent  = $(selects[s]).parents('.det_item');

            if(sel_value =='-1'){
                alertScrollFun(sel_parent,sel_labelEl);
                return false;
            }
        }

        //如果餐厅实拍里没有上床图片，必填
        if($('.z_addImg img').size() == 0){
            layer.msg('请至少上传一张图片！',{time:3000});
            return false;
        }

        return true;
    }
    inputTestFun();
    $('.submit').click(function () {
        if(!inputTestFun()){
            return false;
        }
        //$('#setDetails').submit();//提交表单
    });


});
</script>
</body>
</html>