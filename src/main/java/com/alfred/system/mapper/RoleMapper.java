package com.alfred.system.mapper;

import com.alfred.system.domain.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * @author Alfred
 * @date 2020/5/19 10:50
 */
public interface RoleMapper extends BaseMapper<Role> {
    void deleteRoleMenuByRid(Serializable id);

    void deleteRoleMenuByMid(Serializable id);

    void deleteRoleUserByRid(Serializable id);

    void deleteRoleUserByUid(Serializable id);

    List<Integer> queryMenuIdsByRid(Integer id);

    void insertRoleMenu(@Param("rid") Integer rid, @Param("mid") Integer mid);

    List<Integer> queryRoleIdsByUserId(Integer userId);

    List<Integer> queryMenuIdsByRids(@Param("roleIds") List<Integer> roleIds);
}