package fr.afcepf.al28.ws.tax.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.afcepf.al28.ws.tax.dao.api.ITax;
import fr.afcepf.al28.ws.tax.entity.Tax;
import fr.afcepf.al28.ws.tax.exception.TaxWSError;
import fr.afcepf.al28.ws.tax.exception.TaxWSException;

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
		return  t.getCodePays();
	}

	@Override
	public Tax getTaxByCodePays(String codePays) throws TaxWSException {
		// TODO Auto-generated method stub
		Tax taxe = entityManager.createQuery(
				"SELECT t FROM Tax t WHERE t.codePays = :codePays", 
				 Tax.class)
				.setParameter("codePays", codePays)
				.getSingleResult();
		if (taxe.equals(null)) {
			throw new TaxWSException(TaxWSError.RECHERCHE_NON_PRESENTE_EN_BASE, "Tax corresponding to this country is not in the base");
		}
		return taxe;
	}

	@Override
	public List<Tax> getAllTaxes() throws TaxWSException {
		// TODO Auto-generated method stub
		List<Tax> listeTaxe = new ArrayList<Tax>();
		listeTaxe = entityManager.createNamedQuery("tax.findAll", Tax.class).getResultList();
		if (listeTaxe.isEmpty()) {
			throw new TaxWSException(TaxWSError.RECHERCHE_NON_PRESENTE_EN_BASE, "No tax found in the base");
		}
		return listeTaxe;
	}

}
