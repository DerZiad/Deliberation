package com.ziad.enums;

public enum InscriptionPedagogiqueType {
	SEMESTRE("Semestre"),MODULE("Module"),ELEMENT("Element");
	private String type;
	private InscriptionPedagogiqueType(String type) {
		this.type  = type;
	}
	
	public String getType() {
		return type;
	}
}
