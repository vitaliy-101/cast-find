package com.example.castfindbackend.service;

import com.example.castfindbackend.dto.photo.MainPhotoType;
import com.example.castfindbackend.dto.photo.PhotoCreateRequest;
import com.example.castfindbackend.dto.photo.PhotoData;
import com.example.castfindbackend.dto.photo.PhotoResponse;
import com.example.castfindbackend.entity.Photo;
import com.example.castfindbackend.exceptions.NotFoundByIdException;
import com.example.castfindbackend.mapper.CustomPhotoMapper;
import com.example.castfindbackend.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Comparator;
import java.util.List;

import static com.example.castfindbackend.mapper.CustomPhotoMapper.toEntity;
import static com.example.castfindbackend.utils.FileUtils.*;
import static com.example.castfindbackend.utils.PhotoUtils.PHOTO_URL;
import static com.example.castfindbackend.utils.PhotoUtils.createPhotoData;

@Service
@RequiredArgsConstructor
public class PhotoService {
    private final PhotoRepository repository;

    public void savePhotos(List<MultipartFile> filesData,
                           PhotoCreateRequest request) {
        var imageBytes = getByteFromMultipartList(filesData);
        imageBytes.forEach(imageByte -> {
            var photo = toEntity(request, imageByte);
            repository.save(photo);
        });
    }

    public void savePhoto(MultipartFile fileData,
                          PhotoCreateRequest request) {
        var imageByte = getByteFromMultipart(fileData);
        var photo = toEntity(request, imageByte);
        photo.setMain(request.isMain());
        if (request.isMain()) {
            var foundMain = repository.findPhotoByOtherIdAndTypeAndMain(request.getOtherId(), request.getType(), true);
            if (foundMain != null) {
                photo.setId(foundMain.getId());
            }
        }
        repository.save(photo);
    }

    public List<PhotoResponse> findPhotosByTypeAndOtherId(Long otherId,
                                                          String type,
                                                          MainPhotoType photoType) {
        var photos = repository.findPhotoByOtherIdAndType(otherId, type);
        return photos.stream()
                .filter(photo -> filterByPhotoType(photo.isMain(), photoType))
                .sorted(Comparator.comparing(Photo::isMain).reversed())
                .map(photo -> new PhotoResponse(convertToUrl(PHOTO_URL, photo.getId().toString()), photo.getDescription(), photo.isMain()))
                .toList();
    }

    private boolean filterByPhotoType(boolean isMain, MainPhotoType photoType) {
        if (photoType.equals(MainPhotoType.AVATAR)) {
            return isMain;
        }
        if (photoType.equals(MainPhotoType.DEFAULT)) {
            return !isMain;
        }
        return true;
    }

    public PhotoResponse findAvatar(Long otherId, String type) {
        var photo = repository.findPhotoByOtherIdAndTypeAndMain(otherId, type, true);
        if (photo == null) {
            return null;
        }
        return new PhotoResponse(convertToUrl(PHOTO_URL, photo.getId().toString()), photo.getDescription(), true);
    }

    public PhotoData findPhotoById(Long id) {
        var fileData = repository.findById(id).orElseThrow(() -> new NotFoundByIdException(Photo.class, id))
                .getData();
        return createPhotoData(fileData);
    }

    private void existsById(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Photo by id does not exist");
        }
    }

    public void delete(List<Long> ids) {
        ids.forEach(this::delete);
    }

    @Transactional
    @Modifying
    public void delete(Long id) {
        existsById(id);
        repository.deleteById(id);
    }

}
