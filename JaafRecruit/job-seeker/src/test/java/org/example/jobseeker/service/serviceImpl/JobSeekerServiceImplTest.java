package org.example.jobseeker.service.serviceImpl;

import org.example.jobseeker.dto.JobSeekerDTO;
import org.example.jobseeker.entity.Enum.Availability;
import org.example.jobseeker.entity.JobSeeker;
import org.example.jobseeker.repository.JobSeekerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class JobSeekerServiceImplTest {
    @Mock
    private JobSeekerRepository jobSeekerRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private JobSeekerServiceImpl jobSeekerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @DisplayName("Test Show All JobSeeker :")
    @Test
    public void testShowAllJobSeeker() {
        List<JobSeeker> jobSeekerList = new ArrayList<>();
        jobSeekerList.add(new JobSeeker());
        jobSeekerList.add(new JobSeeker());

        when(jobSeekerRepository.findJobSeekerByDeletedFalse()).thenReturn(jobSeekerList);

        List<JobSeekerDTO> jobSeekerDTOList = new ArrayList<>();
        jobSeekerDTOList.add(new JobSeekerDTO());
        jobSeekerDTOList.add(new JobSeekerDTO());

        when(modelMapper.map(any(), eq(JobSeekerDTO.class))).thenReturn(new JobSeekerDTO());

        List<JobSeekerDTO> result = jobSeekerService.showAllJobSeeker();

        assertEquals(2, result.size());
        verify(jobSeekerRepository, times(1)).findJobSeekerByDeletedFalse();
    }
    @DisplayName("Test Show All JobSeeker Available:")
    @Test
    public void testFindJobSeekerByAvailability() {
        List<JobSeeker> jobSeekerList = new ArrayList<>();
        jobSeekerList.add(new JobSeeker());
        jobSeekerList.add(new JobSeeker());

        when(jobSeekerRepository.findByAvailabilityAndDeletedFalse(any())).thenReturn(jobSeekerList);

        when(modelMapper.map(any(), eq(JobSeekerDTO.class))).thenReturn(new JobSeekerDTO());

        List<JobSeekerDTO> result = jobSeekerService.findJobSeekerByAvailability(Availability.FULL_TIME);

        assertEquals(2, result.size());
        verify(jobSeekerRepository, times(1)).findByAvailabilityAndDeletedFalse(any());
    }

    @DisplayName("Test Add JobSeeker :")
    @Test
    public void testAddJobSeeker() {
        JobSeekerDTO jobSeekerDTO = new JobSeekerDTO();
        JobSeeker jobSeeker = new JobSeeker();

        when(modelMapper.map(any(), eq(JobSeeker.class))).thenReturn(jobSeeker);
        when(jobSeekerRepository.save(any())).thenReturn(jobSeeker);
        when(modelMapper.map(any(), eq(JobSeekerDTO.class))).thenReturn(jobSeekerDTO);

        JobSeekerDTO result = jobSeekerService.addJobSeeker(jobSeekerDTO);

        assertNotNull(result);
        verify(jobSeekerRepository, times(1)).save(any());
    }

    @DisplayName("Test Update JobSeeker :")
    @Test
    public void testUpdateJobSeeker() {
        JobSeekerDTO jobSeekerDTO = new JobSeekerDTO();
        jobSeekerDTO.setId(1L);
        JobSeeker jobSeeker = new JobSeeker();

        when(modelMapper.map(any(), eq(JobSeeker.class))).thenReturn(jobSeeker);
        when(jobSeekerRepository.save(any())).thenReturn(jobSeeker);
        when(modelMapper.map(any(), eq(JobSeekerDTO.class))).thenReturn(jobSeekerDTO);

        JobSeekerDTO result = jobSeekerService.updateJobSeeker(1L, jobSeekerDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(jobSeekerRepository, times(1)).save(any());
    }

    @DisplayName("Test Delete JobSeeker :")
    @Test
    public void testDeleteJobSeeker() {
        JobSeekerDTO jobSeekerDTO = new JobSeekerDTO();
        jobSeekerDTO.setId(1L);
        jobSeekerDTO.setDeleted(false);
        JobSeeker jobSeeker = new JobSeeker();

        when(modelMapper.map(any(), eq(JobSeeker.class))).thenReturn(jobSeeker);
        when(jobSeekerRepository.save(any())).thenReturn(jobSeeker);
        when(modelMapper.map(any(), eq(JobSeekerDTO.class))).thenReturn(jobSeekerDTO);

        Boolean result = jobSeekerService.deleteJobSeeker(jobSeekerDTO);

        assertTrue(result);
        verify(jobSeekerRepository, times(1)).save(any());
    }

}