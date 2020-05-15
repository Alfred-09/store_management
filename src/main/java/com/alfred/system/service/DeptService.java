package com.alfred.system.service;

import com.alfred.system.common.DataGridView;
import com.alfred.system.domain.Dept;
import com.alfred.system.vo.DeptVo;
import com.baomidou.mybatisplus.extension.service.IService;
    /**
 * @author Alfred
 * @date 2020/5/13 10:21
 */
public interface DeptService extends IService<Dept>{


        DataGridView queryAllDept(DeptVo deptVo);

        Dept saveDept(Dept dept);

        Integer queryDeptMaxOrderNum();

        Dept updateDept(Dept dept);

        Integer queryDeptChildrenCountById(Integer id);
    }
