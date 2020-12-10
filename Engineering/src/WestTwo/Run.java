package WestTwo;

public class Run extends Thread
{
    private Thread thread;
    public long startTime;
    public long endTime;
    public static long ans=0;
    public static int which;
    Run(int which,long startTime,long endTime) {
        this.which=which;
        this.startTime=startTime;
        this.endTime=endTime;
    }
    public void start()
    {
        if(this.thread==null)
        {
            this.thread=new Thread(this);
            thread.start();
        }
    }
    @Override
    public void run()
    {
        for(long i=startTime;i<=endTime;i++)
            if(String.valueOf(i).contains(String.valueOf(which)))ans+=i;
    }
}
