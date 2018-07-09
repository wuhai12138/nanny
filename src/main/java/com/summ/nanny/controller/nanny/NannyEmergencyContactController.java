package com.summ.nanny.controller.nanny;


import com.summ.nanny.controller.basic.AutoMapperController;
import com.summ.nanny.model.JNannyEmergencyContact;
import com.summ.nanny.utils.ModelRes;
import com.summ.nanny.utils.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/***
 * 服务师紧急联系人
 */
@Api(tags = "服务师紧急联系人模块")
@RestController
@RequestMapping(value = "nanny/emergency", method = RequestMethod.POST)
public class NannyEmergencyContactController extends AutoMapperController {

    @ApiOperation(value = "添加紧急联系人", notes = "添加紧急联系人")
    @ResponseBody
    @RequestMapping("/insert")
    public Object insert(@RequestBody JNannyEmergencyContact jNannyEmergencyContact) {
        try {

            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jNannyEmergencyContactMapper.insert(jNannyEmergencyContact));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ApiOperation(value = "更新紧急联系人", notes = "更新紧急联系人")
    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody JNannyEmergencyContact jNannyEmergencyContact) {
        try {

            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jNannyEmergencyContactMapper.updateById(jNannyEmergencyContact));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ApiOperation(value = "查找紧急联系人", notes = "根据服务师id查找紧急联系人 nannyId")
    @ResponseBody
    @RequestMapping("/find")
    public Object find(@RequestBody JNannyEmergencyContact jNannyEmergencyContact) {
        try {
            Map map = new HashMap();
            map.put("nannyId",jNannyEmergencyContact.getNannyId());
            return new ModelRes(ModelRes.Status.SUCCESS, "操作功 !", ResponseUtil.List2Map(jNannyEmergencyContactMapper.selectByMap(map)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ApiOperation(value = "删除紧急联系人", notes = "根据emergencyContactId删除紧急联系人")
    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(@RequestBody JNannyEmergencyContact jNannyEmergencyContact) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS, "操作功 !", jNannyEmergencyContactMapper.deleteById(jNannyEmergencyContact.getEmergencyContactId()));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

}
