package com.example.pokemonrecyclerview

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {

    private lateinit var rvPokemon: RecyclerView
    private val pokemonList = mutableListOf<Pokemon>()
    private lateinit var adapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvPokemon = findViewById(R.id.rvPokemon)
        rvPokemon.layoutManager = LinearLayoutManager(this)

        adapter = PokemonAdapter(pokemonList) { pokemon ->
            // Stretch feature: Toast on click
            Toast.makeText(
                this,
                "You tapped ${pokemon.name.replaceFirstChar { it.uppercase() }} (#${pokemon.id})",
                Toast.LENGTH_SHORT
            ).show()
        }

        rvPokemon.adapter = adapter

        fetchPokemonList()
    }

    private fun fetchPokemonList() {
        val client = AsyncHttpClient()
        val url = "https://pokeapi.co/api/v2/pokemon?limit=151"

        client.get(url, object : JsonHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Headers,
                json: JSON
            ) {
                // Make sure everything here runs on the main (UI) thread
                runOnUiThread {
                    try {
                        val results = json.jsonObject.getJSONArray("results")
                        pokemonList.clear()

                        for (i in 0 until results.length()) {
                            val obj = results.getJSONObject(i)
                            val name = obj.getString("name")
                            val url = obj.getString("url")

                            // Get ID from the end of the URL (e.g., "https://.../pokemon/1/")
                            val id = url.trimEnd('/').split("/").last().toInt()

                            val imageUrl =
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"

                            val pokemon = Pokemon(name, id, imageUrl)
                            pokemonList.add(pokemon)
                        }

                        adapter.notifyDataSetChanged()

                    } catch (e: Exception) {
                        e.printStackTrace()
                        Toast.makeText(
                            this@MainActivity,
                            "Error parsing Pokémon data",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                // Also move this Toast to the main thread
                runOnUiThread {
                    Toast.makeText(
                        this@MainActivity,
                        "Failed to load Pokémon list",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }
}
