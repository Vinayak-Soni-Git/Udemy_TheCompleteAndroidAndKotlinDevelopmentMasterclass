package com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.DrawingApp

import android.Manifest
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.graphics.createBitmap
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import yuku.ambilwarna.AmbilWarnaDialog
import java.io.File
import java.io.FileOutputStream
import java.util.Random

class MainDrawingActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var drawingView: DrawingView
    private lateinit var brushButton: ImageButton

    private lateinit var btnPurple: ImageButton
    private lateinit var btnGreen: ImageButton
    private lateinit var btnRed: ImageButton
    private lateinit var btnOrange: ImageButton
    private lateinit var btnBlue: ImageButton

    private lateinit var btnUndo: ImageButton
    private lateinit var btnColorPicker: ImageButton

    private lateinit var btnGallery: ImageButton
    private lateinit var btnSave: ImageButton

    private val openGalleryLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        findViewById<ImageView>(R.id.iv_gallery).setImageURI(result.data?.data)
    }
    val requestPermission: ActivityResultLauncher<Array<String>> = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        permissions.entries.forEach {
            val permissionName = it.key
            val isGranted = it.value
            if (isGranted && permissionName == Manifest.permission.READ_EXTERNAL_STORAGE) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                val pickIntent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                openGalleryLauncher.launch(pickIntent)
            } else if (isGranted && (permissionName == Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                CoroutineScope(IO).launch {
                    saveImage(getBitMapFromView(findViewById(R.id.constraint_l_3)))
                }
            } else {
                if (permissionName == Manifest.permission.READ_EXTERNAL_STORAGE) {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_drawing)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        drawingView = findViewById(R.id.drawing_view)
        brushButton = findViewById(R.id.btn_select_brush)

        btnPurple = findViewById(R.id.btn_purple)
        btnGreen = findViewById(R.id.btn_green)
        btnRed = findViewById(R.id.btn_red)
        btnOrange = findViewById(R.id.btn_orange)
        btnBlue = findViewById(R.id.btn_blue)

        btnUndo = findViewById(R.id.btn_undo)
        btnColorPicker = findViewById(R.id.btn_color_picker)
        btnGallery = findViewById(R.id.btn_gallery)
        btnSave = findViewById(R.id.btn_save)

        drawingView.changeBrushSize(23.toFloat())

        brushButton.setOnClickListener {
            showBrushChooserDialog()
        }

        btnPurple.setOnClickListener(this)
        btnGreen.setOnClickListener(this)
        btnOrange.setOnClickListener(this)
        btnBlue.setOnClickListener(this)
        btnRed.setOnClickListener(this)
        btnUndo.setOnClickListener(this)
        btnColorPicker.setOnClickListener(this)
        btnGallery.setOnClickListener(this)
        btnSave.setOnClickListener(this)
    }

    private fun showBrushChooserDialog() {
        val brushDialog = Dialog(this@MainDrawingActivity)
        brushDialog.setContentView(R.layout.dialog_brush)
        val seekBarProgress = brushDialog.findViewById<SeekBar>(R.id.dialog_seek_bar)
        val tvShowProgress = brushDialog.findViewById<TextView>(R.id.dialog_tv_progress)

        seekBarProgress.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                seekBar?.progress?.let {
                    drawingView.changeBrushSize(it.toFloat())
                }
                tvShowProgress.text = seekBar?.progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_purple -> {
                drawingView.setColor("#d14ef6")
            }

            R.id.btn_red -> {
                drawingView.setColor("#fa5868")
            }

            R.id.btn_orange -> {
                drawingView.setColor("#ef8041")
            }

            R.id.btn_green -> {
                drawingView.setColor("#2dc40b")
            }

            R.id.btn_blue -> {
                drawingView.setColor("#2f6ff1")
            }

            R.id.btn_undo -> {
                drawingView.undoPath()
            }

            R.id.btn_color_picker -> {
                showColorPickerDialog()
            }

            R.id.btn_gallery -> {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    requestStoragePermission()
                } else {
                    val pickIntent =
                        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    openGalleryLauncher.launch(pickIntent)
                }
            }

            R.id.btn_save -> {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    requestStoragePermission()
                } else {
                    val layout = findViewById<ConstraintLayout>(R.id.constraint_l_3)
                    val bitmap = getBitMapFromView(layout)
                    CoroutineScope(IO).launch {
                        saveImage(bitmap)
                    }
                }
            }
        }
    }

    private fun showColorPickerDialog() {
        val dialog =
            AmbilWarnaDialog(this, Color.WHITE, object : AmbilWarnaDialog.OnAmbilWarnaListener {
                override fun onCancel(dialog: AmbilWarnaDialog?) {
                    TODO("Not yet implemented")
                }

                override fun onOk(dialog: AmbilWarnaDialog?, color: Int) {
                    drawingView.setColor(color)
                }
            })
        dialog.show()
    }

    private fun requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        ) {
            showRationalDialog()
        } else {
            requestPermission.launch(
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
            )
        }
    }

    private fun showRationalDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Storage Permission")
            .setMessage("we need this permission in order to access the internal storage")
            .setPositiveButton("Yes") { dialog, _ ->
                dialog.dismiss()
            }
        builder.create().show()
    }

    private fun getBitMapFromView(view: View): Bitmap {
        val bitmap = createBitmap(view.width, view.height)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }

    private suspend fun saveImage(bitmap: Bitmap) {
        val root =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString()
        val myDir = File("$root/saved_images")
        myDir.mkdir()
        val generator = Random()
        var n = 10000
        n = generator.nextInt(n)
        val outputFile = File(myDir, "Images-$n.jpg")
        if (outputFile.exists()) {
            outputFile.delete()
        } else {
            try {
                val out = FileOutputStream(outputFile)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
                out.flush()
                out.close()
            } catch (e: Exception) {
                e.stackTrace
            }
            withContext(Main) {
                Toast.makeText(
                    this@MainDrawingActivity,
                    "${outputFile.absolutePath} saved!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}