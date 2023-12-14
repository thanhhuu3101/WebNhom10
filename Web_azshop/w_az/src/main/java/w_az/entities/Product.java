package w_az.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="products")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
		
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
		
	@OneToMany(mappedBy = "product")
	private List<Hoadonchitiet> hoadonchitiet;
	
	@Column(name="don_gia")
	private int donGia;
	
	private String img;
	
	@Column(name="kich_thuoc")
	private String kichThuoc;

	@Column(name="mau_sac")
	private String mauSac;

	@Column(name="so_luong")
	private int soLuong;

	private String ten;

	public Product() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Hoadonchitiet> getHoadonchitiet() {
		return hoadonchitiet;
	}

	public void setHoadonchitiet(List<Hoadonchitiet> hoadonchitiet) {
		this.hoadonchitiet = hoadonchitiet;
	}

	public int getDonGia() {
		return donGia;
	}

	public void setDonGia(int donGia) {
		this.donGia = donGia;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getKichThuoc() {
		return kichThuoc;
	}

	public void setKichThuoc(String kichThuoc) {
		this.kichThuoc = kichThuoc;
	}

	public String getMauSac() {
		return mauSac;
	}

	public void setMauSac(String mauSac) {
		this.mauSac = mauSac;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}
			
}