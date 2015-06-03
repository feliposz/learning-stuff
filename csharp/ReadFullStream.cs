using System;
using System.IO;

public class ReadFullStream
{
    public static void Main(string[] args)
    {
        Console.WriteLine(getTextFromFile(@"ReadFullStream.cs"));
    }
    
    static string getTextFromFile(string fileName)
    {
        FileStream fs = null;
        StreamReader sr = null;
        string content = null;
        try
        { 
            fs = new FileStream(fileName, FileMode.Open, FileAccess.Read);
            sr = new StreamReader(fs);
            content = sr.ReadToEnd();
        }
        finally
        {
            if (sr != null)
            {
                sr.Close();
            }
        }
        return content;
    }
}
