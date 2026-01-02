package com.example.car_sharing_backend.service.implementation;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.car_sharing_backend.mappers.DrivingLicenseRequestMapper;
import com.example.car_sharing_backend.model.dto.request.DriverLicenseRequestDto;
import com.example.car_sharing_backend.model.dto.response.DrivingLicenseResponseForAdmin;
import com.example.car_sharing_backend.model.entity.DrivingLicenseRequestEntity;
import com.example.car_sharing_backend.model.entity.User;
import com.example.car_sharing_backend.repository.DrivingLicenseRequestRepository;
import com.example.car_sharing_backend.repository.UserRepository;
import com.example.car_sharing_backend.service.DrivingLicenseRequestService;
import lombok.AllArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

@Service
@AllArgsConstructor
public class DrivingLicenseRequestServiceImpl implements DrivingLicenseRequestService {

    private final UserRepository userRepository;
    private DrivingLicenseRequestRepository drivingLicenseRequestRepository;
    private DrivingLicenseRequestMapper drivingLicenseRequestMapper;
    private Cloudinary cloudinary;

    @Override
    public DrivingLicenseResponseForAdmin createDrivingLicenseRequest(
            DriverLicenseRequestDto drivingLicenseRequest,
            MultipartFile drivingLicensePhoto,
            MultipartFile selfiePhoto
    ) throws IOException {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = currentUser.getName();

        User user = userRepository.findByUsername(currentUserName).orElseThrow(
                () -> new UsernameNotFoundException("Username not found")
        );

        Map<String, Object> uploadLicensePhoto;
        Map<String, Object> uploadSelfiePhoto;

        try {
            uploadLicensePhoto = cloudinary.uploader().upload(
                    drivingLicensePhoto.getBytes(),
                    ObjectUtils.asMap("folder", "driving_license_request_photos")
            );
            uploadSelfiePhoto = cloudinary.uploader().upload(
                    selfiePhoto.getBytes(),
                    ObjectUtils.asMap("folder", "driving_license_request_photos")
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        DrivingLicenseRequestEntity entity = drivingLicenseRequestMapper.toEntity(drivingLicenseRequest);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setLicensePhotoUrl((String) uploadLicensePhoto.get("secure_url"));
        entity.setUserSelfieUrl((String) uploadSelfiePhoto.get("secure_url"));


        entity.setUser(user);
        drivingLicenseRequestRepository.save(entity);
        DrivingLicenseResponseForAdmin response = drivingLicenseRequestMapper.toAdminDto(entity);
        return response;

    }

}
