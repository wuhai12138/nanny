package com.summ.nanny.model.response;

import io.swagger.annotations.ApiModelProperty;

public class NannyRankingsRes {
    private Integer nannyId;
    private String nannyName;
    /**排行*/
    private Integer rankings;
    /**工时数*/
    private Double workTimes;
    /**好评数*/
    private Integer praises;
    @ApiModelProperty(value = "服务客户数量")
    private Integer customerCount;

    public Integer getCustomerCount() {
        return customerCount;
    }

    public void setCustomerCount(Integer customerCount) {
        this.customerCount = customerCount;
    }

    public Integer getNannyId() {
        return nannyId;
    }

    public void setNannyId(Integer nannyId) {
        this.nannyId = nannyId;
    }

    public String getNannyName() {
        return nannyName;
    }

    public void setNannyName(String nannyName) {
        this.nannyName = nannyName;
    }

    public Integer getRankings() {
        return rankings;
    }

    public void setRankings(Integer rankings) {
        this.rankings = rankings;
    }

    public Double getWorkTimes() {
        return workTimes;
    }

    public void setWorkTimes(Double workTimes) {
        this.workTimes = workTimes;
    }

    public Integer getPraises() {
        return praises;
    }

    public void setPraises(Integer praises) {
        this.praises = praises;
    }
}
