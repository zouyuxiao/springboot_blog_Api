package com.yuxiao.blog.service;

import com.yuxiao.blog.bean.Reply;

import java.util.List;

/**
 * Created by ${邹} on 2018/11/13.
 * 描述：
 */
public interface ReplyService {
    // 查询所有
    List<Reply> list();
    // 添加
    long add(Reply reply);
    // 删除
    long delete(Long id);
    // 根据id查询
    List<Reply> selectById(Long id);
}
