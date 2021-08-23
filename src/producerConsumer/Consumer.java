package producerConsumer;

import java.util.ArrayList;

public class Consumer extends Thread{
    ArrayList<String> list;
    public Consumer(ArrayList<String> list){
        this.list=list;
    }

    public void run(){
        try{
            synchronized (list){
                while(true){
                    if(list.size()==0) {
                        System.out.println("consumer is waiting ");
                        list.wait();
                    }else
                        consume();
                }
            }
        } catch (Exception e) {
            System.out.println( "error");
        }
    }
    private void consume() throws Exception {

       while(!list.isEmpty()){
           Thread.sleep(1000);
           new Database().insertLines(list.get(0));
            list.remove(0);
            System.out.println("Element removed");
        }
        list.notifyAll();
    }

}
