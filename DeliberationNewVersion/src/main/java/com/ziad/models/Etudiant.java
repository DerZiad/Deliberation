package com.ziad.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ziad.enums.Gender;

@Entity
@Table(name = "Etudiant")
public class Etudiant implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_etudiant")
	private Long id_etudiant;

	@Column(name = "massar_edu", unique = true)
	private String massar_edu;

	/**
	 * prénom en français
	 */
	@Column(name = "first_name_fr")
	private String first_name_fr;

	/**
	 * prénom en arabe
	 */
	@Column(name = "first_name_ar")
	private String first_name_ar;

	/**
	 * nom en français
	 */
	@Column(name = "last_name_fr")
	private String last_name_fr;

	/**
	 * nom en arabe
	 */
	@Column(name = "last_name_ar")
	private String last_name_ar;

	@Column(name = "cne", unique = true)
	private String cne;

	/**
	 * Nationalité
	 */
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.PERSIST})
	private Country nationality;

	/**
	 * sexe: HOMME ou FEMME
	 */
	@Column(name = "gender")
	@Enumerated(EnumType.STRING)
	private Gender gender;

	/**
	 * date de naissance
	 */
	@Column(name = "date")
	private Date birth_date;

	/**
	 * lieu de naissance
	 */
	@Column(name = "birth_place")
	private String birth_place;

	/**
	 * ville
	 */
	@Column(name = "city")
	private String city;// ville

	@Column(name = "province")
	private String province;

	/**
	 * année bac
	 */
	@Column(name = "bac_year")
	private Integer bac_year;

	/**
	 * type du bac: A ou B pour les sciences math, PC ou SVT pour les experimentales
	 */
	@Column(name = "bac_type")
	private String bac_type;

	@Column(name = "mention")
	private String mention;

	/**
	 * lycée
	 */
	@Column(name = "high_school")
	private String high_school;

	/**
	 * lieu d'obtention du bac
	 */
	@Column(name = "bac_place")
	private String bac_place;

	@Column(name = "academy")
	private String academy;

	@Column(name = "registration_date")
	private Date registration_date;

	@Column(name = "email")
	private String email;
	
	/**
	 * Relation
	 * */
	@OneToOne(cascade = CascadeType.ALL)
	private InscriptionEnLigne inscription_en_ligne;

	@OneToOne(cascade = CascadeType.ALL)
	private User user;

	public Etudiant() {

	}

	public Etudiant(String massar_edu, String first_name_fr, String first_name_ar, String last_name_fr,
			String last_name_ar, String cne, Country nationality, Gender gender, Date birth_date, String birth_place,
			String city, String province, Integer bac_year, String bac_type, String mention, String high_school,
			String bac_place, String academy, Date registration_date, String email,
			InscriptionEnLigne inscription_en_ligne, User user) {
		super();
		this.massar_edu = massar_edu;
		this.first_name_fr = first_name_fr;
		this.first_name_ar = first_name_ar;
		this.last_name_fr = last_name_fr;
		this.last_name_ar = last_name_ar;
		this.cne = cne;
		this.nationality = nationality;
		this.gender = gender;
		this.birth_date = birth_date;
		this.birth_place = birth_place;
		this.city = city;
		this.province = province;
		this.bac_year = bac_year;
		this.bac_type = bac_type;
		this.mention = mention;
		this.high_school = high_school;
		this.bac_place = bac_place;
		this.academy = academy;
		this.registration_date = registration_date;
		this.email = email;
		this.inscription_en_ligne = inscription_en_ligne;
		this.user = user;
	}

	public Etudiant(Long id_etudiant, String massar_edu, String first_name_fr, String first_name_ar,
			String last_name_fr, String last_name_ar, String cne, Country nationality, Gender gender, Date birth_date,
			String birth_place, String city, String province, Integer bac_year, String bac_type, String mention,
			String high_school, String bac_place, String academy, Date registration_date, String email,
			InscriptionEnLigne inscription_en_ligne, User user) {
		super();
		this.id_etudiant = id_etudiant;
		this.massar_edu = massar_edu;
		this.first_name_fr = first_name_fr;
		this.first_name_ar = first_name_ar;
		this.last_name_fr = last_name_fr;
		this.last_name_ar = last_name_ar;
		this.cne = cne;
		this.nationality = nationality;
		this.gender = gender;
		this.birth_date = birth_date;
		this.birth_place = birth_place;
		this.city = city;
		this.province = province;
		this.bac_year = bac_year;
		this.bac_type = bac_type;
		this.mention = mention;
		this.high_school = high_school;
		this.bac_place = bac_place;
		this.academy = academy;
		this.registration_date = registration_date;
		this.email = email;
		this.inscription_en_ligne = inscription_en_ligne;
		this.user = user;
	}

	public Long getId_etudiant() {
		return id_etudiant;
	}

	public void setId_etudiant(Long id_etudiant) {
		this.id_etudiant = id_etudiant;
	}

	public String getMassar_edu() {
		return massar_edu;
	}

	public void setMassar_edu(String massar_edu) {
		this.massar_edu = massar_edu;
	}

	public String getFirst_name_fr() {
		return first_name_fr;
	}

	public void setFirst_name_fr(String first_name_fr) {
		this.first_name_fr = first_name_fr;
	}

	public String getFirst_name_ar() {
		return first_name_ar;
	}

	public void setFirst_name_ar(String first_name_ar) {
		this.first_name_ar = first_name_ar;
	}

	public String getLast_name_fr() {
		return last_name_fr;
	}

	public void setLast_name_fr(String last_name_fr) {
		this.last_name_fr = last_name_fr;
	}

	public String getLast_name_ar() {
		return last_name_ar;
	}

	public void setLast_name_ar(String last_name_ar) {
		this.last_name_ar = last_name_ar;
	}

	public String getCne() {
		return cne;
	}

	public void setCne(String cne) {
		this.cne = cne;
	}

	public Country getNationality() {
		return nationality;
	}

	public void setNationality(Country nationality) {
		this.nationality = nationality;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}

	public String getBirth_place() {
		return birth_place;
	}

	public void setBirth_place(String birth_place) {
		this.birth_place = birth_place;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public Integer getBac_year() {
		return bac_year;
	}

	public void setBac_year(Integer bac_year) {
		this.bac_year = bac_year;
	}

	public String getBac_type() {
		return bac_type;
	}

	public void setBac_type(String bac_type) {
		this.bac_type = bac_type;
	}

	public String getMention() {
		return mention;
	}

	public void setMention(String mention) {
		this.mention = mention;
	}

	public String getHigh_school() {
		return high_school;
	}

	public void setHigh_school(String high_school) {
		this.high_school = high_school;
	}

	public String getBac_place() {
		return bac_place;
	}

	public void setBac_place(String bac_place) {
		this.bac_place = bac_place;
	}

	public String getAcademy() {
		return academy;
	}

	public void setAcademy(String academy) {
		this.academy = academy;
	}

	public Date getRegistration_date() {
		return registration_date;
	}

	public void setRegistration_date(Date registration_date) {
		this.registration_date = registration_date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public InscriptionEnLigne getInscription_en_ligne() {
		return inscription_en_ligne;
	}

	public void setInscription_en_ligne(InscriptionEnLigne inscription_en_ligne) {
		this.inscription_en_ligne = inscription_en_ligne;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Etudiant [id_etudiant=" + id_etudiant + ", massar_edu=" + massar_edu + ", first_name_fr="
				+ first_name_fr + ", first_name_ar=" + first_name_ar + ", last_name_fr=" + last_name_fr
				+ ", last_name_ar=" + last_name_ar + ", cne=" + cne + ", nationality=" + nationality + ", gender="
				+ gender + ", birth_date=" + birth_date + ", birth_place=" + birth_place + ", city=" + city
				+ ", province=" + province + ", bac_year=" + bac_year + ", bac_type=" + bac_type + ", mention="
				+ mention + ", high_school=" + high_school + ", bac_place=" + bac_place + ", academy=" + academy
				+ ", registration_date=" + registration_date + ", email=" + email + ", inscription_en_ligne="
				+ inscription_en_ligne + ", user=" + user + "]";
	}
	
	public boolean isHomme() {
		return gender.equals(Gender.HOMME);	
	}
	
	public boolean isFemme() {
		return gender.equals(Gender.FEMME);
	}
	
	

}
