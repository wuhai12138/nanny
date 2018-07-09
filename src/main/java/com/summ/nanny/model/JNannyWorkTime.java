package com.summ.nanny.model;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author summ
 * @since 2018-05-21
 */
@TableName("j_nanny_work_time")
public class JNannyWorkTime implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "workTimeId", type = IdType.AUTO)
    private Integer workTimeId;
    @TableField("nannyId")
    private Integer nannyId;
    private Long monday;
    private Long tuesday;
    private Long wednesday;
    private Long thursday;
    private Long friday;
    private Long saturday;
    private Long sunday;
    private String remark;
    @TableField("isDel")
    private Integer isDel;


    public Integer getWorkTimeId() {
        return workTimeId;
    }

    public void setWorkTimeId(Integer workTimeId) {
        this.workTimeId = workTimeId;
    }

    public Integer getNannyId() {
        return nannyId;
    }

    public void setNannyId(Integer nannyId) {
        this.nannyId = nannyId;
    }

    public Long getMonday() {
        return monday;
    }

    public void setMonday(Long monday) {
        this.monday = monday;
    }

    public Long getTuesday() {
        return tuesday;
    }

    public void setTuesday(Long tuesday) {
        this.tuesday = tuesday;
    }

    public Long getWednesday() {
        return wednesday;
    }

    public void setWednesday(Long wednesday) {
        this.wednesday = wednesday;
    }

    public Long getThursday() {
        return thursday;
    }

    public void setThursday(Long thursday) {
        this.thursday = thursday;
    }

    public Long getFriday() {
        return friday;
    }

    public void setFriday(Long friday) {
        this.friday = friday;
    }

    public Long getSaturday() {
        return saturday;
    }

    public void setSaturday(Long saturday) {
        this.saturday = saturday;
    }

    public Long getSunday() {
        return sunday;
    }

    public void setSunday(Long sunday) {
        this.sunday = sunday;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    @Override
    public String toString() {
        return "JNannyWorkTime{" +
        "workTimeId=" + workTimeId +
        ", nannyId=" + nannyId +
        ", monday=" + monday +
        ", tuesday=" + tuesday +
        ", wednesday=" + wednesday +
        ", thursday=" + thursday +
        ", friday=" + friday +
        ", saturday=" + saturday +
        ", sunday=" + sunday +
        ", remark=" + remark +
        ", isDel=" + isDel +
        "}";
    }
}
