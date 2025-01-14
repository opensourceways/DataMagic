package com.om.DataMagic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.om.DataMagic.task.TaskManager;

@SpringBootApplication
@ComponentScan(basePackages = { "com.om.DataMagic.*" })
@MapperScan("com.om.DataMagic.infrastructure.pgDB.mapper")
public class DataMagicApplication implements CommandLineRunner {
    @Autowired
    private TaskManager taskManager;

    public static void main(String[] args) {
        SpringApplication.run(DataMagicApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        taskManager.runTasks();
    }
}
