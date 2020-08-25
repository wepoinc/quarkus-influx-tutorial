package org.example.controller;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.example.controller.data.dto.DataInDTO;
import org.example.controller.data.dto.DataOutDTO;
import org.example.model.measurement.Data;
import org.example.service.data.DataServiceException;
import org.example.service.data.IDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Data Controller")
@Path("/api/v1/data")
public class DataController {
    private static final Logger logger = LoggerFactory.getLogger("DataController");
    @Inject
    IDataService dataService;
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Store a new data measurement",
            description = "This endpoint will store a new data measurement")
    public ResponseEntity<DataOutDTO> createData(DataInDTO dataInDTO) throws DataServiceException {
        Data data  = dataService.createData(dataInDTO);
        return new ResponseEntity<>(new DataOutDTO(data), HttpStatus.OK);
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    @Operation(summary = "Get all data measures available", description = "Get all data measurement available")
    public ResponseEntity<List<DataOutDTO>> getAllData() throws DataServiceException {
        return new ResponseEntity<>(dataService.getAllData().stream()
                .map(DataOutDTO::new)
                .collect(Collectors.toList()), HttpStatus.OK);
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all data measures for the specified location", description = "Get all data measurement available for the location")
    public ResponseEntity<List<DataOutDTO>> getDataByLocation(
            @NotNull @QueryParam(value = "location") String location
    ) throws DataServiceException {
        return new ResponseEntity<>(dataService.getDataByLocation(location).stream()
                .map(DataOutDTO::new)
                .collect(Collectors.toList()), HttpStatus.OK);
    }
}
