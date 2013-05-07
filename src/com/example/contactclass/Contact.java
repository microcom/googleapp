package com.example.contactclass;

public class Contact {
	
	private String prenom ;
	private String nom ;
	private String rue;
	private String ville ;
	private String province ;
	private String codePostal ;
	
	public Contact(String firstName, String lastName, String street,
			String city, String province, String postalCode) {
		this.prenom = firstName;
		this.nom = lastName;
		this.rue = street;
		this.ville = city;
		this.province = province;
		this.codePostal = postalCode;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String firstName) {
		this.prenom = firstName;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String lastName) {
		this.nom = lastName;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String street) {
		this.rue = street;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String city) {
		this.ville = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String postalCode) {
		this.codePostal = postalCode;
	}
	
	
	
}
