package WestTwo;

public class OverdraftBalanceException extends RuntimeException
{
    private double priceMissing;
    OverdraftBalanceException(String info,double priceMissing)
    {
        super(info);
        this.priceMissing=priceMissing;
    }
    double getPriceMissing(){return this.priceMissing;}
}
