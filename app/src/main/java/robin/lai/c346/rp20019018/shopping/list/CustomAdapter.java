package robin.lai.c346.rp20019018.shopping.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Item> ItemList;

    public CustomAdapter(Context context, int resource, ArrayList<Item> objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        ItemList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvName = rowView.findViewById(R.id.textViewName);
        TextView tvCat = rowView.findViewById(R.id.textViewCategory);
        ImageView image = rowView.findViewById(R.id.imageView);
        RatingBar rb = rowView.findViewById(R.id.ratingBarList);

        // Obtain the Android Version information based on the position
        Item currentVersion = ItemList.get(position);

        // Set values to the TextView to display the corresponding information
        tvName.setText(currentVersion.getName());
        tvCat.setText(currentVersion.getCategory());
        rb.setRating(currentVersion.getRating());
        if(currentVersion.getCategory().equalsIgnoreCase("Others"))
        {
            image.setImageResource(R.drawable.none);
        }
        else if(currentVersion.getCategory().equalsIgnoreCase("Alcohol"))
        {
            image.setImageResource(R.drawable.alcohol);
        }
        else if(currentVersion.getCategory().equalsIgnoreCase("Baby"))
        {
            image.setImageResource(R.drawable.baby);
        }
        else if(currentVersion.getCategory().equalsIgnoreCase("Beverages"))
        {
            image.setImageResource(R.drawable.beverage);
        }
        else if(currentVersion.getCategory().equalsIgnoreCase("Bakery"))
        {
            image.setImageResource(R.drawable.bread);
        }
        else if(currentVersion.getCategory().equalsIgnoreCase("Breakfast"))
        {
            image.setImageResource(R.drawable.cereal);
        }
        else if(currentVersion.getCategory().equalsIgnoreCase("Canned Goods"))
        {
            image.setImageResource(R.drawable.canned);
        }
        else if(currentVersion.getCategory().equalsIgnoreCase("Snacks"))
        {
            image.setImageResource(R.drawable.cookies);
        }
        else if(currentVersion.getCategory().equalsIgnoreCase("Dairy"))
        {
            image.setImageResource(R.drawable.dairy);
        }
        else if(currentVersion.getCategory().equalsIgnoreCase("Deli"))
        {
            image.setImageResource(R.drawable.deli);
        }
        else if(currentVersion.getCategory().equalsIgnoreCase("Eggs"))
        {
            image.setImageResource(R.drawable.eggs);
        }
        else if(currentVersion.getCategory().equalsIgnoreCase("Flowers"))
        {
            image.setImageResource(R.drawable.flowers);
        }
        else if(currentVersion.getCategory().equalsIgnoreCase("Frozen Foods"))
        {
            image.setImageResource(R.drawable.frozen);
        }
        else if(currentVersion.getCategory().equalsIgnoreCase("Fruits and Vegetables"))
        {
            image.setImageResource(R.drawable.produce);
        }
        else if(currentVersion.getCategory().equalsIgnoreCase("Grains,Pasta"))
        {
            image.setImageResource(R.drawable.pasta);
        }
        else if(currentVersion.getCategory().equalsIgnoreCase("Meat and Seafood"))
        {
            image.setImageResource(R.drawable.meat);
        }
        else if(currentVersion.getCategory().equalsIgnoreCase("Miscellaneous"))
        {
            image.setImageResource(R.drawable.miscellaneous);
        }
        else if(currentVersion.getCategory().equalsIgnoreCase("Paper Products"))
        {
            image.setImageResource(R.drawable.paper);
        }
        else if(currentVersion.getCategory().equalsIgnoreCase("Powdered Drinks"))
        {
            image.setImageResource(R.drawable.powdered);
        }
        else if(currentVersion.getCategory().equalsIgnoreCase("Cleaning Supplies"))
        {
            image.setImageResource(R.drawable.cleaning);
        }
        else if(currentVersion.getCategory().equalsIgnoreCase("Hygiene"))
        {
            image.setImageResource(R.drawable.health);
        }
        else if(currentVersion.getCategory().equalsIgnoreCase("Pet Care"))
        {
            image.setImageResource(R.drawable.pet);
        }
        else if(currentVersion.getCategory().equalsIgnoreCase("Pharmacy"))
        {
            image.setImageResource(R.drawable.pharmacy);
        }
        else if(currentVersion.getCategory().equalsIgnoreCase("Tobacco"))
        {
            image.setImageResource(R.drawable.tobacco);
        }



        return rowView;
    }

}