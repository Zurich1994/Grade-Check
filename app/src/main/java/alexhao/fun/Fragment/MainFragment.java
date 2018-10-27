package alexhao.fun.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import alexhao.fun.Activity.MainActivity;
import alexhao.fun.Adapter.MainListAdapter;
import alexhao.fun.JavaBean.MainListBean;
import alexhao.fun.Json.ParseMainList;
import alexhao.fun.R;
import alexhao.fun.View.LoadListView;
import alexhao.fun.View.RefreshableView;

public class MainFragment extends Fragment implements
        RefreshableView.PullToRefreshListener,RefreshableView.MyLoadListener,MainActivity.freshBtnDownInterface {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private LoadListView mloadlist;
    private RefreshableView refreshableView;
    private ListView listView;
    private MainListAdapter listAdapter;
    private MainActivity main;
    String result;
    private static String result1;
    RequestQueue mQueue;
    //请求数据地址
    final String mainAddr = "http://funworks.duapp.com/servlet/FunShowServlet";
    //final String mainAddr = "http://172.22.73.8:8080/Service/servlet/FunShowServlet";

    ParseMainList parse;

    List<MainListBean> mData = new ArrayList<MainListBean>();
    List<MainListBean> mData1 = new ArrayList<MainListBean>();

    BigPicFragment bpfrag = new BigPicFragment();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public MainFragment() {
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

        final FragmentManager fm = getActivity().getSupportFragmentManager();
//      List<HashMap<String,Object>> data =new ArrayList<HashMap<String, Object>>();
        mQueue = Volley.newRequestQueue(getActivity());
        parse = new ParseMainList();
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        listView = (ListView) v.findViewById(R.id.loadlist);
        refreshableView = (RefreshableView) v.findViewById(R.id.refreshable_view);
        JsonArrayRequest req = new JsonArrayRequest(mainAddr, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Toast toast = Toast.makeText(getActivity(), "成功", Toast.LENGTH_SHORT);
                toast.show();
                Log.d("---------------------------", "chenggong!");
                Log.d("---------------------------", response.toString());
                try {
                    List<MainListBean> data = new ArrayList<MainListBean>();
                    data = parse.parseMainlist(response);
                    listAdapter = new MainListAdapter(getActivity(), data, fm);
                    listView.setAdapter(listAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mQueue.add(req);
        refreshableView.setOnRefreshListener(this, 0);
        refreshableView.setLoadDataListener(this);
        main.setFreshBtnDown(this);
        return v;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.main = (MainActivity) activity;
    }

    //点击刷新按钮
    @Override
    public void freshData() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //    initData1();
                if (listAdapter != null)

                    mloadlist.refreshBtnDown();
                //      mloadlist.setSelectionFromTop(-50,0);
                //      listAdapter.DataChangedObserver(mData);
                //      mloadlist.refreshComplete();

            }
        }, 2000);

        //       initData();
        //       if(listAdapter!=null)
//        listAdapter.DataChangedObserver(mData);
        //       mloadlist.refreshBtnDown();
        //       mloadlist.refreshComplete();
    }

    @Override
    public void onRefresh() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                //    initData1();
                if (listAdapter != null)
                    listAdapter.DataChangedObserver(mData);
                refreshableView.finishRefreshing();
            }
        }, 2000);

    }


    @Override
    public void onLoad() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //        initData();
                if (listAdapter != null)
                    listAdapter.DataChangedObserver(mData);
                refreshableView.loadDataComplete();
            }
        }, 2000);
    }

    public void getListInfo() {

        StringRequest stringRequest = new StringRequest(mainAddr,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG", response);
                        result1 = response;
                        Log.d("+++++++++++++++++++++", result1);
                        Toast toast = Toast.makeText(getActivity(), "成功了",
                                Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
                Toast toast = Toast.makeText(getActivity(), "连接服务器失败，请重试",
                        Toast.LENGTH_SHORT);
                toast.show();

            }
        });
        mQueue.add(stringRequest);
    }

    public void getListInfo1() {

        JsonArrayRequest req = new JsonArrayRequest(mainAddr, new Response.Listener<JSONArray>
                () {
            @Override
            public void onResponse(JSONArray response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mQueue.add(req);
    }

}
