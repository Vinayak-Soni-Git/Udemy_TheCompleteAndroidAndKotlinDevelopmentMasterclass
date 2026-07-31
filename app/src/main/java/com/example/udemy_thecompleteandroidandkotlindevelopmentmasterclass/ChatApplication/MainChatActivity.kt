package com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.ChatApplication

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.ChatApplication.model.User
import com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.R
import com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.databinding.ActivityMainChatBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class MainChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainChatBinding
    private lateinit var getResult: ActivityResultLauncher<Intent>
    private lateinit var uri: Uri
    private lateinit var storageRef: StorageReference
    private val db = FirebaseFirestore.getInstance()
    private val usersRef: CollectionReference = db.collection("users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        storageRef = FirebaseStorage.getInstance().reference
        binding.signInButton.setOnClickListener { }
        binding.signUpButton.setOnClickListener { }

        binding.textViewRegister.setOnClickListener {
            startNextAnimation()
        }

        binding.textViewSignIn.setOnClickListener {
            startPreviousAnimation()
        }

        binding.textViewGoToProfile.setOnClickListener {
            startNextAnimation()
        }
        binding.profileImage.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(
                    this@MainChatActivity,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermission()
            } else {
                getImage()
            }
        }
        getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                binding.profileImage.setImageURI(it.data?.data)
                uri = it.data?.data!!
            }
        }
    }

    private fun signIn() {
        binding.progressBar1.visibility = View.VISIBLE
        val email = binding.singInInputEmail.editText?.text.toString().trim()
        val password = binding.singInInputPassword.editText?.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            binding.singInInputEmail.error = "Email must not be empty"
            binding.singInInputPassword.error = "Password must not be empty"
            return
        }
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    binding.singInInputEmail.error = null
                    binding.singInInputPassword.error = null
                    hideProgressBar()
                    val intent = Intent(this, ChatActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    binding.singInInputEmail.error = null
                    binding.singInInputPassword.error = null
                    hideProgressBar()
                    return@addOnCompleteListener
                }
            }
        if (this::uri.isInitialized) {
            val filePath = storageRef.child("profile_images").child(uri.lastPathSegment!!)
            filePath.putFile(uri).addOnSuccessListener { task ->
                filePath.downloadUrl.addOnSuccessListener {
                    val result: Task<Uri> = task.metadata?.reference?.downloadUrl!!
                    result.addOnSuccessListener {
                        uri = it
                    }
                    val user =
                        User(userName, uri.toString(), FirebaseAuth.getInstance().currentUser?.uid)
                    usersRef.document()
                        .set(user)
                        .addOnSuccessListener {
                            Toast.makeText(this, "User created successfully", Toast.LENGTH_SHORT)
                                .show()
                            binding.progressBar2.visibility = View.GONE
                            sendToActivity()
                        }.addOnFailureListener {
                            Toast.makeText(this, "Error creating user", Toast.LENGTH_SHORT).show()
                        }
                }
            }
        } else {

        }
    }

    private fun sendToActivity() {
        startActivity(Intent(this@MainChatActivity, ChatActivity::class.java))
    }

    private fun createAccount() {
        showProgressBar2()
        val email = binding.singUpInputEmail.text.toString().trim()
        val password = binding.singUpInputPassword.text.toString().trim()
        val confirmPassword = binding.singUpInputConfirmPassword.text.toString().trim()
        val userName = binding.singUpInputUsername.text.toString().trim()

        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            binding.singUpInputEmail.error = "Email must not be empty"
            binding.singUpInputPassword.error = "Password must not be empty"
            binding.singUpInputConfirmPassword.error = "Confirm password must not be empty"
            hideProgressBar2()
            return
        }
        if (userName.isEmpty()) {
            binding.singUpInputUsername.error = "Username must not be empty"
            hideProgressBar2()
            return
        }

        if (password != confirmPassword) {
            binding.singUpInputPassword.error = "Password do not match"
            binding.singUpInputConfirmPassword.error = "Password do not match"
            hideProgressBar2()
            return
        }
        if (password.length <= 6) {
            binding.singUpInputPassword.error = "Password must be at least 6 characters"
            binding.singUpInputConfirmPassword.error = "Password must be at least 6 characters"
            hideProgressBar2()
            return
        }
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    binding.singUpInputEmail.error = null
                    binding.singUpInputPassword.error = null
                    binding.singUpInputConfirmPassword.error = null
                    hideProgressBar2()
                    val intent = Intent(this, ChatActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    binding.singUpInputEmail.error = null
                    binding.singUpInputPassword.error = null
                    binding.singUpInputConfirmPassword.error = null
                    hideProgressBar2()
                    return@addOnCompleteListener
                }
            }
    }

    private fun startPreviousAnimation() {
        binding.flipper.setInAnimation(this, R.anim.slide_in_right)
        binding.flipper.setOutAnimation(this, R.anim.slide_out_left)
        binding.flipper.showPrevious()
    }

    private fun startNextAnimation() {
        binding.flipper.setInAnimation(this, R.anim.slide_in_left)
        binding.flipper.setOutAnimation(this, R.anim.slide_out_right)
        binding.flipper.showNext()
    }

    private fun getImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        getResult.launch(intent)
    }

    private fun requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this@MainChatActivity,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        ) {
            AlertDialog.Builder(this@MainChatActivity)
                .setPositiveButton("Yes") { _, _ ->
                    ActivityCompat.requestPermissions(
                        this@MainChatActivity,
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        100
                    )
                }.setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                }.setTitle("Permissoin needed")
                .setMessage("This permission is needed to select a profile picture")
                .show()
        } else {
            ActivityCompat.requestPermissions(
                this@MainChatActivity,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                100
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String?>,
        grantResults: IntArray,
        deviceId: Int
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId)
        if (requestCode == 100 && grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getImage()
        } else {
            requestPermission()
        }
    }

    private fun hideProgressBar() {
        binding.progressBar1.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding.progressBar1.visibility = View.VISIBLE
    }

    private fun hideProgressBar2() {
        binding.progressBar2.visibility = View.GONE
    }

    private fun showProgressBar2() {
        binding.progressBar2.visibility = View.VISIBLE
    }
}