package WestTwo;

import java.time.LocalDate;

public class Beer extends Drinks
{
    private final float proof;

    Beer(String name, double cost, LocalDate date,int life,float proof)
    {
        super(name,cost,date,life);
        this.proof=proof;
    }

    public Beer(Beer temp) {
        super(temp.name,temp.productionCost,temp.productionDate,temp.shelfLife);
        this.proof=temp.proof;
    }
    public String getName()
    {
        return this.name;
    }
    @Override
    public String toString()
    {
        return this.proof+"度的"+this.name+"啤酒";
    }
}
