
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class SlangDictionary
{
    public final static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    } 

    public static void pauseScreen(){
        System.out.println("Press Any Key To Continue...");
        new java.util.Scanner(System.in).nextLine();
    }

    public static HashMap<String,List<String>> m=new HashMap<String,List<String>>();
    public static List<String> historySlangWord=new ArrayList();
    public static Scanner word= new Scanner(System.in);
    
    public static void GetHistory()
    {
        try
     {
        File f=new File("history.txt");
        FileReader fr=new FileReader(f);
        BufferedReader br=new BufferedReader(fr);
        String line;
        while((line=br.readLine())!=null)
        {
            historySlangWord.add(line);
        }
        fr.close();
        br.close();
    }
    catch (Exception ex)
    {
        System.out.println("ERROR"+ex);
    }
    }

    public static void GetData(){
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

    //1.Find Slang Word
    public static void FindSlangWord()
    {
        clearScreen();
        System.out.print("What word u want to find: ");
        String check=word.nextLine();
        List<String> test=m.get(check);
        historySlangWord.add(check);
        System.out.println(test);
        pauseScreen();
        Menu();
    }

    //2.Find Definition
    public static void FindDefinition()
    {
        clearScreen();
        System.out.println("What definition u want to find: ");
        String check=word.nextLine();
        historySlangWord.add(check);
        List<String> answer=new ArrayList();
        for (String i: m.keySet())
        {
            if (m.get(i).contains(check))
            {
                answer.add(i);
            }

        }
        System.out.println(answer);
        pauseScreen();
        Menu();
    }

    //3.History Searching
    static void ShowHistorySlangWord()
    {
        clearScreen();
        System.out.println("Your history search is: ");
        for (String temp: historySlangWord)
        {
            System.out.println(temp);
        }
        pauseScreen();
        Menu();
    }
    
    //4.Add Slang Word
    public static void CreateSlangWord()
    {
        clearScreen();
        System.out.println("What is your new Slang Word: ");
        String check=word.nextLine();
        System.out.println("What is the definition: ");
        String check1=word.nextLine();
        List<String> t=new ArrayList();
        if (m.containsKey(check))
        {
         System.out.println("Do u want to overwrite: ");
        }
        else
        {
            t.add(check1);
            m.put(check,t);
            System.out.println("Add New Slang Word Successfully");
        }
        pauseScreen();
        Menu();
    }


    //5.Edit SlangWord
    static void EditSlangWord(){
        clearScreen();
        System.out.print("What slangword u want to edit: ");
        String check=word.nextLine();
        if (!m.containsKey(check)) System.out.println("This slangword dont't exist");
        System.out.println("What do u want: ");
        System.out.println("1. Replace Definition ");
        System.out.println("2. Edit Definition ");
        System.out.println("YOUR CHOICE:");
        int choice=word.nextInt();
        String pass=word.nextLine();
        if (choice==1) 
        {
            System.out.print("What is the new definition : ");
            String temp=word.nextLine();
            List<String> change=new ArrayList();
            change.add(temp);
            m.replace(check,change);
        }
        else
        {
            System.out.print("Here is the definition: " );
            System.out.println(m.get(check));
            System.out.print("What word u want to change: ");
            pauseScreen();
        }
        Menu();
    }

    //6.Remove Slang Word
    static void RemoveSlangWord()
    {
        clearScreen();
        System.out.println("What slangword u want to remove: ");
        String check=word.nextLine();
        if (m.containsKey(check))
        {
            System.out.println("Are u sure u want to remove it: (Y/N) ");
            String confirm=word.nextLine();
            if (confirm.equals("Y") )m.remove(check);
        }
        Menu();
    }

    //7.Reset List
    public static void ResetSlangDictionary()
    {
        clearScreen();
        m.clear();
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
        System.out.println("Reset List To Default !!!");
        pauseScreen();
        Menu();
    }

    //8.Random Slang Word
    public static void RandomSlangWord(){
        clearScreen();
        int count=0;
        Random rd=new Random();
        int magicNumber=rd.nextInt(m.size());
        for (String i: m.keySet())
        {
            if (count==magicNumber)
            {
                System.out.print("Your Random Slang Word Is : ");
                System.out.println(i);
                System.out.print("And The Definition Is:   ");
                List<String> t=m.get(i);
                System.out.println(t);
                break;
            }else count++;
        }
        pauseScreen();
        Menu();
    }

    //9.Minigame 
    public static void GuessGame(){}

    //Update History
    public static void updateHistory(){
        try {
            File f = new File("history.txt");
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            for (String temp : historySlangWord) {
                fw.write(temp + "\n");
            }
            fw.close();
            bw.close();
        }
        catch (Exception ex) {
            System.out.println("Error: "+ex);
        }
    }

    //Update File
    public static void updateFile()
    {
        try {
            File f = new File("slang.txt");
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            for (String i: m.keySet())
            {
                fw.write(i +"`");
                List<String> temp=m.get(i);
                for (int t=0;t<temp.size();t++)
                {
                    fw.write(temp.get(t));
                    if (t+1<temp.size()) fw.write("|");
                }
                fw.write("\n");
            }
            fw.close();
            bw.close();
        }
        catch (Exception ex) {
            System.out.println("Error: "+ex);
        }
    }


    //Menu
    public static void Menu(){
        clearScreen();
        System.out.println("Choose what u want: ");
        System.out.println("1. Search by SlangWord ");
        System.out.println("2. Search by Definition ");
        System.out.println("3. Show history ");
        System.out.println("4. Add Slangword ");
        System.out.println("5. Edit Slangword ");
        System.out.println("6. Delete Slangword ");
        System.out.println("7. Reset to default ");
        System.out.println("8. Random Slangword ");
        System.out.println("9. Minigame find Definition ");
        System.out.println("10. Minigame find Slangword ");
        System.out.println("11. Clear History");
        System.out.println("12. Exit ");
        System.out.print("YOUR CHOICE:  ");
        int choice=word.nextInt();
        String pass=word.nextLine();
        if (choice==1) FindSlangWord();
        else if (choice==2) FindDefinition();
        else if (choice==3) ShowHistorySlangWord();
        else if (choice==4) CreateSlangWord();
        else if (choice==5) EditSlangWord();
        else if (choice==6) RemoveSlangWord();
        else if (choice==7) ResetSlangDictionary();
        else if (choice==8) RandomSlangWord();
        else if (choice==11) 
        {
            clearScreen();
            System.out.println("Clearing History !!!");
            historySlangWord.clear();
            pauseScreen();
            Menu();
        }
        else 
        {
            updateFile();
            updateHistory();
            System.exit(0);
        }
    }

    public static void main(String[] args)
    {
        GetData();
        GetHistory();
        //System.out.println(m.size());
        Menu();
    }
}
