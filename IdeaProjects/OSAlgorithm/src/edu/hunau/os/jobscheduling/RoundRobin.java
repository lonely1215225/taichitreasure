package edu.hunau.os.jobscheduling;

import java.util.*;

public class RoundRobin {
    private PI[] pis = null;//保存作业数组
    private int timeSlice = 0;//时间片

    //初始化输入信息。
    public RoundRobin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入作业数量：");
        int processNum = scanner.nextInt();
        int arrivalTime = 0;
        int runningTime = 0;
        pis = new PI[processNum];
        for (int i = 0; i < processNum; i++) {
            System.out.println("请输入进程" + (i + 1) + "到达时间：");
            arrivalTime = scanner.nextInt();
            System.out.println("请输入进程" + (i + 1) + "运行时间：");
            runningTime = scanner.nextInt();
            pis[i] = new PI((i + 1), arrivalTime, runningTime);
        }
        System.out.println("请输入时间片大小：");
        this.timeSlice = scanner.nextInt();
        showInit();
    }
public void showInit(){
    System.out.println("PID\tarrivalTime\trunningTime");
    for (int i = 0; i < pis.length; i++) {
        System.out.println(pis[i].getpID()+"\t   "+pis[i].getArrivalTime()+"\t   "+pis[i].getRunningTime());
    }
}
    //时间片轮转调度简单算法
    public void RR() {
        List<PI> runQueue = new ArrayList<>();//用于存储运行过的进程
        LinkedList<PI> readyQueue = new LinkedList<>();//就绪队列
        readyQueue.addLast(pis[0]);//首先第一个进程进入就绪状态
        int temp = 0, curTime = 0, curT = 0;//temp用于遍历作业数组，curTime表示运行的时刻，curT表示curTime修改后的
        while (!readyQueue.isEmpty()) {//就绪队列不为空则进行调度
            showReadyQueue(curT, readyQueue);
            PI rf = readyQueue.removeFirst();//就绪队列队头进程进入运行状态
            curT += run(rf, curTime);//运行rf队头
            runQueue.add(rf);//运行后的进程
            if (temp < pis.length - 1) {//如果PI里的进程还没有全都到就绪队列，则进行下列操作
                temp++;
                readyQueue.addLast(pis[temp]);
            }
            if (rf.getRunningTime() != 0)//判断就绪队列中该进程是否已经运行完毕，如果runningTime=0，则直接跳过
                readyQueue.addLast(rf);
        }
        System.out.println(curT+"时刻：就绪队列为空");
        System.out.println("运行队列：");
        runQueue.forEach((i) -> {
            System.out.print("P"+i.getpID()+"  ");
        });
    }

    public int run(PI pi, int curTime) {//运行方法
        if (pi.getRunningTime() <= timeSlice) {//判断运行进程的运行时间是否小于时间片
            curTime += pi.getRunningTime();
            pi.setRunningTime(0);//小于时间片直接为0
        } else {//否则就花费一个时间片执行该进程任务
            curTime += timeSlice;
            pi.setRunningTime(pi.getRunningTime() - timeSlice);
        }
        return curTime;//返回某运行时刻
    }

    public void showReadyQueue(int curT, LinkedList<PI> readyQueue) {
        System.out.print(curT + "时刻：");
        for (int i = 0; i < readyQueue.size(); i++) {
            PI pi = readyQueue.get(i);
            if (i == readyQueue.size() - 1)
                System.out.print("P" + pi.getpID() +
                        "("+pi.getRunningTime()+")" );
            else
                System.out.print("P" + pi.getpID() +
                        "("+pi.getRunningTime()+")" + "-->");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("===========================================================");
        System.out.println("=======================时间片轮转调度算法（抢占式）======================");
        System.out.println("注意：时间片轮转调度算法是基于先来先服务,请在输入信息上的时间应保证时间顺序");
        System.out.println("注意：由于时间片轮转更关心响应时间，所以这里不再计算周转带权等待之类的时间");
        System.out.println("例如（到达时间从小到大，从上往下）：\n进程   到达时间   运行时间\nP1      0        5\nP2      2        4\nP3      4        1\nP4      5        6");
        System.out.println("P1(3)表示:P1进程运行时间剩余为3");
        System.out.println("注意：时间片大小不可太小或者太大，太大则会退化为先来先服务。\n  比如上述例子，如果时间片为5，则每次就绪队列都只存在一个进程");
        RoundRobin robin = new RoundRobin();
        robin.RR();
    }

}

//进程信息类
class PI {
    private int pID;//进程号
    private int arrivalTime;//到达时间
    private int runningTime;//运行时间

    @Override
    public String toString() {
        return "-->(" +
                "pID:" + pID +
                ", runningTime=" + runningTime +
                ")";
    }

    public int getpID() {
        return pID;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    public PI(int pID, int arrivalTime, int runningTime) {
        this.pID = pID;
        this.arrivalTime = arrivalTime;
        this.runningTime = runningTime;
    }
}