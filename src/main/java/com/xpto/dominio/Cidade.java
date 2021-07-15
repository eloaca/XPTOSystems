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
    @Column(name="ibge_id")
    private int ibge_id;

    @Column(name="uf")
    private String uf;

    @Column(name="name")
    private String name;

    @Column(name="capital", columnDefinition = "TINYINT(1)")
    private boolean capital;

    @Column(name="lon")
    private BigDecimal lon;

    @Column(name="lat")
    private BigDecimal lat;

    @Column(name="no_accents")
    private String no_accents;

    @Column(name="alternative_names")
    private String alternative_names;

    @Column(name="microregion")
    private String microregion;

    @Column(name="mesoregion")
    private String mesoregion;

    public Cidade() {
    }

    public Cidade(int ibge_id, String uf, String name, boolean capital, BigDecimal lon, BigDecimal lat,
                  String no_accents, String alternative_names, String microregion, String mesoregion) {
        this.ibge_id = ibge_id;
        this.uf = uf;
        this.name = name;
        this.capital = capital;
        this.lon = lon;
        this.lat = lat;
        this.no_accents = no_accents;
        this.alternative_names = alternative_names;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCapital() {
        return capital;
    }

    public void setCapital(boolean capital) {
        this.capital = capital;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public String getNo_accents() {
        return no_accents;
    }

    public void setNo_accents(String no_accents) {
        this.no_accents = no_accents;
    }

    public String getAlternative_names() {
        return alternative_names;
    }

    public void setAlternative_names(String alternative_names) {
        this.alternative_names = alternative_names;
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
