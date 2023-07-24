package com.example.pdfreader

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.barteksc.pdfviewer.PDFView


class ViewPdfActivity : AppCompatActivity() {
    lateinit var pdfView: PDFView
    val PDF_SELECTION_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pdf)
        pdfView = findViewById(R.id.pdfView)
        selectPdfFromStorage()
    }

    private fun selectPdfFromStorage() {
        Toast.makeText(this@ViewPdfActivity, "Select PDF From File", Toast.LENGTH_SHORT).show()
        val browseStorage = Intent(Intent.ACTION_GET_CONTENT)
        browseStorage.type = "application/pdf"
        startActivityForResult(Intent.createChooser(browseStorage, "Select pdf"), PDF_SELECTION_CODE)
    }

    private fun showPdfFromUri(uri: Uri) {
        pdfView.fromUri(uri)
            .defaultPage(0)
            .spacing(10)
            .load()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PDF_SELECTION_CODE && resultCode == Activity.RESULT_OK && data != null) {
            showPdfFromUri(data.data!!)
        }
    }
}
