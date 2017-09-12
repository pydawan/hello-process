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
        System.out.println("\t\t\t\033[1;32m<< JAVA PROCESS EXECUTOR >>\033[0m");
        Runtime rt = Runtime.getRuntime();
         Process proc = rt.exec(args);
        BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        while (br.ready()) {
            System.out.println(br.readLine());
        }
        proc.waitFor();
        System.out.println("\033[1;32mProcesso finalizado!\033[0m");
    }
}
