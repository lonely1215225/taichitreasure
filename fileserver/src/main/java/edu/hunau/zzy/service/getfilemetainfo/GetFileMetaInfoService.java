package edu.hunau.zzy.service.getfilemetainfo;

import edu.hunau.zzy.entity.FileMetaInfo;

public interface GetFileMetaInfoService {
    FileMetaInfo queryFileMetaInfoById(String fileUUID);
}
