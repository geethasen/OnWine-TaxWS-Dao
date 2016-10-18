package fr.afcepf.al28.ws.tax.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.afcepf.al28.ws.tax.dao.api.ITax;
import fr.afcepf.al28.ws.tax.entity.Tax;

@Stateless
public class TaxImpl implements ITax{

	@PersistenceContext(unitName="OnWine-TaxWS-Entity")
	//unitName= name de <persitence-unit > de META-INF/persistence.xml
	private EntityManager entityManager;
	
	@Override
	public void majTax(Tax t) {
		// TODO Auto-generated method stub
		entityManager.merge(t);
	}

	@Override
	public void supprimerTax(Tax t) {
		// TODO Auto-generated method stub
		entityManager.remove(t);
	}

	@Override
	public String ajouterTax(Tax t) {
		// TODO Auto-generated method stub
		entityManager.persist(t);
		return t.getCodePays();
	}

	@Override
	public Tax getTaxByCodePays(String codePays) {
		// TODO Auto-generated method stub
		return entityManager.createQuery(
				"SELECT t FROM Tax t WHERE t.codePays = :codePays", 
				 Tax.class)
				.setParameter("codePays", codePays)
				.getSingleResult();
	}

	@Override
	public List<Tax> getAllTaxes() {
		// TODO Auto-generated method stub
		return entityManager.createNamedQuery("tax.findAll", Tax.class).getResultList();
	}

}
