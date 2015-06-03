using System;

public class WriteAll
{
    public static void Main(string[] args)
    {
        string content = "Line1" + Environment.NewLine + 
                         "Line2" + Environment.NewLine;
        System.IO.File.WriteAllText(@"WriteAll.txt", content);
    }
}
