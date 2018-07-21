/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import main.Station;

/**
 *
 * @author MTC
 */
public class ControlDataFlows {

    DataAccessObject dao;

    public ControlDataFlows() {        
        dao = new DataAccessObject();
    }

    public ArrayList<double[]> getListPointWay(String nameWay){
        ArrayList<double[]> listPoint = new ArrayList();
        //nameWay = 1 2 2A 3 4 5 6 7 8
        try {            
            String sql = "SELECT CoordinatesX, CoordinatesY FROM dbo.Route"+nameWay;
            ResultSet rs = dao.getPS(sql).executeQuery();
            while (rs.next()) {
                double[] arr = new double[2];
                arr[0] = rs.getFloat(1);
                arr[1] = rs.getFloat(2);
                listPoint.add(arr);
            }            
        } catch (Exception ex) {
            System.out.println("Error while connect to Db " + ex.getMessage());
        }
        return listPoint;
    }
    
    public ArrayList<Station> getListStation(){
        ArrayList<Station> listStation = new ArrayList();
        try {            
            String sql = "SELECT * FROM dbo.ListStation";
            ResultSet rs = dao.getPS(sql).executeQuery();
            while (rs.next()) {
                Station temp = new Station();
                temp.setName(rs.getString(1));
                temp.setDepot(rs.getInt(2));
                temp.setNode(rs.getInt(3));
                temp.setCoordinatesX(rs.getDouble(4));
                temp.setCoordinatesY(rs.getDouble(5));
                listStation.add(temp);
            }  
        } catch (Exception ex) {
            System.out.println("Error while connect to Db " + ex.getMessage());
        }
        return listStation;
    }
}
