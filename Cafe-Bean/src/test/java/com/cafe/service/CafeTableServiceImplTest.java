package com.cafe.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cafe.dao.CafeTableDao;
import com.cafe.entity.CafeTable;
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

@ContextConfiguration(classes = {CafeTableServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CafeTableServiceImplTest {
    @MockBean
    private BookingService bookingService;

    @MockBean
    private CafeTableDao cafeTableDao;

    @Autowired
    private CafeTableServiceImpl cafeTableServiceImpl;

    
    @Test
    void testCafeTableByID() {
        CafeTable cafeTable = new CafeTable();
        cafeTable.setSeatingCapacity(1);
        cafeTable.setTableId(1L);
        Optional<CafeTable> ofResult = Optional.of(cafeTable);
        when(cafeTableDao.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertSame(cafeTable, cafeTableServiceImpl.cafeTableByID(1L));
        verify(cafeTableDao, atLeast(1)).findById(Mockito.<Long>any());
    }

    @Test
    void testCafeTableByID2() {
        when(cafeTableDao.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> cafeTableServiceImpl.cafeTableByID(1L));
        verify(cafeTableDao).findById(Mockito.<Long>any());
    }

    
    @Test
    void testCafeTableByID3() {
        when(cafeTableDao.findById(Mockito.<Long>any())).thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> cafeTableServiceImpl.cafeTableByID(1L));
        verify(cafeTableDao).findById(Mockito.<Long>any());
    }

    
    @Test
    void testCreatecafeTable() {
        CafeTable cafeTable = new CafeTable();
        cafeTable.setSeatingCapacity(1);
        cafeTable.setTableId(1L);
        when(cafeTableDao.save(Mockito.<CafeTable>any())).thenReturn(cafeTable);

        CafeTable cafeTable2 = new CafeTable();
        cafeTable2.setSeatingCapacity(1);
        cafeTable2.setTableId(1L);
        assertSame(cafeTable, cafeTableServiceImpl.createcafeTable(cafeTable2));
        verify(cafeTableDao).save(Mockito.<CafeTable>any());
    }

   
    @Test
    void testCreatecafeTable2() {
        when(cafeTableDao.save(Mockito.<CafeTable>any())).thenThrow(new ResourceNotFoundException("An error occurred"));

        CafeTable cafeTable = new CafeTable();
        cafeTable.setSeatingCapacity(1);
        cafeTable.setTableId(1L);
        assertThrows(ResourceNotFoundException.class, () -> cafeTableServiceImpl.createcafeTable(cafeTable));
        verify(cafeTableDao).save(Mockito.<CafeTable>any());
    }

   
    @Test
    void testUpdateCafeTable() {
        CafeTable cafeTable = new CafeTable();
        cafeTable.setSeatingCapacity(1);
        cafeTable.setTableId(1L);
        Optional<CafeTable> ofResult = Optional.of(cafeTable);

        CafeTable cafeTable2 = new CafeTable();
        cafeTable2.setSeatingCapacity(1);
        cafeTable2.setTableId(1L);
        when(cafeTableDao.save(Mockito.<CafeTable>any())).thenReturn(cafeTable2);
        when(cafeTableDao.findById(Mockito.<Long>any())).thenReturn(ofResult);

        CafeTable cafeTable3 = new CafeTable();
        cafeTable3.setSeatingCapacity(1);
        cafeTable3.setTableId(1L);
        assertSame(cafeTable2, cafeTableServiceImpl.updateCafeTable(cafeTable3));
        verify(cafeTableDao).save(Mockito.<CafeTable>any());
        verify(cafeTableDao).findById(Mockito.<Long>any());
    }

    
    @Test
    void testUpdateCafeTable2() {
        CafeTable cafeTable = new CafeTable();
        cafeTable.setSeatingCapacity(1);
        cafeTable.setTableId(1L);
        Optional<CafeTable> ofResult = Optional.of(cafeTable);
        when(cafeTableDao.save(Mockito.<CafeTable>any())).thenThrow(new ResourceNotFoundException("An error occurred"));
        when(cafeTableDao.findById(Mockito.<Long>any())).thenReturn(ofResult);

        CafeTable cafeTable2 = new CafeTable();
        cafeTable2.setSeatingCapacity(1);
        cafeTable2.setTableId(1L);
        assertThrows(ResourceNotFoundException.class, () -> cafeTableServiceImpl.updateCafeTable(cafeTable2));
        verify(cafeTableDao).save(Mockito.<CafeTable>any());
        verify(cafeTableDao).findById(Mockito.<Long>any());
    }

    
    @Test
    void testUpdateCafeTable3() {
        when(cafeTableDao.findById(Mockito.<Long>any())).thenReturn(Optional.empty());

        CafeTable cafeTable = new CafeTable();
        cafeTable.setSeatingCapacity(1);
        cafeTable.setTableId(1L);
        assertThrows(ResourceNotFoundException.class, () -> cafeTableServiceImpl.updateCafeTable(cafeTable));
        verify(cafeTableDao).findById(Mockito.<Long>any());
    }

   
    @Test
    void testGetallCafeTable() {
        ArrayList<CafeTable> cafeTableList = new ArrayList<>();
        when(cafeTableDao.findAll()).thenReturn(cafeTableList);
        List<CafeTable> actualGetallCafeTableResult = cafeTableServiceImpl.getallCafeTable();
        assertSame(cafeTableList, actualGetallCafeTableResult);
        assertTrue(actualGetallCafeTableResult.isEmpty());
        verify(cafeTableDao).findAll();
    }

    
    @Test
    void testGetallCafeTable2() {
        when(cafeTableDao.findAll()).thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> cafeTableServiceImpl.getallCafeTable());
        verify(cafeTableDao).findAll();
    }

    
    @Test
    void testAvailableTables() {
        when(cafeTableDao.findAll()).thenReturn(new ArrayList<>());
        assertTrue(cafeTableServiceImpl.availableTables(new ArrayList<>()).isEmpty());
        verify(cafeTableDao).findAll();
    }

    
    @Test
    void testAvailableTables2() {
        CafeTable cafeTable = new CafeTable();
        cafeTable.setSeatingCapacity(1);
        cafeTable.setTableId(1L);

        ArrayList<CafeTable> cafeTableList = new ArrayList<>();
        cafeTableList.add(cafeTable);
        when(cafeTableDao.findAll()).thenReturn(cafeTableList);
        assertEquals(1, cafeTableServiceImpl.availableTables(new ArrayList<>()).size());
        verify(cafeTableDao).findAll();
    }

    
    @Test
    void testAvailableTables3() {
        CafeTable cafeTable = new CafeTable();
        cafeTable.setSeatingCapacity(1);
        cafeTable.setTableId(1L);

        CafeTable cafeTable2 = new CafeTable();
        cafeTable2.setSeatingCapacity(0);
        cafeTable2.setTableId(2L);

        ArrayList<CafeTable> cafeTableList = new ArrayList<>();
        cafeTableList.add(cafeTable2);
        cafeTableList.add(cafeTable);
        when(cafeTableDao.findAll()).thenReturn(cafeTableList);
        assertEquals(2, cafeTableServiceImpl.availableTables(new ArrayList<>()).size());
        verify(cafeTableDao).findAll();
    }

    
    @Test
    void testAvailableTables4() {
        when(cafeTableDao.findAll()).thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> cafeTableServiceImpl.availableTables(new ArrayList<>()));
        verify(cafeTableDao).findAll();
    }

    
    @Test
    void testAvailableTables5() {
        CafeTable cafeTable = new CafeTable();
        cafeTable.setSeatingCapacity(1);
        cafeTable.setTableId(1L);

        CafeTable cafeTable2 = new CafeTable();
        cafeTable2.setSeatingCapacity(0);
        cafeTable2.setTableId(2L);

        ArrayList<CafeTable> cafeTableList = new ArrayList<>();
        cafeTableList.add(cafeTable2);
        cafeTableList.add(cafeTable);
        when(cafeTableDao.findAll()).thenReturn(cafeTableList);

        ArrayList<Long> arr = new ArrayList<>();
        arr.add(1L);
        assertEquals(1, cafeTableServiceImpl.availableTables(arr).size());
        verify(cafeTableDao).findAll();
    }

    
    @Test
    void testDeleteCafeTable() {
        doNothing().when(cafeTableDao).deleteById(Mockito.<Long>any());
        cafeTableServiceImpl.deleteCafeTable(1L);
        verify(cafeTableDao).deleteById(Mockito.<Long>any());
    }

    
    @Test
    void testDeleteCafeTable2() {
        doThrow(new ResourceNotFoundException("An error occurred")).when(cafeTableDao).deleteById(Mockito.<Long>any());
        assertThrows(ResourceNotFoundException.class, () -> cafeTableServiceImpl.deleteCafeTable(1L));
        verify(cafeTableDao).deleteById(Mockito.<Long>any());
    }
}

