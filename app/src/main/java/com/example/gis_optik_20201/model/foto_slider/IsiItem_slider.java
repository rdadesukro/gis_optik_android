package com.example.gis_optik_20201.model.foto_slider;

import com.google.gson.annotations.SerializedName;

public class IsiItem_slider {

	@SerializedName("foto")
	private String foto;

	@SerializedName("nama")
	private String nama;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("optik_id")
	private int optikId;

	public void setFoto(String foto){
		this.foto = foto;
	}

	public String getFoto(){
		return foto;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setOptikId(int optikId){
		this.optikId = optikId;
	}

	public int getOptikId(){
		return optikId;
	}

	@Override
 	public String toString(){
		return 
			"IsiItem{" + 
			"foto = '" + foto + '\'' + 
			",nama = '" + nama + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",optik_id = '" + optikId + '\'' + 
			"}";
		}
}