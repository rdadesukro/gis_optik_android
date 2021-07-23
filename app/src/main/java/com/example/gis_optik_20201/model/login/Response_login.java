package com.example.gis_optik_20201.model.login;

import com.google.gson.annotations.SerializedName;

public class Response_login {

	@SerializedName("access_token")
	private String accessToken;

	@SerializedName("role")
	private int role;

	@SerializedName("expires_at")
	private String expiresAt;

	@SerializedName("nama")
	private String nama;

	@SerializedName("user_id")
	private int userId;

	@SerializedName("kode")
	private String kode;

	@SerializedName("token_type")
	private String tokenType;

	@SerializedName("message")
	private String message;

	public void setAccessToken(String accessToken){
		this.accessToken = accessToken;
	}

	public String getAccessToken(){
		return accessToken;
	}

	public void setRole(int role){
		this.role = role;
	}

	public int getRole(){
		return role;
	}

	public void setExpiresAt(String expiresAt){
		this.expiresAt = expiresAt;
	}

	public String getExpiresAt(){
		return expiresAt;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	public void setKode(String kode){
		this.kode = kode;
	}

	public String getKode(){
		return kode;
	}

	public void setTokenType(String tokenType){
		this.tokenType = tokenType;
	}

	public String getTokenType(){
		return tokenType;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"access_token = '" + accessToken + '\'' + 
			",role = '" + role + '\'' + 
			",expires_at = '" + expiresAt + '\'' + 
			",nama = '" + nama + '\'' + 
			",user_id = '" + userId + '\'' + 
			",kode = '" + kode + '\'' + 
			",token_type = '" + tokenType + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}