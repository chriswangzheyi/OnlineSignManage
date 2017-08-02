<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
    
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
    <script type="text/javascript" src="resources/js/jquery.citys.js"></script><!--jquery地区插件 -->

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
	<c:forEach var="detailform" items="${detailform}"> 
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
                    <div id="citys">
						<div class="form_box">
					        <select name="province" class="shopClass"></select>
					        <select name="city" class="shopClass"></select>
					        <select name="district" class="shopClass"></select>
					        <select name="street" class="shopClass"></select>
    					</div>
					    <input type="hidden" name="restaurant_province">
					    <input type="hidden" name="restaurant_city">
					    <input type="hidden" name="restaurant_district">
					    <input type="hidden" name="restaurant_street">                    
                    </div>
                </div>
            </div>

            <div class="det_item">
                <span class="det_label">餐厅类别：</span>
                <div class="det_text">
                    <select id="shop_class" name="restaurant_type">
                        
                    <c:forEach var="value" items="${allRestaurantType}">  
    						<c:if test="${value.name==detailform.restaurantType}">
								<option  value="${value.name}" selected="selected">${value.name}</option>
    						</c:if>
    						<c:if test="${value.name!=detailform.restaurantType}">
								<option  value="${value.name}">${value.name}</option>
    						</c:if>
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
                                     
                        <c:forTokens items="${detailform.viewURL}" delims="," var="viewURL">
			 				<div class="z_addImg"><img class="imgUrl" src="${imageBaseURL}${viewURL}"></div>		 				
						</c:forTokens>
                          
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
                            <img src="${imageBaseURL}${detailform.licenseURL}"/>
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
                    	<img src="${imageBaseURL}${detailform.contractURL}"/>
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
                    	<img src="${imageBaseURL}${detailform.attorneyURL}"/>
                    </div>
                </div>
            </div>
            <hr/>
        </div>
        <div style="width: 100%;text-align: center;">
            <a class="submit">提交</a>
            
         	<!--   记录被删除的view Pic -->
            <input type="hidden" name="deletedViewPicURL" value=""/>
            
        </div>
    </form>
    <!-- 默认的地区 -->
    <input type="hidden" class="val_Province" value="${detailform.restaurantProvince}"/>
    <input type="hidden" class="val_City" value="${detailform.restaurantCity}"/>
    <input type="hidden" class="val_District" value="${detailform.restaurantDistrict}"/>
    <input type="hidden" class="val_Street" value="${detailform.restaurantStreet}"/>
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
	
	//餐厅实拍删除的图片交互
	$('.det_item').on('click','.z_addImg',function(){
		var delImgStr = $('input[name="deletedViewPicURL"]').val();
		var tar =this;
		if($(this).find('.imgUrl').size()>0){
			
			var imgSrc = $(this).find('.imgUrl').attr('src');
			var tarImgBox = $(this);
			 var lay_img01=layer.confirm('你确定要删除这张图片吗？', {
	             closeBtn: 0,
	             title:false,//标题
	             btn: ['取消','确定'] //按钮
	         }, function(){//取消
	             layer.close(lay_img01);
	             return;
	         }, function(){//确定
	        	 delImgStr += imgSrc+',';
	        	 $('input[name="deletedViewPicURL"]').val(delImgStr);
	        	 console.log(delImgStr);
	        	 
	        	 layer.close(lay_img01);
	        	 layer.msg('图片删除成功！',{time:1000});
	        	 setTimeout(function(){
	        		 tarImgBox.remove();
	        	 },1000);
	      		
	         });
			
		}else{
			var lay_img02=layer.confirm('你确定要删除这张图片吗？', {
	            closeBtn: 0,
	            title:false,//标题
	            btn: ['取消','确定'] //按钮
	        }, function(){//取消
	            layer.close(lay_img02);
	            return;
	        }, function(){//确定
	       	
	       	 layer.close(lay_img02);
	       	 $('input[name="deletedViewPicURL"]').val(delImgStr);
	       	 layer.msg('图片删除成功！',{time:1000});
	       	 setTimeout(function(){
	       		 $(tar).remove();
	       		 /*此处需对input里的value进行处理*/
	       	 },1000);
	     			
	        });
		}
		
	});

	 	var _province 	= $('.val_Province').val();
	    var _city 		= $('.val_City').val();
	    var _district 	= $('.val_District').val();
	    var _street 	= $('.val_Street').val();

	
//TODO 地区三级联动 （需要引用jquery.citys.js）
	 var $town = $('#citys select[name$="street"]');
	    var townFormat = function(info){
	        $town.hide().empty();
	        if(info['code']%1e4&&info['code']<7e6){	//是否为“区”且不是港澳台地区

	            $.ajax({
	                url:'http://passer-by.com/data_location/town/'+info['code']+'.json',
	                dataType:'json',
	                success:function(town){
	                    $town.show();
	                    for(i in town){
	                        $town.append('<option value="'+town[i]+'">'+town[i]+'</option>');
	                    }
	                }
	            });
	        }
	    };
	   
	   
	    $('#citys').citys({
	    	province:_province,
	        city:_city,
	        area:_district,
	        onChange:function(info){
	            townFormat(info);
	        }
	    },function(api){
	        var info = api.getInfo();
	        townFormat(info);
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

        //如果餐厅实拍里没有上传图片，必填
        if($('.z_addImg img').size() == 0){
            layer.msg('请至少上传一张图片！',{time:3000});
            var _top =$('.z_file').offset().top;
            //滚动至相应位置
            $('html,body').animate({
                scrollTop: $('.z_file').offset().top
            }, 200);
            //当前闪烁提示
            setTimeout(function () {
            	var label = $($('.z_file').parents('.det_item')[0]).find('.det_label')
                twinkleFun(label);
            },200);
            return false;
        }
        


        return true;
    }
    $('.submit').click(function () {
        if(!inputTestFun()){
            return false;
        }
        $('#setDetails').submit();//提交表单
    });


});
</script>
</body>
</html>