package com.test.attractgroup.image_loading

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.widget.ImageView
import java.net.URL

class ImageLoader {
    companion object{
        private val TAG = ImageLoadTask::class.java.simpleName
        fun load(imageView: ImageView, url: String){
            ImageLoadTask(object : ImageLoadTask.Callback{
                override fun onSuccess(btmp: Bitmap) {
                    imageView.setImageBitmap(btmp)
                }

                override fun onError(message: String) {
                    Log.e(TAG, "Error load image: $message")
                }
            }).execute(url)
        }

        class ImageLoadTask(private val callback: Callback): AsyncTask<String, Void, Bitmap>(){
            private val TAG = ImageLoadTask::class.java.simpleName

            override fun doInBackground(vararg params: String?): Bitmap? {
                return getBitmap(params[0]?: "")
            }

            override fun onPostExecute(result: Bitmap?) {
                super.onPostExecute(result)
                if(result != null) {
                    callback.onSuccess(result)
                }else
                    callback.onError("Error image loading")
            }

            private fun getBitmap(url: String): Bitmap? {
                var bitmap: Bitmap? = null
                    try {
                        val inputStream = URL(url).openStream()
                        bitmap = BitmapFactory.decodeStream(inputStream)
                    } catch (e: Exception) {
                        Log.e(TAG, "Error: ${e.message}")
                    }
                return bitmap
            }

            interface Callback{
                fun onSuccess(btmp: Bitmap)
                fun onError(message: String)
            }
        }
    }
}