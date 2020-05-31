let  listSearchMap={
    userId:null,
    userName:null,
    blogTitle:null,
    blogId:null,
    blogStatus:null,
    updateTimeStart:null,
    updateTimeEnd:null,
    blogType:null,
    seriesId:null,
    listSort:null,
    sourceFrom:null,
    pn:null
}

$(function () {
    // if($('.monthMinTimePicker').length>0) {
    //     $('.monthMinTimePicker').datetimepicker({
    //         language: 'zh-CN',
    //         format: "yyyy年mm月dd日 ",
    //         weekStart: 1,
    //         todayBtn: "linked",
    //         autoclose: true,
    //         todayHighlight: true,
    //         startView: 3,
    //         forceParse: true,
    //         minView: "month",
    //         startDate: new Date(1970, 0, 0),
    //         endDate: new Date(),
    //     });
    // }
    search_form_init($('.searchForm'));


});

// function home_page_init(sourceFrom) {
//     listSearchMap.sourceFrom = sourceFrom;
// }

function search_form_init(searchForm){
    if($('.hourMinTimePicker').length>0) {
        $('.hourMinTimePicker').datetimepicker({
            language: 'zh-CN',
            format: "yyyy年mm月dd日 HH:mm ",
            weekStart: 1,
            todayBtn: "linked",
            autoclose: true,
            todayHighlight: true,
            startView: 3,
            forceParse: true,
            minView: "hour",
            startDate: new Date(2010, 0, 0),
            endDate: new Date(),
        });
    }
    if($('.timeRangeStart').length>0&&$('.timeRangeEnd').length>0){
        // 时间范围组件设置
        $('.timeRangeStart').datetimepicker().on('changeDate', function(ev){
            $('.timeRangeEnd').datetimepicker('setStartDate',ev.date);
            // if (ev.date.valueOf() < date-start-display.valueOf()){
            // ....
            // }
        });
        $('.timeRangeEnd').datetimepicker().on('changeDate', function(ev){
            $('.timeRangeStart').datetimepicker('setEndDate',ev.date);
            // if (ev.date.valueOf() < date-start-display.valueOf()){
            // ....
            // }
        });
    }
    if(searchForm) {
        searchForm.find('input').focus(function () {
            searchForm.find(".searchWarning").empty().hide();
        });
        searchForm.find('textarea').focus(function () {
            searchForm.find(".searchWarning").empty().hide();
        });
        searchForm.find('select').focus(function () {
            searchForm.find(".searchWarning").empty().hide();
        });
    }
}



let blog_info_modal;
let global_listArea,global_isNeedSetting;
function init_blog_list(listArea,sourceFrom,modal,isNeedSetting){
    listSearchMap.sourceFrom = sourceFrom;
    to_page(1,listArea,isNeedSetting);
    init_blog_info_modal(modal);
    global_listArea = listArea;
    global_isNeedSetting = isNeedSetting;
    if(blog_info_modal) {
        blog_info_modal.not('[readonly]').find('input', 'select').focus(function () {
            blog_info_modal.find('.itemInfoModalWarning').empty();
        });
    }
}

function init_blog_info_modal(modal){
    if(modal) {
        blog_info_modal = modal;
        blog_info_modal.find('input').focus(function () {
            blog_info_modal.find(".itemInfoModalWarning").empty().hide();
        });
        blog_info_modal.find('textarea').focus(function () {
            blog_info_modal.find(".itemInfoModalWarning").empty().hide();
        });
        blog_info_modal.find('select').focus(function () {
            blog_info_modal.find(".itemInfoModalWarning").empty().hide();
        });
    }
}

let globalCurrentPage;
// globalPageSize:设定的每页数量
// globalPages:总页数
// let globalPageSize, globalPages, globalCurrentSize;
// 是否为最后一页
// let globalIsLastPage;
let global_item_list;

function to_page(pn,listArea,isNeedSetting) {
    // 搜索条
    listSearchMap.pn=pn;
    let map =  JSON.stringify(listSearchMap);
    $.ajax({
        url: global_url_map.context_base+global_url_map.blog_page_url,
        type: "POST",
        dataType: "json",
        contentType: "application/json",
        data: map,
        success: function (result) {
            if(result.code==100) {
                // 这里result如果能一个字符串连用控制台显示，那控制台就显示Object，所以单独。
                let pageInfo = result.extend.pageinfo;
                global_item_list = pageInfo.list;
                // 1.获取到新结果首先对全局变量重新赋值
                assign_global_var(pageInfo, listArea, isNeedSetting);
                // 2.解析并显示数据
                build_page_list(pageInfo, isNeedSetting);
                // 3.解析数据并显示分页条
                build_page_nav(pageInfo, listArea, isNeedSetting);
            }else{
                toastr.error(result.msg);
            }
        },
        error:function () {
            toastr.error("获取Page失败");
            global_item_list = null;
        }
    });
}


//每当ajax获取到最新信息就需要对全局变量进行重新赋值
function assign_global_var(pageInfo, listArea,isNeedSetting) {
    // 对页面全局变量赋值
    globalCurrentPage = pageInfo.pageNum;     // 当前页码
    // globalPageSize = pageInfo.pageSize; // 设定的每页数量
    // globalPages = pageInfo.pages;       // 总页数
    // globalIsLastPage = pageInfo.isLastPage; // 是否为最后一页
    // 新页面大小跟旧页面大小不一致时就要刷新页面briefArea区域个数。
    if ($("div[id*='itemArea']").length != pageInfo.size && pageInfo.size >= 0) {
        flush_preview_area($("div[id*='itemArea']").length, pageInfo.size, listArea,isNeedSetting);
        // globalCurrentSize = $("div[id*='itemArea']").length;  // 当前页显示的数量
    }
}

// 初始化页面，每页多少个数据，就初始化多少个区域
function flush_preview_area(oldSize, newSize, listArea,isNeedSetting) {

    // 以下最终都是保证页面大小=newSize
    // 如果新页面大小大于旧的，则添加多余的部分即可
    if (newSize > oldSize) {
        for (let i = oldSize; i < newSize; i++) {
            let itemArea = $("<div></div>").addClass("col-md-12 col-lg-12 wow fadeInLeft hShadowAndRound hPostItem").attr("id", "itemArea" + i)
                .attr("data-wow-duration","1s").attr("data-wow-offset","10");
            let postArea = $("<div></div>").addClass("post");
            let postHeader = $("<div></div>").addClass("post-header font-alt")
                .append($("<h2></h2>").addClass("post-title").append($("<a></a>").attr("id", "itemTitle" + i)));

            let itemMeta1 = $("<div></div>").addClass("post-meta").attr("style", "white-space:pre")
                .append($("<i></i>").addClass("fad fa-user-alt")).append("  ").append($("<small></small>").attr("style", "cursor: pointer").attr("id", "itemAuthor" + i))
                .append($("<div></div>").addClass("pull-right")
                .append($("<i></i>").addClass("fad fa-calendar-alt")).append("  ").append($("<small></small>").attr("id", "itemTime" + i)));
            let itemMeta2 = $("<div></div>").addClass("post-meta").attr("style", "white-space:pre")
                .append($("<i></i>").addClass("fas fa-tag")).append("  ").append($("<small></small>").attr("id", "itemType" + i)).append("  |  ")
                .append($("<i></i>").addClass("fab fa-slack-hash")).append("  ").append($("<small></small>").attr("id", "itemSeries" + i))
                .append($("<div></div>").addClass("pull-right")
                .append($("<i></i>").addClass("fal fa-eye")).append("  ").append($("<small></small>").attr("id", "itemViews" + i)).append("  |  ")
                // .append($("<i></i>").addClass("fal fa-comment-lines")).append("  ").append($("<small></small>").attr("id", "itemCommentNum" + i)).append("  |  ")
                .append($("<i></i>").addClass("fas fa-thumbs-up")).append("  ").append($("<small></small>").attr("id", "itemLikeNum" + i)));
            postHeader.append(itemMeta1).append(itemMeta2);

            let postEntry = $("<div></div>").addClass("post-entry").append($("<p></p>").attr("id", "itemIntro" + i));

            let postMore = $("<div></div>").addClass("post-more dropup").append($("<a></a>").addClass("more-link").attr("id", "itemMoreLink" + i).append("Read more"));
            if (isNeedSetting) {
                let dropdownSpan = $("<span></span>").addClass("icon-gears dropdown-toggle hPostIcon pull-right").attr("data-toggle", "dropdown").attr("aria-hidden", "true");
                let dropdownMenu = $("<ul></ul>").addClass("dropdown-menu pull-right").attr("role", "menu")
                    .append($("<li></li>").append($("<a></a>").attr("id", "item_setting" + i).append("修改")))
                    .append($("<li></li>").append($("<a></a>").attr("id", "item_edit" + i).append("编辑")))
                    .append($("<li></li>").append($("<a></a>").attr("id", "item_delete" + i).append("删除")));
                postMore.append(dropdownSpan).append(dropdownMenu);
            }

            postArea.append(postHeader).append(postEntry).append(postMore);
            itemArea.append(postArea);
            // 再把previewItemArea集成到previewListArea
            listArea.append(itemArea);
        }
    } else {
        // 如果新的模块数量小于旧的，删除多余的即可。
        for (let i = newSize; i < oldSize; i++) {
            if ($("#itemArea" + i).length >= 0) {
                $("#itemArea" + i).remove();
            }
        }
    }

}

function erasure_preview_area(listArea) {
    let lenght = $("div[id*='itemArea']").length;
    for(let i=0;i<lenght;i++){
        $("#itemArea" + i).fadeOut("fast",function () {
            $(this).remove();
        });
    }
    listArea.empty();
}

function add_blog_item(index, item,isNeedSetting) {
    // 给第index briefArea区域插入文章
    // document.getElementById("#blogSpinner"+index).style.display="none";
    $("#itemTitle" + index).empty().append(item.blogTitle);

    $("#itemType" + index).empty().append(checkIsNullOrEmpty(item.blogTypeEntity)?item.blogTypeEntity.typeName:'');
    $("#itemSeries" + index).empty().append(checkIsNullOrEmpty(item.blogSeriesEntity)?item.blogSeriesEntity.seriesName:'');
    $("#itemIntro" + index).empty().append(item.blogIntro);
    $("#itemAuthor" + index).removeAttr("onclick").attr("onclick", "window.open('" + global_url_map.context_base+global_url_map.profile_url+"/" + item.userId + "')")
        .empty().append(item.user.userName);
    $("#itemTime" + index).empty().append(item.blogUpdateTime);
    $("#itemViews" + index).empty().append(item.blogViews);
    $("#itemLikeNum" + index).empty().append(item.blogLikeCount);
    // $("#itemCommentNum" + index).empty().append(item.blogCommentCount);

    if(listSearchMap.sourceFrom == "home") {
        $("#itemTitle" + index).removeAttr("onclick").attr("onclick", "window.open('" + global_url_map.context_base+global_url_map.blog_url+"/" + item.blogId  + "','_blank')");
        $("#itemMoreLink" + index).removeAttr("onclick").attr("onclick", "window.open('" +  global_url_map.context_base+global_url_map.blog_url+"/"  + item.blogId +  "','_blank')");
    }else{
        $("#itemTitle" + index).removeAttr("onclick").attr("onclick", "window.open('" + global_url_map.context_base+global_url_map.blog_url +"/" + item.blogId + "?sf=" + item.sourceFrom + "','_blank')");
        $("#itemMoreLink" + index).removeAttr("onclick").attr("onclick", "window.open('" + global_url_map.context_base+global_url_map.blog_url+"/"  + item.blogId + "?sf=" + item.sourceFrom + "','_blank')");
    }


    if (isNeedSetting) {
        $("#item_setting" + index).removeAttr("onclick").attr("onclick", "listItemSetting(" + index+")");
        $("#item_delete" + index).removeAttr("onclick").attr("onclick", "listItemDelete('" + global_url_map.context_base+global_url_map.blog_url+"/"  + item.blogId + "?sf="+item.sourceFrom+"')");
        $("#item_edit" + index).removeAttr("onclick").attr("onclick", "window.open('" + global_url_map.context_base+global_url_map.blog_edit_url +"/" + item.blogId + "?sf="+item.sourceFrom+"','_blank')");
    }
}

// 解析并显示博文数据
function build_page_list(pageInfo,isNeedSetting) {
    let list = pageInfo.list;
    $.each(list, function (index, item) {
        if (item.blogId != undefined) {
            add_blog_item(index, item,isNeedSetting);
        }
    });
}

// 解析并显示分页条的function
function build_page_nav(pageInfo,  listArea,isNeedSetting) {

    // 首先清空数据
    let paginationArea = listArea.next(".pagination");
    // var pageInfo = result.extend.briefpageinfo;
    paginationArea.empty();
    let prevPage = $("<a></a>").append($("<i></i>").addClass("fa fa-angle-left"));
    let nextPage = $("<a></a>").append($("<i></i>").addClass("fa fa-angle-right"));

    if (pageInfo.hasPreviousPage == false) {
        prevPage.addClass("disabled");
        prevPage.attr("style","cursor:default");
    } else {
        // 在首页和上一页按钮禁用后，也不添加这两个按键的点击事件，以防到负数页，下一页和末页同理
        // 也可以在mybatis-config.xml配置pageHelper插件的属性reasonable=true，合理化。但是不能防止浏览器一直请求。
        // 为元素添加点击翻页事件
        prevPage.attr("style","cursor:pointer");
        prevPage.click(function () {
            // before_page_btn();
            to_page(pageInfo.pageNum - 1, listArea,isNeedSetting);
        });
    }

    if (pageInfo.hasNextPage == false) {
        nextPage.addClass("disabled");
        nextPage.attr("style","cursor:default");
    } else {
        // 为元素添加点击翻页事件
        nextPage.attr("style","cursor:pointer");
        nextPage.click(function () {
            // before_page_btn();
            to_page(pageInfo.pageNum + 1, listArea,isNeedSetting);
        });
    }

    paginationArea.append(prevPage);

    // 页码号
    $.each(pageInfo.navigatepageNums, function (index, item) {
        let numPage = $("<a></a>").append(item);
        if (pageInfo.pageNum == item) {
            numPage.addClass("active").attr("style", "cursor:default");
            // numLi.disable();
        } else {
            numPage.attr("style","cursor:pointer");
            numPage.click(function () {
                to_page(item, listArea,isNeedSetting);
            });
        }
        paginationArea.append(numPage);
    });
    paginationArea.append(nextPage);

}


function blog_info_modal_reset(item,modal) {
    let form = modal.find("form");
    form[0].reset();
    form.find("select[name*=blogType]").removeAttr("disabled");
    form.find("select[name*=seriesId]").removeAttr("disabled");
    form.find("input[name*=newSeries]").val("").attr("disabled","disabled");
    form.find("input[name*=newType]").val("").attr("disabled","disabled");

    form.find("input[name*=userId]").val(item.userId);
    form.find("input[name*=userName]").val(item.user.userName);
    form.find("input[name*=blogId]").val(item.blogId);
    form.find("input[name*=blogTitle]").val(item.blogTitle);
    form.find("textarea[name*=blogIntro]").val(item.blogIntro);
    form.find("select[name*=blogType]").val(item.blogType);
    form.find("select[name*=seriesId]").val(item.seriesId);
    form.find("select[name*=blogStatus]").val(item.blogStatus);
    form.find("input[name*=sourceFrom]").val(item.sourceFrom);

    if (item.sourceFrom == "blog") {
        form.find("input[name*=originalBlogId]").val("");
        form.find("div[name*=originBlogIdArea]").hide();
    } else if (item.sourceFrom == "draft") {
        form.find("input[name*=originalBlogId]").val(item.originalBlogId);
        form.find("div[name*=originBlogIdArea]").show();
    }

}

<!-- 编辑 -->
function listItemSetting(index) {
    let item = global_item_list[index];
    blog_info_modal_reset(item,blog_info_modal);
    blog_info_modal.modal('show');
}

function blog_info_modal_save(form) {
    let title = form.find("input[name*=blogTitle]").val();
    let intro = form.find("textarea[name*=blogIntro]").val();
    let blogStatus = form.find("select[name*=blogStatus]").val();
    let blogId = form.find("input[name*=blogId]").val();
    let sourceFrom = form.find("input[name*=sourceFrom]").val();
    let newSeries = form.find("input[name*=newSeries]").val();
    let newType = form.find("input[name*=newType]").val();
    let blogMap = {
        userId: form.find("input[name*=userId]").val(),
        blogId: blogId,
        blogTitle: title,
        blogIntro: intro,
        seriesId:form.find("select[name*=seriesId]").val(),
        blogType: form.find("select[name*=blogType]").val(),
        blogStatus: blogStatus,
        originalBlogId: form.find("input[name*=originalBlogId]").val(),
        sourceFrom:sourceFrom,
        newType: newType,
        newSeries:newSeries,
    };
    save_blog_info(blogMap,blog_info_modal);

}

function save_blog_info(blogMap,modal) {
    let title = blogMap.blogTitle;
    let intro = blogMap.blogIntro;
    let newSeries = blogMap.newSeries;
    let newType = blogMap.newType;
    if(!title||title.trim()==""){
        if(modal.hasClass('in')) {
            modal.find(".itemInfoModalWarning").empty().append("Title不能为空").show();
        }else{
            toastr.warning("Title不能为空!");
        }
        return "Title不能为空";
    }
    if(!title||!validate_data(title, "title")){
        if(modal.hasClass('in')) {
            modal.find(".itemInfoModalWarning").empty().append("Title不符合规范").show();
        }else{
            toastr.warning("Title不符合规范!");
        }
        return "Title不符合规范";
    }
    if(!intro||!validate_data(intro, "intro")){
        if(modal.hasClass('in')) {
            modal.find(".itemInfoModalWarning").empty().append("简介不符合规范").show();
        }else{
            toastr.warning("简介不符合规范");
        }
        return "简介不符合规范";
    }
    if(!checkIsNullOrEmpty(newSeries)){
        newSeries=null;
    }else if(!validate_data(newSeries, "title")){
        if(modal.hasClass('in')) {
            modal.find(".itemInfoModalWarning").empty().append("新Series name不符合规范").show();
        }else{
            toastr.warning("新Series name不符合规范");
        }
        return "新Series name不符合规范";
    }

    if(!checkIsNullOrEmpty(newType)){
        newType=null;
    }else if(!validate_data(newType, "title")){
        if(modal.hasClass('in')) {
            modal.find(".itemInfoModalWarning").empty().append("新Type name不符合规范").show();
        }else{
            toastr.warning("新Type name不符合规范");
        }
        return "新Type name不符合规范";
    }

    if(modal) {
        modal.modal('hide');
    }
    let oldUpdateTime = blogMap.blogUpdateTime;
    blogMap.blogUpdateTime = null;
    $.ajax({
        url: global_url_map.context_base+global_url_map.blog_url,
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(blogMap),
        success: function (result) {
            if (result.code == 100) {
                // modal.find("input[name*=blogId]").val(result.extend.blogId);
                // modal.find("input[name*=sourceFrom]").val(result.extend.sourceFrom);
                // modal.find("input[name*=originalBlogId]").val(result.extend.originId);
                if(blogMap.blogStatus!=2) {
                    if(typeof global_blogMap!="undefined"&&checkIsNullOrEmpty(global_blogMap)) {
                        global_blogMap.blogId = result.extend.blogId;
                        global_blogMap.sourceFrom = result.extend.sourceFrom;
                        global_blogMap.originalBlogId = result.extend.originId;
                        global_blogMap.blogUpdateTime = result.extend.updateTime;
                    }
                    if (typeof global_blogMap!="undefined"&&checkIsNullOrEmpty(newSeries)) {
                        init_series_list(blogMap.userId, $('.seriesIdSelector'), blogMap.sourceFrom);
                        if(checkIsNullOrEmpty(global_blogMap)) {
                            global_blogMap.newSeries = null;
                            global_blogMap.newSeriesCheck = false;
                        }

                    }
                    if (typeof global_blogMap!="undefined"&&checkIsNullOrEmpty(newType)) {
                        init_type_list(blogMap.userId, $('.blogTypeSelector'), blogMap.sourceFrom);
                        if(checkIsNullOrEmpty(global_blogMap)) {
                            global_blogMap.newType = null;
                            global_blogMap.newTypeCheck = false;
                        }
                    }
                    if(typeof global_blogMap!="undefined"&&checkIsNullOrEmpty(global_blogMap)) {
                        global_blogMap.seriesId = result.extend.seriesId;
                        global_blogMap.blogType = result.extend.typeId;
                    }
                }
                toastr.success(result.msg);
                if(global_listArea){
                    to_page(globalCurrentPage,global_listArea,global_isNeedSetting);
                }else if(blogMap.blogStatus==2){
                    window.open(global_url_map.blog_preview_url+"/"+result.extend.blogId);
                }

            } else {
                if(typeof global_blogMap!="undefined"&&checkIsNullOrEmpty(global_blogMap)) {
                    global_blogMap.blogUpdateTime = oldUpdateTime;
                }
                toastr.error("保存失败!"+result.msg);
            }

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            // 通常 textStatus 和 errorThrown 之中
            // 只有一个会包含信息
            //this; // 调用本次AJAX请求时传递的options参数
            if(typeof global_blogMap!="undefined"&&checkIsNullOrEmpty(global_blogMap)) {
                global_blogMap.blogUpdateTime = oldUpdateTime;
            }
            toastr.error("保存失败!"+textStatus);
        },
    });

}



function listItemDelete(delete_url) {

    $.ajax({
        url: delete_url,
        type: "DELETE",
        success: function (result) {
            if (result.code == 100) {
                toastr.success("删除成功!");
                to_page(globalCurrentPage,global_listArea,global_isNeedSetting);
            } else {
                toastr.error("删除失败!"+result.msg);
            }

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            // 通常 textStatus 和 errorThrown 之中
            // 只有一个会包含信息
            //this; // 调用本次AJAX请求时传递的options参数
            toastr.error("删除失败!");
        },
    });
}

$('a[name="blogOrDraftTab"]').on('shown.bs.tab', function (e) {
    // 获取已激活的标签页的名称
    let activeTab = $(e.target).text().trim();
    // 获取前一个激活的标签页的名称
    let previousTab = $(e.relatedTarget).text().trim();

    if(activeTab=="Draft"){
        listSearchMap.sourceFrom = "draft";
    }else{
        listSearchMap.sourceFrom = "blog";
    }
    to_page(1,global_listArea,global_isNeedSetting);
});


function reset_search(form) {
    listSearchMap.userName = null;
    listSearchMap.blogTitle = null;
    listSearchMap.blogId = null;
    listSearchMap.blogStatus = null;
    listSearchMap.updateTimeStart = null;
    listSearchMap.updateTimeEnd = null;
    listSearchMap.blogType = null;
    listSearchMap.seriesId = null;
    listSearchMap.userId = null;
    form.find('input[name*=userName]').val(listSearchMap.userName);
    form.find('input[name*=blogTitle]').val( listSearchMap.blogTitle);
    form.find('input[name*=blogId]').val(listSearchMap.blogId);
    form.find('select[name*=blogStatus]').val(listSearchMap.blogStatus);
    form.find('input[name*=updateTimeStart]').val(listSearchMap.updateTimeStart);
    form.find('input[name*=updateTimeEnd]').val(listSearchMap.updateTimeEnd);
    form.find('select[name*=blogType]').val(listSearchMap.blogType);
    form.find('select[name*=seriesId]').val(listSearchMap.seriesId);
    form.find('.timeRangeStart').datetimepicker('setStartDate',new Date(2010, 0, 0));
    form.find('.timeRangeEnd').datetimepicker('setStartDate',new Date(2010, 0, 0));
    form.find('.timeRangeStart').datetimepicker('setEndDate',new Date());
    form.find('.timeRangeEnd').datetimepicker('setEndDate',new Date());
    // let searchAnSortInfo = listSearchMap.sourceFrom;
    // if(listSearchMap.listSort!=null){
    //     searchAnSortInfo+='>>'+listSearchMap.listSort;
    // }
    $('.searchAndSortInfo').empty();
    form.find('.searchWarning').empty().hide();
    to_page(1,global_listArea,global_isNeedSetting);
}


$('.blogListSort').next('ul.sortMenu').on("click","li",function(){      //只需要找到你点击的是哪个ul里面的就行
    $('.blogListSort').empty().append($('<i></i>').addClass('fas fa-sort fa-lg')).append('  '+$(this).text());
    let listSort =$(this).attr('value');
    if(checkIsNullOrEmpty(listSort)){
        listSearchMap.listSort = listSort;
    }else{
        listSearchMap.listSort = null;
    }
    init_search_info(listSearchMap);
});

function start_search(form) {
    let userName = form.find('input[name*=userName]').val();
    let blogTitle = form.find('input[name*=blogTitle]').val();
    let blogId = form.find('input[name*=blogId]').val();
    let blogStatus = form.find('select[name*=blogStatus]').val();
    let updateTimeStart = form.find('input[name*=updateTimeStart]').val();
    let updateTimeEnd = form.find('input[name*=updateTimeEnd]').val();
    let blogType = form.find('select[name*=blogType]').val();
    let seriesId = form.find('select[name*=seriesId]').val();

    if(checkIsNullOrEmpty(userName)){
        if(userName.length>10){
            form.find('.searchWarning').empty().append('user name不符合规范!').show();
            return;
        }else {
            listSearchMap.userName = userName;
        }
    }else{
        listSearchMap.userName = null;
    }
    if(checkIsNullOrEmpty(blogTitle)){
        if(blogTitle.length>15){
            form.find('.searchWarning').empty().append('title不符合规范!').show();
            return;
        }else {
            listSearchMap.blogTitle = blogTitle;
        }
    }else{
        listSearchMap.blogTitle = null;
    }
    if(checkIsNullOrEmpty(blogId)){
        if(!validate_data(blogId,'numerical')){
            form.find('.searchWarning').empty().append('blogId不符合规范!').show();
            return;
        }else {
            listSearchMap.blogId = blogId;
        }
    }else{
        listSearchMap.blogId = null;
    }
    if(checkIsNullOrEmpty(blogStatus)){
        if(!validate_data(blogStatus,'numerical')){
            form.find('.searchWarning').empty().append('blogStatus不符合规范!').show();
            return;
        }else {
            listSearchMap.blogStatus = blogStatus;
        }
    }else{
        listSearchMap.blogStatus = null;
    }
    if(checkIsNullOrEmpty(updateTimeStart)) {
        listSearchMap.updateTimeStart = updateTimeStart;
    }else{
        listSearchMap.updateTimeStart = null;
    }
    if(checkIsNullOrEmpty(updateTimeEnd)) {
        listSearchMap.updateTimeEnd = updateTimeEnd;
    }else{
        listSearchMap.updateTimeEnd = null;
    }
    if(checkIsNullOrEmpty(blogType)){
        if(!validate_data(blogType,'numerical')){
            form.find('.searchWarning').empty().append('blogType不符合规范!').show();
            return;
        }else {
            listSearchMap.blogType = blogType;
        }
    }else{
        listSearchMap.blogType = null;
    }
    if(checkIsNullOrEmpty(seriesId)){
        if(!validate_data(seriesId,'numerical')){
            form.find('.searchWarning').empty().append('seriesId不符合规范!').show();
            return;
        }else {
            listSearchMap.seriesId = seriesId;
        }
    }else{
        listSearchMap.seriesId = null;
    }
    init_search_info(listSearchMap);
}

function init_search_info(map) {
    let searchAnSortInfo = listSearchMap.sourceFrom;
    if(checkIsNullOrEmpty(map.userName)){
        searchAnSortInfo+='>>userName';
    }else{
        map.userName = null;
    }
    if(checkIsNullOrEmpty(map.blogTitle)){
        searchAnSortInfo+='>>title';
    }else{
        map.blogTitle= null;
    }
    if(checkIsNullOrEmpty(map.blogId)){
        searchAnSortInfo+='>>blogId';
    }else{
        map.blogId = null;
    }
    if(checkIsNullOrEmpty(map.blogStatus)){
        searchAnSortInfo+='>>status';
    }else{
        map.blogStatus = null;
    }
    if((checkIsNullOrEmpty(map.updateTimeStart))||(checkIsNullOrEmpty(map.updateTimeEnd))){
        searchAnSortInfo+='>>time';
        if(!checkIsNullOrEmpty(map.updateTimeStart)) {
            map.updateTimeStart = null;
        }
        if(!checkIsNullOrEmpty(map.updateTimeEnd)) {
            map.updateTimeEnd = null;
        }
    }
    if(checkIsNullOrEmpty(map.blogType)){
        searchAnSortInfo+='>>type';
    }else{
        map.blogType=null;
    }
    if(checkIsNullOrEmpty(map.seriesId)){
        searchAnSortInfo+='>>series';
    }else{
        map.seriesId=null;
    }
    if(checkIsNullOrEmpty(map.listSort)){
        searchAnSortInfo+='>>'+map.listSort;
    }else{
        map.listSort = null;
    }
    $('.searchAndSortInfo').empty().append(searchAnSortInfo);
    listSearchMap = map;
    to_page(1,global_listArea,global_isNeedSetting);
}


