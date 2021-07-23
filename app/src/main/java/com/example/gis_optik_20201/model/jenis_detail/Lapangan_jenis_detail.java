package com.example.gis_optik_20201.model.jenis_detail;

import com.google.gson.annotations.SerializedName;

public class Lapangan_jenis_detail {


	public String getRaiting() {
		return raiting;
	}

	public void setRaiting(String raiting) {
		this.raiting = raiting;
	}

	@SerializedName("raiting")
	private String raiting;

	@SerializedName("lng")
	private String lng;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("alamat")
	private String alamat;

	@SerializedName("nama")
	private String nama;

	@SerializedName("foto")
	private String foto;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("user_id")
	private int userId;

	@SerializedName("phone")
	private String phone;

	@SerializedName("status_all")
	private int statusAll;

	@SerializedName("id")
	private int id;

	@SerializedName("lat")
	private String lat;

	@SerializedName("status")
	private int status;


	public void setLng(String lng){
		this.lng = lng;
	}

	public String getLng(){
		return lng;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setFoto(String foto){
		this.foto = foto;
	}

	public String getFoto(){
		return foto;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setStatusAll(int statusAll){
		this.statusAll = statusAll;
	}

	public int getStatusAll(){
		return statusAll;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setLat(String lat){
		this.lat = lat;
	}

	public String getLat(){
		return lat;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"Lapangan{" + 
			"raiting = '" + raiting + '\'' + 
			",lng = '" + lng + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",alamat = '" + alamat + '\'' + 
			",nama = '" + nama + '\'' + 
			",foto = '" + foto + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",user_id = '" + userId + '\'' + 
			",phone = '" + phone + '\'' + 
			",status_all = '" + statusAll + '\'' + 
			",id = '" + id + '\'' + 
			",lat = '" + lat + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}