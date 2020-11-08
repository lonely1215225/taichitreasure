package edu.hunau.zzy.service.uploadservice.serviceimpl;

import edu.hunau.zzy.dao.uploaddao.UploadDao;
import edu.hunau.zzy.dao.uploaddao.daoimpl.UploadDaoImpl;
import edu.hunau.zzy.service.uploadservice.UploadService;
import edu.hunau.zzy.utils.CipherUtils;
import org.apache.commons.fileupload.FileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


public class UploadServiceImpl implements UploadService {
    private static final Logger logger = LoggerFactory.getLogger(UploadServiceImpl.class);

    UploadDao dao=new UploadDaoImpl();
    @Override
    public void saveMetaInfo(String fileUUID, Long fileSize, String fileType, String srcName, String date, String filePos, String secretKey) {

        dao.saveMetaInfo(fileUUID, fileSize, fileType, srcName, date, filePos, secretKey);

    }
    /**
     *
     * @param savePath 上传路径
     * @param item 封装的是上传文件
     * @return message
     *
     */
    public String doUpload(String savePath, FileItem item,String publicKey) throws IOException {
        //得到上传的文件名称，
        String fileName = item.getName();
        logger.info("上传文件名:"+fileName);
        long size =item.getSize();
        if(fileName==null || fileName.trim().equals("")){
            return null;
        }
        //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
        //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
        fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
        //得到上传文件的扩展名
        String fileExtName = fileName.substring(fileName.lastIndexOf(".")+1);
        //如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
        logger.info("上传的文件的扩展名是："+fileExtName);
        //获取item中的上传文件的输入流
        InputStream in = item.getInputStream();

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

        String dateTime= format.format(new Date());
        //得到文件保存的名称
        String fileUUID = UUID.randomUUID().toString();
        String saveFilename = makeFileName(fileUUID,fileName,dateTime);
        logger.info("文件的UUID："+fileUUID);
        //得到文件的保存目录
        String realSavePath = makePath(savePath,dateTime);
        logger.info("文件保存位置："+realSavePath);
        //创建一个文件输出流
        FileOutputStream out = new FileOutputStream(realSavePath + "\\" + saveFilename);

        try {
            // aes加密与RSA加密上传
            String cipherTextSecretKey = CipherUtils.encrypt(in, out,publicKey);

            // 保存上传信息到数据库
            saveMetaInfo(fileUUID,size,fileExtName,saveFilename,dateTime,realSavePath,cipherTextSecretKey);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //删除处理文件上传时生成的临时文件
        item.delete();

        return fileUUID;
    }
    /**
     * @Method: makeFileName
     * @Description: 生成上传文件的文件名，文件名以：uuid+"_"+文件的原始名称
     * @Anthor: zzy
     * @param filename 文件的原始名称
     * @return uuid+"_"+文件的原始名称
     */
    private String makeFileName(String uuid,String filename,String dir1){
        //为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
        return uuid+"~"+dir1+ "_" + filename;
    }

    /**
     * 使用yyyyMMdd为目录名（不严谨，可能导致一个目录下存在大量文件）
     * @Method: makePath
     * @Description:
     * @Anthor: zzy
     * @param savePath 文件存储路径
     * @return 新的存储目录
     */
    private String makePath(String savePath,String dir1){

        //构造新的保存目录
        String dir = savePath + "\\" + dir1;
        //File既可以代表文件也可以代表目录
        File file = new File(dir);
        //如果目录不存在
        if(!file.exists()){
            //创建目录
            file.mkdirs();
        }
        return dir;
    }
}
