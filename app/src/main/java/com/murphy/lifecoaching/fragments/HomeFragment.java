package com.murphy.lifecoaching.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.murphy.lifecoaching.R;

/**
 * Created by Administrator on 2/21/2016.
 */
public class HomeFragment extends Fragment {
    public HomeFragment(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home,container, false);
        TextView tv = (TextView) rootView.findViewById(R.id.coachTextView);
        tv.setMovementMethod(new ScrollingMovementMethod());
        return rootView;
    }
    public void replaceFragment(int pos){
        Fragment fragment = null;
        switch (pos){
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
            case 2:
                fragment = new GoodsFragment();
                break;
            case 3:
                fragment = new ServiceFragment();
                break;
        }

        if(null != fragment){
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.main_content,fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

}
