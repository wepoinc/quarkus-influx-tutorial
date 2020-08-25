package org.example.service.data.impl;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.QueryApi;
import com.influxdb.client.WriteApi;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.exceptions.InfluxException;
import com.influxdb.query.dsl.Flux;
import com.influxdb.query.dsl.functions.restriction.Restrictions;
import org.example.controller.data.dto.DataInDTO;
import org.example.model.measurement.Data;
import org.example.service.data.DataServiceException;
import org.example.service.data.IDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class DataServiceDefault implements IDataService {

    private static final Logger logger = LoggerFactory.getLogger("DataServiceDefault");
    public static final Long DATA_RETENTION_DAYS = -14L;

    private InfluxDBClient influxDBClient;

    @Value(value = "${influxdb.connectionUrl}")
    String connectionUrl;

    @Value(value = "${influxdb.token}")
    String token;

    @Value(value = "${influxdb.orgId}")
    String orgId;

    @Value(value = "${influxdb.data.bucketId}")
    String bucketId;

    @Value(value = "${influxdb.data.bucketName}")
    String bucketName;


    public DataServiceDefault() {
    }

    @PostConstruct
    private void initializeInfluxDBClient() {
        logger.info("Connecting to: {}, token: {}, org: {}, bucketId: {}",
                connectionUrl, token, orgId, bucketId);
        this.influxDBClient = InfluxDBClientFactory.create(connectionUrl, token.toCharArray(), orgId, bucketId);
    }

    @Override
    public Data createData(DataInDTO dataInDTO) throws DataServiceException {
        try {
            WriteApi writeApi = influxDBClient.getWriteApi();

            Data data = new Data();
            data.setLocation(dataInDTO.getCoreId());
            data.setTime(Instant.parse(dataInDTO.getPublishedAt()));
            data.setValue(dataInDTO.getData());

            writeApi.writeMeasurement(WritePrecision.NS, data);

            return data;
        } catch (InfluxException ie) {
            throw new DataServiceException("Error while writing data to Influx: " + ie.getMessage());
        }
    }

    @Override
    public List<Data> getAllData() throws DataServiceException {
        try {
            String temperatureByTimeQuery = Flux.from(bucketName)
                    .range(DATA_RETENTION_DAYS, ChronoUnit.DAYS)
                    .toString();

            QueryApi queryApi = influxDBClient.getQueryApi();
            return queryApi.query(temperatureByTimeQuery, Data.class);
        } catch (Exception e) {
            throw new DataServiceException("Error while getting data from InfluxDB: " + e.getMessage());
        }
    }

    @Override
    public List<Data> getDataByLocation(String location) throws DataServiceException {
        try {
            String temperatureByLocationQuery = Flux.from(bucketName)
                    .range(DATA_RETENTION_DAYS, ChronoUnit.DAYS)
                    .filter(Restrictions.and(Restrictions.column("location").equal(location)))
                    .toString();

            QueryApi queryApi = influxDBClient.getQueryApi();

            return queryApi.query(temperatureByLocationQuery, Data.class);
        } catch (Exception e) {
            throw new DataServiceException("Error while getting the data from InfluxDB by location: " + e.getMessage());
        }
    }
}
