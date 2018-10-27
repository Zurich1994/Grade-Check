package alexhao.fun.Adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import java.util.List;

import alexhao.fun.JavaBean.MainListBean;
import alexhao.fun.R;
import alexhao.fun.Utils.ViewHolder;

/**
 * Created by Junqing on 2015/4/25.
 */
/* 0:只有文字    1:只有图片    2:只有语音相册   3:只有视频
                              4：文字+图片  5：文字+语音相册   6：文字+视频
                              7：只有Gif   8：文字+gif
                           */
public class MainListAdapter extends CommonAdapter<MainListBean> {

    protected int typeCount=3;
    List<MainListBean> mlist;
    int mtype;
    FragmentManager fm;
    final int ONLY_WORD = 0;
    final int ONLY_PIC  = 1;
    final int WORD_PIC  = 2;
    Context context;

    public MainListAdapter(Context context, List datas,FragmentManager fm) {
        super(context, datas,fm);
        mlist=datas;
        this.context=context;
        this.fm=fm;
    }

    @Override
    public void convert(ViewHolder viewHolder, MainListBean mainListBean,int type) {

        /* 0:只有文字    1:只有图片    2:只有语音相册   3:只有视频
           4：文字+图片  5：文字+语音相册   6：文字+视频
        */
        switch (type)
        {
            case ONLY_WORD :
                viewHolder.setText(R.id.name,mainListBean.getName());
                viewHolder.setImageURL(R.id.touxiang,mainListBean.getTouxiang());
                viewHolder.setText(R.id.time,mainListBean.getTime());
                viewHolder.setText(R.id.tag1,mainListBean.getTag1());
                viewHolder.setText(R.id.tag2,mainListBean.getTag2());
                viewHolder.setText(R.id.tag3,mainListBean.getTag3());
                viewHolder.setText(R.id.zancount,mainListBean.getZan());
                viewHolder.setText(R.id.caicount,mainListBean.getCai());
                viewHolder.setText(R.id.plcount,mainListBean.getPinglun());
                viewHolder.setText(R.id.sharecount,mainListBean.getShare());
                viewHolder.setImageClickListener(R.id.touxiang,mainListBean.getTouxiang());
    //            viewHolder.setImageResource(R.id.touxiang,mainListBean.getTouxiang());
                viewHolder.setText(R.id.word,mainListBean.getWord());
                break;
            case ONLY_PIC:
                viewHolder.setText(R.id.name1,mainListBean.getName());
                viewHolder.setImageURL(R.id.touxiang1,mainListBean.getTouxiang());
                viewHolder.setText(R.id.time1,mainListBean.getTime());
                viewHolder.setText(R.id.tag1_1,mainListBean.getTag1());
                viewHolder.setText(R.id.tag1_2,mainListBean.getTag2());
                viewHolder.setText(R.id.tag1_3,mainListBean.getTag3());
                viewHolder.setText(R.id.zancount1,mainListBean.getZan());
                viewHolder.setText(R.id.caicount1,mainListBean.getCai());
                viewHolder.setText(R.id.plcount1,mainListBean.getPinglun());
                viewHolder.setText(R.id.sharecount1,mainListBean.getShare());
                viewHolder.setImageClickListener(R.id.touxiang1,mainListBean.getTouxiang());
    //            viewHolder.setImageResource(R.id.touxiang1,mainListBean.getTouxiang());
                viewHolder.setImageURL(R.id.img1,mainListBean.getImg());
                viewHolder.setImageClickListener(R.id.img1,mainListBean.getImg());

                break;
            case WORD_PIC:

                viewHolder.setText(R.id.name4,mainListBean.getName());
                viewHolder.setImageURL(R.id.touxiang4,mainListBean.getTouxiang());
                viewHolder.setText(R.id.time4,mainListBean.getTime());
                viewHolder.setText(R.id.tag4_1,mainListBean.getTag1());
                viewHolder.setText(R.id.tag4_2,mainListBean.getTag2());
                viewHolder.setText(R.id.tag4_3,mainListBean.getTag3());
                viewHolder.setText(R.id.zancount4,mainListBean.getZan());
                viewHolder.setText(R.id.caicount4,mainListBean.getCai());
                viewHolder.setText(R.id.plcount4,mainListBean.getPinglun());
                viewHolder.setText(R.id.sharecount4,mainListBean.getShare());
                viewHolder.setImageClickListener(R.id.touxiang4,mainListBean.getTouxiang());
   //             viewHolder.setImageResource(R.id.touxiang4,mainListBean.getTouxiang());
                viewHolder.setImageURL(R.id.img4,mainListBean.getImg());
                viewHolder.setText(R.id.word4,mainListBean.getWord());
                viewHolder.setImageClickListener(R.id.img4,mainListBean.getImg());
                break;
        }

    }

    @Override
    public int getItemViewType(int position) {
           mtype= mlist.get(position).getType();
           if(mtype==0)
               return ONLY_WORD;
                else if(mtype==1)
                    return ONLY_PIC;
                        else
                         return WORD_PIC;

    }

    @Override
    public int getViewTypeCount() {
        return typeCount;
    }

    public void DataChangedObserver(List<MainListBean> list) {
        this.mlist=list;
        this.notifyDataSetChanged();
    }
}
