using System;
using System.IO;
using System.Xml;

class WriteXmlToString
{

    static void Main(string[] args)
    {
        var xd = new XmlDocument();
        xd.Load(args[0]);
        var sw = new StringWriter();
        var xtw = new XmlTextWriter(sw);
        xtw.Formatting = Formatting.Indented;
        xd.WriteTo(xtw);
        xtw.Flush();
        Console.Write(sw.ToString());
    }

}
