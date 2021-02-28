package com.example.application.views.jobapp;
import com.example.application.model.dto.JobAnnouncementDTO;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

public class JobForm extends FormLayout {

    //-----------------------------------------------------//
    TextField company = new TextField("Company Name");
    TextField position = new TextField("Job Title");
    DatePicker startDate = new DatePicker("Start date");
    DatePicker endDate = new DatePicker("End Date");
    TextField description = new TextField("Description");
    //-----------------------------------------------------//
    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");
    //-----------------------------------------------------//

    Binder<JobAnnouncementDTO> binder = new BeanValidationBinder<>(JobAnnouncementDTO.class);
    private JobAnnouncementDTO job;

    public JobForm() {
        addClassName("candidate-form");
        binder.bindInstanceFields(this);
        add(
            company,
            position,
            startDate,
            endDate,
            description,
            createButtonsLayout()
        );
    }

    public void setJob(JobAnnouncementDTO job) {
        this.job = job;
        binder.readBean(job);
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(click -> validateAndSave());
        delete.addClickListener(click -> fireEvent(new DeleteEvent(this, job)));
        close.addClickListener(click -> fireEvent(new CloseEvent(this)));
        binder.addStatusChangeListener(evt -> save.setEnabled(binder.isValid()));
        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
      try {
        binder.writeBean(job);
        fireEvent(new SaveEvent(this, job));
      } catch (ValidationException e) {
        e.printStackTrace();
      }
    }

    // Events
    public static abstract class JobFormEvent extends ComponentEvent<JobForm> {
      private JobAnnouncementDTO job;

      protected JobFormEvent(JobForm source, JobAnnouncementDTO job) {
        super(source, false);
        this.job = job;
      }

      public JobAnnouncementDTO getJob() {
        return job;
      }
    }

    public static class SaveEvent extends JobFormEvent {
      SaveEvent(JobForm source, JobAnnouncementDTO contact) {
        super(source, contact);
      }
    }

    public static class DeleteEvent extends JobFormEvent {
      DeleteEvent(JobForm source, JobAnnouncementDTO contact) {
        super(source, contact);
      }

    }

    public static class CloseEvent extends JobFormEvent {
      CloseEvent(JobForm source) {
        super(source, null);
      }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
      return getEventBus().addListener(eventType, listener);
    }
}
