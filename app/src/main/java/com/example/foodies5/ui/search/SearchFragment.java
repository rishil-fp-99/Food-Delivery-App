package com.example.foodies5.ui.search;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodies5.R;
import com.example.foodies5.dataclasses.DataSearchAdapter;
import com.example.foodies5.dataclasses.Data_to_be_searched;
import com.example.foodies5.dataclasses.SearchViewAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchFragment extends Fragment {


    private SearchViewModel dashboardViewModel;
    View root;
    RecyclerView recyclerView;
    CardView cardView;
    SearchView searchView;
    DataSearchAdapter dataSearchAdapter;
    DatabaseReference databaseReference;
    ArrayList<Data_to_be_searched> alist;
    private static final String TAG = "SearchFragment";

    FirebaseRecyclerOptions<Data_to_be_searched> options;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
         root = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView=root.findViewById(R.id.recycler_view);
        searchView=root.findViewById(R.id.search_view);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Restaurants");
        FirebaseRecyclerOptions<Data_to_be_searched> options=new FirebaseRecyclerOptions.Builder<Data_to_be_searched>()
                .setQuery(databaseReference,Data_to_be_searched.class).build();
    //    dataSearchAdapter=new DataSearchAdapter(options,root.getContext());


//in search view in the last we are also adding speech recongnition

     //   Log.d("SearchFragment",dataSearchAdapter.rest.toString());
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext(),LinearLayoutManager.VERTICAL,false));

        cardView=root.findViewById(R.id.cardview);
        cardView.setVisibility(View.GONE);
        cardView.setClickable(true);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
//        options=new FirebaseRecyclerOptions.Builder<Data_to_be_searched>()
//                .setQuery(databaseReference,Data_to_be_searched.class).build();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    alist=new ArrayList<Data_to_be_searched>();
                    for(DataSnapshot dataSnapshot:snapshot.getChildren())
                    {
                       alist.add(dataSnapshot.getValue(Data_to_be_searched.class));
                        Toast.makeText(root.getContext(), ""+alist.toString(), Toast.LENGTH_SHORT).show();
                       Log.d("SearchFragment",alist.toString());
                    }
                    for(int i=0;i<alist.size();i++)
                    {
                        Log.d(TAG,alist.get(i).getWeb_url());
                    }

                   dataSearchAdapter=new DataSearchAdapter(root.getContext(),alist,getActivity());
                    recyclerView.setAdapter(dataSearchAdapter);
                }
                if(searchView!=null)
                {
                    Toast.makeText(root.getContext(), "searchview is not null", Toast.LENGTH_SHORT).show();
                    searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if(hasFocus)
                            {
                                Toast.makeText(root.getContext(), ""+hasFocus, Toast.LENGTH_SHORT).show();
                                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                    @Override
                                    public boolean onQueryTextSubmit(String query) {
                                        return  true;
                                    }

                                    @Override
                                    public boolean onQueryTextChange(String newText) {
                                        Toast.makeText(root.getContext(), ""+newText, Toast.LENGTH_SHORT).show();
                                        cardView.setVisibility(View.VISIBLE);
                                        perform_search(newText);
                                        return true;
                                    }
                                });
                            }
                            else{
                                cardView.setVisibility(View.GONE);
                            }
                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(root.getContext(), "Error:"+error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public void onStop() {
        super.onStop();
//        dataSearchAdapter.stopListening();
    //    Log.d("SearchFragment",dataSearchAdapter.rest.toString());
    }

    public void perform_search(String query)
    {
        ArrayList<Data_to_be_searched> filterarrayList=new ArrayList<>();
            for (Data_to_be_searched data_to_be_searched:alist)
            {
                if(data_to_be_searched.getName().toLowerCase().contains(query.toLowerCase()))
                {
                    filterarrayList.add(data_to_be_searched);
                    Log.i("SearchFragment",filterarrayList.toString());
                }
            }
             dataSearchAdapter=new DataSearchAdapter(root.getContext(),filterarrayList,getActivity());

                dataSearchAdapter.notifyDataSetChanged();
            recyclerView.setAdapter(dataSearchAdapter);

    }


}










