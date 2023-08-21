package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    private void validateArgs(ArgsName args) {
        File source = new File(args.get("d"));
        if (!source.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", source.getAbsoluteFile()));
        }
        boolean exclude = args.get("e").startsWith("*.");
        if (!exclude) {
            throw new IllegalArgumentException("Wrong extension format for exclude argument");
        }
        String target = args.get("o");
        if (!target.equals("project.zip")) {
            throw new IllegalArgumentException("Wrong target extension format");
        }
    }

    private void packFiles(String sourceDir, String extension, String zipFile) {
        try (ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile)))) {
            File dirSource = new File(sourceDir);
            addDirectory(zos, extension, dirSource);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addDirectory(ZipOutputStream zos, String extension, File dirSource) throws IOException {
        Path start = dirSource.toPath();
        List<Path> sources = Search.search(start, p -> !p.toString().endsWith(extension));
        for (Path p : sources) {
            File file = p.toFile();
            if (file.isDirectory()) {
                addDirectory(zos, extension, file);
                continue;
            }
            addFile(zos, file);
        }
    }

    private void addFile(ZipOutputStream zos, File fileSource) {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileSource))) {
            zos.putNextEntry(new ZipEntry(fileSource.getPath()));
            zos.write(bis.readAllBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("The required number of Arguments was not passed to the program");
        }
        ArgsName argsName = ArgsName.of(args);
        Zip zipDir = new Zip();
        zipDir.validateArgs(argsName);
        String sourceDir = args[0].substring(3);
        String extension = args[1].substring(4);
        String zipFile = args[2].substring(3);
        zipDir.packFiles(sourceDir, extension, zipFile);
    }
}