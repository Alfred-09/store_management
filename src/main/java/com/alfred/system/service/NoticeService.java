package com.alfred.system.service;

import com.alfred.system.common.DataGridView;
import com.alfred.system.domain.Notice;
import com.alfred.system.vo.NoticeVo;
import com.baomidou.mybatisplus.extension.service.IService;
    /**
 * @author Alfred
 * @date 2020/5/12 9:37
 */
public interface NoticeService extends IService<Notice>{


        DataGridView queryAllNotice(NoticeVo noticeVo);
    }
