package com.alfred.business.service;

import com.alfred.business.domain.Outport;
import com.alfred.business.vo.OutportVo;
import com.alfred.system.common.DataGridView;
import com.baomidou.mybatisplus.extension.service.IService;
    /**
 * @author Alfred
 * @date 2020/5/29 15:51
 */
public interface OutportService extends IService<Outport>{

        Outport saveOutport(Outport outport);

        DataGridView queryAllOutport(OutportVo outportVo);
    }
