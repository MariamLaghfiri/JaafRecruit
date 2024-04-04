package org.example.jobposting.service.serviceImpl;

import org.example.jobposting.dto.JobPostingDTO;
import org.example.jobposting.entity.JobPosting;
import org.example.jobposting.entity.Enum.Contract;
import org.example.jobposting.entity.Enum.JobType;
import org.example.jobposting.repository.JobPostingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JobPostingServiceImplTest {

    @Mock
    private JobPostingRepository jobPostingRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private JobPostingServiceImpl jobPostingService;

    private JobPosting jobPosting;
    private JobPostingDTO jobPostingDTO;

    @BeforeEach
    void setUp() {
        jobPosting = new JobPosting();
        jobPosting.setId(1L);
        jobPosting.setContract(Contract.CDD);

        jobPostingDTO = new JobPostingDTO();
        jobPostingDTO.setId(1L);
        jobPostingDTO.setContract(Contract.CDD);
    }

    @Test
    void testShowAllJobPosting() {
        when(jobPostingRepository.findAll()).thenReturn(Arrays.asList(jobPosting));
        when(modelMapper.map(any(), any())).thenReturn(jobPostingDTO);

        List<JobPostingDTO> result = jobPostingService.showAllJobPosting();

        assertEquals(1, result.size());
        assertEquals(jobPostingDTO, result.get(0));
    }
    @Test
    void testFindJobPostingByContract() {
        when(jobPostingRepository.findByContractAndCloseFalse(Contract.CDD))
                .thenReturn(Arrays.asList(jobPosting));
        when(modelMapper.map(any(), any())).thenReturn(jobPostingDTO);

        List<JobPostingDTO> result = jobPostingService.findJobPostingByContract(Contract.CDD);

        assertEquals(1, result.size());
        assertEquals(jobPostingDTO, result.get(0));
    }

    @Test
    void testShowAllAvailableJobPosting() {
        when(jobPostingRepository.findByCloseFalse()).thenReturn(Arrays.asList(jobPosting));
        when(modelMapper.map(any(), any())).thenReturn(jobPostingDTO);

        List<JobPostingDTO> result = jobPostingService.showAllAvailableJobPosting();

        assertEquals(1, result.size());
        assertEquals(jobPostingDTO, result.get(0));
    }

    @Test
    void testShowAllJobPostingByCompanyId() {
        when(jobPostingRepository.findByCompanyIdAndCloseFalse(1L)).thenReturn(Arrays.asList(jobPosting));
        when(modelMapper.map(any(), any())).thenReturn(jobPostingDTO);

        List<JobPostingDTO> result = jobPostingService.showAllJobPostingByCompanyId(1L);

        assertEquals(1, result.size());
        assertEquals(jobPostingDTO, result.get(0));
    }

    @Test
    void testFindJobPostingByJobType() {
        when(jobPostingRepository.findByJobTypeAndCloseFalse(JobType.FULL_TIME)).thenReturn(Arrays.asList(jobPosting));
        when(modelMapper.map(any(), any())).thenReturn(jobPostingDTO);

        List<JobPostingDTO> result = jobPostingService.findJobPostingByJobType(JobType.FULL_TIME);

        assertEquals(1, result.size());
        assertEquals(jobPostingDTO, result.get(0));
    }

    @Test
    void testGetJobPostingById() {
        when(jobPostingRepository.findByIdAndCloseFalse(1L)).thenReturn(Optional.of(jobPosting));
        when(modelMapper.map(any(), any())).thenReturn(jobPostingDTO);

        JobPostingDTO result = jobPostingService.getJobPostingById(1L);

        assertEquals(jobPostingDTO, result);
    }

    @Test
    void testAddJobPosting() {
        when(jobPostingRepository.save(any())).thenReturn(jobPosting);
        when(modelMapper.map(any(), eq(JobPosting.class))).thenReturn(jobPosting);
        when(modelMapper.map(any(), eq(JobPostingDTO.class))).thenReturn(jobPostingDTO);

        JobPostingDTO result = jobPostingService.addJobPosting(jobPostingDTO);

        assertEquals(jobPostingDTO, result);
    }

    @Test
    void testUpdateJobPosting() {
        when(jobPostingRepository.save(any())).thenAnswer(invocation -> {
            JobPosting savedJobPosting = invocation.getArgument(0);
            savedJobPosting.setId(1L);
            return savedJobPosting;
        });

        when(modelMapper.map(any(), eq(JobPosting.class))).thenReturn(jobPosting);
        when(modelMapper.map(any(), eq(JobPostingDTO.class))).thenReturn(jobPostingDTO);

        JobPostingDTO result = jobPostingService.updateJobPosting(1L, jobPostingDTO);

        assertEquals(jobPostingDTO, result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testCloseJobPosting() {
        when(jobPostingRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        when(modelMapper.map(any(), eq(JobPosting.class))).thenReturn(jobPosting);
        when(modelMapper.map(any(), eq(JobPostingDTO.class))).thenReturn(jobPostingDTO);

        Boolean result = jobPostingService.closeJobPosting(jobPostingDTO);

        assertTrue(result);
        assertTrue(jobPostingDTO.getClose());
    }

    @Test
    void testCloseJobPostingsWithPastClosingDates() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        JobPosting closedJobPosting = new JobPosting();
        closedJobPosting.setClosingDate(yesterday);

        when(jobPostingRepository.findAllByClosingDateBeforeAndCloseFalse(any())).thenReturn(Arrays.asList(closedJobPosting));

        jobPostingService.closeJobPostingsWithPastClosingDates();

        assertEquals(true, closedJobPosting.getClose());
        verify(jobPostingRepository, times(1)).save(any());
    }
}