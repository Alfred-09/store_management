package com.alfred.system.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: Alfred
 * @create: 2020-01-04 17:38
 **/

@Data
@EqualsAndHashCode(callSuper=false)
public class DeptVo extends  BaseVo{
    private String title;
}
