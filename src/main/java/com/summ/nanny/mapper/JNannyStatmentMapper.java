package com.summ.nanny.mapper;

import com.summ.nanny.model.JNannyStatment;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.nanny.model.request.NannyStatmentDetailReq;
import com.summ.nanny.model.response.NannyStatmentDetailRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author summ
 * @since 2018-05-21
 */
public interface JNannyStatmentMapper extends BaseMapper<JNannyStatment> {

    List<NannyStatmentDetailRes> getNannyStatmentDetail(@Param("nannyStatmentDetailReq") NannyStatmentDetailReq nannyStatmentDetailReq);


}
