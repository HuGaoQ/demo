package com.ncamc.internal;

public interface Constant {

    /**
     * 配置不过滤的路径，支持模糊查询swagger
     */
    String NOT_FILTER_STR = "/swagger|/v2|/v3";

    /**
     * header中的token
     */
    String JWT_HEADER_TOKEN = "token";

    /**
     * 当前请求的用户
     */
    String JWT_REQUEST_USER = "staffId";

}
