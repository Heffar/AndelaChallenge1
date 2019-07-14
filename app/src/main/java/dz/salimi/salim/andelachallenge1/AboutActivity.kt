package dz.salimi.salim.andelachallenge1

import android.net.http.SslError
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {

    private val url = "https://andela.com/alc/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        webview.webViewClient = WebViewController()
        webview.loadUrl(url)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}

private class WebViewController : WebViewClient(){
    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        view?.loadUrl(url)
        return true
    }

    override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(view!!.context)
        var message = "SSL Certificate error."

        when (error!!.primaryError) {
             SslError.SSL_UNTRUSTED -> message = "The certificate authority is not trusted."
             SslError.SSL_EXPIRED -> message = "The certificate has expired."
             SslError.SSL_IDMISMATCH -> message = "The certificate Hostname mismatch."
             SslError.SSL_NOTYETVALID -> message = "The certificate is not yet valid."
        }
        message += " Do you want to continue anyway?"

        builder.setTitle("SSL Certificate Error")
        builder.setMessage(message)
        builder.setPositiveButton("continue") { _, _ ->
            handler!!.proceed()
        }
        builder.setNegativeButton("cancel") { _, _ ->
            handler!!.cancel()
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}
