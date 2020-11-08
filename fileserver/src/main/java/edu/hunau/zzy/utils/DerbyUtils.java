package edu.hunau.zzy.utils;

import edu.hunau.zzy.entity.FileMetaInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DerbyUtils {
    private static final Logger logger = LoggerFactory.getLogger(DerbyUtils.class);
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            conn = DriverManager.getConnection("jdbc:derby:fileserverdb;create=true");
            logger.info("成功获取连接");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void createFileMetaInfoTable(Connection conn) {

        Statement state = null;
        try {
            state = conn.createStatement();

            DatabaseMetaData meta = conn.getMetaData();

            ResultSet res = meta.getTables(null, null, null, new String[]{"TABLE"});

            HashSet<String> set = new HashSet<String>();

            while (res.next()) {

                set.add(res.getString("TABLE_NAME"));

            }

            if (!set.contains("fileMetaInfo".toUpperCase()))
                // 将文件大小、文件类型，原始文件名、 创建时间、文件保存目录地址、文件加密数字信封等元数据记录至数据库中，
                state.executeUpdate("create table fileMetaInfo(file_uuid varchar(128),file_size int,file_type varchar(20)," +
                        "src_name varchar(100),create_time varchar(20),file_pos varchar(128),secret_key varchar(500),PRIMARY KEY (file_uuid))");
            else logger.warn("fileMetaInfo表已存在,无需创建,skip...");
            state.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertUploadInfo(Connection conn,String fileUUID, Long fileSize, String fileType, String srcName, String createTime, String filePos, String secretKey) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("insert into fileMetaInfo(file_uuid,file_size,file_type,src_name,create_time,file_pos,secret_key) values (?,?,?,?,?,?,?)");
            ps.setString(1, fileUUID);
            ps.setLong(2, fileSize);
            ps.setString(3, fileType);
            ps.setString(4, srcName);
            ps.setString(5, createTime);
            ps.setString(6, filePos);
            ps.setString(7, secretKey);
            ps.executeUpdate();
            logger.info("上传信息已保存到数据库！");
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<FileMetaInfo> queryInfo(Connection conn) {
        PreparedStatement ps = null;
        List<FileMetaInfo> fileMetaInfos = null;
        try {
            ps = conn.prepareStatement("select * from fileMetaInfo");
            ResultSet rs = ps.executeQuery();
            fileMetaInfos = new ArrayList<>();
            while (rs.next()) {
                FileMetaInfo fileMetaInfo = new FileMetaInfo(rs.getString(1), rs.getLong(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7));
                fileMetaInfos.add(fileMetaInfo);
            }

            fileMetaInfos.forEach(fileMetaInfo -> logger.info(fileMetaInfo.toString()));
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fileMetaInfos;
    }
    public static FileMetaInfo queryInfoById(Connection conn,String fileUUID) {
        PreparedStatement ps = null;
        FileMetaInfo fileMetaInfo = null;
        try {
            ps = conn.prepareStatement("select * from fileMetaInfo where file_uuid = ?");
            ps.setString(1,fileUUID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                fileMetaInfo = new FileMetaInfo(rs.getString(1), rs.getLong(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7));
            }
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fileMetaInfo;
    }
}
