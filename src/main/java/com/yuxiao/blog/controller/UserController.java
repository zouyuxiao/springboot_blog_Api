package com.yuxiao.blog.controller;

import com.github.pagehelper.PageHelper;
import com.yuxiao.blog.bean.MyPageInfo;
import com.yuxiao.blog.bean.User;
import com.yuxiao.blog.service.UserService;
import com.yuxiao.blog.util.MD5Util;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Created by ${邹} on 2018/11/2.
 * 描述：
 */
@RestController
//@Api(tags = "用户",value = "")
@RequestMapping("api/user/")
public class UserController extends BaseApiController {
    @Autowired
    private UserService userService;

    // 注册
    @PostMapping("reg")
    @ApiOperation( value = "用户注册")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "username",value = "用户名"),
            @ApiImplicitParam(name = "password",value = "密码"),
            @ApiImplicitParam(name = "sex",value = "性别"),
            @ApiImplicitParam(name = "email",value = "邮箱"),
            @ApiImplicitParam(name = "phone",value = "手机号"),
            @ApiImplicitParam(name = "file",value = "上传头像"),
            @ApiImplicitParam(name = "code",value = "验证码"),
    } )
    public Map<String, Object> reg(@RequestParam String username, @RequestParam String password, @RequestParam String sex,
                                   @RequestParam String email, @RequestParam String phone, MultipartFile file,
                                   @RequestParam String code, HttpSession session, Model model)throws IOException {
        System.out.println( file );
        if (username == null || username.trim().length() == 0) return onBadResp( "用户名不能为空" );
        if (password == null || password.trim().length() == 0)return onBadResp( "密码不能为空" );
        if (sex == null || sex.trim().length() == 0) return onBadResp( "性别不能为空" );
        if (email == null || email.trim().length() == 0) return onBadResp( "邮箱不能为空" );
        if (phone == null || phone.trim().length() == 0) return onBadResp( "手机号不能为空" );
        if (code == null || code.trim().length() == 0) return onBadResp( "验证码不能为空" );
        User eistUsername = userService.selectByUsername(username);
        User eistEmail = userService.selectByEmail( email );
        User eistPhone = userService.selectByPhone( phone );
        String imageCode = String.valueOf( session.getAttribute( "imageCode" ) );
        String status = "1";
        if (eistUsername != null)  return onBadResp( "验证码不能为空" );
        if (eistEmail != null) return onBadResp( "该邮箱已被注册" );
        if (eistPhone != null)  return onBadResp( "该手机号已注册" );
        if (!email.matches("^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$"))  return onBadResp( "邮箱格式错误" );
        if (!phone.matches( "1[356789]\\d{9}" ))  return onBadResp( "手机号格式错误" );
        if (!imageCode.equalsIgnoreCase( code )) return onBadResp( "验证码错误" );
        // 获取用户提交的文件名12345
        String oringinalFilename = file.getOriginalFilename();
        // 获取Input控件中name属性
        String name = file.getName();
        // 文件保存路径
        // 动态生成文件名--》UUID（生成唯一标识符，网卡号+时间)
        String randomUUID = UUID.randomUUID().toString();
        // 通过oringinalFilename 获取后缀名
        int index = oringinalFilename.lastIndexOf( "." );
        String exet = oringinalFilename.substring( index );
        System.out.println( exet );
        // 根据系统时间生成对应的文件夹，格式为yyyyMMdd
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMdd\\HH\\mm\\ss" );
        String datestr = sdf.format( date );
        String fliePath = "D:\\File\\" + datestr;
        File file1 = new File( fliePath );
        if (!file1.exists()) {
            file1.mkdirs();
        }
        fliePath += "\\" + randomUUID + exet;
        System.out.println( fliePath );
        file.transferTo( new File( fliePath ) );
        String img = "\\images\\user\\01.jpg " /*+ randomUUID + exet */;
        User user = new User();
        user.setUsername( username );
        user.setPassword( MD5Util.encode( password ) );
        user.setSex( sex );
        user.setEmail( email );
        user.setPhone( phone );
        user.setStatus( status );
        user.setImg( img );
        userService.reg( user );
        session.setAttribute( "user",user );
        return onSuccessRep( "注册成功" );
    }

    // shiro 认证登录
    @GetMapping("login")
    @ApiOperation( value = "用户登录")
    @ResponseBody
    public Map<String, Object> login(@RequestParam String username, @RequestParam String password, HttpSession session, Model model){
        // 使用shiro 编写认证操作
        Subject subject = SecurityUtils.getSubject();
        // 封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, MD5Util.encode( password ));
        User user = userService.findByUsername( token.getUsername() );
        // 执行登录方法
        try {
            subject.login( token );
        }catch (UnknownAccountException e){
            return onBadResp( "用户名错误" );
        }catch (IncorrectCredentialsException e){
            return onBadResp( "密码错误" );
        }
        session.setAttribute( "user",user );
        return onSuccessRep( "登录成功" );
    }

    // 注销登录接口
    @ApiOperation( "注销登录" )
    @GetMapping("/logout")
    public Map<String, Object> logout(HttpSession session, HttpServletRequest request) {
        session = request.getSession();
         session.invalidate(); // 关闭session
        return onSuccessRep( "注销成功" );
    }

    // 查询所有用户
    @GetMapping("list")
    @ApiOperation(value="获取用户详细信息")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "page_num",value = "页码默认是第一页"),
            @ApiImplicitParam(name = "page_size",value = "数据条数默认是第10条")
    } )
    @ResponseBody
    public Map<String,Object> list(@RequestParam(defaultValue = "1") Integer page_num, @RequestParam(defaultValue = "10") Integer page_size){
        PageHelper.startPage( page_num,page_size );
        return onDataResp( new MyPageInfo<User>(userService.list()));
    }

    // 修改
    @ApiOperation( "修改用户信息")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "username",value = "用户名"),
            @ApiImplicitParam(name = "password",value = "密码"),
            @ApiImplicitParam(name = "sex",value = "性别"),
            @ApiImplicitParam(name = "email",value = "邮箱"),
            @ApiImplicitParam(name = "phone",value = "手机号"),
            @ApiImplicitParam(name = "file",value = "上传头像"),
    } )
    @PostMapping("update")
    public Map<String,Object> update(@RequestParam Long id,@RequestParam String username, @RequestParam String password, @RequestParam String sex,
                                     @RequestParam String email, @RequestParam String phone, MultipartFile file) throws IOException {
        if (username == null || username.trim().length() == 0) return onBadResp( "用户名不能为空" );
        if (password == null || password.trim().length() == 0) return onBadResp( "密码不能为空" );
        if (sex == null || sex.trim().length() == 0) return onBadResp( "性别不能为空" );
        if (email == null || email.trim().length() == 0) return onBadResp( "邮箱不能为空" );
        if (phone == null || phone.trim().length() == 0) return onBadResp( "手机号不能为空" );
        User eistUsername = userService.selectByUsername(username);
        String status = "1";
        if (eistUsername != null) return onBadResp( "用户名已存在" );
        if (!email.matches("^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$")) return onBadResp( "邮箱格式错误" );
        if (!phone.matches( "1[356789]\\d{9}" )) return onBadResp( "手机号格式错误" );
        // 获取用户提交的文件名12345
        String oringinalFilename = file.getOriginalFilename();
        // 获取Input控件中name属性
        String name = file.getName();
        // 文件保存路径
        // 动态生成文件名--》UUID（生成唯一标识符，网卡号+时间)
        String randomUUID = UUID.randomUUID().toString();
        System.out.println( randomUUID );
        // 通过oringinalFilename 获取后缀名
        int index = oringinalFilename.lastIndexOf( "." );
        System.out.println( index );
        String exet = oringinalFilename.substring( index );
        System.out.println( exet );
        // 根据系统时间生成对应的文件夹，格式为yyyyMMdd
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMdd\\HH\\mm\\ss" );
        String datestr = sdf.format( date );
        String fliePath = "D:\\File\\" + datestr;
        System.out.println( datestr );
        File file1 = new File( fliePath );
        if (!file1.exists()) {
            file1.mkdirs();
        }
        fliePath += "\\" + randomUUID + exet;
        System.out.println( fliePath );
        file.transferTo( new File( fliePath ) );
        String img = "images\\user\\ " + randomUUID + exet ;
        User user = new User();
        user.setuId( id );
        user.setUsername( username );
        user.setPassword( MD5Util.encode( password ) );
        user.setSex( sex );
        user.setEmail( email );
        user.setPhone( phone );
        user.setStatus( status );
        user.setImg( img );
        userService.update( user );
        return onSuccessRep( "修改成功" );
    }


    // 根据id查询用户信息
    @GetMapping("selectById/{id}")
    @ApiOperation( value = "根据用户id查询用户信息" )
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
    public Map<String,Object> selectById(@PathVariable Long id, @RequestParam(defaultValue = "1") Integer page_num,
                                         @RequestParam(defaultValue = "10") Integer page_size){
       PageHelper.startPage( page_num,page_size );
       return onDataResp( new MyPageInfo<User>( userService.selectById( id ) ) );
    }

    // 根据用户名模糊查询用户信息
    @GetMapping("selectByUsername")
    @ApiOperation( "根据用户名模糊查询用户信息" )
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "username",value = "用户名", required = true),
            @ApiImplicitParam(name = "page_num",value = "页码默认是第一页"),
            @ApiImplicitParam(name = "page_size",value = "数据条数默认是第10条")
    } )
    public Map<String,Object> selectByUsername(@RequestParam String username,@RequestParam(defaultValue = "1") Integer page_num,
                                         @RequestParam(defaultValue = "10") Integer page_size){
        PageHelper.startPage( page_num,page_size );
        return onDataResp( new MyPageInfo<User>( userService.selectUsername( username ) ) );
    }

    // 删除用户
    @GetMapping("delete/{id}")
    @ApiOperation(value="删除用户", notes="根据url的id来指定删除用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
    public Map<String,Object> delete(@RequestParam Long id){
        userService.deleteById( id );
        return onSuccessRep( "删除成功" );
    }
}
