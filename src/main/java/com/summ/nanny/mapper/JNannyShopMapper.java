package com.summ.nanny.mapper;

import com.summ.nanny.model.JNannyShop;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.nanny.model.JShop;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author summ
 * @since 2018-05-21
 */
public interface JNannyShopMapper extends BaseMapper<JNannyShop> {
    List<JShop> getShopByNannyId(Integer nannyId);
}
