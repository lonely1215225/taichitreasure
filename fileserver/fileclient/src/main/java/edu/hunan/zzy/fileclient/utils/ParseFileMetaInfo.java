package edu.hunan.zzy.fileclient.utils;

import edu.hunan.zzy.fileclient.entity.FileMetaInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 解析Jason字符串，并将解析出的信息封装到FileMetaInfo中，多个文件则放入list
 * @Author: zzy
 * @Date: 2020-11-06
 */
public class ParseFileMetaInfo {
    public static List<FileMetaInfo> parse(String str){
        String[] fileMetaInfos = str.split(",");
        List<FileMetaInfo> list=new ArrayList<>();
        for (int i = 0; i < fileMetaInfos.length; i++) {
            FileMetaInfo fileMetaInfo = new FileMetaInfo();

            String[] split = fileMetaInfos[i].split(";");
            fileMetaInfo.setDate(split[0].split(":")[1]);
            fileMetaInfo.setFilePos(split[1].substring(split[1].indexOf(":")+1));
            fileMetaInfo.setSrcName(split[2].split(":")[1]);
            fileMetaInfo.setSecretKey(split[3].split(":")[1]);
            fileMetaInfo.setFileSize(Long.parseLong(split[4].split(":")[1]));
            fileMetaInfo.setFileType(split[5].split(":")[1]);
            fileMetaInfo.setFileUUID(split[6].split(":")[1]);
            list.add(fileMetaInfo);
        }
        return list;
    }
}
