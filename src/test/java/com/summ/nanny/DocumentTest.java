package com.summ.nanny;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.summ.nanny.model.response.NannyRankingsRes;
import com.summ.nanny.utils.Base64;
import com.summ.nanny.utils.Pkcs7Encoder;
import com.summ.nanny.utils.SendSMSUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.util.Base64Utils;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jygj_7500 on 17/11/17.
 */
public class DocumentTest {

    public static void main(String[] args) throws ClientException, JSONException {

//        //设置超时时间-可自行调整
//        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
//        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
////初始化ascClient需要的几个参数
//        final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
//        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
////初始化ascClient,暂时不支持多region（请勿修改）
//        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAIjTTqKQ1ULJGC",
//                "rcnEUVQaYpZDzLWW80H0Wh5huk5az7");
//        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product,domain);
//        IAcsClient acsClient = new DefaultAcsClient(profile);
//        //组装请求对象
//        SendSmsRequest request = new SendSmsRequest();
//        //使用post提交
//        request.setMethod(MethodType.POST);
//        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为00+国际区号+号码，如“0085200000000”
//        request.setPhoneNumbers("17717284837");
//        //必填:短信签名-可在短信控制台中找到
//        request.setSignName("家有管家");
//        //必填:短信模板-可在短信控制台中找到
//        request.setTemplateCode("SMS_41015099");
//        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
//        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
//        request.setTemplateParam("{\"code\":\"" + "123456" + "\",\"product\":\"" + "家有管家" + "\"}");
////请求失败这里会抛ClientException异常
//        try {
//            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
//        }catch (ClientException e){
//            System.out.println(e.getErrMsg());
//        }
//        SendSMSUtil.signCustomer(SendSMSUtil.generateCode(),"家有管家","17717284837");


//        String  encryptedData = "MdESBEMfOPGabj4do/chSHgeHwsfsaRlCpgl+S9ztzKf18WX7U+th/bMGpn4BmSTUyVvkBpmYQEVqvHJPPFF3Kue0xY3tkWrtGUOd3VyXo3NHFyWTkiMtOOnPmn0TYjCkOFjPpW3Cw0ato+MY+N6UeX3vaZvra7AaHXBvan+ZF0xahJbkiyRU78yJgQHLHUFCDbJ9RsYFsqG3dtdFOVseQ==";
//        String iv="kW5alac2Fc2KN69EB6sJXg==";
//        String appid = "wx358c6d6d5adb0f0f";
//        String sessionKey = "rVqGk07XZ3mYMhay6+mg2g==";
//        toStr(appid,sessionKey,encryptedData,iv);
        String distance="0.3";
        if (Double.valueOf(distance).compareTo(0.3)==1){
            System.out.println(1);
        }else {
            System.out.println(0);
        }


    }

    private static void toStr(String appid, String sessionKey, String encryptedData, String iv) throws JSONException {
        byte[] sessionKeyBy = Base64.decode(sessionKey);
        byte[] encryptedDataBy = Base64.decode(encryptedData);
        byte[] ivBy = Base64.decode(iv);
        byte[] dec = Pkcs7Encoder.decryptOfDiyIV(encryptedDataBy, sessionKeyBy, ivBy);
        JSONObject h = new JSONObject(new String(dec));
        System.out.println(h.toString());
    }

}
