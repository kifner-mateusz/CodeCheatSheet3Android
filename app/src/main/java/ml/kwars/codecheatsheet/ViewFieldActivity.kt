package ml.kwars.codecheatsheet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.lifecycleScope

class ViewFieldActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_field)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)



        lifecycleScope.launchWhenCreated {
            val short:String = intent.getStringExtra("short").toString()
            val content:String = intent.getStringExtra("content").toString()
            val shortText: TextView = findViewById(R.id.tvShort)
            shortText.text = short
            val contentText: TextView = findViewById(R.id.tvContent)
            contentText.text = content


        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}