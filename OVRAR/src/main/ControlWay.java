/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import DataAccess.ControlDataFlows;
import com.teamdev.jxmaps.Circle;
import com.teamdev.jxmaps.CircleOptions;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.Polyline;
import com.teamdev.jxmaps.PolylineOptions;
import java.util.ArrayList;

/**
 *
 * @author MTC
 */
public class ControlWay {

    ControlDataFlows ctrData;

    public ControlWay(ControlDataFlows ctrData) {
        this.ctrData = ctrData;
    }

    public void addRoute1(Map map) {
        ArrayList<double[]> listPoint = ctrData.getListPointWay("1");
        ArrayList<double[]> listPointBranch = new ArrayList<>();
        listPointBranch.add(listPoint.get(0));
        listPointBranch.add(listPoint.get(1));
        listPointBranch.add(listPoint.get(2));
        listPointBranch.add(listPoint.get(7));
        listPoint.remove(listPoint.get(0));
        listPoint.remove(listPoint.get(0));
        listPoint.remove(listPoint.get(0));
        
        LatLng[] path1 = changPointToPath(listPointBranch);
        addRailwayLine(path1, map, "#00FF00");
        
        LatLng[] path2 = changPointToPath(listPoint);
        addRailwayLine(path2, map, "#00FF00");
    }

    public void addRoute2(Map map) {
        ArrayList<double[]> listPoint = ctrData.getListPointWay("2");
        ArrayList<double[]> listPointBranch = new ArrayList<>();
        listPointBranch.add(listPoint.get(4));
        listPointBranch.add(listPoint.get(5));
        listPoint.remove(listPoint.get(4));
        
        LatLng[] path1 = changPointToPath(listPointBranch);
        addRailwayLine(path1, map, "#FF00FF");
        
        LatLng[] path2 = changPointToPath(listPoint);
        addRailwayLine(path2, map, "#FF00FF");
    }

    public void addRoute2A(Map map) {
        ArrayList<double[]> listPoint = ctrData.getListPointWay("2A");
        LatLng[] path = changPointToPath(listPoint);
        addRailwayLine(path, map, "#FF5733");
    }

    public void addRoute3(Map map) {
        ArrayList<double[]> listPoint = ctrData.getListPointWay("3");
        LatLng[] path = changPointToPath(listPoint);
        addRailwayLine(path, map, "#FFFF00");
    }

    public void addRoute4(Map map) {
        ArrayList<double[]> listPoint = ctrData.getListPointWay("4");
        listPoint.add(listPoint.get(1));
        LatLng[] path = changPointToPath(listPoint);
        addRailwayLine(path, map, "#800080");
    }

    public void addRoute5(Map map) {
        ArrayList<double[]> listPoint = ctrData.getListPointWay("5");
        LatLng[] path = changPointToPath(listPoint);
        addRailwayLine(path, map, "#0000FF");
    }

    public void addRoute6(Map map) {
        ArrayList<double[]> listPoint = ctrData.getListPointWay("6");
        LatLng[] path = changPointToPath(listPoint);
        addRailwayLine(path, map, "#00FFFF");
    }

    public void addRoute7(Map map) {
        ArrayList<double[]> listPoint = ctrData.getListPointWay("7");
        LatLng[] path = changPointToPath(listPoint);
        addRailwayLine(path, map, "#FF0000");
    }

    public void addRoute8(Map map) {
        ArrayList<double[]> listPoint = ctrData.getListPointWay("8");
        LatLng[] path = changPointToPath(listPoint);
        addRailwayLine(path, map, "#A04000");
    }

    public void addCircleMap(Map map) {
        ArrayList<Station> listStation = ctrData.getListStation();
        for (Station mem : listStation) {
            Circle circle = new Circle(map);
            LatLng center = new LatLng(mem.getCoordinatesX(), mem.getCoordinatesY());
            circle.setCenter(center);
            circle.setRadius(100.0);
            CircleOptions options;
            if (mem.getNode() > 0) {
                options = nodeOptionCircle();
            } else options = normOptionCircle();
            circle.setOptions(options);
        }
    }

    private CircleOptions normOptionCircle() {
        CircleOptions options = new CircleOptions();
        options.setStrokeColor("#515A5A");
        options.setFillColor("#FFFFFF");
        options.setFillOpacity(100.0);
        options.setStrokeOpacity(50.0);
        options.setStrokeWeight(1.0);
        return options;
    }
    
    private CircleOptions depotOptionCircle() {
        CircleOptions options = new CircleOptions();
        options.setStrokeColor("#FF0000");
        options.setFillColor("#FFFFFF");
        options.setFillOpacity(100.0);
        options.setStrokeOpacity(50.0);
        options.setStrokeWeight(3.0);
        options.setRadius(150.0);
        return options;
    }
    
    private CircleOptions nodeOptionCircle() {
        CircleOptions options = new CircleOptions();
        options.setStrokeColor("#0000FF");
        options.setFillColor("#FFFFFF");
        options.setFillOpacity(100.0);
        options.setStrokeOpacity(50.0);
        options.setStrokeWeight(3.0);
        options.setRadius(150.0);
        return options;
    }

    private void addRailwayLine(LatLng[] path, Map map, String color) {
        // Creating a new polyline object
        Polyline polyline = new Polyline(map);
        // Initializing the polyline with created path
        polyline.setPath(path);
        // Creating a polyline options object
        PolylineOptions options = new PolylineOptions();
        // Setting geodesic property value
        options.setGeodesic(true);
        // Setting stroke color value
        options.setStrokeColor(color);
        // Setting stroke opacity value
        options.setStrokeOpacity(1.0);
        // Setting stroke weight value
        options.setStrokeWeight(2.0);
        // Applying options to the polyline
        polyline.setOptions(options);
    }

    private LatLng[] changPointToPath(ArrayList<double[]> listPoint) {
        if (listPoint.isEmpty()) {
            return null;
        }
        LatLng[] path = new LatLng[listPoint.size()];
        for (int i = 0; i < listPoint.size(); i++) {
            path[i] = new LatLng(listPoint.get(i)[0], listPoint.get(i)[1]);
        }
        return path;
    }
}
