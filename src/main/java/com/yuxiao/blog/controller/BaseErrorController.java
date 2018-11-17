package com.yuxiao.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ${邹} on 2018/11/8.
 * 描述：
 */
public class BaseErrorController implements ErrorController {
    private static final Logger logger = LoggerFactory.getLogger( BaseApiController.class );
    @Override
    public String getErrorPath() {
        logger.info( "出错了，进入自定义错误控制器" );
        return "/comm/error_404";
    }
    @RequestMapping
    public String errror(){
        return getErrorPath();
    }
}
