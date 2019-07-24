package com.liuyueqi.embedded.thirdparty.middleware.launcher;

import java.util.Collection;

import com.liuyueqi.embedded.thirdparty.middleware.EmbeddedMiddleware;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 *
 */
public class EmbeddedMiddlewareLauncher {

    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private boolean parallel = false;

    @Getter(AccessLevel.PRIVATE)
    private Collection<EmbeddedMiddleware> middlewares;

    public void launch() {
        
        if (isParallel()) {
            middlewares.parallelStream().forEach(EmbeddedMiddleware::startup);
        } else {
            middlewares.stream().forEach(EmbeddedMiddleware::startup);
        }
    }
}
