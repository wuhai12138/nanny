package com.summ.nanny.controller.order;


import com.summ.nanny.controller.basic.AutoMapperController;
import com.summ.nanny.model.JOrderSchedule;
import com.summ.nanny.model.JScheduleChangeApply;
import com.summ.nanny.model.request.ServiceWeeksTimeReq;
import com.summ.nanny.model.response.ScheduleChangeApplyRes;
import com.summ.nanny.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Api(tags = "日程调单模块")
@RestController
@RequestMapping(value = "/order/schedule", method = RequestMethod.POST)
public class ScheduleChangeApplyController extends AutoMapperController {


    @ApiOperation(value = "查找历史调单申请", notes = "customerId")
    @RequestMapping(value = "/change/find")
    public Object find(@RequestBody JScheduleChangeApply jScheduleChangeApply) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功", ResponseUtil.List2Map(jScheduleChangeApplyMapper.getChangeByNanny(jScheduleChangeApply.getNannyId())));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "err", null);
        }
    }

    @ApiOperation(value = "拒绝调单申请", notes = "applyId nannyReason")
    @RequestMapping(value = "/change/refuse")
    public Object refuse(@RequestBody JScheduleChangeApply jScheduleChangeApply) {
        try {
            jScheduleChangeApply.setApplyStatus(292);
            jScheduleChangeApply.setModifyDate(new Date());
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功", jScheduleChangeApplyMapper.updateById(jScheduleChangeApply));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "err", null);
        }
    }

    @ApiOperation(value = "验证调单申请", notes = "applyId,customerId,scheduleId,nannyId,applyType调单申请类型查字典表,scheduleDate变更后的日程日期,startTime变更后开始时间id,endTime变更后结束时间id,customerReason变更原因")
    @RequestMapping(value = "/change/sign")
    public Object sign(@RequestBody JScheduleChangeApply jScheduleChangeApply) {
        try {
            //验证订单时间和服务师时间是否冲突
            ServiceWeeksTimeReq serviceWeeksTimeReq = new ServiceWeeksTimeReq();
            serviceWeeksTimeReq.setNannyId(jScheduleChangeApply.getNannyId());
            serviceWeeksTimeReq.setWeekday(DateUtil.dateAndday(jScheduleChangeApply.getScheduleDate()));
            serviceWeeksTimeReq.setStartId(jScheduleChangeApply.getStartTime());
            serviceWeeksTimeReq.setEndId(jScheduleChangeApply.getEndTime());
            List<String> weekList = new ArrayList<String>();
            serviceWeeksTimeReq.setWeekList(weekList);
            serviceWeeksTimeReq.setStartDate(jScheduleChangeApply.getScheduleDate());
            serviceWeeksTimeReq.setEndDate(new Date(jScheduleChangeApply.getScheduleDate().getTime() + (24 * 3600 * 1000) - 1));
            serviceWeeksTimeReq.setScheduleId(jScheduleChangeApply.getScheduleId());

            /**换算开始结束时间*/
            Map map1 = JsonUtil.Obj2Map(serviceWeeksTimeReq);
            map1.put("startDate", new Date((Long) map1.get("startDate")));
            map1.put("endDate", new Date((Long) map1.get("endDate")));

            //判断该服务师的日程是否符合订单时间要求
            List<JOrderSchedule> signNannySchedule = jOrderScheduleMapper.signNannySchedule(map1);
            if (signNannySchedule.size() > 0) {
                return new ModelRes(ModelRes.Status.FAILED, "服务师日程占用 ! !", ResponseUtil.List2Map(signNannySchedule));
            }
            return new ModelRes(ModelRes.Status.SUCCESS, "服务师时间空闲", jScheduleChangeApply);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "err", null);
        }
    }

    @ApiOperation(value = "调单", notes = "applyId,customerId,scheduleId,nannyId,applyType调单申请类型查字典表,scheduleDate变更后的日程日期,startTime变更后开始时间id,endTime变更后结束时间id,customerReason变更原因")
    @RequestMapping(value = "/change/confirm")
    public Object confirm(@RequestBody JScheduleChangeApply jScheduleChangeApply) {
        try {

            jScheduleChangeApply.setApplyStatus(293);
            jScheduleChangeApply.setModifyDate(new Date());

            JOrderSchedule jOrderSchedule = new JOrderSchedule();
            jOrderSchedule.setScheduleId(jScheduleChangeApply.getScheduleId());
            switch (jScheduleChangeApply.getApplyType()) {
                /**更改订单时间*/
                case 294:
                    jOrderSchedule.setScheduleStatus(152);
                    jOrderSchedule.setScheduleDate(jScheduleChangeApply.getScheduleDate());
                    jOrderSchedule.setStartTime(jScheduleChangeApply.getStartTime());
                    jOrderSchedule.setEndTime(jScheduleChangeApply.getEndTime());
                    jOrderSchedule.setStartTimeValue(NannyWorkTimeUtil.id2Time(jScheduleChangeApply.getStartTime()));
                    jOrderSchedule.setEndTimeValue(NannyWorkTimeUtil.id2Time(jScheduleChangeApply.getEndTime()));
                    jOrderSchedule.setTimeValue(NannyWorkTimeUtil.getTimeListValue(jScheduleChangeApply.getStartTime(), jScheduleChangeApply.getEndTime()));
                    jOrderSchedule.setWeekday(DateUtil.dateAndday(jScheduleChangeApply.getScheduleDate()));

                    jScheduleChangeApplyMapper.updateById(jScheduleChangeApply);
                    jOrderScheduleMapper.updateById(jOrderSchedule);
                    return new ModelRes(ModelRes.Status.SUCCESS, "操作成功", jScheduleChangeApply);
                default:
                    jOrderSchedule.setScheduleStatus(155);
                    jScheduleChangeApplyMapper.updateById(jScheduleChangeApply);
                    jOrderScheduleMapper.updateById(jOrderSchedule);
                    return new ModelRes(ModelRes.Status.SUCCESS, "操作成功", jScheduleChangeApply);

            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "err", null);
        }
    }
}
