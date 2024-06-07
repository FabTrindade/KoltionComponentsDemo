package com.fabscorp.koltioncomponentsdemo

import android.app.TimePickerDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CompoundButton
import android.widget.SeekBar
import android.widget.TimePicker
import android.widget.Toast
import com.fabscorp.koltioncomponentsdemo.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener,
    SeekBar.OnSeekBarChangeListener, CompoundButton.OnCheckedChangeListener,
    TimePickerDialog.OnTimeSetListener{
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
        bindding.buttonIncProgress.setOnClickListener(this)
        bindding.buttonDecProgress.setOnClickListener(this)
        bindding.buttonToogleProgress.setOnClickListener(this)
        bindding.buttonTimepicker.setOnClickListener(this)

        bindding.spinnerDynamic.onItemSelectedListener = this

        bindding.seekBar.setOnSeekBarChangeListener(this)
        bindding.seekBar.progress = 15 // Initial seekbar progress

        bindding.switchOnOff.setOnCheckedChangeListener(this)
        bindding.switchOnOff.isChecked = true

        bindding.checkbox.setOnCheckedChangeListener(this)
        bindding.checkbox.isChecked = true

        bindding.radioYes.setOnCheckedChangeListener(this)
    }

    private fun loadSpinner() {
        val list = listOf("Grams", "Kg", "Pounds", "Ounces")

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
        bindding.spinnerDynamic.adapter = adapter

    }

    override fun onClick(v: View) {
        when (v.id) {
            bindding.buttonToast.id -> {
                val toast = Toast.makeText(this, "Toast me!", Toast.LENGTH_SHORT)
                toast.show()

                // It is possible to manipulate the toast view, but it is deprecated
                // As an alternative, if customization is needed, snack bar is recommended.
                // Example: toast.view

                // Positioning - Works only up to API 29
                // Example: toast.setGravity(Gravity.TOP, 15, 50)
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

                loadSpinner()

            }

            bindding.buttonSetSpinner.id -> {
                //To set spinner finfo.
                //bindding.spinnerDynamic.setSelection(2)
                bindding.spinnerDynamic.adapter = null
            }

            bindding.buttonDecProgress.id -> {
                bindding.progressbar.incrementProgressBy(-5)
            }

            bindding.buttonIncProgress.id -> {
                bindding.progressbar.incrementProgressBy(5)
            }

            bindding.buttonToogleProgress.id -> {
                if (bindding.progressCircular.visibility == View.GONE) {
                    bindding.progressCircular.visibility = View.VISIBLE
                } else {
                    bindding.progressCircular.visibility = View.GONE
                }
            }
            
            bindding.buttonTimepicker.id -> {
                TimePickerDialog(this, this, 20, 15, true).show()
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
        // parent: AdapterView - Adapter where the selection occurred
        // view: View - Layout of the clicked element
        // position: Int - Position of the selected item in the list
        // id: Long - Position of the selected row

        Toast.makeText(this, "$position - $id", Toast.LENGTH_SHORT).show()

    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        Toast.makeText(this, "Nothing selected!", Toast.LENGTH_SHORT).show()
    }

    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        // fromUser is true when the change is made by touching the element
        // If the assignment is made by code, fromUser is false
        bindding.textSeekBar.text = "$progress - $fromUser"
    }

    /**
     * Seekbar - When the component starts to be dragged
     **/
    override fun onStartTrackingTouch(seekBar: SeekBar) {
        Toast.makeText(this, "Seekbar started!", Toast.LENGTH_SHORT).show()
    }

    /**
     * Seekbar - Handles end touch event on the Seekbar
     **/
    override fun onStopTrackingTouch(seekBar: SeekBar) {
        Toast.makeText(this, "Seekbar stopped!", Toast.LENGTH_SHORT).show()
    }

    override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
        when (buttonView.id) {
            R.id.switch_on_off -> {
                Toast.makeText(this, "Switch is ${if (isChecked) "on" else "off"}!", Toast.LENGTH_SHORT).show()
            }
            R.id.checkbox -> {
                Toast.makeText(this, "Checkbox is ${if (isChecked) "on" else "off"}!", Toast.LENGTH_SHORT).show()
            }

            R.id.radio_yes -> {
                Toast.makeText(this, "Radio Yes is ${if (isChecked) "on" else "off"}!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        bindding.textViewTimepicker.text = "$hourOfDay : $minute"
    }
}