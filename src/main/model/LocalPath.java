package main.model;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LocalPath {

    private static final String DB_CONF = "/db/conf";
    private static final String DB_JSON_FILES = "/db/jsonFiles";

    private static @NotNull Path getConfigurationFileDirectory() {
        return Paths.get(System.getProperty("user.dir") + DB_CONF);
    }

    private static @NotNull Path getJsonFilesDirectory() {
        return Paths.get(System.getProperty("user.dir") + DB_JSON_FILES);
    }

    public static void createLocalDirectories() throws IOException {
        if (!Files.isDirectory(getConfigurationFileDirectory()) || !Files.isDirectory(getJsonFilesDirectory())) {
            Files.createDirectories(getJsonFilesDirectory());
            Files.createDirectories(getConfigurationFileDirectory());
        }
    }

    public static List<Path> generatePathListForImportFromFile(String dir) {

        List<Path> path_list = new LinkedList<>();
        try (Stream<Path> walk = Files.walk(Paths.get(dir))) {

            path_list = walk
                    .filter(Files::isRegularFile)
                    .filter(e -> e.toString().endsWith(".json"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path_list;

    }
}
