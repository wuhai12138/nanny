package com.summ.nanny.controller.nanny;

import com.summ.nanny.controller.basic.AutoMapperController;
import com.summ.nanny.model.request.NannyStatmentDetailReq;
import com.summ.nanny.model.response.NannySalaryRes;
import com.summ.nanny.model.response.NannyStatmentDetailRes;
import com.summ.nanny.utils.ModelRes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "服务师对账单模块")
@RestController
@RequestMapping(value = "/nanny/statment", method = RequestMethod.POST)
public class NannyStatmentController extends AutoMapperController {

    /**
     * 服务师对账单明细
     *
     * @param nannyStatmentDetailReq
     * @return
     */
    @ApiOperation(value = "服务师对账单明细")
    @ResponseBody
    @RequestMapping("/find/detail")
    public Object findDetail(@RequestBody NannyStatmentDetailReq nannyStatmentDetailReq) {
        try {
            List<NannyStatmentDetailRes> nannyStatmentDetailResList = jNannyStatmentMapper.getNannyStatmentDetail(nannyStatmentDetailReq);
            NannySalaryRes nannySalaryRes = new NannySalaryRes();
            nannySalaryRes.setNannyStatmentDetailResList(nannyStatmentDetailResList);
            for (NannyStatmentDetailRes nannyStatmentDetailRes : nannyStatmentDetailResList) {
                switch (nannyStatmentDetailRes.getStatmentNannyType()) {
                    case 159:
                        nannySalaryRes.setReward(nannySalaryRes.getReward().add(nannyStatmentDetailRes.getStatmentMoney()));
                        break;
                    case 160:
                        nannySalaryRes.setPunishment(nannySalaryRes.getPunishment().add(nannyStatmentDetailRes.getStatmentMoney()));
                        break;
                    default:
                        nannySalaryRes.setTotalSalary(nannySalaryRes.getTotalSalary().add(nannyStatmentDetailRes.getStatmentMoney()));
                        break;
                }
            }
            nannySalaryRes.setRealSalary(nannySalaryRes.getTotalSalary().subtract(nannySalaryRes.getPunishment()).add(nannySalaryRes.getReward()));

            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", nannySalaryRes);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }
}
