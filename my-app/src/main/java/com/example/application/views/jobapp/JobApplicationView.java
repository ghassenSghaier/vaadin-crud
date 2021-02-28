package com.example.application.views.jobapp;


import com.example.application.model.dto.CandidateDTO;
import com.example.application.model.dto.JobAnnouncementDTO;
import com.example.application.service.AnnouncementService;
import com.example.application.views.candidate.CandidateForm;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.ItemClickEvent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.klaudeta.PaginatedGrid;
import java.util.ArrayList;
import java.util.List;

@CssImport("./views/candidate/candidate-view.css")
@Route(value = "announcement", layout = MainView.class)
@PageTitle("Job Announcements")
public class JobApplicationView extends VerticalLayout {

    private AnnouncementService aService;
    private  final JobForm form;
    PaginatedGrid<JobAnnouncementDTO> grid;
    private static final Logger log = LoggerFactory.getLogger(JobApplicationView.class);

    public JobApplicationView(AnnouncementService aService) {
        this.aService = aService;
        addClassName("candidate-view");
        grid = new PaginatedGrid<>();
        grid.addClassName("candidate-grid");
        //grid.addColumn(JobAnnouncementDTO::getAnnouncementId).setHeader("Announcement ID");
        grid.addColumn(JobAnnouncementDTO::getCompany).setHeader("Company Name").setSortable(true);
        grid.addColumn(JobAnnouncementDTO::getPosition).setHeader("Position Description").setSortable(true);
        grid.addColumn(JobAnnouncementDTO::getStartDate).setHeader("Job Start Date").setSortable(true);
        grid.addColumn(JobAnnouncementDTO::getEndDate).setHeader("Job End Date").setSortable(true);
        grid.addColumn(JobAnnouncementDTO::getDescription).setHeader("Job Description").setSortable(true);
        //grid.setPaginatorSize(5);
        grid.setDataProvider(createCustomDataProvider());
        grid.setPageSize(4);
        grid.addItemClickListener(new ComponentEventListener<ItemClickEvent<JobAnnouncementDTO>>() {
            @Override
            public void onComponentEvent(ItemClickEvent<JobAnnouncementDTO> jobAnnouncementDTOItemClickEvent) {
                editJob(jobAnnouncementDTOItemClickEvent.getItem());
                updateListonAdd(grid.getPage());
            }
        });
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        form = new JobForm();
        form.addListener(JobForm.SaveEvent.class, this::saveJob);
        form.addListener(JobForm.DeleteEvent.class, this::deleteJob);
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
    private void deleteJob(JobForm.DeleteEvent evt) {
        aService.deleteAnnouncement(evt.getJob().getAnnouncementId());
        int page = grid.getPage();
        updateListonDelete(page);
        closeEditor();
    }
    private void saveJob(JobForm.SaveEvent evt){
        aService.addAnnouncement(evt.getJob());
        int page = grid.getPage();
        updateListonAdd(page);
        closeEditor();
    }
    private void addJob() {
        grid.asSingleSelect().clear();
        editJob(new JobAnnouncementDTO());
    }
    private void editJob(JobAnnouncementDTO job) {
        if (job == null) {
            closeEditor();
        } else {
            form.setJob(job);
            form.setVisible(true);
            addClassName("editing");
        }
    }
    private HorizontalLayout getToolBar() {
        Button addContactButton = new Button("Add Announcment", click -> addJob());
        HorizontalLayout toolbar = new HorizontalLayout(addContactButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }
    private void closeEditor() {
        form.setJob(null);
        form.setVisible(false);
        removeClassName("editing");
    }
    private DataProvider<JobAnnouncementDTO, Void> createCustomDataProvider() {
        DataProvider<JobAnnouncementDTO, Void> dataProvider = DataProvider.fromCallbacks(
                // First callback fetches items based on a query
                query -> {
                    // The index of the first item to load
                    int offset = query.getOffset();
                    log.info(String.valueOf(offset));

                    // The number of items to load
                    int limit = query.getLimit();
                    log.info(String.valueOf(limit));

                    int page = offset / limit;
                    log.info(String.valueOf(page));

                    List<JobAnnouncementDTO> userProfileViews = new ArrayList<JobAnnouncementDTO>(aService.findAnnouncementsPageable( page, limit ) );

                    return userProfileViews.stream();
                },
                // Second callback fetches the number of items
                // for a query

                query -> aService.countAnnouncements());

        return dataProvider;
    }
    public void updateListOnEdit(int page) {
        grid.setPage(page);
        grid.refreshPaginator();

    }
    private void updateListonAdd(int page) {
        grid.refreshPaginator();
        if(aService.pageIsFull(page-1,grid.getPageSize())) {
            page++;
        }
        grid.setPage(page);
    }
    private void updateListonDelete(int page) {
        grid.refreshPaginator();
        if(aService.pageIsEmpty(page-1,grid.getPageSize())) {
            page--;
        }
        grid.setPage(page);
    }

}
