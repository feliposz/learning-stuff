using System;
using System.Windows.Forms;

public class ClipboardTest
{
    // Essecial para que o objeto Clipboard funcione!!!
    [STAThread]
    static void Main(string[] args)
    {
        Console.WriteLine("ContainsAudio       : {0}", Clipboard.ContainsAudio()       );
        Console.WriteLine("ContainsFileDropList: {0}", Clipboard.ContainsFileDropList());
        Console.WriteLine("ContainsImage       : {0}", Clipboard.ContainsImage()       );
        Console.WriteLine("ContainsText        : {0}", Clipboard.ContainsText()        );
        
        Console.WriteLine("Bitmap              : {0}", Clipboard.ContainsData(DataFormats.Bitmap              ));
        Console.WriteLine("CommaSeparatedValue : {0}", Clipboard.ContainsData(DataFormats.CommaSeparatedValue ));
        Console.WriteLine("Dib                 : {0}", Clipboard.ContainsData(DataFormats.Dib                 ));
        Console.WriteLine("Dif                 : {0}", Clipboard.ContainsData(DataFormats.Dif                 ));
        Console.WriteLine("EnhancedMetafile    : {0}", Clipboard.ContainsData(DataFormats.EnhancedMetafile    ));
        Console.WriteLine("FileDrop            : {0}", Clipboard.ContainsData(DataFormats.FileDrop            ));
        Console.WriteLine("Html                : {0}", Clipboard.ContainsData(DataFormats.Html                ));
        Console.WriteLine("Locale              : {0}", Clipboard.ContainsData(DataFormats.Locale              ));
        Console.WriteLine("MetafilePict        : {0}", Clipboard.ContainsData(DataFormats.MetafilePict        ));
        Console.WriteLine("OemText             : {0}", Clipboard.ContainsData(DataFormats.OemText             ));
        Console.WriteLine("Palette             : {0}", Clipboard.ContainsData(DataFormats.Palette             ));
        Console.WriteLine("PenData             : {0}", Clipboard.ContainsData(DataFormats.PenData             ));
        Console.WriteLine("Riff                : {0}", Clipboard.ContainsData(DataFormats.Riff                ));
        Console.WriteLine("Rtf                 : {0}", Clipboard.ContainsData(DataFormats.Rtf                 ));
        Console.WriteLine("Serializable        : {0}", Clipboard.ContainsData(DataFormats.Serializable        ));
        Console.WriteLine("StringFormat        : {0}", Clipboard.ContainsData(DataFormats.StringFormat        ));
        Console.WriteLine("SymbolicLink        : {0}", Clipboard.ContainsData(DataFormats.SymbolicLink        ));
        Console.WriteLine("Text                : {0}", Clipboard.ContainsData(DataFormats.Text                ));
        Console.WriteLine("Tiff                : {0}", Clipboard.ContainsData(DataFormats.Tiff                ));
        Console.WriteLine("UnicodeText         : {0}", Clipboard.ContainsData(DataFormats.UnicodeText         ));
        Console.WriteLine("WaveAudio           : {0}", Clipboard.ContainsData(DataFormats.WaveAudio           ));

        Console.WriteLine("Text                : {0}", Clipboard.ContainsText(TextDataFormat.Text                ));
        Console.WriteLine("UnicodeText         : {0}", Clipboard.ContainsText(TextDataFormat.UnicodeText         ));
        Console.WriteLine("Rtf                 : {0}", Clipboard.ContainsText(TextDataFormat.Rtf                 ));
        Console.WriteLine("Html                : {0}", Clipboard.ContainsText(TextDataFormat.Html                ));
        Console.WriteLine("CommaSeparatedValue : {0}", Clipboard.ContainsText(TextDataFormat.CommaSeparatedValue ));

        // Declares an IDataObject to hold the data returned from the clipboard. 
        // Retrieves the data from the clipboard.
        IDataObject iData = Clipboard.GetDataObject();

        if (iData != null)
        {

            // Determines whether the data is in a format you can use. 
            if(iData.GetDataPresent(DataFormats.Text)) {
               // Yes it is, so display it in a text box.
               Console.WriteLine("Text Content: {0}", (String)iData.GetData(DataFormats.Text)); 
            }
            else {
               // No it is not.
               Console.WriteLine("Could not retrieve data off the clipboard.");
            }
        
        } 
        else
        {
            Console.WriteLine("iData == null");
        }
        
        //Console.ReadLine();
    }
}
