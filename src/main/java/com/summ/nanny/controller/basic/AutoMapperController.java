package com.summ.nanny.controller.basic;


import com.summ.nanny.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author jygj_7500
 * @date 17/12/18
 */
public class AutoMapperController {

    @Autowired
    public JNannyDianPingMapper jNannyDianPingMapper;
    @Autowired
    public JScheduleChangeApplyMapper jScheduleChangeApplyMapper;
    @Autowired
    public JNannyScheduleFeedbackMapper jNannyScheduleFeedbackMapper;
    @Autowired
    public JCustomerScheduleFeedbackMapper jCustomerScheduleFeedbackMapper;
    @Autowired
    public JNannyEmergencyContactMapper jNannyEmergencyContactMapper;
    @Autowired
    public JNannyCertificateMapper jNannyCertificateMapper;
    @Autowired
    public JCustomerStatmentMapper jCustomerStatmentMapper;
    @Autowired
    public JCustomerMapper jCustomerMapper;
    @Autowired
    public JProvinceMapper jProvinceMapper;
    @Autowired
    public JCityMapper jCityMapper;
    @Autowired
    public JNannyInfoMapper jNannyInfoMapper;
    @Autowired
    public JStreetMapper jStreetMapper;
    @Autowired
    public JDictInfoMapper jDictInfoMapper;
    @Autowired
    public JDictTypeMapper jDictTypeMapper;
    @Autowired
    public JNannyJobLevelMapper jNannyJobLevelMapper;
    @Autowired
    public JShopMapper jShopMapper;
    @Autowired
    public JNannyShopMapper jNannyShopMapper;
    @Autowired
    public JOrderContractMapper jOrderContractMapper;
    @Autowired
    public JNannyWorkTimeMapper jNannyWorkTimeMapper;
    @Autowired
    public JNannyWorkTimeDicMapper jNannyWorkTimeDicMapper;
    @Autowired
    public JOrderScheduleMapper jOrderScheduleMapper;
    @Autowired
    public JScheduleNannyMapper jScheduleNannyMapper;
    @Autowired
    public JOrderTempMapper jOrderTempMapper;
    @Autowired
    public JNannyStatmentMapper jNannyStatmentMapper;
    @Autowired
    public JOrderYearsMapper jOrderYearsMapper;
    @Autowired
    public JNannyJobLevelGoodsMapper jNannyJobLevelGoodsMapper;
    @Autowired
    public JSystemAuthCodeMapper jSystemAuthCodeMapper;
}
