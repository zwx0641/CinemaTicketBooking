package com.example.gomovie;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.os.StrictMode;

public class MainActivity extends FragmentActivity implements
        View.OnClickListener {

    // 定义3个Fragment对象
    private SearchFragment fg1;
    private SoonFragment fg2;
    private MyFragment fg3;

    // 帧布局对象，用来存放Fragment对象
    private FrameLayout frameLayout;

    // 定义每个选项中的相关控件
    private ImageView mainImage;
    private ImageView searchImage;
    private ImageView myImage;

    private TextView mainText;
    private TextView searchText;
    private TextView myText;

    private RelativeLayout mainLayout;
    private RelativeLayout searchLayout;
    private RelativeLayout myLayout;

    private List<RelativeLayout> layoutList = new ArrayList<RelativeLayout>();

    // 定义几个颜色
    private int whirt = 0xFFE4CEC6;
    private int blue = 0xFFFFFFFF;
    private int orange = 0xFFCFA49A;

    private List<Fragment> mFragments = new ArrayList<Fragment>();

    // 定义FragmentManager对象管理器
    private FragmentManager fragmentManager;

    // 定义页面滑动组件
    private ViewPager vp;
    private List<View> list = new ArrayList<View>();

    private LayoutInflater inflater;
    private FragmentPagerAdapter mAdpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 让ActionBar消失
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 全屏
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        // WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        // 初始化底部导航栏的控件
        mainLayout = (RelativeLayout) findViewById(R.id.main_layout);
        // mainLayout.setBackgroundColor(whirt);
        searchLayout = (RelativeLayout) findViewById(R.id.search_layout);
        myLayout = (RelativeLayout) findViewById(R.id.my_layout);

        layoutList.add(mainLayout);
        layoutList.add(searchLayout);
        layoutList.add(myLayout);

        mainImage = (ImageView) findViewById(R.id.main_image);
        searchImage = (ImageView) findViewById(R.id.search_image);
        myImage = (ImageView) findViewById(R.id.my_image);

        mainText = (TextView) findViewById(R.id.main_text);
        searchText = (TextView) findViewById(R.id.search_text);
        myText = (TextView) findViewById(R.id.my_text);

        mainLayout.setOnClickListener(this);
        searchLayout.setOnClickListener(this);
        myLayout.setOnClickListener(this);

        fg3 = new MyFragment();

        fragmentManager = getSupportFragmentManager();

        setCurrentItem(0); // 初始化页面加载时显示第一个选项卡
        SharedPreferences mSharedPreferences = getSharedPreferences("gomovie", MODE_PRIVATE);
        // 滑动组件
        vp = (ViewPager) findViewById(R.id.pager);

        // 获取布局转换器
        inflater = LayoutInflater.from(this);
        list.add(inflater.inflate(R.layout.search, null));
        list.add(inflater.inflate(R.layout.first, null));
        list.add(inflater.inflate(R.layout.my, null));

        // 设置Viewpager组件
        // SetViewPager();

        fg2 = new SoonFragment();
        fg1 = new SearchFragment();
        fg3 = new MyFragment();

        mFragments.add(fg1);
        mFragments.add(fg2);
        mFragments.add(fg3);

        // 初始化Adapter这里使用FragmentPagerAdapter
        mAdpter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {

                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

        };
        vp.setAdapter(mAdpter);
        vp.setOnPageChangeListener(new MyOnPageChangeListener());

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_layout:
                vp.setCurrentItem(0);
                break;
            case R.id.search_layout:
                vp.setCurrentItem(1);
                break;
            case R.id.my_layout:
                vp.setCurrentItem(2);
                break;

            default:
                break;
        }
    }

    /**
     * 设置点击选项卡的事件处理
     *
     * @param index 选项卡的标号：0, 1, 2
     */
    private void setCurrentItem(int index) {
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        clearChioce(); // 清空, 重置选项, 隐藏所有Fragment
        hideFragments(fragmentTransaction);
        switch (index) {
            case 0:
                // firstImage.setImageResource(R.drawable.XX);
                mainText.setTextColor(blue);
                mainLayout.setBackgroundColor(orange);
                // 如果fg1为空，则创建一个并添加到界面上
                if (fg1 == null) {
                    fg1 = new SearchFragment();
                    fragmentTransaction.add(R.id.content, fg1);
                } else {
                    // 如果不为空，则直接将它显示出来
                    fragmentTransaction.show(fg1);
                }

                break;
            case 1:
                // secondImage.setImageResource(R.drawable.XXXX);
                searchText.setTextColor(blue);
                searchLayout.setBackgroundColor(orange);
                if (fg2 == null) {
                    fg2 = new SoonFragment();
                    fragmentTransaction.add(R.id.content, fg2);
                } else {
                    fragmentTransaction.show(fg2);
                }
                break;
            case 2:
                // thirdImage.setImageResource(R.drawable.XXXX);
                myText.setTextColor(whirt);
                myLayout.setBackgroundColor(orange);
                if (fg3 == null) {
                    fg3 = new MyFragment();
                    fragmentTransaction.add(R.id.content, fg3);
                } else {
                    fragmentTransaction.show(fg3);
                }
                break;
        }
        fragmentTransaction.commit(); // 提交
    }

    /**
     * 当选中其中一个选项卡时，其他选项卡重置为默认
     */
    private void clearChioce() {
        // firstImage.setImageResource(R.drawable.XXX);
        mainText.setTextColor(blue);
        mainLayout.setBackgroundColor(whirt);
        // secondImage.setImageResource(R.drawable.XXX);
        searchText.setTextColor(blue);
        searchLayout.setBackgroundColor(whirt);
        // thirdImage.setImageResource(R.drawable.XXX);
        myText.setTextColor(blue);
        myLayout.setBackgroundColor(whirt);
    }

    /**
     * 隐藏Fragment
     *
     * @param fragmentTransaction
     */
    private void hideFragments(FragmentTransaction fragmentTransaction) {
        if (fg1 != null) {
            fragmentTransaction.hide(fg1);
        }
        if (fg2 != null) {
            fragmentTransaction.hide(fg2);
        }
        if (fg3 != null) {
            fragmentTransaction.hide(fg3);
        }

    }

    public class MyOnPageChangeListener implements OnPageChangeListener {

        public void onPageScrollStateChanged(int arg0) {
            // 滑动过程状态
        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // 滑动结束
        }

        public void onPageSelected(int arg0) {

            for (int i = 0; i < layoutList.size(); i++) {
                if (i == arg0) {
                    layoutList.get(i).setBackgroundColor(orange);
                } else {
                    layoutList.get(i).setBackgroundColor(whirt);
                }
            }
        }
    }

}
