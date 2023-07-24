package top.zoowayss.bank.entity;

import lombok.ToString;
import top.zoowayss.bank.utils.ArraysMathUtil;

import java.util.Arrays;

@ToString
public class Process {
    public static int length;

    /**
     * 进程id
     */
    public int id;
    /**
     * 最大需求
     */
    public int max[];

    /**
     * 已分配
     */
    public int allocation[];

    /**
     * 需求
     */
    public int need[];

    /**
     * 是否完成
     */
    public boolean finish = false;

    public Process(int length, String[] lines) {
        if (lines == null) {
            throw new RuntimeException("init process error");
        }
        this.length = length;
        compareLength(length, lines.length);
        this.max = createArray(lines[0]);
        this.allocation = createArray(lines[1]);
        this.need = createArray(lines[2]);


    }


    /**
     * 创建数组
     * eg: 3,0,2 字符串转 [3,0,2] 数组
     * @param line
     * @return
     */
    private int[] createArray(String line) {
        String[] split = line.split(",");
        compareLength(3, split.length);
        return Arrays.stream(split).mapToInt(Integer::parseInt).toArray();
    }

    /**
     * 比较数组长度
     * @param baseLength
     * @param l1
     */
    private void compareLength(int baseLength, int l1) {
        if (baseLength != l1) {
            throw new RuntimeException("Array length not compared. Please check the array length");
        }
    }

    /**
     * 请求资源
     * @param resourceArr
     * @param system
     */
    public void request(int[] resourceArr, System system) {
        if (!ArraysMathUtil.lessThen(resourceArr, need)) {
            throw new RuntimeException("This process needs more resources than the maximum he has announced.");
        }
        ArraysMathUtil.sub(system.available, resourceArr);
        ArraysMathUtil.add(allocation, resourceArr);
        ArraysMathUtil.sub(need, resourceArr);
    }
}
