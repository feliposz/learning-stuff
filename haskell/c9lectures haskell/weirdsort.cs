using System;
using System.Linq;
using System.Collections.Generic;


public class MyClass
{
	public static void RunSnippet()
	{
		var v = new int[] {10, 2, 23, 223, 43, 2, 23, 11, 9, -1, 0, 12};
		
		var u = WeirdSort(v);
		
		for (int i = 0; i < u.Length; i++)
			WL(u[i]);
	}

/*
f [] = []
f (x:xs) = f ys ++ [x] ++ f zs
    where
      ys = [a | a <- xs, a <= x]
      zs = [b | b <- xs, b > x]
*/
	
	public static int[] WeirdSort(int[] xl)
	{
		if (xl.Length == 0)
			return xl;
		
		var x = xl.First();
		var xs = xl.Skip(1).ToArray();
		var ys = xs.Where(a => a <= x).ToArray();
		var zs = xs.Where(b => b > x).ToArray();
		
		return WeirdSort(ys).Union(new int[] {x}).Union(WeirdSort(zs)).ToArray();
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