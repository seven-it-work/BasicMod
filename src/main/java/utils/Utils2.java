package utils;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.io.FileFilter;

public class Utils2 {

    public static void main(String[] args) {
        // 文件迁移
        String filePath="D:\\临时文件夹\\decompiled-Downfall";
        String targetPath="D:\\steam\\steam\\steamapps\\common\\SlayTheSpire\\mods\\BasicMod\\decompiled-Downfall\\src\\main\\resources";

        FileUtil.loopFiles(filePath, new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getAbsolutePath().contains("localization");
            }
        }).forEach(file -> {
            FileUtil.copyFilesFromDir(file,new File(file.getAbsolutePath().replace(filePath,targetPath)),true);
        });
    }
}
