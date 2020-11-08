package edu.hunau.zzy.handler;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.hunau.zzy.service.uploadservice.UploadService;
import edu.hunau.zzy.service.uploadservice.serviceimpl.UploadServiceImpl;
import edu.hunau.zzy.utils.CipherUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * @ClassName: UploadHandleServlet
 * @Description: 上传文件逻辑处理类
 * @author: zzy
 * @date: 2020-11-07
 */
public class UploadHandlerServlet extends HttpServlet {

    UploadService uploadService = new UploadServiceImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // 准备上传的工作
        readyUpload(request, response);
    }

    /**
     * @param signatureInfo 校验参数，SID ， Signature  和 公钥
     * @return 返回boolean，是否合法校验
     */
    private boolean verify(String signatureInfo) {
        String[] split = signatureInfo.split(",");
        for (String s : split) {
            System.out.println(s);
        }
        return CipherUtils.verify(split[0], split[1], split[2]);
    }

    private void readyUpload(HttpServletRequest request, HttpServletResponse response) throws IOException {


        //得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
        String savePath = this.getServletContext().getRealPath("/upload");
        //上传时生成的临时文件保存目录(如果文件超出单文件设置大小，那么就将其缓存)
        String tempPath = this.getServletContext().getRealPath("/temp");
        File tmpFile = new File(tempPath);
        if (!tmpFile.exists()) {
            //创建临时目录
            tmpFile.mkdir();
        }

        //消息提示
        String fileUUID;
        String message = "";
        ServletFileUpload upload = null;
        StringBuilder builder = new StringBuilder();
        try {
            //使用Apache文件上传组件处理文件上传步骤：
            //1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
            factory.setSizeThreshold(1024 * 100);//设置缓冲区的大小为100KB，如果不指定，那么缓冲区的大小默认是10KB
            //设置上传时生成的临时文件的保存目录
            factory.setRepository(tmpFile);
            //2、创建一个文件上传解析器
            upload = new ServletFileUpload(factory);
            //监听文件上传进度
            upload.setProgressListener((pBytesRead, pContentLength, arg2) ->
                    System.out.println("文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead));
            //解决上传文件名的中文乱码
            upload.setHeaderEncoding("UTF-8");
            //3、判断提交上来的数据是否是上传表单的数据
            if (!ServletFileUpload.isMultipartContent(request)) {
                //按照传统方式获取数据
                return;
            }
            //设置上传单个文件的大小的最大值，目前是设置为1024*1024*10字节，也就是10MB
            upload.setFileSizeMax(1024 * 1024 * 10);
            //设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为100MB
            upload.setSizeMax(1024 * 1024 * 100);
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = upload.parseRequest(request);

            String publicKey = null;
            for (FileItem item : list) {
                // 如果fileitem中封装的是普通输入项的数据
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    //解决普通输入项的数据的中文乱码问题
                    String value = item.getString("UTF-8");
                    if (name.equals("signInfo")) {
                        System.out.println(name + "====" + value);
                        if (!verify(value)) {
                            response.setStatus(403);
                            response.getWriter().println("illegal request");
                            System.out.println("=====不合法");
                            return;
                        }
                    }
                    if (name.equals("publicKey")) publicKey = value;
                } else {// 如果fileitem中封装的是上传文件

                    // 去service层上传文件
                    fileUUID = uploadService.doUpload(savePath, item, publicKey);
                    if (fileUUID == null) continue;
                    builder.append(fileUUID).append(",");
                    System.out.println(builder.toString());
                }

            }
        } catch (FileUploadBase.FileSizeLimitExceededException e) {
            e.printStackTrace();
            message = "单个文件不允许超过" + (upload.getFileSizeMax() >> 20) + "MB";
            System.out.println(message);
        } catch (FileUploadBase.SizeLimitExceededException e) {
            e.printStackTrace();
            message = "上传文件的总的大小超出限制的最大值！！！";
            System.out.println(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("http://localhost:8081/fileclient/upload/fileMetaInfo/from/fileServer?fileUUIDs=" + builder.toString() + "&message=" + message);

    }


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        doGet(request, response);
    }
}