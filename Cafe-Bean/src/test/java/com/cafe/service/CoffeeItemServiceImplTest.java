package com.cafe.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cafe.dao.CoffeeItemDao;
import com.cafe.dao.CustomerDao;
import com.cafe.entity.Coffee;
import com.cafe.entity.CoffeeItem;
import com.cafe.entity.Customer;
import com.cafe.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CoffeeItemServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CoffeeItemServiceImplTest {
    @MockBean
    private CoffeeItemDao coffeeItemDao;

    @Autowired
    private CoffeeItemServiceImpl coffeeItemServiceImpl;

    @MockBean
    private CustomerDao customerDao;

    
    @Test
    void testAddCoffee() {
        Coffee coffee = new Coffee();
        coffee.setCoffee_id(1L);
        coffee.setCoffee_type("Coffee type");
        coffee.setPrice(1L);

        CoffeeItem coffeeItem = new CoffeeItem();
        coffeeItem.setCoffee(coffee);
        coffeeItem.setCoffeeItemId(1L);
        coffeeItem.setCust_id(1L);
        coffeeItem.setQuantity(1);

        Coffee coffee2 = new Coffee();
        coffee2.setCoffee_id(1L);
        coffee2.setCoffee_type("Coffee type");
        coffee2.setPrice(1L);

        CoffeeItem coffeeItem2 = new CoffeeItem();
        coffeeItem2.setCoffee(coffee2);
        coffeeItem2.setCoffeeItemId(1L);
        coffeeItem2.setCust_id(1L);
        coffeeItem2.setQuantity(1);
        when(coffeeItemDao.findBycustidAndCoffee(anyLong(), Mockito.<Coffee>any())).thenReturn(coffeeItem);
        when(coffeeItemDao.save(Mockito.<CoffeeItem>any())).thenReturn(coffeeItem2);

        Customer customer = new Customer();
        customer.setCust_id(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setPassword("iloveyou");
        customer.setPhone("6625550144");
        customer.setUsername("janedoe");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerDao.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Coffee coffee3 = new Coffee();
        coffee3.setCoffee_id(1L);
        coffee3.setCoffee_type("Coffee type");
        coffee3.setPrice(1L);
        coffeeItemServiceImpl.addCoffee(1L, coffee3);
        verify(coffeeItemDao, atLeast(1)).findBycustidAndCoffee(anyLong(), Mockito.<Coffee>any());
        verify(coffeeItemDao).save(Mockito.<CoffeeItem>any());
        verify(customerDao).findById(Mockito.<Long>any());
    }

    
    @Test
    void testAddCoffee2() {
        when(coffeeItemDao.findBycustidAndCoffee(anyLong(), Mockito.<Coffee>any()))
                .thenThrow(new ResourceNotFoundException("An error occurred"));

        Customer customer = new Customer();
        customer.setCust_id(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setPassword("iloveyou");
        customer.setPhone("6625550144");
        customer.setUsername("janedoe");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerDao.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Coffee coffee = new Coffee();
        coffee.setCoffee_id(1L);
        coffee.setCoffee_type("Coffee type");
        coffee.setPrice(1L);
        assertThrows(ResourceNotFoundException.class, () -> coffeeItemServiceImpl.addCoffee(1L, coffee));
        verify(coffeeItemDao).findBycustidAndCoffee(anyLong(), Mockito.<Coffee>any());
        verify(customerDao).findById(Mockito.<Long>any());
    }

    
    @Test
    @Disabled("TODO: Complete this test")
    void testAddCoffee3() {
        
        Coffee coffee = new Coffee();
        coffee.setCoffee_id(1L);
        coffee.setCoffee_type("Coffee type");
        coffee.setPrice(1L);

        CoffeeItem coffeeItem = new CoffeeItem();
        coffeeItem.setCoffee(coffee);
        coffeeItem.setCoffeeItemId(1L);
        coffeeItem.setCust_id(1L);
        coffeeItem.setQuantity(null);

        Coffee coffee2 = new Coffee();
        coffee2.setCoffee_id(1L);
        coffee2.setCoffee_type("Coffee type");
        coffee2.setPrice(1L);

        CoffeeItem coffeeItem2 = new CoffeeItem();
        coffeeItem2.setCoffee(coffee2);
        coffeeItem2.setCoffeeItemId(1L);
        coffeeItem2.setCust_id(1L);
        coffeeItem2.setQuantity(1);
        when(coffeeItemDao.findBycustidAndCoffee(anyLong(), Mockito.<Coffee>any())).thenReturn(coffeeItem);
        when(coffeeItemDao.save(Mockito.<CoffeeItem>any())).thenReturn(coffeeItem2);

        Customer customer = new Customer();
        customer.setCust_id(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setPassword("iloveyou");
        customer.setPhone("6625550144");
        customer.setUsername("janedoe");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerDao.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Coffee coffee3 = new Coffee();
        coffee3.setCoffee_id(1L);
        coffee3.setCoffee_type("Coffee type");
        coffee3.setPrice(1L);
        coffeeItemServiceImpl.addCoffee(1L, coffee3);
    }

    
    @Test
    @Disabled("TODO: Complete this test")
    void testAddCoffee4() {
        

        Coffee coffee = new Coffee();
        coffee.setCoffee_id(1L);
        coffee.setCoffee_type("Coffee type");
        coffee.setPrice(1L);

        CoffeeItem coffeeItem = new CoffeeItem();
        coffeeItem.setCoffee(coffee);
        coffeeItem.setCoffeeItemId(1L);
        coffeeItem.setCust_id(1L);
        coffeeItem.setQuantity(1);

        Coffee coffee2 = new Coffee();
        coffee2.setCoffee_id(1L);
        coffee2.setCoffee_type("Coffee type");
        coffee2.setPrice(1L);

        CoffeeItem coffeeItem2 = new CoffeeItem();
        coffeeItem2.setCoffee(coffee2);
        coffeeItem2.setCoffeeItemId(1L);
        coffeeItem2.setCust_id(1L);
        coffeeItem2.setQuantity(1);
        when(coffeeItemDao.findBycustidAndCoffee(anyLong(), Mockito.<Coffee>any())).thenReturn(coffeeItem);
        when(coffeeItemDao.save(Mockito.<CoffeeItem>any())).thenReturn(coffeeItem2);
        when(customerDao.findById(Mockito.<Long>any())).thenReturn(null);

        Coffee coffee3 = new Coffee();
        coffee3.setCoffee_id(1L);
        coffee3.setCoffee_type("Coffee type");
        coffee3.setPrice(1L);
        coffeeItemServiceImpl.addCoffee(1L, coffee3);
    }

   
    @Test
    void testAddCoffee5() {
        when(customerDao.findById(Mockito.<Long>any())).thenReturn(Optional.empty());

        Coffee coffee = new Coffee();
        coffee.setCoffee_id(1L);
        coffee.setCoffee_type("Coffee type");
        coffee.setPrice(1L);
        assertThrows(ResourceNotFoundException.class, () -> coffeeItemServiceImpl.addCoffee(1L, coffee));
        verify(customerDao).findById(Mockito.<Long>any());
    }

   
  

   
    @Test
    void testGetByUser() {
        ArrayList<CoffeeItem> coffeeItemList = new ArrayList<>();
        when(coffeeItemDao.findBycustid(Mockito.<Long>any())).thenReturn(coffeeItemList);

        Customer customer = new Customer();
        customer.setCust_id(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setPassword("iloveyou");
        customer.setPhone("6625550144");
        customer.setUsername("janedoe");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerDao.findById(Mockito.<Long>any())).thenReturn(ofResult);
        List<CoffeeItem> actualByUser = coffeeItemServiceImpl.getByUser(1L);
        assertSame(coffeeItemList, actualByUser);
        assertTrue(actualByUser.isEmpty());
        verify(coffeeItemDao).findBycustid(Mockito.<Long>any());
        verify(customerDao).findById(Mockito.<Long>any());
    }

   
    @Test
    void testGetByUser2() {
        when(coffeeItemDao.findBycustid(Mockito.<Long>any()))
                .thenThrow(new ResourceNotFoundException("An error occurred"));

        Customer customer = new Customer();
        customer.setCust_id(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setPassword("iloveyou");
        customer.setPhone("6625550144");
        customer.setUsername("janedoe");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerDao.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class, () -> coffeeItemServiceImpl.getByUser(1L));
        verify(coffeeItemDao).findBycustid(Mockito.<Long>any());
        verify(customerDao).findById(Mockito.<Long>any());
    }

    
    @Test
    void testGetByUser3() {
        when(customerDao.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> coffeeItemServiceImpl.getByUser(1L));
        verify(customerDao).findById(Mockito.<Long>any());
    }

    
    @Test
    void testAmountForUserBooking() {
        when(coffeeItemDao.findBycustid(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        assertEquals(0L, coffeeItemServiceImpl.amountForUserBooking(1L).longValue());
        verify(coffeeItemDao).findBycustid(Mockito.<Long>any());
    }

   
    @Test
    void testAmountForUserBooking2() {
        Coffee coffee = new Coffee();
        coffee.setCoffee_id(1L);
        coffee.setCoffee_type("Coffee type");
        coffee.setPrice(1L);

        CoffeeItem coffeeItem = new CoffeeItem();
        coffeeItem.setCoffee(coffee);
        coffeeItem.setCoffeeItemId(1L);
        coffeeItem.setCust_id(1L);
        coffeeItem.setQuantity(1);

        ArrayList<CoffeeItem> coffeeItemList = new ArrayList<>();
        coffeeItemList.add(coffeeItem);
        when(coffeeItemDao.findBycustid(Mockito.<Long>any())).thenReturn(coffeeItemList);
        assertEquals(1L, coffeeItemServiceImpl.amountForUserBooking(1L).longValue());
        verify(coffeeItemDao).findBycustid(Mockito.<Long>any());
    }

    
    @Test
    void testAmountForUserBooking3() {
        Coffee coffee = new Coffee();
        coffee.setCoffee_id(1L);
        coffee.setCoffee_type("Coffee type");
        coffee.setPrice(1L);

        CoffeeItem coffeeItem = new CoffeeItem();
        coffeeItem.setCoffee(coffee);
        coffeeItem.setCoffeeItemId(1L);
        coffeeItem.setCust_id(1L);
        coffeeItem.setQuantity(1);

        Coffee coffee2 = new Coffee();
        coffee2.setCoffee_id(2L);
        coffee2.setCoffee_type("42");
        coffee2.setPrice(0L);

        CoffeeItem coffeeItem2 = new CoffeeItem();
        coffeeItem2.setCoffee(coffee2);
        coffeeItem2.setCoffeeItemId(2L);
        coffeeItem2.setCust_id(2L);
        coffeeItem2.setQuantity(0);

        ArrayList<CoffeeItem> coffeeItemList = new ArrayList<>();
        coffeeItemList.add(coffeeItem2);
        coffeeItemList.add(coffeeItem);
        when(coffeeItemDao.findBycustid(Mockito.<Long>any())).thenReturn(coffeeItemList);
        assertEquals(1L, coffeeItemServiceImpl.amountForUserBooking(1L).longValue());
        verify(coffeeItemDao).findBycustid(Mockito.<Long>any());
    }

    
    @Test
    void testAmountForUserBooking4() {
        when(coffeeItemDao.findBycustid(Mockito.<Long>any()))
                .thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> coffeeItemServiceImpl.amountForUserBooking(1L));
        verify(coffeeItemDao).findBycustid(Mockito.<Long>any());
    }

    
    @Test
    void testDeleteAllCoffeeItemsOfUser() {
        when(coffeeItemDao.findAllBycustid(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        doNothing().when(coffeeItemDao).deleteAll(Mockito.<Iterable<CoffeeItem>>any());
        coffeeItemServiceImpl.deleteAllCoffeeItemsOfUser(1L);
        verify(coffeeItemDao).findAllBycustid(Mockito.<Long>any());
        verify(coffeeItemDao).deleteAll(Mockito.<Iterable<CoffeeItem>>any());
    }

    
    @Test
    void testDeleteAllCoffeeItemsOfUser2() {
        when(coffeeItemDao.findAllBycustid(Mockito.<Long>any()))
                .thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> coffeeItemServiceImpl.deleteAllCoffeeItemsOfUser(1L));
        verify(coffeeItemDao).findAllBycustid(Mockito.<Long>any());
    }

    
    @Test
    void testUpdateCoffeeItem() {
        Coffee coffee = new Coffee();
        coffee.setCoffee_id(1L);
        coffee.setCoffee_type("Coffee type");
        coffee.setPrice(1L);

        CoffeeItem coffeeItem = new CoffeeItem();
        coffeeItem.setCoffee(coffee);
        coffeeItem.setCoffeeItemId(1L);
        coffeeItem.setCust_id(1L);
        coffeeItem.setQuantity(1);
        when(coffeeItemDao.save(Mockito.<CoffeeItem>any())).thenReturn(coffeeItem);

        Coffee coffee2 = new Coffee();
        coffee2.setCoffee_id(1L);
        coffee2.setCoffee_type("Coffee type");
        coffee2.setPrice(1L);

        CoffeeItem c = new CoffeeItem();
        c.setCoffee(coffee2);
        c.setCoffeeItemId(1L);
        c.setCust_id(1L);
        c.setQuantity(1);
        coffeeItemServiceImpl.updateCoffeeItem(c);
        verify(coffeeItemDao).save(Mockito.<CoffeeItem>any());
        assertSame(coffee2, c.getCoffee());
        assertEquals(1, c.getQuantity().intValue());
        assertEquals(1L, c.getCoffeeItemId().longValue());
        assertEquals(1L, c.getCust_id().longValue());
    }

    
    @Test
    void testUpdateCoffeeItem2() {
        when(coffeeItemDao.save(Mockito.<CoffeeItem>any())).thenThrow(new ResourceNotFoundException("An error occurred"));

        Coffee coffee = new Coffee();
        coffee.setCoffee_id(1L);
        coffee.setCoffee_type("Coffee type");
        coffee.setPrice(1L);

        CoffeeItem c = new CoffeeItem();
        c.setCoffee(coffee);
        c.setCoffeeItemId(1L);
        c.setCust_id(1L);
        c.setQuantity(1);
        assertThrows(ResourceNotFoundException.class, () -> coffeeItemServiceImpl.updateCoffeeItem(c));
        verify(coffeeItemDao).save(Mockito.<CoffeeItem>any());
    }

    
    @Test
    void testDeleteCoffeeItem() {
        doNothing().when(coffeeItemDao).deleteById(Mockito.<Long>any());
        coffeeItemServiceImpl.deleteCoffeeItem(1L);
        verify(coffeeItemDao).deleteById(Mockito.<Long>any());
    }

    
    @Test
    void testDeleteCoffeeItem2() {
        doThrow(new ResourceNotFoundException("An error occurred")).when(coffeeItemDao).deleteById(Mockito.<Long>any());
        assertThrows(ResourceNotFoundException.class, () -> coffeeItemServiceImpl.deleteCoffeeItem(1L));
        verify(coffeeItemDao).deleteById(Mockito.<Long>any());
    }
}

