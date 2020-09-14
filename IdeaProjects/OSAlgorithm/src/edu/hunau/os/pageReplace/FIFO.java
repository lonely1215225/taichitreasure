package edu.hunau.os.pageReplace;
import java.util.LinkedList;
public class FIFO {
    //这里将公共代码提取到了PageReplaceFactory中
    private static PageReplaceFactory pageReplaceFactory = null;
    private int[] pageNums ;//页面号
    private int[] memoryBlocks ;//内存块
    private boolean[] missingPages;//记录是否缺页
    private LinkedList<Integer> queue;//链表队列，用于返回最早进入内存的页面

    //初始化所有属性
    public FIFO() {
        pageReplaceFactory=new PageReplaceFactory(new int[]{3,2,1,0,3,2,4,3,2,1,0,4}, "FIFO");
        pageNums = pageReplaceFactory.pageNums;
        memoryBlocks = pageReplaceFactory.memoryBlocks;
        //记录页面分配情况二维表
        missingPages = pageReplaceFactory.missingPages;
        queue = pageReplaceFactory.queue;
    }

    public static void main(String[] args) {
        FIFO fifo = new FIFO();
        int exchangeNums = fifo.fifo();
        int interruptNums = pageReplaceFactory.showTable();
        System.out.println("页面置换次数：" + exchangeNums + "\n缺页中断次数：" + interruptNums + "\n缺页率：" +
                (((float) interruptNums / (float) (fifo.pageNums.length)) * 100) +
                "%");
    }

    //先进先出方法
    public int fifo() {
        int exchange = 0;
        pageReplaceFactory.handleHead("FIFO");
        //处理置换部分
        for (int i = memoryBlocks.length; i < pageNums.length; i++) {
            //判断内存块中是否存在当前页面
            if (!pageReplaceFactory.isExist(pageNums[i])) {
                int re = pageReplaceFactory.returnUnAlloc();//返回每一个未分配的内存块索引
                if (re == -1) {//若所有内存块都被分配
                    Integer firstPage = queue.removeFirst();
                    int index = pageReplaceFactory.returnExistIndex(firstPage);
                    queue.addLast(pageNums[i]);
                    memoryBlocks[index] = pageNums[i];
                    exchange++;
                } else {
                    queue.addLast(pageNums[i]);
                    memoryBlocks[re] = pageNums[i];
                }
                missingPages[i] = true;
            }
            pageReplaceFactory.logAllocated(i);
        }
        return exchange;
    }
}
