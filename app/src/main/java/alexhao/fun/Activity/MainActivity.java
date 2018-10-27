package alexhao.fun.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.File;

import alexhao.fun.Fragment.FollowFragment;
import alexhao.fun.Fragment.MainFragment;
import alexhao.fun.Fragment.MessageFragment;
import alexhao.fun.Fragment.MineFragment;
import alexhao.fun.Fragment.ShouYeFragment;
import alexhao.fun.R;
import alexhao.fun.View.ArcMenu;


public class MainActivity extends FragmentActivity
        implements View.OnClickListener{

    private DrawerLayout mDrawer;
    private LinearLayout mTab1;
    private LinearLayout mTab2;
    private LinearLayout mTab3;
    private LinearLayout mTab4;

    private ImageButton mImgTab1;
    private ImageButton mImgTab2;
    private ImageButton mImgTab3;
    private ImageButton mImgTab4;
    private ImageButton mListbtn;
    private ImageButton mRefresh;

    private TextView mtxt1;
    private TextView mtxt2;
    private TextView mtxt3;
    private TextView mtxt4;

    private Fragment fragment1;
    private Fragment fragment2;
    private Fragment fragment3;
    private Fragment fragment4;

    freshBtnDownInterface fbtn;
    private File cacheFile;
    private ArcMenu arcMenu;
    private View include1;

    private static final int[] ITEM_DRAWABLES = { R.drawable.shi100, R.drawable.sheng100,
            R.drawable.tu100, R.drawable.wen100};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); //声明使用自定义标题
        setContentView(R.layout.activity_main);

        initView();
        initEvents();
        initImgLoaderConfig();

        setSelect(0);
    }

    private void initEvents()
    {
        mTab1.setOnClickListener(this);
        mTab2.setOnClickListener(this);
        mTab3.setOnClickListener(this);
        mTab4.setOnClickListener(this);
        mListbtn.setOnClickListener(this);
        mRefresh.setOnClickListener(this);
    }

    private void initView()
    {
        mTab1= (LinearLayout) findViewById(R.id.tab1);
        mTab2= (LinearLayout) findViewById(R.id.tab2);
        mTab3= (LinearLayout) findViewById(R.id.tab3);
        mTab4= (LinearLayout) findViewById(R.id.tab4);

        mImgTab1=(ImageButton)findViewById(R.id.imgbtn1);
        mImgTab2=(ImageButton)findViewById(R.id.imgbtn2);
        mImgTab3=(ImageButton)findViewById(R.id.imgbtn3);
        mImgTab4=(ImageButton)findViewById(R.id.imgbtn4);
        mListbtn=(ImageButton)findViewById(R.id.listbtn);
        mRefresh=(ImageButton)findViewById(R.id.refreshbtn);
        mDrawer=(DrawerLayout)findViewById(R.id.drawer_layout);

        mtxt1=(TextView)findViewById(R.id.txt1);
        mtxt2=(TextView)findViewById(R.id.txt2);
        mtxt3=(TextView)findViewById(R.id.txt3);
        mtxt4=(TextView)findViewById(R.id.txt4);

        arcMenu= (ArcMenu) findViewById(R.id.arcmenu);
        include1=findViewById(R.id.include1);
        include1.setVisibility(View.GONE);
    }


    public void initImgLoaderConfig()
    {
        cacheFile=this.getExternalCacheDir();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
            .Builder(this)
            .memoryCacheExtraOptions(480, 800) // max width, max height，即保存的每个缓存文件的最大长宽
            .threadPoolSize(3)//线程池内加载的数量
            .threadPriority(Thread.NORM_PRIORITY - 2)
            .denyCacheImageMultipleSizesInMemory()
            .memoryCache(new LruMemoryCache(2 * 1024 * 1024)) // You can pass your own memory cache implementation/你可以通过自己的内存缓存实现
            .memoryCacheSize(2 * 1024 * 1024)
            .discCacheSize(50 * 1024 * 1024)
            .discCacheFileNameGenerator(new Md5FileNameGenerator())//将保存的时候的URI名称用MD5 加密
            .tasksProcessingOrder(QueueProcessingType.LIFO)
            .discCacheFileCount(100) //缓存的文件数量
            .discCache(new UnlimitedDiscCache(cacheFile))//自定义缓存路径
            .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
            .build();//开始构建

        ImageLoader.getInstance().init(config);
    }



    @Override
    public void onClick(View view)
    {

        switch (view.getId())
        {
            case R.id.tab1:
            {
                setSelect(0);
                arcMenu.setVisibility(View.VISIBLE);
                break;
            }

            case R.id.tab2:
            {
                setSelect(1);
                arcMenu.setVisibility(View.GONE);

                break;
            }
            case R.id.tab3:
            {
                setSelect(2);
                arcMenu.setVisibility(View.GONE);

                break;
            }
            case R.id.tab4:
            {
                setSelect(3);
                arcMenu.setVisibility(View.GONE);
                break;
            }
            case R.id.listbtn:
            {
               mDrawer.openDrawer(GravityCompat.START);
                break;
            }
            case R.id.refreshbtn:
            {
                fbtn.freshData();
                break;
            }
        }

    }

    private void setSelect(int i) //更改图片  切换内容区域
    {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        hideFragments(ft);
        resetImgs();
        switch (i)
        {
            case 0:
            {
                if(fragment1==null)
                {
                    include1.setVisibility(View.VISIBLE);
                    fragment1= new ShouYeFragment();
                    ft.add(R.id.content,fragment1);
                }else
                {
                    include1.setVisibility(View.VISIBLE);
                    ft.show(fragment1);
                }
                mImgTab1.setImageResource(R.drawable.main2);
                mtxt1.setTextColor(Color.parseColor("#ffd800"));

                break;
            }
            case 1:
            {
                if(fragment2==null)
                {
                    include1.setVisibility(View.VISIBLE);
                    fragment2=new FollowFragment();
                    ft.add(R.id.content,fragment2);
                }else
                {
                    include1.setVisibility(View.VISIBLE);
                    ft.show(fragment2);
                }
                mImgTab2.setImageResource(R.drawable.follow2);
                mtxt2.setTextColor(Color.parseColor("#ffd800"));

                break;
            }
            case 2:
            {
                if(fragment3==null)
                {
                    include1.setVisibility(View.VISIBLE);
                    fragment3=new MessageFragment();
                    ft.add(R.id.content,fragment3);
                }else
                {
                    include1.setVisibility(View.VISIBLE);
                    ft.show(fragment3);
                }
                mImgTab3.setImageResource(R.drawable.message2);
                mtxt3.setTextColor(Color.parseColor("#ffd800"));

                break;
            }
            case 3:
            {
                if(fragment4==null)
                {
                    include1.setVisibility(View.GONE);
                    fragment4=new MineFragment();
                    ft.add(R.id.content,fragment4);
                }else
                {
                    include1.setVisibility(View.GONE);
                    ft.show(fragment4);
                }

                mImgTab4.setImageResource(R.drawable.mine2);
                mtxt4.setTextColor(Color.parseColor("#ffd800"));

                break;
            }
        }
        ft.commit();
    }


    private void hideFragments(FragmentTransaction ft) {
        if(fragment1!=null)
        {
            ft.hide(fragment1);
        }
        if(fragment2!=null)
        {
            ft.hide(fragment2);
        }
        if(fragment3!=null)
        {
            ft.hide(fragment3);
        }
        if(fragment4!=null)
        {
            ft.hide(fragment4);
        }
    }

    private void resetImgs() {
        mImgTab1.setImageResource(R.drawable.main1);
        mImgTab2.setImageResource(R.drawable.follow1);
        mImgTab3.setImageResource(R.drawable.message1);
        mImgTab4.setImageResource(R.drawable.mine1);
        mtxt1.setTextColor(Color.parseColor("#515567"));
        mtxt2.setTextColor(Color.parseColor("#515567"));
        mtxt3.setTextColor(Color.parseColor("#515567"));
        mtxt4.setTextColor(Color.parseColor("#515567"));
    }


    public void setFreshBtnDown(freshBtnDownInterface fbtn)
    {
        this.fbtn=fbtn;
    }

    public interface freshBtnDownInterface{
        public void freshData();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);

            if (fragment1 == null && fragment instanceof MainFragment) {
                fragment1 = fragment;
            }else if (fragment2 == null && fragment instanceof FollowFragment) {
                fragment2 = fragment;
            }else if (fragment3 == null && fragment instanceof MessageFragment) {
                fragment3 = fragment;
            }else if (fragment4 == null && fragment instanceof MineFragment) {
                fragment4 = fragment;
            }
        }
}

