package com.app.chart.data;

import com.jpa.entity.MelonChart;
import com.jpa.entity.chartType.MelonChartType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@ToString
@AllArgsConstructor
@Getter
public class MelonChartDTO {

    private Integer id;
    private Long songId;
    private String title;
    private String artist;
    private String album;
    private String albumImageUrl;
    private Integer rank;
    private String change;
    private String detailUrl;
    private MelonChartType chartType;
    private Timestamp crawledAt;

    /*public static MelonChartDTO fromEntity(MelonChart chart) {
        return new MelonChartDTO(
                chart.getTitle(),
                chart.getArtist(),
                chart.getAlbum(),
                chart.getRank()
        );
    }*/

    public static MelonChartDTO fromEntity(MelonChart chart) {
        return new MelonChartDTO(
                chart.getId(),
                chart.getSongId(),
                chart.getTitle(),
                chart.getArtist(),
                chart.getAlbum(),
                chart.getAlbumImageUrl(),
                chart.getRank(),
                chart.getChange(),
                chart.getDetailUrl(),
                chart.getChartType(),
                chart.getCrawledAt()
        );
    }
}
