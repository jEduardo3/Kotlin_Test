package com.art.searchengine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.art.searchengine.databinding.ActivityDetailCharacterBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailCharacterActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityDetailCharacterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id: String = intent.getStringExtra(EXTRA_ID).orEmpty()

        getCharacterInformation(id)
    }


    private fun getCharacterInformation(id: String) {
        Log.i("Funciona, el id es:", id)

        CoroutineScope(Dispatchers.IO).launch {
            val characterDetail =
                getRetrofit().create(ApiService::class.java).getCharacterDetail(id)

            if (characterDetail.body() != null) {
                runOnUiThread {
                    createUI(characterDetail.body()!!)
                }
            }

        }
    }

    private fun createUI(character: RickAndMortyDetailResponse) {
        binding.tvName.text = character.name
        binding.tvStatus.text = character.status
        binding.tvSpecie.text = character.species
        binding.tvLocation.text = character.location.locationName
        binding.tvOrigin.text = character.origin.originName
        Picasso.get().load(character.image).into(binding.ivChar)
        Picasso.get().load(character.location.locationImage).into(binding.ivLocation)
        
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://rickandmortyapi.com/api/character/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
