package com.summ.nanny.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.nanny.model.JNannyCertificate;
import com.summ.nanny.model.response.NannyCertificateRes;

import java.util.List;

/**
 *
 * JNannyCertificate 表数据库控制层接口
 *
 */
public interface JNannyCertificateMapper extends BaseMapper<JNannyCertificate> {

    List<NannyCertificateRes> getNannyCertificateByNannyId(Integer nannyId);
}