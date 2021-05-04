package ru.asselinux;

public class Main {

    public static void main(String[] args) throws InterruptedException {
//        runTenThreads();
//        printStates();
       runHundreadThreads();
//        nameThread();
    }
    private static void runTenThreads(){
        for (int i=0; i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100; i++) {
                        System.out.println(i);
                    }
                }

            }).start();
        }
    }
    private static void printStates(){
        Thread thread=new Thread(){
            @Override
            public void run() {
                System.out.println(getState());
            }
        };
        System.out.println(thread.getState());
        thread.start();
        try {
            thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(thread.getState());
    }
    public static void runHundreadThreads() throws InterruptedException {
        Counter counter=new Counter();
        for (int i=0; i<100;i++){
            Thread thread =new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        counter.increment();
                    }
                }
            };
            thread.start();
            try {
                thread.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println(counter.getCount());
    }


    public static void nameThread() throws InterruptedException {
        Object lock=new Object();
        for (int i=0; i<2;i++){
            Thread thread =new Thread() {
                @Override
                public void run() {
                    for (int i=0; i<10;i++) {
                        synchronized (lock) {
                            try {
                                System.out.println(getName());
                                lock.notify();
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            };
            thread.start();
        }
    }
}

