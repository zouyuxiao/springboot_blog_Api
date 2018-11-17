package com.yuxiao.blog.service;

import com.yuxiao.blog.bean.Comment;
import com.yuxiao.blog.bean.Contents;

import java.util.List;

/**
 * Created by ${邹} on 2018/10/31.
 * 描述：
 */
public interface ContentsService {
    // 查询所有
    List<Contents> list();
    long list2();

    // 根据标题模糊查询
    List<Contents> selectByTitle(String title);
    // 根据状态查询
    List<Contents> selectByStatus(String status);
    // 根据id查询
    List<Contents> selectById(Long id);
    // 添加
    long add(Contents contents);
    // 删除
    long delete(Long id);
    // 修改
    long update(Contents contents);
    // 修改状态
    long updateStatus(Contents contents);
    // 查询所有评论
    List<Comment> selectByContentId(Long id);
}
