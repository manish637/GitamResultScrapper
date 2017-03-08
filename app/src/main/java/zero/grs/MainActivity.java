package zero.grs;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Home);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        TextView reg = (TextView)findViewById(R.id.reg);
        Typeface cf1 = Typeface.createFromAsset(getAssets(),  "fonts/Fonty.ttf");
        reg.setTypeface(cf1);

        final EditText regedit = (EditText) findViewById(R.id.regedit);
        Typeface cf2 = Typeface.createFromAsset(getAssets(),  "fonts/Fonty.ttf");
        regedit.setTypeface(cf2);

        TextView sem = (TextView)findViewById(R.id.sem);
        Typeface cf3 = Typeface.createFromAsset(getAssets(),  "fonts/Fonty.ttf");
        sem.setTypeface(cf3);

        Button bget = (Button) findViewById(R.id.btnresult);
        Typeface cf4 = Typeface.createFromAsset(getAssets(),  "fonts/Fonty.ttf");
        bget.setTypeface(cf4);

        final Spinner dropdown = (Spinner)findViewById(R.id.spinner1);
        String[] items = new String[]{"1", "2", "3","4","5","6","7","8","9","10","12","13","14","15"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        Button getresult = (Button) findViewById(R.id.btnresult);
        getresult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent wedIntent = new Intent(MainActivity.this, GitamResult.class);
                wedIntent.putExtra("username", regedit.getText().toString().trim());
                wedIntent.putExtra("sem", dropdown.getSelectedItem().toString());
                startActivity(wedIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.infoicon:
                Intent wedIntent = new Intent(MainActivity.this, About.class);
                startActivity(wedIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}





