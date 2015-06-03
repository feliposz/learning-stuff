using System;
using Oracle.DataAccess.Client;
using Oracle.DataAccess.Types;

class SimpleOracleTest
{
    static void Main()
    {
        string connectionString = "Data Source=xe;User Id=hr;Password=hr;Pooling=True;Min Pool Size=10;Connection Lifetime=120;Max Pool Size=30";
        //var reader = new OracleDataReader();
        using (var conn = new OracleConnection(connectionString))
        {
            using (var comm = conn.CreateCommand())
            {
                conn.Open();
                comm.CommandText = "SELECT SYSDATE FROM DUAL";
                var reader = comm.ExecuteReader();
                if (reader != null)
                {
                    if (reader.Read())
                    {
                        Console.WriteLine(reader.GetValue(0));
                    }
                }            
            }
        }
    }
}
