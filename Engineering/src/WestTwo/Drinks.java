package WestTwo;

import java.time.LocalDate;

public abstract class Drinks
{
    protected String name;
    protected double productionCost;
    protected LocalDate productionDate;
    protected int shelfLife;
    public Drinks(){}
    public Drinks(String name,double cost,LocalDate date,int life)
    {
        this.name=name;
        this.productionCost=cost;
        this.productionDate=date;
        this.shelfLife=life;
    }
    @Override
    public abstract String toString();
    public boolean StaleDrinks(LocalDate temp)
    {
        LocalDate future=this.productionDate.plusDays(shelfLife);//求出产品有效日期截至时间
        return future.compareTo(temp) < 0;//在保质期内
    }
}
