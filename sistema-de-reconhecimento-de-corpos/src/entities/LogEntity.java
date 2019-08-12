package entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="LogEntity")
public class LogEntity  {
	@Id
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@ManyToOne
	private Usuario usuario;
	private Date data;
	@ManyToOne
	private Corpo corpo;
	private int identificado;
	private int confirmado;
	private String dataFormatada;
	
	public String getDataFormatada() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		return sdf.format(data);
	}
	public String getHoraFormatada() {
		String data1 = "dd/MM/yyyy";
		String hora = "HH:mm:ss";
		String hora1;
		
		SimpleDateFormat formata = new SimpleDateFormat(hora);							
		hora1 = formata.format(data);
		return hora1;
	}
	public void setDataFormatada(String dataFormatada) {
		this.dataFormatada = dataFormatada;
	}
	public int getConfirmado() {
		return confirmado;
	}
	public void setConfirmado(int confirmado) {
		this.confirmado = confirmado;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Corpo getCorpo() {
		return corpo;
	}
	public void setCorpo(Corpo corpo) {
		this.corpo = corpo;
	}
	public int getIdentificado() {
		return identificado;
	}
	public void setIdentificado(int identificado) {
		this.identificado = identificado;
	}
}
