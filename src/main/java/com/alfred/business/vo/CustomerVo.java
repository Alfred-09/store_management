package com.alfred.business.vo;

import com.alfred.system.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Alfred
 * @date 2020/5/27 9:14
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class CustomerVo extends BaseVo {

    private String customername;
    private String phone;
    private String connectionperson;

}
