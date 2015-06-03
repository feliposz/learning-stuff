using System;
using System.IO;

public class WriteStream
{
    public static void Main(string[] args)
    {
        FileStream fs = null;
        StreamWriter sw = null;
        try
        {
            fs = new FileStream(@"WriteStream.txt", 
                FileMode.OpenOrCreate, FileAccess.Write);
            sw = new StreamWriter(fs);
            sw.WriteLine("Howdy World\n");
        }
        finally
        {
            if (sw != null)
            {
                sw.Close();
            }
        }
    }
}
