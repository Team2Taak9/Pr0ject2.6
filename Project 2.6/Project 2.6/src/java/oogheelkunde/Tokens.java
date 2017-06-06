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
public class Tokens {
    private String token;
    private int identificatienummer =0;
   
    public Tokens(String deTokens, int identificatienr)
    {
        token= deTokens;
        identificatienummer=identificatienr;
    }
    
    public String getTokens(int identificatienummer)
    {return token;}
    
    public int getIdentificatienummer()
    { identificatienummer= identificatienummer +1;
        return identificatienummer;}
}
