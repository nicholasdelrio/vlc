package edu.utep.cybershare.vlc.pipeline.sink;

import edu.utep.cybershare.vlc.build.ModelProduct;
import edu.utep.cybershare.vlc.pipeline.Pipeline.DumpFilter;

public class VLCUploaderFilter implements DumpFilter {

	public void dumpModelProduct(ModelProduct product) {
		VLCProjectsUploader uploader = new VLCProjectsUploader();
		uploader.setProjects(product.getProjects());
		uploader.upload();
	}
}
