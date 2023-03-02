package com.paheco.mycameraapp

import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import java.io.File

class MainActivity : AppCompatActivity() {
    lateinit var imageuri : Uri
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.cameraBtn).setOnClickListener()
        {
            val fileName = "bilden"
            val directoryStorage = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val tempfile = File.createTempFile(fileName, ".jpg", directoryStorage)
            imageuri = FileProvider.getUriForFile(this, "com.paheco.mycameraapp.fileprovider", tempfile)
            getContentcamera.launch(imageuri)
        }
    }

    val getContentcamera = registerForActivityResult(ActivityResultContracts.TakePicture()) {
        if(it == true) {
            val source = ImageDecoder.createSource(this.contentResolver, imageuri)
            val bitmap = ImageDecoder.decodeBitmap(source)
            val theimage = findViewById<ImageView>(R.id.cameraImg)
            theimage.setImageBitmap(bitmap)
        }
        else {
            // TODO: Handle error
        }
    }



}