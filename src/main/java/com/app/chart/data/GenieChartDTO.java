package com.app.chart.data;

import com.jpa.entity.GenieChart;
import com.jpa.entity.MelonChart;
import com.jpa.entity.chartType.GenieChartType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@ToString
@AllArgsConstructor
@Getter
public class GenieChartDTO {

    private Integer id;
    private String title;
    private String artist;
    private String album;
    private String albumImageUrl;
    private Integer rank;
    private String change;
    private GenieChartType chartType;
    private Timestamp crawledAt;

    /*public static GenieChartDTO fromEntity(GenieChart chart) {
        return new GenieChartDTO(
                chart.getTitle(),
                chart.getArtist(),
                chart.getAlbum(),
                chart.getRank()
        );
    }*/

    public static GenieChartDTO fromEntity(GenieChart chart) {
        return new GenieChartDTO(
                chart.getId(),
                chart.getTitle(),
                chart.getArtist(),
                chart.getAlbum(),
                chart.getAlbumImageUrl(),
                chart.getRank(),
                chart.getChange(),
                chart.getChartType(),
                chart.getCrawledAt()
        );
    }
}