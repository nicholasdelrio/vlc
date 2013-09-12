package edu.utep.cybershare.vlc.harvesting;

import edu.utep.cybershare.vlc.builder.ModelProduct;
import edu.utep.cybershare.vlc.upload.VLCProjectsUploader;

public class VLCUploaderFilter implements DumpFilter {

	public void dumpModelProduct(ModelProduct product) {
		VLCProjectsUploader uploader = new VLCProjectsUploader();
		uploader.setProjects(product.getProjects());
		uploader.upload();
	}
}
