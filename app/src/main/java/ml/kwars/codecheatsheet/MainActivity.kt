package ml.kwars.codecheatsheet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import ml.kwars.codecheatsheet.databinding.ActivityMainBinding
import retrofit2.HttpException
import java.io.IOException



class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding
    private lateinit var programmingLanguageAdapter: ProgrammingLanguageAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecycleView()

        lifecycleScope.launchWhenCreated {
            val response = try {
                RetrofitInstance.api.getProgrammingLanguage();
            }catch (e: IOException){
                Log.e(TAG, "IOException, no internet")
                Log.e(TAG, e.toString())
                return@launchWhenCreated
            }catch (e:HttpException){
                Log.e(TAG, "HttpException, wrong response")
                Log.e(TAG, e.toString())
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body()!=null){
                programmingLanguageAdapter.programmingLanguages = response.body()!!
            }else{
                Log.e(TAG, "Response not successful")
                Log.e(TAG, response.toString())

                return@launchWhenCreated
            }
        }
    }

    private fun setupRecycleView() = binding.rvProgrammingLanguage.apply {
        programmingLanguageAdapter = ProgrammingLanguageAdapter()
        programmingLanguageAdapter.onItemClick = {programmingLanguage ->
            Log.d(TAG,programmingLanguage.name)

            val intent = Intent(this@MainActivity, LanguagePartActivity::class.java)
            intent.putExtra("lang",programmingLanguage.name )
            startActivity(intent)
        }
        adapter = programmingLanguageAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }

}