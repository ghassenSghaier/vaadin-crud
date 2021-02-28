package com.example.application.views.candidate;
import com.example.application.model.dto.CandidateDTO;
import com.example.application.service.CandidateService;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.ItemClickEvent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.vaadin.klaudeta.PaginatedGrid;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@CssImport("./views/candidate/candidate-view.css")
@Route(value = "candidate", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Candidates")
public class CandidateView extends VerticalLayout {

    private  CandidateService cService;
    private  final CandidateForm form;
    PaginatedGrid<CandidateDTO> grid;
    private static final Logger logger = Logger.getLogger(CandidateView.class.getName());

    public CandidateView(CandidateService cService) {
        this.cService = cService;
        addClassName("candidate-view");
        grid = new PaginatedGrid<>();
        //grid.setSizeFull();
        grid.addClassName("candidate-grid");
        grid.addColumn(CandidateDTO::getCandidateId).setHeader("Id Card Number");
        grid.addColumn(CandidateDTO::getFirstName).setHeader("First Name").setSortable(true);
        grid.addColumn(CandidateDTO::getLastName).setHeader("Last Name").setSortable(true);
        grid.addColumn(CandidateDTO::getEmail).setHeader("Email").setSortable(true);
        grid.addColumn(CandidateDTO::getPhone).setHeader("Phone Number").setSortable(true);
        grid.setDataProvider(createCustomDataProvider());
        grid.setPageSize(5);
        // Sets how many pages should be visible on the pagination before and/or after the current selected page
        //grid.setPaginatorSize(4);
        grid.addItemClickListener(new ComponentEventListener<ItemClickEvent<CandidateDTO>>() {
            @Override
            public void onComponentEvent(ItemClickEvent<CandidateDTO> candidateDTOItemClickEvent) {
                editCandidate(candidateDTOItemClickEvent.getItem());
                updateListonAdd(grid.getPage());
            }
        });
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        form = new CandidateForm();
        form.addListener(CandidateForm.SaveEvent.class, this::saveCandidate);
        form.addListener(CandidateForm.DeleteEvent.class, this::deleteCandidate);
        form.addListener(CandidateForm.CloseEvent.class, e -> closeEditor());
        VerticalLayout gridContainer = new VerticalLayout(grid);
        gridContainer.addClassName("grid-container");
        HorizontalLayout content = new HorizontalLayout(gridContainer,form);
        content.addClassName("content");
        add(getToolBar(),content);
        //updateListOnEdit(grid.getPage());
        closeEditor();
        // Sets the max number of items to be rendered on the grid for each page
    }
    private void deleteCandidate(CandidateForm.DeleteEvent evt) {
        cService.deleteCandidate(evt.getCandidate().getCandidateId());
        int page = grid.getPage();
        updateListonDelete(page);
        closeEditor();
    }
    private void saveCandidate(CandidateForm.SaveEvent evt){
        cService.addCandidate(evt.getCandidate());
        int page = grid.getPage();
        updateListonAdd(page);
        closeEditor();
    }
    private void addCandidate() {
        grid.asSingleSelect().clear();
        editCandidate(new CandidateDTO());
    }
    private void editCandidate(CandidateDTO candidate) {
        if (candidate == null) {
            closeEditor();
        } else {
            form.setCandidate(candidate);
            form.setVisible(true);
            addClassName("editing");
        }
    }
    private HorizontalLayout getToolBar() {
        Button addContactButton = new Button("Add Candidate", click -> addCandidate());
        HorizontalLayout toolbar = new HorizontalLayout(addContactButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }
    private void closeEditor() {
        form.setCandidate(null);
        form.setVisible(false);
        removeClassName("editing");
    }
    private DataProvider<CandidateDTO, Void> createCustomDataProvider() {
        DataProvider<CandidateDTO, Void> dataProvider = DataProvider.fromCallbacks(
                // First callback fetches items based on a query
                query -> {
                    // The index of the first item to load
                    int offset = query.getOffset();
                    logger.info(String.valueOf(offset));
                    // The number of items to load
                    int limit = query.getLimit();
                    logger.info(String.valueOf(limit));
                    int page = offset / limit;
                    logger.info(String.valueOf(page));
                    List<CandidateDTO> userProfileViews = new ArrayList<CandidateDTO>(cService.findCandidatesAllPaginated( page, limit ) );
                    return userProfileViews.stream();
                },
                // Second callback fetches the number of items
                // for a query

                query -> cService.countCandidates());

        return dataProvider;
    }
    public void updateListOnEdit(int page) {
        grid.setPage(page);
        grid.refreshPaginator();

    }
    private void updateListonAdd(int page) {
        grid.refreshPaginator();
        if(cService.pageIsFull(page-1,grid.getPageSize())) {
            page++;
        }
        grid.setPage(page);

    }
    private void updateListonDelete(int page) {
        grid.refreshPaginator();
        if(cService.pageIsEmpty(page-1,grid.getPageSize())) {
            page--;
        }
            grid.setPage(page);
    }

}
