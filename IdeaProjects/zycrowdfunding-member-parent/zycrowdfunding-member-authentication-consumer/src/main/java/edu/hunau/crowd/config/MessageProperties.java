package edu.hunau.crowd.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "message.prop")
public class MessageProperties {
    String host; //默认"https://feginesms.market.alicloudapi.com";// 【1】请求地址 支持http 和 https 及 WEBSOCKET
    String path;// = "/codeNotice";// 【2】后缀
    String appCode;// = "25ae8fd6e4ba477c8eb12722ea4c1844"; // 【3】开通服务后 买家中心-查看AppCode
    String sign;// = "1"; // 【4】请求参数，详见文档描述
    String skin;// = "8"; // 【4】请求参数，详见文档描述
    public MessageProperties() {
    }

    public MessageProperties(String host, String path, String appCode, String sign, String skin) {
        this.host = host;
        this.path = path;
        this.appCode = appCode;
        this.sign = sign;
        this.skin = skin;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }
}
