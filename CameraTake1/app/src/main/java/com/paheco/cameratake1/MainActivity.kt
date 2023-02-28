package com.paheco.cameratake1

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.ImageDecoder
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        // Handle the returned Uri
        Log.i("pia11debug", "OK")
        uri?.let {
            val source = ImageDecoder.createSource(this.contentResolver, it)
            val bitmap = ImageDecoder.decodeBitmap(source)
            val theImage = findViewById<ImageView>(R.id.theimage)
            theImage.setImageBitmap(bitmap)
    }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.cameraBTN).setOnClickListener(){
            dispatchTakePictureIntent()
        }
        findViewById<Button>(R.id.galleryBTN).setOnClickListener() {
            getContent.launch("image/*")
        }


    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, 1)
        } catch (e: ActivityNotFoundException) {
            // display error state to the user
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}