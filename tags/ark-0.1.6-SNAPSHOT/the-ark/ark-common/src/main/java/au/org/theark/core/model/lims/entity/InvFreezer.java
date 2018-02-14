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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import au.org.theark.core.model.Constants;

/**
 * InvTank generated by hbm2java
 * Note the hierarchy: site -< tank -< tray -< box -< cell
 * Note the hierarchy: site -< freezer -< rack -< box -< cell
 */
@Entity
@Table(name = "inv_tank", schema = Constants.LIMS_TABLE_SCHEMA)
public class InvFreezer implements java.io.Serializable, InvTreeNode<InvRack> {

	private Long			id;
	private String			timestamp;
	private InvSite		invSite;
	private Integer		deleted;
	private String			location;
	private String			status;
	private Integer		capacity;
	private String			lastservicenote;
	private String			name;
	private Integer		available;
	private Date			decommissiondate;
	private Date			commissiondate;
	private Date			lastservicedate;
	private String			description;
	private List<InvRack>	invTrays	= new ArrayList<InvRack>(0);
	private String			siteFreezer;

	public InvFreezer() {
	}

	public InvFreezer(Long id, InvSite invSite, String name) {
		this.id = id;
		this.invSite = invSite;
		this.name = name;
	}

	public InvFreezer(Long id, InvSite invSite, Integer deleted, String location, String status, Integer capacity, String lastservicenote, String name, Integer available, Date decommissiondate,
			Date commissiondate, Date lastservicedate, String description, List<InvRack> invTrays) {
		this.id = id;
		this.invSite = invSite;
		this.deleted = deleted;
		this.location = location;
		this.status = status;
		this.capacity = capacity;
		this.lastservicenote = lastservicenote;
		this.name = name;
		this.available = available;
		this.decommissiondate = decommissiondate;
		this.commissiondate = commissiondate;
		this.lastservicedate = lastservicedate;
		this.description = description;
		this.invTrays = invTrays;
	}

	@Id
	@SequenceGenerator(name = "invtank_generator", sequenceName = "INVTANK_SEQUENCE")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "invtank_generator")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "TIMESTAMP", length = 55)
	public String getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SITE_ID", nullable = false)
	public InvSite getInvSite() {
		return this.invSite;
	}

	public void setInvSite(InvSite invSite) {
		this.invSite = invSite;
	}

	@Column(name = "DELETED")
	public Integer getDeleted() {
		return this.deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	@Column(name = "LOCATION", length = 65535)
	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "STATUS", length = 50)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "CAPACITY")
	public Integer getCapacity() {
		return this.capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	@Column(name = "LASTSERVICENOTE", length = 65535)
	public String getLastservicenote() {
		return this.lastservicenote;
	}

	public void setLastservicenote(String lastservicenote) {
		this.lastservicenote = lastservicenote;
	}

	@Column(name = "NAME", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "AVAILABLE")
	public Integer getAvailable() {
		return this.available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DECOMMISSIONDATE", length = 19)
	public Date getDecommissiondate() {
		return this.decommissiondate;
	}

	public void setDecommissiondate(Date decommissiondate) {
		this.decommissiondate = decommissiondate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "COMMISSIONDATE", length = 19)
	public Date getCommissiondate() {
		return this.commissiondate;
	}

	public void setCommissiondate(Date commissiondate) {
		this.commissiondate = commissiondate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LASTSERVICEDATE", length = 19)
	public Date getLastservicedate() {
		return this.lastservicedate;
	}

	public void setLastservicedate(Date lastservicedate) {
		this.lastservicedate = lastservicedate;
	}

	@Column(name = "DESCRIPTION", length = 65535)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "invFreezer")
	public List<InvRack> getInvTrays() { 
		return this.invTrays; 
	}
	
	public void setInvTrays(List<InvRack> invTrays) { 
		this.invTrays = invTrays; 
	}

	@Transient
	public List<InvRack> getChildren() {
		return getInvTrays();
	}
	
	@Transient
	public String getNodeName() {
		return getName();
	}
	
	@Transient
	public String getNodeType() {
		return this.getClass().getCanonicalName();
	}

	@Transient
	public String getSiteFreezer() {
		StringBuilder displayExpression = new StringBuilder();
		displayExpression.append(invSite.getName());
		displayExpression.append(" > ");
		displayExpression.append(name);
		return displayExpression.toString();
	}

	public void setSiteFreezer(String siteFreezer) {
		this.siteFreezer = siteFreezer;
	}
}