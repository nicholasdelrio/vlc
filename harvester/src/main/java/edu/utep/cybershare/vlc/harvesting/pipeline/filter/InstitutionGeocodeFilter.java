package edu.utep.cybershare.vlc.harvesting.pipeline.filter;

import edu.utep.cybershare.vlc.builder.ModelProduct;
import edu.utep.cybershare.vlc.util.InstitutionCSV;

public class InstitutionGeocodeFilter implements Filter {

	private InstitutionCSV geocoder;

	public InstitutionGeocodeFilter(){
		geocoder = new InstitutionCSV();
	}
	
	public ModelProduct process(ModelProduct product) {
		geocoder.setInstitutionCoordinates(product.getInstitutions());
		return product;
	}
}
