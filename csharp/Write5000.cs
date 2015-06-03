using System;
using System.IO;

using MyFileWriter = System.IO.StreamWriter;

public class Write5000
{
    public static void Main(string[] args)
    {
        for (int i = 0; i < 5000; i++)
        {
            using (MyFileWriter sw = new MyFileWriter(@"Write5000.txt"))
            {
                string msg = DateTime.Now + ", " + i;
                sw.WriteLine(msg);
                Console.WriteLine(msg);
            }
        }
    }
}
