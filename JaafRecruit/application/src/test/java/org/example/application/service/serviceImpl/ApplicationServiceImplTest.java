package org.example.application.service.serviceImpl;

import org.example.application.dto.ApplicationDTO;
import org.example.application.entity.Application;
import org.example.application.entity.Enum.AppStatus;
import org.example.application.repository.ApplicationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceImplTest {

    @Mock
    private ApplicationRepository applicationRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ApplicationServiceImpl applicationService;

//    @BeforeEach
//    public void setUp() {
//    }

    @Test
    void testShowAllApplicationByUserId() {
        // Given
        Long userId = 123L;
        List<Application> applications = Arrays.asList(new Application(), new Application());
        when(applicationRepository.findApplicationByJobSeekerId(userId)).thenReturn(applications);

        // When
        List<ApplicationDTO> result = applicationService.showAllApplicationByUserId(userId);

        // Then
        assertEquals(2, result.size());
    }

    @Test
    void testShowAllApplicationByPostId() {
        // Given
        Long postId = 456L;
        List<Application> applications = Arrays.asList(new Application(), new Application());
        when(applicationRepository.findApplicationByJobPostingId(postId)).thenReturn(applications);

        // When
        List<ApplicationDTO> result = applicationService.showAllApplicationByPostId(postId);

        // Then
        assertEquals(2, result.size());
    }

    @Test
    void testGetApplicationById() {
        // Given
        Long applicationId = 123L;
        Application application = new Application();
        application.setId(applicationId);
        ApplicationDTO applicationDTO = new ApplicationDTO();
        applicationDTO.setId(applicationId);
        when(applicationRepository.findById(applicationId)).thenReturn(Optional.of(application));
        when(modelMapper.map(application, ApplicationDTO.class)).thenReturn(applicationDTO);

        // When
        ApplicationDTO result = applicationService.getApplicationById(applicationId);

        // Then
        assertEquals(applicationId, result.getId());
    }

    @Test
    void testAddApplication() {
        // Given
        ApplicationDTO applicationDTO = new ApplicationDTO();
        Application application = new Application();
        when(modelMapper.map(applicationDTO, Application.class)).thenReturn(application);
        when(applicationRepository.save(application)).thenReturn(application);
        when(modelMapper.map(application, ApplicationDTO.class)).thenReturn(applicationDTO);

        // When
        ApplicationDTO result = applicationService.addApplication(applicationDTO);

        // Then
        assertEquals(applicationDTO, result);
    }

    @Test
    void testAcceptApplication() {
        // Given
        ApplicationDTO applicationDTO = new ApplicationDTO();
        applicationDTO.setApplicationStatus(AppStatus.PENDING); // Setting status as PENDING initially
        Application application = new Application();
        application.setApplicationStatus(AppStatus.ACCEPTED); // Expected status after acceptance
        when(modelMapper.map(applicationDTO, Application.class)).thenReturn(application);
        when(applicationRepository.save(application)).thenReturn(application);
        when(modelMapper.map(application, ApplicationDTO.class)).thenReturn(applicationDTO);

        // When
        AppStatus result = applicationService.acceptApplication(applicationDTO);

        // Then
        assertEquals(AppStatus.ACCEPTED, result);
    }

    @Test
    void testRejectApplication() {
        // Given
        ApplicationDTO applicationDTO = new ApplicationDTO();
        applicationDTO.setApplicationStatus(AppStatus.PENDING); // Setting status as PENDING initially
        Application application = new Application();
        application.setApplicationStatus(AppStatus.REJECTED); // Expected status after rejection
        when(modelMapper.map(applicationDTO, Application.class)).thenReturn(application);
        when(applicationRepository.save(application)).thenReturn(application);
        when(modelMapper.map(application, ApplicationDTO.class)).thenReturn(applicationDTO);

        // When
        AppStatus result = applicationService.rejectApplication(applicationDTO);

        // Then
        assertEquals(AppStatus.REJECTED, result);
    }

}
