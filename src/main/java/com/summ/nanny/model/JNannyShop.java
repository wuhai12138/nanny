package com.summ.nanny.model;

import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
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
@TableName("j_nanny_shop")
public class JNannyShop implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "nannyShopId", type = IdType.AUTO)
    private Integer nannyShopId;
    @TableField("nannyId")
    private Integer nannyId;
    @TableField("shopId")
    private Integer shopId;
    @TableField("isDel")
    @TableLogic
    private Integer isDel;


    public Integer getNannyShopId() {
        return nannyShopId;
    }

    public void setNannyShopId(Integer nannyShopId) {
        this.nannyShopId = nannyShopId;
    }

    public Integer getNannyId() {
        return nannyId;
    }

    public void setNannyId(Integer nannyId) {
        this.nannyId = nannyId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    @Override
    public String toString() {
        return "JNannyShop{" +
        "nannyShopId=" + nannyShopId +
        ", nannyId=" + nannyId +
        ", shopId=" + shopId +
        ", isDel=" + isDel +
        "}";
    }
}
