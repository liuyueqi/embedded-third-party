package com.liuyueqi.embedded.thirdparty.middleware;

/**
 * 内嵌中间件接口
 */
public interface EmbeddedMiddleware {

    /**
     * 启动中间件
     */
    void startup();

    /**
     * 关闭中间件
     */
    void shutdown();

    /**
     * 是否活跃
     */
    boolean isActive();
}