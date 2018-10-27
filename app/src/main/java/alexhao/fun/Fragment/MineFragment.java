package alexhao.fun.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import alexhao.fun.Activity.LoginActivity;
import alexhao.fun.R;
import alexhao.fun.View.DampView;


public class MineFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView nickname;
    private ImageView img;
    private DampView damp;

    // TODO: Rename and change types and number of parameters
    public static MineFragment newInstance(String param1, String param2) {
        MineFragment fragment = new MineFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public MineFragment() {
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
        View view;
        view= inflater.inflate(R.layout.fragment_mine2, container, false);
        initView(view);
        setOnListener();
        return view;
    }


    public void initView(View view)
    {
        nickname= (TextView) view.findViewById(R.id.nickname);
        img = (ImageView)view.findViewById(R.id.backgoundimg);
        damp= (DampView)view.findViewById(R.id.dampview);
        damp.setImageView(img);
    }

    public void setOnListener()
    {
        nickname.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
         switch(view.getId())
        {
            case R.id.nickname:
            //    FragmentManager fm=getActivity().getSupportFragmentManager();
            //    FragmentTransaction ft=fm.beginTransaction();
            //    loginfragment=new LoginFragment();
            //    ft.add(R.id.content,loginfragment);
            //    ft.show(loginfragment);
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                Toast toast=Toast.makeText(getActivity(),"ok",Toast.LENGTH_SHORT);
                toast.show();
        }
    }
}
