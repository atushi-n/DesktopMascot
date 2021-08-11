package main;

import frame.Mascot;
import frame.Window;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.util.Random;

public class Main {

    static boolean flg;


    public static void main(String[] args) throws InterruptedException {

        Window window = new Window();//ウィンドウの作成


        flg = true;

        Random random = new Random();


        int distance = 0;

        String currentDirection = null;
        String[] directions = {"前", "右", "左", "後"};




        while (flg) {
            distance = random.nextInt(10) + 10;//最低でも10は歩く
            currentDirection = directions[random.nextInt(4)];//進む向き

            //walkは
            window.m.walk(currentDirection, distance);



            Thread.sleep(random.nextInt(400) + 300);//walkするたびsleep

        }

    }
}
