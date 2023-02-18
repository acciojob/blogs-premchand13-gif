package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    UserRepository userRepository1;
    @Autowired
    ImageRepository imageRepository;

    public Blog createAndReturnBlog(Integer userId, String title, String content) throws Exception {
        //create a blog at the current time
        User user=userRepository1.findById(userId).get();
        Blog blog=new Blog();
        blog.setUser(user);
        blog.setTitle(title);
        blog.setContent(content);
        List<Blog> blogList=user.getBlogList();
        blogList.add(blog);
        user.setBlogList(blogList);
        userRepository1.save(user);
        return blog;


    }

    public void deleteBlog(int blogId) throws Exception{
        //delete blog and corresponding images
        Blog blog=blogRepository1.findById(blogId).get();
        List<Image> imageList=blog.getImageList();
        for(Image i:imageList){
            imageRepository.deleteById(i.getId());
        }
        blogRepository1.deleteById(blogId);

    }
}
