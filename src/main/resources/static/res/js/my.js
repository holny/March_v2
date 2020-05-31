
let global_url_map = {
    context_base:null,
    blog_page_url:'blog/page',
    blog_url:'blog',
    blog_edit_url:'blog/edit',
    blog_preview_url:"blog/preview",
    user_url:'user',
    series_list_url:'blog/serieslist',
    type_list_url:'blog/typelist',
    profile_url:'profile',
    series_url:'series',
    file_show_avatar_url:'file/showPic',
    file_show_Bg_url:'file/showBg',
    blog_image_up_url:'file/upload/blogImage',
    summary_url:'summary',
    like_click_url:'like',
    home_url:'home',
    editormd_url:'editormd',
    captchaPic_url:'captcha',
    userlogin_url:'userlogin',
    registercheck_url:'registercheck',
    register_url:'register',
    myinfo_url:'myinfo',
    file_upload_url:'file/upload/img',
}

let global_server_running_time = 0;

let userMap={
    user_id:null,
    user_profile_pic:null,
    user_credit:null,

};

//判断数据是否为Null或者undefined或者为空字符串
function checkIsNullOrEmpty(value) {
    //正则表达式用于判斷字符串是否全部由空格或换行符组成
    var reg = /^\s*$/
    //返回值为true表示不是空字符串
    return (value != undefined && value != null &&  !reg.test(value))
}

$(function () {
    if(typeof toastr!="undefined") {
        // toastr.options = {
        //     "closeButton": false,//显示关闭按钮
        //     "debug": false,//启用debug
        //     "positionClass": "toast-bottom-right",//弹出的位置
        //     "showDuration": "300",//显示的时间
        //     "hideDuration": "1000",//消失的时间
        //     "timeOut": "5000",//停留的时间
        //     "extendedTimeOut": "5000",//控制时间
        //     "showEasing": "swing",//显示时的动画缓冲方式
        //     "hideEasing": "linear",//消失时的动画缓冲方式
        //     "showMethod": "fadeIn",//显示时的动画方式
        //     "hideMethod": "fadeOut",//消失时的动画方式
        //     "escapeHtml": true, //消失时的动画方式
        // };
        toastr.options = {
            timeOut: 5e3,
            closeButton: !0,
            debug: !1,
            newestOnTop: !0,
            progressBar: !0,
            positionClass: "toast-bottom-right",
            preventDuplicates: !0,
            onclick: null,
            showDuration: "300",
            hideDuration: "1000",
            extendedTimeOut: "1000",
            showEasing: "swing",
            hideEasing: "linear",
            showMethod: "fadeIn",
            hideMethod: "fadeOut",
            tapToDismiss: !1

        };
    }

    if($('.monthMinTimePicker').length>0) {
        $('.monthMinTimePicker').datetimepicker({
            language: 'zh-CN',
            format: "yyyy年mm月dd日 ",
            weekStart: 1,
            todayBtn: "linked",
            autoclose: true,
            todayHighlight: true,
            startView: 3,
            forceParse: true,
            minView: "month",
            startDate: new Date(1970, 0, 0),
            endDate: new Date(),
        });
    }
});

// Search区域
function search_bar_hide_or_show(controller,searchForm) {
    // searchForm.hide();
    let hidden = searchForm.is(':hidden');// true 为隐藏状态
    let visible = searchForm.is(':visible');//true 为显示状态
    if(hidden){
        controller.empty().append($('<i></i>').addClass('fad fa-ban')).append("    隐藏筛选");
        searchForm.show();
    }else{
        controller.empty().append($('<i></i>').addClass('fas fa-filter')).append("    筛选一下?");
        searchForm.hide();
    }

}

function init_series_list(userId,elem,sourceFrom,defaultValue) {
    $.ajax({
        url:global_url_map.context_base+global_url_map.series_list_url+"/"+userId+"?sourceFrom="+sourceFrom,
        type: "GET",
        success: function (result) {
            if(result.code==100){
                let seriesList = result.extend.seriesList;
                if(checkIsNullOrEmpty(defaultValue)){
                    elem.empty().append($('<option></option>').attr("value","").attr('disabled','disabled').append('Blog series...'));
                }else{
                    elem.empty().append($('<option></option>').attr("value","").attr('selected','selected').attr('disabled','disabled').append('Blog series...'));
                }
                $.each(seriesList, function (index, item) {
                    if(checkIsNullOrEmpty(defaultValue)&&defaultValue==item.seriesId){
                        elem.append($('<option></option>').attr('value',item.seriesId).attr('selected','selected').append(item.seriesName));
                    }else{
                        elem.append($('<option></option>').attr('value',item.seriesId).append(item.seriesName));
                    }
                });
            }else{
                toastr.error("Series列表信息获取失败!" + result.msg);
            }
        },
        error: function () {
            toastr.error("Series列表信息获取失败!" + result.msg);
        }
    });
}

function init_type_list(userId,elem,sourceFrom) {
    $.ajax({
        url:global_url_map.context_base+global_url_map.type_list_url+"/"+userId+"?sourceFrom="+sourceFrom,
        type: "GET",
        success: function (result) {
            if(result.code==100){
                let typeList = result.extend.typeList;
                elem.empty().append($('<option></option>').attr("value","").attr('selected','selected').attr('disabled','disabled').append('Blog type...'));
                $.each(typeList, function (index, item) {
                    elem.append($('<option></option>').attr('value',item.typeId).append(item.typeName));
                });
            }else{
                toastr.error("Type列表信息获取失败!" + result.msg);
            }
        },
        error: function () {
            toastr.error("Type列表信息获取失败!" + result.msg);
        }
    });
}

function blog_modify_checkbox(elem,mode) {
    let selector;
    let input;
    if(mode=="series") {
        selector = elem.parent().parent().prev().find('select[name*=seriesId]');
        input = elem.parent().parent().next().find('input[name*=newSeries]');
    }else if(mode=="newSeries") {
        selector = elem.parent().parent().prev().find('select[name*=newSeriesId]');
        input = elem.parent().parent().next().find('input[name*=newSeriesName]');
    }else  if(mode=="type") {
        selector = elem.parent().parent().prev().find('select[name*=blogType]');
        input = elem.parent().parent().next().find('input[name*=newType]');
    }else{
        return;
    }
    if (elem.prop("checked")) {
        if(input.length>0){
            input.removeAttr("disabled");
        }
        if(selector.length>0){
            selector.val("");
            selector.attr("disabled","disabled");
        }
    } else {
        if(input.length>0){
            input.val("");
            input.attr("disabled","disabled");
        }
        if(selector.length>0){
            selector.removeAttr("disabled");
        }
    }
}

function lockFormUI(item) {
    if(item.find("input").length>0) {
        item.find("input").attr("readonly", "readonly");
    }
    if(item.find("button").length>0) {
        item.find("button").attr("disabled", "disabled");
    }
    if(item.find("select").length>0) {
        item.find("select").attr("disabled", "disabled");
    }
    if(item.find("i")>0) {
        item.find("i").show();
    }
};

function unLockFormUI(item){
    if(item.find("input").length>0) {
        item.find("input").removeAttr("readonly");
    }
    if(item.find("button").length>0) {
        item.find("button").removeAttr("disabled");
    }
    if(item.find("select").length>0) {
        item.find("select").removeAttr("disabled");
    }
    if(item.find(".captchaPic")>0) {
        item.find(".captchaPic").trigger("click");
    }
    if(item.find("i")>0) {
        item.find("i").hide();
    }
}

// 让用户输入不了空格
$(".hNoSpaceNAutoClear").keyup(function () {
    this.value = this.value.replace(/\s+/g, '');
});

$(".hNoSpaceNAutoClear").focus(function () {
    let elem = $( this );
    // elem.val("");
    if(elem.next(".text-danger").length>0) {
        elem.next(".text-danger").empty();
    }
});


// 正则化校验,target是被检验目标，pattern用什么规则校验
function validate_data(target, pattern) {
    // Jquery api 文档上可以查常用正则表达式.
    const emailValid = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
    const nameValid = /(^([a-zA-Z0-9_\.-]+){5,10}$)|(^([\u4e00-\u9fa5\.]+){2,10}$)/;
    const p = new RegExp("[`~!@%#$^&*()=|{}':;',　\\[\\]<>/? \\.；：%……+￥（）【】‘”“'。，、？]");
    // const nameValid = /(^[\u2E80-\u9FFF]{2,5}$)/;
    const passwordValid = /^[a-zA-Z0-9_-]{4,18}$/;
    const titleValid = /(^(([a-zA-Z0-9_\.-]+)|([\u2E80-\u9FFF]+)|([%&'\s,;\[\]\+\=?$\x22]+)){2,10}$)/;
    const introValid = /(^(([a-zA-Z0-9_\.-]+)|([\u2E80-\u9FFF]+)|([%&'\s,;\[\]\+\=?$\x22]+)){2,10}$)/;
    const phoneNumValid = /^1[3456789]\d{9}$/;
    const numericalValid = /^[0-9]*$/;
    if (pattern == "email") {
        if (!emailValid.test(target)) {
            return false;
        } else {
            if(target.length>30){
                return false;
            }else{
                return true;
            }
        }
    } else if (pattern == "name") {
        if (!nameValid.test(target)) {
            return false;
        } else {
            if(target.length>10){
                return false;
            }else{
                return true;
            }
        }
    } else if (pattern == "password") {
        if (!passwordValid.test(target)) {
            return false;
        } else {
            if(target.length>18){
                return false;
            }else{
                return true;
            }
        }
    }
    else if (pattern == "title") {
        if (!titleValid.test(target)) {
            return false;
        } else {
            if(target.length>30){
                return false;
            }else{
                return true;
            }
        }
    }
    else if (pattern == "intro") {
        if (!introValid.test(target)) {
            return false;
        } else {
            if(target.length>200){
                return false;
            }else{
                return true;
            }
        }
    }
    else if (pattern == "phoneNum") {
        if (!phoneNumValid.test(target)) {
            return false;
        } else {
            if(target.length>16){
                return false;
            }else{
                return true;
            }
        }
    }
    else if (pattern == "numerical") {
        if (!numericalValid.test(target)) {
            return false;
        } else {
            if(target.length>10){
                return false;
            }else{
                return true;
            }
        }
    }
    return false;
}

// sideBar userInfo


function get_user_statics(userId,userInfoSideBarArea) {
    $.ajax({
        url: global_url_map.context_base+global_url_map.user_url+"/"+userId,
        type: "GET",
        success: function (result) {
            if(result.code==100){
                user_info_area_init(result.extend.userStatistics,userInfoSideBarArea);
            }else{
                toastr.error("用户信息获取失败!" + result.msg);
            }
            // 这里result如果能一个字符串连用控制台显示，那控制台就显示Object，所以单独。
            // myInfo = result.extend.user;

        },
        error: function () {
            toastr.error("用户信息获取失败!");
        }
    });
}

function init_user_avatar(src) {
    $('.userProfilePic').attr("src", src);
}

function init_background_image(src) {
    $(".hWhole_bg").attr("style","background: url('"+src+"') no-repeat fixed;")
}

function user_info_area_init(userInfo,userInfoArea) {
    let baseInfoArea = $(userInfoArea.find('.widget')[0]);
    if(checkIsNullOrEmpty(userInfo.userProfilePic)) {
        // baseInfoArea.find('img').attr("src", basePath + "/file/showPic/" + userInfo.userProfilePic);
        // 顺便初始化Navbar的头像
        init_user_avatar(global_url_map.context_base + global_url_map.file_show_avatar_url+'/' + userInfo.userProfilePic);
    }else{
        init_user_avatar(global_url_map.context_base+'/static/res/img/default.jpeg');
    }
    if(checkIsNullOrEmpty(userInfo.userBg)){
        init_background_image(global_url_map.context_base+global_url_map.file_show_Bg_url+'/'+userInfo.userBg);
    }
    let name = null;
    if(checkIsNullOrEmpty(userInfo.userNickname)) {
        name = userInfo.userNickname;
    }
    name = name+ "(" + userInfo.userName + ")"
    if (checkIsNullOrEmpty(name)) {
        baseInfoArea.find('h4').empty().append(userInfo.userNickname + "(" + userInfo.userName + ")");
    }
    $(baseInfoArea.find('p')[0]).empty().append(userInfo.blogStatisticsVO.count);
    $(baseInfoArea.find('p')[1]).empty().append(userInfo.blogStatisticsVO.likeSUM);
    $(baseInfoArea.find('p')[2]).empty().append(userInfo.blogStatisticsVO.commentSUM);
    $(baseInfoArea.find('p')[3]).empty().append(userInfo.blogStatisticsVO.viewSUM);

    if(checkIsNullOrEmpty(userInfo.githubLink)){
        $(baseInfoArea.find('a')[0]).attr("href",userInfo.githubLink);
    }
    if(checkIsNullOrEmpty(userInfo.weiboLink)) {
        $(baseInfoArea.find('a')[1]).attr("href",userInfo.weiboLink);
    }
    if(checkIsNullOrEmpty(userInfo.wechatLink)) {
        $(baseInfoArea.find('a')[2]).attr("href",userInfo.wechatLink);
    }
    if(checkIsNullOrEmpty(userInfo.otherLink)) {
        $(baseInfoArea.find('a')[3]).attr("href",userInfo.otherLink);
    }
    baseInfoArea.find('.post-quote').find('small').empty().append(userInfo.userMotto);

    let blogInfoArea = $(userInfoArea.find('.widget')[1]);


    init_blog_link(blogInfoArea,userInfo.latestBlogList,'blog',5);
    blogInfoArea.find('a[name="newOrHotSidebarTab"]').on('shown.bs.tab', function (e) {
        // 获取已激活的标签页的名称
        let activeTab = $(e.target).text();
        // 获取前一个激活的标签页的名称
        let previousTab = $(e.relatedTarget).text();

        if(activeTab=="New"){
            init_blog_link(blogInfoArea,userInfo.latestBlogList,'blog',5);
        }else{
            init_blog_link(blogInfoArea,userInfo.mostHotBlogList,'blog',5);
        }
    });

    let blogSeriesInfoArea = $(userInfoArea.find('.widget')[2]);
    init_blog_link(blogSeriesInfoArea,userInfo.blogSeries,'series',5)
    // $(blogSeriesInfoArea.find('.icon-list').find('a')[0]).attr("href",global_url_map.context_base+global_url_map.series_url+"/"+userInfo.blogSeries[0].seriesId);
    // $(blogSeriesInfoArea.find('.icon-list').find('a')[0]).empty().append(userInfo.blogSeries[0].seriesName);
    //
    // $(blogSeriesInfoArea.find('.icon-list').find('a')[1]).attr("href",global_url_map.context_base+global_url_map.series_url+"/"+userInfo.blogSeries[1].seriesId);
    // $(blogSeriesInfoArea.find('.icon-list').find('a')[1]).empty().append(userInfo.blogSeries[1].seriesName);
    //
    //
    // $(blogSeriesInfoArea.find('.icon-list').find('a')[2]).attr("href",global_url_map.context_base+global_url_map.series_url+"/"+userInfo.blogSeries[2].seriesId);
    // $(blogSeriesInfoArea.find('.icon-list').find('a')[2]).empty().append(userInfo.blogSeries[2].seriesName);
    //
    //
    // $(blogSeriesInfoArea.find('.icon-list').find('a')[3]).attr("href",global_url_map.context_base+global_url_map.series_url+"/"+userInfo.blogSeries[3].seriesId);
    // $(blogSeriesInfoArea.find('.icon-list').find('a')[3]).empty().append(userInfo.blogSeries[3].seriesName);
    //
    // $(blogSeriesInfoArea.find('.icon-list').find('a')[4]).attr("href",global_url_map.context_base+global_url_map.series_url+"/"+userInfo.blogSeries[4].seriesId);
    // $(blogSeriesInfoArea.find('.icon-list').find('a')[4]).empty().append(userInfo.blogSeries[4].seriesName);


}

function init_blog_link(infoArea,infoList,type,num) {
    let listArea = infoArea.find('.icon-list');
    listArea.empty();
    if (!checkIsNullOrEmpty(infoList)||infoList.length<=0){
        return;
    }
    for(let i=0;i<num;i++)
    {
        let info = infoList[i];
        if(checkIsNullOrEmpty(info)) {
            let url=null;
            let title=null;
            if(type=='blog'){
                url =  global_url_map.context_base + global_url_map.blog_url + "/" + info.blogId;
                title = info.blogTitle;
            }else if(type=='series'){
                url =  global_url_map.context_base+global_url_map.series_url+"/"+info.seriesId;
                title = info.seriesName;
            }else if(type=='user'){
                url =  global_url_map.context_base+global_url_map.profile_url+"/"+info.userId;
                title = info.userNickname+"("+info.userName+")";
            }
            let item = $('<li></li>').append($('<a></a>').attr('href',url).attr('target','_blank').addClass('hText-limit-chars').append(title));
            listArea.append(item);
        }
    }

}

// 首页推荐sideBar区域

function get_home_summary(SideBarArea) {
    $.ajax({
        url: global_url_map.context_base+global_url_map.summary_url,
        type: "GET",
        success: function (result) {
            if(result.code==100){
                init_sidebar_summary(result.extend.summary,SideBarArea);
            }else{
                toastr.error("用户信息获取失败!" + result.msg);
            }
            // 这里result如果能一个字符串连用控制台显示，那控制台就显示Object，所以单独。
            // myInfo = result.extend.user;

        },
        error: function () {
            toastr.error("用户信息获取失败!");
        }
    });
}

function init_sidebar_summary(summary,area) {
    let localInfoArea = $(area.find('.widget')[0]);
    // 本站信息初始化
    // $(localInfoArea.find('p')[0]).empty().append(summary.blogNum);
    // $(localInfoArea.find('p')[1]).empty().append(summary.likeNum);
    // $(localInfoArea.find('p')[2]).empty().append(summary.commentNum);
    // $(localInfoArea.find('p')[3]).empty().append(summary.viewNum);
    localInfoArea.find('.guestIp').empty().append('当前Ip:'+summary.guestIp);
    $(localInfoArea.find('a')[0]).attr('href',summary.githubLink);
    $(localInfoArea.find('a')[1]).attr('href',summary.weiboLink);
    $(localInfoArea.find('a')[2]).attr('href',summary.wechatLink);
    $(localInfoArea.find('a')[3]).attr('href',summary.otherLink);
    localInfoArea.find('blockquote').find('small').empty().append(summary.notice+"aaa");

    let commendBlogArea = $(area.find('.widget')[1]);
    init_blog_link(commendBlogArea,summary.blogList,'blog',10);
    commendBlogArea.find('a[name="commendBlogSidebarTab"]').on('shown.bs.tab', function (e) {
        // 获取已激活的标签页的名称
        let activeTab = $(e.target).text();
        // 获取前一个激活的标签页的名称
        let previousTab = $(e.relatedTarget).text();

        if(activeTab=="Blog"){
            init_blog_link(commendBlogArea,summary.blogList,'blog',10);
        }else{
            init_blog_link(commendBlogArea,summary.blogSeries,'series',10);
        }
    });

    let commendUserArea = $(area.find('.widget')[2]);
    init_blog_link(commendUserArea,summary.topUserList,'user',10);
    commendUserArea.find('a[name="commendUserSidebarTab"]').on('shown.bs.tab', function (e) {
        // 获取已激活的标签页的名称
        let activeTab = $(e.target).text();
        // 获取前一个激活的标签页的名称
        let previousTab = $(e.relatedTarget).text();

        if(activeTab=="最近"){
            init_blog_link(commendUserArea,summary.latestLoginUserList,'user',10);
        }else{
            init_blog_link(commendUserArea,summary.topProductUserList,'user',10);
        }
    });

}

// 点赞图标点击事件<i class="far fa-thumbs-up"></i>
function like_click(blogId,nowCount) {
    let elem = $('.likeClickEvent');
    elem.empty().append($('<i></i>').addClass("fas fa-thumbs-up")).append(nowCount+1);
    elem.removeAttr("onclick");
    $.ajax({
        url: global_url_map.context_base+global_url_map.like_click_url+"/"+blogId,
        type: "GET",
        success: function (result) {
            if (result.code == 100) {
                console.log("点赞成功");
            } else {
                console.log("点赞失败");
            }

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            // 通常 textStatus 和 errorThrown 之中
            // 只有一个会包含信息
            //this; // 调用本次AJAX请求时传递的options参数
            console.log("点赞错误");
        },
    });
}

function runningTime(time,elem) {
    let day = Math.floor(time/(24*3600));
    let leve1 = time%(24*3600)
    let hour = Math.floor(leve1/(3600));
    let level2 = leve1%(3600)
    let minute = Math.floor(level2/(60));
    let level3 = level2%(60);
    let second =  level3;

    let Timer = "本站已累计运行"+day+"天"+hour+"小时"+minute+"分"+second+"秒";
    //在页面上插入日期
    elem.html(Timer);
    setTimeout(function () {
        runningTime(time+1,elem);
    }, 1000);
}

function currentTime(elem) {
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    var hour = date.getHours();
    var minute = date.getMinutes();
    var second = date.getSeconds();
    month = month < 10 ? ("0" + month) : month;
    day = day < 10 ? ("0" + day) : day;
    hour = hour < 10 ? ("0" + hour) : hour;
    minute = minute < 10 ? ("0" + minute) : minute;
    second = second < 10 ? ("0" + second) : second;
    var Timer = year + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second;
    //在页面上插入日期

    elem.html(Timer);
    setTimeout(function () {
        currentTime();
    }, 1000);
}


function transform_code(type,code) {
    if(type=='blogStatus'){
        if(code==1){
            return 'pre-create';
        }else if(code==2){
            return 'preview';
        }else if(code==3){
            return 'draft';
        }else if(code==4){
            return 'private';
        }else if(code==5){
            return 'friendly';
        }else if(code==6){
            return 'normal';
        }else if(code==7){
            return 'highlight';
        }else{
            return code;
        }
    }else if(type=='userRights'){
        if(code==1){
            return 'SuperAdmin';
        }else if(code==2){
            return 'Admin';
        }else if(code==3){
            return 'level-4';
        }else if(code==4){
            return 'level-3';
        }else if(code==5){
            return 'level-2';
        }else if(code==6){
            return 'level-1';
        }else{
            return code;
        }
    }else if(type=='accountStatus'){
        if(code==1){
            return 'normal';
        }else if(code==2){
            return 'friendly';
        }else if(code==3){
            return 'invisible';
        }else if(code==4){
            return 'incomplete';
        }else if(code==5){
            return 'forbidden';
        }else if(code==6){
            return 'deleted';
        }else{
            return code;
        }
    }else if(type=='userSex'){
        if(code=='m'){
            return '男';
        }else if(code=='f'){
            return '女';
        }else{
            return code;
        }
    }
}


