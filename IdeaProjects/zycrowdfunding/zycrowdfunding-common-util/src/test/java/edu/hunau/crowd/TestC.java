package edu.hunau.crowd;

import edu.hunau.crowd.util.CrowdUtil;
import org.junit.Test;

public class TestC {

    @Test
    public void testMD5(){
        System.out.println(CrowdUtil.md5Encrypt("123456"));
    }
}
