package com.goldstine.servlet.fileupload;

import org.apache.commons.io.IOUtils;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Base64;

/**
 * http协议默认情况不支持中文传输，所以对应的文件名如果为中文，则不能显示
 *如果需要使用URL编码
 * URLEncode.encode("中国.jpg","UTF-8");
 * URL编码表示将汉字转换成为%xx%xx的格式
 *
 * IE浏览器和Google浏览器，通过URLEncode的方式解决中文问题
 * firefox附件中文名通过Base64编码的方式：
 *
 *Content-Disposition: attachment;filename==?charset?B?xxxxx?=
 * =?表示编码内容开始
 * charset  表示字符集
 * B   表示Base64
 * xxxxx  表示文件名Base64编码之后的内容
 * ?=表示编码内容的结束
 *
 * 这里使用Base64只能首先创建对应的Base64Encode对象或者解码器对象Base64Decode对象  通过new
 */
public class FileDownload extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //文件的下载，客户端通过get请求进行获取

        //1、获取客户端需要下载的文件名
        String downloadFileName="0.png";

        //2、读取要下载的文件内容,通过输入流将磁盘上的文件读取,这里通过servletContext对象进行读取
        ServletContext servletContext = getServletContext();

        //4、再回传前，将响应头告述客户端返回的数据类型
        //获取对应的文件的数据类型
        String mimeType = servletContext.getMimeType("/imgs/" + downloadFileName);
        System.out.println("下载文件的类型:"+mimeType);
        resp.setContentType(mimeType);
        //5、还要告述客户端收到的数据是用于下载使用（还是使用响应头）
        //设置响应头，该书客户端该数据用于下载

        /**
         * Content-Disposition:响应头，表示收到的数据怎么处理
         * attachment：表示附件，表是用于下载使用
         * filename:表示指定下载的文件名
         */
//字符串的contains(String str)判断字符串中是否含有子串str
        //浏览器之间的兼容性
        if (req.getHeader("User-Agent").contains("Firefox")) {//如果是firefox浏览器，使用base64编码
//            resp.setHeader("Content-Disposition","attachment;fileName==?UTF-8?B?"+ new BASE64Encoder().encode("中国.png".getBytes("UTF-8")) +"?=");
            resp.setHeader("Content-Disposition","attachment;fileName==?UTF-8?B?"+ Base64.getEncoder().encode("中国.png".getBytes("UTF-8")) +"?=");
        }else{
            //如果是IE浏览器，需要使用URLEncode
            resp.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode("中国.png","UTF-8"));
        }

//        resp.setHeader("Content-Disposition","attachment;fileName");
//        resp.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode("中国.png","UTF-8"));
//        resp.setHeader("Content-Disposition","attachment;fileName==?UTF-8?B?"+ new BASE64Encoder().encode("中国.png".getBytes("UTF-8")) +"?=");

        InputStream resourceAsStream = servletContext.getResourceAsStream("/imgs/" + downloadFileName);

        //获取对应的输出流
        OutputStream outputStream = resp.getOutputStream();

        //通过commons-io工具包中的IOUtils类进行输入流复制到输出流
        IOUtils.copy(resourceAsStream,outputStream);


        //3、把下载的文件内容传回给客户端


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }
}
