/*******************************************************************************
 * Copyright (c) 2011  University of Western Australia. All rights reserved.
 * 
 * This file is part of The Ark.
 * 
 * The Ark is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 * 
 * The Ark is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package au.org.theark.core.model.lims.entity;

// Generated 15/06/2011 1:22:58 PM by Hibernate Tools 3.3.0.GA

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import au.org.theark.core.model.Constants;

/**
 * BiodataCriteria generated by hbm2java
 */
@Entity
@Table(name = "biodata_criteria", schema = Constants.LIMS_TABLE_SCHEMA)
public class BiodataCriteria implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long								id;
	private Integer							studyId;
	private String								domain;
	private String								field;
	private String								value;
	private String								description;
	private Set<BiodataGroupCriteria>	biodataGroupCriterias	= new HashSet<BiodataGroupCriteria>(0);

	public BiodataCriteria() {
	}

	public BiodataCriteria(Long id) {
		this.id = id;
	}

	public BiodataCriteria(Long id, Integer studyId, String domain, String field, String value, String description, Set<BiodataGroupCriteria> biodataGroupCriterias) {
		this.id = id;
		this.studyId = studyId;
		this.domain = domain;
		this.field = field;
		this.value = value;
		this.description = description;
		this.biodataGroupCriterias = biodataGroupCriterias;
	}

	@Id
	@SequenceGenerator(name = "biodatacriteria_generator", sequenceName = "BIODATACRITERIA_SEQUENCE")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "biodatacriteria_generator")
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "STUDY_ID")
	public Integer getStudyId() {
		return this.studyId;
	}

	public void setStudyId(Integer studyId) {
		this.studyId = studyId;
	}

	@Column(name = "DOMAIN", length = 50)
	public String getDomain() {
		return this.domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Column(name = "FIELD", length = 50)
	public String getField() {
		return this.field;
	}

	public void setField(String field) {
		this.field = field;
	}

	@Column(name = "VALUE")
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "DESCRIPTION", length = 100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "biodataCriteria")
	public Set<BiodataGroupCriteria> getBiodataGroupCriterias() {
		return this.biodataGroupCriterias;
	}

	public void setBiodataGroupCriterias(Set<BiodataGroupCriteria> biodataGroupCriterias) {
		this.biodataGroupCriterias = biodataGroupCriterias;
	}

}