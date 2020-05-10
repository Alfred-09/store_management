package com.alfred.system.service.impl;

import com.alfred.system.common.DataGridView;
import com.alfred.system.domain.Loginfo;
import com.alfred.system.mapper.LoginfoMapper;
import com.alfred.system.service.LoginfoService;
import com.alfred.system.vo.LoginfoVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Alfred
 * @date 2020/5/10 11:09
 */
@Service
public class LoginfoServiceImpl extends ServiceImpl<LoginfoMapper, Loginfo> implements LoginfoService{

    @Autowired
    private LoginfoMapper loginfoMapper;


    @Override
    public DataGridView queryAllLoginfo(LoginfoVo loginfoVo) {
        IPage<Loginfo> page=new Page<>(loginfoVo.getPage(),loginfoVo.getLimit());
        QueryWrapper<Loginfo> qw=new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(loginfoVo.getLoginname()),"loginname",loginfoVo.getLoginname());
        qw.like(StringUtils.isNotBlank(loginfoVo.getLoginip()),"loginip",loginfoVo.getLoginip());
        qw.ge(loginfoVo.getStartTime()!=null,"logintime",loginfoVo.getStartTime());
        qw.le(loginfoVo.getEndTime()!=null,"logintime",loginfoVo.getEndTime());
        qw.orderByDesc("logintime");
        this.loginfoMapper.selectPage(page,qw);
        return new DataGridView(page.getTotal(),page.getRecords());
    }
}
