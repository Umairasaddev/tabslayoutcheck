package com.example.tabslayoutcheck;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    TabLayout tablayout;
    ViewPager viewpager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    tablayout = findViewById(R.id.tablayout);
    viewpager = findViewById(R.id.viewpager);

        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("Basic");
        arrayList.add("Advance");
        arrayList.add("Pro");

     //  VPAdapter adapter = new VPAdapter(getSupportFragmentManager());
   //viewpager.setAdapter(adapter);

    tablayout.setupWithViewPager(viewpager);
    prepareViewPager(viewpager,arrayList);
    }

    private void prepareViewPager(ViewPager viewpager, ArrayList<String> arrayList) {

        //initializing main adapter
        VPAdapter adapter = new VPAdapter(getSupportFragmentManager());
        ChatsFragment chatsFragment = new ChatsFragment();//main fragment = chats frag

        for(int i=0;i<arrayList.size();i++)
        {
            Bundle bundle = new Bundle();
            bundle.putString("title",arrayList.get(i));

            chatsFragment.setArguments(bundle);
            adapter.addFragment(chatsFragment,arrayList.get(i));
            chatsFragment  = new ChatsFragment();
        }
        viewpager.setAdapter(adapter);

    }

    private class VPAdapter extends FragmentPagerAdapter {


        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        ArrayList<String> stringArrayList = new ArrayList<>();

        int[] imageList = {R.drawable.ic_baseline_battery_basic, R.drawable.ic_baseline_bluetooth_advance,
                R.drawable.ic_baseline_bedtime_pro};
        private Context applicationContext;

        //create constructor
        public void addFragment(Fragment fragment, String s) {
            fragmentArrayList.add(fragment);
            //add title
            stringArrayList.add(s);
        }



        public VPAdapter(FragmentManager supportFragmentManagger){
            super(supportFragmentManagger);

        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            //return fragment array list size
            return fragmentArrayList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {

            //initialize drawable
            Drawable drawable = ContextCompat.getDrawable(applicationContext.getApplicationContext(), imageList[position]);

            //set bounds
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

            SpannableString spannableString = new SpannableString("    " +
                    stringArrayList.get(position));

            ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM);

            spannableString.setSpan(imageSpan, 0, 1,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            return spannableString;

        }

    }


}