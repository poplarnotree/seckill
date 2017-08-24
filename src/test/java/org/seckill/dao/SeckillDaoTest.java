package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 配置Spring和junit整合，junit启动时加载springIOC容器
 * spring-test,junit
 */

@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {
//   注入Dao实现类依赖
    @Resource
    private SeckillDao seckillDao;
    @Test
    public void reduceNumber() throws Exception {
        /**
         * UPDATE
         * seckill
         * SET number = number - 1
         * WHERE seckill_id = ? AND start_time <= ? AND end_time >= ? AND number > 0;
        updataCount = 0
        * */
        Date killTime = new Date();
        int updataCount = seckillDao.reduceNumber(1001L,killTime);
        System.out.println("updataCount = "+ updataCount);
    }

    @Test
    public void queryById() throws Exception {
        long id = 1001;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
        /**
        * 1000元秒杀iphone6
        * Seckill{seckillId=1001, name='1000元秒杀iphone6', number=100, startTime=Mon Aug 14 00:00:00 CST 2017, endTime=Tue Aug 15 00:00:00 CST 2017, createTime=Tue Aug 15 20:00:33 CST 2017}
        * */
    }

    @Test
    public void queryAll() throws Exception {
        /**
         * Seckill{seckillId=1001, name='1000元秒杀iphone6', number=100, startTime=Mon Aug 14 00:00:00 CST 2017, endTime=Tue Aug 15 00:00:00 CST 2017, createTime=Tue Aug 15 20:00:33 CST 2017}
         Seckill{seckillId=1002, name='500元秒杀ipad2', number=200, startTime=Mon Aug 14 00:00:00 CST 2017, endTime=Tue Aug 15 00:00:00 CST 2017, createTime=Tue Aug 15 20:00:33 CST 2017}
         Seckill{seckillId=1003, name='300元秒杀小米4', number=300, startTime=Mon Aug 14 00:00:00 CST 2017, endTime=Tue Aug 15 00:00:00 CST 2017, createTime=Tue Aug 15 20:00:33 CST 2017}
         Seckill{seckillId=1004, name='200元秒杀红米note', number=400, startTime=Mon Aug 14 00:00:00 CST 2017, endTime=Tue Aug 15 00:00:00 CST 2017, createTime=Tue Aug 15 20:00:33 CST 2017}
         */
        List<Seckill> seckills = seckillDao.queryAll(0,100);
        for (Seckill seckill : seckills){
            System.out.println(seckill);
        }
    }

}