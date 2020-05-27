package com.alfred.system.controller;

import com.alfred.system.common.Contant;
import com.alfred.system.common.DataGridView;
import com.alfred.system.common.ResultObj;
import com.alfred.system.domain.Dept;
import com.alfred.system.service.DeptService;
import com.alfred.system.vo.DeptVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alfred
 * @date 2020/5/13 10:28
 */
@RestController
@RequestMapping("dept")
public class DeptController {

    @Autowired
    private DeptService deptService;



    /**
     * 查询部门
     * @param deptVo
     * @return
     */
    @RequestMapping("loadAllDept")
    public Object loadAllDept(DeptVo deptVo){
        return this.deptService.queryAllDept(deptVo);
    }

    /**
     * 查询排序吗
     * @return
     */
    @GetMapping("queryDeptMaxOrderNum")
    public Object queryDeptMaxOrderNum(){
        Integer maxValue = this.deptService.queryDeptMaxOrderNum();
        return new DataGridView(maxValue+1);
    }

    /**
     * 添加部门
     * @param dept
     * @return
     */
    @PostMapping("addDept")
    @RequiresPermissions("dept:add")
    public ResultObj addDept(Dept dept){
        try {
            dept.setSpread(Contant.SPREAD_FALSE);
            dept.setAvailable(Contant.AVAILABLE_TRUE);
            this.deptService.saveDept(dept);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    @PostMapping("updateDept")
    @RequiresPermissions("dept:update")
    public ResultObj updateDept(Dept dept){
        try {
            this.deptService.updateDept(dept);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
    /**
     * 根据ID查询当前部门的子部门的个数
     */
    @GetMapping("getDeptChildrenCountById")
    public Object getDeptChildrenCountById(Integer id){
        Integer count=this.deptService.queryDeptChildrenCountById(id);
        return new DataGridView(count);
    }

    @GetMapping("getDeptById")
    public Object getDeptById(Integer id){
        return new DataGridView(this.deptService.getById(id));
    }
    @RequestMapping("deleteDept")
    @RequiresPermissions("dept:delete")
    public ResultObj deleteDept(Integer id){
        try {
            this.deptService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
