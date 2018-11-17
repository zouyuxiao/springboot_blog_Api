package com.yuxiao.blog.controller;

import com.github.pagehelper.PageHelper;
import com.yuxiao.blog.bean.MyPageInfo;
import com.yuxiao.blog.bean.Reply;
import com.yuxiao.blog.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by ${邹} on 2018/11/13.
 * 描述：
 */
@RestController
@RequestMapping("/api/reply/")
public class ReplyController extends BaseApiController {
    @Autowired
    private ReplyService replyService;

    @GetMapping("list")
    @ResponseBody
    public Map<String,Object> list(@RequestParam(defaultValue = "1") Integer page_num,
                                   @RequestParam(defaultValue = "10") Integer page_size){
        PageHelper.startPage( page_num,page_size );
        return onDataResp( new MyPageInfo<Reply>( replyService.list() ));
    }

    @GetMapping("delete")
    @ResponseBody
    public Map<String,Object> selectByIdlist(@RequestParam Long id){
        replyService.delete( id );
        return onSuccessRep( "删除成功" );
    }

    @GetMapping("selectById/{id}")
    @ResponseBody
    public Map<String,Object> selectById(@RequestParam(defaultValue = "1") Integer page_num,
                                         @RequestParam(defaultValue = "10") Integer page_size,
                                         @PathVariable Long id){
        PageHelper.startPage( page_num,page_size );
        return onDataResp( new MyPageInfo<Reply>( replyService.selectById( id )));
    }

    @GetMapping("add")
    @ResponseBody
    public Map<String,Object> add(@RequestParam Long uId,
                                  @RequestParam String content){
        Reply reply = new Reply();
        if (uId == null) return onBadResp( "回复用户uId不为空" );
        if (content == null || content.trim().length() == 0) return onBadResp( "回复内容不能为空" );
        reply.setuId( uId );
        reply.setContent( content );
        if (replyService.add( reply ) > 0) return onSuccessRep( "回复成功" );
        return onBadResp( "回复失败" );
    }
}
