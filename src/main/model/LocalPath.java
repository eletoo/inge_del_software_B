package model;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LocalPath {

    private static final String DB_CONF = "/db/conf";
    private static final String DB_JSON_FILES = "/db/jsonFiles";

    private static @NotNull Path getConfigurationFileDirectory(){
        return Paths.get(System.getProperty("user.dir") + DB_CONF);
    }

    private static @NotNull Path getJsonFilesDirectory(){
        return Paths.get(System.getProperty("user.dir") + DB_JSON_FILES);
    }

    public static void createLocalDirectories() throws IOException {
        if (!Files.isDirectory(getConfigurationFileDirectory()) || !Files.isDirectory(getJsonFilesDirectory())) {
            Files.createDirectories(getJsonFilesDirectory());
            Files.createDirectories(getJsonFilesDirectory());
        }
    }
}
