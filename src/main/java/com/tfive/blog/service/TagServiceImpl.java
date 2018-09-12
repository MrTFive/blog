package com.tfive.blog.service;

import com.tfive.blog.dao.TagRepository;
import com.tfive.blog.po.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public Tag getTag(Long id) {
        return tagRepository.findById(id).get();
    }

    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public List<Tag> listTags() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> listTags(String ids) {
        return tagRepository.findAllById(convertToList(ids));
    }

    private List<Long> convertToList(String ids){
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null){
            String[] idarray = ids.split(",");
            for(int i=0;i < idarray.length;i++)
                list.add(new Long(idarray[i]));
        }
        return list;
    }

    @Override
    public Tag updateTag(Long id, Tag tag) {

        Tag t = tagRepository.findById(id).get();
        if(t == null)
            throw new RuntimeException("标签不匹配");

        BeanUtils.copyProperties(t,tag);
        return tagRepository.save(t);
    }

    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }
}
