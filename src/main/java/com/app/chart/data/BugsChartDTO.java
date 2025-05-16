package com.app.chart.data;

import com.jpa.entity.BugsChart;
import com.jpa.entity.chartType.BugsChartType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@ToString
@AllArgsConstructor
@Getter
@Builder
public class BugsChartDTO {

    private Integer id;
    private String title;
    private String artist;
    private String album;
    private String albumImageUrl;
    private Integer rank;
    private String change;
    private BugsChartType chartType;
    private Timestamp crawledAt;

    public static BugsChartDTO fromEntity(BugsChart chart) {
        return BugsChartDTO.builder()
                .id(chart.getId())
                .title(chart.getTitle())
                .artist(chart.getArtist())
                .album(chart.getAlbum())
                .albumImageUrl(chart.getAlbumImageUrl())
                .rank(chart.getRank())
                .change(chart.getChange())
                .chartType(chart.getChartType())
                .crawledAt(chart.getCrawledAt())
                .build();
    }
}