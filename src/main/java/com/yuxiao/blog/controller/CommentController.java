package com.yuxiao.blog.controller;

import com.github.pagehelper.PageHelper;
import com.yuxiao.blog.bean.Comment;
import com.yuxiao.blog.bean.MyPageInfo;
import com.yuxiao.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * Created by ${邹} on 2018/11/3.
 * 描述：评论
 */
@RestController
@RequestMapping("/api/comment/")
public class CommentController extends BaseApiController {
    @Autowired
    private CommentService commentService;

    // 查询所有评论
    @GetMapping("list")
    public Map<String,Object> list(@RequestParam(defaultValue = "1")Integer page_num,
                                   @RequestParam(defaultValue = "10")Integer page_size){
        PageHelper.startPage( page_num,page_size );
        return onDataResp( new MyPageInfo<Comment>( commentService.list()));
    }

    // 根据id查询评论
    @GetMapping("selectById/{id}")
    public Map<String,Object> selectById(@RequestParam(defaultValue = "1")Integer page_num,
                                   @RequestParam(defaultValue = "10")Integer page_size,
                                         @PathVariable Long id){
        PageHelper.startPage( page_num,page_size );
        return onDataResp( new MyPageInfo<Comment>( commentService.selectById( id )));
    }

    // 删除评论
    @GetMapping("delete")
    public Map<String,Object> delete(@RequestParam Long id){
        commentService.delete( id );
        return onSuccessRep( "删除成功" );
    }

    // 添加评论
    @PostMapping("add")
    public Map<String,Object> add(@RequestParam String content, @RequestParam Long uId){
        if (content == null || content.trim().length() == 0) return onBadResp( "内容不能为空" );
        if (uId == null) return onBadResp( "用户id不能为空" );
        Comment comment = new Comment();
        comment.setContent( content );
        comment.setTime(new Date());
        comment.setuId( uId );
        if (commentService.add( comment ) > 0) return onSuccessRep( "添加成功" );
        return onBadResp( "添加失败" );
    }
}
