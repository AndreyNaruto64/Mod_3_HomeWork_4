public class Main {

    static char c = 'A';
    static Object m = new Object();

    static class WaitNotify implements Runnable {

        private char current;
        private char next;

        public WaitNotify(char current, char next) {
            this.current = current;
            this.next = next;
        }


        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                synchronized (m) {
                    try {
                        while (c != current) {
                            m.wait();
                        }
                        System.out.print(current);
                        c = next;
                        m.notifyAll();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    public static void main(String[] args) {
        new Thread(new WaitNotify('A','B')).start();
        new Thread(new WaitNotify('B','C')).start();
        new Thread(new WaitNotify('C','A')).start();

    }
}
