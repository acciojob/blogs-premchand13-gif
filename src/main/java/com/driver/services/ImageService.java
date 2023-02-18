package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Blog blog=blogRepository2.findById(blogId).get();
        Image image=new Image();
        image.setBlog(blog);
        image.setDimensions(dimensions);
        image.setDescription(description);
        List<Image> imageList=blog.getImageList();
        imageList.add(image);
        blog.setImageList(imageList);
        blogRepository2.save(blog);
        return image;

    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        String[] sd=screenDimensions.split("X");
        int N1=Integer.parseInt(sd[0]);
        int N2=Integer.parseInt(sd[1]);

        String[] imgD=imageRepository2.findById(id).get().getDimensions().split("X");
        int n1=Integer.parseInt(imgD[0]);
        int n2=Integer.parseInt(imgD[1]);

        return (N1/n1)*(N2/n2);
    }
}
