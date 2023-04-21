package com.pichincha.springjparest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pichincha.springjparest.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

class ClientControllerTest
{
    ClientController clientController;
    ClientService clientService;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    long id = 1324L;
    
    @BeforeEach
    void setUp()
    {
        clientService = Mockito.mock(ClientService.class);
        clientController = new ClientController(clientService);
    }
    
    @Test
    void create() throws Exception
    {
    
    }
}