package com.ziad.deliberation;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "DeliberationModel")
public class DeliberationModel {
	@EmbeddedId
	private ComposedKey idDeliberation;

	private String typeDeliberation;//Ordinaire our rattrapage		
	
	private String typeDelibered;//Le type module ou etape ou semestre
	
	@Column
	private Date date = new Date(System.currentTimeMillis());
	
	public DeliberationModel(ComposedKey idDeliberation) {
		super();
		this.idDeliberation = idDeliberation;
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		formatter.format(date);
	}

	public String[] splitTypes(){
		return typeDeliberation.split(",");
	}
	
	public void addType(String type) {
		typeDeliberation = typeDeliberation.length() == 0?type:typeDeliberation + "," + type;
	}

	public String getTypeDelibered() {
		return typeDelibered;
	}

	public void setTypeDelibered(String typeDelibered) {
		this.typeDelibered = typeDelibered;
	}

	public ComposedKey getIdDeliberation() {
		return idDeliberation;
	}

	public void setIdDeliberation(ComposedKey idDeliberation) {
		this.idDeliberation = idDeliberation;
	}

	public String getTypeDeliberation() {
		return typeDeliberation;
	}

	public void setTypeDeliberation(String typeDeliberation) {
		this.typeDeliberation = typeDeliberation;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
