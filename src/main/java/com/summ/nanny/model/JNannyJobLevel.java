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
@TableName("j_nanny_job_level")
public class JNannyJobLevel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 工种表
     */
    @TableId(value = "nannyJobLevelId", type = IdType.AUTO)
    private Integer nannyJobLevelId;
    @TableField("nannyId")
    private Integer nannyId;
    @TableField("jobLevelId")
    private Integer jobLevelId;
    @TableField("isDel")
    private Integer isDel;


    public Integer getNannyJobLevelId() {
        return nannyJobLevelId;
    }

    public void setNannyJobLevelId(Integer nannyJobLevelId) {
        this.nannyJobLevelId = nannyJobLevelId;
    }

    public Integer getNannyId() {
        return nannyId;
    }

    public void setNannyId(Integer nannyId) {
        this.nannyId = nannyId;
    }

    public Integer getJobLevelId() {
        return jobLevelId;
    }

    public void setJobLevelId(Integer jobLevelId) {
        this.jobLevelId = jobLevelId;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    @Override
    public String toString() {
        return "JNannyJobLevel{" +
        "nannyJobLevelId=" + nannyJobLevelId +
        ", nannyId=" + nannyId +
        ", jobLevelId=" + jobLevelId +
        ", isDel=" + isDel +
        "}";
    }
}
