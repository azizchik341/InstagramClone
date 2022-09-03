package com.example.instagramclone.presentation.fragments.addPost

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import com.example.instagramclone.R
import com.example.instagramclone.databinding.FragmentAddPostBinding
import com.example.instagramclone.domain.models.models.Image
import com.example.instagramclone.domain.models.models.Post
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.parse.ParseFile
import com.parse.ParseUser
import com.parse.SaveCallback
import com.squareup.picasso.Picasso
import java.io.ByteArrayOutputStream

class AddPostFragment : Fragment() {
    var cameraUri: Uri? = null
    private var imageUri: Uri? = null
    private var selectedImage: Bitmap? = null
    private var imageFile: ParseFile? = null
    private var pickImageIntent: Intent? = null
    private val values: ContentValues by lazy {
        ContentValues()
    }
    private val viewModel: AddPostViewModel by viewModels()
    private val binding: FragmentAddPostBinding by lazy {
        FragmentAddPostBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.createpostBtn.setOnClickListener {
            createPost()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }

    private fun getImage() {
        if (imageUri != null) {
            Picasso.get().load(imageUri).into(binding.postImage)
            uploadImage()
        }
    }

    private fun createPost() {
        if (notEmpty()) {
            val post = Post(
                binding.postTitle.text.toString(),
                binding.postDescription.text.toString(),
                parseFileToImage(imageFile!!),
                ParseUser.getCurrentUser().username,
                ParseUser.getCurrentUser().objectId
            )
            viewModel.createPost(post)
        } else {
            Toast.makeText(requireContext(), "Все поля должны быть заполнены", Toast.LENGTH_SHORT)
                .show()

        }
    }

    private fun clear() {
        binding.postDescription.text.clear()
        binding.postTitle.text.clear()
        imageFile = null
    }

    private fun notEmpty(): Boolean {
        if (imageFile != null) {
            if (binding.postTitle.text.isNotEmpty() && binding.postDescription.text.isNotEmpty())
                return true
        }
        return false
    }

    private fun takePicture() {
        values.put(MediaStore.Images.Media.TITLE, "MyPicture")
        values.put(
            MediaStore.Images.Media.DESCRIPTION,
            "Photo taken on " + System.currentTimeMillis()
        )
        cameraUri = requireContext().contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            values
        )
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri);
        startActivityForResult(cameraIntent, REQUEST_CODE)
    }

    @SuppressLint("IntentReset")
    private fun pickFromGallery() {
        pickImageIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.INTERNAL_CONTENT_URI
        )
        pickImageIntent?.type = "image/*"
        pickImageIntent?.putExtra(Intent.EXTRA_MIME_TYPES, MIME_TYPES)
        startActivityForResult(pickImageIntent, IMAGE_PICK_CODE)
    }

    private fun opnebottomSheet() {
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(R.layout.bottom_sheet_dialog)
        val cancelBtn = dialog.findViewById<MaterialButton>(R.id.cancel_btn)
        val takePicture = dialog.findViewById<MaterialCardView>(R.id.take_camera_card_view)
        val pickGallery = dialog.findViewById<MaterialCardView>(R.id.pick_gallery_card_view)
//        val binding =
//            BottomSheetDialogBinding.bind(R.layout.bottom_sheet_dialog.makeView(binding.root))
        binding.apply {
            cancelBtn?.setOnClickListener {
                dialog.dismiss()
            }
            takePicture?.setOnClickListener {
                takePicture()
                Toast.makeText(requireContext(), "Take picture", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            pickGallery?.setOnClickListener {
                pickFromGallery()
                Toast.makeText(requireContext(), "Pick from gallery", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
        }
        dialog.setCancelable(true)
        dialog.show()
    }

    private fun uploadImage() {
        val steam = ByteArrayOutputStream()
        selectedImage =
            MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, imageUri)
        selectedImage?.compress(Bitmap.CompressFormat.PNG, 100, steam)
        val byteArray = steam.toByteArray()
        val parseFile = ParseFile("image.png", byteArray)
        parseFile.saveInBackground(SaveCallback { e ->
            if (e == null) {
                Toast.makeText(requireContext(), "Image saved!", Toast.LENGTH_SHORT).show()
                imageFile = parseFile
            } else {
                Toast.makeText(requireContext(), "Failed is image!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            imageUri = cameraUri
            getImage()
        }
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE && data != null) {
            imageUri = data.data
            getImage()
        }
    }
    private fun parseFileToImage(file:ParseFile):Image{
        return Image("File",file.name,file.url)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!hasPermissions(requireContext(), *PERMISSIONS)) {
            ActivityCompat.requestPermissions(requireActivity(), PERMISSIONS, PERMISSION_ALL);
        }
        binding.pickImage.setOnClickListener {
            opnebottomSheet()
        }
        viewModel.response.observe(viewLifecycleOwner){
            if (it.isSuccessful)
                clear()
        }
    }

    fun hasPermissions(context: Context, vararg permissions: String): Boolean = permissions.all {
        ActivityCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        const val REQUEST_CODE = 200
        var PERMISSION_ALL = 1
        const val IMAGE_PICK_CODE = 100
        var PERMISSIONS = arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
        )
        val MIME_TYPES = arrayOf("image/jpeg", "image/png")
    }

}
