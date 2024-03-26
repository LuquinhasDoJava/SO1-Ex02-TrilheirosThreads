package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Trilheiros extends Thread{
    Semaphore porta;
    public Trilheiros(Semaphore porta){
    this.porta = porta;
    }
    public void run(){
        int distancia = 0;
        Random random = new Random();
        do{
            distancia += random.nextInt(4,7);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Corredor "+getId()+"# está a "+(200-distancia)+"m da porta");
        }while (distancia < 200);
        System.err.println(getId()+"# Chegou na porta!");
        passarPelaPorta();
    }

    private void passarPelaPorta() {
        Random random = new Random();
        int tempo = random.nextInt(1000,2001);
        try {
            porta.acquire();
            System.err.println(getId()+"# esta passando pela porta!");
            sleep(tempo);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.err.println(getId()+"# já passou pela porta!!");
            porta.release();
        }
    }
}