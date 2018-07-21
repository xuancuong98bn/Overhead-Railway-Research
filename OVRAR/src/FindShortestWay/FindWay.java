/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FindShortestWay;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MTC
 */
public class FindWay {

    String nameFile = "data.txt";
    String solvedFile = "floy.txt";
    String pointFile = "point.txt";
    final int number = 34;

    double[][] W = new double[number][number];
    double[][] P = new double[number][number];
    double[][] D = new double[number][number];

    void loadData(double[][] Matrix, String filename) throws IOException {
        RandomAccessFile f;
        int i, j;
        double x;
        String s;
        StringTokenizer t;
        try {
            f = new RandomAccessFile(filename, "r");
            for (i = 0; i < number; i++) {
                s = f.readLine();
                s = s.trim();
                t = new StringTokenizer(s);
                for (j = 0; j < number; j++) {
                    x = Double.parseDouble(t.nextToken().trim());
                    Matrix[i][j] = x;
                }
            }
            f.close();
        } catch (FileNotFoundException ex) {
            System.err.println("Error with load data");
        }
    }

    public void saveData(double[][] Matrix, String nameFile) throws FileNotFoundException, IOException {
        File save = new File(nameFile);
        if (save.exists()) {
            save.delete();
        }
        RandomAccessFile f = new RandomAccessFile(save, "rw");
        for (int i = 0; i < number; i++) {
            for (int j = 0; j < number; j++) {
                f.writeBytes(Matrix[i][j] + " ");
            }
            f.writeBytes("\n");
        }
        f.writeBytes("\r\n");
        f.close();
    }

    public void copyWith(int size, double[][] copy, double[][] origin) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                copy[i][j] = origin[i][j];
            }
        }
    }

    public void floyd(int n, double[][] W, double[][] P, double[][] D) {
        /*
        W : origin adj matrix
        D : solved matrix
        P : matrix point
         */
        copyWith(number, D, W);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                P[i][j] = 0;
            }
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (D[i][k] + D[k][j] < D[i][j]) {
                        D[i][j] = D[i][k] + D[k][j];
                        P[i][j] = k;
                    }
                }
            }
        }
    }

    public boolean checkResult() {
        File solve = new File(solvedFile);
        File point = new File(pointFile);
        return solve.exists() && point.exists();
    }

    public void printMatrix(double[][] Matrix) {
        for (int i = 0; i < number; i++) {
            for (int j = 0; j < number; j++) {
                System.out.print(Matrix[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public void printArray(ArrayList list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i)+" ");
        }
        System.out.println("");
    }
    
    public void controllFloyd() throws IOException {
//        if (checkResult()) {
//            return;
//        }
        loadData(W, nameFile);
        floyd(number, W, P, D);
        saveData(P, pointFile);
        saveData(D, solvedFile);
        getPathPoint(0, 4);
        getListDist(0, 4);
    }

    public ArrayList getPathPoint(int fro, int to) {
        ArrayList points = new ArrayList();
        Stack tmpSave = new Stack();
        int value;
        tmpSave.push(to);
        while (fro != to){
            value = (int)P[fro][to];
            tmpSave.push(value);
            to = value;
        }
        while (!tmpSave.isEmpty()){
            points.add(tmpSave.pop());
        }
        printArray(points);
        return points;
    }
    
    public ArrayList getListDist(int fro, int to) {
        ArrayList dist = new ArrayList();
        ArrayList points = getPathPoint(fro, to);
        double value;
        for (int i = 0; i < points.size(); i++) {
            value = D[fro][(int)points.get(i)];
            dist.add(value);
        }
        printArray(dist);
        return dist;
    }

    public static void main(String[] args) throws IOException {
        FindWay a = new FindWay();
        a.controllFloyd();
    }
}
