/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oogheelkunde;

/**
 *
 * @author Vincie Vossenaar
 */
public class Patient {
    private int identificatienummer; 
    private int patientnr;
    private String achternaam;
    private String voornaam;
    private String geboortedatum;
    private int geslacht;
    private String telefoonnummer;
    private String emailadres;
    private int operatie;
    
    public Patient(int identificatienr, int patientnummer, String voornaamPatient, String achternaamPatient, String geboortedatumPatient, int geslachtPatient, String mailadres, String telefoonnr, int operatief)
    {
        identificatienummer= identificatienr; 
        patientnr = patientnummer;
        achternaam = achternaamPatient;
        voornaam = voornaamPatient;
        geboortedatum = geboortedatumPatient;
        geslacht = geslachtPatient;
        emailadres=mailadres;
        telefoonnummer = telefoonnr;
        operatie= operatief; 
    }
    
    public String getAchternaam()
    { return achternaam;}
    
   public String getVoornaam()
   { return voornaam;}
    
    public String getGeboortedatum()
    { return geboortedatum;}

    public int getGeslacht()       
    {return geslacht;}
    
    public int getPatientnummer()
    {return patientnr;}
    
    public String getEmailadres()
    {return emailadres;}
    
    public String getTelefoonnummer()
    {return telefoonnummer;}
    
    public int getOperatie()
    {return operatie;}
    
}
