package edu.hunau.zzy.service.uploadservice;

import org.apache.commons.fileupload.FileItem;
import java.io.IOException;

/**
 * @Description: 响应标准 http 协议的 post 请求发送来的文件，接收到文件后加密存储，
 * 将文件重命名（使用 UUID）并按照日期保存至服务器文件系统的不同的目录 中（目录格式 yyyyMMdd），
 * 同时将文件大小、文件类型，原始文件名、 创建时间、文件保存目录地址、文件加密数字信封等元数据记录至数据库中，
 * 同时将 UUID 返回给客户端。文件加密方法：先使用对称加密算法（AES）对文件进行加密，
 * 对称秘钥随机生成，每次均不相同，然后使用非对称加密算法（RSA）对前面的随机对称秘钥加密
 * （Server 端持有公钥），加密后的结果（数字信封）保存至数据库中。
 */
public interface UploadService {

    void saveMetaInfo(String fileUUID, Long fileSize, String fileType, String srcName, String date, String filePos, String secretKey);

    String doUpload(String savePath, FileItem item,String publicKey) throws IOException;
}
