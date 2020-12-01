
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;



public class SlangDictionary
{
    public static HashMap<String,List<String>> m=new HashMap<String,List<String>>();
    public List<String> historySlangWord=new ArrayList();
    public static Scanner word= new Scanner(System.in);
    static void GetData(){
     try
     {
        File f=new File("slang.txt");
        FileReader fr=new FileReader(f);
        BufferedReader br=new BufferedReader(fr);
        String line;
        while((line=br.readLine())!=null)
        {
            if (line.contains("`"))
            {
                String[] s=line.split("`");
                String[] tmp=s[1].split("\\|");
                List<String> temp=Arrays.asList(tmp);
                m.put(s[0],temp);
            }
        }
        fr.close();
        br.close();
    }
    catch (Exception ex)
    {
        System.out.println("ERROR"+ex);
    }
    }
    public static void main(String[] args)
    {
        GetData();
        FindSlangWord();
        FindDefinition();
    }
    static void FindSlangWord()
    {
        System.out.println("What word u want to find: ");
        String check=word.nextLine();
        List<String> test=m.get(check);
        System.out.println(test);
    }
    static void FindDefinition()
    {
        Scanner word= new Scanner(System.in);
        System.out.println("What definition u want to find: ");
        String check=word.nextLine();
        List<String> answer=new ArrayList();
        for (String i: m.keySet())
        {
            if (m.get(i).contains(check))
            {
                answer.add(i);
            }

        }
        System.out.println(answer);
    }
    //static void CreateSlangWord()
    //{
      //  Scanner word= new Scanner(System.in);
       // System.out.println("What is your new Slang Word: ");
      //  String check=word.nextLine();
      //  System.out.println("What is the definition: ");
      //  String check1=word.nextLine();
     //   if (m.containsKey(check))
     ///   {
      //      System.out.println("Do u want to overwrite: ");

      //  }
    //}
    static void RemoveSlangSword()
    {
        Scanner word= new Scanner(System.in);
        System.out.println("What slangword u want to remove: ");
        String check=word.nextLine();
        if (m.containsKey(check))
        {
            System.out.println("Are u sure u want to remove it: ");
            Boolean confirm=word.nextBoolean();
            if (confirm==true) m.remove(check, m.get(check));
        }
    }

    //5.Edit SlangWord
    static void EditSlangWord(){
        Scanner word= new Scanner(System.in);
        System.out.println("What slangword u want to edit: ");
        String check=word.nextLine();
        if (m.containsKey(check))
        {
            System.out.println("What is the definition u want to change: ");
            String temp=word.nextLine();
            List<String> change=new ArrayList();
            change.add(temp);
            m.replace(check,change);
        }
        else System.out.println("This slangword dont't exist");
    }

    static void RandomSlangWord(){}

    //7.Reset List
    static void ResetSlangDictionary()
    {
        try
     {
        File f=new File("default.txt");
        FileReader fr=new FileReader(f);
        BufferedReader br=new BufferedReader(fr);
        String line;
        while((line=br.readLine())!=null)
        {
            if (line.contains("`"))
            {
                String[] s=line.split("`");
                String[] tmp=s[1].split("\\|");
                List<String> temp=Arrays.asList(tmp);
                m.put(s[0],temp);
            }
        }
        fr.close();
        br.close();
    }
    catch (Exception ex)
    {
        System.out.println("ERROR"+ex);
    }
    }

    //3.History Searching
    static void ShowHistorySlangWord(){}
}
