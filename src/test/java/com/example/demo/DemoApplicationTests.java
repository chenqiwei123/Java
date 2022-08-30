package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@Slf4j
class DemoApplicationTests {

    @Test
    void contextLoads() {

        valid("2022-2-29");

        valid("2022-2-28");

        valid("2020-2-29");

        valid("2022-12-32");

        valid("2022-12-31");

        valid("2022-6-31");

        valid("2022/6/30 23:59:69","yyyy/MM/dd HH:mm:ss");

    }

    /**
     * 判断string类型的日期是否符合simpleDateFormat的日期转换
     * @param dateString  String类型的date
     * @param simpleDateFormat  日期格式化 例如: yyyy-MM-dd,不传默认设置yyyy-MM-dd格式
     * @return true 是一个符合simpleDateFormat的日期格式,false 反之.
     */
    public boolean valid(String dateString,String... simpleDateFormat){
        String format=simpleDateFormat.length==0?"yyyy-MM-dd":simpleDateFormat[0];
        SimpleDateFormat sdf=new SimpleDateFormat(format);
        // 底层调用calendar.setLenient(lenient),对日期进行严格校验,非正常日期直接抛出异常
        sdf.setLenient(false);
        try {
            //进行string的日期值进行转换校验
            Date parse = sdf.parse(dateString);
        } catch (ParseException e) {
            log.info("{}不是一个符合{}的日期格式",dateString,format);
            return false;
        }
        log.info("{}是一个符合{}的日期格式",dateString,format);
        return true;
    }

}
