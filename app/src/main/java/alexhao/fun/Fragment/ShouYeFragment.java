package alexhao.fun.Fragment;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import alexhao.fun.Adapter.MyPagerAdapter;
import alexhao.fun.R;


public class ShouYeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ViewPager mPager;//页卡内容
    private ImageView cursor;// 动画图片
    private LinearLayout l1,l2,l3,l4,l5;
    private TextView t1,t2,t3,t4,t5;

    private MainFragment frag1;

    private List<View> viewlist;//把需要滑动的页卡添加到这个list中
    private List<Fragment> fragmentlist;
    private int offset = 0;// 动画图片偏移量
    private int currIndex = 0;// 当前页卡编号
    private int bmpW;// 动画图片宽度

    private String mParam1;
    private String mParam2;


    public static ShouYeFragment newInstance(String param1, String param2) {
        ShouYeFragment fragment = new ShouYeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ShouYeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_shouye, container, false);
        InitImageView(view);
        initView(view,inflater);
        return view;
    }

    public void initView(View v,LayoutInflater minflater)

    {
        FragmentManager fm= getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        frag1=new MainFragment();
        mPager=(ViewPager)v.findViewById(R.id.viewpager);
        l1= (LinearLayout) v.findViewById(R.id.line1);
        l2= (LinearLayout) v.findViewById(R.id.line2);
        l3= (LinearLayout) v.findViewById(R.id.line3);
        l4= (LinearLayout) v.findViewById(R.id.line4);
        l5= (LinearLayout) v.findViewById(R.id.line5);
        t1= (TextView) v.findViewById(R.id.quanbu);
        t2= (TextView) v.findViewById(R.id.duanzi);
        t3= (TextView) v.findViewById(R.id.tupian);
        t4= (TextView) v.findViewById(R.id.shengyin);
        t5= (TextView) v.findViewById(R.id.shipin);

 /*       viewlist=new ArrayList<View>();
        viewlist.add(minflater.inflate(R.layout.fragment_main,null));
        viewlist.add(minflater.inflate(R.layout.fragment_main,null));
        viewlist.add(minflater.inflate(R.layout.fragment_main,null));
        viewlist.add(minflater.inflate(R.layout.fragment_main,null));
        viewlist.add(minflater.inflate(R.layout.fragment_main,null));
 */

        fragmentlist=new ArrayList<Fragment>();
        fragmentlist.add(frag1);

        mPager.setAdapter(new MyPagerAdapter(fm,fragmentlist));
        mPager.setCurrentItem(0);
        t1.setTextColor(Color.parseColor("#ffd800"));
        mPager.setOnPageChangeListener(new MyOnPageChangeListener());
        l1.setOnClickListener(new MyOnTitleClickListener(0));
        l2.setOnClickListener(new MyOnTitleClickListener(1));
        l3.setOnClickListener(new MyOnTitleClickListener(2));
        l4.setOnClickListener(new MyOnTitleClickListener(3));
        l5.setOnClickListener(new MyOnTitleClickListener(4));
    }

    private void InitImageView(View v) {
        cursor = (ImageView)v.findViewById(R.id.cursor);
        bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.cursor) .getWidth();// 获取图片宽度
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        offset = (screenW/5);// 计算偏移量
        Matrix matrix = new Matrix();
        matrix.postTranslate(0, 0);
        cursor.setImageMatrix(matrix);// 设置动画初始位置
    }

    public void initWordColor()
    {
        t1.setTextColor(Color.parseColor("#000000"));
        t2.setTextColor(Color.parseColor("#000000"));
        t3.setTextColor(Color.parseColor("#000000"));
        t4.setTextColor(Color.parseColor("#000000"));
        t5.setTextColor(Color.parseColor("#000000"));
    }

    /**
     * 页卡切换监听
     */
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        int one = offset;// 页卡1 -> 页卡2 偏移量
        int two = one*2;// 页卡1 -> 页卡3 偏移量
        int three =one*3;
        int four=one*4;

        @Override
        public void onPageSelected(int arg0) {
            initWordColor();
            Animation animation = null;
            switch (arg0) {
                case 0:
                    if (currIndex == 1) {
                        animation = new TranslateAnimation(one, 0, 0, 0);
                        t1.setTextColor(Color.parseColor("#ffd800"));
                    } else if (currIndex == 2) {
                        animation = new TranslateAnimation(two, 0, 0, 0);
                        t1.setTextColor(Color.parseColor("#ffd800"));
                    }
                    else if (currIndex == 3) {
                        animation = new TranslateAnimation(three, 0, 0, 0);
                        t1.setTextColor(Color.parseColor("#ffd800"));
                    }
                    else if (currIndex == 4) {
                        animation = new TranslateAnimation(four, 0, 0, 0);
                        t1.setTextColor(Color.parseColor("#ffd800"));
                    }
                    break;
                case 1:
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(0, one, 0, 0);
                        t2.setTextColor(Color.parseColor("#ffd800"));
                    } else if (currIndex == 2) {
                        animation = new TranslateAnimation(two, one, 0, 0);
                        t2.setTextColor(Color.parseColor("#ffd800"));
                    }
                    else if (currIndex == 3) {
                        animation = new TranslateAnimation(three, one, 0, 0);
                    }
                    else if (currIndex == 4) {
                        animation = new TranslateAnimation(four, one, 0, 0);
                        t2.setTextColor(Color.parseColor("#ffd800"));
                    }
                    break;
                case 2:
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(offset, two, 0, 0);
                        t3.setTextColor(Color.parseColor("#ffd800"));
                    } else if (currIndex == 1) {
                        animation = new TranslateAnimation(one, two, 0, 0);
                        t3.setTextColor(Color.parseColor("#ffd800"));
                    }
                    else if (currIndex == 3) {
                        animation = new TranslateAnimation(three, two, 0, 0);
                        t3.setTextColor(Color.parseColor("#ffd800"));
                    }
                    else if (currIndex == 4) {
                        animation = new TranslateAnimation(four, two, 0, 0);
                        t3.setTextColor(Color.parseColor("#ffd800"));
                    }
                    break;

                case 3:
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(0, three, 0, 0);
                        t4.setTextColor(Color.parseColor("#ffd800"));
                    } else if (currIndex == 1) {
                        animation = new TranslateAnimation(one, three, 0, 0);
                        t4.setTextColor(Color.parseColor("#ffd800"));
                    }
                    else if (currIndex == 2) {
                        animation = new TranslateAnimation(two, three, 0, 0);
                        t4.setTextColor(Color.parseColor("#ffd800"));
                    }
                    else if (currIndex == 4) {
                        animation = new TranslateAnimation(four, three, 0, 0);
                        t4.setTextColor(Color.parseColor("#ffd800"));
                    }
                    break;

                case 4:
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(0,four, 0, 0);
                        t5.setTextColor(Color.parseColor("#ffd800"));
                    } else if (currIndex == 1) {
                        animation = new TranslateAnimation(one,four, 0, 0);
                        t5.setTextColor(Color.parseColor("#ffd800"));
                    }
                    else if (currIndex == 2) {
                        animation = new TranslateAnimation(two,four, 0, 0);
                        t5.setTextColor(Color.parseColor("#ffd800"));
                    }
                    else if (currIndex == 3) {
                        animation = new TranslateAnimation(three,four, 0, 0);
                        t5.setTextColor(Color.parseColor("#ffd800"));
                    }
                    break;
            }
            currIndex = arg0;
            animation.setFillAfter(true);// True:图片停在动画结束位置
            animation.setDuration(300);
            cursor.startAnimation(animation);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }

    //标题点击接口监听

    public class MyOnTitleClickListener implements View.OnClickListener {
        private int index = 0;
        public MyOnTitleClickListener(int i) {
            index = i;
        }
        @Override
        public void onClick(View v) {

            mPager.setCurrentItem(index,false);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }


}
