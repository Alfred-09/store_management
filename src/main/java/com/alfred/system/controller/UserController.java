package com.alfred.system.controller;

import com.alfred.system.common.*;
import com.alfred.system.domain.User;
import com.alfred.system.service.UserService;
import com.alfred.system.vo.UserVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alfred
 * @date 2020/5/20 11:58
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 查询用户
     * @param userVo
     * @return
     */
    @RequestMapping("loadAllUser")
    public Object loadAllUser(UserVo userVo){
        return this.userService.queryAllUser(userVo);
    }


    /**
     * 查询部门最大的排序码
     */

    @GetMapping("queryUserMaxOrderNum")
    public Object queryUserMaxOrderNum(){
        Integer maxValue=this.userService.queryUserMaxOrderNum();
        return new DataGridView(maxValue+1);
    }

    /**
     * 添加用户
     */
   @PostMapping("addUser")
    public ResultObj addUser(User user){
        try {
            user.setSalt(MD5Utils.createUUID());
            user.setType(Contant.USER_TYPE_NORMAL);
            user.setPwd(MD5Utils.md5(Contant.DEFAULT_PWD,user.getSalt(),2));
            user.setAvailable(Contant.AVAILABLE_TRUE);
            user.setImgpath(Contant.DEFAULT_TITLE_IMAGE);
            this.userService.saveUser(user);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改用户
     */
    @PostMapping("updateUser")
    public ResultObj updateUser(User user){
        try {
            this.userService.updateUser(user);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 重置用户密码
     */
    @PostMapping("resetUserPwd")
    public ResultObj resetUserPwd(Integer id){
        try {
            User user=new User();
            user.setId(id);
            user.setSalt(MD5Utils.createUUID());
            user.setPwd(MD5Utils.md5(Contant.DEFAULT_PWD,user.getSalt(),2));
            this.userService.updateUser(user);
            return ResultObj.RESET_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.RESET_ERROR;
        }
    }


    /**
     * 删除
     */
    @RequestMapping("deleteUser")
    public ResultObj deleteUser(Integer id){
        try {
            this.userService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 保存用户和角色之间的关系
     */
    @RequestMapping("saveUserRole")
    public ResultObj saveUserRole(Integer uid,Integer[] rids){
        try {

            this.userService.saveUserRole(uid,rids);
            return ResultObj.DISPATH_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DISPATH_ERROR;
        }
    }

    /**
     * 查询当前登陆的用户
     */
    @GetMapping("getCurrentUser")
    public Object getCurrentUser(){
        ActiveUser activeUser= (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        return new DataGridView(activeUser.getUser());
    }

}
