package au.org.theark.study.web.component.pedigree.form;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import au.org.theark.core.vo.ArkCrudContainerVO;
import au.org.theark.core.web.component.AbstractDetailModalWindow;
import au.org.theark.study.model.vo.PedigreeVo;
import au.org.theark.study.web.Constants;
import au.org.theark.study.web.component.pedigree.PedigreeParentContainerPanel;

public class SaveForm extends Form<PedigreeVo> {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	protected FeedbackPanel			feedbackPanel;
	
	protected ArkCrudContainerVO arkCrudContainerVO;
   
	protected AjaxButton saveButton;
	protected AjaxButton cancelButton;
	
	protected AbstractDetailModalWindow modalWindow;
	
	public SaveForm(String id,FeedbackPanel feedbackPanel,ArkCrudContainerVO arkCrudContainerVO, AbstractDetailModalWindow modalWindow ) {
		super(id);
		
		this.feedbackPanel =feedbackPanel;
		this.arkCrudContainerVO = arkCrudContainerVO;
		this.modalWindow =modalWindow;
		
		initialiseSaveForm();
		addSaveComponentsToForm();
		// TODO Auto-generated constructor stub
	}

	protected void addSaveComponentsToForm() {
		// TODO Auto-generated method stub
		add(saveButton);
		add(cancelButton);
	}

	protected void initialiseSaveForm() {
		// TODO Auto-generated method stub
		saveButton = new AjaxButton(au.org.theark.core.Constants.SAVE){
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				modalWindow.close(target);
			}						
		};
		
		cancelButton = new AjaxButton(au.org.theark.core.Constants.CANCEL){
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				modalWindow.close(target);
			}						
		};
	}

}
