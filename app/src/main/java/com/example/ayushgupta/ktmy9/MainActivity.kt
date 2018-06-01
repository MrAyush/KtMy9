package com.example.ayushgupta.ktmy9

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.telephony.SmsManager
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var et1:EditText? = null
    var et2:EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val statusCall = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE)
        val statusSms = ContextCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS)
        getPermission(statusCall,statusSms)
        et1 = findViewById(R.id.et1)
        et2 = findViewById(R.id.et2)
    }

    private fun getPermission(statusCall:Int, statusSms:Int) {
        if (statusSms == PackageManager.PERMISSION_DENIED || statusCall == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.SEND_SMS,android.Manifest.permission.CALL_PHONE), 100)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[1] == PackageManager.PERMISSION_DENIED || grantResults[0] == PackageManager.PERMISSION_DENIED){
            Toast.makeText(this,"Permission needed to continue!!",Toast.LENGTH_SHORT).show()
        }
    }

    fun sendSms(v: View) {
        val sManager = SmsManager.getDefault()
        sManager.sendTextMessage(et1!!.text.toString(),null,et2!!.text.toString(),null,null)
    }

    fun call(v: View) {
        val intent = Intent()
        intent.action = Intent.ACTION_CALL
        intent.data = Uri.parse("tel:${et1?.text.toString()}")
        startActivity(intent)
    }
}
