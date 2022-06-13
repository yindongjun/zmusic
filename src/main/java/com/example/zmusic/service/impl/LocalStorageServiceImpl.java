package com.example.zmusic.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import com.example.zmusic.dto.StorageDto;
import com.example.zmusic.enums.Storage;
import com.example.zmusic.exception.BizException;
import com.example.zmusic.exception.ExceptionType;
import com.example.zmusic.service.StorageService;
import com.example.zmusic.utils.FileTypeTransformerUtils;
import com.example.zmusic.utils.KsuidUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service("LOCAL")
public class LocalStorageServiceImpl implements StorageService {

    @Value("${app.upload.path}")
    private String uploadDir;

    private static final DateTimeFormatter YYYY_MM_DD_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    @Override
    public StorageDto createFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String suffix = FileUtil.getSuffix(fileName);
        String fileKey = KsuidUtils.generateKsuid() + "." + suffix;

        // build file dto
        StorageDto storageDto = new StorageDto();
        storageDto.setFileKey(fileKey);
        storageDto.setStorage(Storage.LOCAL);
        storageDto.setName(fileName);
        storageDto.setExt(suffix);
        storageDto.setType(FileTypeTransformerUtils.getFileTypeFromExt(suffix));
        storageDto.setSize(file.getSize());

        // create files
        try {
            FileUtil.writeBytes(file.getBytes(), this.buildFullFilePath(storageDto.getFileKey()));
        } catch (IOException e) {
            throw new BizException(ExceptionType.CREATE_FILE_FAIL);
        }


        return storageDto;
    }

    @Override
    public void deleteFile(String fileKey) {
        String deleteFileFullPath = buildFullFilePath(fileKey);
        try {
            FileUtil.del(deleteFileFullPath);
        } catch (IORuntimeException e) {
            throw new BizException(ExceptionType.DELETE_FILE_FAIL);
        }
    }

    private String buildRealUploadDir() {
        LocalDate now = LocalDate.now();
        String dateDir = now.format(YYYY_MM_DD_FORMATTER);
        String realUploadDir = uploadDir + File.separator + dateDir;
        FileUtil.mkdir(realUploadDir);
        return realUploadDir;
    }

    private String buildFullFilePath(String fileKey) {
        return buildRealUploadDir() + File.separator + fileKey;
    }

}
