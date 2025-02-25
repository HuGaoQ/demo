package com.ncamc;

import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.status.StatusUtil;

/**
 * 检查：logback是否配置正确
 */
public class LogbackCheck {
    public static void main(String[] args) {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusUtil statusUtil = new StatusUtil(context);
        int highestLevel = statusUtil.getHighestLevel(0);
        if (highestLevel == 0) {
            System.out.println("Logback is properly configured.");
        } else {
            System.out.println("Logback configuration issues detected.");
        }
    }
}