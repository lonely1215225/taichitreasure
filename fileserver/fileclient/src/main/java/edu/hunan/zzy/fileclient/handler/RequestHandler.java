package edu.hunan.zzy.fileclient.handler;

import edu.hunan.zzy.fileclient.entity.FileMetaInfo;
import edu.hunan.zzy.fileclient.utils.CipherUtils;
import edu.hunan.zzy.fileclient.utils.ParseFileMetaInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.security.PrivateKey;
import java.util.List;

/**
 * @Description: 客户端的所有请求方法，包括上传动作，接收服务端传过来的文件信息或者UUID，下载动作
 * @Author: zzy
 * @Date: 2020-11-07
 */
@Controller
public class RequestHandler {
private CipherUtils cipherUtils=new CipherUtils();

    /**
     * @Description: 去上传前，首先生成公钥私钥，私钥客户端保留，公钥让服务端持有并对服务端生成的AES密钥进行加密；
     * 再生成一对公钥私钥，用于签名，私钥对SID进行加密，公钥服务端持有并对SID进行校验
     * @param model
     * @return 到上传页面中
     */
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

    /**
     * @Description: 拿到服务端上传成功返回文件的UUID和message
     * @param fileUUIDs 多文件UUID
     * @param message 上传信息
     * @param model
     * @return 展示内容
     */
    @RequestMapping("/upload/fileMetaInfo/from/fileServer")
    public String upload(String fileUUIDs,String message, Model model){
        System.out.println(fileUUIDs);
        model.addAttribute("fileUUIDs",fileUUIDs);
        model.addAttribute("message",message);
        String[] fileUUID = fileUUIDs.split(",");
        model.addAttribute("fileUUID",fileUUID);
        return "returnUploadInfo";
    }

    /**
     * @Description: 拿到服务器刚刚一次上传的所有文件的元信息
     * @param fileMetaInfos json格式的文件元信息
     * @param model
     * @return 返回到页面展示文件详细信息
     */
    @RequestMapping("/getFileMetaInfo")
    public String listFiles(String fileMetaInfos,Model model){
        List<FileMetaInfo> fileMetaInfoList= ParseFileMetaInfo.parse(fileMetaInfos);
        model.addAttribute("fileMetaInfos",fileMetaInfoList);
        return "listPage";
    }

    /**
     * @Description: 拿到文件元信息后，就可以通过密文，私钥和文件路径进行下载；
     * 同一次上传的文件，它们的加密密钥是一致的
     * @param fileName 文件名
     * @param filePos  文件上传路径
     * @param secretKey 加密的密钥
     * @param response
     * @return 返回下载信息
     */
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
