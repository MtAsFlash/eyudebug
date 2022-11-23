package org.example;

import lombok.Getter;
import lombok.Setter;

/**
 * @author mtasflash Created on 2022-11-03 17:38
 */
public class GlobalConfig {
    /**
     * 基础url
     */
    private String baseUrl;
    /**
     * 地址
     */
    private String host;
    /**
     * 端口
     */
    private int port;

    private GlobalConfig() {
    }

    public GlobalConfig buildUrl(String host, int port) {
        this.host = host;
        this.port = port;
        return this;
    }

    private static final GlobalConfig instance = new GlobalConfig();

    public static GlobalConfig getInstance() {
        return instance;
    }

    public String getBaseUrl() {
        return "http://" + host + ":" + port + "/api";
    }
}
