package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {
    @Resource
    private SuccessKilledDao successKilledDao;
    @Test
    public void insertSuccessKilled() throws Exception {
//        第一次 insertCount = 1
//        第二次 insertCount = 0
//        不允许重复插入
        long id = 1002L;
        long phone = 18322712303L;
        int insertCount = successKilledDao.insertSuccessKilled(id,phone);
        System.out.println("insertCount = " + insertCount);
    }

    @Test
    public void queryByIdWithSeckill() throws Exception {
        /**
         * SuccessKilled{seckillId=1001, userPhone=18322712303, state=-1,
         * createTime=Wed Aug 16 21:52:11 CST 2017}
         Seckill{seckillId=1001, name='1000元秒杀iphone6', number=100,
         startTime=Mon Aug 14 00:00:00 CST 2017, endTime=Tue Aug 15 00:00:00 CST 2017,
         createTime=Tue Aug 15 20:00:33 CST 2017}
         */
        long id = 1002L;
        long phone = 18322712303L;
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(id,phone);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
    }

}