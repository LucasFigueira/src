package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sun.istack.internal.Nullable;


@Entity
@Table(name="corpo")
public class Corpo {
	
	@Id
	private Integer codigoCorpo;
	
	@Nullable 
	private Double peso;
	@Nullable 
	private String faixaEtaria;
	@Nullable 
	private String corPele;
	@Nullable 
	private String corOlhos;
	@Nullable 
	private String corCabelo;
	@Nullable 
	private String tipoCabelo;
	@Nullable 
	private String destino;
	@Nullable 
	private Double altura;	
	@Nullable 
	private String dataChegada;
	@Nullable 
	private String horarioChegada;
	@Nullable 
	private String descricao;
	@Nullable 
	private String latitude;
	@Nullable
	private String longitude;
	@Nullable 
	private String sexo;

	@Transient
	private Double chance;
	
	private Integer identificado;
	
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	
	public String getTipoCabelo() {
		return tipoCabelo;
	}
	public void setTipoCabelo(String tipoCabelo) {
		this.tipoCabelo = tipoCabelo;
	}
	
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public String getFaixaEtaria() {
		return faixaEtaria;
	}
	public void setFaixaEtaria(String faixaEtaria) {
		this.faixaEtaria = faixaEtaria;
	}
	
	public Double getChance() {
		return chance;
	}
	public void setChance(Double chance) {
		this.chance = chance;
	}
	public String getHorarioChegada() {
		return horarioChegada;
	}
	public void setHorarioChegada(String horarioChegada) {
		this.horarioChegada = horarioChegada;
	}
	public Integer getCodigoCorpo() {
		return codigoCorpo;
	}
	public void setCodigoCorpo(Integer codigoCorpo) {
		this.codigoCorpo = codigoCorpo;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	public String getCorPele() {
		return corPele;
	}
	public void setCorPele(String corPele) {
		this.corPele = corPele;
	}
	public String getCorOlhos() {
		return corOlhos;
	}
	public void setCorOlhos(String corOlhos) {
		this.corOlhos = corOlhos;
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
	public Integer isIdentificado() {
		return identificado;
	}
	public void setIdentificado(Integer identificado) {
		this.identificado = identificado;
	}
	public Double getAltura() {
		return altura;
	}
	public void setAltura(Double altura) {
		this.altura = altura;
	}
	public String getCorCabelo() {
		return corCabelo;
	}
	public void setCorCabelo(String corCabelo) {
		this.corCabelo = corCabelo;
	}
	 public Integer getIdentificado() {
		return identificado;
	}
	public String getLatitude() {
		return latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
		
	@Override
	public int hashCode() {
	    int hash = 0;
	    hash += (codigoCorpo != null ? codigoCorpo.hashCode() : 0);
	    return hash;
	}
	
	 
	@Override
	public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	   if (!(object instanceof Usuario)) {
	      return false;
	   }
	   Corpo other = (Corpo) object;
	   if ((this.codigoCorpo == null && other.codigoCorpo != null) || (this.codigoCorpo != null && !this.codigoCorpo.equals(other.codigoCorpo))) {
	      return false;
	   }
	  return true;
	 }
	

}
