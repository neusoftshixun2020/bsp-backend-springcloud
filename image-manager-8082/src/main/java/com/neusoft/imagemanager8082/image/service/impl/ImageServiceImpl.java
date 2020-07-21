package com.neusoft.imagemanager8082.image.service.impl;

import com.neusoft.imagemanager8082.image.entity.Image;
import com.neusoft.imagemanager8082.image.mapper.ImageMapper;
import com.neusoft.imagemanager8082.image.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    ImageMapper imageMapper;

    @Override
    public int insert(Image image) {
        return imageMapper.insert(image);
    }

    @Override
    public int delete(int pk) {
        return imageMapper.delete(pk);
    }

    @Override
    public  Image getById(int pk) {
        return imageMapper.getById(pk);
    }


}
