package edu.hunau.os.jobscheduling;

import java.util.*;

public class PriorityNum {
    private Process[] pis = null;//保存作业数组

    //初始化输入信息。
    public PriorityNum() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入作业数量(整数)：");
        int processNum = scanner.nextInt();
        int arrivalTime = 0;
        int runningTime = 0;
        int priorityNum = 0;
        pis = new Process[processNum];
        for (int i = 0; i < processNum; i++) {
            System.out.println("请输入进程" + (i + 1) + "到达时间(整数)：");
            arrivalTime = scanner.nextInt();
            System.out.println("请输入进程" + (i + 1) + "运行时间(整数)：");
            runningTime = scanner.nextInt();
            System.out.println("请输入进程" + (i + 1) + "的优先数(整数)：");
            priorityNum = scanner.nextInt();
            pis[i] = new Process((i + 1), arrivalTime, runningTime, priorityNum);
        }
        showInit();
    }

    public void showInit() {
        System.out.println("PID arrivalTime runningTime priority");
        for (int i = 0; i < pis.length; i++) {
            System.out.println(pis[i].getPID() + "\t" + pis[i].getArrivalTime() + "\t   " + pis[i].getRunTime() + "\t     " + pis[i].getPriorityNum());
        }
    }

    public void scheduling() {
        LinkedList<Process> ready = new LinkedList<>();//就绪队列
        List<Process> finish = new ArrayList<>();
        ready.addLast(pis[0]);
        int i = 1;//用于标记未到就绪队列得进程
        int time = 0;
        System.out.print(pis[0].getArrivalTime() + "时刻:");
        Process first = ready.getFirst();
        System.out.println("P" + first.getPID() + "(" + first.getRunTime() + ")");
        while (!ready.isEmpty()) {
            //首先处理第一个到达就绪状态的进程
            Process rp = ready.removeFirst();
            //处理未到就绪队列的进程
            if (i < pis.length) {
                run(rp, i);
                ready.addLast(pis[i]);
                time = pis[i].getArrivalTime();
                i++;
            } else {//处理就绪队列里的进程
                time += rp.getRunTime();
                rp.setRunTime(0);
            }
            //按照优先数降序排序
            Collections.sort(ready, (p1, p2) -> p2.getPriorityNum() - p1.getPriorityNum());

            //处理时刻显示
            if (!ready.isEmpty() && rp.getPriorityNum() >= ready.getFirst().getPriorityNum())
                time += rp.getRunTime();


            showReady(ready, time);//显示信息

            finish.add(rp);
            //如果将要上处理机的进程运行时间为0，则跳过，进行下一个进程
            if (rp.getRunTime() == 0) {
                continue;
            }
            //如果就绪队列队头优先数高于在处理机上的进程，则将运行的进程下处理机回到就绪队列队尾，继续下一个进程
            if (rp.getPriorityNum() < ready.getFirst().getPriorityNum()) {
                ready.addLast(rp);
                continue;
            } else {//如果当前运行在处理机上的进程优先级更高，那么就让它一种占用处理机，直到其运行时间为0
                rp.setRunTime(0);
            }


        }
        System.out.println("运行完成队列：");
        finish.forEach((data) -> {
            System.out.print("P" + data.getPID() + "  ");
        });
    }

    //处理未到就绪队列的进程
    public void showReady(LinkedList<Process> ready, int time) {
        System.out.print(time + "时刻:");
        if (ready.isEmpty())
            System.out.println("就绪队列为空");
        else {
            for (int i = 0; i < ready.size(); i++) {
                Process first = ready.get(i);
                if (i == ready.size() - 1)
                    System.out.print("P" + first.getPID() + "(" + first.getRunTime() + ")");
                else
                    System.out.print("P" + first.getPID() + "(" + first.getRunTime() + ")-->");
            }
            System.out.println();
        }
    }

    public int run(Process process, int next) {
        //基于到达时间升序，进行剩余运行时间计算
        int runtime = process.getRunTime();
        int leftRunTime = pis[next - 1].getRunTime() - (pis[next].getArrivalTime() - pis[next - 1].getArrivalTime());
        if (leftRunTime < 0) {
            process.setRunTime(0);
        } else {
            process.setRunTime(leftRunTime);
        }
        return runtime;
    }

    public static void main(String[] args) {
        System.out.println("===================================");
        System.out.println("==============优先数调度=============");
        System.out.println("注意：请按照时间顺序输入到达时间！");
        PriorityNum priorityNum = new PriorityNum();
        priorityNum.scheduling();
    }
}

class Process {
    private int PID;
    private int arrivalTime;
    private int runTime;
    private int priorityNum;

    @Override
    public String toString() {
        return "-->(" +
                "PID:" + PID +
                ", arrivalTime=" + arrivalTime +
                ", runTime=" + runTime +
                ", priorityNum=" + priorityNum +
                ')';
    }

    public Process(int PID, int arrivalTime, int runTime, int priorityNum) {
        this.PID = PID;
        this.arrivalTime = arrivalTime;
        this.runTime = runTime;
        this.priorityNum = priorityNum;
    }

    public int getPID() {
        return PID;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getRunTime() {
        return runTime;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    public int getPriorityNum() {
        return priorityNum;
    }

    public void setPriorityNum(int priorityNum) {
        this.priorityNum = priorityNum;
    }
}