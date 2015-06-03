using System;
using System.Linq;

class LambdaTest
{
    public delegate double SimpleDelegate(double x);
    public delegate double DualDelegate(double x, double y);

    static void Main()
    {
        //SimpleDelegateTest();
        //DualDelegateTest();
        //ActionsFunctionsTest();
        //AnonymousType();
        Aggregators();
    }

    static void SimpleDelegateTest()
    {
        SimpleDelegate doubleNumber = x => x * 2;
        SimpleDelegate halfNumber = x => x / 2;
        SimpleDelegate crazyNumber = x => {
            if (x > 0)
                return doubleNumber(x);
            else
                return halfNumber(x);
        };

        double number = 3.1415926;

        Console.WriteLine("Number:       {0}", number);
        Console.WriteLine("Double:       {0}", doubleNumber(number));
        Console.WriteLine("Half:         {0}", halfNumber(number));
        Console.WriteLine("Crazy:        {0}", crazyNumber(number));
        Console.WriteLine("Minus Crazy:  {0}", crazyNumber(-number));
    }

    static void DualDelegateTest()
    {
        DualDelegate greater = (double x, double y) => x > y ? x : y;
        DualDelegate lesser  = (double x, double y) => { if (x < y) return x; else return y; };

        double a = 1.5, b = 1.4;

        Console.WriteLine("a:       {0}", a);
        Console.WriteLine("b:       {0}", b);
        Console.WriteLine("greater: {0}", greater(a, b));
        Console.WriteLine("lesser:  {0}", lesser(a, b));
    }

    static void ActionsFunctionsTest()
    {
        Action<string> print = s => Console.WriteLine(s);
        Func<int, int, int> add = (int x, int y) => x + y;
        FuncAndAction(add, print);
    }

    static void FuncAndAction(Func<int, int, int> f, Action<string> a)
    {
        a(f(1, 2).ToString());
    }

    static void AnonymousType()
    {
        var apple = new { name = "Apple", value = 42, tangible = true };
        Console.WriteLine("name = {0}, value = {1}, tangible = {2}", apple.name, apple.value, apple.tangible);
    }

    static void Aggregators()
    {
        int[] numbers = { 1, 3, 5, 7, 9, 11, 13 };

        Console.WriteLine("sum = {0}, avg = {1}, min = {2}, max = {3}", numbers.Sum(), numbers.Average(), numbers.Min(), numbers.Max());
    }

}
