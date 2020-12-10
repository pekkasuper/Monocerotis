package WestTwo;

public class MealNotFoundException extends RuntimeException
{
    MealNotFoundException(String info)
    {
        super(info);
    }
}
