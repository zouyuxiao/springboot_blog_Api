package com.yuxiao.blog.dao;

import com.yuxiao.blog.bean.Reply;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ${邹} on 2018/11/13.
 * 描述：
 */
@Repository
public interface ReplyDao {
    // 查询所有
    List<Reply> list();
    // 添加
    long add(Reply reply);
    // 删除
    long delete(Long id);
    // 根据id查询
    List<Reply> selectById(Long id);
}
