package ml.kwars.codecheatsheet

import android.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import ml.kwars.codecheatsheet.databinding.ActivityMainBinding
import retrofit2.HttpException
import java.io.IOException
import androidx.annotation.NonNull





class LanguagePartActivity: AppCompatActivity()  {
    private val TAG = "LanguagePartActivity"
    private lateinit var binding: ActivityMainBinding
    private lateinit var languagePartAdapter: LanguagePartAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecycleView()

        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)


        lifecycleScope.launchWhenCreated {
            val response = try {
                val lang:String = intent.getStringExtra("lang").toString()

                RetrofitInstance.api.getLanguagePart(lang);
            }catch (e: IOException){
                Log.e(TAG, "IOException, no internet")
                Log.e(TAG, e.toString())
                return@launchWhenCreated
            }catch (e: HttpException){
                Log.e(TAG, "HttpException, wrong response")
                Log.e(TAG, e.toString())
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body()!=null){
                languagePartAdapter.languageParts = response.body()!!
            }else{
                Log.e(TAG, "Response not successful")
                Log.e(TAG, response.toString())

                return@launchWhenCreated
            }
        }
    }

    private fun setupRecycleView() = binding.rvProgrammingLanguage.apply {
        languagePartAdapter = LanguagePartAdapter()
        languagePartAdapter.onItemClick = {languagePart ->
            val lang:String = intent.getStringExtra("lang").toString()

            val intent = Intent(this@LanguagePartActivity, LanguageFieldActivity::class.java)
            intent.putExtra("lang", lang)
            intent.putExtra("part", languagePart.name)
            startActivity(intent)
            Log.d(TAG,languagePart.name)
        }
        adapter = languagePartAdapter
        layoutManager = LinearLayoutManager(this@LanguagePartActivity)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}