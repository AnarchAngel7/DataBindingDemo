package com.example.bindingdemo

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.bindingdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val myBio: MyBio = MyBio()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.myBio = myBio
        binding.doneButton.setOnClickListener { addBio(it) }

        binding.bioEdit.setOnKeyListener { v, keyCode, _ ->
            handleKeyEvent(
                v,
                keyCode
            )
        }

    }

    private fun addBio(view: View) {
        binding.apply {
            myBio?.name = nameEdit.text.toString()
            invalidateAll()
            nameEdit.visibility = View.GONE
            view.visibility = View.GONE
            nameText.visibility = View.VISIBLE

            myBio?.nickname = nicknameEdit.text.toString()
            invalidateAll()
            nicknameEdit.visibility = View.GONE
            view.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE

            myBio?.bio = bioEdit.text.toString()
            invalidateAll()
            bioEdit.visibility = View.GONE
            view.visibility = View.GONE
            bioText.visibility = View.VISIBLE
        }
    }

    private fun handleKeyEvent(v: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(v.windowToken, 0)
            return true
        }
        return false
    }
}