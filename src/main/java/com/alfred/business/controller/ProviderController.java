package com.alfred.business.controller;


import com.alfred.business.domain.Provider;
import com.alfred.business.service.ProviderService;
import com.alfred.business.vo.ProviderVo;
import com.alfred.system.common.Contant;
import com.alfred.system.common.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alfred
 * @date 2020/5/27 9:11
 */
@RestController
@RequestMapping("provider")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    /**
     * 查询
     * @return
     */
    @RequestMapping("loadAllProvider")
    public Object loadAllProvider(ProviderVo providerVo){
     return this.providerService.queryAllProvider(providerVo);
    }

    /**
     * 添加
     * @return
     */
    @RequestMapping("addProvider")
    public ResultObj addCustomer(Provider provider){
        try {
            provider.setAvailable(Contant.AVAILABLE_TRUE);
            this.providerService.saveProvider(provider);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改
     * @return
     */
    @RequestMapping("updateProvider")
    public ResultObj updateProvider(Provider provider){
        try {
            this.providerService.updateProvider(provider);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("deleteProvider")
    public ResultObj deleteProvider(Integer id){
        try {
            this.providerService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping("batchDeleteProvider")
    public ResultObj batchDeleteProvider(Integer[] ids){
        List<Integer>idsList = null;
        try {
            idsList = new ArrayList<>();
            for (Integer id : ids) {
                idsList.add(id);
            }
            this.providerService.removeByIds(idsList);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }

    }


}
