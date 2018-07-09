package com.summ.nanny.controller.basic;

import com.summ.nanny.utils.ModelRes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "基础信息模块")
@RestController
@RequestMapping(value = "/basic", method = RequestMethod.POST)
public class BasicController extends AutoMapperController{
    /**
     * 获取指定类型有哪些信息
     * @param typeCode
     * @return
     */
    @ApiOperation(value = "查找字典表", notes = "参数 typeCode")
    @ResponseBody
    @RequestMapping("/dict/find")
    public Object getDict(@RequestBody Map typeCode) {
        try {
            Map map = new HashMap();
            if (typeCode.get("info")!=null){
                map.put("list", jDictInfoMapper.getList((Integer) typeCode.get("typeCode"), (String) typeCode.get("info")));
            }else {
                map.put("list", jDictInfoMapper.getList((Integer) typeCode.get("typeCode"),""));
            }
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

}
