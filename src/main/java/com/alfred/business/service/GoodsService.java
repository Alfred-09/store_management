package com.alfred.business.service;

import com.alfred.business.domain.Goods;
import com.alfred.business.vo.GoodsVo;
import com.alfred.system.common.DataGridView;
import com.baomidou.mybatisplus.extension.service.IService;
    /**
 * @author Alfred
 * @date 2020/5/28 10:37
 */
public interface GoodsService extends IService<Goods>{


        DataGridView queryAllGoods(GoodsVo goodsVo);

        Goods saveGoods(Goods goods);

        Goods updateGoods(Goods goods);

        DataGridView getAllAvailableGoods();

        DataGridView getGoodsByProviderId(Integer providerid);
    }
