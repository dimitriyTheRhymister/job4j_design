package searcher;

import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CriterionSearch {
    private final Map<String, String> argsMap = new HashMap<>();

    private String getValue(String key) {
        StringBuilder message = new StringBuilder("This key: '").append(key).append("' is missing");
        if (!argsMap.containsKey(key)) {
            throw new IllegalArgumentException(message.toString());
        }
        String value = null;
        for (String k : argsMap.keySet()) {
            if (key.equals(k)) {
                value = argsMap.get(k);
            }
        }
        return value;
    }

    private void parseArgs(String[] args) {
        for (String s : args) {
            StringBuilder message = new StringBuilder("Error: This argument '").append(s);
            if (!s.startsWith("-")) {
                throw new IllegalArgumentException(String.valueOf(message.append("' does not start with a '-' character")));
            }
            if (!s.contains("=")) {
                throw new IllegalArgumentException(String.valueOf(message.append("' does not contain an equal sign")));
            }
            if (s.endsWith("=")
                    && !s.substring(0, s.length() - 1).contains("=")) {
                throw new IllegalArgumentException(String.valueOf(message.append("' does not contain a value")));
            }
            if (s.startsWith("-=")) {
                throw new IllegalArgumentException(String.valueOf(message.append("' does not contain a key")));
            }
            argsMap.put(s.substring(1, s.indexOf("=")),
                    s.substring(s.indexOf("=") + 1));
        }
        validateArgs(argsMap);
    }

    private void validateArgs(Map<String, String> argsMap) {
        File source = new File(argsMap.get("d"));
        if (!source.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", source.getAbsoluteFile()));
        }
        String type = argsMap.get("t");
        if (!("mask".equals(type)
                || "name".equals(type)
                || "regex".equals(type))) {
            throw new IllegalArgumentException("Wrong type name");
        }
        String target = argsMap.get("o").split("\\.")[1];
        if (!"txt".equals(target)) {
            throw new IllegalArgumentException("Wrong target extension format");
        }
    }

    private void searchFilesInDirectory(File directory, Path path,  String searchPattern, String searchType) {
        if (!directory.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", directory.getAbsoluteFile()));
        }
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", directory.getAbsoluteFile()));
        }
        for (File fileEntry : Objects.requireNonNull(directory.listFiles())) {
            if (fileEntry.isDirectory()) {
                searchFilesInDirectory(fileEntry, path, searchPattern, searchType);
            } else {
                String name = fileEntry.getName();
                writeSearchResultsToFile(path, searchPattern, searchType, name);
            }
        }
    }

    private void writeSearchResultsToFile(Path path,  String searchPattern, String searchType, String name) {
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(path.toString(), true)));
            if ("mask".equals(searchType)) {
                searchPattern = searchPattern.replace("?", "\\S");
                searchPattern = searchPattern.replace("*", "\\S*");
            }
            Pattern pattern = Pattern.compile(searchPattern);
            Matcher matcher = pattern.matcher(name);
            boolean isMatches = matcher.matches();
            if (isMatches) {
                writer.println(name);
                writer.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("The required number of Arguments was not passed to the program");
        }
        CriterionSearch searcher = new CriterionSearch();
        searcher.parseArgs(args);
        File directory = new File(searcher.getValue("d"));
        String searchPattern = searcher.getValue("n");
        String searchType = searcher.getValue("t");
        Path path = Path.of(searcher.getValue("o"));
        searcher.searchFilesInDirectory(directory, path, searchPattern, searchType);
    }
}
/*parameters for tests:
-d=C:/projects/job4j_design/data/a -n=404.txt -t=name -o=log.txt
-d=C:/projects/job4j_design/data/a "-n=\S*\.\S{2,3}" -t=regex -o=log.txt
-d=C:/projects/job4j_design/data/a -n=*.?s -t=mask -o=log.txt*/