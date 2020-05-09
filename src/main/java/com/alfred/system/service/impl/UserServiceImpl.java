package com.alfred.system.service.impl;

import com.alfred.system.domain.User;
import com.alfred.system.mapper.UserMapper;
import com.alfred.system.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
@author  Alfred
@date  2020/4/28 11:44
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

  private Log log = LogFactory.getLog(UserServiceImpl.class);

  /**
   * 根据用户登录名查询用户的信息
   * @param loginname
   * @return
   */
  @Override
  public User queryUserByLoginName(String loginname) {
    UserMapper userMapper = this.getBaseMapper();

    QueryWrapper<User> qw = new QueryWrapper<>();
    //如果为空
    if(StringUtils.isBlank(loginname)){
      //记录日志
      log.error("登录名不能为空");
      return null;
    }
    qw.eq("loginname",loginname);
    User user = userMapper.selectOne(qw);
    System.out.println("2285439455 = " + user);
    return user;
  }


}
