package edu.hunau.zzy.dao.uploaddao.daoimpl;

import edu.hunau.zzy.dao.uploaddao.UploadDao;
import edu.hunau.zzy.utils.DerbyUtils;

import java.sql.Connection;

public class UploadDaoImpl implements UploadDao {
    @Override
    public void saveMetaInfo(String fileUUID, Long fileSize, String fileType, String srcName, String date, String filePos, String secretKey) {
        Connection connection = DerbyUtils.getConnection();
        DerbyUtils.createFileMetaInfoTable(connection);
        DerbyUtils.insertUploadInfo(connection,fileUUID,fileSize,fileType,srcName,date,filePos,secretKey);
        DerbyUtils.queryInfo(connection);
    }
}
