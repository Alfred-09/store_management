package com.alfred.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.alfred.business.domain.Customer;
import com.alfred.business.mapper.CustomerMapper;
import com.alfred.business.service.CustomerService;
import com.alfred.business.vo.CustomerVo;
import com.alfred.system.common.Contant;
import com.alfred.system.common.DataGridView;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
            qw.and(customerQueryWrapper -> customerQueryWrapper.like(StringUtils.isNotBlank(customerVo.getPhone()),"phone",customerVo.getPhone())
                    .or().like(StringUtils.isNotBlank(customerVo.getPhone()),"telephone",customerVo.getPhone()));
        }
        this.customerMapper.selectPage(page,qw);
        return new DataGridView(page.getTotal(),page.getRecords());
    }
    @CachePut(cacheNames = "com.alfred.business.service.impl.CustomerServiceImpl",key = "#result.id")
    @Override
    public Customer saveCustomer(Customer customer) {
        this.customerMapper.insert(customer);
        return customer;
    }
    @CachePut(cacheNames = "com.alfred.business.service.impl.CustomerServiceImpl",key = "#result.id")
    @Override
    public Customer updateCustomer(Customer customer) {
        Customer selectById = this.customerMapper.selectById(customer.getId());
        BeanUtil.copyProperties(customer,selectById, CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
        this.customerMapper.updateById(selectById);
        return selectById;
    }

    @Override
    public DataGridView getAllAvailableCustomer() {
        QueryWrapper<Customer>qw = new QueryWrapper<>();
        qw.eq("available", Contant.AVAILABLE_TRUE);
        return new DataGridView(this.customerMapper.selectList(qw));
    }

    @CacheEvict(cacheNames = "com.alfred.business.service.impl.CustomerServiceImpl",key = "#id")
    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Cacheable(cacheNames = "com.alfred.business.service.impl.CustomerServiceImpl",key = "#id")
    @Override
    public Customer getById(Serializable id) {
        return super.getById(id);
    }
}


