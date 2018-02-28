package com.chengqianyun.eeweb2network.test;


import com.chengqianyun.eeweb2network.App;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author 聂鹏
 * @version 1.0
 * @date 18/2/28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class BaseTest {

  @BeforeClass
  public static void init() {
  }

}