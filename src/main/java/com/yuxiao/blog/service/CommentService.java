package com.yuxiao.blog.service;

import com.yuxiao.blog.bean.Comment;

import java.util.List;

/**
 * Created by ${邹} on 2018/10/31.
 * 描述：
 */
public interface CommentService {
    // 查询所有
    List<Comment> list();
    // 根据id查询
    List<Comment> selectById(Long id);
    // 添加
    long add(Comment comment);
    // 修改
    long update(Comment comment);
    // 删除
    long delete(Long id);
}
