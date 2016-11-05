package com.buechstabet.arlendai.stundenplan2;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class DayChooser extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;
    final int max_array_length = 10;
    private ViewPager mViewPager;
    String[][] lessions,times;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_chooser);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }
    public ArrayAdapter getAdapter(int position, Context context) {
        int errors=0;
        String[] list = new String[10];
        String[] final_list;

        intitLessenAndTime();

        for(int i = 0;i<max_array_length;i++){
            try{
                list[i] = times[position][i] + "\t\t" + lessions[position][i];
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
        adapter = new ArrayAdapter(context,android.R.layout.simple_list_item_1,final_list);
        return adapter;
    }

    private void intitLessenAndTime() {

        String[] mo_lessens,mo_time,tu_lessens,tu_time,we_lessens,we_time,th_lessens,th_time,fr_lessens,fr_time;

        lessions = new String[8][10];
        times = new String[8][10];

        mo_lessens = new String[]{
                "Grundlagen Elektronik",
                "Grundlagen Elektronik",
                "Mittag",
                "Grundlagen Elektronik",
                "Grundlagen Elektronik"};

        for(int i=0;i<mo_lessens.length;i++){
            lessions[2][i]=mo_lessens[i];
        }

        tu_lessens = new String[]{
                "Grundlagen Elektronik",
                "Grundlagen Elektronik",
                "Mittag",
                "Grundlagen Elektronik",
                "Grundlagen Elektronik"};

        for(int i=0;i<tu_lessens.length;i++){
            lessions[3][i]=tu_lessens[i];
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
            lessions[4][i]=we_lessens[i];
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
            lessions[5][i]=th_lessens[i];
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
            lessions[6][i]=fr_lessens[i];
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_day_chooser, menu);
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
            Intent intent = new Intent(DayChooser.this,MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        else if (id == R.id.menu_tests) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {


        public static PlaceholderFragment newInstance(int sectionNumber) {

            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle extras = new Bundle();
            extras.putInt("section_number", sectionNumber);
            fragment.setArguments(extras);

            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_day_chooser, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);

            int position = getArguments().getInt("section_number")+1;

            DayChooser dayChooser = new DayChooser();
            String[] days = new String[]{"Montag","Dienstag","Mittwoch","Donnerstag","Freitag"};

            ArrayAdapter<String> adapter = dayChooser.getAdapter(position,getActivity());
            ListView day_list = (ListView)rootView.findViewById(R.id.fragment_list);
            day_list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            textView.setText(days[position-2]);

            return rootView;
        }
}

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Montag";
                case 1:
                    return "Dienstag";
                case 2:
                    return "Freitag";
            }
            return null;
        }
    }

}
