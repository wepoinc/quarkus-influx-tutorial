package org.example.service.data;

import org.example.controller.data.dto.DataInDTO;
import org.example.model.measurement.Data;

import java.util.List;

public interface IDataService {

    Data createData(DataInDTO temperature) throws DataServiceException;

    List<Data> getAllData() throws DataServiceException;

    List<Data> getDataByLocation(String location) throws DataServiceException;

}
