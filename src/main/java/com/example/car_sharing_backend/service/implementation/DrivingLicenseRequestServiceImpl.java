package com.example.car_sharing_backend.service.implementation;


import com.example.car_sharing_backend.mappers.DrivingLicenseRequestMapper;
import com.example.car_sharing_backend.model.dto.request.DriverLicenseRequestDto;
import com.example.car_sharing_backend.model.dto.response.DrivingLicenseResponseForAdmin;
import com.example.car_sharing_backend.model.entity.DrivingLicenseRequestEntity;
import com.example.car_sharing_backend.model.entity.User;
import com.example.car_sharing_backend.repository.DrivingLicenseRequestRepository;
import com.example.car_sharing_backend.service.DrivingLicenseRequestService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDateTime;


@Service
@AllArgsConstructor
public class DrivingLicenseRequestServiceImpl implements DrivingLicenseRequestService {

    private final CurrentUserService currentUserService;
    private DrivingLicenseRequestRepository drivingLicenseRequestRepository;
    private DrivingLicenseRequestMapper drivingLicenseRequestMapper;

    private PhotoService photoService;

    @Transactional
    @Override
    public DrivingLicenseResponseForAdmin createDrivingLicenseRequest(
            DriverLicenseRequestDto dto,
            MultipartFile drivingLicensePhoto,
            MultipartFile selfiePhoto
    ) throws IOException {

        User user = currentUserService.getCurrentUser();

        String licenseUrl = photoService.uploadPhoto(drivingLicensePhoto, "driving_license_request_photos");
        String selfieUrl = photoService.uploadPhoto(selfiePhoto, "driving_license_request_photos");

        DrivingLicenseRequestEntity entity = drivingLicenseRequestMapper.toEntity(dto);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setLicensePhotoUrl(licenseUrl);
        entity.setUserSelfieUrl(selfieUrl);
        entity.setUser(user);

        drivingLicenseRequestRepository.save(entity);

        return drivingLicenseRequestMapper.toAdminDto(entity);
    }


}
