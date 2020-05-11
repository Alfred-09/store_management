package com.alfred.system.controller;

import com.alfred.system.common.ResultObj;
import com.alfred.system.service.LoginfoService;
import com.alfred.system.vo.LoginfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alfred
 * @date 2020年5月10日11:11:53
 */
@RestController
@RequestMapping(value = "loginfo")
public class LoginfoController {

    @Autowired
    private LoginfoService loginfoService;


    /**
     * 查询
     * @param loginfoVo
     * @return
     */
    @RequestMapping("loadAllLoginfo")
    public Object loadAllLoginfo(LoginfoVo loginfoVo){
        return this.loginfoService.queryAllLoginfo(loginfoVo);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("deleteLoginfo")
    public ResultObj deleteLoginfo(Integer id){
        try{
            this.loginfoService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping("batchDeleteLoginfo")
    public ResultObj batchDeleteLoginfo(Integer[] ids){
        try{
            if (ids != null&&ids.length>0) {
                List<Integer> idsList = new ArrayList();
                for (Integer id : ids) {
                    idsList.add(id);
                }
                this.loginfoService.removeByIds(idsList);
                return ResultObj.DELETE_SUCCESS;
            }else {
                return new ResultObj(-1,"传入的id不能为空");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
