package com.yuxiao.blog.dao;

import com.yuxiao.blog.bean.Category;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ${邹} on 2018/10/30.
 * 描述：
 */
@Repository
public interface CategoryDao {
    // 查看所有
    List<Category> list();
    // 根据id查询
    List<Category> selectById(Long id);
    // 根据分类名模糊查询
    List<Category> listByName(@Param("name") String name);
    // 查询分类名
    Category selectName(@Param("name") String name);
    // 添加分类
    long add(Category category);
    // 删除
    long delete(Long id);
    // 修改
    long update(Category category);
}
