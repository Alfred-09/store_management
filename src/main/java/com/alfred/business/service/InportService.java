package com.alfred.business.service;

import com.alfred.business.domain.Inport;
import com.alfred.business.vo.InportVo;
import com.alfred.system.common.DataGridView;
import com.baomidou.mybatisplus.extension.service.IService;
    /**
 * @author Alfred
 * @date 2020/5/28 16:23
 */
public interface InportService extends IService<Inport>{


        Inport updateInport(Inport inport);

        Inport saveInport(Inport inport);

        DataGridView queryAllInport(InportVo inportVo);
    }
