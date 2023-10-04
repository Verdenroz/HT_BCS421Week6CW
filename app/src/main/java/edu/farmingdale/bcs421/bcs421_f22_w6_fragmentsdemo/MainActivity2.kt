package edu.farmingdale.bcs421.bcs421_f22_w6_fragmentsdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    lateinit var mBtn1:Button
    lateinit var mBtn2:Button
    lateinit var mTV:TextView
    lateinit var mEditText:EditText
    lateinit var mSeekBar:SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        mBtn1 = findViewById(R.id.btn1);
        mBtn2 = findViewById(R.id.btn2);
        mTV = findViewById(R.id.tv1)
        mEditText = findViewById(R.id.editText01)
        mSeekBar = findViewById(R.id.seekBar)
        var frgmnt01 = Fragment01()
        var frgmnt02 = Fragment02()

        mSeekBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seek: SeekBar,
                progress: Int, fromUser: Boolean
            ) {
                mEditText.setText(progress.toString())
                mTV.textSize = progress.toFloat()

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                mTV.textSize = p0?.progress?.toFloat()!!
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                mTV.textSize = p0?.progress?.toFloat()!!
            }
        })


        mBtn1.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.framelayout1, frgmnt01)
                commit()
            }
        }
        mBtn2.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.framelayout1, frgmnt02)
                commit()
            }
            readFromSharedPref()
        }
    }

    private fun readFromSharedPref(){
        var sharedPref= getSharedPreferences(MainActivity().SHAREDPREF_FILENAME, MODE_PRIVATE)
        mTV.setText(sharedPref.getString("KEY", "not forund"))
    }
}