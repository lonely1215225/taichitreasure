package edu.hunau.os.pageReplace;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class PageReplaceFactory {
    protected int[] pageNums;//页面号
    protected int[] memoryBlocks;//内存块
    protected int[][] allocatedMBTable;//记录页面分配情况二维表
    protected boolean[] missingPages;//记录是否缺页
    protected LinkedList<Integer> queue;//链表队列，用于返回最早进入内存的页面

    public PageReplaceFactory(int[] ints, String strategy) {
        if ("FIFO".equalsIgnoreCase(strategy)) {
            System.out.println("===============FIFO(先进先出置换)==================");
            queue = new LinkedList<>();
        }else if ("OPT".equalsIgnoreCase(strategy)){
            System.out.println("===============OPT(最佳置换)==================");
        }else if ("LRU".equalsIgnoreCase(strategy)){
            System.out.println("===============LRU(最近最久未使用置换)==================");
        }
        //这里处理页面序列号的交互
        ArrayList<Integer> ps = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要访问的页面号序列{结束以-1结尾}:\n(如果直接输入-1则使用默认)");
        int page;
        while (true) {
            page = scanner.nextInt();
            if (page == -1)
                break;
            ps.add(page);
        }
        if (ps.isEmpty()) {
            System.out.println("默认页面序号："+ Arrays.toString(ints));
            pageNums = ints;
        } else {
            pageNums = new int[ps.size()];
            for (int i = 0; i < ps.size(); i++) {
                pageNums[i] = ps.get(i);
            }
        }

        //这里处理内存块数的输入
        System.out.println("请分配内存块数：");
        int memoryBlocks = scanner.nextInt();
        missingPages = new boolean[pageNums.length];
        this.memoryBlocks = new int[memoryBlocks];
        Arrays.fill(this.memoryBlocks, -1);
        allocatedMBTable = new int[pageNums.length][this.memoryBlocks.length];
    }

    //找到第一个还未分配的内存块
    int returnUnAlloc() {
        for (int i = 0; i < memoryBlocks.length; i++) {
            if (-1 == memoryBlocks[i])
                return i;
        }
        return -1;
    }

    //展示最后的置换表
    public int showTable() {
        int interrupt = 0;
        System.out.println("==============页面置换表==============");
        for (int i = 0; i < memoryBlocks.length; i++) {
            System.out.print("\t 内存块" + (i + 1));
        }
        System.out.println("  是否缺页");
        for (int i = 0; i < allocatedMBTable.length; i++) {
            System.out.print("页面" + pageNums[i] + ":");
            for (int j = 0; j < allocatedMBTable[i].length; j++) {
                System.out.print("\t" + allocatedMBTable[i][j] + "\t");
            }
            System.out.println("  " + missingPages[i]);
            if (missingPages[i])
                interrupt++;
        }
        return interrupt;
    }

    //存储置换表
    void logAllocated(int index) {
        //要复制的数组memoryBlocks
        //复制原数组的起始位置0
        //复制到allocatedMBTable[index]
        //复制到allocatedMBTable的起始位置0
        //要复制的长度
        System.arraycopy(memoryBlocks, 0, allocatedMBTable[index], 0, memoryBlocks.length);
    }

    //返回已分配内存块中与传入页面号相同的内存块索引
    int returnExistIndex(int pageNum) {
        for (int i = 0; i < memoryBlocks.length; i++) {
            if (pageNum == memoryBlocks[i])
                return i;
        }
        return -1;
    }

    //判断内存块中是否存在某页面
    boolean isExist(int pageNum) {
        for (int mb : memoryBlocks) {
            if (mb == pageNum)
                return true;
        }
        return false;
    }

    //先处理内存块数量的页面
    void handleHead(String strategy) {
        //解决前几个(内存块分配数)页面的分配,即处理中断除去置换的部分
        for (int i = 0; i < memoryBlocks.length; i++) {
            if (i >= pageNums.length || isExist(pageNums[i])) {
                if (i >= pageNums.length)
                    break;
                logAllocated(i);
                continue;
            }
            if ("FIFO".equals(strategy))
                queue.addLast(pageNums[i]);
            int unAllocIndex = returnUnAlloc();
            memoryBlocks[unAllocIndex] = pageNums[i];
            logAllocated(i);
            missingPages[i] = true;
        }
    }
}
