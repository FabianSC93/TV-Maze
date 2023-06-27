# TV-Maze

Esta aplicación muestra diferentes programas de televisión obtenidos de http://www.tvmaze.com/api#embedding, utilizando las siguientes API:

- http://api.tvmaze.com/schedule?country=US&date=<today>   ---> PROPORCIONA LOS PROGRAMAS DE TV DEL DÍA DE HOY
- http://api.tvmaze.com/search/shows?q=<query>   -----> PROPORCIONA UNA LISTA DE PROGRAMAS DE ACUERDO A UNA BÚSQUEDA
- http://api.tvmaze.com/shows/<id>    -----> PROPORCIONA EL DETALLE DE ALGÚN PROGRAMA DE ACUERDO A SU ID
- http://api.tvmaze.com/shows/<id>/cast    -----> PROPORCIONA LOS PARTICIPANTES DEL PROGRAMA
- https://api.tvmaze.com/shows/<id>/episodes    -----> PROPORCIONA LOS EPISODIOS DEL PROGRAMA

Dentro del código se implementó:

- Arquitectura MVVM
- Singleton
- Compose y XML
- Inyección de dependencias
- Consumo de API con Retrofit
- Clean architecture
- Programación reactiva
- Vista en Landscape y Portrait
- Viewbinding
- Custom Tab

Uso de librerías:

//Navigation
- implementation 'androidx.navigation:navigation-fragment-ktx:2.6.0'
- implementation 'androidx.navigation:navigation-ui-ktx:2.6.0'

//Compose
- implementation "androidx.activity:activity-compose:1.7.0"
- implementation "androidx.compose.ui:ui:1.5.0-alpha01"
- implementation "androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1"
- implementation "androidx.compose.material:material:1.5.0-alpha01"
- implementation "androidx.compose.ui:ui-tooling:1.5.0-alpha01"
- implementation "androidx.compose.ui:ui-tooling-preview:1.5.0-alpha01"
- implementation "androidx.compose.animation:animation:1.5.0-alpha01"
- implementation "io.coil-kt:coil-compose:1.4.0"

//Hilt
- implementation("com.google.dagger:hilt-android:2.44")
- kapt("com.google.dagger:hilt-android-compiler:2.44")

//Retrofit
- implementation 'com.squareup.retrofit2:retrofit:2.9.0'
- implementation 'com.squareup.retrofit2:converter-gson:2.9.0'

//Glide
- implementation 'com.github.bumptech.glide:glide:4.15.0'
- annotationProcessor 'com.github.bumptech.glide:compiler:4.15.0'

//Custom Tab
- implementation 'androidx.browser:browser:1.5.0'

Capturas Télefono:

![Telefono1](https://github.com/FabianSC93/TV-Maze/assets/87196367/a7505232-21c2-4ae0-95ec-9d1a5b6e7a2c)
![Telefono2](https://github.com/FabianSC93/TV-Maze/assets/87196367/a65ba2b2-db60-4564-baf7-e30613b25e36)
![Telefono3](https://github.com/FabianSC93/TV-Maze/assets/87196367/9430df0d-d93c-485b-92ce-9b9240d9528b)
![Telefono 4](https://github.com/FabianSC93/TV-Maze/assets/87196367/1e839b50-057c-4a04-8de1-72ebb6928972)
![Telefono 5](https://github.com/FabianSC93/TV-Maze/assets/87196367/05273d7e-8239-4d13-9161-46ffd42509d5)

Capturas Tablet:

![Tablet1](https://github.com/FabianSC93/TV-Maze/assets/87196367/60959e14-0747-4d6c-8bb0-06edf23ee881)
![Tablet2](https://github.com/FabianSC93/TV-Maze/assets/87196367/b3083253-18df-4aa2-9c40-bb67c982afcb)
![Tablet3](https://github.com/FabianSC93/TV-Maze/assets/87196367/708ee07a-7677-40f6-aaeb-ed54688f27c8)
![Tablet4](https://github.com/FabianSC93/TV-Maze/assets/87196367/174bcb45-91dc-4ae0-8cdf-46c214f806c2)
![Tablet5](https://github.com/FabianSC93/TV-Maze/assets/87196367/28667e85-5004-488e-8232-251c26a47ddb)
![Tablet6](https://github.com/FabianSC93/TV-Maze/assets/87196367/0836af20-397d-4b4c-acc6-91f51441f98d)
![Tablet7](https://github.com/FabianSC93/TV-Maze/assets/87196367/7d9cd99e-d8b6-470e-8a90-054516dfe1aa)
