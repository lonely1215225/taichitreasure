package edu.hunau.spring.aaa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestAOP {
        public int div(int i ,int j) {
            return i/j;
        }

}
