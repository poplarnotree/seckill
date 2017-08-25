package org.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"
})
public class SeckillServiceTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() throws Exception {
        List<Seckill> list = seckillService.getSeckillList();
        logger.info("list = {}",list);
    }

    @Test
    public void getById() throws Exception {
        long id = 1001;
        Seckill seckill = seckillService.getById(id);
        logger.info("seckill = {}",seckill);
    }

//    集成测试代码完整逻辑，注意可重复执行
    @Test
    public void testSeckillLogic() throws Exception {
        long id = 1001;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if(exposer.isExposed()) {
            logger.info("exposer = {}", exposer);
            long phone = 18322712303L;
            try {
                SeckillExecution execution = seckillService.executeSeckill(id,phone,exposer.getMd5());
                logger.info("result = {}",execution);
            }catch (RepeatKillException e){
                logger.error(e.getMessage());
            }catch (SeckillCloseException e){
                logger.error(e.getMessage());
            }
        }else {
//            秒杀未开启
            logger.warn("exposer = {}" ,exposer);
        }
//        INFO  o.seckill.service.SeckillServiceTest - exposer =
//        Exposer{exposed=true, md5='56e95051ce6cd3383b5ba25cb38030f1',
//        seckillId=1001, now=0, start=0, end=0}
    }

    @Test
    public void executeSeckill() throws Exception {
        long id = 1001;
        long phone = 18322712303L;
        String md5 = "56e95051ce6cd3383b5ba25cb38030f1";
        try {
            SeckillExecution execution = seckillService.executeSeckill(id,phone,md5);
            logger.info("result = {}",execution);
        }catch (RepeatKillException e){
            logger.error(e.getMessage());
        }catch (SeckillCloseException e){
            logger.error(e.getMessage());
        }
    }

    @Test
    public void executeSeckillProcedure(){
        long seckillId = 1001;
        long phone = 18322712303l;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        if (exposer.isExposed()){
            String md5 = exposer.getMd5();
            SeckillExecution execution = seckillService.executeSeckillProcedure(seckillId, phone, md5);
            logger.error(execution.getStateInfo());
        }
    }
}