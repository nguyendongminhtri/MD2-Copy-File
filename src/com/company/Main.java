package com.company;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        String from = "E:\\CODEGYM\\C0222I1\\MD2\\copyFile\\src\\com\\company\\input\\input.txt";
        String to = "E:\\CODEGYM\\C0222I1\\MD2\\copyFile\\src\\com\\company\\output\\input.txt";
//        String fromFile = "/home/mkyong/dev/db.debug.conf";
//        String toFile = "/home/mkyong/live/db.conf";
        File source = new File(from);
        File dest = new File(to);

        //copy file conventional way using Stream
        long start = System.nanoTime();

            copyFileUsingStream(source, dest);


        System.out.println("Time taken by Stream Copy = "+(System.nanoTime()-start));

    }

    public static void copyFileNIO(String from, String to) throws IOException {

        Path fromFile = Paths.get(from);
        Path toFile = Paths.get(to);

        // if fromFile doesn't exist, Files.copy throws NoSuchFileException
        if (Files.notExists(fromFile)) {
            System.out.println("File doesn't exist? " + fromFile);
            return;
        }

        // if toFile folder doesn't exist, Files.copy throws NoSuchFileException
        // if toFile parent folder doesn't exist, create it.
        Path parent = toFile.getParent();
        System.out.println("parent"+parent);
        System.out.println("get"+parent.getParent());
        if(parent!=null){
            System.out.println("vao if cha");
            if(Files.notExists(parent)){
                System.out.println("vao if");
                Files.createDirectories(parent);
            }
        }

        // default - if toFile exist, throws FileAlreadyExistsException
        Files.copy(fromFile, toFile);

        // if toFile exist, replace it.
        // Files.copy(fromFile, toFile, StandardCopyOption.REPLACE_EXISTING);

        // multiple StandardCopyOption
        /*CopyOption[] options = { StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES,
                LinkOption.NOFOLLOW_LINKS };

        Files.copy(fromFile, toFile, options);*/

    }


    private static void copyFileUsingStream(File source, File dest) throws IOException{
        InputStream is = null;
        OutputStream os = null;
        try {

            is = new FileInputStream(source);
            System.out.println("dest"+is.read());
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
                System.out.println("length = "+length);
            }
//
            System.out.println("Copy is done");
        }
        finally {
            is.close();
            os.close();
        }
    }

}
