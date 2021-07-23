package com.example.gis_optik_20201.model.optik;

import com.google.gson.annotations.SerializedName;

public class IsiItem_optik {

	@SerializedName("lng")
	private int lng;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("alamat")
	private String alamat;

	@SerializedName("foto")
	private String foto;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("phone")
	private String phone;

	@SerializedName("jam_oprasional")
	private String jamOprasional;

	@SerializedName("id")
	private int id;

	@SerializedName("nama_optik")
	private String namaOptik;

	@SerializedName("status_bpjs")
	private String statusBpjs;

	@SerializedName("lat")
	private int lat;

	@SerializedName("informasi")
	private String informasi;

	@SerializedName("status")
	private String status;

	public void setLng(int lng){
		this.lng = lng;
	}

	public int getLng(){
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

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setJamOprasional(String jamOprasional){
		this.jamOprasional = jamOprasional;
	}

	public String getJamOprasional(){
		return jamOprasional;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setNamaOptik(String namaOptik){
		this.namaOptik = namaOptik;
	}

	public String getNamaOptik(){
		return namaOptik;
	}

	public void setStatusBpjs(String statusBpjs){
		this.statusBpjs = statusBpjs;
	}

	public String getStatusBpjs(){
		return statusBpjs;
	}

	public void setLat(int lat){
		this.lat = lat;
	}

	public int getLat(){
		return lat;
	}

	public void setInformasi(String informasi){
		this.informasi = informasi;
	}

	public String getInformasi(){
		return informasi;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"IsiItem{" + 
			"lng = '" + lng + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",alamat = '" + alamat + '\'' + 
			",foto = '" + foto + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",phone = '" + phone + '\'' + 
			",jam_oprasional = '" + jamOprasional + '\'' + 
			",id = '" + id + '\'' + 
			",nama_optik = '" + namaOptik + '\'' + 
			",status_bpjs = '" + statusBpjs + '\'' + 
			",lat = '" + lat + '\'' + 
			",informasi = '" + informasi + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}