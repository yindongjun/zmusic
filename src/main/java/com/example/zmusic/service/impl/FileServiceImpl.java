package com.example.zmusic.service.impl;

import com.example.zmusic.dto.FileDto;
import com.example.zmusic.dto.StorageDto;
import com.example.zmusic.entity.File;
import com.example.zmusic.enums.FileStatus;
import com.example.zmusic.enums.Storage;
import com.example.zmusic.exception.BizException;
import com.example.zmusic.exception.ExceptionType;
import com.example.zmusic.mapper.FileMapper;
import com.example.zmusic.mapper.MapperInterface;
import com.example.zmusic.repository.FileRepository;
import com.example.zmusic.service.FileService;
import com.example.zmusic.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class FileServiceImpl extends SimpleGeneralServiceImpl<File, FileDto> implements FileService {

    private final Map<String, StorageService> storageServiceMap;

    private final FileRepository fileRepository;

    private final FileMapper fileMapper;

    @Override
    public Page<FileDto> search(Pageable pageable) {
        return fileRepository.findAll(pageable)
                .map(fileMapper::toDto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public FileDto create(MultipartFile file) {
        StorageDto storageDto = getDefaultStorageService().createFile(file);
        File fileToSave = fileMapper.toEntity(storageDto);
        fileToSave.setStatus(FileStatus.UPLOADED);

        File savedFile = fileRepository.save(fileToSave);
        return fileMapper.toDto(savedFile);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public FileDto update(String id, MultipartFile newFile) {
        File oldFile = getEntity(id);

        // 删除存储的旧文件
        getDefaultStorageService().deleteFile(oldFile.getFileKey());

        // 存储新文件
        StorageDto newStorageDto = getDefaultStorageService().createFile(newFile);

        // 更新文件记录
        fileMapper.updateEntity(newStorageDto, oldFile);
        File updatedFile = fileRepository.save(oldFile);

        return fileMapper.toDto(updatedFile);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        File fileToDelete = this.getEntity(id);

        // 删除数据库文件记录
        fileRepository.deleteById(id);

        // 删除存储的文件
        this.getDefaultStorageService().deleteFile(fileToDelete.getFileKey());
    }

    @Override
    public FileDto getByFileKey(String key) {
        File file = fileRepository.findFirstByFileKey(key).orElseThrow(() -> new BizException(ExceptionType.FILE_NOT_FOUND));
        return fileMapper.toDto(file);
    }

    private StorageService getDefaultStorageService() {
        return this.storageServiceMap.get(getDefaultStorage().name());
    }

    private Storage getDefaultStorage() {
        return Storage.LOCAL;
    }

    @Override
    public MapperInterface<File, FileDto> getMapstructMapper() {
        return this.fileMapper;
    }

    @Override
    public JpaRepository<File, String> getRepository() {
        return this.fileRepository;
    }

    @Override
    public BizException getNotFoundException() {
        return new BizException(ExceptionType.FILE_NOT_FOUND);
    }
}
