/**
 * 公用脚本(基于jQuery.js)
 */

//重写toLocaleString()
	Date.prototype.toLocaleString = function() {
        var m = this.getMonth() + 1;//月
        var d = this.getDate();//天
        var h = this.getHours();//小时
        var i = this.getMinutes();//分钟
        var s = this.getSeconds();//秒杀
        function timeFormat(t){
            t = (t<10) ? ('0'+parseInt(t)) : t;
            return t;
        }
        return this.getFullYear() + "-" + timeFormat(m) + "-" + timeFormat(d) + "&nbsp;&nbsp;" + timeFormat(h) + ":" + timeFormat(i) + ":" + timeFormat(s);
    };

//表格偶数行背景变灰：
function listEvenStyleFun(){
    $('.listBox tbody tr:nth-child(even)').css('background-color','#f7f7f7');
}


//决定定位的div 可以通过drag() 拖动元素。（封装到$）
$.fn.extend({
    drag: function(){
        this.on('mousedown',function(e){
            $(this).css('position','absolute');
            var disX = e.clientX - $(this).position().left,
                disY = e.clientY - $(this).position().top,
                $self = $(this);
            $(document).on('mousemove',function(e){
                $self.css('left',e.clientX - disX);
                $self.css('top',e.clientY - disY);
            })
            $(document).on('mouseup',function(){
                $(document).off();
            })
        });
    }
});

//获取图片的实际宽高
function getImageWidth(url,callback){
    var img = new Image();
    img.src = url;

    // 如果图片被缓存，则直接返回缓存数据
    if(img.complete){
        callback(img.width, img.height);
    }else{
        // 完全加载完毕的事件
        img.onload = function(){
            callback(img.width, img.height);
        }
    }
}
//限制图片的宽高显示
function imgWidthHeigthSET(imgEL,boxw,boxh){
    var _box = boxw/boxh;
    $(imgEL).each(function () {
        var tar =this;
        var imgSrc = $(this).attr("src");
        getImageWidth(imgSrc,function(w,h){
            var _img = w/h;
            if(_box>_img && h>boxh){//同高度，盒子比图片宽时
                $(tar).css({'width':'auto','height':'100%'});
            }else if(_box<_img && w>boxw){//同宽度，图片比盒子高时
                $(tar).css({'width':'100%','height':'auto'});
            }else{
                $(tar).css({'width':'auto','height':'auto'});
            }
        });
    });
}

//获取今天的日期
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "/";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
        + " " + date.getHours() + seperator2 + date.getMinutes()
        + seperator2 + date.getSeconds();
    return currentdate;
}

//当前闪烁特效方法
function twinkleFun(el){

    var timer=null;
    var i=0;
    clearInterval(timer);
    var timer=setInterval(function(){
        $(el).css('visibility',(i++%2?"hidden":"visible"));
        i>8&&clearInterval(timer);
    },80);
}

//封装提示和滚动方法
function alertScrollFun(elem,elemlabel){
    layer.msg('"'+ $(elemlabel).html() +'"不能为空！',{time:3000});
    var _top =elem.offset().top;
    //滚动至相应位置
    $('html,body').animate({
        scrollTop: elem.offset().top - 70
    }, 200);
    //当前闪烁提示
    setTimeout(function () {
        twinkleFun(elemlabel);
    },200);
}



// TODO 自定义滚动条样式
function windowHeigthChange(){
    //$("html").niceScroll({
    //    cursorborder:"",            //滚动条边框，默认“1px solid #fff”
    //    cursorcolor:"#363f45",      //滚动条颜色，默认“#424242”
    //    cursorwidth:"8px",          //滚动条宽度，默认“5px”
    //    zindex:"2000",              //滚动条位置层级显示，默认“auto”
    //    cursoropacitymax:0.5        //滚动条不透明度，默认“1”
    //});
    $(".hhLIsit").size()>0 && $(".hhLIsit").niceScroll({cursorborder:"",cursoropacitymax:0.5,cursorcolor:"#363f45"});
    $(".mmLIsit").size()>0 && $(".mmLIsit").niceScroll({
        cursorborder:"",
        cursoropacitymax:0.5,
        cursorcolor:"#363f45",
        horizrailenabled:false,
        zindex:"2000"
    });
    $(".shopHours_List ul").size()>0 && $(".shopHours_List ul").niceScroll({
        cursorborder:"",
        cursoropacitymax:0.5,
        cursorcolor:"#363f45",
        horizrailenabled:false,
        zindex:"2000"
    });
}

$(function () {
    //如果是ie8(执行脚本:jquery.placeholder.js.min.js)
    if(navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion .split(";")[1].replace(/[ ]/g,"")=="MSIE8.0"){
        $('input, textarea').placeholder();
    }

    listEvenStyleFun();//表格偶数行背景变灰：


//TODO 多图片上传（餐厅实拍）
    function imgChange(obj1, obj2) {
        //获取点击的文本框
        var file = document.getElementById("file");
        //存放图片的父级元素
        var imgContainer = document.getElementsByClassName(obj1)[0];
        //获取的图片文件
        var fileList = file.files;
        //文本框的父级元素
        var input = document.getElementsByClassName(obj2)[0];
        var imgArr = [];
        //遍历获取到得图片文件
        for (var i = 0; i < fileList.length; i++) {
            var imgUrl = window.URL.createObjectURL(file.files[i]);
            if(!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(file.files[i].name)){
                layer.msg("您上传的图片格式不正确，请重新选择!",{time:3000});
                return false;
            }else{
                imgArr.push(imgUrl);
                var img = document.createElement("img");
                img.setAttribute("src", imgArr[i]);
                var imgAdd = document.createElement("div");
                imgAdd.setAttribute("class", "z_addImg");
                imgAdd.appendChild(img);
                imgContainer.appendChild(imgAdd);

                imgWidthHeigthSET(img,100,100);//显示显示的宽高
            }
        }
        imgRemove();
    };

    function imgRemove() {
        var imgList = document.getElementsByClassName("z_addImg");
        var mask = document.getElementsByClassName("z_mask")[0];
        var cancel = document.getElementsByClassName("z_cancel")[0];
        var sure = document.getElementsByClassName("z_sure")[0];
        for (var j = 0; j < imgList.length; j++) {
            imgList[j].index = j;
            imgList[j].onclick = function() {
                var t = this;
                mask.style.display = "block";
                cancel.onclick = function() {
                    mask.style.display = "none";
                };
                sure.onclick = function() {
                    mask.style.display = "none";
                    t.style.display = "none";
                };

            }
        };
    };
    imgRemove();

    $('#details_Content').on('change','.shopimg',function () {
        imgChange('z_photo','z_file');
    });


    //TODO 餐厅工商营业执照
    imgWidthHeigthSET($('.busLic_img img'),150,150);
    $('#details_Content').on('change','.busLic',function () {
        console.log(456456);
        var _Url = window.URL.createObjectURL(this.files[0]);//图片地址
        var img_name = this.files[0].name;//选择的图片的名称
        if (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(img_name)){
            layer.msg("您上传的图片格式不正确，请重新选择!",{time:3000});
            return false;
        }else{
            $('.busLic_text').html('已选择文件');
            $('.busLic_name').html(img_name);
            if($('.busLic_img img').size()>0){
                $('.busLic_img img')[0].src = _Url;
            }else{
                $('.busLic_img').html('<img src="'+_Url+'">');
            }
            imgWidthHeigthSET($('.busLic_img img'),150,150);
        }
    });
    //TODO 餐厅卫生许可证
    imgWidthHeigthSET($('.DCMcontract_img img'),150,150);
    $('#details_Content').on('change','.DCMcontract',function () {
        console.log(456456);
        var _Url = window.URL.createObjectURL(this.files[0]);//图片地址
        var img_name = this.files[0].name;//选择的图片的名称
        if (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(img_name)){
            layer.msg("您上传的图片格式不正确，请重新选择!",{time:3000});
            return false;
        }else{
            $('.DCMcontract_text').html('已选择文件');
            $('.DCMcontract_name').html(img_name);
            if($('.DCMcontract_img img').size()>0){
                $('.DCMcontract_img img')[0].src = _Url;
            }else{
                $('.DCMcontract_img').html('<img src="'+_Url+'">');
            }
            imgWidthHeigthSET($('.DCMcontract_img img'),150,150);
        }
    });
    //TODO 授权委托书
    imgWidthHeigthSET($('.proxy_img img'),150,150);
    $('#details_Content').on('change','.proxy',function () {
        var _Url = window.URL.createObjectURL(this.files[0]);//图片地址
        var img_name = this.files[0].name;//选择的图片的名称
        if (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(img_name)){
            layer.msg("您上传的图片格式不正确，请重新选择!",{time:3000});
            return false;
        }else{
            $('.proxy_text').html('已选择文件');
            $('.proxy_name').html(img_name);
            if($('.proxy_img img').size()>0){
                $('.proxy_img img')[0].src = _Url;
            }else{
                $('.proxy_img').html('<img src="'+_Url+'">');
            }
            imgWidthHeigthSET($('.proxy_img img'),150,150);
        }
    });
    //“改改照片”按钮的hover交互
    $('.busLic,.proxy,.DCMcontract').size()> 0 && $('.busLic,.proxy,.DCMcontract').bind('mouseover mouseout',function (e) {
        if(e.type == 'mouseover'){
            $(this).prev().css({'backgroundColor':'#b3b3b3'});
        }
        if(e.type == 'mouseout'){
            $(this).prev().css({'backgroundColor':'#ccc'});
        }
    });



    //TODO 图片宽高限制：
    //商家详情页面
    $('.det_imgBox img').each(function () {
        imgWidthHeigthSET(this,100,100);
    });
    //商家信息更改页面
    $('.z_addImg img').each(function () {
        imgWidthHeigthSET(this,100,100);
    });


    windowHeigthChange();//自定义滚动条
    $(window).resize(function () {
        windowHeigthChange();//自定义滚动条

    });





});