package top.zoowayss.bank.entity;

import lombok.extern.slf4j.Slf4j;
import top.zoowayss.bank.utils.ArraysMathUtil;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class System {

    /**
     * 资源种类
     */
    public int resourceType;

    /**
     * 可用资源
     */
    public int[] available;

    /**
     * 安全序列
     */
    public List<Integer> safeList = new ArrayList<>();

    public System(int resourceType, int[] available) {
        if (available == null || resourceType != available.length) {
            throw new RuntimeException("init system error");
        }
        this.resourceType = resourceType;
        this.available = available;
    }

    /**
     * 安全性检查
     *
     * @param ps
     * @return
     */
    public boolean isSafe(Process[] ps) {
        int[] work = available.clone();
        Process p = findNeedLessThenWork(ps, work);
        while (p != null) {
            ArraysMathUtil.add(work, p.allocation);
            safeList.add(p.id);
            p = findNeedLessThenWork(ps, work);
        }
        return hasAllFinished(ps);
    }

    /**
     * 打印安全序列
     */
    public void printSafeList() {
        StringBuilder builder = new StringBuilder();
        safeList.forEach(i -> builder.append("P" + i).append("->"));
        log.info("find a safe list is :{}", builder.substring(0, builder.length() - 2));
    }


    /**
     * 清空安全序列
     * 每次安全性检查前需要清空
     * @param ps
     */
    public void clearSafeList(Process[] ps) {
        for (Process p : ps) {
            p.finish = false;
        }
        safeList.clear();
    }

    /**
     * 找到一个 need 小于 work 的进程
     * @param ps
     * @param work
     * @return
     */
    private Process findNeedLessThenWork(Process[] ps, int[] work) {
        for (int i = 0; i < ps.length; i++) {
            if (!ps[i].finish && ArraysMathUtil.lessThen(ps[i].need, work)) {
                ps[i].finish = true;
                ps[i].id = i;
                return ps[i];
            }
        }
        return null;
    }

    /**
     * 检查是否所有进程都已经完成
     * @param ps
     * @return
     */
    private boolean hasAllFinished(Process[] ps) {
        for (Process p : ps) {
            if (!p.finish)
                return false;
        }
        return true;
    }
}
