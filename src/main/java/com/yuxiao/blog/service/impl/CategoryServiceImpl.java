package com.yuxiao.blog.service.impl;

import com.yuxiao.blog.bean.Category;
import com.yuxiao.blog.dao.CategoryDao;
import com.yuxiao.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ${邹} on 2018/10/30.
 * 描述：
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;
    @Override
    public List<Category> list() {
        return categoryDao.list();
    }

    @Override
    public List<Category> selectById(Long id) {
        return categoryDao.selectById( id );
    }

    @Override
    public List<Category> listByName(String name) {
        return categoryDao.listByName( name );
    }

    @Override
    public long add(Category category) {
        return categoryDao.add( category );
    }

    @Override
    public long delete(Long id) {
        return categoryDao.delete( id );
    }

    @Override
    public long update(Category category) {
        return categoryDao.update( category );
    }

    @Override
    public Category selectName(String name) {
        return categoryDao.selectName( name );
    }
}
