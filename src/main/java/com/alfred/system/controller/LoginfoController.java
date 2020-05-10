package com.alfred.system.controller;

import com.alfred.system.service.LoginfoService;
import com.alfred.system.vo.LoginfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alfred
 * @date 2020年5月10日11:11:53
 */
@RestController
@CrossOrigin(allowCredentials = "true")//跨域注解
@RequestMapping(value = "loginfo")
public class LoginfoController {

    @Autowired
    private LoginfoService loginfoService;


    /**
     * 查询
     * @param loginfoVo
     * @return
     */
    @RequestMapping("loadAllLoginfo")
    public Object loadAllLoginfo(LoginfoVo loginfoVo){
        return this.loginfoService.queryAllLoginfo(loginfoVo);
    }

}
