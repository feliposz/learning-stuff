using System;
using System.IO;
using System.Xml;
using System.Xml.Xsl;

class WriteXmlTransform
{
    static void Main(string[] args)
    {
        
        var xct = new XslCompiledTransform();
        xct.Load(args[1]);
        
        var sw = new StringWriter();
        xct.Transform(args[0], null, sw);
        
        sw.Close();     
        Console.Write(sw.ToString());        
    }
}
