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
    <title>点餐猫商家网签管理后台</title>
    <script type="text/javascript" src="resources/js/jquery.min.js"></script>
    <script type="text/javascript" src="resources/js/jquery.page.js"></script>
    <script type="text/javascript" src="resources/js/jquery.placeholder.js"></script>

    <script type="text/javascript" src="resources/css/layui/layer.js"></script><!--弹层-->
    <link rel="stylesheet" href="resources/css/layer_Style.css"/><!--layer弹层样式修改-->
    <script type="text/javascript" src="resources/css/laydate/laydate.js"></script><!--时间插件-->

	<link rel="stylesheet" href="resources/css/jquery.page.css"/>
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
    <div class="searchBox">
        <div class="search_time">
            <span class="searchLable">时间：</span>
            <input type="text" id="startTime" class="laydate_icon" readonly />
            <span style="color: #666;">&nbsp;至&nbsp;</span>
            <input type="text" id="endTime" class="laydate_icon" readonly />
        </div>
        <div class="search_ip">
            <span class="searchLable">地区：</span>
            <select id="ip_SS">
                <option value="-1">请选择省市</option>
            </select>
            <select id="ip_DQ">
                <option value="-1">全部</option>
            </select>
            <select id="ip_QX">
                <option value="-1">区/县</option>
            </select>
        </div>
        <div class="search_state">
            <span class="searchLable">状态：</span>
            <select>
                <option>未审核</option>
                <option>已审核</option>
                <option>未通过</option>
            </select>
        </div>
        <div class="search_input">
            <span class="searchLable">搜索：</span>
            <div class="searchText">
                <input class="searchInput" type="text"/>
                <a class="icon_seach"></a>
            </div>
        </div>
    </div>
    <div class="mainContent">
        <table class="listBox indexTable">
            <thead>
                <tr>
                    <th>餐厅名称</th>
                    <th>所在地区</th>
                    <th>餐厅类别</th>
                    <th>餐厅电话</th>
                    <th>提交时间</th>
                    <th>状态</th>
                    <th>操作</th>
                    <th>审核人</th>
                </tr>
            </thead>
            <tbody>
                
                <c:forEach var="var" items="${formInfo}" > 
              
	                <tr>
	                    <td><p>${var.restaurantName}</p></td>
	                    <td><p>${var.restaurantProvince}-${var.restaurantCity}-${var.restaurantDistrict}</p></td>
	                    <td>${var. restaurantType}</td>
	                    <td>${var. restaurantTel}</td>
	                    <td>${var. submitTime}</td>
	                    <td class="Not_Pass">未通过<div class="Not_Pass_help"></div></td>
	                    <td class="table_btns">
	                        <a href="details" class="details_btn">详情</a><a class="examine_btn">审核</a>
	                    </td>
	                    <td>张三</td>                    
	                </tr>               
                </c:forEach>  
                
            </tbody>
        </table>
        
        <a href="javascript:void(0);" onclick="changePage(2)">换</a>
        
        
        <div class="pagediv"></div>


</div>
<script>

//换页
function changePage(p){
	
	var params = {};  //params.XX必须与Spring Mvc controller中的参数名称一致  
	params.targetPage=p
	 		
		$.ajax({
	        type: "POST",
	        data: params,
	        url: "changeFormPage",
	        /* dataType:"json",   */
	        success: function(data) {
	  alert("data="+data);
	        }	       	        
		})  
}



    $(function () {
    	
    	$(".pagediv").createPage({
            pageNum : ${numberOfPages},//总页数
            current : 1,//当前页数
            shownum: 9,//最多显示的页数项
            activepage: "current",//activepage当前页选中样式
            activepaf: "",//默认class是“nextpage”//activepaf下一页选中样式
            backFn:function(p){
            	changePage(p);
                console.log(p);
            }
        });

    	var formInfo = '${formInfo}';
    
    	
   	
    	
   //TODO laytate时间组件
        var start = {
            elem: '#startTime',
            istime: true, //是否开启时间选择
            event: 'click', //触发事件
            format: 'YYYY/MM/DD hh:mm',
            istime: true,
            istoday: true, //是否显示今天
            max: getNowFormatDate(), //最大日期：此时此刻
            choose: function(datas){
                end.min = datas; //开始日选好后，重置结束日的最小日期
                end.start = datas; //将结束日的初始值设定为开始日
            }
        };
        var end = {
            elem: '#endTime',
            istime: true, //是否开启时间选择
            event: 'click', //触发事件
            format: 'YYYY/MM/DD hh:mm',
            istime: true,
            istoday: true, //是否显示今天
            max: getNowFormatDate(),//最大日期：此时此刻
            choose: function(datas){
                start.max = datas; //结束日选好后，重置开始日的最大日期
            }
        };
        laydate(start);
        laydate(end);
    //TODO end----laytate时间组件


    //TODO 地区三级联动 （数据来至“data/cityJson.json”）
        //ajax加载省市
        
        $.ajax({
            type: "POST",
            url: "resources/data/cityJson.json",
            data: "json",
            success: function(data){

                $.each(data, function(idx, obj) {
                    if(obj.regLevel == 1){
                        var optionEL = $('<option data-id="'
                                +obj.id+'" '
                                +'data-pid="'+obj.pid +'"'
                                +'value="'+obj.pid+'-'+obj.id+','+obj.name+'">'
                                +obj.name+''
                                +'</option>');
                        $('#ip_SS').append(optionEL);
                    }
                });
            
            }
        });
    

        $('#ip_SS').on('change', function () {
            $('#ip_DQ').html('<option value="-1">全部</option>');//清空城市
            $('#ip_QX').html('<option value="-1">区/县</option>');//清空区县
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
                        $('#ip_DQ').html(optionHTML);
                    }
                });
            }
        });

        //点击地区时加载区县
        $('#ip_DQ').on('change', function () {
            $('#ip_QX').html('<option value="-1">区/县</option>');//清空区县
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
                        $('#ip_QX').html(optionHTML);
                    }
                });
            }
        });

    //TODO end----地区三级联动


        //审核按钮交互：
        $('.indexTable').on('click','.examine_btn', function () {
            var tar = this;
            var text = $(tar).html();//自身的html
            var startTd = $($(tar).parents('tr').find('td')[5]);//状态的td
            var exaLayer = layer.confirm('商家信息审核通过？', {
                title:'提示',
                closeBtn: 0,
                title:false,//标题
                btn: ['不通过','通过'] //按钮
            }, function(){//不通过按钮回调
                parent.layer.open({
                    type: 2,
                    closeBtn: 0,
                    title:false,//标题
                    content: ['iframe/audit_information.html','no'],//iframe的路径(后面加‘no’表示禁用浏览器的滚动条)

                    success: function(layero, index){// 层弹出后的成功回调方法-将值传给弹层
                        var body = layer.getChildFrame('body', index);//获取iframe的DOM
                        var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();

                        //如果是重审 则 点击不通过后的弹出框有上次审核信息
                        if(text == '重审'){
                            body.find('.textareaT').val(startTd.attr('data-help'));
                        }
                    },

                    end: function () {//弹层退出时的回调函数

                        if(parent.textareaT!="" && parent.textareaT != undefined){
                            $(startTd).html('未通过<div class="Not_Pass_help"></div>').addClass('Not_Pass').attr('data-help',parent.textareaT);
                            $(tar).html('重审');

                        }

                    }
                });

                layer.close(exaLayer);

            }, function(){//通过按钮回调
                $($(tar).parents('tr').find('td')[5]).html('已审核').removeAttr('class');
                $(tar).remove();
                layer.close(exaLayer);
                return;
            });
        });


        //未通过的帮助说明
        $('.indexTable').on('click','.Not_Pass', function () {
           var helpText = $(this).attr('data-help');
            if(helpText!='' && helpText!=undefined){
                layer.open({
                    type: 1,
                    title:false,
                    area: '280px',
                    content: '<h2 style="text-align: center;font-size: 18px;font-weight: 400;margin: 0;padding-top: 10px;padding-bottom: 5px;">信息审核</h2><div style="padding:10px;">'+helpText+'</div>'
                });
            }
        });
    });
</script>
</body>
</html>