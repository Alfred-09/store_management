package com.alfred.business.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Alfred
 * @date 2020/5/30 9:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "bus_sales")
public class Sales implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "customerid")
    private Integer customerid;

    @TableField(value = "paytype")
    private String paytype;

    @TableField(value = "salestime")
    private Date salestime;

    @TableField(value = "operateperson")
    private String operateperson;

    @TableField(value = "number")
    private Integer number;

    @TableField(value = "remark")
    private String remark;

    @TableField(value = "saleprice")
    private Double saleprice;

    @TableField(value = "goodsid")
    private Integer goodsid;

    @TableField(exist = false)
    private String customername;

    @TableField(exist = false)
    private String goodsname;

    @TableField(exist = false)
    private String size;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_CUSTOMERID = "customerid";

    public static final String COL_PAYTYPE = "paytype";

    public static final String COL_SALESTIME = "salestime";

    public static final String COL_OPERATEPERSON = "operateperson";

    public static final String COL_NUMBER = "number";

    public static final String COL_REMARK = "remark";

    public static final String COL_SALEPRICE = "saleprice";

    public static final String COL_GOODSID = "goodsid";
}