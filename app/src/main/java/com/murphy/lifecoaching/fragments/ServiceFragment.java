package com.murphy.lifecoaching.fragments;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.murphy.lifecoaching.R;

/**
 * Created by Administrator on 2/21/2016.
 */
public class ServiceFragment extends Fragment {
    public ServiceFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_service,container, false);
        TextView tv = (TextView) rootView.findViewById(R.id.serviceTextView);
        tv.setMovementMethod(new ScrollingMovementMethod());
        return rootView;
    }
}
