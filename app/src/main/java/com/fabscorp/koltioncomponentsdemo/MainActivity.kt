package com.fabscorp.koltioncomponentsdemo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.fabscorp.koltioncomponentsdemo.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var bindding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindding.root)

        supportActionBar?.hide()

        bindding.buttonToast.setOnClickListener(this)
        bindding.buttonSnack.setOnClickListener(this)
        bindding.buttonGetSpinner.setOnClickListener(this)
        bindding.buttonSetSpinner.setOnClickListener(this)

        laodSpinner()
    }

    private fun laodSpinner() {
        val list = listOf("Grams", "Kg", "Pounds", "Ounces")

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
        bindding.spinnerDynamic.adapter = adapter

    }

    override fun onClick(v: View) {
        when (v.id) {
            bindding.buttonToast.id -> {
                val toast = Toast.makeText(this, "Toast me!", Toast.LENGTH_SHORT)
                toast.show()
            }

            bindding.buttonSnack.id -> {
                val snack = Snackbar.make(bindding.linearRoot, "Snack me!", Snackbar.LENGTH_SHORT)
                snack.setTextColor(Color.BLUE)
                snack.animationMode = Snackbar.ANIMATION_MODE_SLIDE
                snack.setActionTextColor(Color.GREEN)
                snack.setAction("Undo", View.OnClickListener {
                    Snackbar.make(bindding.linearRoot, "Succsess!", Snackbar.LENGTH_SHORT)
                        .setAnimationMode(Snackbar.ANIMATION_MODE_FADE)
                        .show()
                })
                snack.setBackgroundTint(Color.GRAY)
                snack.show()
            }

            bindding.buttonGetSpinner.id -> {
                // To get spinner infos.
                val str = bindding.spinnerDynamic.selectedItem
                val id1 = bindding.spinnerDynamic.selectedItemId
                val id2 = bindding.spinnerDynamic.selectedItemPosition

            }
            bindding.buttonSetSpinner.id -> {
                //To set spinner finfo.
                bindding.spinnerDynamic.setSelection(2)
            }

        }
    }
}