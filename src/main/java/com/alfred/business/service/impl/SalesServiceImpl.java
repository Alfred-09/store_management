package com.alfred.business.service.impl;

import com.alfred.business.domain.Customer;
import com.alfred.business.domain.Goods;
import com.alfred.business.domain.Sales;
import com.alfred.business.mapper.SalesMapper;
import com.alfred.business.service.CustomerService;
import com.alfred.business.service.GoodsService;
import com.alfred.business.service.SalesService;
import com.alfred.business.vo.SalesVo;
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
 * @date 2020/5/30 9:33
 */
@Service
public class SalesServiceImpl extends ServiceImpl<SalesMapper, Sales> implements SalesService{

    @Autowired
    private SalesMapper salesMapper;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private GoodsService goodsService;


    @Override
    public Sales saveSales(Sales sales) {
        this.salesMapper.insert(sales);
        //更新库存
        Integer goodsId=sales.getGoodsid();
        Goods goods = this.goodsService.getById(goodsId);
        goods.setNumber(goods.getNumber()-sales.getNumber());
        this.goodsService.updateGoods(goods);
        return sales;
    }

    @Override
    public DataGridView queryAllSales(SalesVo salesVo) {
        IPage<Sales> page=new Page<>(salesVo.getPage(),salesVo.getLimit());

        QueryWrapper<Sales> qw=new QueryWrapper<>();

        qw.eq(salesVo.getGoodsid()!=null,"goodsid",salesVo.getGoodsid());
        qw.eq(salesVo.getCustomerid()!=null,"customerid",salesVo.getCustomerid());

        qw.ge(salesVo.getStartTime()!=null,"salestime",salesVo.getStartTime());
        qw.le(salesVo.getEndTime()!=null,"salestime",salesVo.getEndTime());

        qw.orderByDesc("salestime");

        this.salesMapper.selectPage(page,qw);
        List<Sales> records = page.getRecords();
        for (Sales record : records) {
            if(null!=record.getGoodsid()){
                Goods goods = this.goodsService.getById(record.getGoodsid());
                record.setGoodsname(goods.getGoodsname());
                record.setSize(goods.getSize());
            }
            if(null!= record.getCustomerid()){
                Customer customer = this.customerService.getById(record.getCustomerid());
                record.setCustomername(customer.getCustomername());
            }
        }
        return new DataGridView(page.getTotal(),records);
    }

    @Override
    public Sales updateSales(Sales sales) {
        Sales oldObj=this.salesMapper.selectById(sales.getId());
        Goods goods = this.goodsService.getById(oldObj.getGoodsid());
        goods.setNumber(goods.getNumber()+oldObj.getNumber()-sales.getNumber());
        this.goodsService.updateGoods(goods);
        this.salesMapper.updateById(sales);
        return sales;
    }

    @Override
    public boolean removeById(Serializable id) {
        Sales sales = this.salesMapper.selectById(id);
        Goods goods = this.goodsService.getById(sales.getGoodsid());
        goods.setNumber(goods.getNumber()+sales.getNumber());
        this.goodsService.updateGoods(goods);
        return super.removeById(id);
    }
}
