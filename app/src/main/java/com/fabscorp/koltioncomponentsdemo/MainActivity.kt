package com.fabscorp.koltioncomponentsdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.fabscorp.koltioncomponentsdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var bindding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindding.root)

        supportActionBar?.hide()

        bindding.buttonToast.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            bindding.buttonToast.id -> {
                val toast = Toast.makeText(this, "Toast me!", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }
}