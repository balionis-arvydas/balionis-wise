package com.balionis.wise.ccs.rest;

import com.balionis.wise.ccs.generated.api.HeartbeatApi;
import com.balionis.wise.ccs.generated.model.GetHeartbeatResponse;
import com.balionis.wise.ccs.service.HeartbeatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HeartbeatController implements HeartbeatApi {

    private final HeartbeatService heartbeatService;

    @Override
    public ResponseEntity<GetHeartbeatResponse> getHeartbeat() {
        log.info("alive...");
        var when = heartbeatService.checkStatus();
        return ResponseEntity.ok(new GetHeartbeatResponse().checkTs(when));
    }
}
