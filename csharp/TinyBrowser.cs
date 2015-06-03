using System;
using System.IO;
using System.Text;
using System.Net;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;

namespace TinyBrowser {
   /// <summary>
   /// A Tiny Browser that returns headers also
   /// </summary>
   public class Form1 : System.Windows.Forms.Form {
      private System.Windows.Forms.Label label1;
      private System.Windows.Forms.TextBox textBox2;
      private System.Windows.Forms.Button button1;
      private System.Windows.Forms.Label label2;
      private System.Windows.Forms.Label label3;
      private System.Windows.Forms.TextBox UrlTextBox;
      /// <summary>
      /// Required designer variable.
      /// </summary>
      private System.ComponentModel.Container components = null;

      public Form1() {
         //
         // Required for Windows Form Designer support
         //
         InitializeComponent();

         //
         // TODO: Add any constructor code after InitializeComponent call
         //
      }

      /// <summary>
      /// Clean up any resources being used.
      /// </summary>
      protected override void Dispose( bool disposing ) {
         if( disposing ) {
            if (components != null) {
               components.Dispose();
            }
         }
         base.Dispose( disposing );
      }

		#region Windows Form Designer generated code
      /// <summary>
      /// Required method for Designer support - do not modify
      /// the contents of this method with the code editor.
      /// </summary>
      private void InitializeComponent() {
         this.label1 = new System.Windows.Forms.Label();
         this.textBox2 = new System.Windows.Forms.TextBox();
         this.button1 = new System.Windows.Forms.Button();
         this.label2 = new System.Windows.Forms.Label();
         this.label3 = new System.Windows.Forms.Label();
         this.UrlTextBox = new System.Windows.Forms.TextBox();
         this.SuspendLayout();
         // 
         // label1
         // 
         this.label1.Anchor = (System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right);
         this.label1.AutoSize = true;
         this.label1.Location = new System.Drawing.Point(16, 16);
         this.label1.Name = "label1";
         this.label1.Size = new System.Drawing.Size(30, 13);
         this.label1.TabIndex = 0;
         this.label1.Text = "URL:";
         // 
         // textBox2
         // 
         this.textBox2.Location = new System.Drawing.Point(8, 48);
         this.textBox2.MaxLength = 327670;
         this.textBox2.Multiline = true;
         this.textBox2.Name = "textBox2";
         this.textBox2.ReadOnly = true;
         this.textBox2.ScrollBars = System.Windows.Forms.ScrollBars.Both;
         this.textBox2.Size = new System.Drawing.Size(624, 496);
         this.textBox2.TabIndex = 3;
         this.textBox2.Text = "";
         // 
         // button1
         // 
         this.button1.CausesValidation = false;
         this.button1.Location = new System.Drawing.Point(584, 16);
         this.button1.Name = "button1";
         this.button1.Size = new System.Drawing.Size(48, 24);
         this.button1.TabIndex = 2;
         this.button1.Text = "GO";
         this.button1.Click += new System.EventHandler(this.go);
         // 
         // label2
         // 
         this.label2.Location = new System.Drawing.Point(56, 560);
         this.label2.Name = "label2";
         this.label2.Size = new System.Drawing.Size(512, 24);
         this.label2.TabIndex = 4;
         this.label2.Text = "Waiting.";
         // 
         // label3
         // 
         this.label3.AutoSize = true;
         this.label3.Location = new System.Drawing.Point(16, 560);
         this.label3.Name = "label3";
         this.label3.Size = new System.Drawing.Size(39, 13);
         this.label3.TabIndex = 5;
         this.label3.Text = "Status:";
         // 
         // UrlTextBox
         // 
         this.UrlTextBox.Location = new System.Drawing.Point(48, 16);
         this.UrlTextBox.Name = "UrlTextBox";
         this.UrlTextBox.Size = new System.Drawing.Size(528, 20);
         this.UrlTextBox.TabIndex = 6;
         this.UrlTextBox.Text = "http://fincher.org";
         // 
         // Form1
         // 
         this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
         this.ClientSize = new System.Drawing.Size(664, 589);
         this.Controls.AddRange(new System.Windows.Forms.Control[] {
                                                                      this.UrlTextBox,
                                                                      this.label3,
                                                                      this.label2,
                                                                      this.button1,
                                                                      this.textBox2,
                                                                      this.label1});
         this.Name = "Form1";
         this.Text = "Tiny .Net Browser";
         this.Load += new System.EventHandler(this.Form1_Load);
         this.ResumeLayout(false);

      }
		#endregion

      /// <summary>
      /// The main entry point for the application.
      /// </summary>
      [STAThread]
      static void Main() {
         Application.Run(new Form1());
      }

      private void Form1_Load(object sender, System.EventArgs e) {
       
      }
      private void keyDown(object sender, System.EventArgs e) {
         string url = UrlTextBox.Text;
         label2.Text = "To request: \""+url+"\"...";
      }
      private void go(object sender, System.EventArgs e) {

          string url = UrlTextBox.Text;
         label2.Text = "Requesting \""+url+"\"...";
         string line = "";
         textBox2.Clear();
         try {
            HttpWebRequest httpWebRequest =
               (HttpWebRequest)WebRequest.Create(url);
            HttpWebResponse httpWebResponse = (HttpWebResponse)httpWebRequest.GetResponse();
            System.Net.WebHeaderCollection collection = httpWebResponse.Headers;
            textBox2.AppendText("Headers::\r\n---------\r\n"+collection.ToString()+"HTML returned::\r\n---------------\r\n");
            Stream stream = httpWebResponse.GetResponseStream();
            StreamReader streamReader = 
               new StreamReader(stream, Encoding.ASCII);
           //use ReadToEnd() if you don't need line by line 
           while((line = streamReader.ReadLine()) != null) {
               textBox2.AppendText(line+"\r\n");
            }
         } catch (Exception e2) {
            label2.Text = e2.ToString();
         }
         label2.Text = "Completed loading \""+url+"\".";
         this.Text = ".Net Browser - "+url;
      }
   }
}

    