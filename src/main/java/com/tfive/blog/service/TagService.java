package com.tfive.blog.service;

import com.tfive.blog.po.Tag;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TagService {

    Tag getTag(Long id);

    Tag saveTag(Tag tag);

    List<Tag> listTags();

    List<Tag> listTags(String ids);

    Tag updateTag(Long id,Tag tag);

    void deleteTag(Long id);

}
