package com.learn.io;

import java.io.InputStream;

/**
 * @Author liuxuke
 * @Title: Resources
 * @ProjectName IPersistence_test
 * @Description: //TODO
 * @date 2020/6/19 12:28
 */
public class Resources {
    /**
     * 根据配置文件的路径，将配置文件加载成字节输入流，存储在内存中
     * @param path 配置文件的路径
     * @return
     */
    public static InputStream getResourceAsSteam(String path) {
        InputStream inputStream = Resources.class.getClassLoader().getResourceAsStream(path);
        return inputStream;
    }
}
