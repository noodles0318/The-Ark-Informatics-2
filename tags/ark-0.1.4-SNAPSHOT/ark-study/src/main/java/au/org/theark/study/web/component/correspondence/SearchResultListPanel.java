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
package au.org.theark.study.web.component.correspondence;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.StringResourceModel;

import au.org.theark.core.model.study.entity.Correspondences;
import au.org.theark.core.web.component.ArkBusyAjaxLink;
import au.org.theark.study.web.component.correspondence.form.ContainerForm;

public class SearchResultListPanel extends Panel {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 6424424894090501973L;
	private WebMarkupContainer	detailPanelContainer;
	private WebMarkupContainer	detailPanelFormContainer;
	private WebMarkupContainer	searchPanelContainer;
	private WebMarkupContainer	searchResultContainer;
	private WebMarkupContainer	viewButtonContainer;
	private WebMarkupContainer	editButtonContainer;
	private ContainerForm		containerForm;

	public SearchResultListPanel(String id, WebMarkupContainer detailPanelContainer, WebMarkupContainer detailPanelFormContainer, WebMarkupContainer searchPanelContainer,
			WebMarkupContainer searchResultContainer, WebMarkupContainer viewButtonContainer, WebMarkupContainer editButtonContainer, ContainerForm containerForm) {

		super(id);
		this.detailPanelContainer = detailPanelContainer;
		this.searchPanelContainer = searchPanelContainer;
		this.searchResultContainer = searchResultContainer;
		this.viewButtonContainer = viewButtonContainer;
		this.editButtonContainer = editButtonContainer;
		this.detailPanelFormContainer = detailPanelFormContainer;
		this.containerForm = containerForm;
	}

	@SuppressWarnings("unchecked")
	public PageableListView<Correspondences> buildPageableListView(IModel iModel) {

		PageableListView<Correspondences> pageableListView = new PageableListView<Correspondences>("correspondenceList", iModel, au.org.theark.core.Constants.ROWS_PER_PAGE) {
			/**
			 * 
			 */
			private static final long	serialVersionUID	= 9076367524574951367L;

			@Override
			protected void populateItem(final ListItem<Correspondences> item) {
				Correspondences correspondence = item.getModelObject();

				// set the date to be the link to details
				item.add(buildLink(correspondence));

				if (correspondence.getTime() != null) {
					item.add(new Label("time", correspondence.getTime()));
				}
				else {
					item.add(new Label("time", ""));
				}

				if (correspondence.getOperator() != null) {
					item.add(new Label("operator.ldapUserName", correspondence.getOperator().getLdapUserName()));
				}
				else {
					item.add(new Label("operator.ldapUserName", ""));
				}

				if (correspondence.getCorrespondenceModeType() != null) {
					item.add(new Label("correspondenceModeType.name", correspondence.getCorrespondenceModeType().getName()));
				}
				else {
					item.add(new Label("correspondenceModeType.name", ""));
				}

				if (correspondence.getCorrespondenceDirectionType() != null) {
					item.add(new Label("correspondenceDirectionType.name", correspondence.getCorrespondenceDirectionType().getName()));
				}
				else {
					item.add(new Label("correspondenceDirectionType.name", ""));
				}

				if (correspondence.getCorrespondenceOutcomeType() != null) {
					item.add(new Label("correspondenceOutcomeType.name", correspondence.getCorrespondenceOutcomeType().getName()));
				}
				else {
					item.add(new Label("correspondenceOutcomeType.name", ""));
				}

				if (correspondence.getReason() != null) {
					item.add(new Label("reason", correspondence.getReason()));
				}
				else {
					item.add(new Label("reason", ""));
				}

				// Download file link button
				item.add(buildDownloadButton(correspondence));

				item.add(new AttributeModifier("class", true, new AbstractReadOnlyModel() {
					/**
					 * 
					 */
					private static final long	serialVersionUID	= -1588380616547616236L;

					@Override
					public String getObject() {
						return (item.getIndex() % 2 == 1) ? "even" : "odd";
					}
				}));
			}
		};

		return pageableListView;
	}

	@SuppressWarnings({ "unchecked" })
	private AjaxLink buildLink(final Correspondences correspondence) {
		ArkBusyAjaxLink link = new ArkBusyAjaxLink("correspondence") {
			/**
			 * 
			 */
			private static final long	serialVersionUID	= 826367436671619720L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				containerForm.getModelObject().setCorrespondence(correspondence);

				detailPanelContainer.setVisible(true);
				viewButtonContainer.setVisible(true);
				viewButtonContainer.setEnabled(true);
				detailPanelFormContainer.setEnabled(false);
				searchResultContainer.setVisible(false);
				searchPanelContainer.setVisible(false);
				editButtonContainer.setVisible(false);

				target.addComponent(searchResultContainer);
				target.addComponent(detailPanelContainer);
				target.addComponent(detailPanelFormContainer);
				target.addComponent(searchPanelContainer);
				target.addComponent(viewButtonContainer);
				target.addComponent(editButtonContainer);
			}
		};

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(au.org.theark.core.Constants.DD_MM_YYYY);
		String dateOfCorrespondence = "";

		if (correspondence.getDate() != null)
			dateOfCorrespondence = simpleDateFormat.format(correspondence.getDate());

		Label nameLinkLabel = new Label("correspondenceLabel", dateOfCorrespondence);
		link.add(nameLinkLabel);
		return link;
	}

	private AjaxButton buildDownloadButton(final Correspondences correspondences) {
		AjaxButton ajaxButton = new AjaxButton(au.org.theark.study.web.Constants.DOWNLOAD_FILE, new StringResourceModel("downloadKey", this, null)) {
			/**
			 * 
			 */
			private static final long	serialVersionUID	= 4494157023173040075L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				// Attempt to download the Blob as an array of bytes
				byte[] data = null;
				try {
					data = correspondences.getAttachmentPayload().getBytes(1, (int) correspondences.getAttachmentPayload().length());
				}
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				getRequestCycle().setRequestTarget(new au.org.theark.core.util.ByteDataRequestTarget("", data, correspondences.getAttachmentFilename()));
			};
		};

		ajaxButton.setVisible(true);
		ajaxButton.setDefaultFormProcessing(false);

		if (correspondences.getAttachmentFilename() == null)
			ajaxButton.setVisible(false);

		return ajaxButton;
	}
}