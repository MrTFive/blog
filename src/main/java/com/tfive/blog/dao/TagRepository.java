package com.tfive.blog.dao;

import com.tfive.blog.po.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag,Long>{
}
