package com.yuxiao.blog.bean;

import java.util.List;

/**
 * Created by ${邹} on 2018/11/13.
 * 描述：评论回复类
 */
public class Reply {
    private Long id;
    private Long uId;
    private String content;
    private List<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
