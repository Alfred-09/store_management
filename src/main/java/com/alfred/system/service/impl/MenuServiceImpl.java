package com.alfred.system.service.impl;

import com.alfred.system.common.Contant;
import com.alfred.system.common.DataGridView;
import com.alfred.system.domain.Menu;
import com.alfred.system.mapper.MenuMapper;
import com.alfred.system.mapper.RoleMapper;
import com.alfred.system.service.MenuService;
import com.alfred.system.vo.MenuVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    private RoleMapper roleMapper;

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
        //根据userid查询角色id的集合
        List<Integer> roleIds=this.roleMapper.queryRoleIdsByUserId(id);
        //根据角色ID的集合，查询菜单的ID的集合
        if(null!=roleIds&&roleIds.size()>0){
            List<Integer> menuIds=this.roleMapper.queryMenuIdsByRids(roleIds);
            if(null!=menuIds&&menuIds.size()>0) {
                QueryWrapper<Menu> qw = new QueryWrapper<>();
                qw.eq("available", Contant.AVAILABLE_TRUE);
                qw.and(new Consumer<QueryWrapper<Menu>>() {
                    @Override
                    public void accept(QueryWrapper<Menu> menuQueryWrapper) {
                        menuQueryWrapper.eq("type", Contant.MENU_TYPE_TOP)
                                .or().eq("type", Contant.MENU_TYPE_LEFT);
                    }
                });
                qw.in("id",menuIds);
                qw.orderByAsc("ordernum");
                List<Menu> menus=this.menuMapper.selectList(qw);
                return menus;
            }else{
                return new ArrayList<>();
            }
        }else{
            return new ArrayList<>();
        }


    }

    @Override
    public DataGridView queryAllMenu(MenuVo menuVo) {
        QueryWrapper<Menu> qw = new QueryWrapper<>();
        qw.eq(menuVo.getAvailable()!=null,"available",menuVo.getAvailable());
        qw.orderByAsc("ordernum");
        List<Menu> menus = this.menuMapper.selectList(qw);
        return new DataGridView(Long.valueOf(menus.size()),menus);
    }

    @Override
    public Integer queryMenuMaxOrderNum() {
        return this.menuMapper.queryMenuMaxOrderNum();
    }

    @Override
    public Menu saveMenu(Menu menu) {
        this.menuMapper.insert(menu);
        return menu;
    }

    @Override
    public Menu updateMenu(Menu menu) {
        this.menuMapper.updateById(menu);
        return menu;
    }

    @Override
    public Integer queryMenuChildrenCountById(Integer id) {
        return null;
    }


}
