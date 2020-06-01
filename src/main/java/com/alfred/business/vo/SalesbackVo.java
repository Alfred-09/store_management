package com.alfred.business.vo;

import com.alfred.system.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Alfred
 * @date 2020/5/31 10:19
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class SalesbackVo extends BaseVo {

    private Integer customerid;
    private Integer goodsid;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

}
