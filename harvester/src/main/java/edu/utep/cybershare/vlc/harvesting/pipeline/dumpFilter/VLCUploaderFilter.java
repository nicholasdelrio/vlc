package edu.utep.cybershare.vlc.harvesting.pipeline.dumpFilter;

import edu.utep.cybershare.vlc.builder.ModelProduct;
import edu.utep.cybershare.vlc.harvesting.pipeline.Pipeline.DumpFilter;
import edu.utep.cybershare.vlc.util.VLCProjectsUploader;

public class VLCUploaderFilter implements DumpFilter {

	public void dumpModelProduct(ModelProduct product) {
		VLCProjectsUploader uploader = new VLCProjectsUploader();
		uploader.setProjects(product.getProjects());
		uploader.upload();
	}
}
