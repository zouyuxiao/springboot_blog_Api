package com.yuxiao.blog.dao;

import com.yuxiao.blog.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ${邹} on 2018/10/25.
 * 描述：
 */
@Repository
public interface UserDao {
    // 注册
    long reg(User user);

    // 删除
    long deleteById(@Param("id") Long id);

    // 查询用户名
    User selectByUsername(@Param("username") String username);

    // 查询邮箱
    User selectByEmail(@Param("email") String email);
//
//    // 查询手机号
    User selectByPhone(@Param("phone") String phone);

    // 登录
    User login(@Param("username") String username, @Param("password") String password);
    User findByUsername(String username);

    // 查询所有
    List<User> list();
    User select(User user);

    // 模糊查询
    List<User> selectUsername(@Param("username") String username);

    // 根据id查询
    List<User> selectById(Long id);

    // 修改用户信息
    long update(User user);

    // 权限
    User findById(Long id);

}
