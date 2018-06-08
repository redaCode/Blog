package com.reda.service;

import com.reda.OwnException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //使用Transactional标志后，如果异常被catch,那么不会回滚
    @Transactional
    public void add(String value) throws OwnException {
        String sql = "insert into transaction(value) values('" + value+"')";
        String errorSql = "insert into transaction(id,value) values(1,'qsdad')";
//        try {
            jdbcTemplate.update(sql);
            jdbcTemplate.update(errorSql);
//            throw new RuntimeException();
            throw new OwnException();
//        }catch (RuntimeException e) {
//            e.printStackTrace();
//        }
    }

    //内部调用@Transactional标志的方法，事务是不会执行的，除非调用的方法也用@Transaction标志
//    @Transactional
    public void execAdd(String value) {
        try {
            add(value);
        } catch (OwnException e) {
            e.printStackTrace();
        }
    }
}
