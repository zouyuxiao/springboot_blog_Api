package com.yuxiao.blog.service.impl;

import com.yuxiao.blog.bean.Comment;
import com.yuxiao.blog.dao.CommentDao;
import com.yuxiao.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ${邹} on 2018/10/31.
 * 描述：
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;
    @Override
    public List<Comment> list() {
        return commentDao.list();
    }

    @Override
    public List<Comment> selectById(Long id) {
        return commentDao.selectById( id );
    }

    @Override
    public long add(Comment comment) {
        return commentDao.add( comment );
    }

    @Override
    public long update(Comment comment) {
        return commentDao.update( comment );
    }

    @Override
    public long delete(Long id) {
        return commentDao.delete( id );
    }
}
