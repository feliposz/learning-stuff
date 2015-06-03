using System;

public class FormatOutput
{
    public static void Main(string[] args)
    {
        int i = 16;
        double f = 1234.5678;
        string msg = "Text";
        Console.WriteLine("'{0,8}'", i);
        Console.WriteLine("'{0,-8}'", i);
        Console.WriteLine("'{0,8}'", msg);
        Console.WriteLine("'{0,-8}'", msg);
        Console.WriteLine("'{0:X}'", i);
        Console.WriteLine("'{0:X10}'", i);
        Console.WriteLine(1234567.ToString("#,##0"));
        Console.WriteLine(String.Format("String.Format => {0:##.#}", f));
        Console.WriteLine(f.ToString("#,###.##"));
        //Console.WriteLine(String.Format("{0,8:F3)", f));
        Console.WriteLine(DateTime.Now.ToString("dd/MM/yyyy HH:mm:ss"));
        Console.WriteLine(DateTime.Now.ToString("dd/MMM/yyyy hh:mm:ss d"));
    }
}
