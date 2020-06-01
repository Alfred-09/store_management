package com.alfred.business.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Alfred
 * @date 2020/5/28 16:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "bus_inport")
public class Inport implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "paytype")
    private String paytype;

    //前台传给后台
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "inporttime")
    private Date inporttime;

    @TableField(value = "operateperson")
    private String operateperson;

    @TableField(value = "number")
    private Integer number;

    @TableField(value = "remark")
    private String remark;

    @TableField(value = "inportprice")
    private Double inportprice;

    @TableField(value = "providerid")
    private Integer providerid;

    @TableField(value = "goodsid")
    private Integer goodsid;

    @TableField(exist = false)
    private String providername;

    @TableField(exist = false)
    private String goodsname;

    @TableField(exist = false)
    private String size ;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_PAYTYPE = "paytype";

    public static final String COL_INPORTTIME = "inporttime";

    public static final String COL_OPERATEPERSON = "operateperson";

    public static final String COL_NUMBER = "number";

    public static final String COL_REMARK = "remark";

    public static final String COL_INPORTPRICE = "inportprice";

    public static final String COL_PROVIDERID = "providerid";

    public static final String COL_GOODSID = "goodsid";
}