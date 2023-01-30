package main;

import frame.Window;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Manager {
    private Thread thread;
    private ArrayList<Thread> threads;

    public Manager() {
        threads = new ArrayList<Thread>();
    }

    public void createMascot() {
        thread = new Thread(new OriginRunnable());
        threads.add(thread);
        thread.start();
    }

    public void allDeleteMascot() {
        for(Thread thread: threads){
            thread.stop();
        }
    }
}


class OriginRunnable implements Runnable {

    @Override
    public void run() {

        Window window = new Window();//ウィンドウの作成

        Random random = new Random();

        int walkingDistance = 0;

        String walktDirection = null;
        String[] directions = {"前", "右", "左", "後"};


        while (window.getFlg()) {
            walkingDistance = random.nextInt(10) + 10;//最低でも10は歩く
            walktDirection = directions[random.nextInt(4)];//進む向き

            //walkは
            window.m.walk(walktDirection, walkingDistance);

            try {
                Thread.sleep(random.nextInt(400) + 300);//walkするたびsleep
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
