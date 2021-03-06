﻿using Novacode;
using System.Drawing;

namespace OtherTypes
{
    class Generator
    {
        private static void CreateHeader(DocX doc)
        {
            string headerText = "SoftUni OOP Game Contest";
            var headerFormat = new Formatting();
            headerFormat.FontFamily = new FontFamily("Tahoma");
            headerFormat.Size = 18D;
            headerFormat.Position = 12;
            var header = doc.InsertParagraph(headerText, false, headerFormat);
            header.Alignment = Alignment.center;
        }

        private static void CreateImage(DocX doc)
        {
            try
            {
                string imgPath = "rpg-game.png";
                var pic = doc.AddImage(imgPath).CreatePicture();

                var drawingImg = System.Drawing.Image.FromFile(imgPath);
                int ratio = drawingImg.Width / drawingImg.Height;
                int newWidth = (int)doc.PageWidth - (int)(doc.MarginLeft + doc.MarginRight);
                pic.Width = newWidth;
                pic.Height = newWidth / ratio;

                doc.InsertParagraph().InsertPicture(pic);
            }
            catch (System.IO.FileNotFoundException ex)
            {
                System.Console.WriteLine(ex.Message);
            }
        }

        private static void InsertMainText(DocX doc)
        {
            var textFormat = new Formatting();
            textFormat.FontFamily = new FontFamily("Tahoma");
            textFormat.Size = 10D;

            var p = doc.InsertParagraph();
            p.InsertText("SoftUni is organizing a contest for the best ", false, textFormat);

            textFormat.Bold = true;
            p.InsertText("role playing game", false, textFormat);

            textFormat.Bold = false;
            p.InsertText(" from the OOP teamwork projects. The winning teams will receive a ", false, textFormat);

            textFormat.UnderlineStyle = UnderlineStyle.singleLine;
            textFormat.Bold = true;
            p.InsertText("grand prize", false, textFormat);

            textFormat.UnderlineStyle = UnderlineStyle.none;
            textFormat.Bold = false;
            p.InsertText("!\nThe game should be:", false, textFormat);
        }

        private static void CreateList(DocX doc)
        {
            var list = doc.AddList("Properly structured and follow all good OOP practices", 0, ListItemType.Bulleted);
            doc.AddListItem(list, "Awesome");
            doc.AddListItem(list, "..Very Awesome");
            doc.InsertList(list);
        }

        private static void CreateTable(DocX doc)
        {
            const int TABLE_WIDTH = 3;
            const int TABLE_HEIGHT = 4;
            var table = doc.AddTable(TABLE_HEIGHT, TABLE_WIDTH);
            table.Alignment = Alignment.center;
            for (int row = 0; row < table.RowCount; row++)
            {
                for (int col = 0; col < table.ColumnCount; col++)
                {
                    table.Rows[row].Cells[col].Paragraphs[0].Alignment = Alignment.center;
                    if (row == 0)
                    {
                        table.Rows[row].Cells[col].FillColor = System.Drawing.Color.MediumBlue;
                        switch (col)
                        {
                            case 0:
                                table.Rows[row].Cells[col].Paragraphs[0].Append("Team");
                                break;
                            case 1:
                                table.Rows[row].Cells[col].Paragraphs[0].Append("Game");
                                break;
                            case 2:
                                table.Rows[row].Cells[col].Paragraphs[0].Append("Points");
                                break;
                        }
                    }
                    else
                    {
                        table.Rows[row].Cells[col].Paragraphs[0].Append("-");
                    }
                }
            }

            doc.InsertTable(table);
        }

        private static void CreateFooter(DocX doc)
        {
            var footerFormat = new Formatting();
            footerFormat.FontFamily = new FontFamily("Tahoma");

            footerFormat.Size = 10D;
            var p = doc.InsertParagraph("The top 3 teams will receive a ", false, footerFormat);
            p.Alignment = Alignment.center;

            footerFormat.Bold = true;
            p.InsertText("SPECTACULAR ", false, footerFormat);

            footerFormat.Bold = false;
            p.InsertText("prize:\n");

            footerFormat.Bold = true;
            footerFormat.UnderlineStyle = UnderlineStyle.singleLine;
            footerFormat.FontColor = System.Drawing.Color.MediumBlue;
            footerFormat.Size = 14D;
            p.InsertText("A HANDSHAKE FROM NAKOV", false, footerFormat);
        }

        static void Main(string[] args)
        {
            var doc = DocX.Create("softuni_oop_game_contest.docx");
            CreateHeader(doc);
            CreateImage(doc);
            doc.InsertParagraph("\n");
            InsertMainText(doc);
            CreateList(doc);
            doc.InsertParagraph("\n");
            CreateTable(doc);
            doc.InsertParagraph("\n");
            CreateFooter(doc);

            try
            {
                doc.Save();
                System.Console.WriteLine("Done!");
            }
            catch (System.IO.IOException ex)
            {
                System.Console.WriteLine(ex.Message);
            }
        } 
    }
}
