package com.buechstabet.arlendai.stundenplan2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {


    TextView day,weekend,lession;
    ListView day_list;
    ArrayAdapter<String> adapter;
    String[][] lessons;
    String[][] times;
    double[][] int_time;
    final int max_array_length = 10;
    final String[] days=new String[]{null,"Sonntag","Montag","Dienstag","Mittwoch","Donnerstag","Freitag","Samstag"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        day = (TextView)findViewById(R.id.day_text);
        lession = (TextView)findViewById(R.id.main_lession);
        weekend = (TextView)findViewById(R.id.main_weekend);
        day_list = (ListView)findViewById(R.id.day_list);
        intitLessenAndTime();
        Calendar date = Calendar.getInstance();
        final int today = date.get(Calendar.DAY_OF_WEEK);
        //today = 5;
        day.setText(days[today]);
        if(today<7&&today>1) {
            initList(today);
        }
        else{
            weekend.setVisibility(View.VISIBLE);
            day_list.setVisibility(View.GONE);
        }
        initTimes();
        checkTime(today);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkTime(today);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.menu_select_days) {
            Intent intent = new Intent(MainActivity.this,DayChooser.class);
            makeBundel(intent);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.menu_tests) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void makeBundel(Intent intent){
        String[]bundelArray = new String[10];
        for(int i=0;i<lessons.length;i++){
            intent.putExtra("lessons"+i,lessons[i]);
            intent.putExtra("times"+i,times[i]);
        }
    }

    private void initList(int today_pos) {

        String[] list = new String[10];
        String[] final_list;
        int errors=0;

        for(int i = 0;i<max_array_length;i++){
            try{
                list[i] = times[today_pos][i] + "\t\t" + lessons[today_pos][i];
            }
            catch(Exception e) {
                errors++;
            }
        }
        final_list = new String[list.length-errors];
        for (int i = 0;i<list.length;i++) {
            if(!list[i].equals("null\t\tnull")){
                   final_list[i]=list[i];
            }
            else{
                final_list[i]="";
            }
        }
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,final_list);
        day_list.setAdapter(adapter);
        day_list.setVisibility(View.VISIBLE);
        weekend.setVisibility(View.GONE);
        adapter.notifyDataSetChanged();
    }

    private void intitLessenAndTime() {

        String[] mo_lessens,mo_time,tu_lessens,tu_time,we_lessens,we_time,th_lessens,th_time,fr_lessens,fr_time;

        lessons = new String[8][10];
        times = new String[8][10];

        mo_lessens = new String[]{
                "Grundlagen Elektronik",
                "Grundlagen Elektronik",
                "Mittag",
                "Grundlagen Elektronik",
                "Grundlagen Elektronik"};

        for(int i=0;i<mo_lessens.length;i++){
            lessons[2][i]=mo_lessens[i];
        }

        tu_lessens = new String[]{
                "Grundlagen Elektronik",
                "Grundlagen Elektronik",
                "Mittag",
                "Grundlagen Elektronik",
                "Grundlagen Elektronik"};

        for(int i=0;i<tu_lessens.length;i++){
            lessons[3][i]=tu_lessens[i];
        }

        we_lessens = new String[]{
                "Sport",
                "Sport",
                "Soft- & Hardware",
                "Soft- & Hardware",
                "Werkstoff Zeichnen",
                "Mittag",
                "Tech. Grundlagen Mathe",
                "Elektrotechnik",
                "Elektrotechnik",
                "Elektrotechnik"};

        for(int i=0;i<we_lessens.length;i++){
            lessons[4][i]=we_lessens[i];
        }

        th_lessens = new String[]{
                "Physik",
                "Physik",
                "Arbeitstechnik",
                "Informatik",
                "Informatik",
                "Mittag",
                "Elektronik",
                "Elektronik",
                "Elektrotechnik Labor",
                "Elektrotechnik Labor"};

        for(int i=0;i<th_lessens.length;i++){
            lessons[5][i]=th_lessens[i];
        }

        fr_lessens = new String[]{
                "Englisch",
                "Englisch",
                "Mathematik",
                "Mathematik",
                "Mathematik",
                "Mittag",
                "Chemie",
                "Chemie",
                "Französisch",
                "Französisch"};

        for(int i=0;i<fr_lessens.length;i++){
            lessons[6][i]=fr_lessens[i];
        }


        mo_time=new String[]{
                "07:45-09:10",
                "9:30-11:50",
                "11:50-12:50",
                "12:50-15:00",
                "15:15-16:50"};

        for(int i=0;i<mo_time.length;i++){
            times[2][i]=mo_time[i];
        }

        tu_time = new String[]{
                "07:45-09:55",
                "10:15-12:00",
                "12:00-13:00",
                "13:00-15:00",
                "15:15-16:50"};

        for(int i=0;i<tu_time.length;i++){
            times[3][i]=tu_time[i];
        }

        we_time=new String[]{
                "07:45-08:30",
                "08:35-09:20",
                "09:40-10:25",
                "10:30-11:15",
                "11:20-12:05",
                "12:05-13:20",
                "13:20-14:05",
                "14:10-14:55",
                "15:15-16:00",
                "16:05-16:50"};

        for(int i=0;i<we_time.length;i++){
            times[4][i]=we_time[i];
        }

        th_time=new String[]{
                "07:45-08:30",
                "08:35-09:20",
                "09:40-10:25",
                "10:30-11:15",
                "11:20-12:05",
                "12:05-13:20",
                "13:20-14:05",
                "14:10-14:55",
                "15:15-16:00",
                "16:05-16:50"};

        for(int i=0;i<th_time.length;i++){
            times[5][i]=th_time[i];
        }

        fr_time=new String[]{
                "08:25-09:10",
                "09:15-10:00",
                "10:20-11:05",
                "11:10-11:55",
                "12:00-12:45",
                "12:45-13:45",
                "13:45-14:30",
                "14:35-15:20",
                "15:35-16:20",
                "16:25-17:10"};

        for(int i=0;i<fr_time.length;i++){
            times[6][i]=fr_time[i];
        }
    }

    private void initTimes(){
        double[] int_mo_time,int_tu_time,int_we_time,int_th_time,int_fr_time;
        int_time = new double[8][20];

        int_mo_time = new double[]{
                0.322917, 0.381944,
                0.395833, 0.493056,
                0.493056, 0.534722,
                0.534722,0.625,
                0.635417,0.701389
        };

        int_tu_time = new double[]{
                0.322917, 0.381944,
                0.395833, 0.493056,
                0.493056, 0.534722,
                0.534722,0.625,
                0.635417,0.701389
        };

        int_we_time = new double[]{
                0.322917,0.354167,
                0.357639,0.388889,
                0.402778,0.434028,
                0.4375,0.46875,
                0.472222,0.503472,
                0.503472,0.555556,
                0.555556,0.586806,
                0.590278,0.621528,
                0.635417,0.666667,
                0.670139,0.701389
        };

        int_th_time = new double[]{
                0.322917,0.354167,
                0.357639,0.388889,
                0.402778,0.434028,
                0.4375,0.46875,
                0.472222,0.503472,
                0.503472,0.555556,
                0.555556,0.586806,
                0.590278,0.621528,
                0.635417,0.666667,
                0.670139,0.701389
        };

        int_fr_time = new double[]{
                0.350694,0.381944,
                0.385417,0.416667,
                0.430556,0.461806,
                0.465278,0.496528,
                0.5,0.53125,
                0.53125,0.572917,
                0.572917,0.604167,
                0.607639,0.638889,
                0.649306,0.680556,
                0.684028,0.715278};

        for(int i=0;i<int_we_time.length;i++){
            if(i<int_mo_time.length) {
                int_time[2][i] = int_mo_time[i];
                int_time[3][i] = int_tu_time[i];
            }
            if(i<int_we_time.length){
                int_time[4][i]=int_we_time[i];
                int_time[5][i]=int_th_time[i];
            }
            if(i<int_fr_time.length) {
                int_time[6][i] = int_fr_time[i];
            }
        }
    }

    private void checkTime(int today ) {
        Calendar calendar = Calendar.getInstance();
        double current_hour = calendar.get(Calendar.HOUR_OF_DAY);
        double current_min = calendar.get(Calendar.MINUTE);
        double current_time = ((current_min/60)+current_hour)/24;
        boolean workt=false;


        try {
            if (current_time > int_time[today][0] && current_time < int_time[today][1]) {
                //Lektion 1
                lession.setText(lessons[today][0]);
            } else if (current_time > int_time[today][2] && current_time < int_time[today][3]) {
                //Lektion 2
                lession.setText(lessons[today][1]);
            } else if (current_time > int_time[today][4] && current_time < int_time[today][5]) {
                //Lektion 3
                lession.setText(lessons[today][2]);
            } else if (current_time > int_time[today][6] && current_time < int_time[today][7]) {
                //Lektion 4
                lession.setText(lessons[today][3]);
            } else if (current_time > int_time[today][8] && current_time < int_time[today][9]) {
                //Lektion 5
                lession.setText(lessons[today][4]);
            } else if (current_time > int_time[today][10] && current_time < int_time[today][11]) {
                //Lektion 6
                lession.setText(lessons[today][5]);
            } else if (current_time > int_time[today][12] && current_time < int_time[today][13]) {
                //Lektion 7
                lession.setText(lessons[today][6]);
            } else if (current_time > int_time[today][14] && current_time < int_time[today][15]) {
                //Lektion 8
                lession.setText(lessons[today][7]);
            } else if (current_time > int_time[today][16] && current_time < int_time[today][17]) {
                //Lektion 9
                lession.setText(lessons[today][8]);
            } else if (current_time > int_time[today][18] && current_time < int_time[today][19]) {
                //Lektion 10
                lession.setText(lessons[today][9]);
            } else {
                for (int i = 0; i < 20; i += 2) {
                    if (current_time < int_time[today][i]) {
                        if (i > 2) {
                            lession.setText("Next: " + lessons[today][i / 2]);
                            workt = true;
                            break;
                        } else {
                            lession.setText("Next: " + lessons[today][0]);
                            workt = true;
                            break;
                        }
                    }
                }
                if (!workt) {
                    if (lessons[today + 1][0] != null) {
                        lession.setText("Next: " + lessons[today + 1][0]);
                    } else {
                        lession.setText("Next: " + lessons[2][0]);
                    }
                }
            }
            for (int i = 0; i < int_time.length; i++) {
                for (int c = 0; c < 20; c++) {
                }
            }
        }catch (ArrayIndexOutOfBoundsException e){
            lession.setText("Next Week: " + lessons[2][0]);
        }
    }
}
