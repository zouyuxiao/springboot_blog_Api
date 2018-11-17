package com.yuxiao.blog.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by ${邹} on 2018/10/12.
 * 描述：上传工具类
 */
public class UploadUtil {

    public static void uploadFile(MultipartFile file) throws IOException {
        // 获取用户提交的文件名
        String oringinalFilename = file.getOriginalFilename();
        // 获取Input控件中name属性
        String name = file.getName();
        // 文件保存路径
        // 动态生成文件名--》UUID（生成唯一标识符，网卡号+时间)
        String randomUUID = UUID.randomUUID().toString();
        // 通过oringinalFilename 获取后缀名
        int index = oringinalFilename.lastIndexOf( "." );
        String exet = oringinalFilename.substring( index );
        // 根据系统时间生成对应的文件夹，格式为yyyyMMdd
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMdd\\HH\\mm\\ss" );
        String datestr = sdf.format( date );
        String fliePath = "D:\\File\\" + datestr;
        File file1 = new File( fliePath );
        if (!file1.exists()){
            file1.mkdirs();
        }
        fliePath += "\\" + randomUUID + exet;
        file.transferTo( new File( fliePath ) );
    }
}
