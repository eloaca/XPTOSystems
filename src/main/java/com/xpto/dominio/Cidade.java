package com.xpto.dominio;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "Cidade")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cidade implements Serializable {

    private static final long serialVersionUID = 1010298644392687678L;

    @Id
    @Column(name = "ibge_id")
    private int ibge_id;

    @Column(name = "uf")
    private String uf;

    @Column(name = "name")
    private String name;

    @Column(name = "capital", columnDefinition = "TINYINT(1)")
    private boolean capital;

    @Column(name = "lon")
    private BigDecimal lon;

    @Column(name = "lat")
    private BigDecimal lat;

    @Column(name = "no_accents")
    private String no_accents;

    @Column(name = "alternative_names")
    private String alternative_names;

    @Column(name = "microregion")
    private String microregion;

    @Column(name = "mesoregion")
    private String mesoregion;


    public static Cidade getCidade(Cidade c) {
        return new Cidade(c.getIbge_id(), c.getUf(), c.getName(), c.isCapital(), c.getLon(), c.getLat(),
                c.getNo_accents(), c.getAlternative_names(), c.getMicroregion(), c.getMesoregion());
    }
}
