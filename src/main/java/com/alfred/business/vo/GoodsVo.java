package com.alfred.business.vo;

import com.alfred.system.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Alfred
 * @date 2020/5/28 10:47
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class GoodsVo extends BaseVo {
    private Integer providerid;
    private String goodsname;
    private String size;
    private String productcode;
    private String promitcode;
    private String description;
}
