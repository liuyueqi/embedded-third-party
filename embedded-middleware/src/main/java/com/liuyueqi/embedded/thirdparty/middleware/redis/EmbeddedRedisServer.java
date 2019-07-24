package com.liuyueqi.embedded.thirdparty.middleware.redis;

import java.io.IOException;

import com.liuyueqi.embedded.thirdparty.middleware.EmbeddedMiddleware;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import redis.embedded.RedisServer;

/**
 * 内嵌的Redis服务
 */
@Slf4j
public class EmbeddedRedisServer implements EmbeddedMiddleware {

    /**
     * 端口
     */
    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private int port = 6379;

    @Getter(AccessLevel.PRIVATE)
    private RedisServer redisServer;

    public EmbeddedRedisServer(int port) {
        setPort(port);
    }

    @Override
    public void startup() {

        try {

            log.info("Ready to start embedded redis server on port: {}", getPort());
            redisServer = new RedisServer(getPort());
            redisServer.start();
            log.info("Embedded redis server started on port: {}", getPort());

        } catch (IOException e) {
            log.error("Fail to start redis server on port: " + getPort(), e);
        }
    }

    @Override
    public void shutdown() {

        log.info("Ready to shutdown embedded redis server");
        if (isActive()) {
            getRedisServer().stop();
            log.info("Embedded redis server stopped");
        } else {
            log.info("Embedded redis server is not acitve");
        }
    }

    @Override
    public boolean isActive() {
        return getRedisServer() != null && getRedisServer().isActive();
    }
}