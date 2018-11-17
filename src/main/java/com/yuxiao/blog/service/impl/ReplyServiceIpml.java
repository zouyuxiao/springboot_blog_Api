package com.yuxiao.blog.service.impl;

import com.yuxiao.blog.bean.Reply;
import com.yuxiao.blog.dao.ReplyDao;
import com.yuxiao.blog.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ${邹} on 2018/11/13.
 * 描述：
 */
@Service
public class ReplyServiceIpml implements ReplyService {
    @Autowired
    private ReplyDao replyDao;
    @Override
    public List<Reply> list() {
        return replyDao.list();
    }

    @Override
    public long add(Reply reply) {
        return replyDao.add( reply );
    }

    @Override
    public long delete(Long id) {
        return replyDao.delete( id );
    }

    @Override
    public List<Reply> selectById(Long id) {
        return replyDao.selectById( id );
    }
}
