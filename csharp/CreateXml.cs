using System;
using System.IO;
using System.Xml;

class CreateXml
{

    static void Main(string[] args)
    {
        // <?xml version="1.0" standalone="yes"?>
        var xd = new XmlDocument();
        xd.PrependChild(xd.CreateXmlDeclaration("1.0", "", "yes"));
        
        // <Book author="Xenophone">The Persian Expedition</Book>
        var e = xd.CreateElement("Book");
        e.SetAttribute("author", "Xenophone");
        e.AppendChild(xd.CreateTextNode("The Persian Expedition"));
        xd.AppendChild(e);
        
        var sw = new StringWriter();
        var xtw = new XmlTextWriter(sw);
        xtw.Formatting = Formatting.Indented;
        
        xd.WriteTo(xtw);
        xtw.Flush();
        Console.Write(sw.ToString());
        
        string filename = System.Environment.GetEnvironmentVariable("TEMP") + "\\Book.xml";
        xd.Save(filename);
    }

}
