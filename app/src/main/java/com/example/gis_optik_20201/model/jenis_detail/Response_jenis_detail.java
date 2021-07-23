package com.example.gis_optik_20201.model.jenis_detail;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response_jenis_detail {

	@SerializedName("kode")
	private boolean kode;

	@SerializedName("message")
	private String message;

	@SerializedName("isi")
	private List<IsiItem_jenis_detail> isi;

	public void setKode(boolean kode){
		this.kode = kode;
	}

	public boolean isKode(){
		return kode;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setIsi(List<IsiItem_jenis_detail> isi){
		this.isi = isi;
	}

	public List<IsiItem_jenis_detail> getIsi(){
		return isi;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"kode = '" + kode + '\'' + 
			",message = '" + message + '\'' + 
			",isi = '" + isi + '\'' + 
			"}";
		}
}