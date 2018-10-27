package alexhao.fun.Adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import java.util.List;

import alexhao.fun.JavaBean.DrawerBean;
import alexhao.fun.R;
import alexhao.fun.Utils.ViewHolder;

/**
 * Created by Junqing on 2015/5/12.
 */
public class DrawerAdapter extends CommonAdapter<DrawerBean> {

    private  List<DrawerBean> mlist;

    public DrawerAdapter(Context context, List datas,FragmentManager fm) {
        super(context, datas,fm);
        mlist=datas;
    }

    @Override
    public void convert(ViewHolder viewHolder, DrawerBean drawerBean, int type) {


        viewHolder.setImageResource(R.id.drawerlistimg,drawerBean.getPic());
        viewHolder.setText(R.id.drawerlistword,drawerBean.getItem());
    }

    @Override
    public int getItemViewType(int position) {
        return 3;
    }
}
