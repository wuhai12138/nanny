package com.summ.nanny.controller.order;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.summ.nanny.controller.basic.AutoMapperController;
import com.summ.nanny.model.JScheduleChangeApply;
import com.summ.nanny.model.request.ScheduleReq;
import com.summ.nanny.model.response.OrderRes;
import com.summ.nanny.model.response.ScheduleRes;
import com.summ.nanny.utils.ModelRes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "订单模块")
@RestController
@RequestMapping("/order")
public class OrderController extends AutoMapperController {

    @ApiOperation(value = "获取订单概括信息", response = ScheduleRes.class)
    @RequestMapping(value = "/count", method = RequestMethod.POST)
    public Object countOrder(@RequestBody ScheduleReq scheduleReq) {
        try {
            OrderRes orderRes = jOrderScheduleMapper.getOrderCount(scheduleReq.getNannyId());
            orderRes.setChanging(jScheduleChangeApplyMapper.selectCount(new Wrapper<JScheduleChangeApply>() {
                @Override
                public String getSqlSegment() {
                    return "where isDel=16 and nannyId="+scheduleReq.getNannyId();
                }
            }));
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功", orderRes);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "err", null);
        }
    }



}