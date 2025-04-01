package no.kristiania.androidprogrammeringeksamen.data.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import no.kristiania.androidprogrammeringeksamen.data.Origin

object CharacterTypeConverter {
    private val gson = Gson()

    @TypeConverter
    @JvmStatic
    fun fromOrigin(origin: Origin?): String {
        return gson.toJson(origin)
    }

    @TypeConverter
    @JvmStatic
    fun toOrigin(data: String): Origin {
        return gson.fromJson(data, Origin::class.java)
    }
}