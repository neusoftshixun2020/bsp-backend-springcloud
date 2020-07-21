package com.neusoft.imagemanager8082.image.mapper;

import com.neusoft.imagemanager8082.image.entity.Image;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageMapper {
    int insert(Image image);
    int delete(int pk);
    Image getById(int pk);
}
