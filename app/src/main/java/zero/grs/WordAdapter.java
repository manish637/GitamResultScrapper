package zero.grs;

/**
 * Created by ManishKumar on 26-02-2017.
 */
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.*;

/**
 * Created by ManishKumar on 26-12-2016.
 */
public class WordAdapter extends ArrayAdapter<Word>{
    WordAdapter(Context context, ArrayList<Word> aword){
        super(context,0,aword);
    }
    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        View listItemView = convertView;
        if(listItemView==null){
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        Word currentWord = getItem(position);

        TextView subcode = (TextView) listItemView.findViewById(R.id.subcode);
        subcode.setText(currentWord.getSubjectcode());
        TextView subject = (TextView) listItemView.findViewById(R.id.sub_field);
        subject.setText(currentWord.getSubject());
        TextView grade = (TextView) listItemView.findViewById(R.id.grade);
        grade.setText(currentWord.getGrade());
        TextView credit = (TextView) listItemView.findViewById(R.id.credit);
        credit.setText(currentWord.getCredit());
        return listItemView;
    }
}
