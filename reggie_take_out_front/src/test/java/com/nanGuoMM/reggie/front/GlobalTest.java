package com.nanGuoMM.reggie.front;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GlobalTest {

    @Test
    void test01() {
        System.out.println(System.getProperty("user.dir"));
    }
}
