package com.alfred.system.mapper;

import com.alfred.system.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;

/**
@author  Alfred
@date  2020/4/28 11:44
*/
public interface UserMapper extends BaseMapper<User> {

    Integer queryUserMaxOrderNum();

    void saveUserRole(@Param("uid") Integer uid, @Param("rid") Integer rid);
}