package com.west.bank.controller;

import com.west.bank.entity.CreditCard;
import com.west.bank.service.CreditCardService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MainControllerTest {


    @Mock
    private CreditCardService creditCardService;



    @InjectMocks
    private MainController mainController;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();
    }

    @Test
    public void testGetWelcomePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("welcome"));
    }


    private int page = 0;

    @Test
    public void testGetAllCards() throws Exception {
        final CreditCard first = new CreditCard();
        first.setId(1L);
        first.setNumber("3432343234");
        first.setValue(2000.43f);
        first.setPassword("1234");

        final CreditCard second = new CreditCard();
        first.setId(2L);
        second.setNumber("4444");
        second.setValue(20300.43f);
        second.setPassword("4343");

        when(creditCardService.getAll()).thenReturn(Arrays.asList(first, second));

        mockMvc.perform(get("/getAllCards?page=" + page))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }
}
