package alexhao.fun.Json;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import alexhao.fun.JavaBean.MainListBean;

/**
 * Created by Junqing on 2015/5/18.
 */
public class ParseMainList {

    private static List<MainListBean> mlist;

    public List<MainListBean> parseMainlist(JSONArray jarr) throws JSONException {

        int id;
        String touxiang;
        //int touxiang;
        String name;
        String time;
        String word;
        String img;
        String tag1;
        String tag2;
        String tag3;
        String zan;
        String cai;
        String pinglun;
        String share;
        int type;  /* 0:只有文字    1:只有图片    2:只有语音相册   3:只有视频
                              4：文字+图片  5：文字+语音相册   6：文字+视频
                           */

        mlist =new ArrayList<MainListBean>();

//        String json=new String(strbuf);
//       JSONArray jarr=new JSONArray(json);

        for(int i=0;i<jarr.length();i++)
        {
            JSONObject job=jarr.getJSONObject(i);
            id = job.getInt("fun_id");
            touxiang=job.getString("user_head");
            name=job.getString("user_name");
            time=job.getString("fun_time");
            word=job.getString("fun_content");
            img=job.getString("user_head");
            tag1=job.getString("sign1");
            tag2=job.getString("sign2");
            tag3=job.getString("sign3");
            zan=job.getString("praise_count");
            cai=job.getString("down_count");
            pinglun=job.getString("comment_count");
            share=job.getString("share_count");
            type=job.getInt("type");
           Log.d("AAAAAAAAAAAAAAAAAAAAAAAAAAAAA", id+touxiang+name+time+word+img+tag1+tag2+tag3+zan+cai+pinglun+share+type);
            mlist.add(new MainListBean(id,touxiang,name,time,word,img,tag1,tag2,tag3,zan,cai,pinglun,share,type));

        }

/*        List<HashMap<String,Object>> data =new ArrayList<HashMap<String, Object>>();
        for (MainListBean mlb : mlist)
        {
            HashMap<String,Object>item=new HashMap<String, Object>();
            item.put("id",mlb.getId());
            item.put("touxiang",mlb.getTouxiang());
            item.put("name",mlb.getName());
            item.put("time", mlb.getTime());
            item.put("word",mlb.getWord());
            item.put("img",mlb.getImg());
            item.put("tag1",mlb.getTag1());
            item.put("tag2",mlb.getTag2());
            item.put("tag3",mlb.getTag3());
            item.put("zan",mlb.getZan());
            item.put("cai",mlb.getCai());
            item.put("pinglun",mlb.getPinglun());
            item.put("share",mlb.getShare());
            item.put("type",mlb.getType());
            data.add(item);
        }*/

        return mlist;
    }
}
