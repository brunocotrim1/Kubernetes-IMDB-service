package com.computacao.nuvem.peoplemicroservice.model;


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
public class Principal {

    @EmbeddedId
    private PrincipalId id;
    private String category;
    @Column(length = 65535)
    private String job;
    @Column(length = 65535)
    private String characters;
    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    public class PrincipalId implements Serializable {
        private static final long serialVersionUID = -7199993338620861673L;

        @Column(name = "tconst")
        public String titleId; // corresponds to ID type of Event
        @Column(name = "nconst")
        public String personId;
        public String ordering;
    }
}
