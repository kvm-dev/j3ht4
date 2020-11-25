package ru.geekbrains.j3.ht4;

public class ht4 {
    static volatile char s = 'A';
    static Object obj = new Object();

    public static void main(String[] args) {
        new Thread(new WaitingNotifyClass('A', 'B')).start();
        new Thread(new WaitingNotifyClass('B', 'C')).start();
        new Thread(new WaitingNotifyClass('C', 'A')).start();

        //запускаем доп задача
       // mfu mfu = new mfu();
        //new Thread(() -> mfu.print("A", 2)).start();
        //new Thread(() -> mfu.scan("B", 1)).start();
        //new Thread(() -> mfu.copy("C", 3)).start();
    }

    static class WaitingNotifyClass implements Runnable {
        private char currentLetter;
        private char nextLetter;

        public WaitingNotifyClass(char currentLetter, char nextLetter) {
            this.currentLetter = currentLetter;
            this.nextLetter = nextLetter;
        }


        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                synchronized (obj) {
                    try {
                        while (s != currentLetter)
                            obj.wait();
                        System.out.print(currentLetter);
                        s = nextLetter;
                        obj.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    // доп задача как-то не пошла..пару раз начинал делать получается ерунда.. удалил, потом попробую еще раз...
}