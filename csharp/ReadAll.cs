using System;

namespace Something
{
    class ReadAll
    {
        readonly static string path = @"c:\windows\version";
        
        public static void Main(string[] args)
        {            
            string content = System.IO.File.ReadAllText(path);
            Console.WriteLine("content = " + content);
        }
    }
}