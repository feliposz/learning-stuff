using System;
using System.Text;

class ReflectionTest
{
    int abc = 123;
    string def = "test";
    
    public int Abc
    {
        get { return abc; }
        set { abc = value; }
    }
    
    public string Def
    {
        get { return def; }
        set { def = value; }
    }

    static void Main(string[] args)
    {
        var rt = new ReflectionTest();
        Console.WriteLine(Detail(rt));
        Console.WriteLine(rt.DetailMe());
        
        if (args.Length == 1)
        {
            string className = args[0];
            var myObj = Activator.CreateInstance(Type.GetType(className), new object[]{});
        }        
    }
    
    static string Detail(object obj)
    {
        var sb = new StringBuilder();
        System.Type type = obj.GetType();
        sb.Append("\r\nInformation for: " + type.Name);
        sb.Append("\r\nFields:");
        foreach (var f in type.GetFields())
        {
            sb.Append("\r\n  " + f.ToString() + " = \"" + f.GetValue(obj) + "\"");
        }
        sb.Append("\r\nProperties:");
        foreach (var p in type.GetProperties())
        {
            sb.Append("\r\n  " + p.ToString() + " = \"" + p.GetValue(obj, null) + "\"");
        }
        return sb.ToString();
    }
    
    string DetailMe()
    {
        var sb = new StringBuilder();
        System.Type type = this.GetType();
        sb.Append("\r\nInformation for: " + type.Name);
        sb.Append("\r\nFields:");
        foreach (var f in type.GetFields())
        {
            sb.Append("\r\n  " + f.ToString() + " = \"" + f.GetValue(this) + "\"");
        }
        sb.Append("\r\nProperties:");
        foreach (var p in type.GetProperties())
        {
            sb.Append("\r\n  " + p.ToString() + " = \"" + p.GetValue(this, null) + "\"");
        }
        return sb.ToString();
    }    
}
