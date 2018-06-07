package com.reda;

import com.reda.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Springboot会自动装配好PlatformTransactionManager的Bean，
 * 要使用事务，直接在业务方法上标志@Transactional
 *
 */
@SpringBootApplication
@RestController
public class TransactionApplication
{
    @Autowired
    TransactionService transactionService;


    public static void main( String[] args )
    {
        SpringApplication.run(TransactionApplication.class);
    }

    @RequestMapping("/add/{value}")
    public String add(@PathVariable String value) {
        try {
            transactionService.add(value);
        } catch (OwnException e) {
            e.printStackTrace();
        }
        return "add successful";
    }

    @RequestMapping("/internalAdd/{value}")
    public String internalAdd(@PathVariable String value) {
        transactionService.execAdd(value);
        return "add successful";
    }

}
