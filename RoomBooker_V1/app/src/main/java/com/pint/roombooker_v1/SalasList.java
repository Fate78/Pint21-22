package com.pint.roombooker_v1;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Time;
import java.util.List;

public class SalasList implements Serializable {

    @SerializedName("page")
    public Integer page;
    @SerializedName("per_page")
    public Integer perPage;
    @SerializedName("total")
    public Integer total;
    @SerializedName("total_pages")
    public Integer totalPages;
    @SerializedName("data")
    public List<SalasDataList> salasDataList;

    public class SalasDataList implements Serializable{

        @SerializedName("id")
        public Integer id;
        @SerializedName("n_sala")
        public Integer n_sala;
        @SerializedName("lotacao_max")
        public Integer lotacao_max;
        @SerializedName("tempo_min_limp")
        public Time tempo_min_limp;
        @SerializedName("id_Centro")
        public Integer id_centro;
        @SerializedName("ativo")
        public boolean ativo;
    }
}
