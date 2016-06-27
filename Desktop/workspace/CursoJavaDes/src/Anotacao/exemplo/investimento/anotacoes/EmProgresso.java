package Anotacao.exemplo.investimento.anotacoes;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Constroi uma anotacao para indicar que o metodo ou classe ainda está em
 * processo de construcao
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface EmProgresso {
}
