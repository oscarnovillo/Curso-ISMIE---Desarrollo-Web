package org.example.frontend.ui.pantallas

import javafx.application.Platform
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Alert
import javafx.scene.control.ListView
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.example.frontend.data.LoginRepository
import org.example.frontend.data.PeliculasRepository
import org.example.frontend.data.modelo.AuthenticationRequest
import org.example.frontend.data.retrofit.CacheAuthorization
import org.example.peliculas.type.Pelicula
import org.springframework.stereotype.Component
import java.net.URL
import java.util.*
import org.example.frontend.domain.modelo.Pelicula as domainPelicula

@Component
class Pantalla1Controller(
    private val peliculasRepository: PeliculasRepository,
    private val loginRepository: LoginRepository,
    private val cacheAuthorization: CacheAuthorization,

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
        GlobalScope.launch {
            val response = loginRepository.login(
                AuthenticationRequest(
                    username = "admin",
                    password = "1234"
                )

            )

            val alert : ()-> Unit = {
                Alert(Alert.AlertType.ERROR,"usuario no valido").showAndWait()
            }
            response?.let{

                cacheAuthorization.accesToken = it.accessToken
                cacheAuthorization.refreshToken = it.refreshToken
                resultados.text = it.accessToken
            } ?: alert

        }
    }

    fun getPeliculas() {
        GlobalScope.launch {
            val response = peliculasRepository.getPeliculas()
            peliculas.items.addAll(response)
        }

    }

    fun addPeliculas() {
        GlobalScope.launch {
            try {
                val response = peliculasRepository.addPelicula(
                    domainPelicula(
                        titulo = tituloPelicula.text
                    )
                )
                response?.let {
                    peliculas.items.add(it)
                }
            }
            catch (e: Exception) {
                Platform.runLater {
                    Alert(Alert.AlertType.ERROR, e.message).showAndWait()
                }
            }
        }

    }


}
