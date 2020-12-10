package WestTwo;

import java.time.LocalDate;

public class Juice extends Drinks
{
    Juice(String name, double cost, LocalDate date,int life)
    {
        super(name,cost,date,life);
    }

    public Juice(Juice temp) {
        super(temp.name,temp.productionCost,temp.productionDate,temp.shelfLife);
    }
    public String getName()
    {
        return this.name;
    }
    @Override
    public String toString()
    {
        return this.name+"牌果汁";
    }
}
