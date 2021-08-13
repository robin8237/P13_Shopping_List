package robin.lai.c346.rp20019018.shopping.list;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    EditText name,desc;
    Spinner spinEdit;
    RatingBar rateEdit;
    Button cancel,update,remove;
    Item data;
    String addCat = "";
    int spinPos = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        name = findViewById(R.id.editTextNameEdit);
        desc = findViewById(R.id.editTextDescEdit);
        spinEdit = findViewById(R.id.spinnerEdit);
        rateEdit = findViewById(R.id.ratingBarEdit);
        cancel = findViewById(R.id.btncan);
        update = findViewById(R.id.buttonUpd);
        remove = findViewById(R.id.buttonRem);

        DBHelper dbh = new DBHelper(EditActivity.this);

        Intent i = getIntent();
        data = (Item) i.getSerializableExtra("data");

        name.setText(data.getName());
        desc.setText(data.getDescription());
        rateEdit.setRating(data.getRating());
        switch (data.getCategory())
        {
            case "Others": spinPos = 0;
                break;
            case "Alcohol": spinPos = 1;
                break;
            case "Baby": spinPos = 2;
                break;
            case "Beverages": spinPos = 3;
                break;
            case "Bakery": spinPos = 4;
                break;
            case "Breakfast": spinPos = 5;
                break;
            case "Canned Goods": spinPos = 6;
                break;
            case "Snacks": spinPos = 7;
                break;
            case "Dairy": spinPos = 8;
                break;
            case "Deli": spinPos = 9;
                break;
            case "Eggs": spinPos = 10;
                break;
            case "Flowers": spinPos = 11;
                break;
            case "Frozen Foods": spinPos = 12;
                break;
            case "Fruits and Vegetables": spinPos = 13;
                break;
            case "Grains,Pasta": spinPos = 14;
                break;
            case "Meat and Seafood": spinPos = 15;
                break;
            case "Miscellaneous": spinPos = 16;
                break;
            case "Paper Products": spinPos = 17;
                break;
            case "Powdered Drinks": spinPos = 18;
                break;
            case "Cleaning Supplies": spinPos = 19;
                break;
            case "Hygiene": spinPos = 20;
                break;
            case "Pet Care": spinPos = 21;
                break;
            case "Pharmacy": spinPos = 22;
                break;
            case "Tobacco": spinPos = 23;
            break;

        }

        spinEdit.setSelection(spinPos);

        spinEdit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(EditActivity.this);

                myBuilder.setTitle("Warning");
                myBuilder.setMessage("Are you sure you want to update " + data.getName() + "?");
                myBuilder.setCancelable(false);

                myBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(name.getText().toString().trim().length() != 0 && desc.getText().toString().trim().length() != 0)
                        {
                            String editName = name.getText().toString().trim();
                            String editDesc = desc.getText().toString().trim();
                            float editRate = rateEdit.getRating();
                            data.setCategory(addCat);
                            data.setDescription(editDesc);
                            data.setName(editName);
                            data.setRating(editRate);
                            dbh.updateItem(data);
                            dbh.close();
                            Toast.makeText(EditActivity.this,"Item Updated",Toast.LENGTH_SHORT).show();
                            Intent j = new Intent(EditActivity.this,MainActivity.class);
                            startActivity(j);
                        }
                        else
                        {
                            Toast.makeText(EditActivity.this,"Data Incomplete",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                myBuilder.setNegativeButton("No", null);

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(EditActivity.this);

                myBuilder.setTitle("Warning");
                myBuilder.setMessage("Are you sure you want to delete " + data.getName() + "?");
                myBuilder.setCancelable(false);

                myBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbh.deleteItem(data.getId());
                        Toast.makeText(EditActivity.this,"Item Deleted",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

                myBuilder.setNegativeButton("No", null);

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(EditActivity.this);

                myBuilder.setTitle("Warning");
                myBuilder.setMessage("Are you sure you want to go back?");
                myBuilder.setCancelable(false);

                myBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent j = new Intent(EditActivity.this,MainActivity.class);
                        startActivity(j);
                    }
                });

                myBuilder.setNegativeButton("No", null);

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

    }
}