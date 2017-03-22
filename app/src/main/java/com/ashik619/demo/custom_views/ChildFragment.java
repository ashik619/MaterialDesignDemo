package com.ashik619.demo.custom_views;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ashik619.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChildFragment extends Fragment {


    @BindView(R.id.genderLayout)
    LinearLayout genderLayout;
    @BindView(R.id.profileLayout)
    CardView profileLayout;
    @BindView(R.id.phoneLayout)
    CardView phoneLayout;
    @BindView(R.id.addressLayout)
    CardView addressLayout;
    @BindView(R.id.contactLayout)
    CardView contactLayout;
    int position;


    public ChildFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_child, container, false);
        ButterKnife.bind(this, view);
        position=getArguments().getInt("position");
        switch (position){
            case 0:
                break;
            case 1:
                profileLayout.setVisibility(View.GONE);
                phoneLayout.setVisibility(View.GONE);
                addressLayout.setVisibility(View.GONE);
                contactLayout.setVisibility(View.VISIBLE);
                break;
            case 2:
                profileLayout.setVisibility(View.GONE);
                phoneLayout.setVisibility(View.VISIBLE);
                addressLayout.setVisibility(View.GONE);
                contactLayout.setVisibility(View.GONE);
                break;
            case 3:
                profileLayout.setVisibility(View.GONE);
                phoneLayout.setVisibility(View.GONE);
                addressLayout.setVisibility(View.VISIBLE);
                contactLayout.setVisibility(View.GONE);
        }
        return view;
    }

}
