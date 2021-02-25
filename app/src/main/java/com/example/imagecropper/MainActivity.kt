package com.example.imagecropper

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.canhub.cropper.CropImage
import com.canhub.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        showImage.setOnClickListener {
            CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setAutoZoomEnabled(true)
                .setOutputCompressFormat(Bitmap.CompressFormat.PNG)
                .setAspectRatio(4, 4) // .setRequestedSize(300,800)
                .start(this@MainActivity)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                val resultUri = result?.uri
                // globalVariable.iamgeLinkroom = resultUri.toString()
                // updateImageLink = resultUri.toString()
                showImage.setImageURI(resultUri)
                Toast.makeText(applicationContext,"updateImageLink", Toast.LENGTH_LONG).show()
                 Log.e("resultCode", resultUri.toString())
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result?.error
            }
        }
    }
}