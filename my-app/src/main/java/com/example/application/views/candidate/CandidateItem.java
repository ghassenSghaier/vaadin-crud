//package com.example.application.views.candidate;
//import com.example.application.model.dto.CandidateDTO;
//import com.example.application.model.entity.Candidate;
//import com.vaadin.flow.component.Component;
//import com.vaadin.flow.component.ComponentEvent;
//import com.vaadin.flow.component.ComponentEventListener;
//import com.vaadin.flow.component.Key;
//import com.vaadin.flow.component.button.ButtonVariant;
//import com.vaadin.flow.component.formlayout.FormLayout;
//import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
//import com.vaadin.flow.component.button.Button;
//import com.vaadin.flow.shared.Registration;
//
//public class CandidateItem extends FormLayout {
//
//    CandidateDTO candidate;
//    Button close = new Button("Cancel");
//
//    public CandidateDTO getCandidate() {
//        return candidate;
//    }
//
//    public void setCandidate(CandidateDTO candidate) {
//        this.candidate = candidate;
//    }
//
//    private Component createButtonsLayout() {
//
//        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
//        close.addClickShortcut(Key.ESCAPE);
//        close.addClickListener(click -> fireEvent(new CloseEvent(this)));
//
//        return new HorizontalLayout(close);
//    }
//    // Events
//    public static abstract class ContactFormEvent extends ComponentEvent<CandidateItem> {
//        private CandidateDTO candidate;
//
//        protected ContactFormEvent(CandidateItem source, CandidateDTO contact) {
//            super(source, false);
//            this.candidate = contact;
//        }
//
//        public CandidateDTO getContact() {
//            return candidate;
//        }
//    }
//
//    public static class CloseEvent extends ContactFormEvent {
//        CloseEvent(CandidateItem source) {
//            super(source, null);
//        }
//    }
//
//    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
//                                                                  ComponentEventListener<T> listener) {
//        return getEventBus().addListener(eventType, listener);
//    }
//}
