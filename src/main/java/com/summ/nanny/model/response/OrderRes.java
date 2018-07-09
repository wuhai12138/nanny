package com.summ.nanny.model.response;

public class OrderRes {
    private Integer cancel;
    private Integer finished;
    private Integer working;
    private Integer changing;

    public Integer getChanging() {
        return changing;
    }

    public void setChanging(Integer changing) {
        this.changing = changing;
    }

    public Integer getCancel() {
        return cancel;
    }

    public void setCancel(Integer cancel) {
        this.cancel = cancel;
    }

    public Integer getFinished() {
        return finished;
    }

    public void setFinished(Integer finished) {
        this.finished = finished;
    }

    public Integer getWorking() {
        return working;
    }

    public void setWorking(Integer working) {
        this.working = working;
    }
}
