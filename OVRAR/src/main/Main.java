/*
 * Copyright (c) 2000-2017 TeamDev Ltd. All rights reserved.
 * Use is subject to Apache 2.0 license terms.
 */
package main;

import DataAccess.ControlDataFlows;
import com.teamdev.jxmaps.*;
import com.teamdev.jxmaps.DirectionsStep.ArrayProperty;
import com.teamdev.jxmaps.swing.MapView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 */
public class Main extends MapView {

    ControlDataFlows ctrData;
    ControlWay ctrWay;
    Map map;

    public Main() {
        initMapDetail();
        ctrData = new ControlDataFlows();
        ctrWay = new ControlWay(ctrData);
        initWay(map);
    }

    private void initMapDetail() {
        // Setting of a ready handler to MapView object. onMapReady will be called when map initialization is done and
        // the map object is ready to use. Current implementation of onMapReady customizes the map object.
        setOnMapReadyHandler(new MapReadyHandler() {
            @Override
            public void onMapReady(MapStatus status) {
                // Check if the map is loaded correctly
                if (status == MapStatus.MAP_STATUS_OK) {
                    // Getting the associated map object
                    map = getMap();
                    initMap(map);
                    
                }
            }
        });
        
    }

    private void initWay(Map map) {
        ctrWay.addRoute1(map);
        ctrWay.addRoute2(map);
        ctrWay.addRoute2A(map);
        ctrWay.addRoute3(map);
        ctrWay.addRoute4(map);
        ctrWay.addRoute5(map);
        ctrWay.addRoute6(map);
        ctrWay.addRoute7(map);
        ctrWay.addRoute8(map);
        ctrWay.addCircleMap(map);
    }

    private void initMap(Map map) {
        // Creating a map options object
        MapOptions mapOptions = new MapOptions();
        // Creating a map type control options object
        MapTypeControlOptions controlOptions = new MapTypeControlOptions();
        // Changing position of the map type control
        controlOptions.setPosition(ControlPosition.TOP_RIGHT);
        // Setting map type control options
        mapOptions.setMapTypeControlOptions(controlOptions);
        // Setting map options
        map.setOptions(mapOptions);
        // Setting the map center
        map.setCenter(new LatLng(21.029306, 105.834560));
        // Setting initial zoom value
        map.setZoom(12.0);
    }

    public static void main(String[] args) {
        final Main sample = new Main();
//        sample.initMapDetail();
        JFrame frame = new JFrame("OVRASYR");

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(sample, BorderLayout.CENTER);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
