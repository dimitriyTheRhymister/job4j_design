package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    private void validation(String[] args) {
        ArgsName.checkArgsForPackFiles(args);
    }

    public void packFiles(List<Path> sources, File target) {
        for (Path source : sources) {
            File sourceFile = new File(String.valueOf(source.toFile()));
            packSingleFile(sourceFile, target);
            System.out.println("fuck" + sourceFile);
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
//                zip.closeEntry();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        Zip zipDir = new Zip();
        zipDir.validation(args);
        Path start = new File(args[0].substring(3)).toPath();
        String extension = args[1].substring(4);
        List<Path> sources = Search.search(start, p -> !p.toFile().getName().endsWith(extension));
        File target = new File(args[2].substring(3));
        zipDir.packFiles(sources, target);
    }
}