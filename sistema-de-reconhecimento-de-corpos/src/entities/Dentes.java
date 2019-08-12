package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dente")
public class Dentes {

	@Id	
	private Integer codigoOssada;
	
	private Integer premolares;
	private Integer molares;
	private Integer siso;
	private Integer canino;
	private Integer incisivo;
	public Integer getCodigoOssada() {
		return codigoOssada;
	}
	public void setCodigoOssada(Integer codigoOssada) {
		this.codigoOssada = codigoOssada;
	}
	public Integer getPremolares() {
		return premolares;
	}
	public void setPremolares(Integer premolares) {
		this.premolares = premolares;
	}
	public Integer getMolares() {
		return molares;
	}
	public void setMolares(Integer molares) {
		this.molares = molares;
	}
	public Integer getSiso() {
		return siso;
	}
	public void setSiso(Integer siso) {
		this.siso = siso;
	}
	public Integer getCanino() {
		return canino;
	}
	public void setCanino(Integer canino) {
		this.canino = canino;
	}
	public Integer getIncisivo() {
		return incisivo;
	}
	public void setIncisivo(Integer incisivo) {
		this.incisivo = incisivo;
	}
	
	
	
	
	
}
