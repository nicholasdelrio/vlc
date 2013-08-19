package edu.utep.cybershare.vlc.visko.characterization;

public class SemanticCharacteristics {
	
	private String formatURI;
	private String typeURI;
	
	private SemanticCharacteristics(String formatURI, String typeURI){
		this.formatURI = formatURI;
		this.typeURI = typeURI;
	}
	
	public String getTypeURI(){
		return typeURI;
	}
	
	public String getFormatURI(){
		return formatURI;
	}
	
	private static class Resources{
		private static final String htmlFormatURI = "http://openvisko.org/rdf/pml2/formats/HTML.owl#HTML";
		private static final String jpegFormatURI = "http://openvisko.org/rdf/pml2/formats/JPEG.owl#JPEG";
		private static final String pngFormatURI = "http://openvisko.org/rdf/pml2/formats/PNG.owl#PNG";
		private static final String gifFormatURI = "http://openvisko.org/rdf/pml2/formats/GIF.owl#GIF";
		private static final String pdfFormatURI = "http://openvisko.org/rdf/pml2/formats/PDF.owl#PDF";
		
		private static final String thingTypeURI = "http://www.w3.org/2002/07/owl#Thing";
	}
				
	public static SemanticCharacteristics getCharacteristics(String inputURL){
		String suffix = getSuffix(inputURL);

		if(suffix == null)
			return new SemanticCharacteristics(Resources.htmlFormatURI, Resources.thingTypeURI);
		else if(suffix.equalsIgnoreCase("png"))
			return new SemanticCharacteristics(Resources.pngFormatURI, Resources.thingTypeURI);
		else if(suffix.equalsIgnoreCase("jpg") || suffix.equalsIgnoreCase("jpeg"))
			return new SemanticCharacteristics(Resources.jpegFormatURI, Resources.thingTypeURI);
		else if(suffix.equalsIgnoreCase("gif"))
			return new SemanticCharacteristics(Resources.gifFormatURI, Resources.thingTypeURI);
		else if(suffix.equalsIgnoreCase("pdf"))
			return new SemanticCharacteristics(Resources.pdfFormatURI, Resources.thingTypeURI);
		else
			return new SemanticCharacteristics(Resources.htmlFormatURI, Resources.thingTypeURI);			
	}
		
	private static String getSuffix(String inputURL){
		String[] parts;
		String suffix = null;
		if(inputURL != null){
			parts = inputURL.split(".");
			if(parts.length > 1)
				suffix = parts[parts.length - 1];
		}
		return suffix;
	}
}
