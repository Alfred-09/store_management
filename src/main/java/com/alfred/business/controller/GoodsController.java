package com.alfred.business.controller;

import com.alfred.business.domain.Goods;
import com.alfred.business.service.GoodsService;
import com.alfred.business.vo.GoodsVo;
import com.alfred.system.common.Contant;
import com.alfred.system.common.DataGridView;
import com.alfred.system.common.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alfred
 * @date 2020/5/28 10:44
 */
@RequestMapping("goods")
@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 查询
     * @return
     */
    @RequestMapping("loadAllGoods")
    public Object loadAllGoods(GoodsVo goodsVo){
        return this.goodsService.queryAllGoods(goodsVo);
    }

    /**
     * 添加
     * @return
     */
    @RequestMapping("addGoods")
    public ResultObj addCustomer(Goods goods){
        try {
            goods.setAvailable(Contant.AVAILABLE_TRUE);
            this.goodsService.saveGoods(goods);
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
    @RequestMapping("updateGoods")
    public ResultObj updateGoods(Goods goods){
        try {
            this.goodsService.updateGoods(goods);
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
    @RequestMapping("deleteGoods")
    public ResultObj deleteGoods(Integer id){
        try {
            this.goodsService.removeById(id);
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
    @RequestMapping("batchDeleteGoods")
    public ResultObj batchDeleteGoods(Integer[] ids){
        List<Integer> idsList = null;
        try {
            idsList = new ArrayList<>();
            for (Integer id : ids) {
                idsList.add(id);
            }
            this.goodsService.removeByIds(idsList);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }

    }

    /**
     * 查询所有可用的商品，不分页
     */
    @GetMapping("getAllAvailableGoods")
    public Object getAllAvailableGoods(){
        return this.goodsService.getAllAvailableGoods();
    }

    /**
     * 根据供应商id查询商品信息
     * @param providerid
     * @return
     */
    @GetMapping("getGoodsByProviderId")
    public Object getGoodsByProviderId(Integer providerid){
        return this.goodsService.getGoodsByProviderId(providerid);
    }

    /**
     * 根据商品ID查询商品信息
     */
    @GetMapping("getGoodsByGoodId")
    public Object getGoodsByGoodId(Integer goodsid){
        return new DataGridView(this.goodsService.getById(goodsid));
    }
}
