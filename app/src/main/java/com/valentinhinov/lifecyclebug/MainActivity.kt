package com.valentinhinov.lifecyclebug

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    companion object {
        private const val WRITE_STORAGE_REQUEST_CODE = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener {
            val permission = Manifest.permission.WRITE_EXTERNAL_STORAGE

            when {
                isPermissionGranted(permission) -> Toast.makeText(this, "Ungrant permission and relaunch to test", Toast.LENGTH_LONG).show()
                shouldShowRequestPermissionRationale(permission) ->
                    AlertDialog.Builder(this)
                        .setTitle("Grant permission")
                        .setMessage("Grant permission and check onResume call")
                        .setPositiveButton("Grant") { _, _ ->
                            requestPermissions(arrayOf(permission), WRITE_STORAGE_REQUEST_CODE)
                        }
                        .show()
                else -> requestPermissions(arrayOf(permission), WRITE_STORAGE_REQUEST_CODE)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "OnResume hit, current lifecycle state is ${lifecycle.currentState}", Toast.LENGTH_SHORT).show()
    }

    private fun Context.isPermissionGranted(permission: String): Boolean {
        return checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }
}
