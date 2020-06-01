package com.alfred.business.controller;

import com.alfred.business.domain.Outport;
import com.alfred.business.service.OutportService;
import com.alfred.business.vo.OutportVo;
import com.alfred.system.common.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alfred
 * @date 2020/5/29 15:55
 */
@RestController
@RequestMapping("outport")
public class OutportController {

    @Autowired
    private OutportService outportService;


    /**
     * 查询
     */
    @RequestMapping("loadAllOutport")
    public Object loadAllOutport(OutportVo outportVo){
        return this.outportService.queryAllOutport(outportVo);
    }

    /**
     * 添加退货信息
     */
    @RequestMapping("addOutport")
    public ResultObj addOutport(Outport outport){
        try {
            this.outportService.saveOutport(outport);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e)
        {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }

    }
}
