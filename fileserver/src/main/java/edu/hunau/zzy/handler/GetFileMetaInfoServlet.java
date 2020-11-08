package edu.hunau.zzy.handler;

import com.alibaba.fastjson.JSONArray;
import edu.hunau.zzy.entity.FileMetaInfo;
import edu.hunau.zzy.service.getfilemetainfo.GetFileMetaInfoService;
import edu.hunau.zzy.service.getfilemetainfo.serviceimpl.GetFileMetaInfoServiceImpl;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: ListFileServlet
 * @Description: 列出一次上传系统中的所有文件
 * @author: zzy
 * @date: 2020-11-07
 */
public class GetFileMetaInfoServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String fileUUIDs = request.getParameter("fileUUIDs");
        String[] fileUUID = fileUUIDs.split(",");
        // 调用服务层获取数据库中的文件信息
        GetFileMetaInfoService filesService = new GetFileMetaInfoServiceImpl();
        StringBuilder builder = new StringBuilder();

        // 将多个UUID对应的数据库中的文件信息查出来，封装到FileMetaInfo中，后使用Json字符串传递到客户端
        for (String s : fileUUID) {
            FileMetaInfo fileMetaInfo = filesService.queryFileMetaInfoById(s);
            Object o = JSONArray.toJSON(fileMetaInfo);
            String fileMetaInfoJson = o.toString();
            fileMetaInfoJson = fileMetaInfoJson.replace("\"", "");
            fileMetaInfoJson = fileMetaInfoJson.replace("{", "");
            fileMetaInfoJson = fileMetaInfoJson.replace("}", "");
            fileMetaInfoJson = fileMetaInfoJson.replace(",", ";");
            fileMetaInfoJson = fileMetaInfoJson.replace("\\\\", "/");

            System.out.println(fileMetaInfoJson);
            String encode = URLEncoder.encode(fileMetaInfoJson, StandardCharsets.UTF_8);
            builder.append(encode).append(",");
        }
        response.sendRedirect("http://localhost:8081/fileclient/getFileMetaInfo?fileMetaInfos=" + builder.toString());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        doGet(request, response);
    }
}