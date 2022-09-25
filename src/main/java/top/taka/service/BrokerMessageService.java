package top.taka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.taka.mapper.BrokerMessageMapper;

@Service
public class BrokerMessageService {

    //@Autowired
    private BrokerMessageMapper brokerMessageMapper;

    public void getall() {
        System.out.println(brokerMessageMapper.selectAll().size());
    }
}
