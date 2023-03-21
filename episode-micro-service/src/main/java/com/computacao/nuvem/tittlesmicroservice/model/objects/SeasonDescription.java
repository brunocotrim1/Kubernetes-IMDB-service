package com.computacao.nuvem.tittlesmicroservice.model.objects;

import com.computacao.nuvem.tittlesmicroservice.model.Episode;
import com.computacao.nuvem.tittlesmicroservice.model.Title;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class SeasonDescription {
    private Title parentTitle;
    private List<Episode> episodes;
}
