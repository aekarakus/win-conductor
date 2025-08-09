package io.aekarakus.winconductor.sentinel.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.file.Path;
import java.util.List;

@Data
public class Application {

    private Long id;
    private String name;
    private String chocolateyName;
    private Path path;
}
