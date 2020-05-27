package com.alfred.business.service;

import com.alfred.business.domain.Customer;
import com.alfred.business.vo.CustomerVo;
import com.alfred.system.common.DataGridView;
import com.baomidou.mybatisplus.extension.service.IService;
    /**
 * @author Alfred
 * @date 2020/5/27 9:09
 */
public interface CustomerService extends IService<Customer>{


        DataGridView queryAllCustomer(CustomerVo customerVo);

        Customer saveCustomer(Customer customer);

        Customer updateCustomer(Customer customer);
    }
