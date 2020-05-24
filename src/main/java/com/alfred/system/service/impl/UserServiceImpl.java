package com.alfred.system.service.impl;

import com.alfred.system.common.AppUtils;
import com.alfred.system.common.Contant;
import com.alfred.system.common.DataGridView;
import com.alfred.system.domain.Dept;
import com.alfred.system.domain.User;
import com.alfred.system.mapper.RoleMapper;
import com.alfred.system.mapper.UserMapper;
import com.alfred.system.service.DeptService;
import com.alfred.system.service.UserService;
import com.alfred.system.vo.UserVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
@author  Alfred
@date  2020/4/28 11:44
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

 @Autowired
 private UserMapper userMapper;

@Autowired
private RoleMapper roleMapper;

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
  @Override
  public DataGridView queryAllUser(UserVo userVo) {
    IPage<User> page=new Page<>(userVo.getPage(),userVo.getLimit());
    QueryWrapper<User> qw=new QueryWrapper<>();
    qw.eq("type", Contant.USER_TYPE_NORMAL);
    qw.eq(null!=userVo.getDeptid(),"deptid",userVo.getDeptid());
    qw.like(StringUtils.isNotBlank(userVo.getName()),"name",userVo.getName());
    qw.like(StringUtils.isNotBlank(userVo.getAddress()),"address",userVo.getAddress());
    qw.like(StringUtils.isNotBlank(userVo.getRemark()),"remark",userVo.getRemark());
    this.userMapper.selectPage(page,qw);
    List<User> records = page.getRecords();
    DeptService deptService= AppUtils.getContext().getBean(DeptService.class);
    for (User record : records) {
      if(null!=record.getDeptid()){
        Dept dept = deptService.getById(record.getDeptid());
        record.setDeptname(dept.getTitle());
      }
    }
    return new DataGridView(page.getTotal(),records);
  }

  @Override
  public User saveUser(User user) {
    this.userMapper.insert(user);
    return user;
  }

  @Override
  public User updateUser(User user) {
    this.userMapper.updateById(user);
    return user;
  }

  @Override
  public Integer queryUserMaxOrderNum() {
    return this.userMapper.queryUserMaxOrderNum();
  }




  @Override
  public boolean removeById(Serializable id) {
    //根据用户ID删除角色和用户中间表的数据
    roleMapper.deleteRoleUserByUid(id);
    return super.removeById(id);
  }

  @Override
  public void saveUserRole(Integer uid, Integer[] rids) {
    //根据用户ID删除角色和用户中间表的数据
    roleMapper.deleteRoleUserByUid(uid);
    if(null!=rids&&rids.length>0){
      for (Integer rid : rids) {
        this.userMapper.saveUserRole(uid,rid);
      }
    }

  }

}
