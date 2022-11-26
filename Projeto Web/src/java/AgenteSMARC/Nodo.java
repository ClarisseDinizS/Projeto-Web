/**
 *
 * DUPLA : CLARISSE E DOUGLAS
 */

package AgenteSMARC;

public class Nodo {
    private double evidFavoravel;
    private double evidDesfavoravel;
    private double gc; //grau de certeza
    private double gct; //grau de contradição
    private double vscc; //valor superior de controle da certeza
    private double vscct;//valor superior de controle da contradição
    private double vicc; //valor inferior de controle da certeza
    private double vicct;//valor inferior de controle da contradição
    private String resultado;
    
    public Nodo(double evidencia1, double evidencia2, double parametro){
        evidFavoravel = evidencia1;
        evidDesfavoravel = 1 - evidencia2;
        
        vscc = parametro;
        vscct = parametro;
        vicc = parametro * -1;
        vicct = parametro * -1;
    }
    
    public double getEvidFavoravel(){
        return evidFavoravel;
    }
    
    public double getEvidDesfavoravel(){
        return evidDesfavoravel;
    }
    
    public String geraAvaliacaoNodo(){
        gc = evidFavoravel - evidDesfavoravel;
        gct = evidFavoravel + evidDesfavoravel - 1;
        String s = " ";
        
        if(gc >= vscc) s = "VERDADEIRO ";
        if(gc <= vicc) s = "FALSO ";
        if(gct >= vscct) s = "INCONSISTENTE ";
        if(gct <= vicct) s = "INDETERMINADO ";
        
        if((gc >= 0) && (gc < vscc)) 
            if((gct >= 0) && (gct < vscct)) 
                if(gc >= gct) s = "QUASE VERDADE --> INCONSISTENTE ";
                else s = "INCONSISTENTE --> VERDADEIRO ";
            else if((gct <= 0) && (gct > vicct)) 
                     if((gc > gct) || (gc > (gct * -1))) s = "QUASE VERDADE --> INDETERMINADO ";
                     else s = "INDETERMINADO --> VERDADEIRO ";
            else if((gc <= 0) && (gc > vicc))
                    if((gct <= 0) && (gct > vicct)) 
                        if(modulo(gc) >= modulo(gct))s = "QUASE FALSO --> INDETERMINADO ";
                        else s = "INDETERMINADO --> FALSO";
                    else if((gct >= 0) && (gct < vscct)) 
                             if(modulo(gc) > gct) s = "QUASE FALSO --> INCONSISTENTE ";
                             else s = "INCONSISTENTE --> FALSO ";
        
        s = s + "Gc: "+gc+"   Gct: "+gct;
        return s;    
    }
    
    private double modulo(double valor){
        if(valor < 0) return (valor * -1);
        else return valor;
    }
}
