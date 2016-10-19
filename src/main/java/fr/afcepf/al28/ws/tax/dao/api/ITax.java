package fr.afcepf.al28.ws.tax.dao.api;

import java.util.List;

import javax.ejb.Local;

import fr.afcepf.al28.ws.tax.entity.Tax;
import fr.afcepf.al28.ws.tax.exception.TaxWSException;

@Local
public interface ITax {

	void majTax(Tax t);

	void supprimerTax(Tax t);

	String ajouterTax(Tax t);

	Tax getTaxByCodePays(String codePays) throws TaxWSException;

	List<Tax> getAllTaxes() throws TaxWSException;
	
}
