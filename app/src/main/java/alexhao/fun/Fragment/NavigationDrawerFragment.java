package alexhao.fun.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import alexhao.fun.Activity.MainActivity;
import alexhao.fun.Adapter.DrawerAdapter;
import alexhao.fun.JavaBean.DrawerBean;
import alexhao.fun.R;
import alexhao.fun.View.RoundImgView;


public class NavigationDrawerFragment extends Fragment {


    private DrawerLayout mDrawerLayout;
    private ListView mDrawerListView;
    private RoundImgView img;
    private TextView tv;
    private ImageView drawerimg;
    private TextView drawertv;
    private MainActivity main;

    List<DrawerBean> mData= new ArrayList<DrawerBean>();
    DrawerAdapter drawerAdapter;

    public NavigationDrawerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);



    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        initDatas();
        View v=inflater.inflate(R.layout.fragment_navigation_drawer,container,false);
        mDrawerListView=(ListView)v.findViewById(R.id.list);
        img=(RoundImgView)v.findViewById(R.id.roundimg);
        tv=(TextView)v.findViewById(R.id.roundname);

        drawerAdapter=new DrawerAdapter(getActivity(),mData,getActivity().getSupportFragmentManager());
        mDrawerListView.setAdapter(drawerAdapter);

 //      String[] mData=new String []{"q","e","w","z","k"};
//       ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_list_item_1,mData);
//        mDrawerListView.setAdapter(adapter);
        return v;
    }


    public void initDatas()
    {
        DrawerBean bean = new DrawerBean(R.drawable.search,"搜   索");
        DrawerBean bean1 = new DrawerBean(R.drawable.night,"夜   间");
        DrawerBean bean2 = new DrawerBean(R.drawable.help,"帮   助");
        DrawerBean bean3 = new DrawerBean(R.drawable.feed,"反   馈");
        DrawerBean bean4 = new DrawerBean(R.drawable.about,"关   于");
        DrawerBean bean5 = new DrawerBean(R.drawable.power,"退   出");

        mData.add(bean);
        mData.add(bean1);
        mData.add(bean2);
        mData.add(bean3);
        mData.add(bean4);
        mData.add(bean5);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.main= (MainActivity) activity;
    }
}
