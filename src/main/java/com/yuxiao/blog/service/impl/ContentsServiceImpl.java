package com.yuxiao.blog.service.impl;

import com.yuxiao.blog.bean.Comment;
import com.yuxiao.blog.bean.Contents;
import com.yuxiao.blog.dao.ContentsDao;
import com.yuxiao.blog.service.ContentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ${邹} on 2018/10/31.
 * 描述：
 */
@Service
public class ContentsServiceImpl implements ContentsService {
    @Autowired
    private ContentsDao contentsDao;
    @Override
    public List<Contents> list() {
        return contentsDao.list();
    }

    @Override
    public long list2() {
       return contentsDao.list2( );
    }

    @Override
    public List<Contents> selectByTitle(String title) {
        return contentsDao.selectByTitle( title );
    }

    @Override
    public List<Contents> selectByStatus(String status) {
        return contentsDao.selectByStatus( status );
    }

    @Override
    public List<Contents> selectById(Long id) {
        return contentsDao.selectById( id );
    }

    @Override
    public long add(Contents contents) {
        return contentsDao.add( contents );
    }

    @Override
    public long delete(Long id) {
        return contentsDao.delete( id );
    }

    @Override
    public long update(Contents contents) {
        return contentsDao.update( contents );
    }

    @Override
    public long updateStatus(Contents contents) {
        return contentsDao.updateStatus( contents );
    }

    @Override
    public List<Comment> selectByContentId(Long id) {
        return contentsDao.selectByContentId( id );
    }
}
