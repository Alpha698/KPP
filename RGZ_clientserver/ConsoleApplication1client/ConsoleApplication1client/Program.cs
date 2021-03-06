﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Sockets;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using System.IO;
using System.Threading;

namespace ConsoleApplication1client
{
    class Program
    {

        class Player
        {
            public int X { get; set; }
            public int Y { get; set; }
            public char Sprite { get; set; }
            public ConsoleColor Color { get; private set; }
            public int ID { get; private set;}

            public Player(int x, int y, char sprite, ConsoleColor color, int id)
            {
                X = 1;
                Y = 0;
                Sprite = sprite;
                Color = color;
                ID = id;
            } //параметры игрока

            /// <summary>
            /// //////////////////////////////////////////////////
            /// </summary>
            //class Field
            //{
            //    int width; //ширина поля
            //    int height; //высота поля
            //    static bool[,] tetrisField; //игровое поле

            //    public Field(int w, int h)
            //    {
            //        this.width = w;
            //        this.height = h;
            //        tetrisField = new bool[height, width];
            //        //level = 0;
            //        //scores = 0;
            //    }

            //    public void DrawField()
            //    {

            //        for (int i = 0; i < height - 1; i++)
            //        {
            //            for (int j = 1; j < width - 1; j++)
            //            {
            //                Console.CursorLeft = j;
            //                Console.CursorTop = i;
            //                if (tetrisField[i, j] == false) Console.WriteLine(" ");
            //                else Console.WriteLine("0");
            //            }
            //            Console.WriteLine();
            //        }

            //        //Console.WriteLine("\n   Level " + this.level);
            //       // Console.WriteLine("\n  Scores " + this.scores);
            //    }
            //}

            /// <summary>
            /// /////////////////////////////////////////////////
            /// </summary>


            public void Draw()
            {
                Console.ForegroundColor = Color;
                Console.SetCursorPosition(X, Y);
                Console.Write(Sprite);
            }//отрисовка игрока

            public void Remove()
            {
                Console.SetCursorPosition(X, Y);
                Console.Write(" ");

            }//стирание игрока

        }

        //static Player player = new Player(1 ,1, '@', ConsoleColor.Yellow,0);
        static Socket socket = new Socket(SocketType.Stream, ProtocolType.Tcp);
        static MemoryStream ms = new MemoryStream(new byte[256], 0, 256, true, true);
        static BinaryWriter writer = new BinaryWriter(ms);
        static BinaryReader reader = new BinaryReader(ms);

        static List<Player> players = new List<Player>();

        static Player player;
        static Random random = new Random();

        enum PacketInfo
        {
            ID, Position
        }

        static void Main(string[] args)
        {
            Console.Title = "Client";
            Console.CursorVisible = false;

            //Console.WriteLine("███████████████████████████████████████████████████████████████████████████████");
            //Console.WriteLine("█████████████████████░░░░▀█░░░░███████████████████╔═══════════╗████████████████");
            //Console.WriteLine("██████████████████████░░░░▀█░░░░████████████████╔═╝███████████╚═╗██████████████");
            //Console.WriteLine("███████████████████████░░░░░░░░░▄███▀▀░░░██████╔╝███████████████╚╗█████████████");
            //Console.WriteLine("████████████████████████░░░▄▄▄█▀▀░░░░▄▄▄███████║█████████████████║█████████████");
            //Console.WriteLine("██████████████████████████▀▀▀░░░░▄▄████████████║█████████████████║█████████████");
            //Console.WriteLine("██████████████████████▀▀░░░░▄▄████▀▀███████████║█████████████████║█████████████");
            //Console.WriteLine("███████████████████▀░░░▄▄▄█▀▀░░▀▀█▄▄▄██████████║█╔█████████████╗█║█████████████");
            //Console.WriteLine("███████████████████▄▄█████▀░░▄▄░▄▄█▀▀░▀████████╚╦╝███▒▒███▒▒███╚╦╝█████████████");
            //Console.WriteLine("██████████████████████████░░░██▀█▀░░▄▄█████████╔╝██▒▒▒▒███▒▒▒▒██╚╗█████████████");
            //Console.WriteLine("████████████████████████▀█░░░▀█░▀██▀▀░▄████████║██▒▒▄▒▒███▒▒▄▒▒██║█████████████");
            //Console.WriteLine("███████████████▀▀▀▀░░░░░░▀█░░░▀█░█▄▄██▀▀███████║██▒▒▒▒█████▒▒▒▒██║█████████████");
            //Console.WriteLine("████████████░░░░░░░░░░░░░░▀█░░░▀█░▀█░▄▄████████╚╗███████████████╔╝█████████████");
            //Console.WriteLine("████████████░░░░░░░░░░░░░░░█▄░░░██▄█▀▀▀█████████╚══╦╝██▒█▒██╚╦══╝██████████████");
            //Console.WriteLine("████████████░░░░░░░░░░░░░░░░███████▄░░░████████████║█████████║█████████████████");
            //Console.WriteLine("████████████░░░░░░░░░░░░░▄▄███████████▀▀▀██████████║█║██║██║█║█████████████████");
            //Console.WriteLine("████████████░░░░░░░░░▄▄██████████████░▄▄███████████╚═════════╝█████████████████");
            //Console.WriteLine("████████████░░░░▄▄▄████████████████████████████████████████████████████████████");
            //Console.WriteLine("███████████████████████████████████████████████████████████████████████████████");
            //Console.WriteLine("███████░░░█░██░█░███░█░█░███░░░█░░░█░░░█░░░█░░░█░░░█░░░█░░░█░█░░░█░██░█████████");
            //Console.WriteLine("███████░█░█░░█░█░░█░░█░░░███░███░█░█░█░█░█░█░█░█░█░█░█░██░██░█░█░█░░█░█████████");
            //Console.WriteLine("███████░█░█░█░░█░█░█░███░███░███░█░█░░██░░██░█░█░░██░░░██░██░█░█░█░█░░█████████");
            //Console.WriteLine("███████░░░█░██░█░███░█░░░███░░░█░░░█░█░█░███░░░█░█░█░█░██░██░█░░░█░██░█████████");
            //Console.WriteLine("███████████████████████████████████████████████████████████████████████████████");

            Console.WriteLine("███████████████████████████████████████████████████████████████████████████████");
            Console.WriteLine("█████████████████████░░░░▀█░░░░███████████████████▀▄▄▄▄▄▄▄▄▄▄▄▀████████████████");
            Console.WriteLine("██████████████████████░░░░▀█░░░░████████████████▀░▄███████████▄░▀██████████████");
            Console.WriteLine("███████████████████████░░░░░░░░░▄███▀▀░░░██████░▄███████████████▄░█████████████");
            Console.WriteLine("████████████████████████░░░▄▄▄█▀▀░░░░▄▄▄███████░█████████████████░█████████████");
            Console.WriteLine("██████████████████████████▀▀▀░░░░▄▄████████████░█████████████████░█████████████");
            Console.WriteLine("██████████████████████▀▀░░░░▄▄████▀▀███████████░█████████████████░█████████████");
            Console.WriteLine("███████████████████▀░░░▄▄▄█▀▀░░▀▀█▄▄▄██████████░█░█████████████░█░█████████████");
            Console.WriteLine("███████████████████▄▄█████▀░░▄▄░▄▄█▀▀░▀████████░░▄███▒▒███▒▒███▄░░█████████████");
            Console.WriteLine("██████████████████████████░░░██▀█▀░░▄▄█████████░▄██▒▒▒▒███▒▒▒▒██▄░█████████████");
            Console.WriteLine("████████████████████████▀█░░░▀█░▀██▀▀░▄████████░██▒▒▄▒▒███▒▒▄▒▒██░█████████████");
            Console.WriteLine("███████████████▀▀▀▀░░░░░░▀█░░░▀█░█▄▄██▀▀███████░██▒▒▒▒█████▒▒▒▒██░█████████████");
            Console.WriteLine("████████████░░░░░░░░░░░░░░▀█░░░▀█░▀█░▄▄████████░▀███████████████▀░█████████████");
            Console.WriteLine("████████████░░░░░░░░░░░░░░░█▄░░░██▄█▀▀▀█████████▄░░░░██║█║██░░░░▄██████████████");
            Console.WriteLine("████████████░░░░░░░░░░░░░░░░███████▄░░░████████████░█████████░█████████████████");
            Console.WriteLine("████████████░░░░░░░░░░░░░▄▄███████████▀▀▀██████████░█░██░██░█░█████████████████");
            Console.WriteLine("████████████░░░░░░░░░▄▄██████████████░▄▄███████████░░░░░░░░░░░█████████████████");
            Console.WriteLine("████████████░░░░▄▄▄████████████████████████████████████████████████████████████");
            Console.WriteLine("███████████████████████████████████████████████████████████████████████████████");
            Console.WriteLine("███████░░░█░██░█░███░█░█░███░░░█░░░█░░░█░░░█░░░█░░░█░░░█░░░█░█░░░█░██░█████████");
            Console.WriteLine("███████░█░█░░█░█░░█░░█░░░███░███░█░█░█░█░█░█░█░█░█░█░█░██░██░█░█░█░░█░█████████");
            Console.WriteLine("███████░█░█░█░░█░█░█░███░███░███░█░█░░██░░██░█░█░░██░░░██░██░█░█░█░█░░█████████");
            Console.WriteLine("███████░░░█░██░█░███░█░░░███░░░█░░░█░█░█░███░░░█░█░█░█░██░██░█░░░█░██░█████████");
            Console.WriteLine("███████████████████████████████████████████████████████████████████████████████");
            Thread.Sleep(1000); 
            Console.Clear();

            Console.WriteLine("███████████████████████████████████████████████████████████████████████████████");
            Console.WriteLine("█████████████████████░░░░▀█░░░░███████████████████▀▄▄▄▄▄▄▄▄▄▄▄▀████████████████");
            Console.WriteLine("██████████████████████░░░░▀█░░░░████████████████▀░▄███████████▄░▀██████████████");
            Console.WriteLine("███████████████████████░░░░░░░░░▄███▀▀░░░██████░▄███████████████▄░█████████████");
            Console.WriteLine("████████████████████████░░░▄▄▄█▀▀░░░░▄▄▄███████░█████████████████░█████████████");
            Console.WriteLine("██████████████████████████▀▀▀░░░░▄▄████████████░█████████████████░█████████████");
            Console.WriteLine("██████████████████████▀▀░░░░▄▄████▀▀███████████░█████████████████░█████████████");
            Console.WriteLine("███████████████████▀░░░▄▄▄█▀▀░░▀▀█▄▄▄██████████░█░█████████████░█░█████████████");
            Console.WriteLine("███████████████████▄▄█████▀░░▄▄░▄▄█▀▀░▀████████░░▄███▒▒███▒▒███▄░░█████████████");
            Console.WriteLine("██████████████████████████░░░██▀█▀░░▄▄█████████░▄██▒▒▒▒███▒▒▒▒██▄░█████████████");
            Console.WriteLine("████████████████████████▀█░░░▀█░▀██▀▀░▄████████░██▒▒▄▒▒███▒▒▄▒▒██░█████████████");
            Console.WriteLine("███████████████▀▀▀▀░░░░░░▀█░░░▀█░█▄▄██▀▀███████░██▒▒▒▒█████▒▒▒▒██░█████████████");
            Console.WriteLine("████████████░░░░░░░░░░░░░░▀█░░░▀█░▀█░▄▄████████░▀███████████████▀░█████████████");
            Console.WriteLine("████████████░░░░░░░░░░░░░░░█▄░░░██▄█▀▀▀█████████▄░░░░██║█║██░░░░▄██████████████");
            Console.WriteLine("████████████░░░░░░░░░░░░░░░░███████▄░░░████████████░█████████░█████████████████");
            Console.WriteLine("████████████░░░░░░░░░░░░░▄▄███████████▀▀▀██████████░█░██░██░█░█████████████████");
            Console.WriteLine("████████████░░░░░░░░░▄▄██████████████░▄▄███████████░░░░░░░░░░░█████████████████");
            Console.WriteLine("████████████░░░░▄▄▄████████████████████████████████████████████████████████████");
            Console.WriteLine("███████████████████████████████████████████████████████████████████████████████");
            Console.WriteLine("███████░░░█░██░█░███░█░█░███░░░█░░░█░░░█░░░█░░░█░░░█░░░█░░░█░█░░░█░██░█████████");
            Console.WriteLine("███████░█░█░░█░█░░█░░█░░░███░███░█░█░█░█░█░█░█░█░█░█░█░██░██░█░█░█░░█░█████████");
            Console.WriteLine("███████░█░█░█░░█░█░█░███░███░███░█░█░░██░░██░█░█░░██░░░██░██░█░█░█░█░░█████████");
            Console.WriteLine("███████░░░█░██░█░███░█░░░███░░░█░░░█░█░█░███░░░█░█░█░█░██░██░█░░░█░██░█░███████");
            Console.WriteLine("███████████████████████████████████████████████████████████████████████████████");
            Thread.Sleep(1000);
            Console.Clear();

            Console.WriteLine("███████████████████████████████████████████████████████████████████████████████");
            Console.WriteLine("█████████████████████░░░░▀█░░░░███████████████████▀▄▄▄▄▄▄▄▄▄▄▄▀████████████████");
            Console.WriteLine("██████████████████████░░░░▀█░░░░████████████████▀░▄███████████▄░▀██████████████");
            Console.WriteLine("███████████████████████░░░░░░░░░▄███▀▀░░░██████░▄███████████████▄░█████████████");
            Console.WriteLine("████████████████████████░░░▄▄▄█▀▀░░░░▄▄▄███████░█████████████████░█████████████");
            Console.WriteLine("██████████████████████████▀▀▀░░░░▄▄████████████░█████████████████░█████████████");
            Console.WriteLine("██████████████████████▀▀░░░░▄▄████▀▀███████████░█████████████████░█████████████");
            Console.WriteLine("███████████████████▀░░░▄▄▄█▀▀░░▀▀█▄▄▄██████████░█░█████████████░█░█████████████");
            Console.WriteLine("███████████████████▄▄█████▀░░▄▄░▄▄█▀▀░▀████████░░▄███▒▒███▒▒███▄░░█████████████");
            Console.WriteLine("██████████████████████████░░░██▀█▀░░▄▄█████████░▄██▒▒▒▒███▒▒▒▒██▄░█████████████");
            Console.WriteLine("████████████████████████▀█░░░▀█░▀██▀▀░▄████████░██▒▒▄▒▒███▒▒▄▒▒██░█████████████");
            Console.WriteLine("███████████████▀▀▀▀░░░░░░▀█░░░▀█░█▄▄██▀▀███████░██▒▒▒▒█████▒▒▒▒██░█████████████");
            Console.WriteLine("████████████░░░░░░░░░░░░░░▀█░░░▀█░▀█░▄▄████████░▀███████████████▀░█████████████");
            Console.WriteLine("████████████░░░░░░░░░░░░░░░█▄░░░██▄█▀▀▀█████████▄░░░░██║█║██░░░░▄██████████████");
            Console.WriteLine("████████████░░░░░░░░░░░░░░░░███████▄░░░████████████░█████████░█████████████████");
            Console.WriteLine("████████████░░░░░░░░░░░░░▄▄███████████▀▀▀██████████░█░██░██░█░█████████████████");
            Console.WriteLine("████████████░░░░░░░░░▄▄██████████████░▄▄███████████░░░░░░░░░░░█████████████████");
            Console.WriteLine("████████████░░░░▄▄▄████████████████████████████████████████████████████████████");
            Console.WriteLine("███████████████████████████████████████████████████████████████████████████████");
            Console.WriteLine("███████░░░█░██░█░███░█░█░███░░░█░░░█░░░█░░░█░░░█░░░█░░░█░░░█░█░░░█░██░█████████");
            Console.WriteLine("███████░█░█░░█░█░░█░░█░░░███░███░█░█░█░█░█░█░█░█░█░█░█░██░██░█░█░█░░█░█████████");
            Console.WriteLine("███████░█░█░█░░█░█░█░███░███░███░█░█░░██░░██░█░█░░██░░░██░██░█░█░█░█░░█████████");
            Console.WriteLine("███████░░░█░██░█░███░█░░░███░░░█░░░█░█░█░███░░░█░█░█░█░██░██░█░░░█░██░█░█░█████");
            Console.WriteLine("███████████████████████████████████████████████████████████████████████████████");
            Thread.Sleep(1000);
            Console.Clear();
            Console.WriteLine("███████████████████████████████████████████████████████████████████████████████");
            Console.WriteLine("█████████████████████░░░░▀█░░░░███████████████████▀▄▄▄▄▄▄▄▄▄▄▄▀████████████████");
            Console.WriteLine("██████████████████████░░░░▀█░░░░████████████████▀░▄███████████▄░▀██████████████");
            Console.WriteLine("███████████████████████░░░░░░░░░▄███▀▀░░░██████░▄███████████████▄░█████████████");
            Console.WriteLine("████████████████████████░░░▄▄▄█▀▀░░░░▄▄▄███████░█████████████████░█████████████");
            Console.WriteLine("██████████████████████████▀▀▀░░░░▄▄████████████░█████████████████░█████████████");
            Console.WriteLine("██████████████████████▀▀░░░░▄▄████▀▀███████████░█████████████████░█████████████");
            Console.WriteLine("███████████████████▀░░░▄▄▄█▀▀░░▀▀█▄▄▄██████████░█░█████████████░█░█████████████");
            Console.WriteLine("███████████████████▄▄█████▀░░▄▄░▄▄█▀▀░▀████████░░▄███▒▒███▒▒███▄░░█████████████");
            Console.WriteLine("██████████████████████████░░░██▀█▀░░▄▄█████████░▄██▒▒▒▒███▒▒▒▒██▄░█████████████");
            Console.WriteLine("████████████████████████▀█░░░▀█░▀██▀▀░▄████████░██▒▒▄▒▒███▒▒▄▒▒██░█████████████");
            Console.WriteLine("███████████████▀▀▀▀░░░░░░▀█░░░▀█░█▄▄██▀▀███████░██▒▒▒▒█████▒▒▒▒██░█████████████");
            Console.WriteLine("████████████░░░░░░░░░░░░░░▀█░░░▀█░▀█░▄▄████████░▀███████████████▀░█████████████");
            Console.WriteLine("████████████░░░░░░░░░░░░░░░█▄░░░██▄█▀▀▀█████████▄░░░░██║█║██░░░░▄██████████████");
            Console.WriteLine("████████████░░░░░░░░░░░░░░░░███████▄░░░████████████░█████████░█████████████████");
            Console.WriteLine("████████████░░░░░░░░░░░░░▄▄███████████▀▀▀██████████░█░██░██░█░█████████████████");
            Console.WriteLine("████████████░░░░░░░░░▄▄██████████████░▄▄███████████░░░░░░░░░░░█████████████████");
            Console.WriteLine("████████████░░░░▄▄▄████████████████████████████████████████████████████████████");
            Console.WriteLine("███████████████████████████████████████████████████████████████████████████████");
            Console.WriteLine("███████░░░█░██░█░███░█░█░███░░░█░░░█░░░█░░░█░░░█░░░█░░░█░░░█░█░░░█░██░█████████");
            Console.WriteLine("███████░█░█░░█░█░░█░░█░░░███░███░█░█░█░█░█░█░█░█░█░█░█░██░██░█░█░█░░█░█████████");
            Console.WriteLine("███████░█░█░█░░█░█░█░███░███░███░█░█░░██░░██░█░█░░██░░░██░██░█░█░█░█░░█████████");
            Console.WriteLine("███████░░░█░██░█░███░█░░░███░░░█░░░█░█░█░███░░░█░█░█░█░██░██░█░░░█░██░█░█░█░███");
            Console.WriteLine("███████████████████████████████████████████████████████████████████████████████");
            Thread.Sleep(1000);
            Console.Clear();

            //////////////////////////////////////////////////////////////////Меню
            Console.WriteLine("[   █    ████ ███  █ ███   █ █   █ █████       ]");
            Console.WriteLine("[   █    █  █ █  █ █ █  █  █ ██  █   █    █    ]");
            Console.WriteLine("[   █    ████ ███  █ ███   █ █ █ █   █   ███   ]");
            Console.WriteLine("[   █  █ █  █ █  █ █ █  █  █ █  ██   █    █    ]");
            Console.WriteLine("[   ████ █  █ ████ █ █   █ █ █   █   █         ]");
            Console.WriteLine("  ");
            Console.WriteLine("  ");
            Console.WriteLine("[            Press Y to continue               ]");
            Console.WriteLine("[               Or N to exit                   ]");
            ConsoleKeyInfo key = Console.ReadKey(true);

            if (key.Key == ConsoleKey.N) {   Environment.Exit(0);   }

            Console.ReadLine();
            Console.Clear();

            if (key.Key == ConsoleKey.Y)
            {
                Console.WriteLine("Подключение к серверу...");
                socket.Connect("127.0.0.1", 2048);
                Console.WriteLine("Подключено");
                Thread.Sleep(2000);
                Console.Clear();

                Console.WriteLine("Введите спрайт:");
                string tchar = Console.ReadLine();
                char spr = Convert.ToChar(tchar[0]);
                Console.Clear();

                Console.WriteLine("Выберите цвет:");
                for (int i = 0; i <= 14; i++)
                {
                    Console.ForegroundColor = (ConsoleColor)i;
                    Console.WriteLine(i);
                }
                Console.ResetColor();
                ConsoleColor clr = (ConsoleColor)int.Parse(Console.ReadLine());
                Console.Clear();

                int x = random.Next(1, 5);
                int y = random.Next(1, 5);

                Console.WriteLine("Получение идентификатора");
                SendPacket(PacketInfo.ID);
                int id = ReceivePacket();
                Console.WriteLine("Получен ID:" + id);
                Thread.Sleep(1000);
                Console.Clear();

                player = new Player(x, y, spr, clr, id);
                SendPacket(PacketInfo.Position);

                ////////////////////////лабиринт
                Console.ForegroundColor = (ConsoleColor)7;
                bool sost = false;
                int[,] nums2 =  { {1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
                    {1,0,1,0,0,0,0,1,0,0,0,1,1,1,1,0,0,0,0,1,},
                    {1,0,1,0,1,1,0,1,0,1,0,0,0,0,1,0,1,1,0,1,},
                    {1,0,0,0,0,0,0,1,0,1,1,1,1,0,1,0,0,1,0,1,},
                    {1,1,1,1,1,1,0,1,0,1,1,1,1,0,1,1,0,1,0,1,},
                    {1,0,0,1,0,0,0,0,0,1,1,0,0,0,0,0,0,1,0,1,},
                    {1,0,1,1,0,1,1,1,0,0,0,0,1,0,1,1,1,1,0,1,},
                    {1,0,0,0,0,0,0,0,0,1,1,1,1,0,1,0,1,1,1,1,},
                    {1,1,1,1,1,1,0,1,1,1,0,0,0,0,1,0,1,1,1,1,},
                    {1,1,0,0,0,1,0,1,1,1,0,1,1,1,1,0,0,0,0,1,},
                    {1,0,0,1,0,0,0,0,0,1,0,0,0,0,1,1,1,1,0,1,},
                    {1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,0,0,0,0,1,},
                    {1,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,1,1,1,},
                    {1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,0,0,0,0,1,},
                    {1,0,1,0,0,0,1,0,0,0,1,0,0,0,0,0,1,1,0,1,},
                    {1,0,1,1,1,1,1,0,1,0,1,1,1,0,1,0,0,0,0,1,},
                    {1,0,1,0,0,0,1,0,1,0,0,0,1,0,1,1,1,1,1,1,},
                    {1,0,1,0,1,0,1,0,1,1,1,0,0,0,0,0,0,0,0,1,},
                    {1,0,1,0,1,0,1,0,1,0,1,1,1,1,1,1,1,1,0,1,},
                    {1,0,1,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,1,},
                    {1,0,1,0,1,0,0,0,0,0,1,1,1,1,1,1,0,1,1,1,},
                    {1,0,1,0,1,1,1,1,1,0,0,0,1,0,0,0,0,0,0,1,},
                    {1,0,1,0,0,0,0,0,1,1,1,0,0,0,1,1,1,1,0,1,},
                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,} };


                for (int i = 0; i < nums2.GetLength(0); i++)
                {
                    for (int j = 0; j < nums2.GetLength(1); j++)
                    {

                        if (nums2[i, j] == 0) { Console.Write(" "); }
                        if (nums2[i, j] == 1) { Console.Write("█"); }
                        if (nums2[i, j] == 3) { Console.Write("░"); }

                    }
                    Console.WriteLine();
                }

                //¯\_(ツ)_/¯
                //∩( ・ω・)∩

                Task.Run(() => { while (true) ReceivePacket(); });
                while (true)
                {
                    player.Draw();

                    switch (Console.ReadKey(true).Key)
                    {
                        case ConsoleKey.LeftArrow:
                            if (nums2[player.Y, player.X - 1] == 1) { break; }
                            // Console.WriteLine(player.X + "x" + player.Y + "y");
                            player.Remove(); player.X--;
                            break;

                        case ConsoleKey.RightArrow:
                            if (nums2[player.Y, player.X + 1] == 1) { break; }
                            //Console.WriteLine(player.X + "x" + player.Y + "y");
                            player.Remove(); player.X++;
                            break;

                        case ConsoleKey.UpArrow:
                            if (nums2[player.Y - 1, player.X] == 1) { break; }
                            // Console.WriteLine(player.X + "x" + player.Y + "y");
                            player.Remove(); player.Y--;
                            break;

                        case ConsoleKey.DownArrow:
                            if (nums2[player.Y + 1, player.X] == 1) { break; }
                            //Console.WriteLine(player.X + "x" + player.Y + "y");
                            player.Remove(); player.Y++;
                            break;


                    }//switch

                    if (nums2[player.Y, player.X] == 3)
                    {
                        Console.Clear();

                        // Console.WriteLine("∩( ・ω・)∩ Win: ");Console.WriteLine(id + " !");
                        Console.WriteLine("[      Win: " + id + " !      ]");
                        //socket.Close();
                        Thread.Sleep(3000);

                        //Console.WriteLine("[               Press N to exit                   ]");
                        //if (key.Key == ConsoleKey.N) { Environment.Exit(0); }
                        break;
                    }

                    if (player.X < 0)
                    { player.X++; }
                    if (player.Y < 0)
                    { player.Y++; }
                    if (player.Y >= 25)
                    { player.Y--; }
                    if (player.X >= 80)
                    { player.X--; }
                    player.Draw(); SendPacket(PacketInfo.Position);
                }//while
                Console.ReadKey(true);

            }
            else
                Console.WriteLine("The wrong key is pressed");
        }//main

        static void SendPacket(PacketInfo info)
        {
            ms.Position = 0;
            switch (info)
            {
                case PacketInfo.ID:
                    writer.Write(0);
                    socket.Send(ms.GetBuffer());
                break;

                case PacketInfo.Position:
                    writer.Write(1);
                    writer.Write(player.ID);
                    writer.Write(player.X);
                    writer.Write(player.Y);
                    writer.Write(player.Sprite);
                    writer.Write((int)player.Color);
                    socket.Send(ms.GetBuffer());
                break;
                   
            }
        }

        static int ReceivePacket()
        {
            ms.Position = 0;
            socket.Receive(ms.GetBuffer());
            //|0, id
            int code = reader.ReadInt32();
            //0, |id
            int id;
            int x;
            int y;
            char sprite;
            ConsoleColor color;

            switch(code)
            {
                case 0: return reader.ReadInt32();

                case 1:
                    id = reader.ReadInt32();
                    x = reader.ReadInt32();
                    y = reader.ReadInt32();

                    Player plr = players.Find(p=> p.ID == id);

                    if (plr != null)
                    {
                        plr.Remove();
                        plr.X = x;
                        plr.Y = y;
                        plr.Draw();
                    }
                    else
                    {
                        sprite = reader.ReadChar();
                        color = (ConsoleColor)reader.ReadInt32();
                        plr = new Player(x, y, sprite, color, id);
                        players.Add(plr);
                        plr.Draw();
                    }
                break;
            }

            return -1;
        }

    }
}
