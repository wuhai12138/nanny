package com.summ.nanny.model.request;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by jygj_7500 on 18/1/23.
 */
public class NannyStatmentDetailReq {
    private Integer nannyId;
    private Date startDate;
    private Date endDate;
    private Integer shopId;
    @ApiModelProperty(value = "服务师对账单类型")
    private Integer statmentNannyType;
    @ApiModelProperty(value = "奖惩原因")
    private Integer reason;

    public Integer getReason() {
        return reason;
    }

    public void setReason(Integer reason) {
        this.reason = reason;
    }


    public Integer getStatmentNannyType() {
        return statmentNannyType;
    }

    public void setStatmentNannyType(Integer statmentNannyType) {
        this.statmentNannyType = statmentNannyType;
    }

    public Integer getNannyId() {
        return nannyId;
    }

    public void setNannyId(Integer nannyId) {
        this.nannyId = nannyId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

}
