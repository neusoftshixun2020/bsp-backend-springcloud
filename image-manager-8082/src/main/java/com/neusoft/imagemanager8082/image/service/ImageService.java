package com.neusoft.imagemanager8082.image.service;


import com.neusoft.imagemanager8082.image.entity.Image;

public interface ImageService {

        int insert(Image image);

        int delete(int pk);

        Image getById(int brd_id);


}
