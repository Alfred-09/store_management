package com.alfred.system.service.impl;

import com.alfred.system.common.DataGridView;
import com.alfred.system.domain.Dept;
import com.alfred.system.mapper.DeptMapper;
import com.alfred.system.service.DeptService;
import com.alfred.system.vo.DeptVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @author Alfred
 * @date 2020/5/13 10:21
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService{

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public DataGridView queryAllDept(DeptVo deptVo) {
        QueryWrapper<Dept> qw = new QueryWrapper();
        qw.like(StringUtils.isNotBlank(deptVo.getTitle()),"title",deptVo.getTitle());
        qw.orderByAsc("ordernum");
        List<Dept> depts = this.deptMapper.selectList(qw);
        return new DataGridView(Long.valueOf(depts.size()),depts);
    }

    /**
     * 添加部门
     * @param dept
     * @return
     */
    @Override
    public Dept saveDept(Dept dept) {
         this.deptMapper.insert(dept);
        return dept;
    }

    @Override
    public Integer queryDeptMaxOrderNum() {

        return this.deptMapper.queryDeptMaxOrderNum();
    }

    @Override
    public Dept updateDept(Dept dept) {
        this.deptMapper.updateById(dept);
        return dept;
    }

    @Override
    public Integer queryDeptChildrenCountById(Integer id) {
        return null;
    }

    @Override
    public Dept getById(Serializable id) {
        return super.getById(id);
    }

    //@CacheEvict
    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }
}
