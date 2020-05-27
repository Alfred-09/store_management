package com.alfred.system.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: Alfred
 * @create: 2020-01-04 17:38
 **/

@Data
@EqualsAndHashCode(callSuper=false)
public class MenuVo extends  BaseVo{

     Integer hasPermission;//0不要权限  1的话要权限
}
