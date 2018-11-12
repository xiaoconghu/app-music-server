package com.app.music.utils;


import com.sun.management.OperatingSystemMXBean;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.util.UUID;

public class demo {
    public static void main(String[] args) {

        String line = null;
        String cmd = "wmic cpu get Name";
        try {
            Process pro = Runtime.getRuntime().exec(cmd);
            BufferedReader buf = new BufferedReader(new InputStreamReader(
                    pro.getInputStream(), "gbk"));
            while ((line = buf.readLine()) != null) {
                System.out.println(
                        new String(line.getBytes()));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}

class demo2 {
    public static void main(String[] args) {
        getDiskInfo();
        getMemInfo();
        // 获取uuid
        String Str1 = UUID.randomUUID().toString().replace("-", "");
        System.out.println(Str1);
    }

    public static void getDiskInfo() {
        File[] disks = File.listRoots();
        for (File file : disks) {
            System.out.print(file.getPath() + "    ");
            System.out.print("空闲未使用 = " + file.getFreeSpace() / 1024 / 1024 / 1024 + "G" + "    ");// 空闲空间
            System.out.print("已经使用 = " + file.getUsableSpace() / 1024 / 1024 / 1024 + "G" + "    ");// 可用空间
            System.out.print("总容量 = " + file.getTotalSpace() / 1024 / 1024 / 1024 + "G" + "    ");// 总空间
            System.out.println();
        }
    }

    public static void getMemInfo() {
        OperatingSystemMXBean mem = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        System.out.println("Total RAM：" + mem.getTotalPhysicalMemorySize() / 1024 / 1024 / 1024 + "GB");
        System.out.println("Available　RAM：" + mem.getFreePhysicalMemorySize() / 1024 / 1024 / 1024 + "GB");
    }
}
