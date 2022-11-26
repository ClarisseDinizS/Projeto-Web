package AgenteSMARC;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import org.apache.taglibs.standard.tag.el.core.OutTag;

/**
 *
 * DUPLA : CLARISSE E DOUGLAS
 */

@WebService(serviceName = "SMARCAgente")
@Stateless()
public class SMARCAgente {

    
    @WebMethod(operationName = "pegarDados")
    public String pegarDados(@WebParam(name = "peso") double peso, @WebParam(name = "altura") 
            double altura, @WebParam(name = "PDist") int PDist, @WebParam(name = "PSis") 
                    int PSis, @WebParam(name = "AgtNicoTotal") int AgtNicoTotal, @WebParam(name = "AtvdFisica") 
                            int AtvdFisica) {
        // System.out.println("Campos abaixo: Peso - Altura - Pressão Distólica - Pressão Sistólica - GrauNicotina - Atividade Física");
        
        double totalIMC = peso/(altura*altura);
        double grauIMC =(totalIMC - 25)/(40-25);
        
        double sedentarismoTotal;
        
        if (AtvdFisica == 0){ sedentarismoTotal = 1;
        }
        else if (AtvdFisica == 1){ sedentarismoTotal = 0.75;
        }
        else if (AtvdFisica == 2){ sedentarismoTotal = 0.5;
        }
        else if (AtvdFisica == 3){ sedentarismoTotal = 0.25;
        }
        else{ sedentarismoTotal = 0;
        }
        
        double pSistolica = (PSis - 120)/ (140-120);
        double pDistolica = (PDist - 80)/ (90 - 80);
        
        double agtNicotina = AgtNicoTotal/10;
        
        AgAvaliador avaliador =  new AgAvaliador(0.65);
        avaliador.setRiscoObesidade(grauIMC);
        avaliador.setRiscoPressaoDiastolica(pDistolica);
        avaliador.setRiscoPressaoSistolica(pSistolica);
        avaliador.setRiscoSedentarismo(sedentarismoTotal);
        avaliador.setRiscoTabagismo(agtNicotina);
        
        return avaliador.avaliacao();
    }

    
}
