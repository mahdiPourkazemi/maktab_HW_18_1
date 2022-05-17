package com.pourkazemi.mahdi.maktab_hw_18_1.ui

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.pourkazemi.mahdi.maktab_hw_18_1.R
import com.pourkazemi.mahdi.maktab_hw_18_1.databinding.AppDialogBinding
import com.pourkazemi.mahdi.maktab_hw_18_1.util.toByteArrayConverter
import com.pourkazemi.mahdi.maktab_hw_18_1.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AppDialog(
    private val dialogResult: (
        firstName: String,
        lastName: String,
        nationalCode: String,
        list: List<String>,
        image: ByteArray?,
    ) -> Unit
) : DialogFragment(R.layout.app_dialog) {

    private val binding: AppDialogBinding by viewBinding(AppDialogBinding::bind)
    private var byteArray: ByteArray? = null
    private lateinit var imageFromCamera: ActivityResultLauncher<Void?>
    private lateinit var imageFromGallery: ActivityResultLauncher<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(
            ColorDrawable(Color.TRANSPARENT)
        )

        val listOfHobbeis = mutableListOf<String>()

        binding.apply {

            game.setOnClickListener {
                if (listOfHobbeis.hasElement("game")){
                    listOfHobbeis.add("game")
                    game.setBackgroundColor(Color.parseColor("#CCCCCC"))
                }else{
                   listOfHobbeis.remove("game")
                    game.setBackgroundColor(Color.parseColor("#FFFFFF"))
                }
            }
            Sleep.setOnClickListener {
                if (listOfHobbeis.hasElement("Sleep")){
                    listOfHobbeis.add("Sleep")
                    Sleep.setBackgroundColor(Color.parseColor("#CCCCCC"))
                }else{
                    listOfHobbeis.remove("Sleep")
                   Sleep.setBackgroundColor(Color.parseColor("#FFFFFF"))
                }
            }
            tv.setOnClickListener {
                if (listOfHobbeis.hasElement("tv")){
                    listOfHobbeis.add("tv")
                    tv.setBackgroundColor(Color.parseColor("#CCCCCC"))
                }else{
                    listOfHobbeis.remove("tv")
                    tv.setBackgroundColor(Color.parseColor("#FFFFFF"))
                }
            }
            sport.setOnClickListener {
                if (listOfHobbeis.hasElement("sport")){
                    listOfHobbeis.add("sport")
                    sport.setBackgroundColor(Color.parseColor("#CCCCCC"))
                }else{
                    listOfHobbeis.remove("sport")
                    sport.setBackgroundColor(Color.parseColor("#FFFFFF"))
                }
            }
            imageViewDialog.setOnClickListener {
                takeImageDialog()
            }

            donButton.setOnClickListener{
                dialogResult(
                    personName.text.toString(),
                    personFamily.text.toString(),
                    nationalCode.text.toString(),
                    listOfHobbeis,
                    byteArray //?:"null".toByteArray()
                )
                dismiss()
            }

        }

        imageFromCamera = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
            if (it != null) {
                binding.imageViewDialog.setImageBitmap(it)
                byteArray = it.toByteArrayConverter()
            }
        }

        imageFromGallery = registerForActivityResult(ActivityResultContracts.GetContent()) {
            if (it != null) {
                byteArray =
                    requireActivity().contentResolver?.openInputStream(it)?.readBytes() as ByteArray
                val galleryBitMap=requireActivity().contentResolver?.openInputStream(it) as Bitmap
                binding.imageViewDialog.setImageBitmap(galleryBitMap)
            }
        }
    }


    private fun takeImageDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Take Image")
            .setMessage("Select Image Source")
            .setNegativeButton("CAMERA") { dialog, which ->
                takeImageFromCamera()
            }
            .setPositiveButton("GALLERY") { dialog, which ->
                takeImageFromGallery()
            }
            .show()
    }

    private fun takeImageFromCamera() {
        imageFromCamera.launch(null)
    }

    private fun takeImageFromGallery() {
        imageFromGallery.launch("image/*")
    }

/*    fun listener(result: () -> Unit) {
        result()
    }*/
fun MutableList<String>.hasElement(item:String):Boolean{
   return elementAt(indexOf(item)) == item
}
}


