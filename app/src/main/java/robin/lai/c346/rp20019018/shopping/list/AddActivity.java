package robin.lai.c346.rp20019018.shopping.list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    Spinner spinAdd;
    EditText etName,etDesc;
    RatingBar rbAdd;
    Button btnAdd;
    String addCat = "";
    DBHelper dbh = new DBHelper(AddActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        spinAdd = findViewById(R.id.spinnerAdd);
        etName = findViewById(R.id.editTextAdd);
        etDesc = findViewById(R.id.editTextDesc);
        rbAdd = findViewById(R.id.ratingBar);
        btnAdd = findViewById(R.id.btnAdd);

        spinAdd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0: addCat = "Others";
                        break;
                    case 1: addCat = "Alcohol";
                        break;
                    case 2: addCat = "Baby";
                        break;
                    case 3: addCat = "Beverages";
                        break;
                    case 4: addCat = "Bakery";
                        break;
                    case 5: addCat = "Breakfast";
                        break;
                    case 6: addCat = "Canned Goods";
                        break;
                    case 7: addCat = "Snacks";
                        break;
                    case 8: addCat = "Dairy";
                        break;
                    case 9: addCat = "Deli";
                        break;
                    case 10: addCat = "Eggs";
                        break;
                    case 11: addCat = "Flowers";
                        break;
                    case 12: addCat = "Frozen Foods";
                        break;
                    case 13: addCat = "Fruits and Vegetables";
                        break;
                    case 14: addCat = "Grains,Pasta";
                        break;
                    case 15: addCat = "Meat and Seafood";
                        break;
                    case 16: addCat = "Miscellaneous";
                        break;
                    case 17: addCat = "Paper Products";
                        break;
                    case 18: addCat = "Powdered Drinks";
                        break;
                    case 19: addCat = "Cleaning Supplies";
                        break;
                    case 20: addCat = "Hygiene";
                        break;
                    case 21: addCat = "Pet Care";
                        break;
                    case 22: addCat = "Pharmacy";
                        break;
                    case 23: addCat = "Tobacco";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etName.getText().toString().trim().length() != 0 && etDesc.getText().toString().trim().length() != 0)
                {
                   String name = etName.getText().toString().trim();
                   String desc = etDesc.getText().toString().trim();
                   Float rating = rbAdd.getRating();
                   long result = dbh.insertItem(name,desc,addCat,rating);
                   if(result != -1)
                   {
                       Toast.makeText(AddActivity.this,"Item Inserted",Toast.LENGTH_SHORT).show();
                       Intent j = new Intent(AddActivity.this,MainActivity.class);
                       startActivity(j);
                   }
                   else
                   {
                       Toast.makeText(AddActivity.this,"Insert Failed",Toast.LENGTH_SHORT).show();
                   }
                }
                else
                {
                    Toast.makeText(AddActivity.this,"Data incomplete",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}