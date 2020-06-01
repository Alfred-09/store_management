package com.alfred.business.service.impl;

import com.alfred.business.domain.Goods;
import com.alfred.business.domain.Inport;
import com.alfred.business.domain.Provider;
import com.alfred.business.mapper.InportMapper;
import com.alfred.business.service.GoodsService;
import com.alfred.business.service.InportService;
import com.alfred.business.service.ProviderService;
import com.alfred.business.vo.InportVo;
import com.alfred.system.common.DataGridView;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @author Alfred
 * @date 2020/5/28 16:23
 */
@Service
public class InportServiceImpl extends ServiceImpl<InportMapper, Inport> implements InportService{

    @Autowired
    private InportMapper inportMapper;

    @Autowired
    private ProviderService providerService;

    @Autowired
    private GoodsService goodsService;

    @Override
    public Inport updateInport(Inport inport) {
        Inport oldObj = this.inportMapper.selectById(inport.getId());
        Goods goods = this.goodsService.getById(oldObj.getGoodsid());
        goods.setNumber(goods.getNumber()-oldObj.getNumber()+inport.getNumber());
        this.goodsService.updateGoods(goods);
        this.inportMapper.updateById(inport);
        return inport;
    }

    @Override
    public Inport saveInport(Inport inport) {
        this.inportMapper.insert(inport);
        //更新库存
        //获取到食品id
        Integer goodsid = inport.getGoodsid();
        //通过id查询信息
        Goods goods = this.goodsService.getById(goodsid);
        goods.setNumber(goods.getNumber()+inport.getNumber());
        this.goodsService.updateGoods(goods);
        return inport;
    }

    @Override
    public DataGridView queryAllInport(InportVo inportVo) {
        IPage<Inport> page = new Page<>(inportVo.getPage(),inportVo.getLimit());
        QueryWrapper<Inport>qw = new QueryWrapper<>();

        qw.eq(inportVo.getGoodsid()!=null,"goodsid",inportVo.getGoodsid());
        qw.eq(inportVo.getProviderid()!=null,"providerid",inportVo.getProviderid());
        qw.ge(inportVo.getStartTime()!=null,"inporttime",inportVo.getStartTime());
        qw.le(inportVo.getEndTime()!=null,"inporttime",inportVo.getEndTime());
        qw.orderByDesc("inporttime");
        this.inportMapper.selectPage(page,qw);
        qw.orderByDesc("inporttime");
        List<Inport> records = page.getRecords();
        for (Inport record : records) {
            if(record.getGoodsid()!=null){
                Goods goods = this.goodsService.getById(record.getGoodsid());
                record.setGoodsname(goods.getGoodsname());
                record.setSize(goods.getSize());
            }
            if(null!= record.getProviderid()){
                Provider provider = this.providerService.getById(record.getProviderid());
                record.setProvidername(provider.getProvidername());
            }
        }
        return new DataGridView(page.getTotal(),records);
    }


    @Override
    public boolean removeById(Serializable id) {
        Inport inport = this.inportMapper.selectById(id);
        Goods goods = this.goodsService.getById(inport.getGoodsid());
        goods.setNumber(goods.getNumber()-inport.getNumber());
        this.goodsService.updateGoods(goods);
        return super.removeById(id);
    }
}
