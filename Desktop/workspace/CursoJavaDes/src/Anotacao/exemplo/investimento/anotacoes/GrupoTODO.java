package Anotacao.exemplo.investimento.anotacoes;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Constroi uma anotacao para indicar as atividades em execução 
 * que o metodo ou classe tem por ser concluido
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface GrupoTODO {
  public enum Severidade {
    CRITICO,
    IMPORTANTE,
    TRIVIAL,
    DOCUMENTACAO
  };

  Severidade severity() default Severidade.IMPORTANTE;

  String item();

  String designadoA();

  String dataDesignacao();
}
