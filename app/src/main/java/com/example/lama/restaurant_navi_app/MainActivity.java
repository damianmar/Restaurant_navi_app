package com.example.lama.restaurant_navi_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button setNavigationButton;
    String deliveryAddress = "Wroclaw";
    String[] initialValues = new String[]{"Olawska 12, wroclaw", "curie-sklodowskiej 12, wroclaw", "Stysia 12, wroclaw",
            "pl. Jana Pawla II 12, wroclaw", "pl. Strzegomski 12, Wroclaw", "arbuzowa 12, Wrocaw",
            "Grunwaldzka 12, Wroclaw", "Mila 12, Wroclaw", "Fiolkowa 12, Wroclaw",
            "Warszawska 12, Wroclaw", "Lubuska 12, Wroclaw", "Dluga 12, Wroclaw", "Fransucksa 12, Wroclaw"};
//Tu jest lista inicjalizacyjna. Musisz to zamienic na pobierane z serwera.
// W przypadku klikniecia w przycisk wyznacz trase trzeba tez wysylac informacje na serwer, ze dane zlecenie jest wykonywane

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setNavigationButton = (Button) findViewById(R.id.setNavigationButton);


        View.OnClickListener onClickNavigateButton = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + Uri.encode(deliveryAddress));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        };
        setNavigationButton.setOnClickListener(onClickNavigateButton);


        final ListView listview = (ListView) findViewById(R.id.orderList);
        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < initialValues.length; ++i) {
            list.add(initialValues[i]);
        }
        final ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    final int position, long id) {
                deliveryAddress = initialValues[position];
            }
        });
    }

}
