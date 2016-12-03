package com.west.bank.controller;

import com.west.bank.entity.CreditCard;
import com.west.bank.service.CreditCardService;
import com.west.bank.service.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TransferControllerTest {


    @Mock
    private CreditCardService creditCardService;


    @Mock
    TransactionService transactionService;



    @InjectMocks
    private TransferBetweenMyCardsController transferController;

    private MockMvc mockMvc;

    @Before
    public void setup(){

        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(transferController).build();
    }


    /**
     * to execute this test you need to change model
     * attribute in transferMoney method in TransferController
     * from json to creditCardService.getAll();
     *
     * @throws Exception
     */
    @Test
    public void testTransferMoney() throws Exception {
        final CreditCard first = new CreditCard();
        first.setId(1L);
        first.setNumber(1234567891234567L);
        first.setPincode("1234");

        final CreditCard second = new CreditCard();
        second.setId(2L);
        second.setNumber(1234567891234569L);
        second.setPincode("4343");

        when(creditCardService.getCreditCardByOffset(0,5,1)).thenReturn(Arrays.asList(first, second));

        mockMvc.perform(get("/transferMoney"))
                .andExpect(status().isOk())
                .andExpect(view().name("transfer"))
                .andExpect(model().attribute("cards", hasSize(2)))
                .andExpect(model().attribute("cards", hasItem(
                        allOf(
                                hasProperty("id", is(1L)),
                                hasProperty("number", is(1234567891234567L)),
                                hasProperty("password", is("1234"))
                        )
                )))
                .andExpect(model().attribute("cards", hasItem(
                        allOf(
                                hasProperty("id", is(2L)),
                                hasProperty("number", is(1234567891234569L)),
                                hasProperty("password", is("4343"))
                        )
                )))
                ;
    }
}
