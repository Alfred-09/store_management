package com.alfred.business.service;

import com.alfred.business.domain.Sales;
import com.alfred.business.vo.SalesVo;
import com.alfred.system.common.DataGridView;
import com.baomidou.mybatisplus.extension.service.IService;
    /**
 * @author Alfred
 * @date 2020/5/30 9:33
 */
public interface SalesService extends IService<Sales>{


        Sales saveSales(Sales sales);

        DataGridView queryAllSales(SalesVo salesVo);

        Sales updateSales(Sales sales);
    }
