package com.alfred.business.service.impl;

import com.alfred.business.domain.Customer;
import com.alfred.business.mapper.CustomerMapper;
import com.alfred.business.service.CustomerService;
import com.alfred.business.vo.CustomerVo;
import com.alfred.system.common.DataGridView;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.function.Consumer;

/**
 * @author Alfred
 * @date 2020/5/27 9:09
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService{

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public DataGridView queryAllCustomer(CustomerVo customerVo) {
        IPage<Customer> page=new Page<>(customerVo.getPage(),customerVo.getLimit());
        QueryWrapper<Customer> qw=new QueryWrapper<>();
        qw.eq(customerVo.getAvailable()!=null,"available",customerVo.getAvailable());
        qw.like(StringUtils.isNotBlank(customerVo.getCustomername()),"customername",customerVo.getCustomername());
        qw.like(StringUtils.isNotBlank(customerVo.getConnectionperson()),"connectionperson",customerVo.getConnectionperson());
        if(StringUtils.isNotBlank(customerVo.getPhone())){
            qw.and(new Consumer<QueryWrapper<Customer>>() {
                @Override
                public void accept(QueryWrapper<Customer> customerQueryWrapper) {
                    customerQueryWrapper.like(StringUtils.isNotBlank(customerVo.getPhone()),"phone",customerVo.getPhone())
                            .or().like(StringUtils.isNotBlank(customerVo.getPhone()),"telephone",customerVo.getPhone());
                }
            });
        }
        this.customerMapper.selectPage(page,qw);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        this.customerMapper.insert(customer);
        return customer;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        this.customerMapper.updateById(customer);
        return customer;
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }
}

