package com.example.diyahmmt.mcatalogueuiux;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.diyahmmt.mcatalogueuiux.R;
import com.example.diyahmmt.mcatalogueuiux.nowplay.NowPlayHomeFragment;
import com.example.diyahmmt.mcatalogueuiux.upcom.UpcomingHomeFragment;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class FragmentHome extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private View view;

    public FragmentHome() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragment_home, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewPager.setAdapter(new tabAdapter(getChildFragmentManager()));
        tabLayout = (TabLayout) view.findViewById(R.id.menu_tab);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }

    private class tabAdapter extends FragmentPagerAdapter {
        String nowPlaying = getResources().getString(R.string.now_playing);
        String upComing = getResources().getString(R.string.upcoming);
        final String menuTab[] = {nowPlaying, upComing};

        public tabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new NowPlayHomeFragment();
                case 1:
                    return new UpcomingHomeFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return menuTab.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return menuTab[position];
        }
    }


}
