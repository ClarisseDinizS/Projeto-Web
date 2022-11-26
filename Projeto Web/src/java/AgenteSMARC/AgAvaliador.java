/**
 *
 * DUPLA : CLARISSE E DOUGLAS
 */

package AgenteSMARC;

public class AgAvaliador {
    private double riscoObesidade;
    private double riscoPressaoSistolica;
    private double riscoPressaoDiastolica;
    private double riscoSedentarismo;
    private double riscoTabagismo;
    private Nodo nodo1;
    private double parametro;
    
    public AgAvaliador(double parametro){
        this.parametro = parametro;
    }
    
    public void setRiscoObesidade(double risco){
        riscoObesidade = risco;
    }
    
    public void setRiscoPressaoSistolica(double risco){
        riscoPressaoSistolica = risco;
    }
    
    public void setRiscoPressaoDiastolica(double risco){
        riscoPressaoDiastolica = risco;
    }
    
    public void setRiscoSedentarismo(double risco){
        riscoSedentarismo = risco;
    }
    
    public void setRiscoTabagismo(double risco){
        riscoTabagismo = risco;
    }
    
    public String avaliacao(){
        Nodo nodoA = new Nodo(riscoObesidade, riscoPressaoSistolica, parametro);
        Nodo nodoB = new Nodo(riscoPressaoSistolica, riscoPressaoDiastolica, parametro);
        Nodo nodoC = new Nodo(riscoSedentarismo, riscoTabagismo, parametro);
        
        Nodo nodoD = operacaoMAX(nodoA, nodoB);
        nodo1 = operacaoMAX(nodoC, nodoD);
        
        return nodo1.geraAvaliacaoNodo();
        
    }
    
    private Nodo operacaoMAX(Nodo n1, Nodo n2){
        double a = n1.getEvidFavoravel();
        double b = n2.getEvidFavoravel();
        double maior, menor;
        
        if(a >= b) maior = a;
        else maior = b;
        
        a = n1.getEvidDesfavoravel();
        b = n2.getEvidDesfavoravel();
        
        if(a >= b) menor = b;
        else menor = a;
        
        Nodo resposta = new Nodo(maior, (menor + 1), parametro);
        
        return resposta;
    }

}
