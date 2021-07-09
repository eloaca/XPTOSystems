package com.xpto.dominio;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table
public class Cidade implements Serializable {

    private static final long serialVersionUID = 1010298644392687678L;

    private Long ibge_id;
    private String uf;
    private String nome;
    private boolean capital;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String no_accents;
    private String nome_alternativo;
    private String microregion;
    private String mesoregion;

    public Cidade() {
    }

    public Cidade(Long ibge_id, String uf, String nome, boolean capital, BigDecimal longitude, BigDecimal latitude, String no_accents, String nome_alternativo, String microregion, String mesoregion) {
        this.ibge_id = ibge_id;
        this.uf = uf;
        this.nome = nome;
        this.capital = capital;
        this.longitude = longitude;
        this.latitude = latitude;
        this.no_accents = no_accents;
        this.nome_alternativo = nome_alternativo;
        this.microregion = microregion;
        this.mesoregion = mesoregion;
    }

    public Long getIbge_id() {
        return ibge_id;
    }

    public void setIbge_id(Long ibge_id) {
        this.ibge_id = ibge_id;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isCapital() {
        return capital;
    }

    public void setCapital(boolean capital) {
        this.capital = capital;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getNo_accents() {
        return no_accents;
    }

    public void setNo_accents(String no_accents) {
        this.no_accents = no_accents;
    }

    public String getNome_alternativo() {
        return nome_alternativo;
    }

    public void setNome_alternativo(String nome_alternativo) {
        this.nome_alternativo = nome_alternativo;
    }

    public String getMicroregion() {
        return microregion;
    }

    public void setMicroregion(String microregion) {
        this.microregion = microregion;
    }

    public String getMesoregion() {
        return mesoregion;
    }

    public void setMesoregion(String mesoregion) {
        this.mesoregion = mesoregion;
    }

}
