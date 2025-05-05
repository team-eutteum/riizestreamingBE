package com.app.chart.data;

import com.jpa.entity.GenieChart;
import com.jpa.entity.MelonChart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public class GenieChartDTO {

    private String title;
    private String artist;
    private String album;
    private Integer rank;

    public static GenieChartDTO fromEntity(GenieChart chart) {
        return new GenieChartDTO(
                chart.getTitle(),
                chart.getArtist(),
                chart.getAlbum(),
                chart.getRank()
        );
    }
}