using System;

class ConstructorTest 
{
    static int i = 0;

    static ConstructorTest()
    {
        Console.WriteLine("static constructor i = {0}", i);
    }
    
    public ConstructorTest()
    {
        i++;
        Console.WriteLine("public constructor i = {0}", i);
    }
    
    ~ConstructorTest()
    {
        i--;
        Console.WriteLine("private destructor i = {0}", i);
    }
    
    static void Main()
    {
        Console.WriteLine("Main()");
        new ConstructorTest();
        new ConstructorTest();
        new ConstructorTest();
    }
}

