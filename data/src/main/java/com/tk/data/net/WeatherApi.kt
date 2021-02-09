package com.tk.data.net

class WeatherApi {
    val API_KEY: String get() = "d7687d279957e7939c1db6b087127c89"
    val BASE_URL: String get() = "http://api.openweathermap.org/data/2.5/"
    val ICON_BASE_URL: String get() = "http://openweathermap.org/img/wn/"

    fun getIconUrl(iconData: String): String {
        return "$ICON_BASE_URL$iconData.png"
    }
}