package com.alfred.business.service.impl;

import com.alfred.business.domain.Provider;
import com.alfred.business.mapper.ProviderMapper;
import com.alfred.business.service.ProviderService;
import com.alfred.business.vo.ProviderVo;
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
 * @date 2020/5/27 10:29
 */
@Service
public class ProviderServiceImpl extends ServiceImpl<ProviderMapper, Provider> implements ProviderService{

    @Autowired
    private ProviderMapper providerMapper;

    @Override
    public DataGridView queryAllProvider(ProviderVo providerVo) {
        IPage<Provider> page=new Page<>(providerVo.getPage(),providerVo.getLimit());
        QueryWrapper<Provider> qw=new QueryWrapper<>();
        qw.eq(providerVo.getAvailable()!=null,"available",providerVo.getAvailable());
        qw.like(StringUtils.isNotBlank(providerVo.getProvidername()),"providername",providerVo.getProvidername());
        qw.like(StringUtils.isNotBlank(providerVo.getConnectionperson()),"connectionperson",providerVo.getConnectionperson());
        if(StringUtils.isNotBlank(providerVo.getPhone())){
            qw.and(new Consumer<QueryWrapper<Provider>>() {
                @Override
                public void accept(QueryWrapper<Provider> providerQueryWrapper) {
                    providerQueryWrapper.like(StringUtils.isNotBlank(providerVo.getPhone()),"phone",providerVo.getPhone())
                            .or().like(StringUtils.isNotBlank(providerVo.getPhone()),"telephone",providerVo.getPhone());
                }
            });
        }
        this.providerMapper.selectPage(page,qw);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    @Override
    public Provider saveProvider(Provider provider) {
        this.providerMapper.insert(provider);
        return provider;
    }

    @Override
    public Provider updateProvider(Provider provider) {
        this.providerMapper.updateById(provider);
        return provider;
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }
}
