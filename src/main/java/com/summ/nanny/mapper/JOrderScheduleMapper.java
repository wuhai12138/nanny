package com.summ.nanny.mapper;

import com.summ.nanny.model.JOrderSchedule;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.nanny.model.request.NannyRankingsReq;
import com.summ.nanny.model.request.ScheduleReq;
import com.summ.nanny.model.response.NannyRankingsRes;
import com.summ.nanny.model.response.OrderRes;
import com.summ.nanny.model.response.ScheduleRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author summ
 * @since 2018-05-21
 */
public interface JOrderScheduleMapper extends BaseMapper<JOrderSchedule> {

    List<ScheduleRes> getOrderSchedule(@Param("scheduleReq")ScheduleReq scheduleReq);

    ScheduleRes getScheduleByScheduleId(Integer scheduleId);

    List<NannyRankingsRes> getNannyRankingsWorkTimes(@Param("nannyRankingsReq") NannyRankingsReq nannyRankingsReq);
    Integer getCustomerCount(@Param("nannyRankingsReq") NannyRankingsReq nannyRankingsReq);
    List<NannyRankingsRes> getNannyRankingsPraises(@Param("nannyRankingsReq") NannyRankingsReq nannyRankingsReq);

    OrderRes getOrderCount(Integer nannyId);

    List<JOrderSchedule> signNannySchedule(@Param("map") Map map);
}
