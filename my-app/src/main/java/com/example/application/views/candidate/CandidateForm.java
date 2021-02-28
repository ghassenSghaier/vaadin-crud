package com.example.application.views.candidate;

import com.example.application.model.dto.CandidateDTO;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

public class CandidateForm extends FormLayout {

    //-----------------------------------------------------//

    TextField candidateId = new TextField("cin");
    TextField firstName = new TextField("First name");
    TextField lastName = new TextField("Last Name");
    TextField email = new TextField("Email");
    TextField phone = new TextField("phone");

    //-----------------------------------------------------//

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    //-----------------------------------------------------//

    Binder<CandidateDTO> binder = new BeanValidationBinder<>(CandidateDTO.class);
    private CandidateDTO candidate;

    public CandidateForm() {
        //addClassName("candidate-form");
        binder.bindInstanceFields(this);
        add(
            candidateId,
            firstName,
            lastName,
            email,
            phone,
            createButtonsLayout()
        );
    }

    public void setCandidate(CandidateDTO candidate) {
        this.candidate = candidate;
        binder.readBean(candidate);
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(click -> validateAndSave());
        delete.addClickListener(click -> fireEvent(new DeleteEvent(this, candidate)));
        close.addClickListener(click -> fireEvent(new CloseEvent(this)));
        binder.addStatusChangeListener(evt -> save.setEnabled(binder.isValid()));
        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
      try {
        binder.writeBean(candidate);
        fireEvent(new SaveEvent(this, candidate));
      } catch (ValidationException e) {
        e.printStackTrace();
      }
    }

    // Events
    public static abstract class CandidateFormEvent extends ComponentEvent<CandidateForm> {
      private CandidateDTO candidate;

      protected CandidateFormEvent(CandidateForm source, CandidateDTO contact) {
        super(source, false);
        this.candidate = contact;
      }

      public CandidateDTO getCandidate() {
        return candidate;
      }
    }

    public static class SaveEvent extends CandidateFormEvent {
      SaveEvent(CandidateForm source, CandidateDTO contact) {
        super(source, contact);
      }
    }

    public static class DeleteEvent extends CandidateFormEvent {
      DeleteEvent(CandidateForm source, CandidateDTO contact) {
        super(source, contact);
      }

    }

    public static class CloseEvent extends CandidateFormEvent {
      CloseEvent(CandidateForm source) {
        super(source, null);
      }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
      return getEventBus().addListener(eventType, listener);
    }
}
