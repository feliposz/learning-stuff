// cd D:\OneDrive\Desenvolvimento\Projects\Haskell\c9lectures haskell
// d:
// C:\Windows\Microsoft.NET\Framework\v4.0.30319\csc.exe chapter3.cs
// chapter3.exe


using System;
using System.Linq;
using System.Collections.Generic;

public class MyClass
{
	public static void RunSnippet()
	{
		int x = 10;
		int[] xs1 = new [] { 1, 2, 3, 4, 5 };
		int[][] xss2 = new [][] { new [] {1, 2, 3}, new [] {4, 5} };
		
		/*
		var t1 = Tuple.Create(1, false, 'x');
		WL(t1);
		WL(t1.Item1);
		WL(t1.Item2);
		WL(t1.Item3);
		*/
		
		Func<int, int, int> add = (a, b) => a + b;	
		
		WL(add(1, 2));
		
	}
	
	#region Helper methods
	
	public static void Main()
	{
		try
		{
			RunSnippet();
		}
		catch (Exception e)
		{
			string error = string.Format("---\nThe following error occurred while executing the snippet:\n{0}\n---", e.ToString());
			Console.WriteLine(error);
		}
		finally
		{
			Console.Write("Press any key to continue...");
			Console.ReadKey();
		}
	}

	private static void WL(object text, params object[] args)
	{
		Console.WriteLine(text.ToString(), args);	
	}
	
	private static void RL()
	{
		Console.ReadLine();	
	}
	
	private static void Break() 
	{
		System.Diagnostics.Debugger.Break();
	}

	#endregion
}
