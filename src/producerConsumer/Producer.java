package producerConsumer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Producer extends Thread{
    ArrayList<String> list;
    ArrayList<String> fileList=new ArrayList<>(30);
    public Producer(ArrayList<String> list){
        this.list=list;
    }

    public void run(){
        try (FileReader fileReader = new FileReader("/home/inc4/Downloads/ProducerConsumer/test.txt")) {
            BufferedReader br = new BufferedReader(fileReader);
             fileList=new ArrayList<>();
            String i;
            while((i=br.readLine())!=null){
                fileList.add(i);
            }
        } catch (Exception e) {
            System.out.println("File not found");
        }
        try{
            synchronized (list){
                while(true){
                    if(list.size()>0) {
                        System.out.println("producer is waiting ");
                        list.wait();
                    }else
                    produce(fileList);
                }

            }
        } catch (Exception e) {
            System.out.println( );
        }
    }
    private void produce(ArrayList<String> fileList) throws InterruptedException {

        for(int i=0;i<5;i++){
            Thread.sleep(1000);
            list.add(fileList.get(0));
            fileList.remove(0);
            System.out.println("Element added");
        }
        list.notifyAll();
    }

}
