package com.summ.nanny.mapper;

import com.summ.nanny.model.JDictInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.nanny.model.JShop;
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
public interface JDictInfoMapper extends BaseMapper<JDictInfo> {

    List<JDictInfo> getJobLevelByNannyId(Integer nannyId);

    List<JDictInfo> getList(@Param("typeCode") int typeCode, @Param("info") String info);
}
