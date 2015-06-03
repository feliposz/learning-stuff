using System;
using System.IO;

public class FZ_SC_VerifPCLD
{
    public static void Main(string[] args)
    {
        IFormatProvider culture = System.Threading.Thread.CurrentThread.CurrentCulture;
    
        int countLines = 0;
        
        // nome do arquivo
        if (args.Length != 1)
        {
            Console.WriteLine("Uso: FZ_SC_VerifPCLD nome_do_arquivo.csv");
            return;
        }
        string path = args[0];
        
        // abre o arquivo csv
        using(StreamReader sr = new StreamReader(path))
        {
            string line;
            decimal total = 0;
            DateTime minVen = DateTime.Today, maxVen = DateTime.Today;
            
            while ((line = sr.ReadLine()) != null) {
                
                // divide a linha por ;
                countLines++;
                string[] cells = line.Split(';');
                
                // faz as conversões conforme o conteúdo das células
                DateTime ven, dt;
                decimal val;

                string cpf = cells[0];
                string doc = cells[1];
                string sp1 = cells[4];
                string sp2 = cells[6];
                string sp3 = cells[7];
                string sp4 = cells[8];
                string sp5 = cells[9];
                
                if (decimal.TryParse(cells[3], out val) == false)
                    val = 0;
                if (DateTime.TryParse(cells[2], culture, System.Globalization.DateTimeStyles.AssumeLocal, out ven) == false)
                    ven = DateTime.Today;
                if (DateTime.TryParse(cells[5], culture, System.Globalization.DateTimeStyles.AssumeLocal, out dt) == false)
                    ven = DateTime.Today;

                // inicializa data mínima e máxima
                if (countLines == 1)
                {
                    minVen = maxVen = ven;
                }
                
                // calcula os totais e data mín/máx
                if (cpf != "")
                {
                    if (ven < minVen)
                        minVen = ven;
                    if (ven > maxVen)
                        maxVen = ven;
                    total += val;
                }
                else
                {
                    // exibe informações extras
                    if (sp3 != "") Console.WriteLine("sp3: {0}", sp3);
                    if (sp4 != "") Console.WriteLine("sp4: {0}", sp4);
                    if (sp5 != "") Console.WriteLine("sp5: {0}", sp5);
                }
            }
            
            Console.WriteLine("Min Vencimento: {0}", minVen);
            Console.WriteLine("Max Vencimento: {0}", maxVen);
            Console.WriteLine("Total: {0}", total);
        }
        
        
    }
}
