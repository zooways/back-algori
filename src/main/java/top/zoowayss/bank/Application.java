package top.zoowayss.bank;

import lombok.extern.slf4j.Slf4j;
import top.zoowayss.bank.entity.Process;
import top.zoowayss.bank.entity.System;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * @Description:
 * @Author: <a href="https://github.com/zooways">zooways</a>
 * @Date: 2023/7/24 09:40
 */
@Slf4j
public class Application {

    public static void main(String[] args) {
        System system = new System(3, new int[]{3, 3, 2});

        Process[] ps = initProcesses(system.resourceType);
        if (system.isSafe(ps)) {
            system.printSafeList();
        } else {
            log.error("system is not safe");
        }

        // p1 请求  1,0,2
        ps[1].request(new int[]{1, 0, 2}, system);
        system.clearSafeList(ps);
        if (system.isSafe(ps)) {
            system.printSafeList();
        } else {
            log.error("system is not safe");
        }

        // p4 请求  3,3,0
        ps[4].request(new int[]{3, 3, 0}, system);
        system.clearSafeList(ps);
        if (system.isSafe(ps)) {
            system.printSafeList();
        } else {
            log.error("system is not safe");
        }

    }

    /**
     * 从文件加载系统进程信息初始化
     * @param resourceType
     * @return
     */
    private static Process[] initProcesses(int resourceType) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./data.txt"));
            return reader.lines().map(line -> {
                String[] splits = line.split(";");
                validStrLength(resourceType, splits);
                return new Process(resourceType, splits);
            }).toList().toArray(new Process[resourceType]);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 校验文件中的进程信息是否正确
     * @param length
     * @param splits
     */
    private static void validStrLength(int length, String[] splits) {
        if (splits.length != length) {
            throw new RuntimeException("data source init error. Please check system resource type and process array length");
        }
    }
}
