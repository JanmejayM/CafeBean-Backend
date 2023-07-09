package com.cafe.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cafe.dao.CustomerDao;
import com.cafe.entity.Customer;
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

@ContextConfiguration(classes = {CustomerServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CustomerServiceImplTest {
    @MockBean
    private CustomerDao customerDao;

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    
    @Test
    void testGetCustomerByID() {
        Customer customer = new Customer();
        customer.setCust_id(1L);
        customer.setEmail("sagar234@gmail.com");
        customer.setPassword("Hello@123");
        customer.setPhone("6625550144");
        customer.setUsername("sagar");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerDao.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertSame(customer, customerServiceImpl.getCustomerByID(1L));
        verify(customerDao, atLeast(1)).findById(Mockito.<Long>any());
    }

    
    @Test
    void testGetCustomerByID2() {
        when(customerDao.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> customerServiceImpl.getCustomerByID(1L));
        verify(customerDao).findById(Mockito.<Long>any());
    }

    
    @Test
    void testGetCustomerByID3() {
        when(customerDao.findById(Mockito.<Long>any())).thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> customerServiceImpl.getCustomerByID(1L));
        verify(customerDao).findById(Mockito.<Long>any());
    }

   
    @Test
    void testCreateCustomer() {
        Customer customer = new Customer();
        customer.setCust_id(1L);
        customer.setEmail("sagar23401@gmail.com");
        customer.setPassword("Hey@123456");
        customer.setPhone("9999999999");
        customer.setUsername("sagar");
        when(customerDao.findByemail(Mockito.<String>any())).thenReturn(customer);

        Customer customer2 = new Customer();
        customer2.setCust_id(1L);
        customer2.setEmail("sagar23401@gmail.com");
        customer2.setPassword("Hey@123456");
        customer2.setPhone("9999999999");
        customer2.setUsername("sagar");
        assertThrows(ResourceNotFoundException.class, () -> customerServiceImpl.createCustomer(customer2));
        verify(customerDao).findByemail(Mockito.<String>any());
    }

    
    
    
    @Test
    void testUpdateCustomer() {
        Customer customer = new Customer();
        customer.setCust_id(1L);
        customer.setEmail("sagar23401@gmail.om");
        customer.setPassword("Hello@1234");
        customer.setPhone("8888888888");
        customer.setUsername("sagar");
        Optional<Customer> ofResult = Optional.of(customer);

        Customer customer2 = new Customer();
        customer2.setCust_id(1L);
        customer2.setEmail("sagar23401@gmail.com");
        customer2.setPassword("Hello@1234");
        customer2.setPhone("8888888884");
        customer2.setUsername("sagar");
        when(customerDao.save(Mockito.<Customer>any())).thenReturn(customer2);
        when(customerDao.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Customer customer3 = new Customer();
        customer3.setCust_id(1L);
        customer3.setEmail("jane.doe@example.org");
        customer3.setPassword("iloveyou");
        customer3.setPhone("6625550144");
        customer3.setUsername("janedoe");
        assertSame(customer2, customerServiceImpl.updateCustomer(customer3));
        verify(customerDao).save(Mockito.<Customer>any());
        verify(customerDao).findById(Mockito.<Long>any());
    }

    
    @Test
    void testUpdateCustomer2() {
        Customer customer = new Customer();
        customer.setCust_id(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setPassword("iloveyou");
        customer.setPhone("6625550144");
        customer.setUsername("janedoe");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerDao.save(Mockito.<Customer>any())).thenThrow(new ResourceNotFoundException("An error occurred"));
        when(customerDao.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Customer customer2 = new Customer();
        customer2.setCust_id(1L);
        customer2.setEmail("jane.doe@example.org");
        customer2.setPassword("iloveyou");
        customer2.setPhone("6625550144");
        customer2.setUsername("janedoe");
        assertThrows(ResourceNotFoundException.class, () -> customerServiceImpl.updateCustomer(customer2));
        verify(customerDao).save(Mockito.<Customer>any());
        verify(customerDao).findById(Mockito.<Long>any());
    }

    
    @Test
    void testUpdateCustomer3() {
        when(customerDao.findById(Mockito.<Long>any())).thenReturn(Optional.empty());

        Customer customer = new Customer();
        customer.setCust_id(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setPassword("iloveyou");
        customer.setPhone("6625550144");
        customer.setUsername("janedoe");
        assertThrows(ResourceNotFoundException.class, () -> customerServiceImpl.updateCustomer(customer));
        verify(customerDao).findById(Mockito.<Long>any());
    }

    
    @Test
    void testGetallCustomer() {
        ArrayList<Customer> customerList = new ArrayList<>();
        when(customerDao.findAll()).thenReturn(customerList);
        List<Customer> actualGetallCustomerResult = customerServiceImpl.getallCustomer();
        assertSame(customerList, actualGetallCustomerResult);
        assertTrue(actualGetallCustomerResult.isEmpty());
        verify(customerDao).findAll();
    }

    
    @Test
    void testGetallCustomer2() {
        when(customerDao.findAll()).thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> customerServiceImpl.getallCustomer());
        verify(customerDao).findAll();
    }

    
    @Test
    void testLogin() {
        Customer customer = new Customer();
        customer.setCust_id(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setPassword("iloveyou");
        customer.setPhone("6625550144");
        customer.setUsername("janedoe");
        when(customerDao.findByEmailAndPassword(Mockito.<String>any(), Mockito.<String>any())).thenReturn(customer);

        Customer c = new Customer();
        c.setCust_id(1L);
        c.setEmail("jane.doe@example.org");
        c.setPassword("iloveyou");
        c.setPhone("6625550144");
        c.setUsername("janedoe");
        assertSame(customer, customerServiceImpl.login(c));
        verify(customerDao, atLeast(1)).findByEmailAndPassword(Mockito.<String>any(), Mockito.<String>any());
    }

    
    @Test
    void testLogin2() {
        when(customerDao.findByEmailAndPassword(Mockito.<String>any(), Mockito.<String>any()))
                .thenThrow(new ResourceNotFoundException("An error occurred"));

        Customer c = new Customer();
        c.setCust_id(1L);
        c.setEmail("jane.doe@example.org");
        c.setPassword("iloveyou");
        c.setPhone("6625550144");
        c.setUsername("janedoe");
        assertThrows(ResourceNotFoundException.class, () -> customerServiceImpl.login(c));
        verify(customerDao).findByEmailAndPassword(Mockito.<String>any(), Mockito.<String>any());
    }
}

