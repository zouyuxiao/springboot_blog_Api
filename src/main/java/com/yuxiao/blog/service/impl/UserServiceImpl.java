package com.yuxiao.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuxiao.blog.bean.User;
import com.yuxiao.blog.dao.UserDao;
import com.yuxiao.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ${邹} on 2018/10/25.
 * 描述：
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    public long reg(User user) {
        return userDao.reg( user );
    }

    @Override
    public long deleteById(Long id) {
        return userDao.deleteById( id );
    }

    @Override
    public User selectByUsername(String username) {
        return userDao.selectByUsername(username);
    }

    @Override
    public User selectByEmail(String email) {
        return userDao.selectByEmail( email );
    }

    @Override
    public User selectByPhone(String phone) {
        return userDao.selectByPhone( phone );
    }

    @Override
    public User login(String username, String password) {
        return userDao.login( username,password );
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername( username );
    }

    @Override
    public List<User> list() {
        return userDao.list();
    }

    @Override
    public User select(User user) {
        return userDao.select(user);
    }

    @Override
    public List<User> selectUsername(String username) {
        return userDao.selectUsername(username);
    }

    @Override
    public List<User> selectById(Long id) {
        return userDao.selectById( id );
    }

    @Override
    public long update(User user) {
        return userDao.update( user );
    }

    @Override
    public PageInfo<User> findAllUser(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userDao.list();
        PageInfo result = new PageInfo(users);
        return result;
    }

    @Override
    public User findById(Long id) {
        return userDao.findById( id );
    }

}
