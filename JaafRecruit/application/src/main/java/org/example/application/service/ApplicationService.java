package org.example.application.service;

import org.example.application.dto.ApplicationDTO;
import org.example.application.entity.Enum.AppStatus;

import java.util.List;

public interface ApplicationService {

    List<ApplicationDTO> showAllApplicationByUserId(Long userId);

    List<ApplicationDTO> showAllApplicationByPostId(Long postId);

    List<ApplicationDTO> findApplicationByAccepted(Long userId);
    ApplicationDTO getApplicationById(Long id);
    ApplicationDTO addApplication(ApplicationDTO applicationDTO);
    AppStatus acceptApplication(ApplicationDTO applicationDTO);
    AppStatus rejectApplication(ApplicationDTO applicationDTO);
}
