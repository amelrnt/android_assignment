package com.fit2081.assignment3.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fit2081.assignment3.Data.EventCategory;
import com.fit2081.assignment3.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private final List<EventCategory> categoryList;

    private final int HEADER_TYPE = 0;// Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
    private final int ITEM_TYPE = 1;// Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project

    public CategoryAdapter(List<EventCategory> categoryList) {
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == HEADER_TYPE) {  // Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_header, parent, false);// Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
        } else {//Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_dashboard_event, parent, false);// Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == 0) {
//            TODO: fix instead of comment
            holder.textViewCategoryId.setText("Id");  // Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
            holder.textViewName.setText("Name");//Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
            holder.textViewCount.setText("Event Count");//Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
            holder.isActive.setText("Active");//Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
        } else {
            EventCategory category = categoryList.get(position - 1); // Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
            //            TODO: fix instead of comment
            holder.textViewCategoryId.setText(category.getEventCategoryId());//Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
            holder.textViewName.setText(category.getName());//Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
            holder.textViewCount.setText(String.valueOf(category.getCategoryCount()));//Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
            holder.isActive.setText(category.getIsActive() ? "Yes" : "No");//Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
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
