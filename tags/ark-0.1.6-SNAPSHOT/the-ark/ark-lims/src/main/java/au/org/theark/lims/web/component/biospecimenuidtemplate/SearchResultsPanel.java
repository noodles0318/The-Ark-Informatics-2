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
package au.org.theark.lims.web.component.biospecimenuidtemplate;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.org.theark.core.model.lims.entity.BiospecimenUidTemplate;
import au.org.theark.core.vo.ArkCrudContainerVO;
import au.org.theark.core.web.component.ArkDataProvider;
import au.org.theark.core.web.component.link.ArkBusyAjaxLink;
import au.org.theark.lims.service.ILimsAdminService;
import au.org.theark.lims.web.component.biospecimenuidtemplate.form.ContainerForm;

public class SearchResultsPanel extends Panel {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -5165716102918554398L;

	protected transient Logger	log					= LoggerFactory.getLogger(SearchResultsPanel.class);

	@SpringBean(name = au.org.theark.lims.web.Constants.LIMS_ADMIN_SERVICE)
	private ILimsAdminService		iLimsAdminService;

	private ContainerForm		containerForm;
	private ArkCrudContainerVO	arkCrudContainerVo;

	public SearchResultsPanel(String id, ContainerForm containerForm, ArkCrudContainerVO arkCrudContainerVo) {
		super(id);
		this.containerForm = containerForm;
		this.arkCrudContainerVo = arkCrudContainerVo;
	}

	public DataView<BiospecimenUidTemplate> buildDataView(ArkDataProvider<BiospecimenUidTemplate, ILimsAdminService> dataProvider) {
		DataView<BiospecimenUidTemplate> dataView = new DataView<BiospecimenUidTemplate>("resultList", dataProvider) {
			/**
			 * 
			 */
			private static final long	serialVersionUID	= 2981419595326128410L;

			@Override
			protected void populateItem(final Item<BiospecimenUidTemplate> item) {
				BiospecimenUidTemplate biospecimenUidTemplate = item.getModelObject();

				item.add(buildLink(biospecimenUidTemplate));
				
				item.add(new Label("study", biospecimenUidTemplate.getStudy().getName()));

				if (biospecimenUidTemplate.getBiospecimenUidPrefix() != null) {
					// the ID here must match the ones in mark-up
					item.add(new Label("biospecimenUid.template", getBiospecimenUidExample(biospecimenUidTemplate)));
				}
				else {
					item.add(new Label("biospecimenUid.template", ""));
				}

				item.add(new AttributeModifier("class", new AbstractReadOnlyModel<String>() {
					/**
					 * 
					 */
					private static final long	serialVersionUID	= 1L;

					@Override
					public String getObject() {
						return (item.getIndex() % 2 == 1) ? "even" : "odd";
					}
				}));
			}
		};
		return dataView;
	}

	@SuppressWarnings("unchecked")
	public PageableListView<BiospecimenUidTemplate> buildPageableListView(IModel iModel, final WebMarkupContainer searchResultsContainer) {
		PageableListView<BiospecimenUidTemplate> pageableListView = new PageableListView<BiospecimenUidTemplate>("resultList", iModel, au.org.theark.core.Constants.ROWS_PER_PAGE) {
			/**
			 * 
			 */
			private static final long	serialVersionUID	= 1L;

			@Override
			protected void populateItem(final ListItem<BiospecimenUidTemplate> item) {
				BiospecimenUidTemplate biospecimenUidTemplate = item.getModelObject();

				item.add(buildLink(biospecimenUidTemplate));
				
				item.add(new Label("study", biospecimenUidTemplate.getStudy().getName()));

				if (biospecimenUidTemplate.getBiospecimenUidPrefix() != null) {
					// the ID here must match the ones in mark-up
					item.add(new Label("biospecimenUid.template", getBiospecimenUidExample(biospecimenUidTemplate)));
				}
				else {
					item.add(new Label("biospecimenUid.template", ""));
				}

				item.add(new AttributeModifier("class", new AbstractReadOnlyModel<String>() {
					/**
					 * 
					 */
					private static final long	serialVersionUID	= 1L;

					@Override
					public String getObject() {
						return (item.getIndex() % 2 == 1) ? "even" : "odd";
					}
				}));

			}
		};
		return pageableListView;
	}

	@SuppressWarnings( { "unchecked", "serial" })
	private AjaxLink buildLink(final BiospecimenUidTemplate BiospecimenUidTemplate) {
		ArkBusyAjaxLink link = new ArkBusyAjaxLink("link") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				BiospecimenUidTemplate BiospecimenUidTemplateFromDb = iLimsAdminService.searchBiospecimenUidTemplate(BiospecimenUidTemplate);
				containerForm.setModelObject(BiospecimenUidTemplateFromDb);

				arkCrudContainerVo.getSearchResultPanelContainer().setVisible(false);
				arkCrudContainerVo.getSearchPanelContainer().setVisible(false);
				arkCrudContainerVo.getDetailPanelContainer().setVisible(true);
				arkCrudContainerVo.getDetailPanelFormContainer().setEnabled(false);
				arkCrudContainerVo.getViewButtonContainer().setVisible(true);
				arkCrudContainerVo.getViewButtonContainer().setEnabled(true);
				arkCrudContainerVo.getEditButtonContainer().setVisible(false);

				// Refresh the markup containers
				target.add(arkCrudContainerVo.getSearchResultPanelContainer());
				target.add(arkCrudContainerVo.getDetailPanelContainer());
				target.add(arkCrudContainerVo.getDetailPanelFormContainer());
				target.add(arkCrudContainerVo.getSearchPanelContainer());
				target.add(arkCrudContainerVo.getViewButtonContainer());
				target.add(arkCrudContainerVo.getEditButtonContainer());

				// Refresh base container form to remove any feedBack messages
				target.add(containerForm);
			}
		};

		// Add the label for the link
		Label linkLabel = new Label("id", BiospecimenUidTemplate.getId().toString());
		link.add(linkLabel);
		return link;
	}
	
	public String getBiospecimenUidExample(BiospecimenUidTemplate biospecimenUidTemplate) {
		String biospecimenUidPrefix = new String("");
		String biospecimenUidToken = new String("");
		String biospecimenUidPaddedIncrementor = new String("");
		String biospecimenUidPadChar = new String("0");
		String biospecimenUidStart = new String("1");
		StringBuilder biospecimenUidExample = new StringBuilder();

		if (biospecimenUidTemplate.getBiospecimenUidPrefix() != null)
			biospecimenUidPrefix = biospecimenUidTemplate.getBiospecimenUidPrefix();

		if (biospecimenUidTemplate.getBiospecimenUidToken() != null)
			biospecimenUidToken = biospecimenUidTemplate.getBiospecimenUidToken().getName();

		if (biospecimenUidTemplate.getBiospecimenUidPadChar() != null) {
			biospecimenUidPadChar = biospecimenUidTemplate.getBiospecimenUidPadChar().getName().trim();
		}

		int size = Integer.parseInt(biospecimenUidPadChar);
		biospecimenUidPaddedIncrementor = StringUtils.leftPad(biospecimenUidStart, size, "0");
		
		biospecimenUidExample.append(biospecimenUidPrefix);
		biospecimenUidExample.append(biospecimenUidToken);
		biospecimenUidExample.append(biospecimenUidPaddedIncrementor);

		return biospecimenUidExample.toString();
	}
}