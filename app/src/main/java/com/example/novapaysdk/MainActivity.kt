package com.example.novapaysdk

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.launch
import com.novapaysdk.presentation.PaymentDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val session = findViewById<EditText>(R.id.etPrice)
        val btPay = findViewById<MaterialButton>(R.id.btPay)
        btPay.setOnClickListener {
            PaymentDialog.show(
                fragmentActivity = this,
                sessionId = session.text.toString(),
//                sessionId = "71d7df4a-1cf8-4e64-925f-14f2eeea5a63",
                paymentStatusCallback = { status -> showMessage(status, session.text.toString()) },
                bottomSheetStatusCallback = { status ->
                    Toast.makeText(
                        this,
                        status,
                        Toast.LENGTH_LONG
                    ).show()
                }
            )
        }
    }

    private fun showMessage(paymentStatus: String, session: String) {
        Toast.makeText(
            this,
            paymentStatus,
            Toast.LENGTH_LONG
        ).show()

        lifecycleScope.launch {
            try {
                val result = PaymentDialog.getSession(session)
                Log.d("Payment", "Session status: $result")
            } catch (e: Exception) {
                Log.e("Payment", "Error: ${e.message}")
            }
        }

        PaymentDialog.dismiss(this)
    }
}