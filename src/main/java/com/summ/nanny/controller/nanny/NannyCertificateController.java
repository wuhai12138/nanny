package com.summ.nanny.controller.nanny;

import com.alibaba.fastjson.JSON;
import com.summ.nanny.Consts;
import com.summ.nanny.controller.basic.AutoMapperController;
import com.summ.nanny.model.JDictInfo;
import com.summ.nanny.model.JNannyCertificate;
import com.summ.nanny.model.JNannyInfo;
import com.summ.nanny.model.response.NannyCertificateRes;
import com.summ.nanny.utils.ModelRes;
import com.summ.nanny.utils.ResponseUtil;
import com.summ.nanny.utils.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Api(tags = "服务师证书模块")
@RestController
@RequestMapping(value = "/nanny/certificate", method = RequestMethod.POST)
public class NannyCertificateController extends AutoMapperController {

    @ApiOperation(value = "添加证书", notes = "添加证书信息")
    @ResponseBody
    @RequestMapping("/insert")
    public Object insert(@RequestBody JNannyCertificate map) {
        try {
            Map map2 = new HashMap();
            map2.put("certificateNumber", map.getCertificateNumber());
            map2.put("certificateId", map.getCertificateId());
            List<JNannyCertificate> jNannyCertificateList = jNannyCertificateMapper.selectByMap(map2);
            if (jNannyCertificateList.size() > 0) {
                return new ModelRes(ModelRes.Status.FAILED, "证件重复 !", ResponseUtil.List2Map(jNannyCertificateList));
            }
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jNannyCertificateMapper.insert(map));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

//    @ApiOperation(value = "上传证书照片", notes = "上传证书照片:字段为 photo base64编码")
//    @ResponseBody
//    @RequestMapping("/upload")
//    public Object upload(@RequestBody JNannyCertificate jNannyCertificate) {
//        try {
//            String fileName = "NC"+System.currentTimeMillis()+".jpg";
//            if(StringUtil.generateImage(jNannyCertificate.getPhoto(),Consts.nannyCertUrl + fileName)){
//                jNannyCertificate.setPhoto(fileName);
//                return new ModelRes(ModelRes.Status.SUCCESS,"操作成功 !",jNannyCertificateMapper.updateById(jNannyCertificate));
//            }
//            return new ModelRes(ModelRes.Status.FAILED, "photo err !");
//        } catch (Exception e)
//        {
//            e.printStackTrace();
//            return new ModelRes(ModelRes.Status.ERROR, "server err !");
//        }
//    }

    @ApiOperation(value = "上传证书照片", notes = "上传证书照片:字段为 photo multipart/form-data")
    @RequestMapping(value = ("/upload"), method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Object fileUpload(@RequestParam("file") MultipartFile file,@RequestParam("nannyCertId") Integer nannyCertId) throws IOException {

        System.out.println("file upload");
        String filePath = null;
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                Pattern pattern = Pattern.compile("\\.[a-z|A-Z]+$");
                Matcher matcher = pattern.matcher(file.getOriginalFilename());
                String fileName = "NC"+System.currentTimeMillis()+".jpg";
                if (matcher.find()) {
                    // 文件保存路径
//                     filePath = "C:\\Users\\jygj_7500\\Desktop\\upload\\" + System.currentTimeMillis();
                    filePath = Consts.nannyCertUrl + fileName;
                    filePath = filePath + fileName;
                } else {
                    return new ModelRes(ModelRes.Status.FAILED, "上传失败 !");
                }

                // 转存文件
                file.transferTo(new File(filePath));

                JNannyCertificate jNannyCertificate = new JNannyCertificate();
                jNannyCertificate.setPhoto(fileName);
                jNannyCertificate.setNannyCertId(nannyCertId);
                jNannyCertificateMapper.updateById(jNannyCertificate);
                return new ModelRes(ModelRes.Status.SUCCESS, "上传成功 !");
            } catch (Exception e) {
                e.printStackTrace();
                return new ModelRes(ModelRes.Status.ERROR, "server err !");
            }
        } else {
            return new ModelRes(ModelRes.Status.FAILED, "文件为空 !");
        }
    }

    @ApiOperation(value = "查找证书列表", notes = "根据服务师id获取证书列表:nannyId")
    @ResponseBody
    @RequestMapping("/find")
    public Object findCertificateData(@RequestBody JNannyCertificate jNannyCertificate) {
        try {
            Map map = new HashMap();
            List<NannyCertificateRes> list = jNannyCertificateMapper.getNannyCertificateByNannyId(jNannyCertificate.getNannyId());
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setPhoto(Consts.nannyCertUrlRes + list.get(i).getPhoto());
            }
            map.put("certificate", list);
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ApiOperation(value = "删除证书", notes = "根据证书表id删除证书:nannyCertId")
    @ResponseBody
    @RequestMapping("/delete")
    public Object deleteCertificateData(@RequestBody JNannyCertificate map) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jNannyCertificateMapper.deleteById(map));
        } catch (Exception e) {

            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }
}
