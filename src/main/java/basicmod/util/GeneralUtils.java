package basicmod.util;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class GeneralUtils {

    /**
     * 从给定的列表中随机获取 num 个不重复的元素。
     *
     * @param list 输入的列表
     * @param num  需要获取的元素数量
     * @param <T>  列表中元素的类型
     * @return 包含 num 个随机不重复元素的新列表
     */
    public static <T> List<T> getRandomElementsUsingSet(List<T> list, int num) {
        if (num > list.size()) {
            throw new IllegalArgumentException("不能从列表中选择比列表本身更多的元素");
        }

        Set<Integer> selectedIndices = new HashSet<>();
        Random random = new Random();
        while (selectedIndices.size() < num) {
            selectedIndices.add(random.nextInt(list.size()));
        }

        return selectedIndices.stream()
                .map(list::get)
                .collect(Collectors.toList());
    }

    public static String arrToString(Object[] arr) {
        if (arr == null)
            return null;
        if (arr.length == 0)
            return "";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length - 1; ++i) {
            sb.append(arr[i]).append(", ");
        }
        sb.append(arr[arr.length - 1]);
        return sb.toString();
    }

    public static String removePrefix(String ID) {
        return ID.substring(ID.indexOf(":") + 1);
    }
}
