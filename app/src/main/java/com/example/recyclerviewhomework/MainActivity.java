package com.example.recyclerviewhomework;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setLogo(R.mipmap.icon_home);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        MyAdapter myAdapter = new MyAdapter(initData());
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

        }
        return super.onOptionsItemSelected(item);
    }

    private List<Pole> initData() {
        List<Pole> list = new ArrayList<>();
        list.add(new Pole(R.drawable.icon_car, R.drawable.group_copy_8, R.drawable.time,
                "ОСАГО", "EEE0858555888", "Nissan Qashqai",
                "02.01.2016", 619));

        list.add(new Pole(R.drawable.icon_key, R.drawable.group_copy_8, R.drawable.time,
                "Имущество", "EAE0555555888", "г.Москва,ул.Дмитриевского,д.5б кв55",
                "01.01.2015", 1098));//958

        list.add(new Pole(R.drawable.icon_car_kasko, R.drawable.group_copy_8, R.drawable.time,
                "КАСКО", "EEE0454555555", "Nissan Qashqai",
                "25.01.2016", 573));

        list.add(new Pole(R.drawable.icon_medec, R.drawable.group_copy_8, R.drawable.time,
                "Медецинское страхование", "10088K5567895", "Снегирев Андрей Ларисович",
                "25.01.2016", 600));

        list.add(new Pole(R.drawable.icon_car, R.drawable.group_copy_8, R.drawable.time,
                "Зеленая карта", "EFE0868325888", "ВАЗ 2017",
                "02.01.2017", 732));

        list.add(new Pole(R.drawable.icon_car_kasko, R.drawable.group_copy_8, R.drawable.time,
                "КАСКО", "AEE0454554376", "AUDI Q7",
                "01.03.2016", 670));

        list.add(new Pole(R.drawable.icon_medec, R.drawable.group_copy_8, R.drawable.time,
                "Медецинское страхование", "1088F55567873", "Жилина Лариса Васильевна",
                "13.05.1980", 13630));

        list.add(new Pole(R.drawable.icon_car_kasko, R.drawable.group_copy_8, R.drawable.time,
                "КАСКО", "AEA0450455401", "Bentley Flying Spur W12",
                "05.05.2017", 130));

        list.add(new Pole(R.drawable.icon_key, R.drawable.group_copy_8, R.drawable.time,
                "Имущество", "OAO0123555978", "Деревня Красные Орлы. Площадь Добрых традиций. Дом 13А.",
                "01.01.2010", 2928));

        list.add(new Pole(R.drawable.icon_key, R.drawable.group_copy_8, R.drawable.time,
                "Имущество", "OOOOAAAA666", "Продовольственный магазин \"СОСТОЯТЕЛЬНЫЕ КРОТЫ\"",
                "01.01.2013", 1716));

        return list;
    }
}
