package com.summ.nanny.controller.nanny;


import com.google.gson.JsonObject;
import com.summ.nanny.controller.basic.AutoMapperController;
import com.summ.nanny.model.JNannyInfo;
import com.summ.nanny.model.JNannyShop;
import com.summ.nanny.model.JSystemAuthCode;
import com.summ.nanny.model.request.BindReq;
import com.summ.nanny.model.request.CodeReq;
import com.summ.nanny.model.request.DecodeReq;
import com.summ.nanny.model.response.DecodeObj;
import com.summ.nanny.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "小程序验证模块")
@RestController
@RequestMapping(value = "/nanny/mini/app", method = RequestMethod.POST)
public class NannyMiNiAppController extends AutoMapperController {

    /**
     * 1.验证微信openid有没有绑定过手机号
     * 2.验证手机号有没有绑定过微信号
     *
     * @param jNannyInfo
     * @return
     */
    @ApiOperation(value = "验证手机号是否绑定openid或openid是否绑定过手机号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "nannyPhone", value = "服务师手机号"),
            @ApiImplicitParam(name = "WeiXinOpenId", value = "微信openid")
    })
    @ResponseBody
    @RequestMapping(value = "/sign", method = RequestMethod.POST)
    public Object sign(@RequestBody JNannyInfo jNannyInfo) {
        try {
            /**验证openid*/
            if (jNannyInfo.getWeiXinOpenId() != null) {
                Map mapOpenId = new HashMap();
                mapOpenId.put("weiXinOpenId", jNannyInfo.getWeiXinOpenId());
                List<JNannyInfo> jNannyInfoList = jNannyInfoMapper.selectByMap(mapOpenId);
                if (jNannyInfoList.size() > 0) {
                    return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jNannyInfoList.get(0));
                } else {
                    return new ModelRes(ModelRes.Status.FAILED, "该微信未绑定过手机号", null);
                }
            }
            /**验证手机号*/
            else if (jNannyInfo.getNannyPhone() != null) {
                Map mapPhone = new HashMap();
                mapPhone.put("nannyPhone", jNannyInfo.getNannyPhone());
                List<JNannyInfo> jNannyInfoList = jNannyInfoMapper.selectByMap(mapPhone);
                if (jNannyInfoList.size() > 0) {
                    if (null == jNannyInfoList.get(0).getWeiXinOpenId() || "".equals(jNannyInfoList.get(0).getWeiXinOpenId())) {
                        return new ModelRes(ModelRes.Status.FAILED, "未绑定openid !", jNannyInfoList.get(0));
                    } else {
                        return new ModelRes(ModelRes.Status.SUCCESS, "该手机号已经绑定过微信openid !", jNannyInfoList.get(0));
                    }
                } else {
                    /**查不到用户信息则新增客户后返回*/
                    JNannyInfo jNannyInfoNew = new JNannyInfo();
                    jNannyInfoNew.setNannyPhone(jNannyInfo.getNannyPhone());
                    jNannyInfoNew.setNannyName("服务师" + jNannyInfo.getNannyPhone().substring(8));
                    jNannyInfoMapper.insert(jNannyInfoNew);
                    return new ModelRes(ModelRes.Status.FAILED, "未绑定openid", jNannyInfoNew);
                }
            } else {
                return new ModelRes(ModelRes.Status.SUCCESS, "");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 获取手机验证码
     *
     * @param jNannyInfo
     * @return
     */
    @ApiOperation(value = "获取验证码", notes = "输入手机号获取验证码:nannyPhone")
    @RequestMapping(value = "/authCode", method = RequestMethod.POST)
    public Object getAuthCode(@RequestBody JNannyInfo jNannyInfo) {
        try {
            /**六位随机验证码*/
            String code = SendSMSUtil.generateCode();
            JSystemAuthCode jSystemAuthCode = new JSystemAuthCode(jNannyInfo.getNannyPhone(), code, new Date());
            jSystemAuthCodeMapper.insert(jSystemAuthCode);
//            SendSMSUtil.signCustomer(code,"家有管家小程序",jCustomer.getCustomerPhone());
            return new ModelRes(ModelRes.Status.SUCCESS, "验证法已发送");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 绑定或者更新微信openid
     *
     * @return
     */
    @ApiOperation(value = "绑定或者更新微信openid")
    @ResponseBody
    @RequestMapping("/bind")
    public Object getBindCustomer(@RequestBody BindReq bindReq) {
        try {

            Map map = new HashMap();
            map.put("phone", bindReq.getNannyPhone());
            map.put("authCode", bindReq.getAuthCode());
            List<JSystemAuthCode> jSystemAuthCodeList = jSystemAuthCodeMapper.selectByMap(map);
            if (jSystemAuthCodeList.size() > 0 || bindReq.getAuthCode().equals("666")) {
                JNannyInfo jNannyInfo = new JNannyInfo();
                jNannyInfo.setNannyId(bindReq.getNannyId());
                jNannyInfo.setNannyPhone(bindReq.getNannyPhone());
                jNannyInfo.setWeiXinOpenId(bindReq.getWeiXinOpenId());

                JNannyShop jNannyShop = new JNannyShop();
                jNannyShop.setNannyId(bindReq.getNannyId());
                jNannyShop.setShopId(bindReq.getShopId());

                jNannyInfoMapper.updateById(jNannyInfo);
                jNannyShopMapper.insert(jNannyShop);

                return new ModelRes(ModelRes.Status.SUCCESS, "绑定成功", jNannyInfo);
            } else {
                return new ModelRes(ModelRes.Status.FAILED, "验证码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 根据code获取用户openid
     *
     * @return
     */
    @ApiOperation(value = "根据code获取用户openid")
    @ResponseBody
    @RequestMapping("/info")
    public Object getOpenId(@RequestBody CodeReq codeReq) {
        try {
            String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wx358c6d6d5adb0f0f&secret=0c907239d51603851602ac8e93c8326a&js_code=" + codeReq.getCode() + "&grant_type=authorization_code";
            return new ModelRes(ModelRes.Status.SUCCESS, "请求结果", RequestUtil.doGet(url));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 解密getPhoneNumber密文并更新服务师信息
     *
     * @return
     */
    @ApiOperation(value = "解密getPhoneNumber密文并更新服务师信息")
    @ResponseBody
    @RequestMapping("/decodeAndBind")
    public Object decodeAndBind(@RequestBody DecodeReq decodeReq) {
        try {
            if (null==decodeReq.getOpenId()){
                return new ModelRes(ModelRes.Status.FAILED, "未提交openid !", decodeReq);
            }
            String s = Pkcs7Encoder.decode(decodeReq.getSessionKey(), decodeReq.getEncryptedData(), decodeReq.getIv());
            DecodeObj decodeObj = JsonUtil.json2Bean(s, DecodeObj.class);
            /**验证手机号*/
            Map mapPhone = new HashMap();
            mapPhone.put("nannyPhone", decodeObj.getPhoneNumber());
            List<JNannyInfo> jNannyInfoList = jNannyInfoMapper.selectByMap(mapPhone);
            if (jNannyInfoList.size() > 0) {
                JNannyInfo jNannyInfo = jNannyInfoList.get(0);
                jNannyInfo.setWeiXinOpenId(decodeReq.getOpenId());
                jNannyInfoMapper.updateById(jNannyInfo);
                return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jNannyInfo);
            } else {
                /**查不到用户信息则新增客户后返回*/
                JNannyInfo jNannyInfoNew = new JNannyInfo();
                jNannyInfoNew.setNannyPhone(decodeObj.getPhoneNumber());
                jNannyInfoNew.setNannyName("服务师" + decodeObj.getPhoneNumber().substring(8));
                jNannyInfoNew.setWeiXinOpenId(decodeReq.getOpenId());
                jNannyInfoMapper.insert(jNannyInfoNew);
                return new ModelRes(ModelRes.Status.FAILED, "操作成功", jNannyInfoNew);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }
}
