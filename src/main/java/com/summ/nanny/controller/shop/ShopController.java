package com.summ.nanny.controller.shop;


import com.baomidou.mybatisplus.mapper.Wrapper;
import com.summ.nanny.controller.basic.AutoMapperController;
import com.summ.nanny.model.JShop;
import com.summ.nanny.utils.ModelRes;
import com.summ.nanny.utils.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.text.Collator;
import java.util.*;

/**
 * 门店增删改查
 * @author jygj_7500
 */
@Api(tags = "门店信息模块")
@RestController
@RequestMapping(value = "/shop",method = RequestMethod.POST)
public class ShopController extends AutoMapperController {

    @ApiOperation(value = "查询所有门店")
    @ResponseBody
    @RequestMapping("/all")
    public Object findAll(@RequestBody JShop jShop) {
        try {
            Map map = new HashMap();
            map.put("isDel",16);
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jShopMapper.selectByMap(map));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ApiOperation(value = "根据市查询门店")
    @ResponseBody
    @RequestMapping("/find")
    public Object find(@RequestBody JShop jShop) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", ResponseUtil.List2Map(jShopMapper.selectList(new Wrapper<JShop>() {
                @Override
                public String getSqlSegment() {
                    return "where cityId="+jShop.getCityId()+"and isDel=16";
                }
            })));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 根据地址的经纬度查看该地址与各门店的距离
     * @param map
     * @return
     */
//    @ApiImplicitParam(name = "houseAddress",value = "房产详细地址")
//    @ResponseBody
//    @RequestMapping("/distance")
//    public Object distance(@RequestBody Map<String,String> map) {
//        try {
//            Map<String, Double> baiduMap = BaiduAPIUtil.getLngAndLat("上海市" + map.get("houseAddress"));
//            if (baiduMap == null) {
//                return new ModelRes(ModelRes.Status.FAILED, "获取不到该地址，请重新输入 !");
//            }
//            List<JShop> shopList = jShopMapper.getAllShop();
//            List<JShop> shopListRes = new ArrayList<JShop>();
//            for (JShop jShop : shopList) {
//                double distance = BaiduAPIUtil.getDistance(baiduMap.get("lng"), baiduMap.get("lat"), jShop.getLongitude(), jShop.getLatitude());
//                jShop.setDistance(distance);
//                shopListRes.add(jShop);
//            }
//
//            Collections.sort(shopListRes, new Comparator<JShop>() {
//                @Override
//                public int compare(JShop o1, JShop o2) {
//                    Collator collator = Collator.getInstance();
//                    return collator.compare(String.valueOf(o2.getDistance()), String.valueOf(o1.getDistance()));
//                }
//            });
//
//            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", ResponseUtil.List2Map(shopListRes));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ModelRes(ModelRes.Status.ERROR, "server err !");
//        }
//    }
}
