using System;

class Birthday
{
    static void Main(string[] args)
    {
        int day, month, year;
        string strDay, strMonth, strYear;
        bool valid;
        
        Console.WriteLine("Please, enter your birthday below.");
        
        do {
            Console.Write("Day: ");
            strDay = Console.ReadLine();
            valid = int.TryParse(strDay, out day) && day >= 1 && day <= 31;
            if (!valid)
                Console.WriteLine("Invalid day");
        } while (!valid);
        
        do {
            Console.Write("Month: ");
            strMonth = Console.ReadLine();
            valid = int.TryParse(strMonth, out month) && month >= 1 && month <= 12;
            if (!valid)
                Console.WriteLine("Invalid month");
        } while (!valid);

        do {
            Console.Write("Year: ");
            strYear = Console.ReadLine();        
            valid = int.TryParse(strYear, out year) && year >= 1;
            if (!valid)
                Console.WriteLine("Invalid year");
        } while (!valid);
        
        Console.WriteLine("{0}/{1}/{2}", day, month, year);
        
        DateTime today = DateTime.Today;
        int years = today.Year - year;
        int months = today.Month - month;
        int days = today.Day - day;
        
        if (days < 0) {
            months --;
            days += 30; // ?
        }

        if (months < 0) {
            years--;
            months += 12;
        }
        
        Console.WriteLine("You have lived: {2} Years {1} Months {0} Days", days, months, years);
        
        DateTime birth = new DateTime(year, month, day); 
        TimeSpan ts = today - birth;
        
        Console.WriteLine("A total of: {0} Days ", ts.TotalDays);
    }
}
