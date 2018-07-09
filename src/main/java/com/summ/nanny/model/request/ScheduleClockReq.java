package com.summ.nanny.model.request;

import com.summ.nanny.utils.StringUtil;

public class ScheduleClockReq {
    private Integer scheduleId;
    private Double clockLatitude;
    private Double clockLongitude;

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Double getClockLatitude() {
        return clockLatitude;
    }

    public void setClockLatitude(Double clockLatitude) {
        this.clockLatitude = clockLatitude;
    }

    public Double getClockLongitude() {
        return clockLongitude;
    }

    public void setClockLongitude(Double clockLongitude) {
        this.clockLongitude = clockLongitude;
    }
}
