/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import View.GameFrame;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author marcopreziosi
 */
public class CompositeProfessor extends Professor {

    private ArrayList<Professor> professorsArray;
    private ArrayList<Integer> prof;
    private static final int NUM_PROFESSORS =7;
    
    
    
    public CompositeProfessor() {
        professorsArray = new ArrayList<>();
        
        prof = new ArrayList<>();
        
        for (int i=1; i<22; i++)
            prof.add(i);
        createProfessorArray();
    }

    public void addProfessor(Professor professor) {
        professorsArray.add(professor);
    }

    public void removeProfessor(Professor professor) {
        professorsArray.remove(professor);
    }

    public ArrayList<Professor> getProfessorsArray() {
        return professorsArray;
    }

    public void setProfessorsY(ArrayList<Integer> yArray) {

        for (int i = 0; i < yArray.size(); i++) {
            professorsArray.get(i).setY(yArray.get(i));
        }
    }

    public void setProfessorsX(ArrayList<Integer> xArray) {

        for (int i = 0; i < xArray.size(); i++) {
            professorsArray.get(i).setX(xArray.get(i));
        }
    }

    public ArrayList<Integer> getProfesorsY() {

        ArrayList<Integer> yArray = new ArrayList<>();
        
        for (int i = 0; i < professorsArray.size(); i++) {
            yArray.add(professorsArray.get(i).getY());
        }
        
        return yArray;
    }

    public ArrayList<Integer> getProfessorsX() {

        ArrayList<Integer> xArray = new ArrayList<>();
        
        for (int i = 0; i < professorsArray.size(); i++) {
            xArray.add(professorsArray.get(i).getX());
        }
        return xArray;
    }
    
    
    private void createProfessorArray(){
        for (int i = 0; i < NUM_PROFESSORS; i++)
            professorsArray.add(new Professor(generateRandom(), (i * 70) * -1, "src/resources/" + Integer.toString(prof.get(i))+ ".png"));

    }
    
    public int generateRandom() {
        Random random = new Random();
        return random.nextInt(GameFrame.MAX_X - 160) + 80;
    }

    public ArrayList<Integer> getProf() {
        return prof;
    }
    
    
    public ArrayList<Integer> getY0Array(){
        
        ArrayList <Integer> y0 = new ArrayList<>();

        for (int i = 0; i< professorsArray.size(); i++){
            y0.add(i, professorsArray.get(i).getY0());
    }
    return y0;
    }
    
    
    public void setVisibleProfessorsArray(ArrayList<Boolean> visibleArray){
        
        for (int i=0; i<visibleArray.size(); i++)
            professorsArray.get(i).setVisible(visibleArray.get(i));
        
        
    }

}
