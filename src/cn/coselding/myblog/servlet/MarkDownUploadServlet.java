package cn.coselding.myblog.servlet;

import cn.coselding.myblog.utils.WebUtils;
import com.google.gson.JsonObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.List;

/**
 * Created by 宇强 on 2016/6/17 0017.
 */
@WebServlet(name = "MarkDownUploadServlet")
public class MarkDownUploadServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 初始化upload对象
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");// 上传文件名中文乱码问题

        if (!upload.isMultipartContent(request)) {
            // /普通类型的表单
            return;
        }
        // /获得上传数据
        List<FileItem> list = null;
        try {
            list = upload.parseRequest(request);
        } catch (FileUploadException e1) {
            throw new RuntimeException(e1);
        }
        // 设置单个文件大小
        upload.setFileSizeMax(1024*4);
        // 设置总文件大小
        upload.setSizeMax(1024 * 10);

        String contextPath = "";
        // 进行文件保存操作
        for (FileItem item : list) {
            if (item.isFormField()) {
                //表单中的普通类型数据
                String name = item.getFieldName();
                // 普通表单中文乱码问题手工转换
                String value = item.getString("UTF-8");
                System.out.println(name + " = " + value);
            } else {
                // 获得文件名
                String filename = item.getName();

                System.out.println("filename="+filename);
                // 判断上传文件为空
                if (filename == null || filename.trim().equals(""))
                    continue;

                //截取文件名
                if (filename.contains("\\"))
                    filename = filename.substring(filename.lastIndexOf("\\") + 1);
                else if (filename.contains("/"))
                    filename = filename.substring(filename.lastIndexOf("/") + 1);
                System.out.println("upload : filename = " + filename);
                // /原文件名生成对应的随即目录

                //合成服务器路径
                String path = this.getServletContext().getRealPath("/upload/images/");
                filename = WebUtils.encodeFilename(filename);
                //hash打散文件
                String savePath = WebUtils.encodePath(filename, path);
                System.out.println("最终路径=" + savePath);
                contextPath = savePath.substring(savePath.indexOf("/upload/images/"));
                // 获取文件流
                InputStream inputStream = item.getInputStream();
                File file = new File(savePath);
                file.createNewFile();
                FileOutputStream outputStream = new FileOutputStream(file);
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, len);
                }
                inputStream.close();
                outputStream.close();
                item.delete();
            }
        }

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("success",1);
        jsonObject.addProperty("message","文件上传成功");
        jsonObject.addProperty("url","http://localhost:8080/MyBlog"+contextPath);
        String result = jsonObject.toString();
        System.out.println(result);
        PrintWriter out = ServletActionContext.getResponse().getWriter();
        out.write(result);
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
