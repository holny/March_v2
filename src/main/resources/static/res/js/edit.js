let global_blogMap = {
    userId: null,
    blogId: null,
    blogTitle: null,    //modal
    blogIntro: null,     //modal
    seriesId: null,      //modal
    blogType:null,       //modal
    blogStatus: null,
    originalBlogId: null,
    sourceFrom:null,
    newType: null,
    newSeries:null,
    blogContent:null,
    blogUpdateTime:null,
    newTypeCheck:false,
    newSeriesCheck:false
};
let blogEditor;

function editPageInit(modal) {
    init_blog_info_modal(modal);

    global_blogMap.blogTitle = modal.find("form").find("input[name*=blogTitle]").val();
    global_blogMap.blogIntro = modal.find("form").find("textarea[name*=blogIntro]").val();
    global_blogMap.seriesId = modal.find("form").find("select[name*=seriesId]").val();
    global_blogMap.blogType = modal.find("form").find("select[name*=blogType]").val();
    global_blogMap.blogUpdateTime = modal.find("form").find("p[name*=blogUpdateTime]").text();
    global_blogMap.blogStatus=modal.find("form").find("select[name*=blogStatus]").val();
    global_blogMap.originalBlogId=modal.find("form").find("input[name*=originalBlogId]").val();

    modal.on('hidden.bs.modal', function (e) {
        // 模态框hidden后立即解除确认按钮监听绑定，为空就是解除所有
        modal.find('.confirmBtn').unbind("click");
        modal.find('.cancelBtn').unbind("click");
        modal.find('form').hide();
    });

    modal.on('show.bs.modal', function (e) {
        //
        let form = $(this).find("form");
        form.find("input[name*=userId]").val(global_blogMap.userId);
        form.find("input[name*=blogId]").val(global_blogMap.blogId);
        form.find("input[name*=blogTitle]").val(global_blogMap.blogTitle);
        form.find("textarea[name*=blogIntro]").val(global_blogMap.blogIntro);
        form.find("select[name*=seriesId]").val(global_blogMap.seriesId);
        form.find("input[name*=sourceFrom]").val(global_blogMap.sourceFrom);
        form.find("select[name*=blogType]").val(global_blogMap.blogType);
        form.find("select[name*=blogStatus]").val(global_blogMap.blogStatus);
        form.find("input[name*=originalBlogId]").val(global_blogMap.originalBlogId);
        form.find("input[name*=blogTypeCheckbox]").prop("checked",global_blogMap.newTypeCheck);
        form.find("input[name*=newType]").val(global_blogMap.newType);
        form.find("input[name*=seriesIdCheckbox]").prop("checked",global_blogMap.newSeriesCheck);
        form.find("input[name*=newSeries]").val(global_blogMap.newSeries);

        form.find("p[name*=blogUpdateTime]").empty().append(global_blogMap.blogUpdateTime);

        if (global_blogMap.sourceFrom == "blog") {
            form.find("div[name*=originBlogIdArea]").hide();
        } else if (global_blogMap.sourceFrom == "draft") {
            form.find("div[name*=originBlogIdArea]").show();
        }

        if(global_blogMap.newTypeCheck){
            form.find("select[name*=blogType]").val("");
            form.find("select[name*=blogType]").attr("disabled","disabled");
            form.find("input[name*=newType]").removeAttr("disabled");
            form.find("input[name*=newType]").val(global_blogMap.newType);
        }else{
            form.find("input[name*=newType]").val("");
            form.find("input[name*=newType]").attr("disabled","disabled");
            form.find("select[name*=blogType]").removeAttr("disabled");
            form.find("select[name*=blogType]").val(global_blogMap.blogType);
        }
        if(global_blogMap.newSeriesCheck){
            form.find("select[name*=seriesId]").val("");
            form.find("select[name*=seriesId]").attr("disabled","disabled");
            form.find("input[name*=newSeries]").removeAttr("disabled");
            form.find("input[name*=newSeries]").val(global_blogMap.newSeries);
        }else{
            form.find("input[name*=newSeries]").val("");
            form.find("input[name*=newSeries]").attr("disabled","disabled");
            form.find("select[name*=seriesId]").removeAttr("disabled");
            form.find("select[name*=seriesId]").val(global_blogMap.seriesId);
        }

    });
}



// 使用函数名 filterXSS，用法一样
// let html = filterXSS('<script href title target>alert("xss");</scr' + 'ipt>');
function xss_filter(source) {
    let options = {
        whiteList: {
            // a: ["href", "title", "target"]
        },
        // stripIgnoreTag:true,
    };  // 自定义规则
    let myxss = new filterXSS.FilterXSS(options);
    // 以后直接调用 myxss.process() 来处理即可
    // let html = myxss.process(source);
    let result = myxss.process(source);
    return result;
}


function blog_save(saveType) {
    // 2.开始xss过滤
    let mdstr = blogEditor.getMarkdown();
    let result = xss_filter(mdstr);

    let form = blog_info_modal.find("form");

    global_blogMap.blogTitle = form.find("input[name*=blogTitle]").val();
    global_blogMap.blogIntro = form.find("textarea[name*=blogIntro]").val();
    global_blogMap.seriesId = form.find("select[name*=seriesId]").val();
    global_blogMap.blogType = form.find("select[name*=blogType]").val();
    global_blogMap.newType = form.find("input[name*=newType]").val();
    global_blogMap.newSeries = form.find("input[name*=newSeries]").val();
    // 对URL中除 ASCII字母、数字、~!*()'之外的字符编码，也就是对中文编码。同类的有encodeURI、escape。不过encodeURIComponent范围更广。
    // 因为浏览器一般是UTF-8编码，tomcat有可能是iso8859-1解码，所以有可能乱码，而且后端Java又有另一台Java解码。
    // POST方式传中文不会乱码，因为JSP utf-8，后端web.xml配置request.setCharacterEncoding("utf-8")了
    // get方式有自动解码操作，容易乱码。所以在乱码后encodeURIComponent试下

    global_blogMap.blogContent = mdstr;
    global_blogMap.blogStatus = saveType;
    save_blog_info(global_blogMap,blog_info_modal);
}

// editor.md 初始化
function blog_editor_init(elemStr) {
    console.log("blog_editor_init");
    blogEditor = editormd(elemStr, {
        placeholder: '',  //默认显示的文字
        // width: "100vm",
        // height: "100%",
        syncScrolling: "single",
        path:global_url_map.context_base+global_url_map.editormd_url+"/lib/",   //你的path路径（原资源文件中lib包在我们项目中所放的位置）
        theme : "",
        previewTheme : "",
        editorTheme : "mdn-like",
        watch: true,                // 开启实时预览
        saveHTMLToTextarea: true,
        toolbarAutoFixed: false,
        emoji: false,
        taskList: true,

        imageUpload    : true,
        imageFormats   : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
        imageUploadURL : global_url_map.context_base+global_url_map.blog_image_up_url,

        //tocm: true,         // Using [TOCM]
        // tex: true,                   // 开启科学公式TeX语言支持，默认关闭
        // flowChart: true,             // 开启流程图支持，默认关闭
        // sequenceDiagram: true,       // 开启时序/序列图支持，默认关闭,

        // 工具栏添加一个自定义方法
        toolbarIcons: function () {
            let full = [
                "undo", "redo", "|",
                "bold", "del", "italic", "quote", "ucwords", "uppercase", "lowercase", "|",
                "h1", "h2", "h3", "h4", "h5", "h6", "|",
                "list-ul", "list-ol", "hr", "|",
                "link", "reference-link", "image", "code", "preformatted-text", "code-block", "table", "datetime", "emoji", "html-entities", "pagebreak", "|",
                "goto-line", "watch", "preview", "fullscreen", "clear", "search", "|",
                // "help", "info"
            ];
            // 给工具栏full模式添加一个自定义方法
            return full.concat(["settingIcon", "saveIcon", "draftIcon", "emptyIcon", "eyeIcon", "rocketIcon", "homeIcon"]);
        },
        // 自定义方法的图标 指定一个FontAawsome的图标类
        toolbarIconsClass: {
            homeIcon: "fas fa-home",
            saveIcon: "fas fa-save",
            eyeIcon: "fas fa-eye",
            rocketIcon: "fas fa-rocket",
            draftIcon: "fa-paste",
            emptyIcon: "fas fa-trash",
            settingIcon: "fas fa-bars",
        },
        // 没有图标可以插入内容，字符串或HTML标签
        // toolbarIconTexts: {
        //     customIcon: "主页",
        // },
        // 图标的title
        lang: {
            toolbar: {
                settingIcon: "Blog标题等设置",
                homeIcon: "主页",
                saveIcon: "保存为草稿",
                eyeIcon: "预览(非保存)",
                rocketIcon: "发布并退出编辑",
                draftIcon: "从本地缓存获取草稿",
                autoSaveIcon: "关闭自动本地缓存",
                emptyIcon: "清空本地缓存",
            }
        },
        // 自定义工具栏按钮的事件处理
        toolbarHandlers: {
            /**
             * @param {Object}      cm         CodeMirror对象
             * @param {Object}      icon       图标按钮jQuery元素对象
             * @param {Object}      cursor     CodeMirror的光标对象，可获取光标所在行和位置
             * @param {String}      selection  编辑器选中的文本
             */
            homeIcon: function (cm, icon, cursor, selection) {
                blog_info_modal.find("form").hide();
                blog_info_modal.find(".hModalInfo").empty().append("确认返回主页吗? 会丢失未保存的数据!").show();
                blog_info_modal.modal("show");
                // 只触发一次，随后立即解除绑定
                blog_info_modal.find('.confirmBtn').one("click", function () {
                    blog_info_modal.modal("hide");
                    window.location.href = global_url_map.context_base+global_url_map.home_url;
                });
            },
            // 保存草稿到服务器
            saveIcon: function (cm, icon, cursor, selection) {
                blog_info_modal.find("form").hide();
                blog_info_modal.find(".hModalInfo").empty().append("确认保存为草稿吗?").show();
                // $("#modalInfo").empty().append("确认保存为草稿吗?");
                blog_info_modal.modal({
                    backdrop: "static"
                });

                // 只触发一次，随后立即解除绑定
                blog_info_modal.find('.confirmBtn').one("click", function () {
                    blog_info_modal.modal("hide");

                    blog_save(3);
                });
            },
            // 预览
            eyeIcon: function (cm, icon, cursor, selection) {
                blog_info_modal.find("form").hide();
                blog_info_modal.find(".hModalInfo").empty().append("确认预览(非保存)吗?这并不会关闭编辑页面，但还是建议先保存!").show();
                // $("#modalInfo").empty().append("确认预览(非保存)吗?这并不会关闭编辑页面，但还是建议先保存!");
                blog_info_modal.modal("show");

                // 只触发一次，随后立即解除绑定
                blog_info_modal.find('.confirmBtn').one("click", function () {
                    // 对URL中除 ASCII字母、数字、~!*()'之外的字符编码，也就是对中文编码。同类的有encodeURI、escape。不过encodeURIComponent范围更广。
                    // 因为浏览器一般是UTF-8编码，tomcat有可能是iso8859-1解码，所以有可能乱码，而且后端Java又有另一台Java解码。
                    // POST方式传中文不会乱码，因为JSP utf-8，后端web.xml配置request.setCharacterEncoding("utf-8")了
                    // get方式有自动解码操作，容易乱码。所以在乱码后encodeURIComponent试下
                    // 由blog_save里保存预览件并打开新窗口
                    blog_info_modal.modal("hide");
                    blog_save(2);

                });
            },
            // 保存到服务器
            rocketIcon: function (cm, icon, cursor, selection) {
                blog_info_modal.find("form").hide();
                blog_info_modal.find(".hModalInfo").empty().append("确认发布吗?这会保存!").show();
                blog_info_modal.modal("show");

                // 只触发一次，随后立即解除绑定
                blog_info_modal.find('.confirmBtn').one("click", function () {
                    blog_info_modal.modal("hide");
                    blog_save(4);
                });
            },
            draftIcon: function (cm, icon, cursor, selection) {
                blog_info_modal.find("form").hide();
                blog_info_modal.find(".hModalInfo").empty().append("确认从本地缓存获取草稿吗？这会覆盖已有内容！").show();
                blog_info_modal.modal("show");


                // 只触发一次，随后立即解除绑定
                blog_info_modal.find('.confirmBtn').one("click", function () {
                    blog_info_modal.modal("hide");
                    // 读取缓存内容
                    blogEditor.CodeAutoSaveGetCache();
                });
            },
            // 清空本地缓存
            emptyIcon: function (cm, icon, cursor, selection) {
                blog_info_modal.find("form").hide();
                blog_info_modal.find(".hModalInfo").empty().append("确认清空本地缓存内容吗?").show();
                blog_info_modal.modal("show");


                // 只触发一次，随后立即解除绑定
                blog_info_modal.find('.confirmBtn').one("click", function () {
                    blog_info_modal.modal("hide");
                });
            },
            // 设置blog的相关信息
            settingIcon: function (cm, icon, cursor, selection) {


                blog_info_modal.find("form").show();
                blog_info_modal.find(".hModalInfo").empty().show();
                blog_info_modal.find(".itemInfoModalWarning").empty().append("修改后不会立即存入数据库,请点击草稿或者发布按键!").show();
                blog_info_modal.modal("show");

                blog_info_modal.find('.confirmBtn').on("click", function () {
                    let form = $(this).parent().parent('.modal-content').find("form");
                    let title = form.find("input[name*=blogTitle]").val();
                    let intro = form.find("textarea[name*=blogIntro]").val();
                    if (!validate_data(title, "title") || !validate_data(intro, "intro")) {
                        blog_info_modal.find(".itemInfoModalWarning").empty().append("数据校验失败，不符合规范").show();
                    }else {
                        global_blogMap.blogTitle = form.find("input[name*=blogTitle]").val();
                        global_blogMap.blogIntro = form.find("textarea[name*=blogIntro]").val();
                        global_blogMap.seriesId = form.find("select[name*=seriesId]").val();
                        global_blogMap.blogType = form.find("select[name*=blogType]").val();
                        global_blogMap.blogStatus = form.find("select[name*=blogStatus]").val();

                        global_blogMap.newType = form.find("input[name*=newType]").val();
                        global_blogMap.newSeries = form.find("input[name*=newSeries]").val();
                        global_blogMap.newTypeCheck = form.find("input[name*=blogTypeCheckbox]").prop("checked");
                        global_blogMap.newSeriesCheck =form.find("input[name*=seriesIdCheckbox]").prop("checked");
                        // 后期开启xss
                        // let result = xss_filter(mdstr);
                        blog_info_modal.modal("hide");
                    }
                });
            },

        },
        onload: function () {
            //this.fullscreen();
            //this.unwatch();
            //this.watch().fullscreen();
            //this.setMarkdown("#PHP");
            this.width("110vv");
            this.height("100vh");
            //this.resize("100%", 640);
            // 引入插件 执行监听方法
            editormd.loadPlugin(global_url_map.context_base+global_url_map.editormd_url+"/plugins/code-auto-save/code-auto-save", function () {
                // 初始化插件 实现监听
                blogEditor.CodeAutoSave();
            });
        }
    });
}




