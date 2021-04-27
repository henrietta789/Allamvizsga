package com.example.pixi2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class FilterListFragment extends Fragment {

    View v;
    private RecyclerView myRecyclerView;
    private List<Filter> listFilter;

    public FilterListFragment() {

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_filter_list,container, false);
        myRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        RecyclerViewAdapter recyclerAdapter = new RecyclerViewAdapter(getContext(),listFilter);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false)); //filtermenu horizontal gordulo
        myRecyclerView.setAdapter(recyclerAdapter);

        myRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), myRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                Toast.makeText(getActivity(), "Single Click on position        :"+position,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listFilter = new ArrayList<>();
        listFilter.add(new Filter("Filter1",R.drawable.dog));
        listFilter.add(new Filter("Filter2",R.drawable.dog));
        listFilter.add(new Filter("Filter3",R.drawable.dog));
        listFilter.add(new Filter("Filter4",R.drawable.dog));
        listFilter.add(new Filter("Filter5",R.drawable.dog));

    }
    public static interface ClickListener{
        public void onClick(View view,int position);
        public void onLongClick(View view,int position);
    }

}