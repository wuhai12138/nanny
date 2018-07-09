package com.summ.nanny.controller.order;

import com.summ.nanny.Consts;
import com.summ.nanny.controller.basic.AutoMapperController;
import com.summ.nanny.model.*;
import com.summ.nanny.model.request.ScheduleClockReq;
import com.summ.nanny.model.request.ScheduleReq;
import com.summ.nanny.model.response.ScheduleRes;
import com.summ.nanny.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Api(tags = "订单日程模块")
@RestController
@RequestMapping("/order/schedule")
public class OrderScheduleController extends AutoMapperController {

    @ApiOperation(value = "获取订单日程", response = ScheduleRes.class)
    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public Object getOrderSchedule(@RequestBody ScheduleReq scheduleReq) {
        try {
            scheduleReq.setPage(scheduleReq.getSize() * (scheduleReq.getPage() - 1));

            System.out.println(scheduleReq.getStartDate());
            System.out.println(scheduleReq.getEndDate());

            List<ScheduleRes> scheduleResList = jOrderScheduleMapper.getOrderSchedule(scheduleReq);

            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功", ResponseUtil.List2Map(scheduleResList));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "err", null);
        }
    }


    @ApiOperation(value = "日程提交反馈和图片")
    @RequestMapping(value = "/feedback", method = RequestMethod.POST)
    public Object feedback(@RequestParam("file") MultipartFile file, @RequestParam("scheduleId") Integer scheduleId,@RequestParam("nannyId") Integer nannyId,@RequestParam("remark") String remark) throws IOException {


        System.out.println("file upload");
        String filePath = null;
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                Pattern pattern = Pattern.compile("\\.[a-z|A-Z]+$");
                Matcher matcher = pattern.matcher(file.getOriginalFilename());
                String fileName = "NSF"+System.currentTimeMillis()+".jpg";
                if (matcher.find()) {
                    // 文件保存路径
//                     filePath = "C:\\Users\\jygj_7500\\Desktop\\upload\\" + System.currentTimeMillis();
                    filePath = Consts.nannyScheduleFeedbackUrl + fileName;
                } else {
                    return new ModelRes(ModelRes.Status.FAILED, "上传失败 !");
                }

                // 转存文件
                file.transferTo(new File(filePath));

                JOrderSchedule jOrderSchedule = jOrderScheduleMapper.selectById(scheduleId);
                JNannyScheduleFeedback jNannyScheduleFeedback = new JNannyScheduleFeedback();
                jNannyScheduleFeedback.setNannyId(nannyId);
                jNannyScheduleFeedback.setScheduleId(jOrderSchedule.getScheduleId());
                jNannyScheduleFeedback.setCustomerId(jOrderSchedule.getCustomerId());
                jNannyScheduleFeedback.setPhote(fileName);
                jNannyScheduleFeedback.setRemark(remark);
                jNannyScheduleFeedbackMapper.insert(jNannyScheduleFeedback);
                return new ModelRes(ModelRes.Status.SUCCESS, "上传成功 !");
            } catch (Exception e) {
                e.printStackTrace();
                return new ModelRes(ModelRes.Status.ERROR, "server err !");
            }
        } else {
            return new ModelRes(ModelRes.Status.FAILED, "文件为空 !");
        }
    }

    @ApiOperation(value = "签到日程", notes = "")
    @RequestMapping(value = "/sign", method = RequestMethod.POST)
    public Object getOrderSchedule(@RequestBody ScheduleClockReq scheduleClockReq) {
        try {
            ScheduleRes scheduleRes = jOrderScheduleMapper.getScheduleByScheduleId(scheduleClockReq.getScheduleId());

            /**给客户和服务师添加积分*/
            JCustomer jCustomer1 = jCustomerMapper.selectById(scheduleRes.getCustomerId());
            JNannyInfo jNannyInfo = jNannyInfoMapper.selectById(scheduleRes.getNannyId());
            jCustomer1.setCustomerCredit(jCustomer1.getCustomerCredit()+1);
            jNannyInfo.setNannyCredit(jNannyInfo.getNannyCredit()+1);


            /**服务时间*/
            String serviceTime = scheduleRes.getStartTimeValue() + "-" + scheduleRes.getEndTimeValue();
            Double serviceTimeLength = Double.valueOf((scheduleRes.getEndTime() - scheduleRes.getStartTime()) / 2f);
            /** orderPriceType标记订单是否为自定义价格（即手动修改的价格,1表示手动修改） */
            int orderPriceType = 1;

            /**判断签到距离*/
            String distance = TencentMapUtil.getDistance(scheduleClockReq.getClockLongitude(),scheduleClockReq.getClockLatitude(),scheduleRes.getLongitude(),scheduleRes.getLatitude());
            if (distance.equals("距离超过10公里")){
                return new ModelRes(ModelRes.Status.FAILED, "签到距离超过三百米 !", scheduleRes);
            }
            if (Double.valueOf(distance).compareTo(0.3)==1){
                return new ModelRes(ModelRes.Status.FAILED, "签到距离超过三百米 !", scheduleRes);
            }

            /***日程*/
            JOrderSchedule jOrderSchedule = new JOrderSchedule();

            switch (scheduleRes.getOrderType()) {
                case 163:
                    if (scheduleRes.getScheduleStatus() == 153) {
                        return new ModelRes(ModelRes.Status.FAILED, "有订单已签到 !", scheduleRes);
                    }
                    if (scheduleRes.getScheduleStatus() == 155) {
                        return new ModelRes(ModelRes.Status.FAILED, "有订单已取消 !", scheduleRes);
                    }
                    /**判断签到时间在不在服务时间点之后**/
                    if (DateUtil.parseStringToDate(DateUtil.parseDateToString(scheduleRes.getScheduleDate(), DateUtil.PATTERN_yyyy_MM_dd) + scheduleRes.getEndTimeValue(), DateUtil.PATTERN_yyyy_MM_HHmm).after(new Date())) {
                        return new ModelRes(ModelRes.Status.FAILED, "有订单未到签到时间，无法签到 !", scheduleRes);
                    }
                    //判断服务师工资是否为0，是则实时计算出服务师当前工资

                    if (scheduleRes.getCost().compareTo(new BigDecimal(0)) == 0) {
                        orderPriceType = 0;
                        scheduleRes.setCost(new BigDecimal(scheduleRes.getNannyCurrentPayment()).multiply(new BigDecimal(String.valueOf((scheduleRes.getEndTime() - scheduleRes.getStartTime()) / 2f))).setScale(2));
                    }
                    scheduleRes.setUnitPrice(scheduleRes.getScheduleCurrentPrice());
                    //判断日程总价是否为0，是则实时计算出当前总价A
                    if (scheduleRes.getTotalPrice().compareTo(new BigDecimal(0)) == 0) {
                        scheduleRes.setTotalPrice(scheduleRes.getScheduleCurrentPrice().multiply(new BigDecimal(String.valueOf((scheduleRes.getEndTime() - scheduleRes.getStartTime()) / 2f))).setScale(2));
                    }

                    /**客户*/
                    JCustomer jCustomer = jCustomerMapper.selectById(Long.valueOf(scheduleRes.getCustomerId()));
                    /**余额*/
                    BigDecimal customerBalance = jCustomer.getCustomerBalance();

                    if (scheduleRes.getNannyStatus() != 56) {
                        return new ModelRes(ModelRes.Status.FAILED, "服务师未培训成功", scheduleRes);
                    }
                    if (scheduleRes.getDataSignStatus() != 255) {
                        return new ModelRes(ModelRes.Status.FAILED, "服务师工作信息验证未通过！请先通过验证", scheduleRes);
                    }


                    /**新增服务师对账单*/
                    JNannyStatment jNannyStatment = new JNannyStatment(OrderUtil.generateStamentNumber(jCustomer.getCustomerId()), scheduleRes.getNannyId(),
                            scheduleRes.getScheduleId(), scheduleRes.getOrderId(),
                            scheduleRes.getShopId(), scheduleRes.getHouseId(), scheduleRes.getCustomerId(),
                            162, scheduleRes.getCost(), 163, scheduleRes.getGoodsId(), serviceTime, serviceTimeLength,
                            scheduleRes.getScheduleDate(), "", 0, scheduleRes.getNannyLevel(), new BigDecimal(scheduleRes.getNannyCurrentPayment()), orderPriceType);
                    jNannyStatmentMapper.insert(jNannyStatment);

                    /**计算客户余额*/
                    customerBalance = customerBalance.subtract(scheduleRes.getTotalPrice());

                    /**新增客户对账单*/
                    JCustomerStatment jCustomerStatment = new JCustomerStatment(OrderUtil.generateStamentNumber(jCustomer.getCustomerId()),
                            scheduleRes.getCustomerId(), scheduleRes.getGoodsId(), scheduleRes.getHouseId(),
                            scheduleRes.getOrderId(), 163, scheduleRes.getScheduleId(),
                            scheduleRes.getShopId(), serviceTime, serviceTimeLength,
                            scheduleRes.getScheduleDate(), new Date(), 39, scheduleRes.getTotalPrice(),
                            48, 0,52 , 53, customerBalance,
                            "", "");
                    jCustomerStatmentMapper.insert(jCustomerStatment);
                    /**修改客户账户余额*/
                    jCustomer.setCustomerBalance(customerBalance);
                    jCustomerMapper.updateById(jCustomer);

                    /**修改日程状态*/

                    jOrderSchedule.setClockLatitude(scheduleClockReq.getClockLatitude());
                    jOrderSchedule.setClockLongitude(scheduleClockReq.getClockLongitude());
                    jOrderSchedule.setScheduleId(scheduleClockReq.getScheduleId());
                    /**日程支付状态为已支付*/
                    jOrderSchedule.setPayStatus(158);
                    jOrderSchedule.setClockTime(new Date());
                    jOrderSchedule.setScheduleStatus(153);
                    jOrderScheduleMapper.updateById(jOrderSchedule);
                    break;
                case 164:
                    if (scheduleRes.getScheduleStatus() != 152) {
                        return new ModelRes(ModelRes.Status.FAILED, "有日程状态不是待完工", scheduleRes);
                    }
                    /**判断签到时间在不在服务时间点之后**/
                    if (DateUtil.parseStringToDate(DateUtil.parseDateToString(scheduleRes.getScheduleDate(), DateUtil.PATTERN_yyyy_MM_dd) + scheduleRes.getEndTimeValue(), DateUtil.PATTERN_yyyy_MM_HHmm).after(new Date())) {
                        return new ModelRes(ModelRes.Status.FAILED, "有订单未到签到时间，无法签到 !", scheduleRes);
                    }
                    if (scheduleRes.getDataSignStatus() != 255) {
                        return new ModelRes(ModelRes.Status.FAILED, "服务师工作信息验证未通过！请先通过验证", scheduleRes);
                    }
                    if (scheduleRes.getNannyStatus() != 56) {
                        return new ModelRes(ModelRes.Status.FAILED, "服务师未培训成功", scheduleRes);
                    }

                    /**判断是不是需要实时计算成本 0则表明需要实时计算*/
                    if (scheduleRes.getCost().compareTo(new BigDecimal(0)) == 0) {
                        orderPriceType = 0;
                        /**根据服务师星级小时工资与日程工作时长计算成本*/
                        scheduleRes.setCost(new BigDecimal(scheduleRes.getNannyCurrentPayment()).multiply(new BigDecimal(String.valueOf((scheduleRes.getEndTime() - scheduleRes.getStartTime()) / 2f))).setScale(2));
                    }

                    /**判断日程是自营还是供应商提供 1为自营*/
                    JNannyStatment jNannyStatmentTemp = new JNannyStatment(OrderUtil.generateStamentNumber(scheduleRes.getNannyId()), scheduleRes.getNannyId(),
                            scheduleRes.getScheduleId(), scheduleRes.getOrderId(),
                            scheduleRes.getShopId(), scheduleRes.getHouseId(), scheduleRes.getCustomerId(),
                            166, scheduleRes.getCost(), 164, scheduleRes.getGoodsId(), serviceTime, serviceTimeLength,
                            scheduleRes.getScheduleDate(), "", 0, scheduleRes.getNannyLevel(), new BigDecimal(scheduleRes.getNannyCurrentPayment()), orderPriceType);
                    jNannyStatmentMapper.insert(jNannyStatmentTemp);
                    jOrderSchedule.setScheduleStatus(153);
                    jOrderSchedule.setScheduleId(scheduleRes.getScheduleId());
                    jOrderSchedule.setClockTime(new Date());
                    jOrderScheduleMapper.updateById(jOrderSchedule);
                    break;
                case 165:
                    jOrderSchedule.setScheduleStatus(153);
                    jOrderSchedule.setPayStatus(158);
                    jOrderSchedule.setScheduleId(scheduleRes.getScheduleId());
                    jOrderSchedule.setClockTime(new Date());
                    jOrderScheduleMapper.updateById(jOrderSchedule);
                    break;
                case 277:
                    jOrderSchedule.setScheduleStatus(153);
                    jOrderSchedule.setPayStatus(158);
                    jOrderSchedule.setScheduleId(scheduleRes.getScheduleId());
                    jOrderSchedule.setClockTime(new Date());
                    jOrderScheduleMapper.updateById(jOrderSchedule);
                    break;
                default:
                    break;

            }
            jCustomerMapper.updateById(jCustomer1);
            jNannyInfoMapper.updateById(jNannyInfo);

            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "err", null);
        }
    }

}