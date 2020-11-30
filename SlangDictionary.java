
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
        Scanner word= new Scanner(System.in);
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
}