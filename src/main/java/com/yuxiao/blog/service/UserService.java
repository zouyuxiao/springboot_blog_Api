package com.yuxiao.blog.service;

import com.github.pagehelper.PageInfo;
import com.yuxiao.blog.bean.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ${邹} on 2018/10/25.
 * 描述：
 */
@Repository
public interface UserService {
    // 注册
    long reg(User user);
    // 删除
    long deleteById(Long id);
    // 查询用户名
    User selectByUsername(String username);
    // 查询邮箱
    User selectByEmail(String email);
//    // 查询手机号
    User selectByPhone(String phone);
        // 登录
    User login(String username, String password);
    User findByUsername(String username);
    // 查询所有
    List<User> list();
    User select(User user);
    // 模糊查询
    List<User> selectUsername(String username);
    // 根据id查询
    List<User> selectById(Long id);
    // 修改用户信息
    long update(User user);
    // 分页查询
    PageInfo<User> findAllUser(int pageNum, int pageSize);
    //权限
    User findById(Long id);
}
