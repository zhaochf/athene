/**
 * 
 */
package com.athene.data.tool;

import org.hibernate.tool.hbm2x.ArtifactCollector;

/**
 * @author zhaochf
 *
 */
public class NoXMLFormatArtifactCollector extends ArtifactCollector {

	/* (non-Javadoc)
	 * @see org.hibernate.tool.hbm2x.ArtifactCollector#formatFiles()
	 */
	@Override
	public void formatFiles() {
		// do nothing
	}
}
