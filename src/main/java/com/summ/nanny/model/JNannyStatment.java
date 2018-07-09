package com.summ.nanny.model;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author summ
 * @since 2018-05-21
 */
@TableName("j_nanny_statment")
public class JNannyStatment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "statmentId", type = IdType.AUTO)
    private Integer statmentId;
    @TableField("statmentNanny")
    private String statmentNanny;
    @TableField("nannyId")
    private Integer nannyId;
    /**
     * 服务师当前实时星级84、85、86
     */
    @TableField("nannyCurrentJobLevel")
    private Integer nannyCurrentJobLevel;
    /**
     * 服务师当前小时薪资
     */
    @TableField("nannyCurrentUnitPrice")
    private BigDecimal nannyCurrentUnitPrice;
    @TableField("scheduleId")
    private Integer scheduleId;
    @TableField("orderId")
    private Integer orderId;
    @TableField("shopId")
    private Integer shopId;
    @TableField("houseId")
    private Integer houseId;
    @TableField("customerId")
    private Integer customerId;
    /**
     * 服务师对账单类型
     */
    @TableField("statmentNannyType")
    private Integer statmentNannyType;
    /**
     * 金额
     */
    @TableField("statmentMoney")
    private BigDecimal statmentMoney;
    /**
     * 订单类型
     */
    @TableField("orderType")
    private Integer orderType;
    /**
     * 标记订单是否为自定义价格（即手动修改的价格,1表示手动修改）
     */
    @TableField("orderPriceType")
    private Integer orderPriceType;
    /**
     * 产品Id
     */
    @TableField("goodsId")
    private Integer goodsId;
    /**
     * 服务时间（几点到几点）
     */
    @TableField("serviceTime")
    private String serviceTime;
    /**
     * 服务时长
     */
    @TableField("serviceTimeLength")
    private Double serviceTimeLength;
    /**
     * 服务日期
     */
    @TableField("serviceDate")
    private Date serviceDate;
    /**
     * 奖励原因
     */
    private Integer reason;
    @TableField("createDate")
    private Date createDate;
    private String remark;
    @TableField("adminId")
    private Integer adminId;

    public JNannyStatment(String statmentNanny, Integer nannyId, Integer scheduleId, Integer orderId, Integer shopId, Integer houseId, Integer customerId,
                          Integer statmentNannyType, BigDecimal statmentMoney, Integer orderType, Integer goodsId, String serviceTime, Double serviceTimeLength,
                          Date serviceDate, String remark,Integer adminId,Integer nannyCurrentJobLevel,BigDecimal nannyCurrentUnitPrice,Integer orderPriceType) {
        this.statmentNanny = statmentNanny;
        this.nannyId = nannyId;
        this.scheduleId = scheduleId;
        this.orderId = orderId;
        this.shopId = shopId;
        this.houseId = houseId;
        this.customerId = customerId;
        this.statmentNannyType = statmentNannyType;
        this.statmentMoney = statmentMoney;
        this.orderType = orderType;
        this.goodsId = goodsId;
        this.serviceTime = serviceTime;
        this.serviceTimeLength = serviceTimeLength;
        this.serviceDate = serviceDate;
        this.remark = remark;
        this.adminId=adminId;
        this.nannyCurrentJobLevel=nannyCurrentJobLevel;
        this.nannyCurrentUnitPrice=nannyCurrentUnitPrice;
        this.orderPriceType=orderPriceType;
    }

    public JNannyStatment() {
    }

    public Integer getStatmentId() {
        return statmentId;
    }

    public void setStatmentId(Integer statmentId) {
        this.statmentId = statmentId;
    }

    public String getStatmentNanny() {
        return statmentNanny;
    }

    public void setStatmentNanny(String statmentNanny) {
        this.statmentNanny = statmentNanny;
    }

    public Integer getNannyId() {
        return nannyId;
    }

    public void setNannyId(Integer nannyId) {
        this.nannyId = nannyId;
    }

    public Integer getNannyCurrentJobLevel() {
        return nannyCurrentJobLevel;
    }

    public void setNannyCurrentJobLevel(Integer nannyCurrentJobLevel) {
        this.nannyCurrentJobLevel = nannyCurrentJobLevel;
    }

    public BigDecimal getNannyCurrentUnitPrice() {
        return nannyCurrentUnitPrice;
    }

    public void setNannyCurrentUnitPrice(BigDecimal nannyCurrentUnitPrice) {
        this.nannyCurrentUnitPrice = nannyCurrentUnitPrice;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getStatmentNannyType() {
        return statmentNannyType;
    }

    public void setStatmentNannyType(Integer statmentNannyType) {
        this.statmentNannyType = statmentNannyType;
    }

    public BigDecimal getStatmentMoney() {
        return statmentMoney;
    }

    public void setStatmentMoney(BigDecimal statmentMoney) {
        this.statmentMoney = statmentMoney;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getOrderPriceType() {
        return orderPriceType;
    }

    public void setOrderPriceType(Integer orderPriceType) {
        this.orderPriceType = orderPriceType;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }

    public Double getServiceTimeLength() {
        return serviceTimeLength;
    }

    public void setServiceTimeLength(Double serviceTimeLength) {
        this.serviceTimeLength = serviceTimeLength;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public Integer getReason() {
        return reason;
    }

    public void setReason(Integer reason) {
        this.reason = reason;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    @Override
    public String toString() {
        return "JNannyStatment{" +
        "statmentId=" + statmentId +
        ", statmentNanny=" + statmentNanny +
        ", nannyId=" + nannyId +
        ", nannyCurrentJobLevel=" + nannyCurrentJobLevel +
        ", nannyCurrentUnitPrice=" + nannyCurrentUnitPrice +
        ", scheduleId=" + scheduleId +
        ", orderId=" + orderId +
        ", shopId=" + shopId +
        ", houseId=" + houseId +
        ", customerId=" + customerId +
        ", statmentNannyType=" + statmentNannyType +
        ", statmentMoney=" + statmentMoney +
        ", orderType=" + orderType +
        ", orderPriceType=" + orderPriceType +
        ", goodsId=" + goodsId +
        ", serviceTime=" + serviceTime +
        ", serviceTimeLength=" + serviceTimeLength +
        ", serviceDate=" + serviceDate +
        ", reason=" + reason +
        ", createDate=" + createDate +
        ", remark=" + remark +
        ", adminId=" + adminId +
        "}";
    }
}
