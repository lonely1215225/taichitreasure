package edu.hunau.os.jobscheduling;

import java.util.Scanner;

public class FCFS {
    private ProcessInfo[] processInfos;
    private float[] averageInfos = new float[3];

    public FCFS() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入作业数量：");
        int processNum = scanner.nextInt();
        this.processInfos = new ProcessInfo[processNum];
        for (int i = 0; i < processNum; i++) {
            System.out.println("请输入进程" + (i + 1) + "到达时间：");
            int arrivalTime = scanner.nextInt();
            System.out.println("请输入进程" + (i + 1) + "运行时间：");
            int runningTIme = scanner.nextInt();
            this.processInfos[i] = new ProcessInfo(arrivalTime, runningTIme);
        }

    }

    public void calc() {
        float finishTime = 0;
        for (ProcessInfo processInfo : processInfos) {
            float runningTime = processInfo.getRunningTime();
            finishTime += runningTime;
            processInfo.setRoundTime(finishTime - processInfo.getArrivalTime());
            float roundTime = processInfo.getRoundTime();
            processInfo.setRoundTimeWithPower(roundTime / runningTime);
            processInfo.setWaitTime(roundTime - runningTime);
        }

        float totalRT = 0;
        float totalRTWP = 0;
        float totalWT = 0;
        for (ProcessInfo processInfo : processInfos) {
            totalRT += processInfo.getRoundTime();
            totalRTWP += processInfo.getRoundTimeWithPower();
            totalWT += processInfo.getWaitTime();
        }
        averageInfos[0] = totalRT / processInfos.length;//平均周转时间
        averageInfos[1] = totalRTWP / processInfos.length;//平均带权周转时间
        averageInfos[2] = totalWT / processInfos.length;//平均等待时间
    }

    public static void main(String[] args) {
        FCFS fcfs = new FCFS();
        fcfs.calc();
        for (ProcessInfo processInfo : fcfs.processInfos) {
            System.out.println(processInfo);
        }
        System.out.println("平均周转时间："+fcfs.averageInfos[0]);
        System.out.println("平均带权周转时间："+fcfs.averageInfos[1]);
        System.out.println("平均等待时间："+fcfs.averageInfos[2]);
    }

}

class ProcessInfo {
    private float arrivalTime;//到达时间
    private float runningTime;//运行时间
    private float roundTime;//周转时间
    private float roundTimeWithPower;//带权周转时间
    private float waitTime;//等待时间


    public ProcessInfo(int arrivalTime, int runningTime) {
        this.arrivalTime = arrivalTime;
        this.runningTime = runningTime;
    }

    @Override
    public String toString() {
        return "ProcessInfo{" +
                "arrivalTime=" + arrivalTime +
                ", runningTime=" + runningTime +
                ", roundTime=" + roundTime +
                ", roundTimeWithPower=" + roundTimeWithPower +
                ", waitTime=" + waitTime +
                '}';
    }

    public float getArrivalTime() {
        return arrivalTime;
    }


    public float getRunningTime() {
        return runningTime;
    }


    public float getRoundTime() {
        return roundTime;
    }

    public void setRoundTime(float roundTime) {
        this.roundTime = roundTime;
    }

    public float getRoundTimeWithPower() {
        return roundTimeWithPower;
    }

    public void setRoundTimeWithPower(float roundTimeWithPower) {
        this.roundTimeWithPower = roundTimeWithPower;
    }

    public float getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(float waitTime) {
        this.waitTime = waitTime;
    }

}