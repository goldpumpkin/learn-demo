package com.gold.springcloudfeign.externalapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaiduApiTest {

    @Autowired
    private BaiduApi baiduApi;

    @Test
    public void testIndex() {
        System.out.println(baiduApi.index());
    }
}