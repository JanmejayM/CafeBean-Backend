package com.cafe.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cafe.dao.CoffeeDao;
import com.cafe.entity.Coffee;
import com.cafe.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CoffeeServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CoffeeServiceImplTest {
    @MockBean
    private CoffeeDao coffeeDao;

    @Autowired
    private CoffeeServiceImpl coffeeServiceImpl;

    
    @Test
    void testGetCoffeByID() {
        Coffee coffee = new Coffee();
        coffee.setCoffee_id(1L);
        coffee.setCoffee_type("Coffee type");
        coffee.setPrice(1L);
        Optional<Coffee> ofResult = Optional.of(coffee);
        when(coffeeDao.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertSame(coffee, coffeeServiceImpl.getCoffeByID(1L));
        verify(coffeeDao, atLeast(1)).findById(Mockito.<Long>any());
    }

   
    @Test
    void testGetCoffeByID2() {
        when(coffeeDao.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> coffeeServiceImpl.getCoffeByID(1L));
        verify(coffeeDao).findById(Mockito.<Long>any());
    }

    
    @Test
    void testGetCoffeByID3() {
        when(coffeeDao.findById(Mockito.<Long>any())).thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> coffeeServiceImpl.getCoffeByID(1L));
        verify(coffeeDao).findById(Mockito.<Long>any());
    }

   
    @Test
    void testCreatecoffee() {
        Coffee coffee = new Coffee();
        coffee.setCoffee_id(1L);
        coffee.setCoffee_type("Coffee type");
        coffee.setPrice(1L);
        when(coffeeDao.save(Mockito.<Coffee>any())).thenReturn(coffee);

        Coffee coffee2 = new Coffee();
        coffee2.setCoffee_id(1L);
        coffee2.setCoffee_type("Coffee type");
        coffee2.setPrice(1L);
        assertSame(coffee, coffeeServiceImpl.createcoffee(coffee2));
        verify(coffeeDao).save(Mockito.<Coffee>any());
    }

    
    @Test
    void testCreatecoffee2() {
        when(coffeeDao.save(Mockito.<Coffee>any())).thenThrow(new ResourceNotFoundException("An error occurred"));

        Coffee coffee = new Coffee();
        coffee.setCoffee_id(1L);
        coffee.setCoffee_type("Coffee type");
        coffee.setPrice(1L);
        assertThrows(ResourceNotFoundException.class, () -> coffeeServiceImpl.createcoffee(coffee));
        verify(coffeeDao).save(Mockito.<Coffee>any());
    }

    
    @Test
    void testUpdateCoffee() {
        Coffee coffee = new Coffee();
        coffee.setCoffee_id(1L);
        coffee.setCoffee_type("Coffee type");
        coffee.setPrice(1L);
        Optional<Coffee> ofResult = Optional.of(coffee);

        Coffee coffee2 = new Coffee();
        coffee2.setCoffee_id(1L);
        coffee2.setCoffee_type("Coffee type");
        coffee2.setPrice(1L);
        when(coffeeDao.save(Mockito.<Coffee>any())).thenReturn(coffee2);
        when(coffeeDao.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Coffee coffee3 = new Coffee();
        coffee3.setCoffee_id(1L);
        coffee3.setCoffee_type("Coffee type");
        coffee3.setPrice(1L);
        assertSame(coffee2, coffeeServiceImpl.updateCoffee(coffee3));
        verify(coffeeDao).save(Mockito.<Coffee>any());
        verify(coffeeDao).findById(Mockito.<Long>any());
    }

    
    @Test
    void testUpdateCoffee2() {
        Coffee coffee = new Coffee();
        coffee.setCoffee_id(1L);
        coffee.setCoffee_type("Coffee type");
        coffee.setPrice(1L);
        Optional<Coffee> ofResult = Optional.of(coffee);
        when(coffeeDao.save(Mockito.<Coffee>any())).thenThrow(new ResourceNotFoundException("An error occurred"));
        when(coffeeDao.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Coffee coffee2 = new Coffee();
        coffee2.setCoffee_id(1L);
        coffee2.setCoffee_type("Coffee type");
        coffee2.setPrice(1L);
        assertThrows(ResourceNotFoundException.class, () -> coffeeServiceImpl.updateCoffee(coffee2));
        verify(coffeeDao).save(Mockito.<Coffee>any());
        verify(coffeeDao).findById(Mockito.<Long>any());
    }

    
    @Test
    void testUpdateCoffee3() {
        when(coffeeDao.findById(Mockito.<Long>any())).thenReturn(Optional.empty());

        Coffee coffee = new Coffee();
        coffee.setCoffee_id(1L);
        coffee.setCoffee_type("Coffee type");
        coffee.setPrice(1L);
        assertThrows(ResourceNotFoundException.class, () -> coffeeServiceImpl.updateCoffee(coffee));
        verify(coffeeDao).findById(Mockito.<Long>any());
    }

    
    @Test
    void testGetallCoffee() {
        ArrayList<Coffee> coffeeList = new ArrayList<>();
        when(coffeeDao.findAll()).thenReturn(coffeeList);
        List<Coffee> actualGetallCoffeeResult = coffeeServiceImpl.getallCoffee();
        assertSame(coffeeList, actualGetallCoffeeResult);
        assertTrue(actualGetallCoffeeResult.isEmpty());
        verify(coffeeDao).findAll();
    }

    
    @Test
    void testGetallCoffee2() {
        when(coffeeDao.findAll()).thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> coffeeServiceImpl.getallCoffee());
        verify(coffeeDao).findAll();
    }

    
    @Test
    void testDeleteCoffee() {
        doNothing().when(coffeeDao).deleteById(Mockito.<Long>any());
        coffeeServiceImpl.deleteCoffee(1L);
        verify(coffeeDao).deleteById(Mockito.<Long>any());
    }

    
    @Test
    void testDeleteCoffee2() {
        doThrow(new ResourceNotFoundException("An error occurred")).when(coffeeDao).deleteById(Mockito.<Long>any());
        assertThrows(ResourceNotFoundException.class, () -> coffeeServiceImpl.deleteCoffee(1L));
        verify(coffeeDao).deleteById(Mockito.<Long>any());
    }
}

