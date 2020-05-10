package com.alfred.system.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Alfred
 * @date 2020/5/10 11:15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LoginfoVo extends BaseVo{
    private String loginname;
    private String loginip;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}
