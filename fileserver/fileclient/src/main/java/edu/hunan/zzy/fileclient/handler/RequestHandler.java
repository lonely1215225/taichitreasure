package edu.hunan.zzy.fileclient.handler;

import edu.hunan.zzy.fileclient.entity.FileMetaInfo;
import edu.hunan.zzy.fileclient.utils.CipherUtils;
import edu.hunan.zzy.fileclient.utils.ParseFileMetaInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.security.PrivateKey;
import java.util.List;

@Controller
public class RequestHandler {
private CipherUtils cipherUtils=new CipherUtils();
    @RequestMapping("/toUploadPage")
    public String toUploadPage(Model model){
        String publicKey = cipherUtils.getPublicKey();
        model.addAttribute("publicKey",publicKey);

        CipherUtils signCipher = new CipherUtils();
        PrivateKey privateKey = signCipher.getaPrivate();
        String signedCipher = signCipher.signId(privateKey);
        String pk = signCipher.getPublicKey();
        // sp = SID + signature + publicKey
        String sp=signedCipher+","+pk;
        System.out.println(sp);
        model.addAttribute("sp",sp);
        return "uploadPage";
    }

    @RequestMapping("/upload/fileMetaInfo/from/fileServer")
    public String upload(String fileUUIDs,String message, Model model){
        System.out.println(fileUUIDs);
        model.addAttribute("fileUUIDs",fileUUIDs);
        model.addAttribute("message",message);
        String[] fileUUID = fileUUIDs.split(",");
        model.addAttribute("fileUUID",fileUUID);
        return "returnUploadInfo";
    }

    @RequestMapping("/getFileMetaInfo")
    public String listFiles(String fileMetaInfos,Model model){
        List<FileMetaInfo> fileMetaInfoList= ParseFileMetaInfo.parse(fileMetaInfos);
        model.addAttribute("fileMetaInfos",fileMetaInfoList);
        return "listPage";
    }
    @ResponseBody
    @RequestMapping("/download")
    public String download(String fileName, String filePos, String secretKey, HttpServletResponse response){
        System.out.println(filePos+"/"+fileName);
        //得到要下载的文件
        File file = new File(filePos + "/" + fileName);
        //如果文件不存在
        if(!file.exists()) return "您要下载的资源已被删除！！";

        //读取要下载的文件，保存到文件输入流
        FileInputStream in = null;
        OutputStream out=null;
        try {
            // 处理文件名
            // 设置响应头，控制浏览器下载该文件
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            in = new FileInputStream(filePos + "/" + fileName);
            //创建输出流
            out = response.getOutputStream();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 解密下载
        try {
            cipherUtils.decryptAndDownload(in,out,secretKey);
        } catch (Exception e) {
            e.printStackTrace();
            return "下载失败！";
        }
        return "下载成功";
    }
}
