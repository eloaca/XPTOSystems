package com.xpto.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "Cidade")
public class Cidade implements Serializable {

    private static final long serialVersionUID = 1010298644392687678L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ibge_id;

    @Column(name="uf")
    private String uf;

    @Column(name="nome")
    private String nome;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean capital;

    @Column(name="longitude")
    private BigDecimal longitude;

    @Column(name="latitude")
    private BigDecimal latitude;

    @Column(name="no_accents")
    private String no_accents;

    @Column(name="nome_alternativo")
    private String nome_alternativo;

    @Column(name="microregion")
    private String microregion;

    @Column(name="mesoregion")
    private String mesoregion;

    public Cidade() {
    }

    public Cidade(int ibge_id, String uf, String nome, boolean capital, BigDecimal longitude, BigDecimal latitude, String no_accents, String nome_alternativo, String microregion, String mesoregion) {
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

    public int getIbge_id() {
        return ibge_id;
    }

    public void setIbge_id(int ibge_id) {
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
