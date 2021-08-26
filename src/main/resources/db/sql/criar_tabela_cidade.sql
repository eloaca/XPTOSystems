CREATE TABLE cidade (
  ibge_id INT NOT NULL,
  uf VARCHAR(255) NULL,
  name VARCHAR(255) NULL,
  capital TINYINT(1) NULL,
  lon DECIMAL NULL,
  lat DECIMAL NULL,
  no_accents VARCHAR(255) NULL,
  alternative_names VARCHAR(255) NULL,
  microregion VARCHAR(255) NULL,
  mesoregion VARCHAR(255) NULL,
  CONSTRAINT pk_cidade PRIMARY KEY (ibge_id)
);