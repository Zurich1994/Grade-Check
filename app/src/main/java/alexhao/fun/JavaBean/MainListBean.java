package alexhao.fun.JavaBean;

/**
 * Created by Junqing on 2015/4/15.
 */
public class MainListBean
{
        private String touxiang;
        private int id;
 //       private int touxiang;
        private String name;
        private String time;
        private String word;
        private String img;
        private String tag1;
        private String tag2;
        private String tag3;
        private String zan;
        private String cai;
        private String pinglun;
        private String share;
        private int type;  /* 0:只有文字    1:只有图片    2:只有语音相册   3:只有视频
                              4：文字+图片  5：文字+语音相册   6：文字+视频
                           */




    public MainListBean(int id,String touxiang, String name, String time, String word, String img, String tag1,String tag2,String tag3, String zan, String cai, String share, String pinglun,int type) {
        this.id=id;
        this.touxiang = touxiang;
        this.name = name;
        this.time = time;
        this.word = word;
        this.img = img;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
        this.zan = zan;
        this.cai = cai;
        this.share = share;
        this.pinglun = pinglun;
        this.type=type;
    }

    public String getTouxiang() {

        return touxiang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTouxiang(String touxiang) {
        this.touxiang = touxiang;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getZan() {
        return zan;
    }

    public void setZan(String zan) {
        this.zan = zan;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCai() {
        return cai;
    }

    public void setCai(String cai) {
        this.cai = cai;
    }

    public String getPinglun() {
        return pinglun;
    }

    public void setPinglun(String pinglun) {
        this.pinglun = pinglun;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }
    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }
    public String getTag3() {
        return tag3;
    }

    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
