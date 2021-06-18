package com.descifrador.roomdemoapp.view.activity

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.descifrador.roomdemoapp.R
import com.descifrador.roomdemoapp.databinding.ActivityAddUpdateDishBinding
import com.descifrador.roomdemoapp.databinding.AddDishSelectionDialogBinding

class AddUpdateDishActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityAddUpdateDishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityAddUpdateDishBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setupActionBar()

        mBinding.addAPhotoImage.setOnClickListener {
            customDialogBox()
        }
    }

    private fun setupActionBar(){
        setSupportActionBar(mBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mBinding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun customDialogBox(){
        val dialog = Dialog(this)
        val dialogBinding: AddDishSelectionDialogBinding = AddDishSelectionDialogBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)

        dialogBinding.addFromCamera.setOnClickListener {
            Toast.makeText(this,"Clicked Camera Option",Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        dialogBinding.addFromGallery.setOnClickListener {
            Toast.makeText(this,"Clicked Gallery Option",Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }


        dialog.show()



    }
}