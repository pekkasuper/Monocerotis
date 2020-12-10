package WestTwo;

import java.time.LocalDate;
import java.util.*;

public class West2FriedChickenRestaurant implements FriedChickenRestaurant
{
    public double MoneyLeft;
    private static final Map<String,Integer>BeerStore;
    private static final Map<String,Integer>JuiceStore;
    private static final Map<Integer,SetMeal> MealStore;
    West2FriedChickenRestaurant(int money)
    {
        this.MoneyLeft=money;
    }
    static
    {
        //哈希散列表查找速度较快也可以储存大量数据
        //只要能通过饮品的名称快速查找数量就可以满足条件
        System.out.println("初始化菜单成功");
        MealStore=new HashMap<>();
        BeerStore=new HashMap<>();
        JuiceStore=new HashMap<>();
        BeerStore.put("雪津",50);
        JuiceStore.put("哇哈哈哈",1);
        MealStore.put(1,new SetMeal("勇闯天下套餐",20,"麻辣大鸡排",new Beer("雪津",5,LocalDate.now(),30,5)));
        MealStore.put(2,new SetMeal("温馨童年套餐",15,"中号鸡排",new Juice("哇哈哈哈",5,LocalDate.now(),30)));
        //MealStore.add();
    }
    private void use(Beer beer, LocalDate today)throws IngredientSortOutException
    {
        if(beer.StaleDrinks(today))throw new IngredientSortOutException(beer.toString(),beer);//过期啤酒喝不得
        int beerNumber=BeerStore.get(beer.getName());//在啤酒列表中查找对应的啤酒
        if(beerNumber==0)throw new IngredientSortOutException(beer.toString(),beer);//数量不足抛出异常
        else BeerStore.put(beer.getName(),beerNumber-1);
    }
    private void use(Juice juice,LocalDate today)throws IngredientSortOutException
    {
        if(juice.StaleDrinks(today))throw new IngredientSortOutException(juice.toString(),juice);//过期饮料使不得
        try
        {
            int juiceNumber=JuiceStore.get(juice.getName());
            if(juiceNumber==0)throw new IngredientSortOutException(juice.toString(),juice);//数量不足抛出异常
            else JuiceStore.put(juice.getName(),juiceNumber-1);
        }catch(NullPointerException e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        //在果汁列表中查找对应的果汁
    }
    @Override
    public String SoldMeal(SetMeal meal,LocalDate today)throws IngredientSortOutException
    {
        //这里假定用户购买套餐时不会出现购买根本不存在的套餐
        try{
            if(meal.drink instanceof Beer)
            {
                Beer temp=(Beer)meal.drink;
                use(temp,today);
            }
            else
            {
                Juice temp=(Juice)meal.drink;
                use(temp,today);
            }
            this.MoneyLeft+=meal.mealPrice;
            return "购买成功"+"请享用您的"+meal.Enjoy();
        }catch(IngredientSortOutException e) {//某个东西的件数不够
            throw e;//在测试.java文件中调用时如果捕获了该异常需要立即打印信息
                //return e.getMessage()+"不足，购买失败请见谅";
        }
    }
    @Override
    public boolean BoughtGoods(Drinks which,int number)throws OverdraftBalanceException
    {
        if(this.MoneyLeft>=which.productionCost*number)
        {
            this.MoneyLeft-=which.productionCost*number;//余额充足购入原料
            if(which instanceof Beer)
            {
                Beer temp=(Beer)which;
                BeerStore.put(temp.getName(),number);//出现不足时候由于购买时仅能是单份购买不足时说明应是过期或者数量为0
            }
            else
            {
                Juice temp=(Juice)which;
                JuiceStore.put(temp.getName(),number);
            }
            return true;
        }
        else throw new OverdraftBalanceException("",which.productionCost*number-this.MoneyLeft);
    }
    public static void main(String[] args)
    {
        Scanner rec=new Scanner(System.in);
        LocalDate today=LocalDate.now();
        Integer counter=1;
        West2FriedChickenRestaurant non=new West2FriedChickenRestaurant(0);
        while(true)
        {
            if(!MealStore.containsKey(counter))break;
            else
            {
                System.out.println(counter.toString()+"."+MealStore.get(counter));
            }
            counter++;
        }
        while(true)
        {
            System.out.println("您好，想要选择什么套餐吗?输入非0合法范围内数字来购买套餐否则退出");
            int choice=rec.nextInt();
            if(choice<=0||choice>=counter)
            {
                System.out.println("欢迎下次光临！");
                break;
            }
            else
            {
                try{
                    String success=non.SoldMeal(MealStore.get(choice),today);
                    System.out.println(success);
                }catch (IngredientSortOutException e) {
                    System.out.println(e.getMessage()+"不足，购买失败请见谅");
                    System.out.println("输入合法正整数以立即进货否则暂时不进货");
                    int drinksNumber=rec.nextInt();
                    if(drinksNumber>0)
                    {
                        try {
                            non.BoughtGoods(e.getWhich(), drinksNumber);
                        } catch (OverdraftBalanceException e1) {
                            System.out.println("你的余额不足还差" + e1.getPriceMissing() + "进货失败");
                        }
                    }
                }
            }
        }
    }
}
