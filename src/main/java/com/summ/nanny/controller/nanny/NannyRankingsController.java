package com.summ.nanny.controller.nanny;

import com.summ.nanny.controller.basic.AutoMapperController;
import com.summ.nanny.model.request.NannyRankingsReq;
import com.summ.nanny.model.response.NannyRankingsRes;
import com.summ.nanny.utils.ModelRes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "服务师排行榜")
@RestController
@RequestMapping(value = "/nanny/rankings", method = RequestMethod.POST)
public class NannyRankingsController extends AutoMapperController {

    @ApiOperation(value = "查找服务师工时排行榜", notes = "")
    @ResponseBody
    @RequestMapping("/worktimes")
    public Object worktimes(@RequestBody NannyRankingsReq nannyRankingsReq) {
        try {
            Map map = new HashMap();

            nannyRankingsReq.setPage((nannyRankingsReq.getPage() - 1) * nannyRankingsReq.getSize());
            List<NannyRankingsRes> nannyRankingsResList = jOrderScheduleMapper.getNannyRankingsWorkTimes(nannyRankingsReq);
            Integer customerCount = jOrderScheduleMapper.getCustomerCount(nannyRankingsReq);

            Integer rankings = 0;
            if (nannyRankingsResList.size() > 0) {
                nannyRankingsResList.get(0).setRankings(1);
                if (nannyRankingsResList.get(0).getNannyId().equals(nannyRankingsReq.getNannyId())){
                    map.put("rankings",nannyRankingsResList.get(0).getRankings());
                    map.put("workTimes",nannyRankingsResList.get(0).getWorkTimes()/2);
                    map.put("customerCount",customerCount);
                }
                System.out.println(nannyRankingsResList.get(0).getRankings()+"----"+nannyRankingsResList.get(0).getWorkTimes());
                for (int i = 1; i < nannyRankingsResList.size(); i++) {
                    if (nannyRankingsResList.get(i - 1).getWorkTimes().equals(nannyRankingsResList.get(i).getWorkTimes())) {
                        nannyRankingsResList.get(i).setRankings(rankings);
                        System.out.println(nannyRankingsResList.get(i).getRankings()+"----"+nannyRankingsResList.get(i).getWorkTimes());
                    } else {
                        rankings = i+1;
                        nannyRankingsResList.get(i).setRankings(rankings);
                        System.out.println(nannyRankingsResList.get(i).getRankings()+"----"+nannyRankingsResList.get(i).getWorkTimes());
                    }

                    if (nannyRankingsResList.get(i).getNannyId().equals(nannyRankingsReq.getNannyId())){
                        map.put("rankings",nannyRankingsResList.get(i).getRankings());
                        map.put("workTimes",nannyRankingsResList.get(i).getWorkTimes()/2);
                        map.put("customerCount",customerCount);
                    }
                }
            }
            map.put("list",nannyRankingsResList);

            return new ModelRes(ModelRes.Status.SUCCESS, "", map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }
    @ApiOperation(value = "查找服务师好评排行榜", notes = "")
    @ResponseBody
    @RequestMapping("/praises")
    public Object praises(@RequestBody NannyRankingsReq nannyRankingsReq) {
        try {
            Map map = new HashMap();

            nannyRankingsReq.setPage((nannyRankingsReq.getPage() - 1) * nannyRankingsReq.getSize());
            List<NannyRankingsRes> nannyRankingsResList = jOrderScheduleMapper.getNannyRankingsPraises(nannyRankingsReq);
            Integer customerCount = jOrderScheduleMapper.getCustomerCount(nannyRankingsReq);
            Integer rankings = 0;
            map.put("rankings",0);
            map.put("praises",0);
            map.put("customerCount",0);
            if (nannyRankingsResList.size() > 0) {
                nannyRankingsResList.get(0).setRankings(1);
                if (nannyRankingsResList.get(0).getNannyId().equals(nannyRankingsReq.getNannyId())){
                    map.put("rankings",nannyRankingsResList.get(0).getRankings());
                    map.put("praises",nannyRankingsResList.get(0).getPraises());
                    map.put("customerCount",customerCount);
                }
                System.out.println(nannyRankingsResList.get(0).getRankings()+"----"+nannyRankingsResList.get(0).getPraises());
                for (int i = 1; i < nannyRankingsResList.size(); i++) {
                    if (nannyRankingsResList.get(i - 1).getPraises().equals(nannyRankingsResList.get(i).getPraises())) {
                        nannyRankingsResList.get(i).setRankings(rankings);
                        System.out.println(nannyRankingsResList.get(i).getRankings()+"----"+nannyRankingsResList.get(i).getPraises());
                    } else {
                        rankings = i+1;
                        nannyRankingsResList.get(i).setRankings(rankings);
                        System.out.println(nannyRankingsResList.get(i).getRankings()+"----"+nannyRankingsResList.get(i).getPraises());
                    }

                    if (nannyRankingsResList.get(i).getNannyId().equals(nannyRankingsReq.getNannyId())){
                        map.put("rankings",nannyRankingsResList.get(i).getRankings());
                        map.put("praises",nannyRankingsResList.get(i).getPraises());
                        map.put("customerCount",customerCount);
                    }
                }
            }
            map.put("list",nannyRankingsResList);

            return new ModelRes(ModelRes.Status.SUCCESS, "", map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }
}
