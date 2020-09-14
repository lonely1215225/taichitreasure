package edu.hunau.crowd;

import edu.hunau.crowd.util.CrowdUtil;
import edu.hunau.crowd.util.ResultEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthTest {
   @Test
    public void testMessageCode(){
       ResultEntity messageCode = CrowdUtil.createMessageCode(null, null, null, null, null, null);
       System.out.println(messageCode);
   }
}
