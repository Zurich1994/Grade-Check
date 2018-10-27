package alexhao.fun.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import alexhao.fun.Fragment.BigPicFragment;
import alexhao.fun.R;

//import com.android.volley.toolbox.ImageLoader;

/**
 * Created by Junqing on 2015/4/24.
 */
public class ViewHolder {

    private SparseArray<View> mViews;
    private Context context;
    private View mConvertView;
    private ImageLoader imgloader;
    private FragmentManager fm;

    RequestQueue queue;

    private int  type;
    private int position;
    final int ONLY_WORD = 0;
    final int ONLY_PIC  = 1;
    final int WORD_PIC  = 2;
    final int DRAWER = 3;

    private int ONLY_WORD_LAYOUT = R.layout.item_only_word;
    private int ONLY_PIC_LAYOUT = R.layout.item_only_pic;
    private int WORD_PIC_LAYOUT = R.layout.item_word_pic;
    private int DRAWER_LAYOUT = R.layout.drawer_list_item;
    private String des="http://funworks.duapp.com/test/";
    private DisplayImageOptions options;
    BigPicFragment bigPicFragment=new BigPicFragment();

    public ViewHolder(Context context,ViewGroup parent,int position,int type,FragmentManager fm)
    {
            queue= Volley.newRequestQueue(context);
            this.position=position;
            this.mViews=new SparseArray<View>();
            this.type=type;
            this.context=context;


            switch(type){
                case ONLY_WORD :
                    mConvertView= LayoutInflater.from(context).inflate(ONLY_WORD_LAYOUT,parent,false);
                    break;
                case ONLY_PIC :
                    mConvertView= LayoutInflater.from(context).inflate(ONLY_PIC_LAYOUT,parent,false);
                    break;
                case WORD_PIC :
                    mConvertView= LayoutInflater.from(context).inflate(WORD_PIC_LAYOUT,parent,false);
                    break;
                case DRAWER :
                    mConvertView= LayoutInflater.from(context).inflate(DRAWER_LAYOUT,parent,false);
            }
            mConvertView.setTag(this);
    }


    /**
* 入口方法 用于初始化viewHolder
*
* */
    public static ViewHolder get(Context context,View convertView
            ,ViewGroup parent,int position,int type,FragmentManager fm)
    {
        if(convertView==null) {
            return new ViewHolder(context, parent, position,type,fm);
        }else
        {
            ViewHolder viewHolder=(ViewHolder)convertView.getTag();
            viewHolder.position=position;
            viewHolder.type=type;
            viewHolder.context=context;
            viewHolder.fm=fm;
            return viewHolder;
        }
    }

    public View getConvertView()
    {
        return mConvertView;
    }

/**
 * 得到view id的方法
 *
 */
    public <T extends View> T getView(int viewId)  //T 泛型
    {
        View view=mViews.get(viewId);
        if(view==null)
        {
            view=mConvertView.findViewById(viewId);
            mViews.put(viewId,view);
        }
            return (T)view;
    }

/*---------------------------------------------------------------------*/

    public ViewHolder setText(int viewId,String text)
    {
        TextView tv=getView(viewId);
        tv.setText(text);
        return this;
    }

    public ViewHolder setImageResource(int viewId,int resId)
    {
        ImageView imageView=getView(viewId);
        imageView.setImageResource(resId);
        return this;
    }

    public ViewHolder setImageBitmap(int viewId,Bitmap bitmap)
    {
        ImageView imageView= getView(viewId);
        imageView.setImageBitmap(bitmap);
        return this;
    }

    public ViewHolder setImageURL(int viewId,String imgUrl)
    {
        ImageView imageView= getView(viewId);
        displayImageOptions();
        imgloader.getInstance().displayImage(des+imgUrl,imageView,options);
        Log.d("BBBBBBBBB",des+imgUrl);
        return this;
    }

    public ViewHolder setImageClickListener(int viewId, final String url)
    {
        ImageView imageView= getView(viewId);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           //     Toast toast=Toast.makeText(context, "图片被点击", Toast.LENGTH_SHORT);
           //     toast.show();

               bigPicFragment.setBigPic(des+url);
    //            FragmentTransaction transaction = fm.beginTransaction();
      //          transaction.add(android.R.id.content, bigPicFragment).addToBackStack(null).commit();
                bigPicFragment.show(fm,"bigpicfrag");

            }
        });
        return this;
    }

    public void displayImageOptions()
    {
         options = new DisplayImageOptions.Builder()
          .showImageOnLoading(R.drawable.morenpic) //设置图片在下载期间显示的图片
          .showImageForEmptyUri(R.drawable.morenpic)//设置图片Uri为空或是错误的时候显示的图片
         .showImageOnFail(R.drawable.morenpic)  //设置图片加载/解码过程中错误时候显示的图片
         .cacheInMemory(true)//设置下载的图片是否缓存在内存中
         .cacheOnDisc(true)//设置下载的图片是否缓存在SD卡中
         .bitmapConfig(Bitmap.Config.ARGB_8888)
         .build();//构建完成
    }


}
