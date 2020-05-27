package com.alfred.business.controller;

import com.alfred.business.domain.Customer;
import com.alfred.business.service.CustomerService;
import com.alfred.business.vo.CustomerVo;
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
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * 查询
     * @return
     */
    @RequestMapping("loadAllCustomer")
    public Object loadAllCustomer(CustomerVo customerVo){
     return this.customerService.queryAllCustomer(customerVo);
    }

    /**
     * 添加
     * @return
     */
    @RequestMapping("addCustomer")
    public ResultObj addCustomer(Customer customer){
        try {
            customer.setAvailable(Contant.AVAILABLE_TRUE);
            this.customerService.saveCustomer(customer);
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
    @RequestMapping("updateCustomer")
    public ResultObj updateCustomer(Customer customer){
        try {
            this.customerService.updateCustomer(customer);
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
    @RequestMapping("deleteCustomer")
    public ResultObj deleteCustomer(Integer id){
        try {
            this.customerService.removeById(id);
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
    @RequestMapping("batchDeleteCustomer")
    public ResultObj batchDeleteCustomer(Integer[] ids){
        List<Integer>idsList = null;
        try {
            idsList = new ArrayList<>();
            for (Integer id : ids) {
                idsList.add(id);
            }
            this.customerService.removeByIds(idsList);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }

    }


}
