package com.gold.aop_learn.service.impl;

import com.gold.aop_learn.dto.AopReqDto;
import com.gold.aop_learn.dto.AopRespDto;
import com.gold.aop_learn.service.BizService;
import org.springframework.stereotype.Service;

@Service
public class BizServiceImpl implements BizService {

    @Override
    public AopRespDto doWork(AopReqDto reqD) {
        System.out.println("BizService doWork Start ...");
        AopRespDto resp = new AopRespDto();
        resp.setId(1);
        resp.setName("aop tester");
        System.out.println("BizService doWork finish method but not return");
        return resp;
    }
}
