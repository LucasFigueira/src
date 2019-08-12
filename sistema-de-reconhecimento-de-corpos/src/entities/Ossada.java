package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="ossada")
public class Ossada {
	
	@Id
	private Integer codigoOssada;
	private String longitude;
	private String latitude;
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	private boolean arcadaCompleta;
	private String peso;
	public String getPeso() {
		return peso;
	}
	public void setPeso(String peso) {
		this.peso = peso;
	}
	private boolean dentesPermanentes;
	private String estruturaDentaria;
	public String getEstruturaDentaria() {
		return estruturaDentaria;
	}
	public void setEstruturaDentaria(String estruturaDentaria) {
		this.estruturaDentaria = estruturaDentaria;
		if(estruturaDentaria.equals("Permanentes")){
			this.dentesPermanentes = true;
		}
	}
	public boolean isDentesPermanentes() {
		return dentesPermanentes;
	}
	public void setDentesPermanentes(boolean dentesPermanentes) {
		this.dentesPermanentes = dentesPermanentes;
	}
	
	public boolean isArcadaCompleta() {
		return arcadaCompleta;
	}
	public void setArcadaCompleta(boolean arcadaCompleta) {
		this.arcadaCompleta = arcadaCompleta;
	}
	private Integer idade;
	private String sexo;
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String faixaEtaria;
	public String getFaixaEtaria() {
		return faixaEtaria;
	}
	public void setFaixaEtaria(String faixaEtaria) {
		this.faixaEtaria = faixaEtaria;
	}
	private String tipoRacial;
	private Double altura;	
	private String dataChegada;
	private String horarioChegada;
	private String descricao;
	@Transient
	private Double chance;
	
	public Double getChance() {
		return chance;
	};
	public void setChance(Double chance) {
		this.chance = chance;
	}
	public String getHorarioChegada() {
		return horarioChegada;
	}
	public void setHorarioChegada(String horarioChegada) {
		this.horarioChegada = horarioChegada;
	}
	
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	
	
	
	public String getTipoRacial() {
		return tipoRacial;
	}
	public void setTipoRacial(String tipoRacial) {
		this.tipoRacial = tipoRacial;
	}
	public String getDataChegada() {
		return dataChegada;
	}
	public void setDataChegada(String dataChegada) {
		this.dataChegada = dataChegada;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public Double getAltura() {
		return altura;
	}
	public void setAltura(Double altura) {
		this.altura = altura;
	}
 
	@Override
	public int hashCode() {
	    int hash = 0;
	    hash += (codigoOssada != null ? codigoOssada.hashCode() : 0);
	    return hash;
	}
	 
	public Integer getCodigoOssada() {
		return codigoOssada;
	}
	public void setCodigoOssada(Integer codigoOssada) {
		this.codigoOssada = codigoOssada;
	}
	@Override
	public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	   if (!(object instanceof Usuario)) {
	      return false;
	   }
	   Ossada other = (Ossada) object;
	   if ((this.codigoOssada == null && other.codigoOssada != null) || (this.codigoOssada != null && !this.codigoOssada.equals(other.codigoOssada))) {
	      return false;
	   }
	  return true;
	 }
}
