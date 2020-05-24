package com.alfred.system.service;

import com.alfred.system.common.DataGridView;
import com.alfred.system.domain.Role;
import com.alfred.system.vo.RoleVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author Alfred
 * @date 2020/5/19 10:50
 */
public interface RoleService extends IService<Role>{


        Role updateRole(Role role);

        Role saveRole(Role role);

        DataGridView queryAllRole(RoleVo roleVo);

        List<Integer> queryMenuIdsByRid(Integer id);

        void saveRoleMenu(Integer rid, Integer[] mids);

        DataGridView queryAllAvailableRoleNoPage(RoleVo roleVo);
}
