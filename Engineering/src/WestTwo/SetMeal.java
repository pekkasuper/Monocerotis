package WestTwo;

public class SetMeal
{
    public String mealName;
    public double mealPrice;
    public String fryChickenName;
    public Drinks drink;
    SetMeal(String mealName,double mealPrice,String fryChickenName,Drinks drink)
    {
        this.mealName=mealName;
        this.mealPrice=mealPrice;
        this.fryChickenName=fryChickenName;
        if(drink instanceof Juice)
        {
            Juice temp=(Juice)drink;
            this.drink=new Juice(temp);
        }
        else
        {
            Beer temp=(Beer)drink;
            this.drink=new Beer(temp);
        }
    }
    @Override
    public String toString()
    {
        return this.mealName+"套餐的价格是"+this.mealPrice+"包含了" +this.fryChickenName+"和"+this.drink;
    }
    public String Enjoy(){return this.mealName;}
}
