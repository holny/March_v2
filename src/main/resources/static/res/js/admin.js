let admin_url_map = {
    admin_blog_list_url : 'admin/blog/page',
    admin_user_list_url : 'admin/user/page',
    admin_global_search_url: null,       // 看情况从上面这些Url选择一个
    admin_series_list_url : 'admin/seriesList',
    admin_blog_edit_url : 'admin/blog',
    admin_user_edit_url : 'admin/user',
}
let admin_global_list_data_from;
let admin_global_search_map;
let admin_global_listArea;
let admin_global_modal;
let admin_global_current_page;

let  admin_search_map_blog={
    userId:null,
    userName:null,
    blogTitle:null,
    blogId:null,
    blogStatus:null,
    updateTimeStart:null,
    updateTimeEnd:null,
    blogType:null,
    seriesName:null,
    listSort:null,
    sourceFrom:'blog',
    pn:null
}
let  admin_search_map_user={
    userId:null,
    userName:null,
    userEmail:null,
    userTelephoneNumber:null,
    lastLoginTimeStart:null,
    lastLoginTimeEnd:null,
    userRights:null,
    accountStatus:null,
    listSort:null,
    sourceFrom:'user',
    pn:null
}
let  admin_search_map_series={
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
    sourceFrom:'series',
    pn:null
}

function init_admin_page(type,pn,listArea,searchForm,listSortArea,itemInfoModal,switchTab){
    init_admin_param(type,pn,listArea,searchForm,listSortArea,itemInfoModal)
    switchTab.on('shown.bs.tab', function (e) {
        // 获取已激活的标签页的名称
        let activeTab = $(e.target).text().trim();
        // 获取前一个激活的标签页的名称
        let previousTab = $(e.relatedTarget).text().trim();
        if(activeTab=="Draft"){
            init_admin_param("draft",1,listArea,searchForm,listSortArea,itemInfoModal);
        }else if(activeTab=="Blog"){
            init_admin_param("blog",1,listArea,searchForm,listSortArea,itemInfoModal);
        }else if(activeTab=="User"){
            init_admin_param("user",1,listArea,searchForm,listSortArea,itemInfoModal);
        }
    });
}

function init_admin_param(type,pn,listArea,searchForm,listSortArea,itemInfoModal) {
    admin_global_search_map = null;
    admin_url_map.admin_global_search_url = null;
    if(type=='blog'||type=='draft'){
        admin_global_search_map = admin_search_map_blog;
        admin_global_search_map.sourceFrom=type;
        admin_global_search_map.pn=pn;
        admin_url_map.admin_global_search_url = admin_url_map.admin_blog_list_url;
        admin_global_list_data_from = 'blog';
    }else if(type=='user'){
        admin_global_search_map = admin_search_map_user;
        admin_global_search_map.sourceFrom='user';
        admin_global_search_map.pn=pn;
        admin_url_map.admin_global_search_url = admin_url_map.admin_user_list_url;
        admin_global_list_data_from = 'user';
    }else if(type=='series'){
        admin_global_search_map = admin_search_map_series;
        admin_global_search_map.sourceFrom='series';
        admin_global_search_map.pn=pn;
        admin_url_map.admin_global_search_url = admin_url_map.admin_series_list_url;
        admin_global_list_data_from = 'user';
    }else{
            console.log("init_admin_page---type不符合--type="+type);
            return;
    }
    admin_global_listArea = listArea;
    admin_global_modal = itemInfoModal;
    init_list_search_form(type,searchForm);
    init_list_sort_area(type,listSortArea);
    admin_to_page(admin_global_search_map,type);
}

function admin_to_page(searchMap,type) {
    admin_global_search_map = searchMap;
    let map =  JSON.stringify(admin_global_search_map);
    console.log(global_url_map.context_base+admin_url_map.admin_global_search_url);
    $.ajax({
        url: global_url_map.context_base+admin_url_map.admin_global_search_url,
        type: "POST",
        dataType: "json",
        contentType: "application/json",
        data: map,
        success: function (result) {
            if(result.code==100) {
                let pageInfo = result.extend.pageinfo;
                admin_global_current_page = pageInfo.pageNum;
                admin_build_page_list(pageInfo,type,admin_global_listArea);
                // 这里result如果能一个字符串连用控制台显示，那控制台就显示Object，所以单独。
                // let pageInfo = result.extend.pageinfo;
                // global_item_list = pageInfo.list;
                // // 1.获取到新结果首先对全局变量重新赋值
                // assign_global_var(pageInfo, listArea, isNeedSetting);
                // // 2.解析并显示数据
                // build_page_list(pageInfo, isNeedSetting);
                // // 3.解析数据并显示分页条
                // build_page_nav(pageInfo, listArea, isNeedSetting);
            }else{
                toastr.error(result.msg);
            }
        },
        error:function () {
            toastr.error("获取page失败");
        }
    });

}


function init_list_search_form(type,searchForm) {
    if(type=='blog'||type=='draft'){
        searchForm.children().remove();
        let form_1 = $('<div></div>').addClass('form-row mb-sm-10 mt-sm-40');
        let userNameInputDiv = $('<div></div>').addClass('col-sm-3')
            .append($('<div></div>').addClass('form-group hInputGroup')
                .append($('<input>').addClass('form-control input-sm').attr('name','userName').attr('type','text').attr('placeholder','Author')));
        let titleInputDiv = $('<div></div>').addClass('col-sm-3')
            .append($('<div></div>').addClass('form-group hInputGroup')
                .append($('<input>').addClass('form-control input-sm').attr('name','blogTitle').attr('type','text').attr('placeholder','Title')));
        let IdInputDiv = $('<div></div>').addClass('col-sm-3')
            .append($('<div></div>').addClass('form-group hInputGroup')
                .append($('<input>').addClass('form-control input-sm').attr('name','blogId').attr('type','text').attr('placeholder','Blog Id')));
        let seriesNameInputDiv = $('<div></div>').addClass('col-sm-3')
            .append($('<div></div>').addClass('form-group hInputGroup')
                .append($('<input>').addClass('form-control input-sm').attr('name','seriesName').attr('type','text').attr('placeholder','Series name')));
        form_1.append(userNameInputDiv).append(titleInputDiv).append(IdInputDiv).append(seriesNameInputDiv);

        let form_2 = $('<div></div>').addClass('form-row');
        let startTimeInputDiv = $('<div></div>').addClass('col-sm-3')
            .append($('<div></div>').addClass('form-group hInputGroup')
                .append($('<div></div>').addClass('input-group date hourMinTimePicker timeRangeStart')
                    .append($('<input/>').addClass('form-control  input-sm').attr('type','text').attr('name','updateTimeStart').attr('placeholder','最近更新-起').attr('readonly','readonly'))
                    .append($('<span></span>').addClass('input-group-addon').append($('<span></span>').addClass('glyphicon glyphicon-calendar')))));
        let endTimeInputDiv = $('<div></div>').addClass('col-sm-3')
            .append($('<div></div>').addClass('form-group hInputGroup')
                .append($('<div></div>').addClass('input-group date hourMinTimePicker timeRangeEnd')
                    .append($('<input/>').addClass('form-control  input-sm').attr('type','text').attr('name','updateTimeEnd').attr('placeholder','最近更新-止').attr('readonly','readonly'))
                    .append($('<span></span>').addClass('input-group-addon').append($('<span></span>').addClass('glyphicon glyphicon-calendar')))));

        let typeSelectDiv = $('<div></div>').addClass('col-sm-3')
            .append($('<div></div>').addClass('form-group hInputGroup')
                .append($('<select></select>').addClass('form-control blogTypeSelector').attr('name','blogType')
                    .append($('<option></option>').attr('selected','selected').attr('disabled','disabled').append('Blog type...'))));
        let statusSelectDiv = $('<div></div>').addClass('col-sm-3')
            .append($('<div></div>').addClass('form-group hInputGroup')
                .append($('<select></select>').addClass('form-control').attr('name','blogStatus')
                    .append($('<option></option>').attr('selected','selected').attr('disabled','disabled').attr("value","").append('Blog status...'))
                    .append($('<option></option>').attr('value','7').append('highlight'))
                    .append($('<option></option>').attr('value','6').append('normal'))
                    .append($('<option></option>').attr('value','5').append('friendly'))
                    .append($('<option></option>').attr('value','4').append('private'))
                    .append($('<option></option>').attr('value','3').append('draft'))
                    .append($('<option></option>').attr('value','2').append('preview'))
                    .append($('<option></option>').attr('value','1').append('pre-create'))));
        form_2.append(startTimeInputDiv).append(endTimeInputDiv).append(typeSelectDiv).append(statusSelectDiv);

        let form_btn_area = $('<div></div>').addClass('form-group text-center')
            .append($('<button></button>').addClass('btn btn-d btn-round btn-xs searchBtn').attr('type','button').append($('<i></i>').addClass('fal fa-search')).append('  搜索'))
            .append($('<button></button>').addClass('btn btn-g btn-round btn-xs resetBtn').attr('type','button').append($('<i></i>').addClass('fal fa-sync')).append('  重置'))
            .append($('<p></p>').addClass('help-block text-danger hInputInfo searchWarning'));

        searchForm.append(form_1).append(form_2).append(form_btn_area);
        form_btn_area.on("click",".searchBtn",function () {
            start_search(type,searchForm);
        });
        form_btn_area.on("click",".resetBtn",function () {
            reset_search(type,searchForm);
        });

        init_type_list(1, $('.blogTypeSelector'),'admin');
    }else if(type=='user'){
        searchForm.children().remove();
        let form_1 = $('<div></div>').addClass('form-row mb-sm-10 mt-sm-40');
        let userNameInputDiv = $('<div></div>').addClass('col-sm-3')
            .append($('<div></div>').addClass('form-group hInputGroup')
                .append($('<input>').addClass('form-control input-sm').attr('name','userName').attr('type','text').attr('placeholder','User name')));
        let userIdInputDiv = $('<div></div>').addClass('col-sm-3')
            .append($('<div></div>').addClass('form-group hInputGroup')
                .append($('<input>').addClass('form-control input-sm').attr('name','userId').attr('type','text').attr('placeholder','User Id')));
        let emailInputDiv = $('<div></div>').addClass('col-sm-3')
            .append($('<div></div>').addClass('form-group hInputGroup')
                .append($('<input>').addClass('form-control input-sm').attr('name','userEmail').attr('type','text').attr('placeholder','Email')));

        let phoneNumInputDiv = $('<div></div>').addClass('col-sm-3')
            .append($('<div></div>').addClass('form-group hInputGroup')
                .append($('<input>').addClass('form-control input-sm').attr('name','userTelephoneNumber').attr('type','text').attr('placeholder','Phone number')));
        form_1.append(userNameInputDiv).append(userIdInputDiv).append(emailInputDiv).append(phoneNumInputDiv);

        let form_2 = $('<div></div>').addClass('form-row');
        let startTimeInputDiv = $('<div></div>').addClass('col-sm-3')
            .append($('<div></div>').addClass('form-group hInputGroup')
                .append($('<div></div>').addClass('input-group date hourMinTimePicker timeRangeStart')
                    .append($('<input/>').addClass('form-control  input-sm').attr('type','text').attr('name','lastLoginTimeStart').attr('placeholder','最近登录-起').attr('readonly','readonly'))
                    .append($('<span></span>').addClass('input-group-addon').append($('<span></span>').addClass('glyphicon glyphicon-calendar')))));
        let endTimeInputDiv = $('<div></div>').addClass('col-sm-3')
            .append($('<div></div>').addClass('form-group hInputGroup')
                .append($('<div></div>').addClass('input-group date hourMinTimePicker timeRangeEnd')
                    .append($('<input/>').addClass('form-control  input-sm').attr('type','text').attr('name','lastLoginTimeEnd').attr('placeholder','最近登录-止').attr('readonly','readonly'))
                    .append($('<span></span>').addClass('input-group-addon').append($('<span></span>').addClass('glyphicon glyphicon-calendar')))));
        let rightsSelectDiv = $('<div></div>').addClass('col-sm-3')
            .append($('<div></div>').addClass('form-group hInputGroup')
                .append($('<select></select>').addClass('form-control').attr('name','userRights')
                    .append($('<option></option>').attr('selected','selected').attr('disabled','disabled').attr("value","").append('Rights...'))
                    .append($('<option></option>').attr('value',6).append('level-1'))
                    .append($('<option></option>').attr('value',5).append('level-2'))
                    .append($('<option></option>').attr('value',4).append('level-3'))
                    .append($('<option></option>').attr('value',3).append('level-4'))
                    .append($('<option></option>').attr('value',2).append('Admin'))
                    .append($('<option></option>').attr('value',1).append('SuperAdmin'))));
        let statusSelectDiv = $('<div></div>').addClass('col-sm-3')
            .append($('<div></div>').addClass('form-group hInputGroup')
                .append($('<select></select>').addClass('form-control').attr('name','accountStatus')
                    .append($('<option></option>').attr('selected','selected').attr('disabled','disabled').attr("value","").append('Status...'))
                    .append($('<option></option>').attr('value',6).append('deleted'))
                    .append($('<option></option>').attr('value',5).append('forbidden'))
                    .append($('<option></option>').attr('value',4).append('incomplete'))
                    .append($('<option></option>').attr('value',3).append('invisible'))
                    .append($('<option></option>').attr('value',2).append('friendly'))
                    .append($('<option></option>').attr('value',1).append('normal'))));

        form_2.append(startTimeInputDiv).append(endTimeInputDiv).append(rightsSelectDiv).append(statusSelectDiv);

        let form_btn_area = $('<div></div>').addClass('form-group text-center')
            .append($('<button></button>').addClass('btn btn-d btn-round btn-xs searchBtn').attr('type','button').append($('<i></i>').addClass('fal fa-search')).append('  搜索'))
            .append($('<button></button>').addClass('btn btn-g btn-round btn-xs resetBtn').attr('type','button').append($('<i></i>').addClass('fal fa-sync')).append('  重置'))
            .append($('<p></p>').addClass('help-block text-danger hInputInfo searchWarning'));

        searchForm.append(form_1).append(form_2).append(form_btn_area);
        form_btn_area.on("click",".searchBtn",function () {
            start_search(type,searchForm);
        });
        form_btn_area.on("click",".resetBtn",function () {
            reset_search(type,searchForm);
        });
    }



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
        });
        $('.timeRangeEnd').datetimepicker().on('changeDate', function(ev){
            $('.timeRangeStart').datetimepicker('setEndDate',ev.date);
        });
    }

}

function reset_search(type,searchForm) {
    if(type=='blog'||type=='draft') {
        admin_global_search_map.userName = null;
        admin_global_search_map.blogTitle = null;
        admin_global_search_map.blogId = null;
        admin_global_search_map.blogStatus = null;
        admin_global_search_map.updateTimeStart = null;
        admin_global_search_map.updateTimeEnd = null;
        admin_global_search_map.blogType = null;
        admin_global_search_map.seriesName = null;
        admin_global_search_map.userId = null;
        admin_global_search_map.pn=null;
        searchForm.find('input[name*=userName]').val(admin_global_search_map.userName);
        searchForm.find('input[name*=blogTitle]').val(admin_global_search_map.blogTitle);
        searchForm.find('input[name*=blogId]').val(admin_global_search_map.blogId);
        searchForm.find('input[name*=seriesName]').val(admin_global_search_map.seriesName);
        searchForm.find('input[name*=updateTimeStart]').val(admin_global_search_map.updateTimeStart);
        searchForm.find('input[name*=updateTimeEnd]').val(admin_global_search_map.updateTimeEnd);
        searchForm.find('select[name*=blogType]').val(admin_global_search_map.blogType);
        searchForm.find('select[name*=blogStatus]').val(admin_global_search_map.blogStatus);

    }else if(type=='user'){
        admin_global_search_map.userId=null;
        admin_global_search_map.userName=null;
        admin_global_search_map.userEmail=null;
        admin_global_search_map.userTelephoneNumber=null;
        admin_global_search_map.lastLoginTimeStart=null;
        admin_global_search_map.lastLoginTimeEnd=null;
        admin_global_search_map.userRights=null;
        admin_global_search_map.accountStatus=null;
        admin_global_search_map.listSort=null;
        admin_global_search_map.sourceFrom='user';
        admin_global_search_map.pn=null;
        searchForm.find('input[name*=userName]').val(admin_global_search_map.userName);
        searchForm.find('input[name*=userId]').val(admin_global_search_map.userId);
        searchForm.find('input[name*=userEmail]').val(admin_global_search_map.userEmail);
        searchForm.find('input[name*=userTelephoneNumber]').val(admin_global_search_map.userTelephoneNumber);
        searchForm.find('input[name*=lastLoginTimeStart]').val(admin_global_search_map.lastLoginTimeStart);
        searchForm.find('input[name*=lastLoginTimeEnd]').val(admin_global_search_map.lastLoginTimeEnd);
        searchForm.find('select[name*=userRights]').val(admin_global_search_map.userRights);
        searchForm.find('select[name*=accountStatus]').val(admin_global_search_map.accountStatus);
    }
    searchForm.find('.timeRangeStart').datetimepicker('setStartDate', new Date(2010, 0, 0));
    searchForm.find('.timeRangeEnd').datetimepicker('setStartDate', new Date(2010, 0, 0));
    searchForm.find('.timeRangeStart').datetimepicker('setEndDate', new Date());
    searchForm.find('.timeRangeEnd').datetimepicker('setEndDate', new Date());
    // let searchAnSortInfo = listSearchMap.sourceFrom;
    // if(listSearchMap.listSort!=null){
    //     searchAnSortInfo+='>>'+listSearchMap.listSort;
    // }
    $('.searchAndSortInfo').empty();
    searchForm.find('.searchWarning').empty().hide();
    admin_global_search_map.pn=1;
    admin_to_page(admin_global_search_map,type);
}


function start_search(type,searchForm) {

    if(type =='blog'||type=='draft') {
        blog_searchmap_check(admin_global_search_map,type,searchForm);
    }else if(type=='user'){
        user_searchmap_check(admin_global_search_map,type,searchForm);
    }
}

function user_searchmap_check(searchMap,type,searchForm) {
    let userName = searchForm.find('input[name*=userName]').val();
    let userId = searchForm.find('input[name*=userId]').val();
    let userEmail = searchForm.find('input[name*=userEmail]').val();
    let userTelephoneNumber = searchForm.find('input[name*=userTelephoneNumber]').val();

    let lastLoginTimeStart = searchForm.find('input[name*=lastLoginTimeStart]').val();
    let lastLoginTimeEnd = searchForm.find('input[name*=lastLoginTimeEnd]').val();
    let userRights = searchForm.find('select[name*=userRights]').val();
    let accountStatus = searchForm.find('select[name*=accountStatus]').val();

    if(checkIsNullOrEmpty(userName)){
        if(userName.length>10){
            searchForm.find('.searchWarning').empty().append('user name不符合规范!').show();
            return;
        }else {
            searchMap.userName = userName;
        }
    }else{
        searchMap.userName = null;
    }

    if(checkIsNullOrEmpty(userId)){
        if(!validate_data(userId,'numerical')){
            searchForm.find('.searchWarning').empty().append('User Id不符合规范!').show();
            return;
        }else {
            searchMap.userId = userId;
        }
    }else{
        searchMap.userId = null;
    }
    if(checkIsNullOrEmpty(userEmail)){
        if(userEmail.length>10){
            searchForm.find('.searchWarning').empty().append('Email不符合规范!').show();
            return;
        }else {
            searchMap.userEmail = userEmail;
        }
    }else{
        searchMap.userEmail = null;
    }

    if(checkIsNullOrEmpty(userTelephoneNumber)){
        if(!validate_data(userTelephoneNumber,'numerical')){
            searchForm.find('.searchWarning').empty().append('Phone number不符合规范!').show();
            return;
        }else {
            searchMap.userTelephoneNumber = userTelephoneNumber;
        }
    }else{
        searchMap.userTelephoneNumber = null;
    }
    if(checkIsNullOrEmpty(lastLoginTimeStart)) {
        searchMap.lastLoginTimeStart = lastLoginTimeStart;
    }else{
        searchMap.lastLoginTimeStart = null;
    }
    if(checkIsNullOrEmpty(lastLoginTimeEnd)) {
        searchMap.lastLoginTimeEnd = lastLoginTimeEnd;
    }else{
        searchMap.lastLoginTimeEnd = null;
    }
    if(checkIsNullOrEmpty(userRights)) {
        searchMap.userRights = userRights;
    }else{
        searchMap.userRights = null;
    }
    if(checkIsNullOrEmpty(accountStatus)) {
        searchMap.accountStatus = accountStatus;
    }else{
        searchMap.accountStatus = null;
    }
    init_user_search_info(searchMap,type,searchForm);
}

function init_user_search_info(searchMap,type,searchForm) {
    let searchAnSortInfo = searchMap.sourceFrom;
    if(checkIsNullOrEmpty(searchMap.userName)){
        searchAnSortInfo+='>>UserName';
    }else{
        searchMap.userName = null;
    }
    if(checkIsNullOrEmpty(searchMap.userId)){
        searchAnSortInfo+='>>UserId';
    }else{
        searchMap.userId= null;
    }
    if(checkIsNullOrEmpty(searchMap.userEmail)){
        searchAnSortInfo+='>>Email';
    }else{
        searchMap.userEmail = null;
    }
    if(checkIsNullOrEmpty(searchMap.userTelephoneNumber)){
        searchAnSortInfo+='>>Phone';
    }else{
        searchMap.userTelephoneNumber = null;
    }
    if((checkIsNullOrEmpty(searchMap.lastLoginTimeStart))||(checkIsNullOrEmpty(searchMap.lastLoginTimeEnd))){
        searchAnSortInfo+='>>Time';
        if(!checkIsNullOrEmpty(searchMap.lastLoginTimeStar)) {
            searchMap.lastLoginTimeStart = null;
        }
        if(!checkIsNullOrEmpty(searchMap.lastLoginTimeEnd)) {
            searchMap.lastLoginTimeEnd = null;
        }
    }
    if(checkIsNullOrEmpty(searchMap.userRights)){
        searchAnSortInfo+='>>Rights';
    }else{
        searchMap.userRights=null;
    }
    if(checkIsNullOrEmpty(searchMap.accountStatus)){
        searchAnSortInfo+='>>Status';
    }else{
        searchMap.accountStatus=null;
    }
    if(checkIsNullOrEmpty(searchMap.listSort)){
        searchAnSortInfo+='>>'+searchMap.listSort;
    }else{
        searchMap.listSort = null;
    }
    $('.searchAndSortInfo').empty().append(searchAnSortInfo);
    searchMap.pn=1;
    admin_to_page(searchMap,type);
}


function blog_searchmap_check(searchMap,type,searchForm) {
    let userName = searchForm.find('input[name*=userName]').val();
    let blogTitle = searchForm.find('input[name*=blogTitle]').val();
    let blogId = searchForm.find('input[name*=blogId]').val();
    let seriesName = searchForm.find('input[name*=seriesName]').val();

    let updateTimeStart = searchForm.find('input[name*=updateTimeStart]').val();
    let updateTimeEnd = searchForm.find('input[name*=updateTimeEnd]').val();
    let blogStatus = searchForm.find('select[name*=blogStatus]').val();
    let blogType = searchForm.find('select[name*=blogType]').val();

    if(checkIsNullOrEmpty(userName)){
        if(userName.length>10){
            searchForm.find('.searchWarning').empty().append('user name不符合规范!').show();
            return;
        }else {
            searchMap.userName = userName;
        }
    }else{
        searchMap.userName = null;
    }
    if(checkIsNullOrEmpty(blogTitle)){
        if(blogTitle.length>15){
            searchForm.find('.searchWarning').empty().append('title不符合规范!').show();
            return;
        }else {
            searchMap.blogTitle = blogTitle;
        }
    }else{
        searchMap.blogTitle = null;
    }
    if(checkIsNullOrEmpty(blogId)){
        if(!validate_data(blogId,'numerical')){
            searchForm.find('.searchWarning').empty().append('blogId不符合规范!').show();
            return;
        }else {
            searchMap.blogId = blogId;
        }
    }else{
        searchMap.blogId = null;
    }
    if(checkIsNullOrEmpty(blogStatus)){
        if(!validate_data(blogStatus,'numerical')){
            searchForm.find('.searchWarning').empty().append('blogStatus不符合规范!').show();
            return;
        }else {
            searchMap.blogStatus = blogStatus;
        }
    }else{
        searchMap.blogStatus = null;
    }
    if(checkIsNullOrEmpty(updateTimeStart)) {
        searchMap.updateTimeStart = updateTimeStart;
    }else{
        searchMap.updateTimeStart = null;
    }
    if(checkIsNullOrEmpty(updateTimeEnd)) {
        searchMap.updateTimeEnd = updateTimeEnd;
    }else{
        searchMap.updateTimeEnd = null;
    }
    if(checkIsNullOrEmpty(blogType)){
        if(!validate_data(blogType,'numerical')){
            searchForm.find('.searchWarning').empty().append('blogType不符合规范!').show();
            return;
        }else {
            searchMap.blogType = blogType;
        }
    }else{
        searchMap.blogType = null;
    }
    if(checkIsNullOrEmpty(seriesName)){
        if(blogTitle.length>15){
            searchForm.find('.searchWarning').empty().append('Series name不符合规范!').show();
            return;
        }else {
            searchMap.seriesName = seriesName;
        }
    }else{
        searchMap.seriesName = null;
    }
    init_blog_search_info(searchMap,type,searchForm);
}



function init_blog_search_info(searchMap,type,searchForm) {
    let searchAnSortInfo = searchMap.sourceFrom;
    if(checkIsNullOrEmpty(searchMap.userName)){
        searchAnSortInfo+='>>userName';
    }else{
        searchMap.userName = null;
    }
    if(checkIsNullOrEmpty(searchMap.blogTitle)){
        searchAnSortInfo+='>>title';
    }else{
        searchMap.blogTitle= null;
    }
    if(checkIsNullOrEmpty(searchMap.blogId)){
        searchAnSortInfo+='>>blogId';
    }else{
        searchMap.blogId = null;
    }
    if(checkIsNullOrEmpty(searchMap.blogStatus)){
        searchAnSortInfo+='>>status';
    }else{
        searchMap.blogStatus = null;
    }
    if((checkIsNullOrEmpty(searchMap.updateTimeStart))||(checkIsNullOrEmpty(searchMap.updateTimeEnd))){
        searchAnSortInfo+='>>time';
        if(!checkIsNullOrEmpty(searchMap.updateTimeStart)) {
            searchMap.updateTimeStart = null;
        }
        if(!checkIsNullOrEmpty(searchMap.updateTimeEnd)) {
            searchMap.updateTimeEnd = null;
        }
    }
    if(checkIsNullOrEmpty(searchMap.blogType)){
        searchAnSortInfo+='>>type';
    }else{
        searchMap.blogType=null;
    }
    if(checkIsNullOrEmpty(searchMap.seriesName)){
        searchAnSortInfo+='>>series';
    }else{
        searchMap.seriesName=null;
    }
    if(checkIsNullOrEmpty(searchMap.listSort)){
        searchAnSortInfo+='>>'+searchMap.listSort;
    }else{
        searchMap.listSort = null;
    }
    $('.searchAndSortInfo').empty().append(searchAnSortInfo);
    searchMap.pn=1;
    admin_to_page(searchMap,type);
}



function init_list_sort_area(type,listSortArea) {
    listSortArea.children().remove();
    listSortArea.append($('<a></a>').addClass('dropdown-toggle font-alt').attr('data-toggle','dropdown').append($('<i></i>').addClass('fas fa-sort fa-lg')).append('  排序'));

    let sortSelectArea = $('<ul></ul>').addClass('dropdown-menu sortMenu').attr('role','menu');

    if(type=='blog'){
        sortSelectArea.append($('<li></li>').attr('value','updateTimeASC').append($('<a></a>').append($('<i></i>').addClass('fal fa-sort-amount-up-alt')).append('  更新时间-升序')))
            .append($('<li></li>').attr('value','updateTimeDESC').append($('<a></a>').append($('<i></i>').addClass('fal fa-sort-amount-down')).append('  更新时间-降序')))
            .append($('<li></li>').attr('value','viewASC').append($('<a></a>').append($('<i></i>').addClass('fal fa-sort-amount-up-alt')).append('  浏览量-升序')))
            .append($('<li></li>').attr('value','viewDESC').append($('<a></a>').append($('<i></i>').addClass('fal fa-sort-amount-down')).append('  浏览量-降序')))
            .append($('<li></li>').attr('value','likeASC').append($('<a></a>').append($('<i></i>').addClass('fal fa-sort-amount-up-alt')).append('  点赞数-升序')))
            .append($('<li></li>').attr('value','likeDESC').append($('<a></a>').append($('<i></i>').addClass('fal fa-sort-amount-down')).append('  点赞数-降序')))
            .append($('<li></li>').append($('<a></a>').append($('<i></i>').addClass('far fa-circle-notch')).append('  默认排序')));
    }else if(type=='draft'){
        sortSelectArea.append($('<li></li>').attr('value','updateTimeASC').append($('<a></a>').append($('<i></i>').addClass('fal fa-sort-amount-up-alt')).append('  更新时间-升序')))
            .append($('<li></li>').attr('value','updateTimeDESC').append($('<a></a>').append($('<i></i>').addClass('fal fa-sort-amount-down')).append('  更新时间-降序')))
            .append($('<li></li>').append($('<a></a>').append($('<i></i>').addClass('far fa-circle-notch')).append('  默认排序')));
    }else if(type=='user'){
        sortSelectArea.append($('<li></li>').attr('value','lastLoginTimeASC').append($('<a></a>').append($('<i></i>').addClass('fal fa-sort-amount-up-alt')).append('  最近登录时间-升序')))
            .append($('<li></li>').attr('value','lastLoginTimeDESC').append($('<a></a>').append($('<i></i>').addClass('fal fa-sort-amount-down')).append('  最近登录时间-降序')))
            .append($('<li></li>').attr('value','registerTimeASC').append($('<a></a>').append($('<i></i>').addClass('fal fa-sort-amount-up-alt')).append('  注册时间-升序')))
            .append($('<li></li>').attr('value','registerTimeDESC').append($('<a></a>').append($('<i></i>').addClass('fal fa-sort-amount-down')).append('  注册时间-降序')))
            .append($('<li></li>').attr('value','birthdayTimeASC').append($('<a></a>').append($('<i></i>').addClass('fal fa-sort-amount-up-alt')).append('  出生日期-升序')))
            .append($('<li></li>').attr('value','birthdayTimeDESC').append($('<a></a>').append($('<i></i>').addClass('fal fa-sort-amount-down')).append('  出生日期-降序')))
            .append($('<li></li>').attr('value','creditASC').append($('<a></a>').append($('<i></i>').addClass('fal fa-sort-amount-up-alt')).append('  积分-升序')))
            .append($('<li></li>').attr('value','creditDESC').append($('<a></a>').append($('<i></i>').addClass('fal fa-sort-amount-down')).append('  积分-降序')))
            .append($('<li></li>').append($('<a></a>').append($('<i></i>').addClass('far fa-circle-notch')).append('  默认排序')));
    }
    listSortArea.append(sortSelectArea);

    sortSelectArea.on("click","li",function(){      //只需要找到你点击的是哪个ul里面的就行
        listSortArea.find('a.dropdown-toggle').empty().append($('<i></i>').addClass('fas fa-sort fa-lg')).append('  '+$(this).text());
        let listSort =$(this).attr('value');
        if(checkIsNullOrEmpty(listSort)){
            admin_global_search_map.listSort = listSort;
        }else{
            admin_global_search_map.listSort = null;
        }
        admin_global_search_map.pn=1;
        admin_to_page(admin_global_search_map,type);
    });
}


function  admin_build_page_list(pageInfo,type,listArea){
    listArea.children().remove();
    let tableArea = $('<table></table>').addClass('table table-striped table-hover');
    if(type=='blog') {
        let theadArea = $('<thead></thead>').append($('<tr></tr>')
            .append($('<th></th>').addClass('col-sm-1').append($('<input/>').attr('type', 'checkbox').addClass('listItemCheck')))
            .append($('<th></th>').addClass('col-sm-1').append('Id'))
            .append($('<th></th>').addClass('col-sm-2').append('Title'))
            .append($('<th></th>').addClass('col-sm-1').append('Author(User Id)'))
            .append($('<th></th>').addClass('col-sm-1').append('Status'))
            .append($('<th></th>').addClass('col-sm-1').append('Type'))
            .append($('<th></th>').addClass('col-sm-1').append('Series'))
            .append($('<th></th>').addClass('col-sm-1').append('UpdateTime'))
            .append($('<th></th>').addClass('col-sm-1').append('Views'))
            .append($('<th></th>').addClass('col-sm-1').append('Likes'))
            .append($('<th></th>').addClass('col-sm-1').append('操作')));
        tableArea.append(theadArea);
    }else if(type=='draft') {
        let theadArea = $('<thead></thead>').append($('<tr></tr>')
            .append($('<th></th>').addClass('col-sm-1').append($('<input/>').attr('type', 'checkbox').addClass('listItemCheck')))
            .append($('<th></th>').addClass('col-sm-1').append('Id'))
            .append($('<th></th>').addClass('col-sm-2').append('Title'))
            .append($('<th></th>').addClass('col-sm-1').append('Author(User Id)'))
            .append($('<th></th>').addClass('col-sm-1').append('Status'))
            .append($('<th></th>').addClass('col-sm-1').append('Type'))
            .append($('<th></th>').addClass('col-sm-1').append('Series'))
            .append($('<th></th>').addClass('col-sm-1').append('UpdateTime'))
            .append($('<th></th>').addClass('col-sm-1').append('OriginId'))
            .append($('<th></th>').addClass('col-sm-1').append('操作')));
        tableArea.append(theadArea);
    }else if(type=='user') {
        let theadArea = $('<thead></thead>').append($('<tr></tr>')
            .append($('<th></th>').addClass('col-sm-1').append($('<input/>').attr('type', 'checkbox').addClass('listItemCheck')))
            .append($('<th></th>').addClass('col-sm-1').append('Id'))
            .append($('<th></th>').addClass('col-sm-2').append('Name'))
            .append($('<th></th>').addClass('col-sm-1').append('Ip'))
            .append($('<th></th>').addClass('col-sm-1').append('Email'))
            .append($('<th></th>').addClass('col-sm-1').append('Phone number'))
            .append($('<th></th>').addClass('col-sm-1').append('Credit'))
            .append($('<th></th>').addClass('col-sm-1').append('Rights'))
            .append($('<th></th>').addClass('col-sm-1').append('Status'))
            .append($('<th></th>').addClass('col-sm-1').append('Sex'))
            .append($('<th></th>').addClass('col-sm-1').append('Latest login'))
            .append($('<th></th>').addClass('col-sm-1').append('操作')));
        tableArea.append(theadArea);
    }

    let tbodyArea =  $('<tbody></tbody>');
    let list = pageInfo.list;
    $.each(list, function (index, item) {
        add_list_item(index, item,type,tbodyArea);
    });
    tableArea.append(tbodyArea);
    listArea.append(tableArea);
    admin_build_page_nav(pageInfo,type,listArea);
}


// 解析并显示分页条的function
function admin_build_page_nav(pageInfo,type, listArea) {

    // 首先清空数据
    let paginationArea = listArea.next(".pagination");
    // var pageInfo = result.extend.briefpageinfo;
    paginationArea.children().remove();
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
            admin_global_search_map.pn=pageInfo.pageNum - 1;

            admin_to_page(admin_global_search_map,type);
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
            admin_global_search_map.pn=pageInfo.pageNum + 1;
            admin_to_page( admin_global_search_map, type);
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
                admin_global_search_map.pn=item;
                admin_to_page( admin_global_search_map, type);
            });
        }
        paginationArea.append(numPage);
    });
    paginationArea.append(nextPage);


}



function add_list_item(index, item,type,tbodyArea) {

    if(type=='blog') {
        let trArea = $('<tr></tr>')
            .append($('<td></td>').append($('<input/>').attr('type', 'checkbox').addClass('listItemCheck').attr('name', 'itemCheck' + index)))
            .append($('<td></td>').append(item.blogId))
            .append($('<td></td>').append($('<a></a>').attr('href',global_url_map.context_base+global_url_map.blog_url+"/" + item.blogId+'?sf=' + item.sourceFrom).attr('target','_blank').append(item.blogTitle)))
            .append($('<td></td>').append($('<a></a>').attr('href',global_url_map.context_base+global_url_map.profile_url+"/" + item.userId).attr('target','_blank').append(item.user.userName+'('+item.user.userId+')')))
            .append($('<td></td>').append(transform_code('blogStatus',item.blogStatus)))
            .append($('<td></td>').append(checkIsNullOrEmpty(item.blogTypeEntity)?item.blogTypeEntity.typeName:'无'))
            .append($('<td></td>').append(checkIsNullOrEmpty(item.blogSeriesEntity)?$('<a></a>').attr('href',global_url_map.context_base+global_url_map.series_url+"/"+item.seriesId).attr('target','_blank').append(item.blogSeriesEntity.seriesName):'无'))
            .append($('<td></td>').append(item.blogUpdateTime))
            .append($('<td></td>').append(item.blogViews))
            .append($('<td></td>').append(item.blogLikeCount))
            .append($('<td></td>')
                .append($('<button></button>').addClass('btn btn-d btn-circle btn-block btn-xs').attr('type','button').append($('<i></i>').addClass('fa fa-cog fa-spin')).append('  设置').attr('name','item_setting'+index))
                .append($('<button></button>').addClass('btn btn-g btn-circle btn-block btn-xs').attr('type','button').append($('<i></i>').addClass('fas fa-trash')).append('  删除').attr('name','item_delete'+index)));
        tbodyArea.append(trArea);
        tbodyArea.on('click','button[name=item_setting'+index+']',function () {
            item_setting(index,type,item);
        });
        tbodyArea.on('click','button[name=item_delete'+index+']',function () {
            item_delete(index,type,item);
        });
    }else if(type=='draft') {
        let trArea = $('<tr></tr>')
            .append($('<td></td>').append($('<input/>').attr('type', 'checkbox').addClass('listItemCheck').attr('name', 'itemCheck' + index)))
            .append($('<td></td>').append(item.blogId))
            .append($('<td></td>').append($('<a></a>').attr('href',global_url_map.context_base+global_url_map.blog_url+"/" + item.blogId+'?sf=' + item.sourceFrom).attr('target','_blank').append(item.blogTitle)))
            .append($('<td></td>').append($('<a></a>').attr('href',global_url_map.context_base+global_url_map.profile_url+"/" + item.userId).attr('target','_blank').append(item.user.userName+'('+item.user.userId+')')))
            .append($('<td></td>').append(transform_code('blogStatus',item.blogStatus)))
            .append($('<td></td>').append(checkIsNullOrEmpty(item.blogTypeEntity)?item.blogTypeEntity.typeName:'无'))
            .append($('<td></td>').append(checkIsNullOrEmpty(item.blogSeriesEntity)?$('<a></a>').attr('href',global_url_map.context_base+global_url_map.series_url+"/"+item.seriesId).attr('target','_blank').append(item.blogSeriesEntity.seriesName):'无'))
            .append($('<td></td>').append(item.blogUpdateTime))
            .append($('<td></td>').append(item.originalBlogId))
            .append($('<td></td>')
                .append($('<button></button>').addClass('btn btn-d btn-circle btn-block btn-xs').attr('type','button').append($('<i></i>').addClass('fa fa-cog fa-spin')).append('  设置').attr('name','item_setting'+index))
                .append($('<button></button>').addClass('btn btn-g btn-circle btn-block btn-xs').attr('type','button').append($('<i></i>').addClass('fas fa-trash')).append('  删除').attr('name','item_delete'+index)));
        tbodyArea.append(trArea);
        tbodyArea.on('click','button[name=item_setting'+index+']',function () {
            item_setting(index,type,item);
        });
        tbodyArea.on('click','button[name=item_delete'+index+']',function () {
            item_delete(index,type,item);
        });
    }else if(type=='user') {
        let trArea = $('<tr></tr>')
            .append($('<td></td>').append($('<input/>').attr('type', 'checkbox').addClass('listItemCheck').attr('name', 'itemCheck' + index)))
            .append($('<td></td>').append(item.userId))
            .append($('<td></td>').append($('<a></a>').attr('href',global_url_map.context_base+global_url_map.profile_url+"/" + item.userId).attr('target','_blank').append(item.userNickname+'('+item.userName+')')))
            .append($('<td></td>').append(item.userIp))
            .append($('<td></td>').append(item.userEmail))
            .append($('<td></td>').append(item.userTelephoneNumber))
            .append($('<td></td>').append(item.userCredit))
            .append($('<td></td>').append(transform_code('userRights',item.userRights)))
            .append($('<td></td>').append(transform_code('accountStatus',item.accountStatus)))
            .append($('<td></td>').append(transform_code('userSex',item.userSex)))
            .append($('<td></td>').append(item.userLastLoginTime))
            .append($('<td></td>')
                .append($('<button></button>').addClass('btn btn-d btn-circle btn-block btn-xs').attr('type','button').append($('<i></i>').addClass('fa fa-cog fa-spin')).append('  设置').attr('name','item_setting'+index))
                .append($('<button></button>').addClass('btn btn-g btn-circle btn-block btn-xs').attr('type','button').append($('<i></i>').addClass('fas fa-trash')).append('  删除').attr('name','item_delete'+index)));
        tbodyArea.append(trArea);
        tbodyArea.on('click','button[name=item_setting'+index+']',function () {
            item_setting(index,type,item);
        });
        tbodyArea.on('click','button[name=item_delete'+index+']',function () {
            item_delete(index,type,item);
        });
    }

}

function item_setting(index,type,item) {
    if(type=='blog'||type=='draft'){
        admin_global_modal.children().remove();
        let modal_content = $('<div></div>').addClass('modal-content');
        let modal_header = $('<div></div>').addClass('modal-header')
            .append($('<button></button>').attr('type','button').addClass('close').attr('data-dismiss','modal').attr('aria-label','Close').append($('<span></span>').attr('aria-hidden','true').text('x')))
            .append($('<h5></h5>').addClass('font-alt mb-0 modal-title').append(type));
        let modal_body = $('<div></div>').addClass('modal-body');
        let form = $('<form></form>').addClass('form itemInfoForm');
        let form_sourceFrom = $('<div></div>').addClass('form-group').attr('style','display: none').append($('<input/>').addClass('form-control').attr('name','sourceFrom').attr('type','text').attr('placeholder','sourceFrom').attr('readonly',''));
        let form_userId = $('<div></div>').addClass('form-group').attr('style','display: none').append($('<input/>').addClass('form-control').attr('name','userId').attr('type','text').attr('placeholder','User Id').attr('readonly',''));

        let form_blogId_head = $('<h5></h5>').addClass('font-alt mb-0').text('Blog ID:');
        let form_blogId = $('<div></div>').addClass('form-group').append($('<input/>').addClass('form-control').attr('name','blogId').attr('type','text').attr('placeholder','Blog Id').attr('readonly',''));

        let form_originId_div = $('<div></div>').attr('style','display: none').attr('name','originBlogIdArea').append($('<hr>').addClass('divider-w mt-10 mb-20')).append($('<h5></h5>').addClass('font-alt mb-0').text('Origin Blog Id:'))
            .append($('<div></div>').addClass('form-group').append($('<input>').addClass('form-control').attr('name','originalBlogId').attr('type','text').attr('placeholder','Origin Blog Id').attr('readonly','')));

        let form_userName_hr = $('<hr>').addClass('divider-w mt-10 mb-20');
        let form_userName_head = $('<h5></h5>').addClass('font-alt mb-0').text('User Name:');
        let form_userName = $('<div></div>').addClass('form-group').append($('<input/>').addClass('form-control').attr('name','userName').attr('type','text').attr('placeholder','User Name').attr('readonly',''));

        let form_blogTitle_hr = $('<hr>').addClass('divider-w mt-10 mb-20');
        let form_blogTitle_head = $('<h5></h5>').addClass('font-alt mb-0').text('Blog Title:');
        let form_blogTitle = $('<div></div>').addClass('form-group').append($('<input/>').addClass('form-control').attr('name','blogTitle').attr('type','text').attr('placeholder','Title'));

        let form_blogIntro_hr = $('<hr>').addClass('divider-w mt-10 mb-20');
        let form_blogIntro_head = $('<h5></h5>').addClass('font-alt mb-0').text('Blog Intro:');
        let form_blogIntro = $('<div></div>').addClass('form-group').append($('<textarea></textarea>').addClass('form-control hTextarea').attr('name','blogIntro').attr('rows','7').attr('cols','7').attr('maxlength','300').attr('placeholder','Blog Intro'));

        let form_blogStatus_hr = $('<hr>').addClass('divider-w mt-10 mb-20');
        let form_blogStatus_head = $('<h5></h5>').addClass('font-alt mb-0').text('Blog Status:');
        let form_blogStatus = $('<div></div>').addClass('form-group');
        let status_select = $('<select></select>').addClass('form-control').attr('name','blogStatus')
            .append($('<option></option>').attr('selected',null).attr('disabled',null).attr('value',null).text('Choose...'))
            .append($('<option></option>').attr('value','7').text('highlight'))
            .append($('<option></option>').attr('value','6').text('normal'))
            .append($('<option></option>').attr('value','5').text('friendly'))
            .append($('<option></option>').attr('value','4').text('private'))
            .append($('<option></option>').attr('value','3').text('draft'))
            .append($('<option></option>').attr('value','2').text('preview').attr('disabled',null))
            .append($('<option></option>').attr('value','1').text('pre-create').attr('disabled',null));
        form_blogStatus.append(status_select).append($('<p></p>').addClass('help-block text-danger pull-right hInputInfo itemInfoModalWarning'));

        form.append(form_sourceFrom).append(form_userId).append(form_blogId_head).append(form_blogId).append(form_originId_div).append(form_userName_hr).append(form_userName_head).append(form_userName).append(form_blogTitle_hr).append(form_blogTitle_head).append(form_blogTitle).append(form_blogIntro_hr).append(form_blogIntro_head).append(form_blogIntro).append(form_blogStatus_hr).append(form_blogStatus_head).append(form_blogStatus);
        modal_body.append(form);

        let modal_footer=$('<div></div>').addClass('modal-footer').append($('<button></button>').addClass('btn btn-border-d btn-round').attr('data-dismiss','modal').attr('type','button').text('取消')).append($('<button></button>').addClass('btn btn-b btn-round blogModalConfirmBtn'+index).attr('type','button').text('确认'));

        modal_content.append(modal_header).append(modal_body).append(modal_footer);
        admin_global_modal.append($('<div></div>').addClass('modal-dialog hShadowAndRound').attr('role','document').append(modal_content));

        admin_global_modal.find('input[name=sourceFrom]').val(item.sourceFrom);
        admin_global_modal.find('input[name=userId]').val(item.userId);
        admin_global_modal.find('input[name=blogId]').val(item.blogId);
        if(type=='blog'){
            admin_global_modal.find('div[name=originBlogIdArea]').hide();
        }else{
            admin_global_modal.find('input[name=originalBlogId]').val(item.originalBlogId);
            admin_global_modal.find('div[name=originBlogIdArea]').show();
        }
        admin_global_modal.find('input[name=userName]').val(item.user.userName);
        admin_global_modal.find('input[name=blogTitle]').val(item.blogTitle);
        admin_global_modal.find('textarea[name=blogIntro]').val(item.blogIntro);
        admin_global_modal.find('select[name=blogStatus]').val(item.blogStatus);
        admin_global_modal.modal('show');
        $('.blogModalConfirmBtn'+index).click(function () {
            let blogMap = {
                sourceFrom:item.sourceFrom,
                userId:item.userId,
                blogId:item.blogId,
                originalBlogId:item.originalBlogId,
                blogTitle:admin_global_modal.find('input[name=blogTitle]').val(),
                blogIntro:admin_global_modal.find('textarea[name=blogIntro]').val(),
                blogStatus: admin_global_modal.find('select[name=blogStatus]').val()
            }
            blog_edit_save_with_check(blogMap,admin_global_modal,type);
        });
        init_modal_warning_auto_hide(admin_global_modal);

    }else if(type=='user'){
        regEmailPass = true;
        admin_global_modal.children().remove();
        let modal_content = $('<div></div>').addClass('modal-content');
        let modal_header = $('<div></div>').addClass('modal-header')
            .append($('<button></button>').attr('type','button').addClass('close').attr('data-dismiss','modal').attr('aria-label','Close').append($('<span></span>').attr('aria-hidden','true').text('x')))
            .append($('<h5></h5>').addClass('font-alt mb-0 modal-title').append(type));
        let modal_body = $('<div></div>').addClass('modal-body');
        let form = $('<form></form>').addClass('form itemInfoForm');

        let form_userId_head = $('<h5></h5>').addClass('font-alt mb-0').text('User ID:');
        let form_userId = $('<div></div>').addClass('form-group').append($('<input/>').addClass('form-control').attr('name','userId').attr('type','text').attr('placeholder','Blog Id').attr('readonly',''));

        let form_userName_hr = $('<hr>').addClass('divider-w mt-10 mb-20');
        let form_userName_head = $('<h5></h5>').addClass('font-alt mb-0').text('User name:');
        let form_userName = $('<div></div>').addClass('form-group').append($('<input/>').addClass('form-control').attr('name','userName').attr('type','text').attr('placeholder','User name'));

        let form_nickName_hr = $('<hr>').addClass('divider-w mt-10 mb-20');
        let form_nickName_head = $('<h5></h5>').addClass('font-alt mb-0').text('Nick name:');
        let form_nickName = $('<div></div>').addClass('form-group').append($('<input/>').addClass('form-control').attr('name','userNickname').attr('type','text').attr('placeholder','User nickName'));

        let form_userEmail_hr = $('<hr>').addClass('divider-w mt-10 mb-20');
        let form_userEmail_head = $('<h5></h5>').addClass('font-alt mb-0').text('Email:');
        let form_userEmail = $('<div></div>').addClass('form-group').append($('<input/>').addClass('form-control userEmail').attr('name','userEmail').attr('type','text').attr('placeholder','Email')).append($('<p></p>').addClass('help-block text-danger hInputInfo'));

        let form_phoneNum_hr = $('<hr>').addClass('divider-w mt-10 mb-20');
        let form_phoneNum_head = $('<h5></h5>').addClass('font-alt mb-0').text('Phone number:');
        let form_phoneNum = $('<div></div>').addClass('form-group').append($('<input/>').addClass('form-control').attr('name','userTelephoneNumber').attr('type','text').attr('placeholder','Phone Number'));

        let form_githubLink_hr = $('<hr>').addClass('divider-w mt-10 mb-20');
        let form_githubLink_head = $('<h5></h5>').addClass('font-alt mb-0').text('Github link:');
        let form_githubLink = $('<div></div>').addClass('form-group').append($('<input/>').addClass('form-control').attr('name','githubLink').attr('type','text').attr('placeholder','Github link'));

        let form_weiboLink_hr = $('<hr>').addClass('divider-w mt-10 mb-20');
        let form_weiboLink_head = $('<h5></h5>').addClass('font-alt mb-0').text('Weibo link:');
        let form_weiboLink = $('<div></div>').addClass('form-group').append($('<input/>').addClass('form-control').attr('name','weiboLink').attr('type','text').attr('placeholder','Weibo link'));


        let form_qqLink_hr = $('<hr>').addClass('divider-w mt-10 mb-20');
        let form_qqLink_head = $('<h5></h5>').addClass('font-alt mb-0').text('QQ link:');
        let form_qqLink = $('<div></div>').addClass('form-group').append($('<input/>').addClass('form-control').attr('name','qqLink').attr('type','text').attr('placeholder','QQ link'));


        let form_wexinLink_hr = $('<hr>').addClass('divider-w mt-10 mb-20');
        let form_wexinLink_head = $('<h5></h5>').addClass('font-alt mb-0').text('WeChat link:');
        let form_wexinLink = $('<div></div>').addClass('form-group').append($('<input/>').addClass('form-control').attr('name','wechatLink').attr('type','text').attr('placeholder','WeChat link'));

        let form_emailLink_hr = $('<hr>').addClass('divider-w mt-10 mb-20');
        let form_emailLink_head = $('<h5></h5>').addClass('font-alt mb-0').text('Email link:');
        let form_emailLink = $('<div></div>').addClass('form-group').append($('<input/>').addClass('form-control').attr('name','emailLink').attr('type','text').attr('placeholder','Email link'));

        let form_csdnLink_hr = $('<hr>').addClass('divider-w mt-10 mb-20');
        let form_csdnLink_head = $('<h5></h5>').addClass('font-alt mb-0').text('CSDN link:');
        let form_csdnLink = $('<div></div>').addClass('form-group').append($('<input/>').addClass('form-control').attr('name','csdnLink').attr('type','text').attr('placeholder','CSDN link'));

        let form_otherLink_hr = $('<hr>').addClass('divider-w mt-10 mb-20');
        let form_otherLink_head = $('<h5></h5>').addClass('font-alt mb-0').text('Other link:');
        let form_otherLink = $('<div></div>').addClass('form-group').append($('<input/>').addClass('form-control').attr('name','otherLink').attr('type','text').attr('placeholder','Other link'));

        let form_rights_hr = $('<hr>').addClass('divider-w mt-10 mb-20');
        let form_rights_head = $('<h5></h5>').addClass('font-alt mb-0').text('Rights:');
        let form_rights = $('<div></div>').addClass('form-group');
        let rights_select = $('<select></select>').addClass('form-control').attr('name','userRights')
            .append($('<option></option>').attr('selected','selected').attr('disabled','disabled').attr("value","").append('Rights...'))
            .append($('<option></option>').attr('value',6).append('level-1'))
            .append($('<option></option>').attr('value',5).append('level-2'))
            .append($('<option></option>').attr('value',4).append('level-3'))
            .append($('<option></option>').attr('value',3).append('level-4'))
            .append($('<option></option>').attr('value',2).append('Admin'))
            .append($('<option></option>').attr('value',1).append('SuperAdmin'));
        form_rights.append(rights_select);

        let form_status_hr = $('<hr>').addClass('divider-w mt-10 mb-20');
        let form_status_head = $('<h5></h5>').addClass('font-alt mb-0').text('Status:');
        let form_status = $('<div></div>').addClass('form-group');
        let status_select = $('<select></select>').addClass('form-control').attr('name','accountStatus')
            .append($('<option></option>').attr('selected','selected').attr('disabled','disabled').attr("value","").append('Status...'))
            .append($('<option></option>').attr('value',6).append('deleted'))
            .append($('<option></option>').attr('value',5).append('forbidden'))
            .append($('<option></option>').attr('value',4).append('incomplete'))
            .append($('<option></option>').attr('value',3).append('invisible'))
            .append($('<option></option>').attr('value',2).append('friendly'))
            .append($('<option></option>').attr('value',1).append('normal'));
        form_status.append(status_select);

        let form_psw_hr = $('<hr>').addClass('divider-w mt-10 mb-20');
        let form_psw_head = $('<h5></h5>').addClass('font-alt mb-0').text('Password:');
        let form_psw = $('<div></div>').addClass('form-group').append($('<input/>').addClass('form-control').attr('name','newPassword').attr('type','text').attr('placeholder','Password')).append($('<p></p>').addClass('help-block text-danger pull-right hInputInfo itemInfoModalWarning'));

        form.append(form_userId_head).append(form_userId).append(form_userName_hr).append(form_userName_head).append(form_userName).append(form_nickName_hr).append(form_nickName_head).append(form_nickName)
            .append(form_userEmail_hr).append(form_userEmail_head).append(form_userEmail).append(form_phoneNum_hr).append(form_phoneNum_head).append(form_phoneNum).append(form_githubLink_hr).append(form_githubLink_head).append(form_githubLink)
            .append(form_weiboLink_hr).append(form_weiboLink_head).append(form_weiboLink).append(form_qqLink_hr).append(form_qqLink_head).append(form_qqLink).append(form_wexinLink_hr).append(form_wexinLink_head).append(form_wexinLink)
            .append(form_emailLink_hr).append(form_emailLink_head).append(form_emailLink).append(form_csdnLink_hr).append(form_csdnLink_head).append(form_csdnLink).append(form_otherLink_hr).append(form_otherLink_head).append(form_otherLink)
            .append(form_rights_hr).append(form_rights_head).append(form_rights).append(form_status_hr).append(form_status_head).append(form_status).append(form_psw_hr).append(form_psw_head).append(form_psw);

        modal_body.append(form);

        let modal_footer=$('<div></div>').addClass('modal-footer').append($('<button></button>').addClass('btn btn-border-d btn-round').attr('data-dismiss','modal').attr('type','button').text('取消')).append($('<button></button>').addClass('btn btn-b btn-round userModalConfirmBtn'+index).attr('type','button').text('确认'));

        modal_content.append(modal_header).append(modal_body).append(modal_footer);
        admin_global_modal.append($('<div></div>').addClass('modal-dialog hShadowAndRound').attr('role','document').append(modal_content));

        admin_global_modal.find('input[name=userId]').val(item.userId);
        admin_global_modal.find('input[name=userName]').val(item.userName);
        admin_global_modal.find('input[name=userNickname]').val(item.userNickname);
        admin_global_modal.find('input[name=userEmail]').val(item.userEmail);
        admin_global_modal.find('input[name=userTelephoneNumber]').val(item.userTelephoneNumber);
        admin_global_modal.find('input[name=githubLink]').val(item.githubLink);
        admin_global_modal.find('input[name=weiboLink]').val(item.weiboLink);
        admin_global_modal.find('input[name=qqLink]').val(item.qqLink);
        admin_global_modal.find('input[name=wechatLink]').val(item.wechatLink);

        admin_global_modal.find('input[name=emailLink]').val(item.emailLink);
        admin_global_modal.find('input[name=csdnLink]').val(item.csdnLink);
        admin_global_modal.find('input[name=otherLink]').val(item.otherLink);

        admin_global_modal.find('select[name=userRights]').val(item.userRights);
        admin_global_modal.find('select[name=accountStatus]').val(item.accountStatus);

        admin_global_modal.find('input[name=newPassword]').val('');

        admin_global_modal.modal('show');
        $('.userModalConfirmBtn'+index).click(function () {
            let userMap={
                userId:item.userId,
                userName: admin_global_modal.find('input[name=userName]').val(),
                userNickname: admin_global_modal.find('input[name=userNickname]').val(),
                userEmail: admin_global_modal.find('input[name=userEmail]').val(),
                userTelephoneNumber:  admin_global_modal.find('input[name=userTelephoneNumber]').val(),
                githubLink: admin_global_modal.find('input[name=githubLink]').val(),
                weiboLink: admin_global_modal.find('input[name=weiboLink]').val(),
                qqLink: admin_global_modal.find('input[name=qqLink]').val(),
                wechatLink: admin_global_modal.find('input[name=wechatLink]').val(),
                emailLink: admin_global_modal.find('input[name=emailLink]').val(),
                csdnLink:admin_global_modal.find('input[name=csdnLink]').val(),
                otherLink:admin_global_modal.find('input[name=otherLink]').val(),
                userRights: admin_global_modal.find('select[name=userRights]').val(),
                accountStatus:admin_global_modal.find('select[name=accountStatus]').val(),
                newPassword: admin_global_modal.find('input[name=newPassword]').val()
            }
            user_edit_save_with_check(userMap,admin_global_modal,type);
        });
        admin_global_modal.find('.userEmail').change(function () {
            regEmailCheck(global_url_map.context_base+'/registercheck',this);
        });
        admin_global_modal.find('.userEmail').focus(function () {
            $(this).next(".text-danger").empty().hide();
        });
        init_modal_warning_auto_hide(admin_global_modal);
    }
}

let regEmailPass;
function user_edit_save_with_check(userMap,modal,type) {
    let userName = userMap.userName;
    let userNickname = userMap.userNickname;
    let userEmail = userMap.userEmail;
    let userTelephoneNumber = userMap.userTelephoneNumber;
    let newPassword = userMap.newPassword;
    if(checkIsNullOrEmpty(userName)&&!validate_data(userName, "name")){
        if(modal.hasClass('in')) {
            modal.find(".itemInfoModalWarning").empty().append("User name不符合规范").show();
        }else{
            toastr.warning("User name不符合规范!");
        }
        return "User name不符合规范";
    }
    if(checkIsNullOrEmpty(userNickname)&&!validate_data(userNickname, "name")){
        if(modal.hasClass('in')) {
            modal.find(".itemInfoModalWarning").empty().append("Nick name不符合规范").show();
        }else{
            toastr.warning("Nick name不符合规范!");
        }
        return "Nick name不符合规范";
    }
    if(checkIsNullOrEmpty(userEmail)&&!validate_data(userEmail, "email")){
        if(modal.hasClass('in')) {
            modal.find(".itemInfoModalWarning").empty().append("Email不符合规范").show();
        }else{
            toastr.warning("Email不符合规范!");
        }
        return "Email不符合规范";
    }
    if(regEmailPass==false){
        if(modal.hasClass('in')) {
            modal.find(".itemInfoModalWarning").empty().append("Email已被注册").show();
        }else{
            toastr.warning("Email已被注册!");
        }
        return "Email已被注册";
    }
    if(checkIsNullOrEmpty(userTelephoneNumber)&&!validate_data(userTelephoneNumber, "phoneNum")){
        if(modal.hasClass('in')) {
            modal.find(".itemInfoModalWarning").empty().append("Phone number不符合规范").show();
        }else{
            toastr.warning("Phone number不符合规范!");
        }
        return "Phone number不符合规范";
    }
    if(checkIsNullOrEmpty(newPassword)&&!validate_data(newPassword, "password")){
        if(modal.hasClass('in')) {
            modal.find(".itemInfoModalWarning").empty().append("Password不符合规范").show();
        }else{
            toastr.warning("Password不符合规范!");
        }
        return "Password不符合规范";
    }

    let url = global_url_map.context_base+admin_url_map.admin_user_edit_url;
    modal.modal('hide');
    admin_save_user_info(userMap,type,url);
}


function admin_save_user_info(map,type,url) {
    $.ajax({
        url: url,
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(map),
        success: function (result) {
            if (result.code == 100) {
                toastr.success(result.msg);
                if(admin_global_listArea){
                    admin_global_search_map.pn=admin_global_current_page;
                    admin_to_page(admin_global_search_map,type);
                }
            } else {
                toastr.error("保存失败!"+result.msg);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {

            toastr.error("保存失败!"+textStatus);
        },
    });

}


function blog_edit_save_with_check(blogMap,modal,type) {
    let title = blogMap.blogTitle;
    let intro = blogMap.blogIntro;
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
    let url = global_url_map.context_base+admin_url_map.admin_blog_edit_url;
    modal.modal('hide');
    admin_save_blog_info(blogMap,type,url);
}

function admin_save_blog_info(map,type,url) {
    $.ajax({
        url: url,
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(map),
        success: function (result) {
            if (result.code == 100) {
                toastr.success(result.msg);
                if(admin_global_listArea){
                    admin_global_search_map.pn=admin_global_current_page;
                    admin_to_page(admin_global_search_map,type);
                }
            } else {
                toastr.error("保存失败!"+result.msg);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {

            toastr.error("保存失败!"+textStatus);
        },
    });

}


function item_delete(index,type,item) {
    if(type=='blog'||type=='draft'){
        admin_global_modal.children().remove();
        let modal_content = $('<div></div>').addClass('modal-content');
        let modal_header = $('<div></div>').addClass('modal-header')
            .append($('<button></button>').attr('type','button').addClass('close').attr('data-dismiss','modal').attr('aria-label','Close').append($('<span></span>').attr('aria-hidden','true').text('x')))
            .append($('<h5></h5>').addClass('font-alt mb-0 modal-title').append(type));
        let modal_body = $('<div></div>').addClass('modal-body').append($('<h5></h5>').addClass('font-alt mb-0').text("确认删除"+type+'('+item.blogId+')吗？'));


        let modal_footer=$('<div></div>').addClass('modal-footer').append($('<button></button>').addClass('btn btn-border-d btn-round').attr('data-dismiss','modal').attr('type','button').text('取消')).append($('<button></button>').addClass('btn btn-b btn-round modalBlogDeleteConfirmBtn'+index).attr('type','button').text('确认'));

        modal_content.append(modal_header).append(modal_body).append(modal_footer);
        admin_global_modal.append($('<div></div>').addClass('modal-dialog hShadowAndRound').attr('role','document').append(modal_content));

        admin_global_modal.modal('show');
        admin_global_modal.on('click','.modalBlogDeleteConfirmBtn'+index,function () {
            admin_item_delete(global_url_map.context_base+admin_url_map.admin_blog_edit_url+'/'+item.blogId+'?sf='+item.sourceFrom,type);
            admin_global_modal.modal('hide');
        });
    }else if(type=='user'){
        admin_global_modal.children().remove();
        let modal_content = $('<div></div>').addClass('modal-content');
        let modal_header = $('<div></div>').addClass('modal-header')
            .append($('<button></button>').attr('type','button').addClass('close').attr('data-dismiss','modal').attr('aria-label','Close').append($('<span></span>').attr('aria-hidden','true').text('x')))
            .append($('<h5></h5>').addClass('font-alt mb-0 modal-title').append(type));
        let modal_body = $('<div></div>').addClass('modal-body').append($('<h5></h5>').addClass('font-alt mb-0').text("确认删除"+type+'(Name:'+item.userNickname+'('+item.userName+'),Id:'+item.userId+')吗？'));

        let modal_footer=$('<div></div>').addClass('modal-footer').append($('<button></button>').addClass('btn btn-border-d btn-round').attr('data-dismiss','modal').attr('type','button').text('取消')).append($('<button></button>').addClass('btn btn-b btn-round modalUserDeleteConfirmBtn'+index).attr('type','button').text('确认'));

        modal_content.append(modal_header).append(modal_body).append(modal_footer);
        admin_global_modal.append($('<div></div>').addClass('modal-dialog hShadowAndRound').attr('role','document').append(modal_content));

        admin_global_modal.modal('show');
        admin_global_modal.on('click','.modalUserDeleteConfirmBtn'+index,function () {
            admin_item_delete(global_url_map.context_base+admin_url_map.admin_user_edit_url+'/'+item.userId,type);
            admin_global_modal.modal('hide');
        });
    }

}


function admin_item_delete(delete_url,type) {
    $.ajax({
        url: delete_url,
        type: "DELETE",
        success: function (result) {
            if (result.code == 100) {
                toastr.success(result.msg);
                admin_global_search_map.pn=admin_global_current_page;
                admin_to_page(admin_global_search_map,type);
            } else {
                toastr.error("删除失败!"+result.msg);
            }

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {

            toastr.error("删除失败!");
        },
    });
}





function init_modal_warning_auto_hide(modal){
        modal.find('input').focus(function () {
            modal.find(".itemInfoModalWarning").empty().hide();
        });
        modal.find('textarea').focus(function () {
            modal.find(".itemInfoModalWarning").empty().hide();
        });
        modal.find('select').focus(function () {
            modal.find(".itemInfoModalWarning").empty().hide();
        });
}