package com.example.application.dataprovider;
import com.example.application.model.dto.CandidateDTO;
import com.example.application.model.dto.JobAnnouncementDTO;
import com.example.application.model.repository.AnnouncementRepository;
import com.example.application.service.AnnouncementService;
import com.vaadin.flow.data.provider.DataProvider;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AnnouncementDataProvider implements CustomDataProvider{
    private final static Logger logger = Logger.getLogger(CustomDataProvider.class.getName());
    AnnouncementService aService;
    @Override
    public DataProvider create(Service service) {
        try {
            aService = (AnnouncementService) service;
        }catch(ClassCastException e)
        {
            Logger.getLogger("service can not be cast to Announcement Service");
        }
        DataProvider<JobAnnouncementDTO, Void> dataProvider = DataProvider.fromCallbacks(
                // First callback fetches items based on a query
                query -> {
                    // The index of the first item to load
                    int offset = query.getOffset();

                    // The number of items to load
                    int limit = query.getLimit();

                    int page = offset / limit;

                    List<JobAnnouncementDTO> userProfileViews = new ArrayList<JobAnnouncementDTO>(aService.findAnnouncementsPageable( page, limit ) );

                    return userProfileViews.stream();
                },
                // Second callback fetches the number of items
                // for a query

                query -> aService.countAnnouncements() );

        return dataProvider;
    }

}
