package com.bighit.on.user.dao;

import com.bighit.on.cmn.DTO;

public class UsersVO extends DTO {
	private String user_serial; /* 계정시리얼키 */
	private String ws_link; /* 워크스페이스 링크 */
	private String email; /* 이메일 */
	private String password;  /* 비밀번호 */
	private String name;  /* 이름 */
	private String nickname;  /* 표시이름 */
	private String profile_img;  /* 프로필사진 */
	private String position;  /* 직책 */
	private String phone_num;  /* 전화번호 */
	private int country; /* 시간대 */
	private int state;  /* 상태 */
	private int online_state;  /* 상태(on/off) */
	private String reg_id;  /* 작성자 */
	private String reg_dt;  /* 작성시간 */
	
	public UsersVO() {
		
	}

	public UsersVO(String user_serial, String ws_link, String email, String password, String name, String nickname,
			String profile_img, String position, String phone_num, int country, int state, int online_state,
			String reg_id, String reg_dt) {
		super();
		this.user_serial = user_serial;
		this.ws_link = ws_link;
		this.email = email;
		this.password = password;
		this.name = name;
		this.nickname = nickname;
		this.profile_img = profile_img;
		this.position = position;
		this.phone_num = phone_num;
		this.country = country;
		this.state = state;
		this.online_state = online_state;
		this.reg_id = reg_id;
		this.reg_dt = reg_dt;
	}

	public String getUser_serial() {
		return user_serial;
	}

	public void setUser_serial(String user_serial) {
		this.user_serial = user_serial;
	}

	public String getWs_link() {
		return ws_link;
	}

	public String getWsLink() {
		return ws_link;
	}
	public void setWs_link(String ws_link) {
		this.ws_link = ws_link;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getProfile_img() {
		return profile_img;
	}

	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPhone_num() {
		return phone_num;
	}

	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}

	public int getCountry() {
		return country;
	}

	public void setCountry(int country) {
		this.country = country;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getOnline_state() {
		return online_state;
	}

	public void setOnline_state(int online_state) {
		this.online_state = online_state;
	}

	public String getReg_id() {
		return reg_id;
	}

	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}

	public String getReg_dt() {
		return reg_dt;
	}

	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}

	@Override
	public String toString() {
		return "User [user_serial=" + user_serial + ", ws_link=" + ws_link + ", email=" + email + ", password="
				+ password + ", name=" + name + ", nickname=" + nickname + ", profile_img=" + profile_img
				+ ", position=" + position + ", phone_num=" + phone_num + ", country=" + country + ", state=" + state
				+ ", online_state=" + online_state + ", reg_id=" + reg_id + ", reg_dt=" + reg_dt + ", toString()="
				+ super.toString() + "]";
	}
	
	
}
