package com.alfred.system.service;

import com.alfred.system.domain.User;
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
    User queryUserByLoginName(String loginname);

}
