package com.alfred.system.controller;

import com.alfred.system.common.Contant;
import com.alfred.system.common.DataGridView;
import com.alfred.system.common.ResultObj;
import com.alfred.system.domain.Role;
import com.alfred.system.service.RoleService;
import com.alfred.system.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author Alfred
 * @date 2020/5/19 10:52
 */
@RestController
@RequestMapping("role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 查询角色
     * @param roleVo
     * @return
     */
    @RequestMapping("loadAllRole")
    public DataGridView loadAllRole(RoleVo roleVo){
        return this.roleService.queryAllRole(roleVo);
    }

    /**
     * 查询所有可用角色
     * @param roleVo
     * @return
     */
    @RequestMapping("loadAllAvailableRoleNoPage")
    public Object loadAllAvailableRoleNoPage(RoleVo roleVo){
        roleVo.setAvailable(Contant.AVAILABLE_TRUE);
        return this.roleService.queryAllAvailableRoleNoPage(roleVo);
    }
    /**
     * 添加角色
     * @param role
     * @return
     */
    @PostMapping("addRole")
    public ResultObj addRole( Role role){
        try {
            role.setCreatetime(new Date());
            role.setAvailable(Contant.AVAILABLE_TRUE);
            this.roleService.saveRole(role);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 修改角色
     * @param role
     * @return
     */
    @PostMapping("updateRole")
    public ResultObj updateRole(Role role){
        try {
            this.roleService.updateRole(role);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @RequestMapping("deleteRole")
    public ResultObj deleteRole(Integer id){
        try {
            this.roleService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 根据角色id查询角色拥有的菜单和权限id
     * @param id
     * @return
     */
    @RequestMapping("queryMenuIdsByRid")
    public Object queryMenuIdsByRid(Integer id){
        List<Integer> mids = this.roleService.queryMenuIdsByRid(id);
        return new DataGridView(mids);
    }

    @RequestMapping("saveRoleMenu")
    public ResultObj saveRoleMenu(Integer rid,Integer[] mids){
        try {
            this.roleService.saveRoleMenu(rid,mids);
            return ResultObj.DISPATH_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DISPATH_ERROR;
        }
    }


}
