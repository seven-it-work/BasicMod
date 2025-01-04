package utils;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.io.FileFilter;

public class Utils1 {

    private static String renameFile(String fileName){
        return fileName.replace("basicmod","jywx");
    }
    public static void main(String[] args) {
        // 批量替换文件名称
        String filePath="D:\\steam\\steam\\steamapps\\common\\SlayTheSpire\\mods\\BasicMod\\JinYongWuXia\\src\\main\\resources\\JYWXMod\\localization\\eng";

        FileUtil.loopFiles(filePath, new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return true;
            }
        }).forEach(file -> FileUtil.move(file, new File(renameFile(file.getAbsolutePath())),true));
    }
}
