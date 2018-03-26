package utils;

public class CandidateData {
	public String name;
	public String phone;
	public String id;
	public CandidateData(String name, String phone, String id) {
		this.setName(name);
		this.setPhone(phone);
		this.setId(id);
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
