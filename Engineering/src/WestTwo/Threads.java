package WestTwo;

import java.util.Scanner;

public class Threads
{
    public static void main(String[] args)throws Exception
    {
        //ExecutorService executorService= Executors.newFixedThreadPool(10000);
        //CompletionService<String> service=new ExecutorCompletionService<>(executorService);
        Scanner scanner = new Scanner(System.in);
        Run[] threadStore=new Run[10];
        int x=scanner.nextInt();
        long startTemp=1,endTemp=100000000;
        long increase=100000000;
        for(int i=0;i<10;i++)
        {
            threadStore[i]=new Run(x,startTemp,endTemp);
            startTemp+=increase;
            endTemp+=increase;
        }
        for(int i=0;i<10;i++)
        {
            threadStore[i].start();
            threadStore[i].join();
        }
        System.out.println(Run.ans);
    }
}
