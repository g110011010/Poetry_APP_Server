package com.gqf.Server;

import com.gqf.constants.CONSTANTS;
import com.gqf.db.MySql;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class Init implements InitializingBean {
/**
 * 会在服务器启动时执行*/
    @Override
    public void afterPropertiesSet() throws Exception {
//        连接到数据库
        new MySql(CONSTANTS.DB_USERNAME,CONSTANTS.DB_PASSWORD,CONSTANTS.DB_NAME,CONSTANTS.DB_HOST,CONSTANTS.DB_PORT);
    }
}
