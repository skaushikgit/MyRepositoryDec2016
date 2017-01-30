package com.example.saurabhkaushik.galleryview.Services;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.example.saurabhkaushik.galleryview.Utils.ImageUtils;

import java.lang.ref.WeakReference;

/**
 * Created by saurabhkaushik on 27/01/17.
 */

public class BitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap> {
    private final WeakReference<ImageView> imageViewReference;
    public int data = 0;
    Resources resources;

    public BitmapWorkerTask(ImageView imageView, Resources resources) {
        imageViewReference = new WeakReference<ImageView>(imageView);
        this.resources = resources;
    }

    @Override
    protected Bitmap doInBackground(Integer... params) {
        data = params[0];
        return ImageUtils.decodeFullImage(resources, data, 100, 100);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (isCancelled()) {
            bitmap = null;
        }
        if (imageViewReference != null && bitmap != null) {
            final ImageView imageView = imageViewReference.get();
            final BitmapWorkerTask bitmapWorkerTask =
                    getBitmapWorkerTask(imageView);
            if (this == bitmapWorkerTask && imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    private BitmapWorkerTask getBitmapWorkerTask(ImageView imageView) {
        if (imageView != null) {
            final Drawable drawable = imageView.getDrawable();
            if (drawable instanceof BitmapWorkerTask.AsyncDrawable) {
                final BitmapWorkerTask.AsyncDrawable asyncDrawable = (BitmapWorkerTask.AsyncDrawable) drawable;
                return asyncDrawable.getBitmapWorkerTask();
            }
        }
        return null;
    }

    //AsyncDrawable extends BitmapDrawable and maintains weakreference to BitmapWorkerTask that decodes the image and return bitmap.
    public static class AsyncDrawable extends BitmapDrawable {
        private final WeakReference<BitmapWorkerTask> BitmapWorkerTaskWeakReference;

        public AsyncDrawable(Resources resources, Bitmap bitmap, BitmapWorkerTask bitmapWorkerTask) {
            super(resources, bitmap);
            BitmapWorkerTaskWeakReference = new WeakReference<BitmapWorkerTask>(bitmapWorkerTask);
        }

        public BitmapWorkerTask getBitmapWorkerTask() {
            return BitmapWorkerTaskWeakReference.get();
        }
    }
}