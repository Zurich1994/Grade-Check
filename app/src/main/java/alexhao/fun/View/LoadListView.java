package alexhao.fun.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import alexhao.fun.R;

/**
 * Created by Junqing on 2015/4/24.
 */
public class LoadListView extends ListView implements AbsListView.OnScrollListener {

    View header;
    View footer;
    int headerHeight;//顶部布局文件的高度
    int totalCount; //当前总数量
    int firstVisbleItem; //当前第一个可见的item
    int lastVisbleItem; //最后一个可见的item
    int scollState;//当前滚动状态
    boolean isRemark;//
    int startY;//
    int state;//当前状态

    final int NONE =0;  //正常状态
    final int PULL =1;  //提示下拉
    final int RELEASE =2; //提示释放
    final int REFRESHING =3; //刷新状态

    boolean isRefreshBtnDown=false; //是否为刷新按钮按下刷新


    Boolean isLoading=false; //正在加载
    MyLoadListener mLoadListener;
    MyFreshListener mFreshListener;

    public LoadListView(Context context) {
        super(context);
        initView(context);
    }



    public LoadListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public LoadListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater=LayoutInflater.from(context);

        footer=inflater.inflate(R.layout.footer,null);
        footer.findViewById(R.id.loadmore).setVisibility(View.GONE);

        header= inflater.inflate(R.layout.header,null);
        MeasureView(header);
        headerHeight=header.getMeasuredHeight();
        topPadding(-headerHeight);


        this.addHeaderView(header);
        this.addFooterView(footer);
        this.setOnScrollListener(this);
    }

    private void  MeasureView(View view)
    {
        ViewGroup.LayoutParams p=view.getLayoutParams();
        if(p==null)
        {
            p=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int width=ViewGroup.getChildMeasureSpec(0,0,p.width);
        int height;
        int tempHeight=p.height;
        if(tempHeight>0)
        {
            height=MeasureSpec.makeMeasureSpec(tempHeight,MeasureSpec.EXACTLY);
        }else
        {
            height=MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);
        }

        view.measure(width,height);
    }

    private void topPadding(int top)
    {
            header.setPadding(header.getPaddingLeft(),top,header.getPaddingRight(),header.getPaddingBottom());
            header.invalidate();
    }


    @Override
    public void onScrollStateChanged(AbsListView absListView, int scollState) {

        this.scollState=scollState;

        if(totalCount==lastVisbleItem&&scollState==SCROLL_STATE_IDLE)
        {
            //加载更多
            if(!isLoading)
            {
                isLoading=true;
                footer.findViewById(R.id.loadmore).setVisibility(View.VISIBLE);
                mLoadListener.onLoad();
            }

        }

    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisbleItem, int VisbleItemCount, int totalItemCount) {
        this.lastVisbleItem=firstVisbleItem+VisbleItemCount;
        this.totalCount=totalItemCount;
        this.firstVisbleItem=firstVisbleItem;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction())
        {

            case MotionEvent.ACTION_DOWN :
                if (firstVisbleItem == 0) {
                    isRemark=true;
                    startY = (int) ev.getY();
                  }
                   break;
            case MotionEvent.ACTION_MOVE :
                onMove(ev);
                break;
            case MotionEvent.ACTION_UP :
                if(state==RELEASE)
                {
                    state=REFRESHING;

       //------------  加载最新数据-------------------//
                    refreshViewByState();
                    mFreshListener.onFresh();

                }else if(state==PULL)
                {
                    state=NONE;
                    isRemark=false;
                    refreshViewByState();
                }

                break;}
      return super.onTouchEvent(ev);
    }

    private void onMove(MotionEvent ev) {
        if (!isRemark)
        {
            return;
        }

        int tempY=(int)ev.getY();
        int space=tempY-startY;
        int topPadding=space-headerHeight;

        switch(state)
        {
            case NONE:
                if(space>0){
                    state=PULL;}
                refreshViewByState();
                break;
            case PULL:
                topPadding(topPadding);
                if(space>headerHeight+20&&scollState==SCROLL_STATE_TOUCH_SCROLL)
                {
                        state=RELEASE;
                    refreshViewByState();
                }

                break;
            case RELEASE:
                topPadding(topPadding);
                if(space<headerHeight+20)
                {
                    state=PULL;
                    refreshViewByState();
                }else if (space<0)
                {
                    state=NONE;
                    refreshViewByState();
                }

                break;
        }
    }

    public void refreshViewByState()
    {

        TextView tv=(TextView)header.findViewById(R.id.tip1);
        ImageView img= (ImageView) header.findViewById(R.id.xiala);
        ProgressBar pro=(ProgressBar)header.findViewById(R.id.progress);
        RotateAnimation anim=new RotateAnimation(0,180,RotateAnimation.RELATIVE_TO_SELF,0.5f,
                RotateAnimation.RELATIVE_TO_SELF,0.5f);
        RotateAnimation anim1=new RotateAnimation(180,0,RotateAnimation.RELATIVE_TO_SELF,0.5f,
                RotateAnimation.RELATIVE_TO_SELF,0.5f);
        anim.setDuration(500);
        anim.setFillAfter(true);
        anim1.setDuration(500);
        anim1.setFillAfter(true);
            switch (state)

            {
                case NONE:
                    img.clearAnimation();
                    topPadding(-headerHeight);
                    break;
                case PULL:
                    img.setVisibility(View.VISIBLE);
                    pro.setVisibility(View.GONE);
                    tv.setText("下拉可以刷新");
                    img.clearAnimation();
                    img.setAnimation(anim1);
                    break;
                case RELEASE:
                    img.setVisibility(View.VISIBLE);
                    pro.setVisibility(View.GONE);
                    tv.setText("松开可以刷新");
                    img.clearAnimation();
                    img.setAnimation(anim);
                    break;
                case REFRESHING:
                    topPadding(50);
                    img.setVisibility(View.GONE);
                    pro.setVisibility(View.VISIBLE);
                    tv.setText("正在刷新...");
                    img.clearAnimation();
                    break;
            }
    }

    public void refreshComplete() //刷新完毕
    {
        state=NONE;
        isRemark=false;
        refreshViewByState();
        TextView lastupdatetime= (TextView) header.findViewById(R.id.tip2);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date(System.currentTimeMillis());
        String time=format.format(date);
        lastupdatetime.setText(time);
    }

    public void loadComplete() //加载完毕
    {
        isLoading=false;
        footer.findViewById(R.id.loadmore).setVisibility(View.GONE);
    }

    public void refreshBtnDown()
    {
    //    isRefreshBtnDown=true;
       header.setTop(100);
      // refreshViewByState();
        mFreshListener.onFresh();
    }


    /**
     * 回调接口（下拉刷新 、 加载更多）
     * @param mLoadListener
     */
    public void setLoadInterface(MyLoadListener mLoadListener)
    {
        this.mLoadListener=mLoadListener;
    }

    public void setFreshInterface(MyFreshListener mFreshListener)
    {
        this.mFreshListener=mFreshListener;
    }

    //加载更多数据的回调接口
    public interface MyLoadListener
    {
        public void onLoad();
    }

    public interface MyFreshListener
    {
        public void onFresh();
    }

}
