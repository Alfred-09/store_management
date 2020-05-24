package com.alfred.system.service;

import com.alfred.system.common.DataGridView;
import com.alfred.system.domain.Menu;
import com.alfred.system.vo.MenuVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
@author  Alfred
@date  2020/4/27 12:14
*/
public interface MenuService extends IService<Menu>{


    /**
     * 全查询
     * @return
     */
    List<Menu>queryAllMenuForList();

    /**
     * 根据id查询
     * @param id
     * @return
     */
    List<Menu> queryMenuForListUserById(Integer id);

    DataGridView queryAllMenu(MenuVo menuVo);

    Integer queryMenuMaxOrderNum();

    Menu saveMenu(Menu menu);

    Menu updateMenu(Menu menu);

    Integer queryMenuChildrenCountById(Integer id);


}
