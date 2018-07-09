package com.summ.nanny.controller.nanny;


import com.summ.nanny.controller.basic.AutoMapperController;
import com.summ.nanny.model.JNannyShop;
import com.summ.nanny.utils.ModelRes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(tags = "服务师门店信息模块")
@RestController
@RequestMapping(value = "/nanny/shop", method = RequestMethod.POST)
public class NannyShopController extends AutoMapperController {

    /**
     * @return
     */
    @ApiOperation(value = "给服务师绑定门店", notes = "")
    @ResponseBody
    @RequestMapping("/insert")
    public Object getBindCustomer(@RequestBody JNannyShop jNannyShop) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS, "绑定成功", jNannyShopMapper.insert(jNannyShop));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

}
