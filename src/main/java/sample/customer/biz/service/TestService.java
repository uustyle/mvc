package sample.customer.biz.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.customer.biz.dto.TestDto;
import sample.customer.biz.entity.Test;
import sample.customer.biz.mapper.TestMapper;

@Service
public class TestService {

    @Autowired
    private TestMapper testMapper;

    public TestDto getTest(Integer id) {
        TestDto dto = new TestDto();
        Test entity = testMapper.getTest(id);
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

}
