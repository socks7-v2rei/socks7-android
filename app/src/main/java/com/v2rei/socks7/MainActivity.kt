package com.v2rei.socks7

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var inputServer: TextInputEditText
    private lateinit var inputPort: TextInputEditText
    private lateinit var inputUser: TextInputEditText
    private lateinit var inputPass: TextInputEditText
    private lateinit var txtLink: TextView
    private lateinit var btnSave: MaterialButton
    private lateinit var btnCopy: MaterialButton
    private lateinit var btnShare: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputServer = findViewById(R.id.inputServer)
        inputPort = findViewById(R.id.inputPort)
        inputUser = findViewById(R.id.inputUser)
        inputPass = findViewById(R.id.inputPass)
        txtLink = findViewById(R.id.txtLink)
        btnSave = findViewById(R.id.btnSave)
        btnCopy = findViewById(R.id.btnCopy)
        btnShare = findViewById(R.id.btnShare)

        loadConfig()
        updateLink()

        btnSave.setOnClickListener {
            saveConfig()
            updateLink()
            Toast.makeText(this, "Config saved", Toast.LENGTH_SHORT).show()
        }

        btnCopy.setOnClickListener {
            val link = buildLink()
            val cm = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            cm.setPrimaryClip(ClipData.newPlainText("socks5", link))
            Toast.makeText(this, "Link copied", Toast.LENGTH_SHORT).show()
        }

        btnShare.setOnClickListener {
            val link = buildLink()
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, "Socks7 / V2rei\n$link")
            }
            startActivity(Intent.createChooser(intent, "Share proxy"))
        }
    }

    private fun buildLink(): String {
        val server = inputServer.text?.toString()?.trim().orEmpty()
        val port = inputPort.text?.toString()?.trim().orEmpty().ifEmpty { "7777" }
        val user = inputUser.text?.toString()?.trim().orEmpty()
        val pass = inputPass.text?.toString()?.trim().orEmpty()
        return if (user.isNotEmpty() && pass.isNotEmpty()) {
            "socks5://$user:$pass@$server:$port"
        } else {
            "socks5://$server:$port"
        }
    }

    private fun updateLink() {
        txtLink.text = buildLink()
    }

    private fun saveConfig() {
        val prefs = getSharedPreferences("socks7", Context.MODE_PRIVATE)
        prefs.edit()
            .putString("server", inputServer.text?.toString()?.trim())
            .putString("port", inputPort.text?.toString()?.trim())
            .putString("user", inputUser.text?.toString()?.trim())
            .putString("pass", inputPass.text?.toString()?.trim())
            .apply()
    }

    private fun loadConfig() {
        val prefs = getSharedPreferences("socks7", Context.MODE_PRIVATE)
        inputServer.setText(prefs.getString("server", "193.233.218.5"))
        inputPort.setText(prefs.getString("port", "7777"))
        inputUser.setText(prefs.getString("user", ""))
        inputPass.setText(prefs.getString("pass", ""))
    }
}
