package co.torres.christhian.testgrability.lib;

import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class GlideImageLoader implements ImageLoader {

    private RequestManager glideRequestManager;

    public GlideImageLoader(RequestManager glideRequestManager) {
        this.glideRequestManager = glideRequestManager;
    }

    @Override
    public void loadImage(ImageView imageView, String url) {
        glideRequestManager
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .override(600, 400)
                .into(imageView);

    }
}
