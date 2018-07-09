package com.summ.nanny.mapper;

import com.summ.nanny.model.JNannyCertificate;
import com.summ.nanny.model.JNannyEmergencyContact;
import com.summ.nanny.model.JNannyInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.nanny.model.request.NannyReq;
import com.summ.nanny.model.response.NannyInfoRes;
import io.swagger.models.auth.In;
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
public interface JNannyInfoMapper extends BaseMapper<JNannyInfo> {

    NannyInfoRes getNannyInfoById(Integer nannyId);

    List<NannyInfoRes> getNannyList(@Param("nannyReq") NannyReq nannyReq);
    Integer getNannyListCount(@Param("nannyReq") NannyReq nannyReq);

    List<JNannyCertificate> getCerByNannyId(Integer nannyId);
    List<JNannyEmergencyContact> getContactByNannyId(Integer nannyId);
}
