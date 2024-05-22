package com.fit2081.assignment3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fit2081.assignment3.Activity.MapsActivity;
import com.fit2081.assignment3.Data.EventCategory;
import com.fit2081.assignment3.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private List<EventCategory> categoryList = new ArrayList<>();

    private final int HEADER_TYPE = 0;// Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
    private final int ITEM_TYPE = 1;// Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
    private Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == HEADER_TYPE) {  // Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_header, parent, false);// Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
        } else {//Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_dashboard_event, parent, false);// Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
        }
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (categoryList.isEmpty()){
            Log.d("Activity", "Empty List");
            holder.textViewCategoryId.setText("None");  // Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
            holder.textViewName.setText("None");//Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
            holder.textViewCount.setText("None");//Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
            holder.isActive.setText("None");//Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
        }
        else{
            if (position == 0) {
            holder.textViewCategoryId.setText("Id");  // Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
            holder.textViewName.setText("Name");//Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
            holder.textViewCount.setText("Event Count");//Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
            holder.isActive.setText("Active");//Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
            }
            else {
                EventCategory currentCategory = categoryList.get(position);
                holder.textViewCategoryId.setText(currentCategory.getName());  // Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
                holder.textViewName.setText(currentCategory.getEventCategoryId());//Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
                holder.textViewCount.setText(Integer.toString(currentCategory.getCategoryCount()));//Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
                holder.isActive.setText(currentCategory.getIsActive() ? "Active" : "Not Active");//Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, MapsActivity.class);
                        intent.putExtra("location", currentCategory.getLocationName());
                        context.startActivity(intent);
                        Log.d("Activity", "item event on click");
                    }
                });
            }
        }


    }

    @Override
    public int getItemCount() {//Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
        return categoryList.size()+ 1; //Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
    }

    @Override
    public int getItemViewType(int position) { //Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
        if (position == 0) {//Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
            return HEADER_TYPE;//Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
        } else {
            return ITEM_TYPE;//Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
        }
    }

    public void setCategories(List<EventCategory> categories) {
        this.categoryList = categories;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {//Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
        public TextView textViewCategoryId; //Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
        public TextView textViewCount;//Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
        public TextView textViewName;//Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
        public TextView isActive;//Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCategoryId = itemView.findViewById(R.id.card_category_id);//Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
            textViewCount = itemView.findViewById(R.id.card_event_count);//Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
            textViewName = itemView.findViewById(R.id.card_name);//Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
            isActive = itemView.findViewById(R.id.card_is_active);//Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
        }
    }
}
