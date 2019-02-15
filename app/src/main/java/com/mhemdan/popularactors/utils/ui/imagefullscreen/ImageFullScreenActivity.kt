package com.mhemdan.popularactors.utils.ui.imagefullscreen

import android.Manifest
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mhemdan.popularactors.R
import com.mhemdan.popularactors.utils.extensions.actorName
import com.mhemdan.popularactors.utils.extensions.imageUrl
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_image_full_screen.*
import java.util.*


class ImageFullScreenActivity : AppCompatActivity() {

    lateinit var currentBitmap: Bitmap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_full_screen)
        setToolbar()

        init()
    }

    private fun init() {
        Picasso.get().load(intent.extras.imageUrl).into(object : Target {
            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
            }

            override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom) {
                currentBitmap = bitmap
                photoView.setImageDrawable(BitmapDrawable(resources, bitmap))
            }

            override fun onPrepareLoad(placeHolderDrawable: Drawable) {}
        })
    }

    private fun setToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = intent.extras.actorName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_save_image, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            com.mhemdan.popularactors.R.id.actionSave -> {
                saveImageToGallery()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveImageToGallery() {
        RxPermissions(this)
            .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .subscribe { granted ->
                if (granted) {
                    val imageName = "Image_${intent.extras.actorName}_${Calendar.getInstance().timeInMillis}.jpg"
                    var url = MediaStore.Images.Media.insertImage(
                        applicationContext.contentResolver,
                        currentBitmap, imageName, intent.extras.actorName
                    )
                    Toast.makeText(this, "Image Saved on $url", Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
