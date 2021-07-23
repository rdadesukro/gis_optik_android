package com.example.gis_optik_20201.model.user;

import com.google.gson.annotations.SerializedName;

public class IsiItem_user {

	@SerializedName("role")
	private int role;

	@SerializedName("updated_at")
	private String updatedAt;


	@SerializedName("alamat")
	private String alamat;

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getTelpon() {
		return telpon;
	}

	public void setTelpon(String telpon) {
		this.telpon = telpon;
	}

	@SerializedName("telpon")
	private String telpon;



	@SerializedName("foto")
	private String foto;

	@SerializedName("name")
	private String name;

	@SerializedName("created_at")
	private Object createdAt;

	@SerializedName("email_verified_at")
	private Object emailVerifiedAt;

	@SerializedName("id")
	private int id;

	@SerializedName("uuid")
	private String uuid;

	@SerializedName("email")
	private String email;

	@SerializedName("username")
	private String username;

	public void setRole(int role){
		this.role = role;
	}

	public int getRole(){
		return role;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setFoto(String foto){
		this.foto = foto;
	}

	public String getFoto(){
		return foto;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setCreatedAt(Object createdAt){
		this.createdAt = createdAt;
	}

	public Object getCreatedAt(){
		return createdAt;
	}

	public void setEmailVerifiedAt(Object emailVerifiedAt){
		this.emailVerifiedAt = emailVerifiedAt;
	}

	public Object getEmailVerifiedAt(){
		return emailVerifiedAt;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setUuid(String uuid){
		this.uuid = uuid;
	}

	public String getUuid(){
		return uuid;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"IsiItem{" + 
			"role = '" + role + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",foto = '" + foto + '\'' + 
			",name = '" + name + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",email_verified_at = '" + emailVerifiedAt + '\'' + 
			",id = '" + id + '\'' + 
			",uuid = '" + uuid + '\'' + 
			",email = '" + email + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}