package edu.hunau.os.pageReplace;
public class OPT {
    //这里将公共代码提取到了PageReplaceFactory中
    private static PageReplaceFactory pageReplaceFactory = null;
    private int[] pageNums;//页面号
    private int[] memoryBlocks;//内存块
    private boolean[] missingPages;//记录是否缺页

    public OPT() {
        pageReplaceFactory = new PageReplaceFactory(new int[]{7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0, 1}, "OPT");
        pageNums = pageReplaceFactory.pageNums;
        memoryBlocks = pageReplaceFactory.memoryBlocks;
        missingPages = pageReplaceFactory.missingPages;
    }

    //主函数
    public static void main(String[] args) {
        OPT opt = new OPT();
        int exchangeNums = opt.opt();
        int interruptNums = pageReplaceFactory.showTable();
        System.out.println("页面置换次数：" + exchangeNums + "\n缺页中断次数：" + interruptNums + "\n缺页率：" +
                (((float) interruptNums / (float) (opt.pageNums.length)) * 100) +
                "%");
    }


    //置换算法
    public int opt() {
        int exchange = 0;//页面置换次数
        //最先来的先分配内存块
        pageReplaceFactory.handleHead(null);
        //开始替换
        for (int i = memoryBlocks.length; i < pageNums.length; i++) {
            if (pageReplaceFactory.isExist(pageNums[i])) { //如果所有内存块中有某页面，那么就直接跳过。
                pageReplaceFactory.logAllocated(i);
                continue;
            }
            exchange+=replace(i);//否则发生缺页，替换
            pageReplaceFactory.logAllocated(i);
            missingPages[i] = true;

        }
        return exchange;
    }

    //替换方法
    public int replace(int curIndex) {
        int exchange=0;
        int rua = pageReplaceFactory.returnUnAlloc();
        if (rua != -1) {
            memoryBlocks[rua] = pageNums[curIndex];//替换
            return exchange;
        }
        int index = 0;
        int block = 0;
        int j;
        for (int i = 0; i < memoryBlocks.length; i++) {
            for (j = curIndex; j < pageNums.length; j++) {
                if (memoryBlocks[i] == pageNums[j]) break;
            }
            if (j > index) {
                index = j;
                block = i;
            }
        }
        memoryBlocks[block] = pageNums[curIndex];//替换
        exchange++;
        return exchange;
    }
}