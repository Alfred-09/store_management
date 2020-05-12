package com.alfred.system.service.impl;

import com.alfred.system.common.DataGridView;
import com.alfred.system.domain.Notice;
import com.alfred.system.mapper.NoticeMapper;
import com.alfred.system.service.NoticeService;
import com.alfred.system.vo.NoticeVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author Alfred
 * @date 2020/5/12 9:37
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService{

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public DataGridView queryAllNotice(NoticeVo noticeVo) {
        IPage<Notice> page=new Page<>(noticeVo.getPage(),noticeVo.getLimit());
        QueryWrapper<Notice> qw=new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(noticeVo.getTitle()),"title",noticeVo.getTitle());
        qw.like(StringUtils.isNotBlank(noticeVo.getOpername()),"opername",noticeVo.getOpername());
        qw.ge(noticeVo.getStartTime()!=null,"createtime",noticeVo.getStartTime());
        qw.le(noticeVo.getEndTime()!=null,"createtime",noticeVo.getEndTime());
        qw.orderByDesc("createtime");
        this.noticeMapper.selectPage(page,qw);
        return new DataGridView(page.getTotal(),page.getRecords());
    }


}
