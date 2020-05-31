

// // 让用户输入不了空格
// $(".hNoSpaceNAutoClear").keyup(function () {
//     this.value = this.value.replace(/\s+/g, '');
// });
//
// $(".hNoSpaceNAutoClear").focus(function () {
//     let elem = $( this );
//     // elem.val("");
//     elem.nextAll(".text-danger").empty();
// });

// 用户点击验证码图片切换新的验证码图片
// $(".captchaPic").click(function () {
function getCaptchaPic(elem){
    elem.src = global_url_map.context_base+global_url_map.captchaPic_url+"?tockenId=QWERTYUISDFGHJEW?" + Math.random();
    // this.src = getContextPath()+url+"?tockenId=QWERTYUISDFGHJEW?" + Math.random();
};

<!-- Login -->
// $("#loginBtn").click(function () {
function user_login(form){
    // let loginAccount = $("#loginAccount");
    let accountItem = form.find('[name*=account]');
    let accountWarn = accountItem.next('p');
    let pswItem = form.find('[type*=password]');
    let pswWarn = pswItem.next('p');
    let captchaWarn = form.find('img').next('p');
    // // 1. 先对登录信息进行前端校验,已经完成。不过为了便于测试，先取消
    // if(!validate_data(accountItem.val(),"email")){
    //     accountWarn.empty().append("账号不符合规范,请检查后再输入").show();
    // }
    // if(!validate_data(pswItem.val(),"password")){
    //     pswWarn.empty().append("密码不符合规范,请检查后再输入").show();;
    //     return;
    // }
    lockFormUI(form);

    $.ajax({
        url: global_url_map.context_base+global_url_map.userlogin_url,
        type: "POST",
        data: form.serialize(),
        success: function (result) {
            if (result.code == 100) {
                // 用户登录成功
                unLockFormUI(form);
                window.location.href = global_url_map.context_base + result.extend.redirect;
                // alert("没有这个用户！");
            } else if (result.code == 200) {
                // 用户验证失败
                unLockFormUI($("#loginForm"));
                captchaWarn.empty().append(result.msg).show();
                toastr.warning("登录失败!"+result.msg,"登录失败");
                // $("#loginCaptchaWarning").show();
                // window.location.href = "/index";
                form.find('img').trigger("click");
            } else {
                // 后台处理失败
                unLockFormUI($("#loginForm"));
                // captchaWarn.empty().append("错误，请刷新"+result.msg).show();
                toastr.warning("登录失败!"+result.msg,"登录失败");
                form.find('img').trigger("click");
                // $("#loginCaptchaWarning").show();
            }
        },
        error:function (result) {
            toastr.error("登录失败!"+result.msg,"登录错误");
        }
    });
};




document.onkeydown = keyListener;

function keyListener(e) {
    // 当按下回车键，执行我们的代码
    if (e.keyCode == 13) {
        $('.hEnterKeyListener').click();//换成按钮的id即可
    }
}

// <!-- 以下时 Sign UP界面 -->
// let regEmailPass = false, regUserNamePass = false, regPSW1Pass = false, regPSW2Pass = false,regPhoneNum=false;
// 邮箱账户输入不止要有正则校验，还要看是否已存在账户
// $(".regEmailCheck").change(function () {
function regEmailCheck(elem){

    let regEmail = elem.value;
    // 1. 先前端正则校验
    if (validate_data(regEmail, "email")) {
        // $("#regEmailWarning").empty().hide();
        // 2.发送ajax请求校验用户名是否可用
        $.ajax({
            url: global_url_map.context_base+global_url_map.registercheck_url,
            type: "GET",
            data: "registerEmail=" + regEmail,
            success: function (result) {
                if (result.code == 100) {
                    if( $(elem).next(".text-danger").length>0) {
                        $(elem).next(".text-danger").empty().hide();
                    }
                    // $("#regEmailWarning").empty();
                    // $("#regEmailWarning").hide();
                    regEmailPass = true;
                } else {
                    if( $(elem).next(".text-danger").length>0) {
                        $(elem).next(".text-danger").empty().append(result.msg).show();
                    }
                    // $("#regEmailWarning").empty().append(result.extend.registerEmailCheck);
                    // $("#regEmailWarning").show();
                    regEmailPass = false;
                }
            }
        });

    } else {
        // 前端正则校验没过
        $(elem).next(".text-danger").empty().append("邮箱不规范(必须符合格式,并且最长30位)").show();
        regEmailPass = false;
    }

};

function regPhoneNumCheck(elem){

    let regPhoneNum = elem.value;

    // 1. 先前端正则校验
    if (validate_data(regPhoneNum, "phoneNum")) {
        // $("#regEmailWarning").empty().hide();
        // 2.发送ajax请求校验用户名是否可用
        regPhoneNum = true;
        // $.ajax({
        //     url: url,
        //     type: "GET",
        //     data: "registerEmail=" + regEmail,
        //     success: function (result) {
        //         if (result.code == 100) {
        //             if( $(elem).next(".text-danger").length>0) {
        //                 $(elem).next(".text-danger").empty().hide();
        //             }
        //             // $("#regEmailWarning").empty();
        //             // $("#regEmailWarning").hide();
        //             regEmailPass = true;
        //         } else {
        //             if( $(elem).next(".text-danger").length>0) {
        //                 $(elem).next(".text-danger").empty().append(result.extend.registerEmailCheck).show();
        //             }
        //             // $("#regEmailWarning").empty().append(result.extend.registerEmailCheck);
        //             // $("#regEmailWarning").show();
        //             regEmailPass = false;
        //         }
        //     }
        // });

    } else {
        // 前端正则校验没过
        $(elem).next(".text-danger").empty().append("手机号码不规范").show();
        regPhoneNum = false;
    }

};

// 用户姓名输入校验
// $(".regUserNameCheck").change(function () {
function regUserNameCheck(elem){
    let regUserName = elem.value;
    // 1.先进行正则校验
    if (validate_data(regUserName, "name")) {
        // 正则校验过了才会隐藏提示信息
        // $("#regUserNameWarning").empty().hide();
        regUserNamePass = true;
    } else {
        if($(elem).next(".text-danger").length>0) {
            $(elem).next(".text-danger").empty().append("用户姓名不符合规范，字母/汉字和.最长10位").show();
        }
        regUserNamePass = false;
    }
};


// 密码1输入校验
// $("#regUserPSW1").change(function () {
function regUserPSW1Check(elem,brother){
    let regUserPSW1 = elem.value;
    let regUserPSW2 = brother.val();
    // 1.先进行正则校验
    if (validate_data(regUserPSW1, "password")) {
        // 正则校验过了才会隐藏提示信息
        // $("#regUserPSWWarning1").empty().hide();
        regPSW1Pass = true;
    } else {
        if( $(elem).next(".text-danger").length>0) {
            $(elem).next(".text-danger").empty().append("密码不符合规范，6-18位字母数字").show();
        }
        regPSW1Pass = false;
    }
    // 预防用户让密码2与密码1匹配后再回头修改密码1情况
    // input什么都不输入input!=null，也input!=""。但是如果输入后再删除完就会input==""（估计是因为change了，触发了事件)。输入空格 input.trim()==""
    if(checkIsNullOrEmpty(regUserPSW2)){
        if (regUserPSW1 != regUserPSW2) {
            if( $(brother).next(".text-danger").length>0) {
                brother.next(".text-danger").empty().append("与上一个密码不同").show();
            }
            regPSW2Pass = false;
        }else {
            if( $(brother).next(".text-danger").length>0) {
                brother.next(".text-danger").empty().hide();
            }
            regPSW2Pass = true;
        }
    }
};

// 密码2输入校验
// $("#regUserPSW2").change(function () {
function regUserPSW2Check(elem,brother){
    let regUserPSW2 = elem.value;
    let regUserPSW1 = brother.val();
    // 1.首先与密码1进行比对，相同才进行下一步
    if (regUserPSW1 != regUserPSW2) {
        if( $(elem).next(".text-danger").length>0) {
            $(elem).next(".text-danger").empty().append("与上一个密码不同").show();
        }
        regPSW2Pass = false;
    } else {
        // 进行正则校验
        if (validate_data(regUserPSW2, "password")) {
            if( $(elem).next(".text-danger").length>0) {
                // 2. 正则校验过了才会隐藏提示信息
                $(elem).next(".text-danger").empty().hide();
            }
            regPSW2Pass = true;
        } else {
            if( $(elem).next(".text-danger").length>0) {
                $(elem).next(".text-danger").empty().append("密码不符合规范，6-18位字母数字").show();
            }
            regPSW2Pass = false;
        }
    }
};
// 注册按钮点击事件
function user_register(form){
    let captchaPic = form.find('img');
    // let captchaWarn = captchaPic.next('p');
    // 1.先看表单输入信息是否都通过了前端校验
    if (regEmailPass == true && regUserNamePass == true && regPSW1Pass == true && regPSW2Pass == true) {
        // 全部通过了前端校验才表单发往后端
        $.ajax({
            url: global_url_map.context_base+global_url_map.register_url,
            type: "POST",
            data: form.serialize(),
            success: function (result) {
                if (result.code == 100) {
                    window.location.href = global_url_map.context_base + result.extend.redirect;
                } else {
                    toastr.warning(result.msg,"注册失败");
                    captchaPic.trigger("click");
                }
            },
            error:function (result) {
                toastr.error("注册失败!请稍后再试!"+result.msg,"注册错误");
            }
        });
    }else{
        // 表单前端校验没通过
        toastr.warning("注册信息未通过校验!请检查后再输入!","注册失败");
        // captchaWarn.empty().append("注册信息未通过校验").show();
    }
};