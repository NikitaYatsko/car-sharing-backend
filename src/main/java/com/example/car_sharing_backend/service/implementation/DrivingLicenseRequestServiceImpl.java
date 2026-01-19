package com.example.car_sharing_backend.service.implementation;


import com.example.car_sharing_backend.exception.DrivingLicenseNotFoundException;
import com.example.car_sharing_backend.mappers.DrivingLicenseRequestMapper;
import com.example.car_sharing_backend.model.dto.request.DriverLicenseRequestDto;
import com.example.car_sharing_backend.model.dto.request.UpdateDrivingLicenseRequestDto;
import com.example.car_sharing_backend.model.dto.response.DrivingLicenseResponse;
import com.example.car_sharing_backend.model.dto.response.DrivingLicenseResponseForAdmin;
import com.example.car_sharing_backend.model.entity.DrivingLicense;
import com.example.car_sharing_backend.model.entity.DrivingLicenseRequestEntity;
import com.example.car_sharing_backend.model.entity.User;
import com.example.car_sharing_backend.model.enums.DrivingLicenseRequestStatus;
import com.example.car_sharing_backend.model.enums.ErrorMessage;
import com.example.car_sharing_backend.repository.DrivingLicenseRepository;
import com.example.car_sharing_backend.repository.DrivingLicenseRequestRepository;
import com.example.car_sharing_backend.service.DrivingLicenseRequestService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.UUID;


@Service
@AllArgsConstructor
public class DrivingLicenseRequestServiceImpl implements DrivingLicenseRequestService {

    private final CurrentUserService currentUserService;
    private DrivingLicenseRequestRepository drivingLicenseRequestRepository;
    private DrivingLicenseRequestMapper drivingLicenseRequestMapper;
    private DrivingLicenseRepository drivingLicenseRepository;

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

    @Override
    public DrivingLicenseResponseForAdmin getDrivingLicenseById(UUID id) {
        DrivingLicenseRequestEntity entity = drivingLicenseRequestRepository.findById(id).orElseThrow(
                () -> new DrivingLicenseNotFoundException(ErrorMessage.DRIVING_LICENSE_NOT_FOUND.getMessage())
        );
        return drivingLicenseRequestMapper.toAdminDto(entity);

    }


    @Transactional
    @Override
    public DrivingLicenseResponse approveOrDenyRequest(UUID id, UpdateDrivingLicenseRequestDto dto) {

        DrivingLicenseRequestEntity entity = drivingLicenseRequestRepository.findById(id)
                .orElseThrow(() -> new DrivingLicenseNotFoundException(
                        ErrorMessage.DRIVING_LICENSE_NOT_FOUND.getMessage()
                ));

        if (entity.getRequestStatus() != DrivingLicenseRequestStatus.PENDING) {
            throw new IllegalStateException("Заявка уже обработана");
        }

        entity.setRequestStatus(dto.getStatus());
        drivingLicenseRequestRepository.save(entity);

        if (dto.getStatus() == DrivingLicenseRequestStatus.APPROVED) {

            DrivingLicense drivingLicense = drivingLicenseRepository
                    .findByUser(entity.getUser())
                    .orElseGet(DrivingLicense::new);

            drivingLicense.setUser(entity.getUser());
            drivingLicense.setLicenseNumber(entity.getLicenseNumber());
            drivingLicense.setIssuedBy(entity.getIssuedBy());
            drivingLicense.setIssuedDate(entity.getIssuedDate());
            drivingLicense.setExpiryDate(entity.getExpiryDate());
            drivingLicense.setCategories(new HashSet<>(entity.getCategories()));

            drivingLicenseRepository.save(drivingLicense);

            return new DrivingLicenseResponse(
                    entity.getRequestStatus(),
                    "Заявка подтверждена, данные сохранены."
            );
        }

        if (dto.getStatus() == DrivingLicenseRequestStatus.REJECTED) {
            return new DrivingLicenseResponse(
                    entity.getRequestStatus(),
                    "Заявка отклонена."
            );
        }

        throw new IllegalArgumentException("Неизвестный статус заявки: " + dto.getStatus());
    }


}
