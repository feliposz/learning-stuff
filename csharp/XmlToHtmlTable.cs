using System;
using System.IO;
using System.Xml;
using System.Linq;
using System.Text;
using System.Xml.Linq;

class XmlToHtmlTable
{
    static void Main(string[] args)
    {
        string xml = 
            "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
            "<!-- Edited by XMLSpy® -->" +
            "<breakfast_menu>" +
            "  <food" +
            "    name=\"Belgian Waffles\"" +
            "    price=\"$5.95\"" +
            "    description=\"two of our famous Belgian Waffles with plenty of real maple syrup\"" +
            "    calories=\"650\" ></food>" +
            "  <food" +
            "    name=\"Strawberry Belgian Waffles\"" +
            "    price=\"$7.95\"" +
            "    description=\"light Belgian waffles covered with strawberries and whipped cream\"" +
            "    calories=\"900\" ></food>" +
            "  <food" +
            "    name=\"Berry-Berry Belgian Waffles\"" +
            "    price=\"$8.95\"" +
            "    description=\"light Belgian waffles covered with an assortment of fresh berries and whipped cream\"" +
            "    calories=\"900\" ></food>" +
            "  <food" +
            "    name=\"French Toast\"" +
            "    price=\"$4.50\"" +
            "    description=\"thick slices made from our homemade sourdough bread\"" +
            "    calories=\"600\" ></food>" +
            "  <food" +
            "    name=\"Homestyle Breakfast\"" +
            "    price=\"$6.95\"" +
            "    description=\"two eggs, bacon or sausage, toast, and our ever-popular hash browns\"" +
            "    calories=\"950\" ></food>" +
            "</breakfast_menu>";
        
        Console.Write(ConvertXmlToHtmlTable(xml));
    }

    static string ConvertXmlToHtmlTable(string xml)
    {
        //var xd = XDocument.Parse("<outer>" + xml + "</outer>");
        var xd = XDocument.Parse(xml);
        var root = xd.Root;
        if (root == null || root.Elements().Count() == 0)
            return "Empty";
        
        var attributes = root.Descendants().First().Attributes();

        var html = new StringBuilder("<table>");
        
        html.Append("<tr>");
        foreach (var attribute in attributes)
        {
            html.Append("<th>" + attribute.Name + "</th>");
        }
        html.Append("</tr>");

        foreach (var element in root.Descendants())
        {
            html.Append("<tr>");
            foreach (var attribute in element.Attributes())
            {
                html.Append("<td>" + attribute.Value + "</td>");
            }
            html.Append("</tr>");
        }
        
        html.Append("</table>");
    
        return html.ToString();
    }

}
