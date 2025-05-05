package com.app.chart.data;

import com.jpa.entity.MelonChart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public class MelonChartDTO {

    private String title;
    private String artist;
    private String album;
    private Integer rank;

    public static MelonChartDTO fromEntity(MelonChart chart) {
        return new MelonChartDTO(
                chart.getTitle(),
                chart.getArtist(),
                chart.getAlbum(),
                chart.getRank()
        );
    }
}
