package com.alfred.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.alfred.business.domain.Goods;
import com.alfred.business.domain.Provider;
import com.alfred.business.mapper.GoodsMapper;
import com.alfred.business.service.GoodsService;
import com.alfred.business.service.ProviderService;
import com.alfred.business.vo.GoodsVo;
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
import java.util.Collection;
import java.util.List;

/**
 * @author Alfred
 * @date 2020/5/28 10:37
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService{

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private ProviderService providerService;

    @Override
    public DataGridView queryAllGoods(GoodsVo goodsVo) {
        IPage<Goods>page = new Page<>(goodsVo.getPage(),goodsVo.getLimit());
        QueryWrapper<Goods> qw = new QueryWrapper<>();
        qw.eq(goodsVo.getAvailable()!=null,"available",goodsVo.getAvailable());
        qw.eq(goodsVo.getProviderid()!=null,"providerid",goodsVo.getProviderid());
        qw.like(StringUtils.isNotBlank(goodsVo.getGoodsname()),"goodsname",goodsVo.getGoodsname());
        qw.like(StringUtils.isNotBlank(goodsVo.getSize()),"size",goodsVo.getSize());
        qw.like(StringUtils.isNotBlank(goodsVo.getProductcode()),"productcode",goodsVo.getProductcode());
        qw.like(StringUtils.isNotBlank(goodsVo.getPromitcode()),"promitcode",goodsVo.getPromitcode());
        qw.like(StringUtils.isNotBlank(goodsVo.getDescription()),"description",goodsVo.getDescription());
        this.goodsMapper.selectPage(page,qw);
        List<Goods> records = page.getRecords();
        for (Goods record : records) {
            if(record.getProviderid()!=null){
                Provider provider = providerService.getById(record.getProviderid());
                record.setProvidername(provider.getProvidername());
            }
        }
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    @CachePut(value = "com.alfred.business.service.impl.GoodsServiceImpl",key = "#result.id")
    @Override
    public Goods saveGoods(Goods goods) {
        this.goodsMapper.insert(goods);
        return goods;
    }

    @CachePut(value = "com.alfred.business.service.impl.GoodsServiceImpl",key = "#result.id")
    @Override
    public Goods updateGoods(Goods goods) {
        Goods selectById = this.goodsMapper.selectById(goods.getId());
        BeanUtil.copyProperties(goods,selectById, CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
        this.goodsMapper.updateById(selectById);
        return selectById;
    }

    @Override
    public DataGridView getAllAvailableGoods() {
        QueryWrapper<Goods> qw=new QueryWrapper<>();
        qw.eq("available", Contant.AVAILABLE_TRUE);
        List<Goods> goods = this.goodsMapper.selectList(qw);
        return new DataGridView(goods);
    }

    @Override
    public DataGridView getGoodsByProviderId(Integer providerid) {
        if(providerid==null){
            return new DataGridView();
        }
        QueryWrapper<Goods> qw=new QueryWrapper<>();
        qw.eq("available", Contant.AVAILABLE_TRUE);
        qw.eq("providerid",providerid);
        List<Goods> goods = this.goodsMapper.selectList(qw);
        return new DataGridView(goods);

    }

    @Cacheable(value = "com.alfred.business.service.impl.GoodsServiceImpl",key = "#id")
    @Override
    public Goods getById(Serializable id) {
        return super.getById(id);
    }

    @CacheEvict(value = "com.alfred.business.service.impl.GoodsServiceImpl",key = "#id")
    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

}
