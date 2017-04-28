package com.terserah.yogs.duel.gui;

public class Countdown implements java.lang.Runnable{

    @Override
    public void run() {
        this.runTimer();
    }

    public void runTimer(){
        int i = 60;
        while (i>0){
          try {
            i--;
            Thread.sleep(1000L);    // 1000L = 1000ms = 1 second
           }
           catch (InterruptedException e) {
               //I don't think you need to do anything for your particular problem
           }
         }
    }

}