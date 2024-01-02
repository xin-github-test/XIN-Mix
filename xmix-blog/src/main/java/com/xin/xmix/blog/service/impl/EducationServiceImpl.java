package com.xin.xmix.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xin.xmix.blog.dao.EducationDao;
import com.xin.xmix.blog.entity.Education;
import com.xin.xmix.blog.service.EducationService;
import org.springframework.stereotype.Service;

@Service
public class EducationServiceImpl extends ServiceImpl<EducationDao,Education> implements EducationService {
}
