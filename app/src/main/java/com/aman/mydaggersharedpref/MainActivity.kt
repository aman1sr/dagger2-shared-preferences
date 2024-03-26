package com.aman.mydaggersharedpref

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.aman.mydaggersharedpref.databinding.ActivityMainBinding
import javax.inject.Inject

/*
* geek4Geeks: https://www.geeksforgeeks.org/dependency-injection-with-dagger-2-in-android/
* sharedPref doc: https://developer.android.com/reference/android/content/SharedPreferences
*
* */
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var sharedPref: SharedPreferences
    lateinit var sharedPrefComp: SharedPreferenceComponent

    companion object {
        val TAG = "SharedPreferences_d"
        val SHARED_PREF_KEY = "SHARED_PREF_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        injectDagger()

        binding.saveBtn.setOnClickListener {
            sharedPref.edit().apply {
                putString(SHARED_PREF_KEY, binding.inputField.text.toString().trim())
                commit()
            }
            Toast.makeText(this, "Saved Successfullly", Toast.LENGTH_SHORT).show()
        }
        binding.getBtn.setOnClickListener {
            binding.outputField.text = sharedPref.getString(SHARED_PREF_KEY, "")
            Log.d(TAG, "onCreate:getBtn : ${sharedPref.getString(SHARED_PREF_KEY, "")}")
        }

    }

    private fun injectDagger() {
        sharedPrefComp = DaggerSharedPreferenceComponent.builder()
            .sharedPreferenceModule(SharedPreferenceModule(this))
            .build()
        sharedPrefComp.inject(this)
    }
}