package com.mrtergn.conversion.restcontroller;

import com.mrtergn.conversion.service.ConversionDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ConversionListControllerTests {

    @MockBean
    private ConversionDbService conversionDbService;

    @Autowired
    private MockMvc mockMvc;

    public void a(){
        /*
            private String transactionId;
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            private Date transactionDate;
            private int pageNo;
            private int pageSize;
         */
    }

}
