package com.art.searchengine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.art.searchengine.DetailCharacterActivity.Companion.EXTRA_ID
import com.art.searchengine.databinding.ActivityRickandmortyappBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RickAndMortyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRickandmortyappBinding
    private lateinit var retrofit: Retrofit

    private lateinit var adapter: RickAndMortyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRickandmortyappBinding.inflate(layoutInflater)
        setContentView(binding.root)

        retrofit = getRetrofit()
        initUI()
    }

    private fun initUI() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?) = false
        })

        adapter = RickAndMortyAdapter { characterId -> navigateToDetail(characterId)}
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun searchByName(query: String) {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse: Response<RickAndMortyDataResponse> =
                retrofit.create(ApiService::class.java).getCharacter(query)
            if (myResponse.isSuccessful) {
                val response: RickAndMortyDataResponse? = myResponse.body()
                if (response != null) {
                    runOnUiThread {
                        adapter.updateList(response.rickAndMorty)
                        binding.progressBar.isVisible = false
                    }
                }
            } else {
                Log.i("sorayi", "no funciona")
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun navigateToDetail(id: String) {
        val intent = Intent(this, DetailCharacterActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        startActivity(intent)
    }
}