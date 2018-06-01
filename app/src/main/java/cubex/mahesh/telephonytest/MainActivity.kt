package cubex.mahesh.telephonytest

import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {
        var et1:EditText? = null
        var et2:EditText? = null
        var et3:EditText? = null
        var et4:EditText? = null
        var et5:EditText? = null
        var u:Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        et1 = findViewById(R.id.et1)
        et2 = findViewById(R.id.et2)
        et3 = findViewById(R.id.et3)
        et4 = findViewById(R.id.et4)
        et5 = findViewById(R.id.et5)

    }

    fun sendSMS(v:View){
      var list =   et1?.text.toString().split(",")
        for ( num in list ) {
            var sendIntent = Intent(this,
                    SendActivity::class.java)
            var deliverIntent = Intent(this,
                    DeliverActivity::class.java)
            var psIntent = PendingIntent.getActivity(this,
                    0, sendIntent, 0)
            var pdIntent = PendingIntent.getActivity(this,
                    0, deliverIntent, 0)
            var sManager = SmsManager.getDefault()
            sManager.sendTextMessage(num,
                    null,
                    et2?.text.toString(),
                    psIntent, pdIntent)
        }
    }
    fun call(v:View){

        var i = Intent( )
        i.setAction(Intent.ACTION_CALL)
        i.setData(Uri.parse("tel:"+
                                et1?.text.toString()))
        startActivity(i)
    }
    fun javaMail(v:View)
    {
            var task = LongOperation(et3?.text.toString(),
                 et4?.text.toString(),et5?.text.toString())
            task.execute( )
    }
    fun sendMail(v:View)
    {
        var i = Intent( )
        i.setAction(Intent.ACTION_SEND)
        i.putExtra(Intent.EXTRA_EMAIL,
                        arrayOf(et3?.text.toString()))
        i.putExtra(Intent.EXTRA_SUBJECT,
                                        et4?.text.toString())
        i.putExtra(Intent.EXTRA_TEXT,
                                         et5?.text.toString())
        i.putExtra(Intent.EXTRA_STREAM, u)
        i.setType("message/rfc822")
        startActivity(Intent.createChooser(i,
                "Select Any Email Client ...."))
    }
    fun attach(v:View){
        var i = Intent( )
        i.setAction(Intent.ACTION_GET_CONTENT)
        i.setType("*/*")
        startActivityForResult(i,123)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        u = data?.data
    }
}
