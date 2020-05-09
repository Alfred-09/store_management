package com.alfred.system.controller;

import com.alfred.system.common.ActiveUser;
import com.alfred.system.common.Contant;
import com.alfred.system.common.MenuTreeNode;
import com.alfred.system.common.ResultObj;
import com.alfred.system.domain.Menu;
import com.alfred.system.domain.User;
import com.alfred.system.service.MenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Alfred
 * @date 2020/4/28 12:07
 */
@Controller
@CrossOrigin(allowCredentials = "true")//跨域注解
@RequestMapping(value = "login")
public class LoginController {

    @Autowired
    private MenuService menuService;


  /**
   * 用户登录
   * @param loginname
   * @param password
   * @return
   */
  @RequestMapping("doLogin")
  @ResponseBody
  public ResultObj doLogin(String loginname, String password){

    try {
      Subject subject = SecurityUtils.getSubject();
      //分装用户的数据
      UsernamePasswordToken loginToken = new UsernamePasswordToken(loginname,password);
      subject.login(loginToken);
      //ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
      //得到shiro的sessionid==token
      String token = subject.getSession().getId().toString();

      System.out.println(token);
      //成功的话
      return new ResultObj(200,"登录成功",token);
    } catch (AuthenticationException e) {
      e.printStackTrace();
      return new ResultObj(-1,"用户名或密码不正确");
    }
  }

    /**
     * 加载菜单
     * @return
     */
    @RequestMapping("loadIndexMenu")
    @ResponseBody
    public Object loadIndexMenu(){
        //得到当前登陆的用户
        Subject subject = SecurityUtils.getSubject();
        ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
        User user=activeUser.getUser();
        if(null==user){
            return null;
        }
        List<Menu> menus=null;

        if(user.getType().equals(Contant.USER_TYPE_SUPER)){//超级管理员
            menus=menuService.queryAllMenuForList();
        }else{
            menus=menuService.queryMenuForListUserById(user.getId());
        }

        List<MenuTreeNode> treeNodes=new ArrayList<>();

        for (Menu m : menus) {
            Boolean spread=m.getSpread()==Contant.SPREAD_TRUE?true:false;
            treeNodes.add(new MenuTreeNode(m.getId(),m.getPid(),m.getTitle(),m.getHref(),m.getIcon(),spread,m.getTarget(),m.getTypecode()));
        }
        List<MenuTreeNode> nodes = MenuTreeNode.MenuTreeNodeBuilder.bulid(treeNodes, 0);
        Map<String,Object> res=new HashMap<>();
        for (MenuTreeNode n : nodes) {
            res.put(n.getTypecode(),n);
        }
        return res;
    }

    /**
     * 验证当前token是否登录
     * @return
     */
    @RequestMapping("checkLogin")
    @ResponseBody
    public ResultObj checkLogin(){
        //得到当前登录的用户
        Subject subject = SecurityUtils.getSubject();
        //判断是否认证
        if (subject.isAuthenticated()) {
            return ResultObj.IS_LOGIN;
        }else {
            return ResultObj.UN_LOGIN;
        }


    }

}
