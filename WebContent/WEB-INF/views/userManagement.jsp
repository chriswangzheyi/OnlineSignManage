<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>     
<!DOCTYPE html><html>
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
                    <th>编号</th>
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
	                
	                <td>${var.id}</td>
                    <td>${var.username}</td>
                    <td>${var.phone}</td>
                    <td>${var.createTime}</td>
                    <td>${var.lastLoginTime}</td>
                    <td class="userStatus_normal">
      				<c:if test="${var.status == 0}">
					 冻结
					</c:if> 
					<c:if test="${var.status == 1}">
					  正常
					</c:if>              
                   	</td>
                    <td class="table_btns">
                        <a class="fro_btn" href="javascript:void(0);" onclick="blockuser(${var.id})">冻结</a>
                        <a class="del_btn" href="javascript:void(0);" onclick="deleteuser(${var.id})">删除</a>
                    </td>
	                
	                </tr>
	            </c:forEach>
                
                
                
                
            </tbody>
        </table>

        <!--分页-->
        <div class="pagingbox">
            <div class="jumppag">
                <form class="inputpag">
                    <label for="pagNum">跳至</label>
                    <input id="pagNum" type='text' onkeyup="this.value=this.value.replace(/[^0-9-]+/,'');" />
                </form>
                <a class="jumppagBtn" href="javascript:;">跳转</a>
            </div>
            <div class="paging">
                <span class="prevpag"><i></i></span>
                <a class="active">1</a>
                <a>2</a>
                <a>3</a>
                <a>4</a>
                <a>5</a>
                <span class="ellipsis">…</span>
                <a>100</a>
                <span class="nextpag"><i></i></span>
            </div>
        </div>
        <iframe id="id_iframe" name="add_iframe" style="display:none;"></iframe>
    </div>

</div>

<script>

		//冻结用户
		function blockuser(id){
			
			var params = {};  //params.XX必须与Spring Mvc controller中的参数名称一致   
		    params.id =id;
			 		
				$.ajax({
			        type: "POST",
			        async:false,
			        data: params,
			        url: "blockAccount",
			        datatype:"json",  
			        success: function(data) {
			        	var blockuser= data;
			        }	       	        
				})  
		}
		
		
		
		//解冻用户
		function blockuser(id){
			
			var params = {};  //params.XX必须与Spring Mvc controller中的参数名称一致   
		    params.id =id;
			 		
				$.ajax({
			        type: "POST",
			        async:false,
			        data: params,
			        url: "unblockAccount",
			        datatype:"json",  
			        success: function(data) {
			        	var blockuser= data;
			        }	       	        
				})  
		}
		
		
		
		//删除用户
		function deleteuser(id,p){
			
			var params = {};  //params.XX必须与Spring Mvc controller中的参数名称一致   
		    params.id =id;
			params.currentPage=p
			 		
				$.ajax({
			        type: "POST",
			        async:false,
			        data: params,
			        url: "deleteAccount",
			        datatype:"json",  
			        success: function(data) {
			        	var blockuser= data;
			        }	       	        
				})  
		}
		
		
		//新增用户
		function newuser(username,password,phone){
			var params = {};  //params.XX必须与Spring Mvc controller中的参数名称一致   
		    params.username =username;
		    params.password =password;
		    params.phone =phone;
			 		
				$.ajax({
			        type: "POST",
			        async:false,
			        data: params,
			        url: "newAccount",
			        datatype:"json",  
			        success: function(data) {
			        	var blockuser= data;
	
			        }	       	        
				})  
		}
		
		
		//换页
		function changePage(p){
			
			var params = {};  //params.XX必须与Spring Mvc controller中的参数名称一致  
			params.targetPage=p
			 		
				$.ajax({
			        type: "POST",
			        data: params,
			        contentType:"application/x-www-form-urlencoded;charset=utf-8", 
			        url: "changeUserPage",
			        /* dataType:"json",   */
			        success: function(data) {
			        		
			        }	       	        
				})  
		}


    $(function () {
        //删除交互
        $('.userTable').on('click','.del_btn', function () {
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
               tr.remove();
               layer.close(lay01);
               return;
           });
        });

        //冻结交互
        $('.userTable').on('click','.fro_btn', function () {
            var that = this;
            var tr = $(this).parents('tr');
            var tr_name = tr.find('td')[1].innerHTML;

            var lay01=layer.confirm('您确定要冻结“'+tr_name+'”吗？', {
                closeBtn: 0,
                title:false,//标题
                btn: ['取消','确定'] //按钮
            }, function(index,layero){
                layer.close(lay01);
                return;

            }, function(){
                //此处在放（后台删除数据代码）
                $(tr.find('td')[5]).html('冻结').css('color','#999');
                $(that).html('解冻').addClass('unlock');
                layer.close(lay01);
                return;
            });
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
            //呼叫后台增加用户
            newuser(user,pwd,phone)

            /*****************/
            //编号(建议后台给ID 赋值给编号)
            var num =001;
            if($('.userTable tbody tr').size()>0){
                var number = parseInt($('.userTable tbody tr:last-of-type td').html());
                num =number+1;//编号
                num<10? num = '00'+num :
                        num<100? num = '0'+num :num;
            }
            /*****************/

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
            $('#addUser').fadeOut(200);


            var addHtml = '<tr>'+
                    '<td>'+num+'</td>'+
                    '<td>'+user+'</td>'+
                    '<td>'+phone+'</td>'+
                    '<td>'+getNowFormatDate()+'</td>'+
            '<td>----</td>'+
            '<td class="userStatus_normal">正常</td>'+
                    '<td class="table_btns">'+
                    '<a class="fro_btn">冻结</a><a class="del_btn">删除</a>'+
            '</td>'+
            '</tr>';
            $('.userTable tbody').append(addHtml);

        });


        $('body').on('click','.noADD', function () {
            $('#addUser').fadeOut(200).delay(200).remove();
        });
    });
</script>
</body>
</html>