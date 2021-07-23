package com.example.gis_optik_20201.model.jenis_detail;

import com.google.gson.annotations.SerializedName;

public class IsiItem_jenis_detail {

	@SerializedName("lapangan_id")
	private int lapanganId;

	@SerializedName("jumlah_komen")
	private int jumlahKomen;


	public float getRaiting() {
		return raiting;
	}

	public void setRaiting(float raiting) {
		this.raiting = raiting;
	}

	@SerializedName("raiting")
	private float raiting;

	@SerializedName("lapangan")
	private Lapangan_jenis_detail lapangan;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("uuid")
	private String uuid;

	@SerializedName("foto")
	private String foto;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("harga")
	private int harga;

	@SerializedName("nama")
	private String nama;

	@SerializedName("id")
	private int id;

	@SerializedName("informasi")
	private String informasi;

	@SerializedName("status")
	private int status;

	public void setLapanganId(int lapanganId){
		this.lapanganId = lapanganId;
	}

	public int getLapanganId(){
		return lapanganId;
	}

	public void setJumlahKomen(int jumlahKomen){
		this.jumlahKomen = jumlahKomen;
	}

	public int getJumlahKomen(){
		return jumlahKomen;
	}




	public void setLapangan(Lapangan_jenis_detail lapangan){
		this.lapangan = lapangan;
	}

	public Lapangan_jenis_detail getLapangan(){
		return lapangan;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setUuid(String uuid){
		this.uuid = uuid;
	}

	public String getUuid(){
		return uuid;
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

	public void setHarga(int harga){
		this.harga = harga;
	}

	public int getHarga(){
		return harga;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setInformasi(String informasi){
		this.informasi = informasi;
	}

	public String getInformasi(){
		return informasi;
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
			"IsiItem{" + 
			"lapangan_id = '" + lapanganId + '\'' + 
			",jumlah_komen = '" + jumlahKomen + '\'' + 
			",raiting = '" + raiting + '\'' + 
			",lapangan = '" + lapangan + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",uuid = '" + uuid + '\'' + 
			",foto = '" + foto + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",harga = '" + harga + '\'' + 
			",nama = '" + nama + '\'' + 
			",id = '" + id + '\'' + 
			",informasi = '" + informasi + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}