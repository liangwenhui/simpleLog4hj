package xyz.liangwh.simplelogger4j;


import org.springframework.util.StopWatch;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;

public class Test {

    @org.junit.jupiter.api.Test
    public void t() throws Exception {
        File file = new File("D:\\info.log");
        if(!file.exists()) {
            if(!file.getParentFile().exists())
                file.getParentFile().mkdirs();
            file.createNewFile();
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for(int i=0;i<1000000;i++){
            bw.newLine();
            bw.write(i+"_sadasdsadsasdasdasdsafsfsafasfsafsadsadsadsdsadsadasdsdsadsadadssasadasdsasadsadsasd");
            bw.flush();
        }
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }
    @org.junit.jupiter.api.Test
    public void t2() throws Exception {
        File file = new File("D:\\info2.log");
        if(!file.exists()) {
            if(!file.getParentFile().exists())
                file.getParentFile().mkdirs();
            file.createNewFile();
        }
        PrintWriter printWriter = new PrintWriter(new FileWriter(file));

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for(int i=0;i<1000000;i++){
//            printWriter.printf("Parameters are invalidI am %s : %s %d  %f\n", "lishugang", "40", i, 3.43564);
            printWriter.print(i+"_abc abc\n");
            printWriter.flush();
        }
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }

}
