package com.alfred.business.vo;

import com.alfred.system.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Alfred
 * @date 2020/5/29 15:53
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class OutportVo extends BaseVo {

    private Integer providerid;
    private Integer goodsid;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;


}
