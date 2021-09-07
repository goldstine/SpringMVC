package com.goldstine.servlet.fileupload;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 处理文件上传post请求
 */
public class FileUpload extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    //处理文件上传
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("文件上传过来了.....");

        //表单设置为encType="multipart/form-data"：表示多段数据提交，以二进制流的形式发送给服务器端
        //这个不可能像普通的表单一样，通过参数进行接收，就是应为表单设置了encType="multipart/form-data"，所以是以流的形式进行接收
        //所以不是通过参数进行接收，并且对应的浏览器地址栏没有参数
//        InputStream is = req.getInputStream();
//        //将输入字节流继续包装成缓冲字节流
//        BufferedInputStream bis = new BufferedInputStream(is);
//        //首先创建一个桶
//        byte[] buffer=new byte[1024];
//        //创建读的数量
//        int len;
//        while((len=bis.read(buffer))!=-1){
//            System.out.println(new String(buffer,0,len));
//        }

        /**
         * 通过第三方jar进行解析上传的表单数据
         * commons-io  commons-fileupload
         */
        //首先判断上传数据是否是多段数据,只有多段的数据，才是文件上传的
        if(ServletFileUpload.isMultipartContent(req)){
            //如果是多段数据，将多段数据解析成一个一个的FileItem，文件项
            //必须使用对应的创建FileItem对象的工程对象fileItemFactory
//            FileItemFactory fileItemFactory = new FileItemFactory() {
//                @Override
//                public FileItem createItem(String s, String s1, boolean b, String s2) {
//                    return null;
//                }
//            };

            //直接使用FileItemFactory接口的实现类 DiskFileItemFactory
            DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();

            ServletFileUpload sf = new ServletFileUpload(fileItemFactory);

            List<FileItem> fileItems=null;

            try {
                fileItems = sf.parseRequest(req);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            //将解析的所有fileItem对象进行遍历判断
            for (FileItem fileItem : fileItems) {
                //判断是否是普通的表单项
                if(fileItem.isFormField()){
                    //如果是普通的表单项
                    System.out.println(fileItem.getFieldName()+"====="+fileItem.getString("UTF-8"));//参数UFT-8解决乱码问题
                }else{
                    //文件的表单项上传
                    System.out.println("上传文件的表单项名称为:"+fileItem.getFieldName());

                    String name = fileItem.getName();//首先获得对应的文件名
                    System.out.println("上传的文件名为："+name);

                    //将该文件保存到对应的磁盘
                    try {
                        fileItem.write(new File("D:\\goldstine\\"+name));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }

        }

        //
    }
}
