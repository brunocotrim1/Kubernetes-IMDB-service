package com.computacao.nuvem.principalsmicroservice.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Aka {

    @EmbeddedId
    private AkaID id;
    @Column(length = 65535)
    private String title;
    private String region;
    private String language;
    private String types;
    private String attributes;
    private String isOriginalTitle;

    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    public class AkaID implements Serializable {
        private static final long serialVersionUID = -7199993338620861673L;

        @Column(name = "titleId")
        public String titleId; // corresponds to ID type of Event
        public int ordering;
    }
}
