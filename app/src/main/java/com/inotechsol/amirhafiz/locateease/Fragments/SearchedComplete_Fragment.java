package com.inotechsol.amirhafiz.locateease.Fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inotechsol.amirhafiz.locateease.MainActivity;
import com.inotechsol.amirhafiz.locateease.R;
import com.inotechsol.amirhafiz.locateease.ViewPagerSlidingTab.PagetSlidingTab_Strip;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Amir on 5/5/2017.
 */

public class SearchedComplete_Fragment extends Fragment implements ViewPager.OnPageChangeListener {


    static PagetSlidingTab_Strip view_comm_frag_tabs;
    ViewPager pager;

    View viewpager_divider;
    public ViewComm_Adapter mAdapter;


    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

        View frgView = inflater.inflate(R.layout.searched_completed_fragment,container,false);

        pager = (ViewPager)frgView.findViewById(R.id.pager);
        mAdapter = new ViewComm_Adapter(getChildFragmentManager());
        pager.setAdapter(mAdapter);
        pager.setOffscreenPageLimit(1);//1

        view_comm_frag_tabs = (PagetSlidingTab_Strip) frgView.findViewById(R.id.view_comm_frag_tabs);
        view_comm_frag_tabs.setViewPager(pager);
        Typeface mRobotoRegular = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Roboto-Regular.ttf");
        view_comm_frag_tabs.setTypeface(mRobotoRegular);
        view_comm_frag_tabs.setOnPageChangeListener(this);

        ((MainActivity)getActivity()).hideShowActionBar(true);

        return frgView;

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public class ViewComm_Adapter extends FragmentPagerAdapter {

        private FragmentManager mFragmentManager;
        private Map<Integer, String> mFragmentTags;

        public ViewComm_Adapter(FragmentManager fm) {
            super(fm);
            this.mFragmentManager = fm;
            this.mFragmentTags = new HashMap<Integer, String>();
        }


        @Override
        public Fragment getItem(int position) {

            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = new SearchedResult_FourSquare_Fragment();
                    break;
                case 1:
                    fragment = new SearchedResult_Google_Fragment();//
                    break;



            }
            return fragment;//(position == 0)? new Enquries_Fragment() : new Suggestions_Fragment();
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            CharSequence TabName = "";
            switch (position) {
                case 0:         //About
                    TabName = "FourSquare";//"About";
                    break;
                case 1:       //Directory
                    TabName = "Google";
                    break;

            }
            return TabName;
        }

//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
//            mFragmentTags.remove(position);
//        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Object obj = super.instantiateItem(container, position);
            if (obj instanceof Fragment) {
                Fragment currFrg = (Fragment) obj;


                mFragmentTags.put(position, currFrg.getTag());
                Log.d("TAG", position + ": " + currFrg.getTag());


            }
            return obj;
        }

        public Fragment getFragment(int position) {
            String tag = mFragmentTags.get(position);

            return tag == null ? null : mFragmentManager.findFragmentByTag(tag);
        }
    }




}
