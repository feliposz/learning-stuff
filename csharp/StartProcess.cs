using System;
using System.Diagnostics;
using System.Threading;

class StartProcess
{
    static void Main()
    {
        var process = Process.Start("Notepad.exe");
        //Thread.Sleep(1000);
        //process.
        Thread.Sleep(5000);
        try
        {
            process.Kill();
        }
        catch {}
    }
}
