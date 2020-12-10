package WestTwo;

import java.time.LocalDate;

public interface FriedChickenRestaurant
{
    String SoldMeal(SetMeal meal, LocalDate today);
    boolean BoughtGoods(Drinks which,int number);
}
