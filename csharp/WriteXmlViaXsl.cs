using System;
using System.IO;
using System.Xml;
using System.Xml.Xsl;

class WriteXmlViaXsl
{
    static void Main(string[] args)
    {
        var xd = new XmlDocument();
        xd.Load(args[0]);
        
        var xt = new XslTransform();
        xt.Load(args[1]);
        
        var sw = new StringWriter();
        var xtw = new XmlTextWriter(sw);
        xtw.Formatting = Formatting.Indented;        
        xt.Transform(xd, null, xtw);
        
        xtw.Flush();        
        Console.Write(sw.ToString());        
    }
}
