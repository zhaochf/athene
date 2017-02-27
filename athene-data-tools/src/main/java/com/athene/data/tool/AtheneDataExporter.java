/**
 * 
 */
package com.athene.data.tool;

import java.io.File;
import java.util.Map;
import java.util.Properties;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2x.GenericExporter;
import org.hibernate.tool.hbm2x.pojo.POJOClass;

/**
 * @author zhaochf
 *
 */
public class AtheneDataExporter extends GenericExporter {

	/**
	 * 
	 */
	public AtheneDataExporter() {
		init();
	}

	/**
	 * @param cfg
	 * @param outputdir
	 */
	public AtheneDataExporter(Configuration cfg, File outputdir) {
		super(cfg, outputdir);
		init();
	}

	private void init() {
		super.setArtifactCollector(new NoXMLFormatArtifactCollector());
	}

	/* (non-Javadoc)
	 * @see org.hibernate.tool.hbm2x.GenericExporter#doStart()
	 */
	@Override
	protected void doStart() {
		// boolean generateRepository = Boolean.valueOf(getProperties().getProperty("generate-repository")).booleanValue();
		
		
	}
	
	private GenericExporter configureExporter(String template, String pattern) {
		// Add custom template path if specified
        String[] templatePaths;
        if (getProperties().getProperty("templatedirectory") != null) {
            templatePaths = new String[getTemplatePaths().length + 1];
            templatePaths[0] = getProperties().getProperty("templatedirectory");
            if (getTemplatePaths().length > 1) {
                for (int i = 1; i < getTemplatePaths().length; i++) {
                    templatePaths[i] = getTemplatePaths()[i-1];
                }
            }
        } else {
            templatePaths = getTemplatePaths();
        }
        
        GenericExporter exporter = new GenericExporter(getConfiguration(), getOutputDirectory()) {
            
			@Override
            protected void exportPOJO(Map map, POJOClass element) {
                if (element.getShortName().equals(System.getProperty("athene.entity"))) {
                    super.exportPOJO(map, element);
                }
            }

            @Override
            protected String resolveFilename(POJOClass element) {
                String filename = super.resolveFilename(element);
                String packageLocation = getPackageNameForFile(element).replace(".", "/");

                String pojoName = System.getProperty("entity");
                if (pojoName == null) {
                    pojoName = System.getProperty("athene.entity");
                }

                // A dot in the entity name means the person is specifying the package.
                if (pojoName.contains(".")) {
                    packageLocation = pojoName.substring(0, pojoName.indexOf(".domain"));
                    packageLocation = packageLocation.replace(".", "/");
                }

                if (packageLocation.endsWith("domain") && packageLocation.indexOf('/') > -1) {
                    packageLocation = packageLocation.substring(0, packageLocation.lastIndexOf('/'));
                }
                filename = filename.replace("{basepkg-name}", packageLocation);
                return filename;
            }
        };
        
        exporter.setProperties((Properties) getProperties().clone());
        exporter.setTemplatePath(templatePaths);
        exporter.setTemplateName(template);
        exporter.setFilePattern(pattern);
        exporter.setArtifactCollector(getArtifactCollector());
        
        return exporter;
	}
	
}
