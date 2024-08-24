package com.cinicaragua.mapaclinicas

import android.os.Bundle
import androidx.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import com.cinicaragua.mapaclinicas.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import org.osmdroid.config.Configuration.*
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val map = binding.mapaClinicas

        getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this))

        map.setTileSource(TileSourceFactory.MAPNIK)

        map.controller.setZoom(7.0)
        map.controller.setCenter(GeoPoint(12.13282, -86.2504))

        //Ejemplo de como crear los marcadores que representen los centros de salud
        //En teoria, ustedes aqui obtendrian la lista de lugares que estan ingresados en la base de datos y crearian un Marker por cada uno
        val marker = Marker(map)
        marker.position = GeoPoint(12.14574864059122, -86.29179228465924)
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        marker.setOnMarkerClickListener { marker, mapView ->

            //Aqui implementar que sucedera cuando le den click al marcador del lugar
            Snackbar.make(binding.root, "Hola", 1000).show()
            false

        }
        map.overlays.add(marker)

    }
}