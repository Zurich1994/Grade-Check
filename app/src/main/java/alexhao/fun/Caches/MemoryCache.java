package alexhao.fun.Caches;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by Junqing on 2015/4/29.
 */
public class MemoryCache implements ImageLoader.ImageCache {

    private LruCache<String, Bitmap> mCache;
    @Override
    public Bitmap getBitmap(String url) {
        return  mCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        mCache.put(url, bitmap);

    }

        public MemoryCache() {
            int maxSize = 10 * 1024 * 1024;
            mCache = new LruCache<String, Bitmap>(maxSize) {
                @Override
                protected int sizeOf(String key, Bitmap bitmap) {
                    return bitmap.getRowBytes() * bitmap.getHeight();
                }
            };
        }


}
