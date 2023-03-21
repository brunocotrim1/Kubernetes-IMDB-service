package com.computacao.nuvem.principalsmicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Episode {
    @Id
    private String tconst;
    private String parentTconst;
    private String seasonNumber;
    private String episodeNumber;
}
