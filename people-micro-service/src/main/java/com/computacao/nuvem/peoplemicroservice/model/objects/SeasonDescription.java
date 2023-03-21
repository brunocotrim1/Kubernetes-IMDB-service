package com.computacao.nuvem.peoplemicroservice.model.objects;

import com.computacao.nuvem.peoplemicroservice.model.Episode;
import com.computacao.nuvem.peoplemicroservice.model.Title;
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
