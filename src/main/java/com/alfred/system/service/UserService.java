package com.alfred.system.service;

import com.alfred.system.common.DataGridView;
import com.alfred.system.domain.User;
import com.alfred.system.vo.UserVo;
import com.baomidou.mybatisplus.extension.service.IService;
    /**
@author  Alfred
@date  2020/4/28 11:44
*/
public interface UserService extends IService<User>{

        /**
         * 根据用户登录名查询用户的信息
         * @param loginname
         * @return
         */
        /**
         * 根据用户登陆名查询用户信息
         */
        User queryUserByLoginName(String loginname);

        DataGridView queryAllUser(UserVo userVo);

        User saveUser(User user);

        User updateUser(User user);

        Integer queryUserMaxOrderNum();

        void saveUserRole(Integer uid, Integer[] rids);
    }
