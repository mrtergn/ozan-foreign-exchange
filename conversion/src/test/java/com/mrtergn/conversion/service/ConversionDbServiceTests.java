package com.mrtergn.conversion.service;

import com.mrtergn.conversion.model.entity.Conversion;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import(ConversionDbService.class)
public class ConversionDbServiceTests {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ConversionDbService conversionDbService;

    static String source = "TRY";
    static String target = "USD";
    static double amount = 1;
    static double conversion = 7;
    static String transactionId = UUID.randomUUID().toString();

    static Conversion defaultConversionObj;

    @BeforeAll
    public static void init() {
        Conversion conversionObj = new Conversion();
        conversionObj.setSource(source);
        conversionObj.setTarget(target);
        conversionObj.setAmount(amount);
        conversionObj.setConversion(conversion);
        conversionObj.setTransactionId(transactionId);

        defaultConversionObj = conversionObj;
    }

    @Test
    @DisplayName("Find conversion data by transactionId")
    public void findByTransactionId() {
        testEntityManager.persist(defaultConversionObj);
        testEntityManager.flush();

        List<Conversion> conversionList = conversionDbService.findByTransactionId(defaultConversionObj.getTransactionId());

        assertEquals(conversionList.size(), 1);
        assertThat(defaultConversionObj.getSource()).isEqualTo(conversionList.get(0).getSource());
        assertThat(defaultConversionObj.getTarget()).isEqualTo(conversionList.get(0).getTarget());
        assertThat(defaultConversionObj.getAmount()).isEqualTo(conversionList.get(0).getAmount());
        assertThat(defaultConversionObj.getConversion()).isEqualTo(conversionList.get(0).getConversion());
        assertThat(defaultConversionObj.getTransactionId()).isEqualTo(conversionList.get(0).getTransactionId());
    }

}
