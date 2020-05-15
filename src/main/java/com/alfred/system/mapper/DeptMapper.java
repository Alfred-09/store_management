package com.alfred.system.mapper;

import com.alfred.system.domain.Dept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author Alfred
 * @date 2020/5/13 10:21
 */
public interface DeptMapper extends BaseMapper<Dept> {
    Integer queryDeptMaxOrderNum();

    Integer queryDeptChildrenCountById(Integer id);
}