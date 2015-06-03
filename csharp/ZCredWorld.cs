using System;
using System.Threading;
using System.Diagnostics;
using System.Collections.Generic;

namespace ZCred
{
    public class ZCredWorld
    {
   
        static void Main(string[] args)
        {
            DateTime begin = DateTime.UtcNow;
            var stopwatch = Stopwatch.StartNew();
            
            Console.WriteLine("ZCredWorld spawned into existance at {0}", DateTime.Now.ToString());
            
            var ned = new Client("Ned Stark");
            Card c1 = new Card();
            Card c2 = new GoldCard();
            
            WaitABit();

            ned.AddCard(c1);
            ned.AddCard(c2);
            ned.Show();

            WaitABit();
            
            if (c1 < c2) {
                Console.WriteLine("c2 is better than c1.");
            }

            if (c1 > c2) {
                Console.WriteLine("c1 is better than c2.");
            }
            
            WaitABit();
            
            Console.WriteLine("ned = " + ned);
            Console.WriteLine("c1 = " + c1);
            Console.WriteLine("c2 = " + c2);
            
            WaitABit();

            ned.Name = "Eddard Stark";
            
            WaitABit();
            
            Console.WriteLine("ZCredWorld ended it's short existance of {0} seconds", DateTime.UtcNow - begin);
            stopwatch.Stop();
            long millisecs = stopwatch.ElapsedMilliseconds;
            Console.WriteLine("Or... {0} milliseconds", millisecs);
        }

        static void WaitABit()
        {
            //Thread.Sleep(1000);
            Thread.Sleep(TimeSpan.FromSeconds(1));
        }
        
    }

    class Client
    {
        string name;
        private List<Card> cards = new List<Card>();

        public string Name {
            get { return name; }
            
            set
            {
                Console.WriteLine("Client's name changed from {0} to {1}", name, value);
                name = value;
            }
        }
        
        public Client(string name)
        {
            this.name = name;
        }

        public void Show()
        {
            Console.WriteLine("Client: {0}", name);
            Console.WriteLine("Cards: {0}", cards.Count);
            for (int i = 0; i < cards.Count; i++) {
                cards[i].Show();
            }
        }

        public void AddCard(Card crd)
        {
            cards.Add(crd);
        }
        
        public override string ToString()
        {
            return base.ToString() + " => name = " + name + ", cards = " + cards.Count;
        }
    }

    class Card
    {
        static long cardNumberSeq = 8888000000000000;

        protected long cardNumber;

        public Card()
        {
            cardNumber = ++cardNumberSeq;
        }

        public virtual void Show()
        {
            Console.WriteLine("Card Number: {0}", cardNumber);
        }

        public static bool operator>(Card c1, Card c2)
        {
            return c1 is GoldCard && c2 is Card;
        }

        public static bool operator<(Card c1, Card c2)
        {
            return c1 is Card && c2 is GoldCard;
        }
        
        public override string ToString()
        {
            return base.ToString() + " => number = " + cardNumber;
        }
        
    }

    class GoldCard : Card
    {
        public override void Show()
        {
            Console.WriteLine("Gold Card Number: {0}", cardNumber);
        }
    }
}