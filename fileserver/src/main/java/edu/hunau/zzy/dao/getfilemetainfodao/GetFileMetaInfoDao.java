package edu.hunau.zzy.dao.getfilemetainfodao;

import edu.hunau.zzy.entity.FileMetaInfo;

public interface GetFileMetaInfoDao {
    FileMetaInfo queryFileMetaInfoById(String fileUUID);
}
