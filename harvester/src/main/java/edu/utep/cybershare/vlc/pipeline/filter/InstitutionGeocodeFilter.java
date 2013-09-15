package edu.utep.cybershare.vlc.pipeline.filter;

import edu.utep.cybershare.vlc.build.ModelProduct;
import edu.utep.cybershare.vlc.pipeline.Pipeline.Filter;
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
