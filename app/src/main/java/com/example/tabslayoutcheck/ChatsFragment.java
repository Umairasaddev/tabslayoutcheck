package com.example.tabslayoutcheck;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ChatsFragment extends Fragment {


    TextView textView;
    public ChatsFragment()
    {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_chats, container, false);

    textView = view.findViewById(R.id.tvchats);

    String sTitle = getArguments().getString("title");
    textView.setText(sTitle);

        return view;

    }
}