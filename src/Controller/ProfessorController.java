/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import ObserverPackage.Controller;
import entities.Professor;
import java.util.Random;
import View.GameFrame;

import java.util.ArrayList;


public class ProfessorController implements Controller {

    private ArrayList<Professor> professorArray;
    private static final int NUM_PROFESSORS =7;
    private boolean disappearProfessorFlag =false; //flag per la scomparsa dei libri dopo tot secondi
    private PlayController playController;
    private ArrayList<Integer> prof;
    private int j;
    public static final int HARD_PROFESSOR=7, MEDIUM_PROFESSOR=5, EASY_PROFESSOR=3;
    
    public ProfessorController() {
        this.j = 7;
        professorArray = new ArrayList<>();
        
        
        prof = new ArrayList<>();
        
        
        for (int i=0; i<21; i++){
            prof.add(i);
        }
       
        createProfessorArray();
        
    }
 
    
    private void createProfessorArray(){
        for (int i = 0; i < NUM_PROFESSORS; i++)
            professorArray.add(new Professor(generateRandom(), (i * 70) * -1, "src/resources/" + Integer.toString(prof.get(i))+ ".png"));
    }
    
    public void updateImageProfessorArray(){
        
        
        for (int i = 0; i < NUM_PROFESSORS; i++)
            
            professorArray.get(i).changeImageProfessor(prof.get(i+j));
            
        if (j==7)
            j=14;
        else if (j==14)
            j=0;
        else 
            j=7;
            
    }
    
    public void setDisappearProfessorFlag(boolean flag){
        this.disappearProfessorFlag =flag;
    }

    public ArrayList<Professor> getProfessors() {
        return professorArray;
    }

    private int generateRandom() {
        Random random = new Random();
        return random.nextInt(GameFrame.MAX_X - 160) + 80;
    }


     
    @Override
    public void update() {
        cycle();
    }

    private void cycle() {
        int x, y;
        for (int i = 0; i < professorArray.size(); i++) {
            Professor professor = professorArray.get(i);
            x = professor.getX();
            y = professor.getY();

            y += Professor.getSpeed();

            if (y > GameFrame.MAX_Y) {
                if (!disappearProfessorFlag) { //VARIABILE PER FAR SCOMPARIRE I LIBRI QUANDO APPARE IL BOSS
                    y = professor.getY0();
                    x = generateRandom();
                    professor.setX(x);
                    professor.setY(y);
                    //professor.setVisible(true);
                } else 
                    professor.setVisible(false);
            } else 
                professor.setY(y);
        }
        
        /*ATTENDERE LA SCOMPARSA DI TUTTI I LIBRI PRIMA DI FAR COMPARIRE IL BOSS*/
        boolean flag=true;
        if(disappearProfessorFlag){
            for (int i = 0; i < professorArray.size(); i++) {
                Professor professor = professorArray.get(i);
                if(professor.isVisible())
                    flag=false;
            }

            if(flag){
                playController = PlayController.getPlayController();
                playController.getBossController().getBoss().addState(PlayController.BOSS_IS_VISIBLE);
            } 
        }
    }
}
