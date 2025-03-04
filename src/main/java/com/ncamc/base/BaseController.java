package com.ncamc.base;

import lombok.Data;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Data
public class BaseController {

    @Resource
    protected static HttpServletRequest request;

    @Resource
    protected static HttpServletResponse response;

}
