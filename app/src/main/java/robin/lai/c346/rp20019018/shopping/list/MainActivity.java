package robin.lai.c346.rp20019018.shopping.list;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    EditText etSearch;
    ListView lv;
    Button addNew;
    ArrayList<Item> itemArray;
    CustomAdapter ca;
    DBHelper dbh = new DBHelper(MainActivity.this);
    ArrayList<Item> tempList;
    CustomAdapter tempCa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSearch = findViewById(R.id.etSearch);
        lv = findViewById(R.id.listView);
        addNew = findViewById(R.id.buttonAdd);
        itemArray = new ArrayList<Item>();
        ca = new CustomAdapter(this,R.layout.row,itemArray);
        lv.setAdapter(ca);

        itemArray.clear();
        itemArray.addAll(dbh.getAllItem());
        ca.notifyDataSetChanged();

        tempList = new ArrayList<Item>();
        tempCa = new CustomAdapter(this,R.layout.row,tempList);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tempList.clear();

                for(int i = 0; i < itemArray.size();i++)
                {
                    if(itemArray.get(i).getName().toLowerCase().contains(s.toString().toLowerCase()))
                    {
                        tempList.add(itemArray.get(i));
                    }
                }
                lv.setAdapter(tempCa);
                tempCa.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,AddActivity.class);
                startActivity(i);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Item data = itemArray.get(position);
                Intent i = new Intent(MainActivity.this,
                        EditActivity.class);
                i.putExtra("data",data);
                startActivity(i);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        itemArray.clear();

        itemArray.addAll(dbh.getAllItem());

        ca.notifyDataSetChanged();

    }
}