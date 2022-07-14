package com.prob_jr.sikcal_app.domain.service;

import com.prob_jr.sikcal_app.domain.controller.MemberController;
import com.prob_jr.sikcal_app.domain.repository.MyPostRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl {
    private final MyPostRepository repository;
    private final Logger LOGGER = LoggerFactory.getLogger(MemberController.class);

}
