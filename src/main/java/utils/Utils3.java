package utils;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.io.FileFilter;

public class Utils3 {

    public static void main(String[] args) {
        // 文件删除
        String filePath = "D:\\临时文件夹\\decompiled-Downfall";

        FileUtil.loopFiles(filePath, new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getAbsolutePath().contains("localization");
            }
        }).forEach(file -> {
            FileUtil.del(file);
        });
    }
}
