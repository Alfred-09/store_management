package com.alfred.system.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: Alfred
 * @create: 2020-01-04 17:38
 **/

@Data
@EqualsAndHashCode(callSuper=false)
public class RoleVo extends  BaseVo{

     private Integer userId;
     private String name;
     private String remark;

}
