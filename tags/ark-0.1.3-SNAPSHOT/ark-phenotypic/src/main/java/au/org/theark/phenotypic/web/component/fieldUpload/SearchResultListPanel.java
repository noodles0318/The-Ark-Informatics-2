package au.org.theark.phenotypic.web.component.fieldUpload;

import java.sql.SQLException;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.org.theark.core.Constants;
import au.org.theark.core.exception.ArkSystemException;
import au.org.theark.core.exception.EntityCannotBeRemoved;
import au.org.theark.core.model.pheno.entity.FieldUpload;
import au.org.theark.core.model.pheno.entity.PhenoUpload;
import au.org.theark.core.web.component.AjaxDeleteButton;
import au.org.theark.core.web.component.ArkDownloadTemplateButton;
import au.org.theark.phenotypic.service.IPhenotypicService;
import au.org.theark.phenotypic.web.component.fieldUpload.form.ContainerForm;

@SuppressWarnings( { "serial", "unchecked", "unused" })
public class SearchResultListPanel extends Panel
{
	@SpringBean(name = au.org.theark.phenotypic.service.Constants.PHENOTYPIC_SERVICE)
	private IPhenotypicService	iPhenotypicService;

	private transient Logger	log	= LoggerFactory.getLogger(SearchResultListPanel.class);

	private WebMarkupContainer	detailsPanelContainer;
	private WebMarkupContainer	feedBackPanel;
	private WebMarkupContainer	searchPanelContainer;
	private WebMarkupContainer	searchResultContainer;
	private ContainerForm		containerForm;
	private DetailPanel			detailPanel;
	private WebMarkupContainer	detailPanelFormContainer;
	private WebMarkupContainer	viewButtonContainer;
	private WebMarkupContainer	editButtonContainer;

	public SearchResultListPanel(String id, WebMarkupContainer detailPanelContainer, WebMarkupContainer feedBackPanel, WebMarkupContainer searchPanelContainer, ContainerForm studyCompContainerForm,
			WebMarkupContainer searchResultContainer, DetailPanel detail, WebMarkupContainer viewButtonContainer, WebMarkupContainer editButtonContainer, WebMarkupContainer detailPanelFormContainer)
	{
		super(id);
		this.detailsPanelContainer = detailPanelContainer;
		this.feedBackPanel = feedBackPanel;
		this.containerForm = studyCompContainerForm;
		this.searchPanelContainer = searchPanelContainer;
		this.searchResultContainer = searchResultContainer;
		this.viewButtonContainer = viewButtonContainer;
		this.editButtonContainer = editButtonContainer;
		this.detailPanelFormContainer = detailPanelFormContainer;
		this.setDetailPanel(detail);

		ArkDownloadTemplateButton downloadTemplateButton = new ArkDownloadTemplateButton("downloadTemplate", "DataDictionaryUpload", au.org.theark.phenotypic.web.Constants.DATA_DICTIONARY_HEADER);
		add(downloadTemplateButton);
	}

	/**
	 * 
	 * @param iModel
	 * @return the pageableListView of Upload
	 */
	public PageableListView<PhenoUpload> buildPageableListView(IModel iModel)
	{
		PageableListView<PhenoUpload> sitePageableListView = new PageableListView<PhenoUpload>(Constants.RESULT_LIST, iModel, au.org.theark.core.Constants.ROWS_PER_PAGE)
		{
			@Override
			protected void populateItem(final ListItem<PhenoUpload> item)
			{
				PhenoUpload upload = item.getModelObject();

				// The ID
				if (upload.getId() != null)
				{
					// Add the id component here
					item.add(new Label(au.org.theark.phenotypic.web.Constants.UPLOADVO_UPLOAD_ID, upload.getId().toString()));
				}
				else
				{
					item.add(new Label(au.org.theark.phenotypic.web.Constants.UPLOADVO_UPLOAD_ID, ""));
				}

				// / The filename
				if (upload.getFilename() != null)
				{
					// Add the id component here
					item.add(new Label(au.org.theark.phenotypic.web.Constants.UPLOADVO_UPLOAD_FILENAME, upload.getFilename()));
				}
				else
				{
					item.add(new Label(au.org.theark.phenotypic.web.Constants.UPLOADVO_UPLOAD_FILENAME, ""));
				}

				// TODO when displaying text escape any special characters
				// File Format
				if (upload.getFileFormat() != null)
				{
					item.add(new Label(au.org.theark.phenotypic.web.Constants.UPLOADVO_UPLOAD_FILE_FORMAT, upload.getFileFormat().getName()));// the name
					// here
					// must match the
					// ones in mark-up
				}
				else
				{
					item.add(new Label(au.org.theark.phenotypic.web.Constants.UPLOADVO_UPLOAD_FILE_FORMAT, ""));// the ID here must match the ones in
					// mark-up
				}

				// TODO when displaying text escape any special characters
				// UserId
				if (upload.getUserId() != null)
				{
					item.add(new Label(au.org.theark.phenotypic.web.Constants.UPLOADVO_UPLOAD_USER_ID, upload.getUserId()));// the ID here must match the
					// ones in
					// mark-up
				}
				else
				{
					item.add(new Label(au.org.theark.phenotypic.web.Constants.UPLOADVO_UPLOAD_USER_ID, ""));// the ID here must match the ones in mark-up
				}

				/*
				 * TODO when displaying text escape any special characters // Insert time if (upload.getInsertTime() != null) { item.add(new
				 * Label(au.org.theark.phenotypic.web.Constants. UPLOADVO_UPLOAD_INSERT_TIME, upload.getInsertTime().toString()));// the ID // here must
				 * // match the // ones in mark-up } else { item.add(new Label(au.org .theark.phenotypic.web.Constants.UPLOADVO_UPLOAD_INSERT_TIME,
				 * ""));// the ID here must match the ones in // mark-up }
				 */

				// Start time
				if (upload.getStartTime() != null)
				{
					item.add(new Label(au.org.theark.phenotypic.web.Constants.UPLOADVO_UPLOAD_START_TIME, upload.getStartTime().toString()));// the ID here
					// must
					// match the
					// ones in mark-up
				}
				else
				{
					item.add(new Label(au.org.theark.phenotypic.web.Constants.UPLOADVO_UPLOAD_START_TIME, ""));// the ID here must match the ones in
					// mark-up
				}

				// Finish time
				if (upload.getFinishTime() != null)
				{
					item.add(new Label(au.org.theark.phenotypic.web.Constants.UPLOADVO_UPLOAD_FINISH_TIME, upload.getInsertTime().toString()));// the ID
					// here must
					// match the
					// ones in mark-up
				}
				else
				{
					item.add(new Label(au.org.theark.phenotypic.web.Constants.UPLOADVO_UPLOAD_FINISH_TIME, ""));
					// the ID here must match the ones in mark-up
				}

				// Download file link button
				item.add(buildDownloadButton(upload));

				// Download upload report button
				item.add(buildDownloadReportButton(upload));

				// Delete the upload file
				item.add(buildDeleteButton(upload));

				// For the alternative stripes
				item.add(new AttributeModifier("class", true, new AbstractReadOnlyModel()
				{
					@Override
					public String getObject()
					{
						return (item.getIndex() % 2 == 1) ? "even" : "odd";
					}
				}));
			}
		};
		return sitePageableListView;
	}

	private Link buildDownloadLink(final PhenoUpload upload)
	{
		Link link = new Link(au.org.theark.phenotypic.web.Constants.DOWNLOAD_FILE)
		{
			@Override
			public void onClick()
			{
				// Attempt to download the Blob as an array of bytes
				byte[] data = null;
				try
				{
					data = upload.getPayload().getBytes(1, (int) upload.getPayload().length());
				}
				catch (SQLException e)
				{
					log.error(e.getMessage());
				}
				getRequestCycle().setRequestTarget(new au.org.theark.core.util.ByteDataRequestTarget("text/plain", data, upload.getFilename()));

			};
		};

		// Add the label for the link
		// TODO when displaying text escape any special characters
		Label nameLinkLabel = new Label("downloadFileLbl", "Download File");
		link.add(nameLinkLabel);
		return link;
	}

	private AjaxButton buildDownloadButton(final PhenoUpload upload)
	{
		AjaxButton ajaxButton = new AjaxButton(au.org.theark.phenotypic.web.Constants.DOWNLOAD_FILE, new StringResourceModel("downloadKey", this, null))
		{
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form)
			{
				// Attempt to download the Blob as an array of bytes
				byte[] data = null;
				try
				{
					data = upload.getPayload().getBytes(1, (int) upload.getPayload().length());
				}
				catch (SQLException e)
				{
					log.error(e.getMessage());
				}
				getRequestCycle().setRequestTarget(new au.org.theark.core.util.ByteDataRequestTarget("text/plain", data, upload.getFilename()));
			};
		};

		ajaxButton.setDefaultFormProcessing(false);

		if (upload.getPayload() == null)
			ajaxButton.setVisible(false);

		return ajaxButton;
	}

	private Link buildDownloadReportLink(final PhenoUpload upload)
	{
		Link link = new Link(au.org.theark.phenotypic.web.Constants.UPLOADVO_UPLOAD_UPLOAD_REPORT)
		{
			@Override
			public void onClick()
			{
				// Attempt to download the Blob as an array of bytes
				byte[] data = null;
				try
				{
					data = upload.getUploadReport().getBytes(1, (int) upload.getUploadReport().length());
				}
				catch (SQLException e)
				{
					log.error(e.getMessage());
				}
				getRequestCycle().setRequestTarget(new au.org.theark.core.util.ByteDataRequestTarget("text/plain", data, "uploadReport" + upload.getId()));
			};
		};

		// Add the label for the link
		// TODO when displaying text escape any special characters
		Label nameLinkLabel = new Label("downloadReportLbl", "Download Report");
		link.add(nameLinkLabel);
		return link;
	}

	private AjaxButton buildDownloadReportButton(final PhenoUpload upload)
	{
		AjaxButton ajaxButton = new AjaxButton(au.org.theark.phenotypic.web.Constants.UPLOADVO_UPLOAD_UPLOAD_REPORT, new StringResourceModel("downloadReportKey", this, null))
		{
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form)
			{
				// Attempt to download the Blob as an array of bytes
				byte[] data = null;
				try
				{
					data = upload.getUploadReport().getBytes(1, (int) upload.getUploadReport().length());
				}
				catch (SQLException e)
				{
					log.error(e.getMessage());
				}
				getRequestCycle().setRequestTarget(new au.org.theark.core.util.ByteDataRequestTarget("text/plain", data, "uploadReport" + upload.getId()));
			};
		};

		ajaxButton.setVisible(true);
		ajaxButton.setDefaultFormProcessing(false);

		if (upload.getUploadReport() == null)
			ajaxButton.setVisible(false);

		return ajaxButton;
	}

	private AjaxDeleteButton buildDeleteButton(final PhenoUpload upload)
	{
		DeleteButton ajaxButton = new DeleteButton(upload, SearchResultListPanel.this)
		{
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form)
			{
				// Attempt to delete upload
				if (upload.getId() != null)
				{
					try
					{
						iPhenotypicService.deleteUpload(upload);
						containerForm.info("Data Upload file " + upload.getFilename() + " was deleted successfully.");
					}
					catch (ArkSystemException e)
					{
						containerForm.error(e.getMessage());
						log.error(e.getMessage());
					}
					catch (EntityCannotBeRemoved e)
					{
						containerForm.error(e.getMessage());
						log.error(e.getMessage());
					}
				}

				// Update the result panel and containerForm (for feedBack message)
				target.addComponent(searchResultContainer);
				target.addComponent(containerForm);
			}
		};

		ajaxButton.setDefaultFormProcessing(false);

		return ajaxButton;
	}

	/**
	 * @param detailPanel
	 *           the detailPanel to set
	 */
	public void setDetailPanel(DetailPanel detailPanel)
	{
		this.detailPanel = detailPanel;
	}

	/**
	 * @return the detailPanel
	 */
	public DetailPanel getDetailPanel()
	{
		return detailPanel;
	}
}