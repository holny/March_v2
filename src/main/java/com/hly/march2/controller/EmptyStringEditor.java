package com.hly.march2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyEditorSupport;

class EmptyStringEditor extends PropertyEditorSupport {
    private static final Logger log = LoggerFactory.getLogger(EmptyStringEditor.class);

    /**
     * 自定义编辑器，供GlobalControllerAdvice-initBinder注册。
     * 作用是在前端数据传到后端，在controller接收到数据前对数据进行一些形式转换。这里只作为测试，对本项目无作用，
     * 同样作用的的可以考虑spring 的convert，可以做一些更为复杂的转换。converter目录下写了一点例子。
     * @param text
     */
    @Override
    public void setAsText(String text) {
        log.debug("setAsText");
        if(text!=null){
            log.debug("setAsText. text:{}",text);
        }else{
            log.debug("setAsText. text=null");
        }
        if(text!=null&&("".equals(text.trim())||"null".equals(text.trim()))){
            setValue(null);
        }else{
            setValue(text);
        }
    }

    @Override
    public void setValue(Object value) {
        super.setValue(value);
    }

    @Override
    public Object getValue() {
        log.debug("getValue");
        //当变量为字符串"null"时  转换为null
        if("null".equals(super.getValue())){
            return null;
        }
        return super.getValue();
    }

}
