package br.org.hello.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author thiago-amm
 * @version v1.0.0 12/09/2017
 * @since v1.0.0
 */
public class ProcessExecutor {
   public static void main(String args[]) throws IOException, InterruptedException {
      // Referência o objeto runtime que permite a aplicação se comunicar 
      // com seu ambiente de execução (Sistema Operacional).
      Runtime rt = Runtime.getRuntime();
      // Executa o comando especificado com seus argumentos em um processo separado (sub-processo)
      // do processo principal (main) da aplicação.
      Process proc = rt.exec(args);
      // Lê o fluxo de entrada do processo Java (processo da JVM) que está conectado
      // ao processo do runtime (processo do Sistema Operacional).
      BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
      // Mostra cada linha na saída padrão (STDOUT - Standard Output).
      while (br.ready()) {
         System.out.println(br.readLine());
      }
      // Espera até o sub-processo finalizar sua execução para continuar a execução do processo principal (main).
      proc.waitFor();
   }
}
