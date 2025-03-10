package com.ncamc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 白名单
 */
@Data
@Component
@ConfigurationProperties("secure.ignore")
public class WhiteListConfig {

    private List<String> urls;

}
