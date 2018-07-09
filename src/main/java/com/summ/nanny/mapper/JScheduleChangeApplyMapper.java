package com.summ.nanny.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.nanny.model.JScheduleChangeApply;
import com.summ.nanny.model.response.ScheduleChangeApplyRes;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author summ
 * @since 2018-06-15
 */
public interface JScheduleChangeApplyMapper extends BaseMapper<JScheduleChangeApply> {

    List<ScheduleChangeApplyRes> getChangeByCustomer(Integer customer);
    List<ScheduleChangeApplyRes> getChangeByNanny(Integer nanny);
}
