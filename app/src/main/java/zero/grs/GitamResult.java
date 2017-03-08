package zero.grs;

/**
 * Created by ManishKumar on 26-02-2017.
 */
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Text;

import java.io.*;
public class GitamResult extends AppCompatActivity {
   public String username;
  public  String cbosem;
    EditText reg;
    TextView ct;
    TextView ex;
    TextView sem;
    TextView month,year;
    TextView name,branch,roll,sgpa,cgpa;
    private String subjectcode;
    private String subject, credit;
    private String grade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        username = getIntent().getExtras().getString("username");
        cbosem = getIntent().getExtras().getString("sem");
        setTheme(R.style.MyTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_page);

        reg = (EditText) findViewById(R.id.regedit);
        final ArrayList<Word> words = new ArrayList<Word>();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        get_results(words);
        WordAdapter Adapter = new WordAdapter(this, words);


        ListView listView = (ListView) findViewById(R.id.resultView);

        listView.setAdapter(Adapter);

        //words.add(new Word(get));
    }

   public void get_results(ArrayList<Word> a){
        try{
            String[] Marks = new String[10];
            String[] Info = new String[10];
            // these are the various Form Data that are passed along the post method

            String url,Button1,useragent;
            String _EVENTTARGET="",_EVENTARGUMENT="",_VIEWSTATE,_VIEWSTATEGENERATOR,_EVENTVALIDATION;
            _VIEWSTATE="/wEPDwULLTE3MTAzMDk3NzUPZBYCAgMPZBYCAgcPDxYCHgRUZXh0ZWRkZKKjA/8YeuWfLRpWAZ2J1Qp0eXCJ";
            _VIEWSTATEGENERATOR="65B05190";
            _EVENTVALIDATION="/wEWFQKj/sbfBgLnsLO+DQLIk+gdAsmT6B0CypPoHQLLk+gdAsyT6B0CzZPoHQLOk+gdAt+T6B0C0JPoHQLIk6geAsiTpB4CyJOgHgLIk5weAsiTmB4CyJOUHgKL+46CBgKM54rGBgK7q7GGCALWlM+bAsr6TbZa4e1ProM8biQQXbC9/wS2";
            Button1="Get+Result";

            // url of the page where rollno and semmester is asked
            url = "https://doeresults.gitam.edu/onlineresults/pages/Newgrdcrdinput1.aspx";
            useragent = "Mozilla/5.0";

            // creating connection
            Connection.Response response = Jsoup.connect(url).userAgent(useragent)
                    .method(Connection.Method.GET)
                    .execute();

            // storing response after posting
            response = Jsoup.connect(url)
                    .cookies(response.cookies())
                    .data("__EVENTTARGET", _EVENTTARGET)
                    .data("__EVENTARGUMENT", _EVENTARGUMENT)
                    .data("__VIEWSTATE", _VIEWSTATE)
                    .data("__VIEWSTATEGENERATOR", _VIEWSTATEGENERATOR)
                    .data("__EVENTVALIDATION", _EVENTVALIDATION)
                    .data("cbosem", this.cbosem)
                    .data("txtreg", this.username)
                    .data("Button1",Button1)
                    .userAgent(useragent)
                    .method(Connection.Method.POST)
                    .followRedirects(true)
                    .execute();

            int i=0;

            // parsing the response
            Document d = response.parse();

            Elements course = d.select("div.vrg");
            for(Element co : course.select("span")){
                // System.out.println(c.text());
                Info[i++]=co.text();

            }
            ct = (TextView) findViewById(R.id.college_type);
            ex = (TextView) findViewById(R.id.examination);
            sem = (TextView) findViewById(R.id.semester);
            month = (TextView) findViewById(R.id.month);
            year = (TextView) findViewById(R.id.year);
            name = (TextView) findViewById(R.id.name);
            branch = (TextView) findViewById(R.id.branch);
            roll = (TextView) findViewById(R.id.rollno);
            sgpa = (TextView) findViewById(R.id.sgpa);
            cgpa = (TextView) findViewById(R.id.cgpa);
            ct.setText(Info[0] + "  ");
            ex.setText(Info[1]);
            sem.setText(Info[2]);
            month.setText(Info[3]+ "  ");
            year.setText(Info[4]);
            name.setText(Info[5]);
            roll.setText(Info[6]);
            branch.setText(Info[7]);
            sgpa.setText(Info[8]);
            cgpa.setText(Info[9]);
      /*      System.out.println("\n\n");
            System.out.println("\t\t\t\t GITAM UNIVERSITY");
            System.out.println("\t\t\t"+Info[0]+" "+Info[1]);
            System.out.println("Semester "+Info[2]+"\t\t\t\t\t\t\t"+Info[3]+" "+Info[4]);
            System.out.println("\t\t\t\t RESULT SHEET\n");
            System.out.println("Name : "+Info[5]+"\t\t\t\t\tRoll No: "+Info[6]);
            System.out.println("Branch : "+Info[7]);*/

          //  final ArrayList<Word> words = new ArrayList<Word>();

            Elements table = d.select("table.table-responsive");
         /*   System.out.print("COURSE CODE");
            System.out.print("              SUBJECT NAME");
            System.out.print("                                  CREDITS   GRADE");
            System.out.println("");*/
            for(Element row : table.select("tr")){
                i=0;
                Elements tds = row.getElementsByTag("td");
                for(Element t : tds){
                    Marks[i++]=t.text();
                }
                subjectcode = Marks[0];
                subject = Marks[1];
                credit = Marks[2];
                grade = Marks[3];
             /*  for(int k=0;k<i;k++){
                    // System.out.print(Marks[k].length());
                   if(k==0)subjectcode = Marks[k];
                    if(k==1)System.out.print("     ");
                    else if(k==2)for(int j=0;j<(60-Marks[k-1].length());j++)System.out.print(" ");
                    else if(k==3)System.out.print("      ");
                    System.out.print(Marks[k]);
                }*/
                a.add(new Word(subjectcode,subject,credit,grade));
            }
         //   System.out.println("\n\t\t\t\t\t\t\t\t\tGPA :  "+Info[8]);
        //    System.out.println("\t\t\t\t\t\t\t\t\tCGPA:  "+Info[9]);
            //  public String getUsername(){return username;}


        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}