using System;
using System.Net;
using System.IO;

class WebClientTest
{
    static void Main(string[] args)
    {
        if (args.Length != 2) {
            Console.WriteLine("Usage: {0} [url] [file]", "WebClientTest");
            return;
        }
        
        string url = args[0];
        string filename =  args[1];
        
        Console.WriteLine("URL: {0}", url);
        Console.WriteLine("File: {0}", filename);
        
        // for (int i = 0; i < args.Length; i++)
        // {
            // Console.WriteLine("{0}: {1}", i, args[i]);
        // }
        
        string content;
        
        using (var wc = new WebClient()) 
        {
            content = wc.DownloadString(url);
        }
        
        using (var sw = new StreamWriter(filename))
        {
            sw.Write(content);
        }
    }   
}
