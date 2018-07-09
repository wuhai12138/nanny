package com.summ.nanny.controller.nanny;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.summ.nanny.Consts;
import com.summ.nanny.controller.basic.AutoMapperController;
import com.summ.nanny.mapper.JNannyDianPingMapper;
import com.summ.nanny.model.JNannyDianPing;
import com.summ.nanny.model.JNannyScheduleFeedback;
import com.summ.nanny.model.JNannyWorkTime;
import com.summ.nanny.model.JOrderSchedule;
import com.summ.nanny.model.request.NannyDianPingReq;
import com.summ.nanny.model.request.NannyReq;
import com.summ.nanny.model.response.NannyInfoRes;
import com.summ.nanny.model.response.TimeAndWeekRes;
import com.summ.nanny.utils.ModelRes;
import com.summ.nanny.utils.NannyWorkTimeUtil;
import com.summ.nanny.utils.ResponseUtil;
import com.summ.nanny.utils.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Api(tags = "服务师基本信息模块")
@RestController
@RequestMapping(value = "/nanny", method = RequestMethod.POST)
public class NannyController extends AutoMapperController {

    /**
     * @return
     */
    @ApiOperation(value = "获取基本信息", notes = "根据服务师id获取基本信息:nannyId")
    @ResponseBody
    @RequestMapping("/find")
    public Object findNanny(@RequestBody Map mapRep) {
        try {
            Integer nannyId = (Integer) mapRep.get("nannyId");
            if (StringUtil.isBlank(String.valueOf(nannyId))){
                return new ModelRes(ModelRes.Status.FAILED, "未传nannyId", null);
            }
            NannyInfoRes nannyInfoRes = jNannyInfoMapper.getNannyInfoById(nannyId);
            List<TimeAndWeekRes> nannyWorkTimes = new ArrayList<>();

            Map map = new HashMap();
            map.put("nannyId",nannyId);
            List<JNannyWorkTime> jNannyWorkTimeList = jNannyWorkTimeMapper.selectByMap(map);
            StringBuffer worktime = new StringBuffer();
            if (jNannyWorkTimeList.size()==0){
                worktime.append("暂无工时");
            }else {
                JNannyWorkTime jNannyWorkTime = jNannyWorkTimeList.get(0);
                Long timeValue=0L;
                if (jNannyWorkTime.getMonday()!=0){
                    worktime.append("周一;");
                    timeValue=jNannyWorkTime.getMonday();
                    List<TimeAndWeekRes> timeAndWeekResList = NannyWorkTimeUtil.value2TimeAndWeekRes(timeValue,"周一");
                    nannyWorkTimes.addAll(timeAndWeekResList);
                }
                if (jNannyWorkTime.getTuesday()!=0){
                    worktime.append("周二;");
                    timeValue=jNannyWorkTime.getTuesday();
                    List<TimeAndWeekRes> timeAndWeekResList = NannyWorkTimeUtil.value2TimeAndWeekRes(timeValue,"周二");
                    nannyWorkTimes.addAll(timeAndWeekResList);
                }
                if (jNannyWorkTime.getWednesday()!=0){
                    worktime.append("周三;");
                    timeValue=jNannyWorkTime.getWednesday();
                    List<TimeAndWeekRes> timeAndWeekResList = NannyWorkTimeUtil.value2TimeAndWeekRes(timeValue,"周三");
                    nannyWorkTimes.addAll(timeAndWeekResList);
                }
                if (jNannyWorkTime.getThursday()!=0){
                    worktime.append("周四;");
                    timeValue=jNannyWorkTime.getThursday();
                    List<TimeAndWeekRes> timeAndWeekResList = NannyWorkTimeUtil.value2TimeAndWeekRes(timeValue,"周四");
                    nannyWorkTimes.addAll(timeAndWeekResList);
                }
                if (jNannyWorkTime.getFriday()!=0){
                    worktime.append("周五;");
                    timeValue=jNannyWorkTime.getFriday();
                    List<TimeAndWeekRes> timeAndWeekResList = NannyWorkTimeUtil.value2TimeAndWeekRes(timeValue,"周五");
                    nannyWorkTimes.addAll(timeAndWeekResList);
                }
                if (jNannyWorkTime.getSaturday()!=0){
                    worktime.append("周六;");
                    timeValue=jNannyWorkTime.getSaturday();
                    List<TimeAndWeekRes> timeAndWeekResList = NannyWorkTimeUtil.value2TimeAndWeekRes(timeValue,"周六");
                    nannyWorkTimes.addAll(timeAndWeekResList);
                }
                if (jNannyWorkTime.getSunday()!=0){
                    worktime.append("周日;");
                    timeValue=jNannyWorkTime.getSunday();
                    List<TimeAndWeekRes> timeAndWeekResList = NannyWorkTimeUtil.value2TimeAndWeekRes(timeValue,"周日");
                    nannyWorkTimes.addAll(timeAndWeekResList);
                }

            }
            nannyInfoRes.setWorkTime(worktime.toString());
            nannyInfoRes.setNannyAvatar(Consts.nannyAvatarUrlRes+nannyInfoRes.getNannyAvatar());


            nannyInfoRes.setShopList(jNannyShopMapper.getShopByNannyId(nannyInfoRes.getNannyId()));
            nannyInfoRes.setJobLevelList(jDictInfoMapper.getJobLevelByNannyId(nannyInfoRes.getNannyId()));
            nannyInfoRes.setjNannyCertificateList(jNannyInfoMapper.getCerByNannyId(nannyInfoRes.getNannyId()));
            nannyInfoRes.setjNannyEmergencyContactList(jNannyInfoMapper.getContactByNannyId(nannyInfoRes.getNannyId()));
            nannyInfoRes.setNannyWorkTimeList(nannyWorkTimes);


            return new ModelRes(ModelRes.Status.SUCCESS, "绑定成功", nannyInfoRes);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ApiOperation(value = "获取服务师列表", notes = "根据筛选条件获取列表")
    @ResponseBody
    @RequestMapping("/list")
    public Object findList(@RequestBody NannyReq nannyReq){
        nannyReq.setPage(nannyReq.getSize()*(nannyReq.getPage()-1));
        try {
            return new ModelRes(ModelRes.Status.SUCCESS, "查询成功", ResponseUtil.List2Map(jNannyInfoMapper.getNannyList(nannyReq),jNannyInfoMapper.getNannyListCount(nannyReq)));
        }catch (Exception e){
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ApiOperation(value = "提交大众点评客户评论",notes = "请求参数: nannyId photo 表单上传图片")
    @RequestMapping(value = "/dianping/insert", method = RequestMethod.POST)
    public Object insertDianPing(@RequestParam("file") MultipartFile file, @RequestParam("nannyId") Integer nannyId) throws IOException {


        System.out.println("file upload");
        String filePath = null;
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                Pattern pattern = Pattern.compile("\\.[a-z|A-Z]+$");
                Matcher matcher = pattern.matcher(file.getOriginalFilename());
                String fileName = "NDP"+System.currentTimeMillis()+".jpg";
                if (matcher.find()) {
                    // 文件保存路径
//                     filePath = "C:\\Users\\jygj_7500\\Desktop\\upload\\" + System.currentTimeMillis();
                    filePath = Consts.nannyDianPingUrl + fileName;
                } else {
                    return new ModelRes(ModelRes.Status.FAILED, "上传失败 !");
                }

                // 转存文件
                file.transferTo(new File(filePath));

                JNannyDianPing jNannyDianPing = new JNannyDianPing();
                jNannyDianPing.setNannyId(nannyId);
                jNannyDianPing.setPhote(fileName);
                jNannyDianPingMapper.insert(jNannyDianPing);
                return new ModelRes(ModelRes.Status.SUCCESS, "上传成功 !");
            } catch (Exception e) {
                e.printStackTrace();
                return new ModelRes(ModelRes.Status.ERROR, "server err !");
            }
        } else {
            return new ModelRes(ModelRes.Status.FAILED, "文件为空 !");
        }
    }
    @ApiOperation(value = "查找大众点评客户评论",notes = "请求参数: nannyId page size")
    @RequestMapping(value = "/dianping/find", method = RequestMethod.POST)
    public Object findDianPing(@RequestBody NannyDianPingReq nannyDianPingReq) throws IOException {
            try {
                nannyDianPingReq.setPage((nannyDianPingReq.getPage()-1)*nannyDianPingReq.getSize());
                List<JNannyDianPing> jNannyDianPingList = jNannyDianPingMapper.selectList(new Wrapper<JNannyDianPing>() {
                    @Override
                    public String getSqlSegment() {
                        return "where isDel=16 and nannyId="+nannyDianPingReq.getNannyId()+" order by createDate desc limit "+nannyDianPingReq.getPage()+","+nannyDianPingReq.getSize();
                    }
                });
                for (JNannyDianPing jNannyDianPing:jNannyDianPingList){
                    jNannyDianPing.setPhote(Consts.nannyDianPingUrlRes+jNannyDianPing.getPhote());
                }
                return new ModelRes(ModelRes.Status.SUCCESS, "查询成功 !",ResponseUtil.List2Map(jNannyDianPingList));
            } catch (Exception e) {
                e.printStackTrace();
                return new ModelRes(ModelRes.Status.ERROR, "server err !");
            }
    }
    @ApiOperation(value = "删除大众点评客户评论",notes = "请求参数: dianPingId")
    @RequestMapping(value = "/dianping/delete", method = RequestMethod.POST)
    public Object findDianPing(@RequestBody JNannyDianPing jNannyDianPing) throws IOException {
            try {
                return new ModelRes(ModelRes.Status.SUCCESS, "查询成功 !",jNannyDianPingMapper.deleteById(jNannyDianPing.getDianPingId()));
            } catch (Exception e) {
                e.printStackTrace();
                return new ModelRes(ModelRes.Status.ERROR, "server err !");
            }
    }

}
