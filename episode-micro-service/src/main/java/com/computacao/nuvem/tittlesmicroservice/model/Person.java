package com.computacao.nuvem.tittlesmicroservice.model;

import com.computacao.nuvem.tittlesmicroservice.model.converter.StringListConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Person {
    @Id
    @Column(name = "nconst")
    private String id;
    @Column(name = "primaryName")
    private String primaryName;
    @Column(name = "birthYear")
    private String birthYear;
    @Column(name = "deathYear")
    private String deathYear;
    @Column(name = "primaryProfession")
    @Convert(converter = StringListConverter.class)
    private List<String> professions;
    @Column(name = "knownForTitles")
    @Convert(converter = StringListConverter.class)
    private List<String> titles;
}
