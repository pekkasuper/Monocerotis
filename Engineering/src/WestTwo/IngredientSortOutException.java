package WestTwo;

public class IngredientSortOutException extends RuntimeException
{
    private Drinks Which;
    IngredientSortOutException(String info,Drinks NotEnough)
    {
        super(info);
        this.Which=NotEnough;
    }
    Drinks getWhich(){return this.Which;}
}
