package com.descifrador.jetpackcomposeplayground

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.descifrador.jetpackcomposeplayground.mappers.PokemonDetailsDtoMapper
import com.descifrador.jetpackcomposeplayground.mappers.PokemonsListDtoMapper
import com.descifrador.jetpackcomposeplayground.network.service.NetworkingService
import com.descifrador.jetpackcomposeplayground.ui.theme.JetpackComposePlaygroundTheme
import com.descifrador.jetpackcomposeplayground.util.AppConstants
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val service = Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(NetworkingService::class.java)

        CoroutineScope(IO).launch{
            val response = service.getPokemonDetails("ditto")
            Log.d("RESPONSE - 1", "${response.abilities} ${response.stats!![0]
                .base_stat} " +
                    "${response.stats!![0].stat!!.name}  ")
            val mapper = PokemonDetailsDtoMapper()
            val pokemonDetails = mapper.mapToDomainModel(response)
            Log.d("RESPONSE - 2","${pokemonDetails.abilities} ${pokemonDetails.stats!![0]
                .base_stat} ${pokemonDetails.stats!![0].stat!!.name}")

        }

        CoroutineScope(IO).launch{
            Log.e("RETROFIT","${service.getPokemonList()}")
            val response = service.getPokemonList()
            Log.d("RESPONSE - 3", "${response.count} ${response.pokemons[0].name}")

            val mapper = PokemonsListDtoMapper()
            val pokemonList = mapper.mapToDomainModel(response)
            Log.d("RESPONSE - 4","${pokemonList.count} ${response.pokemons[0].name}")

        }


        setContent {
            JetpackComposePlaygroundTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview
@Composable
fun DefaultPreview() {
    JetpackComposePlaygroundTheme {
        Greeting("Android")
    }
}
