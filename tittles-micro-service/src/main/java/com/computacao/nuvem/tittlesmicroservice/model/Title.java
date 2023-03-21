package com.computacao.nuvem.tittlesmicroservice.model;

import com.computacao.nuvem.tittlesmicroservice.model.converter.StringListConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Title implements Serializable {

    @Id
    @Column(name = "tconst")
    private String id;
    @Column(name = "titleType")
    private String type;
    @Column(length = 65535)
    private String primaryTitle;
    @Column(length = 65535)
    private String originalTitle;
    private String isAdult;
    private String startYear;
    private String endYear;
    private String runtimeMinutes;
    @Convert(converter = StringListConverter.class)
    private List<String> genres;
}
