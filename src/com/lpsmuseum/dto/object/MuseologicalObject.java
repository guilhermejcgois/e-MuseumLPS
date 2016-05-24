package com.lpsmuseum.dto.object;
import com.lpsmuseum.entity.MuseologicalObjectDO;



/**
 * This class represents a <u>specialization</u> of <code>MuseologicalObject
 * </code>, just for transfer between client-side and server-side.
 */
public  class MuseologicalObject {

	/**
	 * This field represents the text's content.
	 */
	private String text;

	/**
	 * Returns the content of this <code>Text</code> instance.
	 * 
	 * @return the content of this <code>Text</code> instance.
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the content of this <code>Text</code> instance.
	 * 
	 * @param text the content of this <code>Text</code> instance.
	 */
	public void setText(String text) {
		((MuseologicalObject) this).text = text;
	}

	@Override
	public MuseologicalObjectDO getEntity() {
		MuseologicalObjectDO objDO = super.getEntity();
		
		objDO.setText(text);
		
		return objDO;
	}

}