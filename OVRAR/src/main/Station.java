/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author MTC
 */
public class Station {
    private String name;
    private int depot;
    private int node;
    private double coordinatesX;
    private double coordinatesY;

    public Station() {
    }

    public Station(String name, int depot, int node, double coordinatesX, double coordinatesY) {
        this.name = name;
        this.depot = depot;
        this.node = node;
        this.coordinatesX = coordinatesX;
        this.coordinatesY = coordinatesY;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDepot() {
        return depot;
    }

    public void setDepot(int depot) {
        this.depot = depot;
    }

    public int getNode() {
        return node;
    }

    public void setNode(int node) {
        this.node = node;
    }

    public double getCoordinatesX() {
        return coordinatesX;
    }

    public void setCoordinatesX(double coordinatesX) {
        this.coordinatesX = coordinatesX;
    }

    public double getCoordinatesY() {
        return coordinatesY;
    }

    public void setCoordinatesY(double coordinatesY) {
        this.coordinatesY = coordinatesY;
    }
    
    
}
