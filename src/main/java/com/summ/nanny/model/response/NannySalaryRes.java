package com.summ.nanny.model.response;

import java.math.BigDecimal;
import java.util.List;

public class NannySalaryRes {
    private BigDecimal totalSalary=new BigDecimal(0);
    private BigDecimal realSalary=new BigDecimal(0);
    private BigDecimal reward=new BigDecimal(0);
    private BigDecimal punishment=new BigDecimal(0);
    private List<NannyStatmentDetailRes> nannyStatmentDetailResList;

    public BigDecimal getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(BigDecimal totalSalary) {
        this.totalSalary = totalSalary;
    }

    public BigDecimal getRealSalary() {
        return realSalary;
    }

    public void setRealSalary(BigDecimal realSalary) {
        this.realSalary = realSalary;
    }

    public BigDecimal getReward() {
        return reward;
    }

    public void setReward(BigDecimal reward) {
        this.reward = reward;
    }

    public BigDecimal getPunishment() {
        return punishment;
    }

    public void setPunishment(BigDecimal punishment) {
        this.punishment = punishment;
    }

    public List<NannyStatmentDetailRes> getNannyStatmentDetailResList() {
        return nannyStatmentDetailResList;
    }

    public void setNannyStatmentDetailResList(List<NannyStatmentDetailRes> nannyStatmentDetailResList) {
        this.nannyStatmentDetailResList = nannyStatmentDetailResList;
    }
}
