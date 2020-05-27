package com.alfred.business.service;

import com.alfred.business.domain.Provider;
import com.alfred.business.vo.ProviderVo;
import com.alfred.system.common.DataGridView;
import com.baomidou.mybatisplus.extension.service.IService;
    /**
 * @author Alfred
 * @date 2020/5/27 10:29
 */
public interface ProviderService extends IService<Provider>{


        DataGridView queryAllProvider(ProviderVo providerVo);

        Provider saveProvider(Provider provider);

        Provider updateProvider(Provider provider);
    }
