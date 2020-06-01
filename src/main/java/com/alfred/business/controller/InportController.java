package com.alfred.business.controller;

import com.alfred.business.domain.Inport;
import com.alfred.business.service.InportService;
import com.alfred.business.vo.InportVo;
import com.alfred.system.common.ActiveUser;
import com.alfred.system.common.ResultObj;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Alfred
 * @date 2020/5/28 16:38
 */
@RequestMapping("inport")
@RestController
public class InportController {

    @Autowired
    private InportService inportService;


    /**
     * 查询
     * @param inportVo
     * @return
     */
    @RequestMapping("loadAllInport")
    public Object loadAllInport(InportVo inportVo){
        return this.inportService.queryAllInport(inportVo);
    }

    /**
     * 添加
     * @return
     */
    @RequestMapping("addInport")
    public ResultObj addInport(Inport inport){
        try {
            ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
            inport.setOperateperson(activeUser.getUser().getName());
            inport.setInporttime(new Date());
            this.inportService.saveInport(inport);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改
     * @param inport
     * @return
     */
    @RequestMapping("updateInport")
    public ResultObj updateInport(Inport inport){
        try {
            this.inportService.updateInport(inport);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
    /**
     * 删除
     */
    @RequestMapping("deleteInport")
    public ResultObj deleteInport(Integer id){
        try {
            this.inportService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e)
        {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
