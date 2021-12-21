package com.happy.video.pojo;

import lombok.Data;

import java.util.Objects;

@Data
public class DebankSupportProtocal {
    private Integer id;

    private String chain;

    private String debankId;

    private String nameEn;

    private String nameCh;

    private Integer priority;

    private String siteUrl;

    private String platformTokenId;

    private String spiderDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DebankSupportProtocal that = (DebankSupportProtocal) o;
        return Objects.equals(chain, that.chain) && Objects.equals(debankId, that.debankId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chain, debankId);
    }
}