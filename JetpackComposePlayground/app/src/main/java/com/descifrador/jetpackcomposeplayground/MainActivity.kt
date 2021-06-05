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
import com.descifrador.jetpackcomposeplayground.domain.model.PokemonDetails
import com.descifrador.jetpackcomposeplayground.network.model.PokemonDetailsEntity
import com.descifrador.jetpackcomposeplayground.network.model.PokemonDetailsEntityMapper
import com.descifrador.jetpackcomposeplayground.network.service.NetworkingService
import com.descifrador.jetpackcomposeplayground.ui.theme.JetpackComposePlaygroundTheme
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.IO_PARALLELISM_PROPERTY_NAME
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val service = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/pokemon/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(NetworkingService::class.java)

        CoroutineScope(IO).launch{
            val response = service.getPokemonDetails("ditto")
            Log.d("RESPONSE", "${response.abilities} ${response.stats!![0].base_stat} " +
                    "${response.stats!![0].stat!!.name}  ")
            val mapper = PokemonDetailsEntityMapper()
            val pokemonDetails = mapper.mapFromEntity(response)
            Log.d("RESPONSE","${pokemonDetails.abilities} ${pokemonDetails.stats!![0].base_stat} \" +\n" +
                    "                    \"${pokemonDetails.stats!![0].stat!!.name} ")

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
