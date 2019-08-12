package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cicatriz")
public class Cicatriz {

	@Id	
	private Integer codigoCorpo;
	
	private Integer rosto;
	private Integer peito;
	private Integer costas;
	private Integer braco;
	private Integer perna;
	private Integer pe;
	
	public Integer getCodigoCorpo() {
		return codigoCorpo;
	}
	public void setCodigoCorpo(Integer codigoCorpo) {
		this.codigoCorpo = codigoCorpo;
	}
	public Integer getRosto() {
		return rosto;
	}
	public void setRosto(Integer rosto) {
		this.rosto = rosto;
	}
	public Integer getPeito() {
		return peito;
	}
	public void setPeito(Integer peito) {
		this.peito = peito;
	}
	public Integer getCostas() {
		return costas;
	}
	public void setCostas(Integer costas) {
		this.costas = costas;
	}
	public Integer getBraco() {
		return braco;
	}
	public void setBraco(Integer braco) {
		this.braco = braco;
	}
	public Integer getPerna() {
		return perna;
	}
	public void setPerna(Integer perna) {
		this.perna = perna;
	}
	public Integer getPe() {
		return pe;
	}
	public void setPe(Integer pe) {
		this.pe = pe;
	}
	
	
}
