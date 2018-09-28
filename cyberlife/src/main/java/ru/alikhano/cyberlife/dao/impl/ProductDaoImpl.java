package ru.alikhano.cyberlife.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.LockModeType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.alikhano.cyberlife.dao.ProductDao;
import ru.alikhano.cyberlife.model.Product;

@Repository
public class ProductDaoImpl extends GenericDaoImpl<Product> implements ProductDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Product getByModel(String model) {
		return (Product) sessionFactory.getCurrentSession().createQuery("from Product where model =:model")
				.setParameter("model", model).uniqueResult();
	}

	@Override
	public List<Product> searchParam(int category, int consLevel, double fromPrice, double toPrice) {

		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder searchCriteriaBuilder = session.getCriteriaBuilder();

		CriteriaQuery<Product> searchCriteria = searchCriteriaBuilder.createQuery(Product.class);
		Root<Product> root = searchCriteria.from(Product.class);
		
		List<Predicate> predicates = new ArrayList<>();

		

		if (category != 0) {
			predicates.add(searchCriteriaBuilder.equal(root.get("category"), category));
		}
		if (consLevel != 0) {
			predicates.add(searchCriteriaBuilder.equal(root.get("cons"), consLevel));
		}

		if (fromPrice != 0 && toPrice != 0) {
			predicates.add(searchCriteriaBuilder.between(root.get("price"), fromPrice, toPrice));
		}
		
		searchCriteria.select(root).where(predicates.toArray(new Predicate[] {}));

		return session.createQuery(searchCriteria).getResultList();
	}

	@Override
	public List<Product> getTopProducts() {
		
		List<Product> topProducts = new ArrayList<>();

		String hql = "select item.product, count(*) as purchaseCount  from OrderItem item where item.order.paymentStatus =: paymentStatus " + "GROUP BY item.product ORDER BY purchaseCount desc";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("paymentStatus", "paid");
		query.setMaxResults(10);
		List<Object[]> resultList = query.list();
		
		for (Object o : resultList) {
			Object[] row = (Object[]) o;
			Product product = (Product) row[0];
			topProducts.add(product);
		}
		
		return topProducts;
		
	}

	@Override
	public Product selectForUpdate(Integer id) {
		String hql = "from Product where productId =: productId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("productId", id);
		query.setLockMode(LockModeType.PESSIMISTIC_WRITE);
		 return (Product) query.uniqueResult();
	}

}
