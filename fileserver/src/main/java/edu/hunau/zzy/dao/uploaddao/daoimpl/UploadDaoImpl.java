package edu.hunau.zzy.dao.uploaddao.daoimpl;

import edu.hunau.zzy.dao.uploaddao.UploadDao;
import edu.hunau.zzy.utils.DerbyUtils;

import java.sql.Connection;

public class UploadDaoImpl implements UploadDao {
    @Override
    public void saveMetaInfo(String fileUUID, Long fileSize, String fileType, String srcName, String date, String filePos, String secretKey) {
        // 拿到数据库连接
        Connection connection = DerbyUtils.getConnection();
        // 创建表，如果存在则忽略
        DerbyUtils.createFileMetaInfoTable(connection);
        // 插入数据
        DerbyUtils.insertUploadInfo(connection,fileUUID,fileSize,fileType,srcName,date,filePos,secretKey);
        // 测试查询所有上传信息
        DerbyUtils.queryInfo(connection);
    }
}
