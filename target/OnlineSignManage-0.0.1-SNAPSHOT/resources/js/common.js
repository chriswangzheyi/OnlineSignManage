/**
 * ���ýű�(����jQuery.js)
 */
//��ȡ���������
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


$(function () {
    //�����ie8(ִ�нű�:jquery.placeholder.js.min.js)
    if(navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion .split(";")[1].replace(/[ ]/g,"")=="MSIE8.0"){
        $('input, textarea').placeholder();
    }
    //���ż���б�����ң�
    $('.listBox tbody tr:nth-child(even)').css('background-color','#f7f7f7');

});