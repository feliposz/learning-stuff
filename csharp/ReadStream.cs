using System;
using System.IO;

public class ReadStream
{
    public static void Main(string[] args)
    {
        int countLines = 0;
        int countChars = 0;
        string path = args[0];
        using(StreamReader sr = new StreamReader(path))
        {
            string line;
            while ((line = sr.ReadLine()) != null) {
                Console.WriteLine(line);
                countChars += line.Length;
                countLines++;
            }
        }
        Console.WriteLine("Lines = " + countLines);
        Console.WriteLine("Chars = " + countChars);
    }
}
