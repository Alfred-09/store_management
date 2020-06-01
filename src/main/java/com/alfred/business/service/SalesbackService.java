package com.alfred.business.service;

import com.alfred.business.domain.Salesback;
import com.alfred.business.vo.SalesbackVo;
import com.alfred.system.common.DataGridView;
import com.baomidou.mybatisplus.extension.service.IService;
    /**
 * @author Alfred
 * @date 2020/5/31 10:16
 */
public interface SalesbackService extends IService<Salesback>{


        DataGridView queryAllSalesback(SalesbackVo salesbackVo);

        Salesback saveSalesback(Salesback salesback);
    }
