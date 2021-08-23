package producerConsumer;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> list=new ArrayList<>();
        Producer producer=new Producer(list);
        Consumer consumer=new Consumer(list);
        producer.start();
        consumer.start();
    }
}
