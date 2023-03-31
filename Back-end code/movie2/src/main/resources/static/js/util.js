/**
 *
 * @param myUrl 请求的url
 * @param myType 提交方式， get ，post，put，delete ...
 * @param myContentType 提交的数据的字符类型,https://www.runoob.com/http/http-content-type.html
 * @param myData   提交的json数据
 * @param mySuccess 成功后的回调函数
 */
function myAjax(myUrl,myType,myContentType,myData,mySuccess){
    if(myType==""){
        myType="get";
    }
    if(myContentType=""){
        myContentType="application/x-www-form-urlencoded;charset=utf-8";
    }
    $.ajax({
        url:myUrl,
        type:myType,
        contentType:myContentType, //数据类型是json
        data:JSON.stringify(myData),
        headers:{
            "token": localStorage.getItem("token"),
        },
        success:mySuccess,
        error:function (data) {
            console.log(data);
        }
    });
}
// 日期处理
//https://blog.csdn.net/Kiss_The_sky/article/details/78168463
function convertTime(jsonTime, format) {
    var date = new Date(parseInt(jsonTime.replace("/Date(", "").replace(")/", ""), 10));
    var formatDate = date.format(format);
    return formatDate;
}

Date.prototype.format = function (format) {
    var date = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S+": this.getMilliseconds()
    };

    if (/(y+)/i.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }

    for (var k in date) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
        }
    }

    return format;
}

//yyyy-MM-dd HH:mm:SS
function getDateTime(date) {
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    var hh = date.getHours();
    var mm = date.getMinutes();
    var ss = date.getSeconds();
    return year + "-" + month + "-" + day + " " + hh + ":" + mm + ":" + ss;
}
//调用的是这个方法
function ConvertJSONDateToJSDate(jsondate) {
    var date = new Date(parseInt(jsondate.replace("/Date(", "").replace(")/", ""), 10));
    return date;
}