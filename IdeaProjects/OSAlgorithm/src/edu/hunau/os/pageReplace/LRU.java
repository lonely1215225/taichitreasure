package edu.hunau.os.pageReplace;

public class LRU {
    //这里将公共代码提取到了PageReplaceFactory中
    private static PageReplaceFactory pageReplaceFactory = null;
    private int[] pageNums = null;//页面号
    private int[] memoryBlocks = null;//内存块
    private boolean[] missingPages;//记录是否缺页

    public LRU() {
        pageReplaceFactory = new PageReplaceFactory(new int[]{1,8,1,7,8,2,7,2,1,8,3,8,2,1,3,1,7,1,3,7}, "LRU");
        pageNums = pageReplaceFactory.pageNums;
        memoryBlocks = pageReplaceFactory.memoryBlocks;
        missingPages = pageReplaceFactory.missingPages;
    }

    public static void main(String[] args) {
        LRU lru = new LRU();
        int exchangeNums = lru.lRU();
        int interruptNums = pageReplaceFactory.showTable();
        System.out.println("页面置换次数：" + exchangeNums + "\n缺页中断次数：" + interruptNums + "\n缺页率：" +
                (((float) interruptNums / (float) (lru.pageNums.length)) * 100) +
                "%");
    }
    public int lRU(){
        int exchange=0;
        pageReplaceFactory.handleHead("LRU");
        int i;
        for (i = memoryBlocks.length; i < pageNums.length; i++) {
            if (!pageReplaceFactory.isExist(pageNums[i])) {//如果当前页面未分配内存块
                int re = pageReplaceFactory.returnUnAlloc();//返回每一个未分配的内存块索引
                if (re == -1) {//若所有内存块都被分配,需要替换
                    break;
                }else {//分配空闲内存块
                    memoryBlocks[re] = pageNums[i];
                    missingPages[i]=true;
                }
            }
            pageReplaceFactory.logAllocated(i);
        }
        for (int j = i; j < pageNums.length; j++) {
            if (!pageReplaceFactory.isExist(pageNums[j])) {//如果当前页面未分配内存块
                int MBIndex = findIndex(j);
                memoryBlocks[MBIndex] = pageNums[j];
                missingPages[j]=true;
                exchange++;
            }
            pageReplaceFactory.logAllocated(j);
        }
        return exchange;
    }
    public int findIndex(int index){
        int min = 0;
        int beforeMissing = findBeforeMissing(index);
        for (int i = index-1; i >beforeMissing; i--) {
            int existIndex = pageReplaceFactory.returnExistIndex(pageNums[i]);
            if (existIndex!=-1)
                min=existIndex;
        }
        return min;
    }
    public int findBeforeMissing(int index){
        for (int i = index-1; i>=0; i--) {
            if (missingPages[i])
                return i;
        }
        return -1;
    }
}
