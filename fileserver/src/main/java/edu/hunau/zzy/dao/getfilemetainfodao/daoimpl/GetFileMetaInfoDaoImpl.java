package edu.hunau.zzy.dao.getfilemetainfodao.daoimpl;

import edu.hunau.zzy.dao.getfilemetainfodao.GetFileMetaInfoDao;
import edu.hunau.zzy.entity.FileMetaInfo;
import edu.hunau.zzy.utils.DerbyUtils;

import java.sql.Connection;


public class GetFileMetaInfoDaoImpl implements GetFileMetaInfoDao {
    @Override
    public FileMetaInfo queryFileMetaInfoById(String fileUUID) {
        Connection conn = DerbyUtils.getConnection();

        return DerbyUtils.queryInfoById(conn, fileUUID);

    }
}
