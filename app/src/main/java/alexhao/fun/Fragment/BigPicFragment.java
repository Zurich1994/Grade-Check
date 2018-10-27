package alexhao.fun.Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import alexhao.fun.Libs.photoview.PhotoView;
import alexhao.fun.R;

public class BigPicFragment extends DialogFragment {

    private DisplayImageOptions options;
    private ImageLoader imageLoader;
    PhotoView img;
    RelativeLayout bigpiclayout;
    String url;
    private String des="http://192.168.0.105:8080/pic/";
    public BigPicFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view= inflater.inflate(R.layout.fragment_bigpic, container, false);
        img= (PhotoView) view.findViewById(R.id.bigpic);
        bigpiclayout= (RelativeLayout) view.findViewById(R.id.bigpiclayout);
//        setBigPic(des+url);
        displayImageOptions();
        showBigPic();
        onDialogClick();
        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void setBigPic(String url)
    {
        this.url=url;
    }

    public void showBigPic()
    {

        imageLoader.getInstance().displayImage(url,img,options);
    }
    public void displayImageOptions()
    {
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.max90) //设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.drawable.max90)//设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.max90)  //设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisc(true)//设置下载的图片是否缓存在SD卡中
                .bitmapConfig(Bitmap.Config.ARGB_8888)
                .build();//构建完成
    }

    public void onDialogClick()
    {
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

}
