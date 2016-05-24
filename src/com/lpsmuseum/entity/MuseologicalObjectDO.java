package com.lpsmuseum.entity;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.lpsmuseum.dto.MuseologicalObject;
import com.lpsmuseum.dto.object.Text;



/**
 * An entity class for museological object's table (with name
 * <code>museological_object</code>). The table has columns for:
 * <ul>
 * <li>An id
 * <li>A name
 * <li>A type descriminator
 * <li>An object's type
 * <li> A date
 * </ul>
 * <p>
 * This entity can be <b>specialized</b> to represent <u>image</u> or <u>text
 * </u>. For such, this entity has a <u>type descriminator</u> column of type
 * <code>String</code> and the possible values are:
 * <ul>
 * <li>ImageDO: for museological objects that is an image.
 * <li>TextDO: for museological objects that is a text.
 * </ul>
 * <p>
 * The inheritance occurs in the same table.
 *
 * @serial
 * @see ImageDO
 * @see TextDO
 */
@Entity
@Table(name = "museological_object")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "MuseologicalObjectDO")
abstract class MuseologicalObjectDO$$MuseologicalObject$com$lpsmuseum$entity implements Serializable {

	/**
	 * This field is the <b>primary key</b> of the table (so can't be null by
	 * default) and is <u>auto generated</u> sequentially. The column's name in
	 * the table is <code>id_object</code>.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_object")
	private Long id;

	/**
	 * This field represents the scenario's name and can't be null. The column's
	 * name in the table is <code>name</code>.
	 */
	@NotNull
	@Column(name = "name")
	private String name;

	/**
	 * This field represents the object's type. The column's name in the table
	 * is <code>object_type</code>. Not has a consensus what stands for.
	 */
	@Column(name = "object_type")
	private Boolean objectType;

	/**
	 * This field represents the date of the museological object. The column's
	 * name in the table is <code>date</code>.
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Date date;

	/**
	 * Class constructor.
	 */
	public MuseologicalObjectDO$$MuseologicalObject$com$lpsmuseum$entity() {
	}

	/**
	 * Returns the id of this museological object.
	 *
	 * @return the id of this museological object.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id of this museological object.
	 *
	 * @param id the id of this museological object.
	 */
	public void setId(Long i) {
		id = i;
	}

	/**
	 * Returns the name of this museological object.
	 *
	 * @return the name of this museological object.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of this museological object.
	 *
	 * @param name the name of this museological object.
	 */
	public void setName(String n) {
		name = n;
	}

	/**
	 * Returns the type of this museological object.
	 *
	 * @return the type of this museological object.
	 */
	public Boolean getObjectType() {
		return objectType;
	}

	/**
	 * Sets the type of this museological object.
	 *
	 * @param objectType the type of this museological object.
	 */
	public void setObjectType(Boolean type) {
		objectType = type;
	}

	/**
	 * Returns the date of this museological object.
	 *
	 * @return the date of this museological object.
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date of this museological object.
	 *
	 * @param date the date of this museological object.
	 */
	public void setDate(Date d) {
		date = d;
	}

	/**
	 * Returns the transfer object representing this museological object.
	 *
	 * @return the transfer object representing this museological object.
	 */
	public MuseologicalObject getDto() {
		MuseologicalObject obj = new MuseologicalObject();

		obj.setId(getId());
		obj.setName(getName());
		obj.setObjectType(getObjectType());
		Calendar c = Calendar.getInstance();
		c.setTime(getDate());
		obj.setDate(c);

		return obj;
	}
}



/**
 * An entity class for text's table (that is a specialization of <code>
 * Museological Object</code>). This entity add this columns to <code>
 * museological_object</code>:
 * <ul>
 * <li>A text
 * </ul>
 *
 * @serial
 * @see MuseologicalObjectDO
 */
@Entity
public class MuseologicalObjectDO extends  MuseologicalObjectDO$$MuseologicalObject$com$lpsmuseum$entity  {

	/**
	 * This fields represents the text. The column's name is <code>text</code>.
	 */
	@Column(name = "text")
	String text;

	/**
	 * Returns the text for this object.
	 * 
	 * @return the text for this object.
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the text for this object.
	 * 
	 * @param text the text for this object.
	 */
	public void setText(String text) {
		((MuseologicalObjectDO) this).text = text;
	}

	@Override
	public MuseologicalObject getDto() {
		Text t = new Text();
		
		t.setId(getId());
		t.setName(getName());
		Calendar c = Calendar.getInstance();
		c.setTime(getDate());
		t.setDate(c);
		t.setObjectType(getObjectType());
		t.setText(text);
		
		return t;
	}
      // inherited constructors



	/**
	 * Class constructor.
	 */
	public MuseologicalObjectDO (  ) { super(); }

}