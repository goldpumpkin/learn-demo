package com.gold.aop_learn.service;

import com.gold.aop_learn.dto.AopReqDto;
import com.gold.aop_learn.dto.AopRespDto;

public interface BizService {

    AopRespDto doWork(AopReqDto reqD);
}
