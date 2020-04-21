package com.example.gmailrecyclerview;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<EmailItemModel> items;
    EmailItemAdapter adapter;
    boolean check;
    EditText editFind;
    Button btnFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        check = false;
        editFind = findViewById(R.id.text_find);
        btnFavorite = findViewById(R.id.btn_favorite);
        editFind.setInputType(InputType.TYPE_NULL);

        editFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editFind.setInputType(InputType.TYPE_CLASS_TEXT);
                editFind.setFocusableInTouchMode(true);
            }
        });

        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<EmailItemModel> filteredList = new ArrayList<>();
                if (!check) {
                    for (EmailItemModel itemModel : items)
                        if (itemModel.isFavorite == true) {
                            filteredList.add(itemModel);
                        }
                }
                else {
                    filteredList.addAll(items);
                }
                adapter.filterList((ArrayList<EmailItemModel>) filteredList);
                check = !check;
            }
        });

        editFind.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        items = new ArrayList<>();
        items.add(new EmailItemModel("Miss Jessica", "Quo hic rem similique eaque nihil error", "Dlorem aut impedit et nostrum fugared", "9:16 AM"));
        items.add(new EmailItemModel("Vinesr", "Sint praseherst dicerat netoer coersda", "Dismissiosa geserlw oderslr karodg", "11:45 AM"));
        items.add(new EmailItemModel("Polers", "Accusanrters dertormint haerlit gormernts noere", "Ametdersct toerminer hartme joinercun", "12:00 AM"));
        items.add(new EmailItemModel("Paul", "Molerdeimor hortile minitertion horigiter hinterral", "Comerts rotnet knowlerist", "2:00 PM"));
        items.add(new EmailItemModel("Phierosi", "Volumnitersi motinter onlist culumber", "Metroer notifiver carline homsaune", "4:50 PM"));
        items.add(new EmailItemModel("Willy Linare", "Suberst homist", "Multifical udr gomeiter", "10:00 PM"));
        items.add(new EmailItemModel("Fille Milticy", "Milder tiror haticonlate", "Citifuneris smlilor", "12:00 PM"));

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new EmailItemAdapter(items);
        recyclerView.setAdapter(adapter);
    }

    private void filter(String text)  {
        List<EmailItemModel> filteredList = new ArrayList<>();

        if (text == null || text.length() < 3) {
            filteredList.addAll(items);
        }
        else {
            String filterPattern = text.toLowerCase().trim();

            for (EmailItemModel itemModel: items)  {
                if (itemModel.getName().toLowerCase().contains(filterPattern))  {
                    filteredList.add(itemModel);
                }
                if (itemModel.getContent().toLowerCase().contains(filterPattern))  {
                    filteredList.add(itemModel);
                }
                if (itemModel.getSubject().toLowerCase().contains(filterPattern))  {
                    filteredList.add(itemModel);
                }
            }
        }
        adapter.filterList((ArrayList<EmailItemModel>) filteredList);
    }
}
