package w_az.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.Query;

import w_az.entities.Hoadon;
import w_az.entities.Hoadonchitiet;
import w_az.entities.Product;
import w_az.utils.JPAUtils;

public class hoaDonChiTietDAO {
	private EntityManager em;

	public hoaDonChiTietDAO() {
		this.em = JPAUtils.getEntityManager();
	}
	
	public Hoadonchitiet create(Hoadonchitiet entity) throws Exception{
		try {
			this.em.getTransaction().begin();
			this.em.persist(entity);
			this.em.getTransaction().commit();
			return entity;			
		} catch (Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
			throw e;
		}
	}
	
	public Hoadonchitiet update(Hoadonchitiet entity) throws Exception{
		try {
			this.em.getTransaction().begin();
			this.em.merge(entity);
			this.em.getTransaction().commit();
			return entity;			
		} catch (Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
			throw e;
		}
	}
	
	public Hoadonchitiet delete(Hoadonchitiet entity) throws Exception{
		try {
			this.em.getTransaction().begin();
			this.em.remove(entity);
			this.em.getTransaction().commit();
			return entity;			
		} catch (Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
			throw e;
		}
	}
	
	public List<Hoadonchitiet> all() {
		String jpql = "SELECT obj FROM Hoadonchitiet obj";
		TypedQuery<Hoadonchitiet> query = this.em.createQuery(jpql, Hoadonchitiet.class);
		List<Hoadonchitiet> result = query.getResultList();
		return result;
	}
	
	public Hoadonchitiet findById(int id) {
		return this.em.find(Hoadonchitiet.class, id);
	}
		
	public List<Hoadonchitiet> selectHoaDonChiTietInUserId(int id){
		try {
			String jpql = "SELECT obj FROM Hoadonchitiet obj WHERE obj.status = 1 AND obj.user.id = :user";
			TypedQuery<Hoadonchitiet> query = this.em.createQuery(jpql, Hoadonchitiet.class);					
			query.setParameter("user", id);
			List<Hoadonchitiet> result = query.getResultList();				
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Hoadonchitiet> showHoaDonChiTietInHoaDon(int id){
		try {
			String jpql = "SELECT obj FROM Hoadonchitiet obj WHERE obj.hoadon.id = :hoadon";
			TypedQuery<Hoadonchitiet> query = this.em.createQuery(jpql, Hoadonchitiet.class);					
			query.setParameter("hoadon", id);
			List<Hoadonchitiet> result = query.getResultList();				
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
