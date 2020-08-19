package org.example.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.example.controller.data.dto.DataInDTO;
import org.example.controller.data.dto.DataOutDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Tag(name = "Data Controller")
@Path("/api/v1/data")
public class DataController {

    private static final Logger logger = LoggerFactory.getLogger("DataController");

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Store a new data measurement", description = "This endpoint will store a new data measurement")
    public ResponseEntity<DataOutDTO> createData(
            DataInDTO dataInDTO) {
        logger.info("Received new data from sensor: {} with value: {}", dataInDTO.getCoreId(), dataInDTO.getData());
        return new ResponseEntity<>(new DataOutDTO(dataInDTO), HttpStatus.OK);
    }

}