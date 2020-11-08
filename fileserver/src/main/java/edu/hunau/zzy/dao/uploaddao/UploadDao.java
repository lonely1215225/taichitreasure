package edu.hunau.zzy.dao.uploaddao;

public interface UploadDao {
    void saveMetaInfo(String fileUUID, Long fileSize, String fileType, String srcName, String date, String filePos, String secretKey);

}
