<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>
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
    <title>网签管理后台--用户管理</title>
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
                <li><a class="act" href="userManagement">用户管理</a></li>
                <li><a href="changePassword">用户密码</a></li>
                |
                <li><a href="export">数据导出</a></li>
                <li><a href="logout">退出登录</a></li>
            </ul>
        </div>
    </div>
</div>
<!--主体内容-->
<div id="main">
    <div class="btns_topBox">
        <a class="btn_add">新增用户</a>
    </div>
    <div class="mainContent">
        <table class="listBox userTable">
            <thead>
                <tr>
                    <th  style="display:none;">编号</th>
                    <th>用户名称</th>
                    <th>联系电话</th>
                    <th>创建时间</th>
                    <th>最后登录</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                
                <c:forEach var="var" items="${userInfoList}" > 
	                <tr>
	                
	                <td style="display:none;">${var.id}</td>
                    <td>${var.username}</td>
                    <td>${var.phone}</td>
                    <td>${var.createTime}</td>
                    <td>${var.lastLoginTime}</td>
                    
                    <c:if test="${var.status == 0}">
						<td class="userStatus_normal" style="color:#999;">冻结</td>
					</c:if> 
					
					<c:if test="${var.status == 1}">
						<td class="userStatus_normal">正常</td>
					</c:if>              

                    <td class="table_btns">
                    	<c:if test="${var.status == 0}">
							<a class="fro_btn unlock" href="javascript:void(0);">解冻</a>
						</c:if> 
					
						<c:if test="${var.status == 1}">
							<a class="fro_btn" href="javascript:void(0);">冻结</a>
						</c:if>
                    
                        <!-- <a class="del_btn" href="javascript:void(0);">删除</a> -->
                    </td>
	                
	                </tr>
	            </c:forEach>
                
                
                
                
            </tbody>
        </table>

        <!--分页-->
        <div class="pagediv"></div>
        
        
        <iframe id="id_iframe" name="add_iframe" style="display:none;"></iframe>
    </div>

</div>

<script>

		/*************************************************************/
		/*
		*注意：删除功能暂时不做，可保留代码
		*/
		/* //删除用户
		function deleteuser(id){
			
			var params = {};  //params.XX必须与Spring Mvc controller中的参数名称一致   
		    params.id =id;
		    params.currentPage = 3;

            var callbackStr = 'err';
            $.ajax({
                type: "POST",
                data: params,
                url: "deleteAccount",
                async:false,
                datatype:"json",
                success: function(data) {
                
                    if(data == 'success'){
                        callbackStr = 'success';
                    }else{
                    	callbackStr ='fail';
                    }
                }
            });
            return callbackStr;
		} */
		/******************************************************************/
		
		
		//新增用户
		function newuser(username,password,phone){
			var params = {};  //params.XX必须与Spring Mvc controller中的参数名称一致   
		    params.username =username;
		    params.password =password;
		    params.phone =phone;
		   	var callbackStr = 'err';
            $.ajax({
                type: "POST",
                async:false,
                data: params,
                url: "newAccount",
                datatype:"json",
                success: function(data) {
                    if(data == 'success'){
                        callbackStr = 'success';
                    }
                },
                error:function(err){
                    console.log('错误：'+err);
                }
            });
            return callbackStr;
		}
		
		
		//换页
		function changePage(p){
            openLoadingFun('数据加载中...')//开启loading
			var params = {};  //params.XX必须与Spring Mvc controller中的参数名称一致  
			params.targetPage=p
			 		var pageHtml='';
            $.ajax({
                type: "POST",
                data: params,
                contentType:"application/x-www-form-urlencoded;charset=utf-8",
                url: "changeUserPage",
                /* dataType:"json",   */
                success: function(data) {
                    closeLoadingFun()//关闭loading
                    $.each(JSON.parse(data), function(idx, obj) {
                         //创建时间
                    	var commonTime = '';
                        if(obj.createTime !=null){
                            var creatime = obj.createTime.time;//提交时间的时间戳
                            var creaunixTime= new Date( creatime ) ;
                            commonTime = creaunixTime.toLocaleString();
                        }else{
                        	commonTime = '';
                        }

                        //最后登录时间
                        var lastLoginTime = '';
                        if(obj.lastLoginTime !=null){
                            var lasttime = obj.lastLoginTime.time;//提交时间的时间戳
                            var lastunixTime= new Date( lasttime ) ;
                            lastLoginTime = lastunixTime.toLocaleString();
                        }else{
                            lastLoginTime = '';
                        }

                      //状态：0:冻结；1:正常。
                      var stateHTML = '<td class="userStatus_normal">正常</td>'+
                        '<td class="table_btns">'+
                        '<a class="fro_btn">冻结</a>'+
                    '</td>';

                      if(obj.status == '1'){
                          stateHTML = '<td class="userStatus_normal">正常</td>'+
                            '<td class="table_btns">'+
                            '<a class="fro_btn">冻结</a>'+
                        '</td>';
                      }else if(obj.status == '0'){
                          stateHTML = '<td class="userStatus_normal" style="color:#999;">冻结</td>'+
                            '<td class="table_btns">'+
                            '<a class="fro_btn unlock">解冻</a>'+
                        '</td>';
                      }

                        pageHtml += '<tr>'+
                            '<td style="display:none;">'+obj.id+'</td>'+
                            '<td>'+obj.username+'</td>'+
                            '<td>'+obj.phone+'</td>'+
                            '<td>'+commonTime+'</td>'+
                            '<td>'+lastLoginTime+'</td>'+
                                stateHTML+
                        '</tr>';
                    });
                        $('.userTable tbody').html(pageHtml);
                }

            });
		}
		
		
		//获取总页数
	    function getPagesFun(){
        openLoadingFun()//开启loading
        var pages = 0;
		   $.ajax({
		        type: "POST",
		        contentType:"application/x-www-form-urlencoded;charset=utf-8", 
		        url: "getNewUserManagePageNum",
                async: false,//同步
		        /* dataType:"json",   */
		        success: function(data) {
                closeLoadingFun()//关闭loading
                    pages=data;
		        }
			}) ;
            return pages;
        }
		
		//分页加载数据
	    function createPageFun(page){
            if(page==null || typeof(page) == "undefined"){
                page=1;
            }
			$(".pagediv").createPage({
	            pageNum : getPagesFun(),//总页数
	            current : page,//当前页数
	            shownum: 9,//最多显示的页数项
	            activepage: "current",//activepage当前页选中样式
	            activepaf: "",//默认class是“nextpage”//activepaf下一页选中样式
	            backfun:function(p){
	            	//p.current// 总页数
	            	changePage(p.current);

	            }
	        });
		}
	


$(function (){
	

	var pagesss = ${numberOfPages};
	$(".pagediv").createPage({
        pageNum : pagesss,//总页数
        current : 1,//当前页数
        shownum: 9,//最多显示的页数项
        activepage: "current",//activepage当前页选中样式
        activepaf: "",//默认class是“nextpage”//activepaf下一页选中样式
        backfun:function(p){
        	//p.current//总页数
        	changePage(p.current);
        }
    });
	
		/*
		 * 删除暂时不用 ，此处代码保留
		 */
	
        /* //删除交互
        $('.userTable').on('click','.del_btn', function () {
        	var  tar = this;
           var tr = $(this).parents('tr');
           var tr_name = tr.find('td')[1].innerHTML;

           var lay01=layer.confirm('您确定要删除“'+tr_name+'”吗？', {
               closeBtn: 0,
               title:false,//标题
               btn: ['取消','确定'] //按钮
           }, function(){
               layer.close(lay01);
               return;

           }, function(){
               //此处在放（后台删除数据代码）
               var tar_id = $($(tar).parents('tr')[0]).find('td:first-of-type').text();
               
               
               console.log(deleteuser(tar_id));
               var tr_n = $('.userTable tbody tr').size();//当前页的有多上项
               if(deleteuser(tar_id)== 'success'){//后台已经删除数据了
            	   var page = parseInt($('.jQPagenum.current').text());
              	var pages = getPagesFun();
            	   
            	   if(pages> page){
            		   createPageFun(page);
            		   changePage(page);
            	   }else{
            		   if(tr_n==1){
            			   createPageFun(pages);
            			   changePage(pages);
            		   }else{
            			   changePage(page);
            		   }
            	   }
            	   
               }
               
               layer.close(lay01);
               return;
           });
        }); */

        //冻结交互 和 解冻交互
        $('.userTable').on('click','.fro_btn', function () {
            var tar = this;
            var tr = $(this).parents('tr');
            var tr_name = tr.find('td')[1].innerHTML;

          //解冻
            if($(tar).hasClass('unlock')){
            	 var lay02=layer.confirm('您确定要解冻“'+tr_name+'”吗？', {
                     closeBtn: 0,
                     title:false,//标题
                     btn: ['取消','确定'] //按钮
                 }, function(index,layero){
                     layer.close(lay02);
                     return;

                 }, function(){
                     //此处在放（后台冻结数据代码）
                     var tar_id = $($(tar).parents('tr')[0]).find('td:first-of-type').text();
                     
                     
                     openLoadingFun('数据更新中，请稍等...')//开启loading
         			var params = {};  //params.XX必须与Spring Mvc controller中的参数名称一致   
         		    params.id =tar_id;
         		   $.ajax({
                       type: "POST",
                       //async:false,
                       data: params,
                       url: "unblockAccount",
                       datatype:"json",
                       success: function(data) {
                             if(data == 'success'){
                                 callbackStr = 'success';
                                $(tr.find('td')[5]).html('正常').css('color','#d73938');
                             	$(tar).html('冻结').removeClass('unlock'); 
                             	layer.close(lay02);
                                 closeLoadingFun()//关闭loading
                             }else{
                             	layer.close(lay02);
                             	layer.msg("解冻失败！",{time:2000});
                             	closeLoadingFun()//关闭loading
                             } 
                         }
                     });
                     
                     return;
                 });
            	 
            	 
            }else{
            	
           	//冻结
            	var lay01=layer.confirm('您确定要冻结“'+tr_name+'”吗？', {
                    closeBtn: 0,
                    title:false,//标题
                    btn: ['取消','确定'] //按钮
                }, function(index,layero){
                    layer.close(lay01);
                    return;

                }, function(){
                    //此处在放（后台冻结数据代码）
                    var tar_id = $($(tar).parents('tr')[0]).find('td:first-of-type').text();
                    
                    openLoadingFun('数据更新中，请稍等...')//开启loading
        			var params = {};  //params.XX必须与Spring Mvc controller中的参数名称一致   
        		    params.id =tar_id;
                    $.ajax({
                        type: "POST",
                        //async:false,
                        data: params,
                        url: "blockAccount",
                        datatype:"json",
                        success: function(data) {
                            if(data == 'success'){
                                callbackStr = 'success';
                                $(tr.find('td')[5]).html('冻结').css('color','#999');
                            	$(tar).html('解冻').addClass('unlock'); 
                            	layer.close(lay01);
                                closeLoadingFun()//关闭loading
                            }else{
                            	layer.close(lay01);
                            	layer.msg("冻结失败！",{time:2000});
                            	closeLoadingFun()//关闭loading
                            } 
                        }
                    });
                    
                    return;
                });
            }
            
        });
        //新增用户
        $('.btn_add').on('click', function () {
            var addUserHtml = '<div id="addUser">'+
                    '<form target="add_iframe">'+
                    '<h2>新增用户</h2>'+
                    '<label>用户名：</label>'+
                    '<input class="add_user" type="text"/><br/>'+

                    '<label>初始密码：</label>'+
                    '<input class="add_pwd" type="text"/><br/>'+

                    '<label>联系电话：</label>'+
                    '<input class="add_phone" type="text"/><br/>'+

                    '<div class="addUserBtn">'+
                    '<a class="noADD">取消</a>'+
                    '<a class="yesADD" href="javascript:void(0);" onclick="newuser{"11","22","33")">添加</a>'+
                    '</div>'+

                    '</form>'+
                    '</div>';

            if($('#addUser').size()>0){
                $('#addUser .add_user').val("");
                $('#addUser .add_pwd').val("");
                $('#addUser .add_phone').val("");
            }else{
                $('body').append(addUserHtml);
            }
            $('#addUser').fadeIn(200);
        });
        
        $('body').on('click', '.yesADD',function () {
            var user = $('.add_user').val();
            var pwd = $('.add_pwd').val();
            var phone = $('.add_phone').val();

            //用户名验证
            if(user =="") {
                layer.tips('请输入用户名！', $('.add_user'), {
                    time: 2000,
                    tips: [2, '#d73938'],
                    tipsMore: true
                });
                return false;
            }

            //密码验证
            if(user =="") {
                layer.tips('请输入密码！', $('.add_pwd'), {
                    time: 2000,
                    tips: [2, '#d73938'],
                    tipsMore: true
                });
                return false;
            }

            //电话号码验证
            if(user =="") {
                layer.tips('请输入联系电话！', $('.add_phone'), {
                    time: 2000,
                    tips: [2, '#d73938'],
                    tipsMore: true
                });
                return false;
            }
          	
            $('#addUser form').submit();
            
          	//后台刷新当前页面
          	if(newuser(user,pwd,phone) == 'success'){
          		layer.msg("添加成功",{time:2000});
                var page = parseInt($('.jQPagenum.current').text());
                createPageFun(page);
         	   	changePage(page);
          	}else{
          		layer.msg("添加失败！",{time:2000});
          	}
            $('#addUser').fadeOut(200);

        });


        $('body').on('click','.noADD', function () {
            $('#addUser').fadeOut(200).delay(200).remove();
        });
 });
</script>
</body>
</html>