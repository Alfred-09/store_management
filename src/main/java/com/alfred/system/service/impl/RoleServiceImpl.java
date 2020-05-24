package com.alfred.system.service.impl;

import com.alfred.system.common.DataGridView;
import com.alfred.system.domain.Role;
import com.alfred.system.mapper.RoleMapper;
import com.alfred.system.service.RoleService;
import com.alfred.system.vo.RoleVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Alfred
 * @date 2020/5/19 10:50
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService{

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role updateRole(Role role) {
        this.roleMapper.updateById(role);
        return role;
    }

    @Override
    public Role saveRole(Role role) {
        this.roleMapper.insert(role);
        return role;
    }

    @Override
    public DataGridView queryAllRole(RoleVo roleVo) {
        IPage<Role> page = new Page<>(roleVo.getPage(),roleVo.getLimit());
        QueryWrapper<Role> qw = new QueryWrapper<>();
        qw.eq(roleVo.getAvailable()!=null,"available",roleVo.getAvailable());
        qw.like(StringUtils.isNotBlank(roleVo.getName()),"name",roleVo.getName());
        qw.like(StringUtils.isNotBlank(roleVo.getRemark()),"remark",roleVo.getRemark());
        this.roleMapper.selectPage(page,qw);
        return new DataGridView(page.getTotal(),page.getRecords());
    }



    @Override
    public boolean removeById(Serializable id) {
        //根据角色id删除角色和菜单之间的关系
        roleMapper.deleteRoleMenuByRid(id);
        //根据橘色id删除角色和用户之间的关系
        roleMapper.deleteRoleUserByRid(id);
        return super.removeById(id);
    }

    @Override
    public List<Integer> queryMenuIdsByRid(Integer id) {

        return this.roleMapper.queryMenuIdsByRid(id);
    }

    /**
     * 保存角色和菜单之间的关系
     * @param rid
     * @param mids
     */
    @Override
    public void saveRoleMenu(Integer rid, Integer[] mids) {
        this.roleMapper.deleteRoleMenuByRid(rid);
        if(mids!=null&&mids.length>0){
            for (Integer mid : mids) {
                this.roleMapper.insertRoleMenu(rid,mid);
            }
        }
    }

    @Override
    public DataGridView queryAllAvailableRoleNoPage(RoleVo roleVo) {
        QueryWrapper<Role> qw=new QueryWrapper<>();
        qw.eq(roleVo.getAvailable()!=null,"available",roleVo.getAvailable());
        List<Role> roles = this.roleMapper.selectList(qw);
        //根据用户ID查询已拥有的角色ID
        List<Integer> roleIds=this.roleMapper.queryRoleIdsByUserId(roleVo.getUserId());

        List<Map<String,Object>> lists=new ArrayList<>();

        for (Role role : roles) {
            Boolean LAY_CHECKED=false;
            for (Integer roleId : roleIds) {
                if(role.getId().equals(roleId)){
                    LAY_CHECKED=true;
                    break;
                }
            }
            Map<String,Object> map=new HashMap<>();
            map.put("id",role.getId());
            map.put("name",role.getName());
            map.put("remark",role.getRemark());
            map.put("LAY_CHECKED",LAY_CHECKED);
            lists.add(map);
        }
        return new DataGridView(Long.valueOf(lists.size()),lists);
    }

}
