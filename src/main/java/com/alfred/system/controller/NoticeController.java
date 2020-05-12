package com.alfred.system.controller;

import com.alfred.system.common.ActiveUser;
import com.alfred.system.common.ResultObj;
import com.alfred.system.domain.Notice;
import com.alfred.system.service.NoticeService;
import com.alfred.system.vo.NoticeVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 公告控制器
 * @author Alfred
 * @date 2020/5/12 9:43
 */
@RestController
@RequestMapping("notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;


    /**
     * 全查询
     * @param noticeVo
     * @return
     */
    @RequestMapping("loadAllNotice")
    public Object loadAllNotice(NoticeVo noticeVo){
        return this.noticeService.queryAllNotice(noticeVo);
    }

    /**.
     * 添加公告
     * @param notice
     * @return
     */
    @RequestMapping("addNotice")
    public ResultObj addNotice(Notice notice){
        try {
            //前台传了两个字段后台需要4个字段
            notice.setCreatetime(new Date());
            Subject subject = SecurityUtils.getSubject();
            ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
            notice.setOpername(activeUser.getUser().getName());
            this.noticeService.save(notice);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 更新数据
     * @param notice
     * @return
     */
    @RequestMapping("updateNotice")
    public ResultObj updateNotice(Notice notice){
        try {
            this.noticeService.updateById(notice);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("deleteNotice")
    public ResultObj deleteNotice(Integer id){
        try {
            this.noticeService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping("batchDeleteNotice")
    public ResultObj batchDeleteNotice(Integer[] ids){
        try {
            List<Integer> idList = new ArrayList<>();
            for (Integer id : ids) {
                idList.add(id);
            }
            this.noticeService.removeByIds(idList);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
