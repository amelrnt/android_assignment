    package com.fit2081.assignment3;
    import android.os.Bundle;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import androidx.annotation.NonNull;
    import androidx.fragment.app.Fragment;
    import androidx.lifecycle.Observer;
    import androidx.lifecycle.ViewModelProvider;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;
    import java.util.ArrayList;
    import java.util.List;

    import com.fit2081.assignment3.Data.EventCategory;

    public class FragmentListCategory extends Fragment {

        private RecyclerView recyclerView;
        private MyCategoryAdapter categoryAdapter;
        private List<EventCategory> categoryList = new ArrayList<>();
        private EventViewModel eventViewModel;

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_list_category, container, false);

            recyclerView = view.findViewById(R.id.recycler_view_categories);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setHasFixedSize(true);

            categoryAdapter = new MyCategoryAdapter(categoryList);
            recyclerView.setAdapter(categoryAdapter);

//            loadCategoriesFromPreferences();
            loadCategoryFromRoom();

            return view;
        }

        private void loadCategoryFromRoom(){
            eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);
            // Observe data changes
            eventViewModel.getAllCategories().observe(getViewLifecycleOwner(), new Observer<List<EventCategory>>() {
                @Override
                public void onChanged(List<EventCategory> eventCategories) {
                    categoryList = eventCategories;
                }
            });

            categoryAdapter.notifyDataSetChanged();
        }

    }
