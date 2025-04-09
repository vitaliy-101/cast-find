package com.example.castfindbackend.service;

import com.example.castfindbackend.dto.photo.PhotoCreateRequest;
import com.example.castfindbackend.dto.photo.PhotoData;
import com.example.castfindbackend.dto.photo.PhotoResponse;
import com.example.castfindbackend.entity.Photo;
import com.example.castfindbackend.exceptions.NotFoundByIdException;
import com.example.castfindbackend.mapper.PhotoMapper;
import com.example.castfindbackend.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Comparator;
import java.util.List;

import static com.example.castfindbackend.utils.FileUtils.*;
import static com.example.castfindbackend.utils.PhotoUtils.PHOTO_URL;
import static com.example.castfindbackend.utils.PhotoUtils.createPhotoData;

@Service
@RequiredArgsConstructor
public class PhotoService {
    private final PhotoRepository repository;
    private final PhotoMapper mapper;

    public void savePhotos(List<MultipartFile> filesData,
                           PhotoCreateRequest request) {
        var imageBytes = getByteFromMultipartList(filesData);
        imageBytes.forEach(imageByte -> {
            var photoOrganisation = mapper.convertFromByteToEntity(imageByte, request.getOtherId(), request.getType());
            repository.save(photoOrganisation);
        });
    }

    public void savePhoto(MultipartFile fileData,
                          PhotoCreateRequest request) {
        var imageByte = getByteFromMultipart(fileData);
        var photoOrganisation = mapper.convertFromByteToEntity(imageByte, request.getOtherId(), request.getType());
        photoOrganisation.setMain(request.isMain());
        if (request.isMain()) {
            var foundMain = repository.findPhotoByOtherIdAndTypeAndMain(request.getOtherId(), request.getType(), true);
            if (foundMain != null) {
                photoOrganisation.setId(foundMain.getId());
            }
        }
        repository.save(photoOrganisation);
    }

    public List<PhotoResponse> findPhotosByTypeAndOtherId(Long otherId, String type) {
        var photos = repository.findPhotoByOtherIdAndType(otherId, type);
        return photos.stream()
                .sorted(Comparator.comparing(Photo::isMain).reversed())
                .map(photo -> new PhotoResponse(convertToUrl(PHOTO_URL, photo.getId().toString()), photo.isMain()))
                .toList();
    }

    public PhotoResponse findAvatar(Long otherId, String type) {
        var photo = repository.findPhotoByOtherIdAndTypeAndMain(otherId, type, true);
        if (photo == null) {
            return null;
        }
        return new PhotoResponse(convertToUrl(PHOTO_URL, photo.getId().toString()), true);
    }

    public PhotoData findPhotoById(Long id) {
        var fileData = repository.findById(id).orElseThrow(() -> new NotFoundByIdException(Photo.class, id))
                .getData();
        return createPhotoData(fileData);
    }

}
