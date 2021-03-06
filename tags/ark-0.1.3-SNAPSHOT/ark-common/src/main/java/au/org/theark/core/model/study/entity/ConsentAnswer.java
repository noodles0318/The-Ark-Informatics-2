/**
 * 
 * This is a new file
 *
 *
 */
package au.org.theark.core.model.study.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import au.org.theark.core.Constants;

/**
 * @author nivedann
 *
 */

@Entity
@Table(name = "CONSENT_ANSWER", schema = Constants.STUDY_SCHEMA)
public class ConsentAnswer implements Serializable{
	
	private Long id;
	private String name;
	
	
	public ConsentAnswer(){
		
	}

	@Id
	@SequenceGenerator(name="consent_answer_gen", sequenceName="CONSENT_ANSWER_SEQ")
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "consent_answer_gen")
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	

}
