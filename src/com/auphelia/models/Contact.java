package com.auphelia.models;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import java.io.IOException;

@XmlRootElement
@Entity(name="contact")
public class Contact {

	private String _id;
	private String prenom ;
	private String nom ;
	@Id private String email;
	private String rue;
	private String ville ;
	private String province ;
	private String codePostal ;
	
	public Contact(){}
	
	public Contact(String prenom, String nom, String email, String rue, String ville, String province, String codePostal) {
		super();
		this.prenom = prenom;
		this.nom = nom;
		this.email = email;
		this.rue = rue;
		this.ville = ville;
		this.province = province;
		this.codePostal = codePostal;
	}
	
	public Contact(String jsonInput, String email) throws JSONException{
		JSONObject contactJson = new JSONObject (jsonInput) ;
		prenom = contactJson.get("prenom").toString() ;
		nom = contactJson.get("nom").toString() ;
		rue = contactJson.get("rue").toString() ;
		ville = contactJson.get("ville").toString() ;
		province = contactJson.get("province").toString() ;
		codePostal = contactJson.get("codePostal").toString() ;
		this.email = email;
	}
	
	public Contact(Contact contact){
		this._id = contact.get_id();
		this.nom = contact.getNom();
		this.prenom = contact.getPrenom();
		this.email = contact.getEmail();
		this.rue = contact.getRue();
		this.ville = contact.getVille();
		this.province = contact.getProvince();
		this.codePostal = contact.getCodePostal();
	}
	
	/*public newContact(String jsonInput) throws JSONException, JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		Contact contact = mapper.readValue(jsonInput, Contact.class);
		}*/
	
	public String get_id() {
		return _id;
	}

	public void set_id(String prenom) {
		this._id = prenom;
	}
	
	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
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

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	@Override
	public String toString() {
		return "Contact [prenom=" + prenom + ", nom=" + nom + ", email="
				+ email + ", rue=" + rue + ", ville=" + ville + ", province="
				+ province + ", codePostal=" + codePostal + "]";
	}
}
