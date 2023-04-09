/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package don;
import java.awt.BorderLayout;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.teamdev.jxmaps.swing.MapView;

import com.teamdev.jxmaps.*;
/**
 *
 * @author 21629
 */
public class Mapa extends MapView {
    private Map map;

    public Mapa(String nName, double lat,double log){
  JFrame frame = new JFrame(nName);  
    setOnMapReadyHandler(new MapReadyHandler() {
			@Override
			public void onMapReady(MapStatus status) {
				// Check if the map is loaded correctly
				if (status == MapStatus.MAP_STATUS_OK) {
					// Getting the associated map object
					map = getMap();
					MapOptions mapOptions = new MapOptions();
					MapTypeControlOptions controlOptions = new MapTypeControlOptions();
					controlOptions.setPosition(ControlPosition.BOTTOM_LEFT);
					mapOptions.setMapTypeControlOptions(controlOptions);
					
					map.setOptions(mapOptions);
					map.setCenter(new LatLng(lat, log));
					map.setZoom(15);
                                        Marker mark = new Marker(map);
                                        mark.setPosition(map.getCenter());

				}
			}
		});
    frame.add(this, BorderLayout.CENTER);
		frame.setSize(700, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
    
    }
}
