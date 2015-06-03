using System;

namespace SomethingElse
{
    public class ReadToArray
    {
        public static void Main(string[] args)
        {
            string path = @"ReadToArray.cs";
            string[] lines = System.IO.File.ReadAllLines(path);
            Console.WriteLine("Total lines = " + lines.Length);
            int i = 1;
            foreach (string line in lines)
            {
                Console.WriteLine("{0,2}: {1}", i, line);
                i++;
            }
            Console.ReadLine();
        }
    }
}