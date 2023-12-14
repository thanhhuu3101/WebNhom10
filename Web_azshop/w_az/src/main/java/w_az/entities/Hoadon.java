package w_az.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="Hoadon")
@NamedQuery(name="Hoadon.findAll", query="SELECT hd FROM Hoadon hd")
public class Hoadon implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@OneToMany(fetch = FetchType.LAZY ,mappedBy = "hoadon")
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	private List<Hoadonchitiet> hoadonchitiet;
	
	@Column(name="tong_tien")
	private int tongTien;
	
	private int status;
		
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<Hoadonchitiet> getHoadonchitiet() {
		return hoadonchitiet;
	}

	public void setHoadonchitiet(List<Hoadonchitiet> hoadonchitiet) {
		this.hoadonchitiet = hoadonchitiet;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getTongTien() {
		return tongTien;
	}

	public void setTongTien(int tongTien) {
		this.tongTien = tongTien;
	}
}