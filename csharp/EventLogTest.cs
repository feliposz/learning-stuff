using System;
using System.Diagnostics;
using System.Security;

class EventLogTest
{
    static void Main()
    {
        try 
        {
            var ev = new EventLog();
            ev.Source = "EventLogTest";
            ev.WriteEntry("Testando o objeto do EventLog", EventLogEntryType.Error);
            ev.Close();
        }
        catch (SecurityException)
        {
            Console.WriteLine("Não foi possível registrar o evento");
        }
    }
}
