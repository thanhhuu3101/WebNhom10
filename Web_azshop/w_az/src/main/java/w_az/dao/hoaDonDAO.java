package w_az.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import w_az.entities.Hoadon;
import w_az.entities.Hoadonchitiet;
import w_az.entities.User;
import w_az.utils.JPAUtils;

public class hoaDonDAO {
private EntityManager em;

	public hoaDonDAO() {
		this.em = JPAUtils.getEntityManager();
	}
	
	public Hoadon create(Hoadon entity) throws Exception{
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
	
	public Hoadon update(Hoadon entity) throws Exception{
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
	
	public Hoadon delete(Hoadon entity) throws Exception{
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
	
	public List<Hoadon> all() {
		String jpql = "SELECT obj FROM Hoadon obj";
		TypedQuery<Hoadon> query = this.em.createQuery(jpql, Hoadon.class);
		List<Hoadon> result = query.getResultList();
		return result;
	}
	
	public Hoadon findById(int id) {
		return this.em.find(Hoadon.class, id);
	}
	
	public List<Hoadon> selectHoaDonChuaThanhToan(){
		try {
			String jpql = "SELECT obj FROM Hoadon obj WHERE obj.status = 0";
			TypedQuery<Hoadon> query = this.em.createQuery(jpql, Hoadon.class);					
			List<Hoadon> result = query.getResultList();				
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
		
	public List<Hoadon> selectLichSuDatHangByUserId(int id){
		try {
			String jpql = "SELECT obj FROM Hoadon obj WHERE obj.user.id = :user";
			TypedQuery<Hoadon> query = this.em.createQuery(jpql, Hoadon.class);	
			query.setParameter("user", id);
			List<Hoadon> result = query.getResultList();				
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
