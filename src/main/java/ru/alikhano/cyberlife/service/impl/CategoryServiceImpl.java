package ru.alikhano.cyberlife.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.alikhano.cyberlife.dto.CategoryDTO;
import ru.alikhano.cyberlife.dao.CategoryDao;
import ru.alikhano.cyberlife.mapper.CategoryMapper;
import ru.alikhano.cyberlife.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger LOGGER = LogManager.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * {@inheritDoc}
     */
    @Transactional
    public List<CategoryDTO> getAll() {
        return categoryDao.getAll().stream().
                filter(Objects::nonNull).
                map(category -> categoryMapper.forward(category))
                .collect(
                        Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public CategoryDTO getById(int id) {
        return categoryMapper.forward(categoryDao.getById(id));
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public CategoryDTO getByType(String catType) {
        if (StringUtils.isNullOrEmpty(catType)) {
            LOGGER.info("Empty or null category type");
            return null;
        }
        return categoryMapper.forward(categoryDao.getByType(catType));
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public void create(CategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            LOGGER.info("Category is null");
            return;
        }
        categoryDao.create(categoryMapper.backward(categoryDTO));
    }

}
