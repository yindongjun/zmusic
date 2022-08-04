package com.example.zmusic.service;

import com.example.zmusic.dto.FileDto;
import com.example.zmusic.dto.StorageDto;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    StorageDto createFile(MultipartFile file);

    /**
     * 删除存储的文件
     *
     * @param fileKey 文件的 key, 例如 2ANNNmaUupVYKiLa2LACMsXVxtG.txt
     */
    void deleteFile(String fileKey);

    /**
     * 获取文件访问地址
     *
     * @param fileDto 数据库文件信息
     * @return 访问全路径
     */
    String getFullUrl(FileDto fileDto);
}
