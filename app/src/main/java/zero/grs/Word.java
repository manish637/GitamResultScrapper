package zero.grs;

/**
 * Created by ManishKumar on 26-02-2017.
 */
public class Word {
    private String subjectcode;
    private String subject, credit;
    private String grade;

    Word(String a, String b, String c,String d){
       subjectcode = a;
        subject = b;
        credit = c;
        grade = d;
    }

    public String getSubjectcode(){
        return subjectcode;
    }
    public String getSubject(){
        return subject;
    }
    public String getCredit(){
        return credit;
    }
    public String getGrade(){return grade;}
}
