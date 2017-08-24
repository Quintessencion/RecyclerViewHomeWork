package com.example.recyclerviewhomework;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ToggleButton;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //Fields
    ToggleButton toggleButton;
    ImageButton btnListUp, btnListDown;

    MyAdapter myAdapter;
    RecyclerView recyclerView;
    ArrayList<Pole> list;

    LinearLayoutManager verticalLLM;
    LinearLayoutManager horizontalLLM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        if (savedInstanceState != null) {
            list = savedInstanceState.getParcelableArrayList("listPoles");
        } else {
            list = new ArrayList<>();
            Observable.from(initData())
                    .toList()
                    .map(poles -> list.addAll(poles))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
        }

        myAdapter = new MyAdapter(list);
//        MyAdapter myAdapter = new MyAdapter(initData());
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        verticalLLM = new LinearLayoutManager(this);
        horizontalLLM = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(verticalLLM);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(myAdapter);

        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (!isChecked) {
                myAdapter.isFilter = false;
                recyclerView.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();
            } else if (isChecked) {
                myAdapter.isFilter = true;
                recyclerView.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();
            }
        });

        btnListUp = (ImageButton) findViewById(R.id.btnListUp);
        btnListUp.setOnClickListener(v -> myAdapter.filterListUp());
        btnListDown = (ImageButton) findViewById(R.id.btnListDown);
        btnListDown.setOnClickListener(v -> myAdapter.filterListDown());

    }

    private void initViews() {

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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("listPoles", list);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        list = savedInstanceState.getParcelableArrayList("listPoles");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
//        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            recyclerView.setLayoutManager(horizontalLLM);
//        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
//            recyclerView.setLayoutManager(verticalLLM);
//        }
    }

    private List<Pole> initData() {
        List<Pole> list = new ArrayList<>();
        list.add(new Pole(R.drawable.icon_car, R.drawable.group_copy_8, R.drawable.time,
                "ОСАГО", "EEE0858555888", "Nissan Qashqai",
                "02.01.2016", 625));

        list.add(new Pole(R.drawable.icon_key, R.drawable.group_copy_8, R.drawable.time,
                "Имущество", "EAE0555555888", "г.Москва,ул.Дмитриевского,д.5б кв55",
                "01.01.2015", 1098));//958

        list.add(new Pole(R.drawable.icon_car_kasko, R.drawable.group_copy_8, R.drawable.time,
                "КАСКО", "EEE0454555555", "Nissan Qashqai",
                "25.01.2016", 581));

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
                "Имущество", "OAO0123555978", "Красные Орлы. Площадь Добрых традиций. Дом 13А.",
                "01.01.2010", 2928));

        list.add(new Pole(R.drawable.icon_key, R.drawable.group_copy_8, R.drawable.time,
                "Имущество", "OOOOAAAA666", "Магазин \"СОСТОЯТЕЛЬНЫЕ КРОТЫ\"",
                "01.01.2013", 1702));

        return list;
    }
}
