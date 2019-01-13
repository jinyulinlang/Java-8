package com.gao.task;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TaskModel implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println ("正在紧张的计算----------");
        Thread.sleep (3000);
        return 1;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TaskModel taskModel = new TaskModel ();
        FutureTask<Integer> fu = new FutureTask<> (taskModel);
        Thread thread = new Thread (fu);
        thread.start ();
        System.out.println ("计算出的结果是---------" + fu.get ());


    }

}
