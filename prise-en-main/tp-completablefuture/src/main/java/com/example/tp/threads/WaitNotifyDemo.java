package com.example.tp.threads;

public class WaitNotifyDemo {

    static class MessageBox {
        private String msg;
        private boolean empty = true;

        public synchronized void put(String m) {
            while (!empty) { try { wait(); } catch (InterruptedException ignored) {} }
            msg = m;
            empty = false;
            notifyAll();
        }

        public synchronized String take() {
            while (empty) { try { wait(); } catch (InterruptedException ignored) {} }
            String m = msg;
            msg = null;
            empty = true;
            notifyAll();
            return m;
        }
    }

    public static void main(String[] args) {
        MessageBox box = new MessageBox();

        Thread producer = new Thread(() -> {
            String[] data = {"Bonjour", "Les", "Stagiaires", "FIN"};
            for (String m : data) {
                try { Thread.sleep(200); } catch (InterruptedException ignored) {}
                System.out.println("[P] produce: " + m);
                box.put(m);
            }
        }, "producer");

        Thread consumer = new Thread(() -> {
            for (;;) {
                String m = box.take();
                System.out.println("    [C] consume: " + m);
                if ("FIN".equals(m)) break;
            }
        }, "consumer");

        producer.start();
        consumer.start();
    }
}
