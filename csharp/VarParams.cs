using System;
using System.Text;

class VarParams
{
    enum ListType { UnorderedList, OrderedList };

    static string List(ListType type, params string[] elements)
    {
        var sb = new StringBuilder();

        if (type == ListType.UnorderedList)
            sb.Append("<ul>");
        else
            sb.Append("<ol>");

        for (int i = 0; i < elements.Length; i++)
        {
            sb.Append("<li>" + elements[i] + "</li>");
        }

        if (type == ListType.UnorderedList)
            sb.Append("</ul>");
        else
            sb.Append("</ol>");

        return sb.ToString();
    }

    static void Main()
    {
        Console.WriteLine(List(ListType.UnorderedList, "apple", "banana"));
        Console.WriteLine(List(ListType.OrderedList, "car", "die", "eagle", "forest"));
    }
}
