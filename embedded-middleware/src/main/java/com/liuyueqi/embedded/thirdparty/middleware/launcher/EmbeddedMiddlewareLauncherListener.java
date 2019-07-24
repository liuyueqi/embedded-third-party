package com.liuyueqi.embedded.thirdparty.middleware.launcher;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 */
@Slf4j
public class EmbeddedMiddlewareLauncherListener implements ServletContextListener {

    private static final String CONFIG_LOCATION_PARAM = "embeddedMiddlewareConfigLocation";

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void contextInitialized(ServletContextEvent event) {

        String configLocation = event.getServletContext().getInitParameter(CONFIG_LOCATION_PARAM);
        if (StringUtils.isBlank(configLocation)) {
            log.warn("");
            return;
        }

        initEmbeddedMiddlewareFactory(configLocation);
    }

    private void initEmbeddedMiddlewareFactory(String configLocation) {

        log.info("");
        this.applicationContext = new ClassPathXmlApplicationContext(configLocation);
        log.info("");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        closeEmbeddedMiddlewareFactory();
    }

    private void closeEmbeddedMiddlewareFactory() {

        if (this.applicationContext == null) {
            return;
        }

        log.info("");
        applicationContext.close();
        log.info("");
    }
}
