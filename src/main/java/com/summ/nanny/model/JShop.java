package com.summ.nanny.model;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
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
@TableName("j_shop")
public class JShop implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "shopId", type = IdType.AUTO)
    private Integer shopId;
    @TableField("trainId")
    private Integer trainId;
    @TableField("shopName")
    private String shopName;
    /**
     * é—¨åº—åº§æœº
     */
    @TableField("shopPhone")
    private String shopPhone;
    /**
     * é—¨åº—æ‰‹æœº
     */
    @TableField("shopMobile")
    private String shopMobile;
    @TableField("shopAddress")
    private String shopAddress;
    @TableField("shopPayCode")
    private String shopPayCode;
    @TableField("shopUrl")
    private String shopUrl;
    private Double longitude;
    private Double latitude;
    private Integer staring;
    @TableField("isDel")
    private Integer isDel;
    private Double distance;
    @TableField("createTime")
    private Date createTime;
    @TableField("modifyTime")
    private Date modifyTime;
    @TableField("provinceId")
    private Integer provinceId;
    @TableField("cityId")
    private Integer cityId;
    @TableField("areaId")
    private Integer areaId;
    @TableField("streetId")
    private Integer streetId;


    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopPhone() {
        return shopPhone;
    }

    public void setShopPhone(String shopPhone) {
        this.shopPhone = shopPhone;
    }

    public String getShopMobile() {
        return shopMobile;
    }

    public void setShopMobile(String shopMobile) {
        this.shopMobile = shopMobile;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopPayCode() {
        return shopPayCode;
    }

    public void setShopPayCode(String shopPayCode) {
        this.shopPayCode = shopPayCode;
    }

    public String getShopUrl() {
        return shopUrl;
    }

    public void setShopUrl(String shopUrl) {
        this.shopUrl = shopUrl;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Integer getStaring() {
        return staring;
    }

    public void setStaring(Integer staring) {
        this.staring = staring;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getStreetId() {
        return streetId;
    }

    public void setStreetId(Integer streetId) {
        this.streetId = streetId;
    }

    @Override
    public String toString() {
        return "JShop{" +
        "shopId=" + shopId +
        ", trainId=" + trainId +
        ", shopName=" + shopName +
        ", shopPhone=" + shopPhone +
        ", shopMobile=" + shopMobile +
        ", shopAddress=" + shopAddress +
        ", shopPayCode=" + shopPayCode +
        ", shopUrl=" + shopUrl +
        ", longitude=" + longitude +
        ", latitude=" + latitude +
        ", staring=" + staring +
        ", isDel=" + isDel +
        ", distance=" + distance +
        ", createTime=" + createTime +
        ", modifyTime=" + modifyTime +
        ", provinceId=" + provinceId +
        ", cityId=" + cityId +
        ", areaId=" + areaId +
        ", streetId=" + streetId +
        "}";
    }
}
