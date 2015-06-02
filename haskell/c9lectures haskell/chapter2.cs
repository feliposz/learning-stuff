using System;
using System.Linq;
using System.Collections.Generic;

public class MyClass
{
	public static void RunSnippet()
	{
		var myList = new int[] {1,2,3,4,5};
		var t1 = myList.First();
		var t2 = myList.Skip(1);
		var t3 = myList[2];
		var t4 = myList.Take(3);
		var t5 = myList.Skip(3);
		var t9 = myList.Aggregate((a, b) => a * b);
		var t10 = new int[] {1,2,3}.Union(new int[] {4,5});
		var t11 = myList.Reverse();
		
		WL(t1);
		WL(lstStr(t2));
		WL(t3);
		WL(lstStr(t4));
		WL(lstStr(t5));
		WL(lstStr(myList) + " == " + lstStr(t4.Union(t5)));
		WL(myList.Length);
		WL(myList.Sum());
		WL(t9);
		WL(lstStr(t10));
		WL(lstStr(t11));
		WL(myDouble(5));
		WL(myQuadruple(5));
		
	}
	
	static string lstStr(IEnumerable<int> lst)
	{
		return "[" + String.Join(",", lst.Select(e => e.ToString()).ToArray()) + "]";
	}
	/*
	static T myQuadruple <T> (T x) where T:INumber<T>
	{
		return myDouble(myDouble(x));
	}

	static T myDouble<T> (T x) where T:INumber<T>
	{
		return x + x;	
	}*/
	

	
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
