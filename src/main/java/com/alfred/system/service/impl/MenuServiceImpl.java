package com.alfred.system.service.impl;

import com.alfred.system.common.Contant;
import com.alfred.system.domain.Menu;
import com.alfred.system.mapper.MenuMapper;
import com.alfred.system.service.MenuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

/**
@author  Alfred
@date  2020/4/27 12:14
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> queryAllMenuForList() {

        QueryWrapper<Menu> qw = new QueryWrapper();
        qw.eq("available", Contant.AVAILABLE_TRUE);
        qw.and(new Consumer<QueryWrapper<Menu>>() {
            @Override
            public void accept(QueryWrapper<Menu> menuQueryWrapper) {
                menuQueryWrapper.eq("type",Contant.MENU_TYPE_TOP)
                        .or().eq("type",Contant.MENU_TYPE_LEFT);
            }
        });
        qw.orderByAsc("ordernum");
        return this.menuMapper.selectList(qw);
    }

    @Override
    public List<Menu> queryMenuForListUserById(Integer id) {

        QueryWrapper<Menu> qw = new QueryWrapper();
        qw.eq("available", Contant.AVAILABLE_TRUE);
        qw.and(new Consumer<QueryWrapper<Menu>>() {
            @Override
            public void accept(QueryWrapper<Menu> menuQueryWrapper) {
                menuQueryWrapper.eq("type",Contant.MENU_TYPE_TOP)
                        .or().eq("type",Contant.MENU_TYPE_LEFT);
            }
        });
        qw.orderByAsc("ordernum");
        return this.menuMapper.selectList(qw);
    }
}
