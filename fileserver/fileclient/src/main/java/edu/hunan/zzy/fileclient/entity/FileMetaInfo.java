package edu.hunan.zzy.fileclient.entity;

public class FileMetaInfo {
    String fileUUID;
    Long fileSize;
    String fileType;
    String srcName;
    String date;
    String filePos;
    String secretKey;

    public FileMetaInfo() {
    }

    public FileMetaInfo(String fileUUID, Long fileSize, String fileType, String srcName, String date, String filePos, String secretKey) {
        this.fileUUID = fileUUID;
        this.fileSize = fileSize;
        this.fileType = fileType;
        this.srcName = srcName;
        this.date = date;
        this.filePos = filePos;
        this.secretKey = secretKey;
    }

    public String getFileUUID() {
        return fileUUID;
    }

    public void setFileUUID(String fileUUID) {
        this.fileUUID = fileUUID;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getSrcName() {
        return srcName;
    }

    public void setSrcName(String srcName) {
        this.srcName = srcName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFilePos() {
        return filePos;
    }

    public void setFilePos(String filePos) {
        this.filePos = filePos;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public String toString() {
        return "FileMetaInfo{" +
                "fileUUID='" + fileUUID + '\'' +
                ", fileSize=" + fileSize +
                ", fileType='" + fileType + '\'' +
                ", srcName='" + srcName + '\'' +
                ", date='" + date + '\'' +
                ", filePos='" + filePos + '\'' +
                ", secretKey='" + secretKey + '\'' +
                '}';
    }
}
