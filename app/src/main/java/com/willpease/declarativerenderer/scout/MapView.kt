package com.willpease.declarativerenderer

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import android.view.View
import com.nabinbhandari.android.permissions.PermissionHandler
import com.nabinbhandari.android.permissions.Permissions
import com.willpease.declarativerenderer.renderer.*
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.MapView

/**
 * @author Will Pease
 * @date 3/10/18
 */

class MapView(context: Context) : Component(context) {

    var mapView: MapView? = null

    override fun componentDidMount() {

        // Request permissions on mount
        requestPermissions()

        mapView?.onResume()
    }

    override fun componentWillUnmount() {
        mapView?.onPause()
    }

    private fun requestPermissions() {
        val permissions = arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION
        )

        val permissionsHandler = object: PermissionHandler() {
            override fun onGranted() {
                update()
            }
        }

        Permissions.check(context, permissions,
                "External storage and location access are required to use the map.",
                Permissions.Options(), permissionsHandler
        )
    }

    private fun hasRequiredPermissions(): Boolean {
        val writeExternal = ContextCompat.checkSelfPermission(
                context, Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

        val accessLocation = ContextCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        return writeExternal && accessLocation
    }

    /**
     * Render a RelativeLayout with a Header, Content, and Footer inside.
     */
    override fun render(): Element<View> {

        // Only show map view when the app has the required permissions.
        return if(hasRequiredPermissions()) {
            E(::MapView, arrayOf(
                    p(MapView::setTileSource, TileSourceFactory.MAPNIK),
                    p(MapView::setMultiTouchControls, true)
            ), ref={mapView = it as MapView})
        } else {
            E(::View, arrayOf(
                    p(View::setBackgroundResource, R.color.transparent)
            ))
        }
    }

}
