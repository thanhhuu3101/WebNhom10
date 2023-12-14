package w_az.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import net.bytebuddy.implementation.bind.annotation.Empty;

@Entity
@Table(name = "users")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String avatar;

	@OneToMany(mappedBy = "user")
	private List<Category> categories;
	
	@OneToMany(mappedBy = "user")
	private List<Hoadon> hoadon;
	
	@OneToMany(mappedBy = "user")
	private List<Hoadonchitiet> hoadonchitiet;
	
	@Column(name = "dia_chi")
	private String diaChi;
	
	private String email;
	
	@Column(name = "gioi_tinh")
	private int gioiTinh;
	
	@Column(name = "ho_ten")
	private String hoTen;
	
	private String password;
	
	private String sdt;
	
	private int role;

	
	public User() {
	}

	public int getRole() {
		return role;
	}


	public void setRole(int role) {
		this.role = role;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAvatar() {
		return this.avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getDiaChi() {
		return this.diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getGioiTinh() {
		return this.gioiTinh;
	}

	public void setGioiTinh(int gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getHoTen() {
		return this.hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSdt() {
		return this.sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Hoadon> getHoadon() {
		return hoadon;
	}

	public void setHoadon(List<Hoadon> hoadon) {
		this.hoadon = hoadon;
	}

	public List<Hoadonchitiet> getHoadonchitiet() {
		return hoadonchitiet;
	}

	public void setHoadonchitiet(List<Hoadonchitiet> hoadonchitiet) {
		this.hoadonchitiet = hoadonchitiet;
	}
	
	
}