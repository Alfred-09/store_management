package com.alfred.system.service;

import com.alfred.system.common.DataGridView;
import com.alfred.system.domain.Loginfo;
import com.alfred.system.vo.LoginfoVo;
import com.baomidou.mybatisplus.extension.service.IService;
    /**
 * @author Alfred
 * @date 2020/5/10 11:09
 */
public interface LoginfoService extends IService<Loginfo>{


        DataGridView queryAllLoginfo(LoginfoVo loginfoVo);
    }
