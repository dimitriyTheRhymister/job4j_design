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

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
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
        String sourceDir = argsName.get("d");
        String extension = argsName.get("e").substring(1);
        String target = argsName.get("o");
        Path root = Path.of(sourceDir);
        List<Path> sources = Search.search(root, p -> !p.toString().endsWith(extension));
        zipDir.packFiles(sources, new File(target));
    }
}