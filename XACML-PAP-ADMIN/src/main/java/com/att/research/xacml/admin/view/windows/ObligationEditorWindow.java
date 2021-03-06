/*
 *
 *          Copyright (c) 2014,2019  AT&T Knowledge Ventures
 *                     SPDX-License-Identifier: MIT
 */
package com.att.research.xacml.admin.view.windows;

import oasis.names.tc.xacml._3_0.core.schema.wd_17.EffectType;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.ObligationExpressionType;

import com.att.research.xacml.admin.XacmlAdminUI;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Buffered.SourceException;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ObligationEditorWindow extends Window {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private Button buttonSave;
	@AutoGenerated
	private OptionGroup optionGroupFullfillOn;
	@AutoGenerated
	private TextField textFieldObligationID;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ObligationEditorWindow self = this;
	private final ObligationExpressionType obligation;
	private boolean isSaved = false;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 * @param obligation 
	 */
	public ObligationEditorWindow(ObligationExpressionType obligation) {
		buildMainLayout();
		//setCompositionRoot(mainLayout);
		setContent(mainLayout);
		//
		// Save
		//
		this.obligation = obligation;
		//
		// Set our shortcuts
		//
		this.setCloseShortcut(KeyCode.ESCAPE);
		//
		// Initialize GUI
		//
		this.initialize();
		this.initializeButton();
		//
		// Focus
		//
		this.textFieldObligationID.focus();
	}
	
	protected void initialize() {
		//
		// The text field for the advice ID
		//
		this.textFieldObligationID.setNullRepresentation("");
		if (this.obligation.getObligationId() == null) {
			this.textFieldObligationID.setValue(XacmlAdminUI.getDomain());
		} else {
			this.textFieldObligationID.setValue(obligation.getObligationId());
		}
		this.textFieldObligationID.setRequiredError("You must have an ID for the obligation");
		//
		// The option
		//
		this.optionGroupFullfillOn.setRequiredError("You must select Permit or Deny for the obligation");
		this.optionGroupFullfillOn.addItem(EffectType.PERMIT);
		this.optionGroupFullfillOn.addItem(EffectType.DENY);
		if (this.obligation.getFulfillOn() == null) {
			this.optionGroupFullfillOn.select(EffectType.PERMIT);
		} else {
			if (this.obligation.getFulfillOn().equals(EffectType.PERMIT)) {
				this.optionGroupFullfillOn.select(EffectType.PERMIT);
			} else {
				this.optionGroupFullfillOn.select(EffectType.DENY);
			}
		}
	}
	
	protected void initializeButton() {
		this.buttonSave.setImmediate(true);
		this.buttonSave.setClickShortcut(KeyCode.ENTER);
		this.buttonSave.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					//
					// Commit
					//
					self.textFieldObligationID.commit();
					self.optionGroupFullfillOn.commit();
					//
					// all good, save everything
					//
					self.obligation.setObligationId(self.textFieldObligationID.getValue());
					self.obligation.setFulfillOn((EffectType) self.optionGroupFullfillOn.getValue());
					//
					// Set ourselves as saved
					//
					self.isSaved = true;
					//
					// Close the window
					//
					self.close();
				} catch (SourceException | InvalidValueException e) {
					//
					// Vaadin displays the error
					//
				}
			}			
		});
	}
	
	public boolean isSaved() {
		return this.isSaved;
	}
	
	public ObligationExpressionType getAdvice() {
		return this.obligation;
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("-1px");
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		
		// top-level component properties
		setWidth("-1px");
		setHeight("-1px");
		
		// textFieldObligationID
		textFieldObligationID = new TextField();
		textFieldObligationID.setCaption("Obligation ID");
		textFieldObligationID.setImmediate(false);
		textFieldObligationID.setWidth("-1px");
		textFieldObligationID.setHeight("-1px");
		textFieldObligationID.setInvalidAllowed(false);
		textFieldObligationID.setRequired(true);
		textFieldObligationID.setInputPrompt("Eg. urn:com:foo:obligation:sample");
		mainLayout.addComponent(textFieldObligationID);
		
		// optionGroupFullfillOn
		optionGroupFullfillOn = new OptionGroup();
		optionGroupFullfillOn.setCaption("Fulfill On");
		optionGroupFullfillOn.setImmediate(false);
		optionGroupFullfillOn.setWidth("-1px");
		optionGroupFullfillOn.setHeight("-1px");
		optionGroupFullfillOn.setInvalidAllowed(false);
		optionGroupFullfillOn.setRequired(true);
		mainLayout.addComponent(optionGroupFullfillOn);
		
		// buttonSave
		buttonSave = new Button();
		buttonSave.setCaption("Save");
		buttonSave.setImmediate(true);
		buttonSave.setWidth("-1px");
		buttonSave.setHeight("-1px");
		mainLayout.addComponent(buttonSave);
		mainLayout.setComponentAlignment(buttonSave, new Alignment(48));
		
		return mainLayout;
	}

}
