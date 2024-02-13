package org.example.frontend.ui.pantallas

import javafx.application.Platform
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Alert
import javafx.scene.control.ListView
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import kotlinx.coroutines.*
import org.example.frontend.common.ServiceResult
import org.example.frontend.data.LoginRepository
import org.example.frontend.data.PeliculasRepository
import org.example.frontend.data.modelo.AuthenticationRequest
import org.example.frontend.data.retrofit.CacheAuthorization
import org.example.peliculas.type.Pelicula
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import java.net.URL
import java.util.*
import org.example.frontend.domain.modelo.Pelicula as domainPelicula

@Component
class Pantalla1Controller(
    private val peliculasRepository: PeliculasRepository,
    private val loginRepository: LoginRepository,
    private val cacheAuthorization: CacheAuthorization,
    @Qualifier("mainDispatcher")
    private  val mainDispatcher: CoroutineDispatcher = Dispatchers.Main

) : Initializable {

    @FXML
    private lateinit var peliculas: ListView<org.example.frontend.domain.modelo.Pelicula>

    @FXML
    private lateinit var resultados: TextArea
    @FXML
    private lateinit var tituloPelicula: TextField

    override fun initialize(location: URL?, resources: ResourceBundle?) {


    }

    fun login() {
        CoroutineScope(Dispatchers.IO).launch {
            when (val response = loginRepository.login(
                AuthenticationRequest(
                    username = "admin",
                    password = "1234"
                )
            )) {
                is ServiceResult.ErrorResult -> withContext(Dispatchers.Main) {
                    Alert(Alert.AlertType.ERROR, response.mesagge).showAndWait()
                }

                is ServiceResult.Success -> {
                    resultados.text = response.data.accessToken
                    cacheAuthorization.accesToken = response.data.accessToken
                    cacheAuthorization.refreshToken = response.data.refreshToken
                }
            }

        }
    }

    fun getPeliculas() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = peliculasRepository.getPeliculas()
            peliculas.items.addAll(response)
        }

    }

    fun addPeliculas() {
        CoroutineScope(Dispatchers.IO).launch {

           when (val result = peliculasRepository.addPelicula(
                    domainPelicula(
                        titulo = tituloPelicula.text
                    )
                )) {
               is ServiceResult.ErrorResult -> withContext(Dispatchers.Main) {
                   Alert(Alert.AlertType.ERROR, result.mesagge).showAndWait()
               }
               is ServiceResult.Success -> peliculas.items.addAll(result.data)
           }


        }

    }


}
