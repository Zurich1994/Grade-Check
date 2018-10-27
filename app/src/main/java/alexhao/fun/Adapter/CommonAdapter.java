package alexhao.fun.Adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import alexhao.fun.Utils.ViewHolder;

/**
 * Created by Junqing on 2015/4/24.
 */
public abstract class CommonAdapter <T> extends BaseAdapter {


    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;

    protected int mtype;
    protected  FragmentManager fm;
    protected CommonAdapter(Context context,List<T> datas,FragmentManager fm) {

        this.mContext=context;
        this.mDatas=datas;
        mInflater=LayoutInflater.from(context);
        this.fm=fm;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        mtype=getItemViewType(position);
        ViewHolder viewHolder= ViewHolder.get(mContext,convertView,parent,position,mtype,fm);
        convert(viewHolder,getItem(position),mtype);
        return viewHolder.getConvertView();
    }


    public abstract void convert(ViewHolder viewHolder, T t,int type);

}
