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
      Runtime rt = Runtime.getRuntime();
      Process proc = rt.exec(args);
      BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
      while (br.ready()) {
         System.out.println(br.readLine());
      }
      proc.waitFor();
   }
}
