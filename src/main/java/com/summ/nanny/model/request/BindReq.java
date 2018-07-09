package com.summ.nanny.model.request;

import io.swagger.models.auth.In;

public class BindReq {
    private String nannyPhone;
    private Integer nannyId;
    private String WeiXinOpenId;
    private String authCode;
    private Integer shopId;

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getNannyPhone() {
        return nannyPhone;
    }

    public void setNannyPhone(String nannyPhone) {
        this.nannyPhone = nannyPhone;
    }

    public Integer getNannyId() {
        return nannyId;
    }

    public void setNannyId(Integer nannyId) {
        this.nannyId = nannyId;
    }

    public String getWeiXinOpenId() {
        return WeiXinOpenId;
    }

    public void setWeiXinOpenId(String weiXinOpenId) {
        WeiXinOpenId = weiXinOpenId;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }
}
