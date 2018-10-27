package alexhao.fun.JavaBean;

/**
 * Created by Junqing on 2015/5/12.
 */
public class DrawerBean {

    private int pic;
    private String item;

    public DrawerBean(int pic, String item) {
        this.pic = pic;
        this.item = item;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
