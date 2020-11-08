package edu.hunau.zzy;

import edu.hunau.zzy.entity.FileMetaInfo;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DerbyTest {
    @Test
    public void TestDerby(){
        try {

            Class.forName("org.apache.derby.jdbc.ClientDriver");

            Connection conn = DriverManager.getConnection("jdbc:derby:fileserverdb;create=true");

            Statement state = conn.createStatement();

            DatabaseMetaData meta = conn.getMetaData();

            ResultSet res = meta.getTables(null, null, null, new String[]{"TABLE"});

            HashSet<String> set=new HashSet<String>();

            while (res.next()) {

                set.add(res.getString("TABLE_NAME"));

            }
            System.out.println(set);

            if (!set.contains("fileMetaInfo".toUpperCase()))
            // 将文件大小、文件类型，原始文件名、 创建时间、文件保存目录地址、文件加密数字信封等元数据记录至数据库中，
            state.executeUpdate("create table fileMetaInfo(file_uuid varchar(128),file_size Bigint,file_type varchar(20)," +
                    "src_name varchar(100),create_time Date,file_pos varchar(128),secret_key varchar(500),PRIMARY KEY (file_uuid))");
            else System.out.println("该表已存在！！！");
            state.close();



            Statement state2 = conn.createStatement();

            state2.executeUpdate("insert into fileMetaInfo(file_uuid,file_size,file_type) values ('sijd12231233443112',12138,'png') ");
            state2.close();



            PreparedStatement pstate1 = conn.prepareStatement("select * from fileMetaInfo");



            ResultSet rset1 = pstate1.executeQuery();
            List<FileMetaInfo> fileMetaInfos=new ArrayList<>();
            while(rset1.next()) {
                FileMetaInfo fileMetaInfo = new FileMetaInfo(rset1.getString(1),rset1.getLong(2),
                        rset1.getString(3), rset1.getString(4),
                        rset1.getString(5),rset1.getString(6),rset1.getString(7));
                fileMetaInfos.add(fileMetaInfo);
            }

            fileMetaInfos.forEach(fileMetaInfo -> System.out.println(fileMetaInfo));
            pstate1.close();



            conn.close();

        } catch (SQLException e) {

            e.printStackTrace();

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        }
    }
}
