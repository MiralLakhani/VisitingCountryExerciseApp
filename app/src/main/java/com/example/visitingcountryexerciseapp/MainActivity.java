package com.example.visitingcountryexerciseapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Spinner country, city;
    TextView expense, days, selectedDays, total,person,selectedPerson,finalMessage;
    Button visit, remove,update;
    ListView selectedCities;
    SeekBar seekDays,seekPerson;
    String counties[] = {"Canada", "France", "Italy", "Germany", "India", "Turkey", "England"};
    ArrayList<City> cityList = new ArrayList<>();
    ArrayList<City>tempCity=new ArrayList<>();
    ArrayList<SelectedCity>visitedCities=new ArrayList<>();
    ArrayList<String>names=new ArrayList<>();

    public static String theCity;
    public static double totalAmount=0;
    public  static int totalDays,totalPerson;
    public  static int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillData();

        country = findViewById(R.id.spCountry);
        city = findViewById(R.id.spcity);
        expense = findViewById(R.id.txvExpense);
        days = findViewById(R.id.txvDays);
        selectedDays = findViewById(R.id.txvSellDays);
        total = findViewById(R.id.txvTotal);
        visit = findViewById(R.id.btnVisit);
        remove = findViewById(R.id.btnRemove);
        selectedCities = findViewById(R.id.listCity);
        seekDays = findViewById(R.id.seekDays);
        person=findViewById(R.id.txvPerson);
        selectedPerson=findViewById(R.id.txvUpdatePerson);
        update=findViewById(R.id.btnUpdate);
        seekPerson=findViewById(R.id.seekPerson);
        finalMessage=findViewById(R.id.txvFinal);

        //fill the first spinner by creating an array adapter of countries
        ArrayAdapter aa = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, counties);
        country.setAdapter(aa);

        country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String cityName[]=getCities(counties[position]);
                ArrayAdapter aa2=new ArrayAdapter(MainActivity.this,R.layout.support_simple_spinner_dropdown_item,cityName);
                city.setAdapter(aa2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                expense.setText(String.valueOf(tempCity.get(position).getExpense()));
                theCity=tempCity.get(position).getCity();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        seekDays.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                days.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        seekPerson.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                person.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int noDays= Integer.parseInt(days.getText().toString());
                int noPerson=Integer.parseInt(person.getText().toString());
                double dailyExpense=Double.parseDouble(expense.getText().toString());
                double personExpense=noPerson*dailyExpense;
                double amount=noDays*personExpense;
                selectedDays.setText(String.valueOf(noDays));
                selectedPerson.setText(String.valueOf(noPerson));
                visitedCities.add(new SelectedCity(theCity,noDays,amount,noPerson));

                names.add(theCity);
                totalAmount+=amount;
                totalDays+=noDays;
                totalPerson+=noPerson;
                ArrayAdapter aa3=new ArrayAdapter(MainActivity.this,R.layout.support_simple_spinner_dropdown_item,names);
                selectedCities.setAdapter(aa3);
                total.setText(String.valueOf(totalAmount));
                selectedDays.setText(String.valueOf(totalDays));
                selectedPerson.setText(String.valueOf(totalPerson));
            }
        });
        selectedCities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedDays.setText(String.valueOf(visitedCities.get(position).getDays()));
                selectedPerson.setText(String.valueOf(visitedCities.get(position).getPerson()));
                index=position;
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalAmount-=visitedCities.get(index).getTotal();
                totalDays-=visitedCities.get(index).getDays();
                totalPerson-=visitedCities.get(index).getPerson();
                names.remove(index);
                visitedCities.remove(index);
                ArrayAdapter aa3=new ArrayAdapter(MainActivity.this,R.layout.support_simple_spinner_dropdown_item,names);
                selectedCities.setAdapter(aa3);
                selectedDays.setText(String.valueOf(totalDays));
                selectedPerson.setText(String.valueOf(totalPerson));
                total.setText(String.valueOf(totalAmount));
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total.setText(String.valueOf(totalAmount));
                finalMessage.setText(String.valueOf("Total Days are: "+totalDays+", Total Persons are: "+totalPerson));
                Toast.makeText(getBaseContext(),"Thank you for visiting..! Your Total is: "+totalAmount,Toast.LENGTH_LONG).show();
            }
        });

    }

    //filling the data of city
    public void fillData() {
        cityList.add(new City(counties[0], "Toronto", 200));
        cityList.add(new City(counties[0], "Montreal", 150));
        cityList.add(new City(counties[0], "Vancouver", 240));
        cityList.add(new City(counties[1], "Paris", 230));
        cityList.add(new City(counties[1], "Marseille", 200));
        cityList.add(new City(counties[2], "Rome", 220));
        cityList.add(new City(counties[2], "Pizza", 280));
        cityList.add(new City(counties[2], "Vanice", 180));
        cityList.add(new City(counties[3], "Munich", 150));
        cityList.add(new City(counties[3], "Humburg", 100));
        cityList.add(new City(counties[3], "Berlin", 300));
        cityList.add(new City(counties[4], "Kerla", 200));
        cityList.add(new City(counties[4], "Sikkim", 240));
        cityList.add(new City(counties[4], "Leh-Ladakh", 270));
        cityList.add(new City(counties[5], "Istanbul", 160));
        cityList.add(new City(counties[5], "Antalya", 260));
        cityList.add(new City(counties[5], "Izmir", 290));
        cityList.add(new City(counties[6], "London", 350));
        cityList.add(new City(counties[6], "Cambridge", 440));
        cityList.add(new City(counties[6], "Manchester", 500));

    }
    //receive a country name and return and
    // fill the temp city and return array of city names
    public String[] getCities(String countryName){
        String cityname[];
        tempCity.clear();
        for(City ct:cityList)
            if(ct.getCountry().equals(countryName))
                tempCity.add(ct);
        cityname=new String[tempCity.size()];
        for(int i=0;i<tempCity.size();i++)
            cityname[i]=tempCity.get(i).getCity();
        return cityname;
    }
}