package edu.hunau.zzy.service.getfilemetainfo.serviceimpl;

import edu.hunau.zzy.dao.getfilemetainfodao.GetFileMetaInfoDao;
import edu.hunau.zzy.dao.getfilemetainfodao.daoimpl.GetFileMetaInfoDaoImpl;
import edu.hunau.zzy.entity.FileMetaInfo;
import edu.hunau.zzy.service.getfilemetainfo.GetFileMetaInfoService;

public class GetFileMetaInfoServiceImpl implements GetFileMetaInfoService {

    @Override
    public FileMetaInfo queryFileMetaInfoById(String fileUUID) {
        GetFileMetaInfoDao metaInfoDao=new GetFileMetaInfoDaoImpl();
        return metaInfoDao.queryFileMetaInfoById(fileUUID);
    }
}
