package com.yuxiao.blog.dao;

import com.yuxiao.blog.bean.Comment;
import com.yuxiao.blog.bean.Contents;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ${邹} on 2018/10/31.
 * 描述：
 */
@Repository
public interface ContentsDao {
    // 查询所有
    List<Contents> list();
    long list2();
    // 根据标题模糊查询
    List<Contents> selectByTitle(@Param("title") String title);
    // 根据状态查询
    List<Contents> selectByStatus(@Param("status") String status);
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
