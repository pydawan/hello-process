package br.org.hello.process;

import java.io.*;
import java.util.*;

public class PythonShell {
    
    // these are out here so the inner class can access them...
    static BufferedReader in = null;
    static boolean ok = true;
    
    public static void main(String[] args) throws InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("python", "-i"); // force interactive
        pb.redirectErrorStream(true); // combine stdout and stderr
        Process p = null;
        PrintStream out = null;
//         boolean started = false;
        try {
            p = pb.start();
//             started = true;
            in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            out = new PrintStream(p.getOutputStream(), true);
        } catch (IOException exc) {
            exc.printStackTrace();
//            if (started)
//                p.destroy();
//            return;
        }
        final Scanner userIn = new Scanner(System.in);
        final Thread mainthread = Thread.currentThread();
        class ReaderThread implements Runnable {
            public void run() {
                try {
                    while (true) {
                        int c = in.read();
                        if (c == -1) {
                            ok = false;
                            break;
                        }
                        System.out.print((char) c);
                    }
                } catch (IOException exc) {
                    ok = false;
                }
                // try to interrupt the other thread
                userIn.close();
                try {
                    System.in.close();
                } catch (IOException exc) {
                }
                mainthread.interrupt();
            }
        }
        Thread rt = new Thread(new ReaderThread());
        rt.start();
        while (ok) {
            try {
                String input = userIn.nextLine();
                out.println(input);
                System.out.println(ok);
            } catch (NoSuchElementException exc) {
                ok = false;
            }
        }
        p.destroy();
        p.waitFor();
    }
}
