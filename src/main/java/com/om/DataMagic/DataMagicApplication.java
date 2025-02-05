package com.om.DataMagic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.retry.annotation.EnableRetry;

import com.om.DataMagic.task.TaskManager;

@SpringBootApplication
@EnableRetry
@ComponentScan(basePackages = {"com.om.DataMagic.*"})
@MapperScan("com.om.DataMagic.infrastructure.pgDB.mapper")
public class DataMagicApplication implements CommandLineRunner {
    /**
     * task manager.
     */
    @Autowired
    private TaskManager taskManager;

    /**
     * @param args system args.
     */
    public static void main(String[] args) {
        SpringApplication.run(DataMagicApplication.class, args);
    }

    /**
     * @param args system args.
     * @throws Exception .
     */
    @Override
    public void run(String... args) throws Exception {
        taskManager.runTasks();
    }
}
