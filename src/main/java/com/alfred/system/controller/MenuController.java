package com.alfred.system.controller;

import com.alfred.system.common.Contant;
import com.alfred.system.common.DataGridView;
import com.alfred.system.common.ResultObj;
import com.alfred.system.domain.Menu;
import com.alfred.system.service.MenuService;
import com.alfred.system.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Alfred
 * @date 2020/5/13 10:28
 */
@RestController
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private MenuService menuService;



    /**
     * 查询菜单和权限
     * @param menuVo
     * @return
     */
    @RequestMapping("loadAllMenu")
    public Object loadAllMenu(MenuVo menuVo){
        return this.menuService.queryAllMenu(menuVo);
    }

    /**
     * 查询菜单
     * @param menuVo
     * @return
     */
    @RequestMapping("loadMenu")
    public Object loadMenu(MenuVo menuVo){
        List<Menu> menus = this.menuService.queryAllMenuForList();

        return new DataGridView(Long.valueOf(menus.size()),menus);
    }

    /**
     * 查询排序吗
     * @return
     */
    @GetMapping("queryMenuMaxOrderNum")
    public Object queryMenuMaxOrderNum(){
        Integer maxValue = this.menuService.queryMenuMaxOrderNum();
        return new DataGridView(maxValue+1);
    }

    /**
     * 添加菜单和权限
     * @param menu
     * @return
     */
    @PostMapping("addMenu")
    public ResultObj addMenu(Menu menu){
        try {
            if(menu.getType().equals("topmenu")){
                menu.setPid(0);
            }
            menu.setSpread(Contant.SPREAD_FALSE);
            menu.setAvailable(Contant.AVAILABLE_TRUE);
            this.menuService.saveMenu(menu);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    @PostMapping("updateMenu")
    public ResultObj updateMenu(Menu menu){
        try {
            this.menuService.updateMenu(menu);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
    /**
     * 根据ID查询当前菜单和权限的子菜单和权限的个数
     */
    @GetMapping("getMenuChildrenCountById")
    public Object getMenuChildrenCountById(Integer id){
        Integer count=this.menuService.queryMenuChildrenCountById(id);
        return new DataGridView(count);
    }

    @GetMapping("getMenuById")
    public Object getMenuById(Integer id){
        return new DataGridView(this.menuService.getById(id));
    }
    @RequestMapping("deleteMenu")
    public ResultObj deleteMenu(Integer id){
        try {
            this.menuService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
