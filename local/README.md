# Local Environment

This module holds resources needed to create development environment
on localhost.

The directory contains a set of composable docker-compose files
that can be used to create a local functional develop environment.

## Run

In order to start a full environment, run:
```shell
make up
```

To kill all the containers, execute:
```shell
make down
```

## Test

```
% curl -v -X GET http://localhost:9091/api/v1/heartbeat
...
< HTTP/1.1 200 
...

% docker logs local-producer --follow
...
11:05:48.019 [,] [main] INFO  c.b.d.l.p.c.AppConfigurationProperties - appConfigurationProperties=AppConfigurationProperties(name=dainius-lesson11-producer)
...
11:09:32.988 [8d2805328169a515ac13bfe2aaae3807,934fad8fd1146d95] [http-nio-9090-exec-1] INFO  c.b.d.l.p.rest.HeartbeatController - alive...
11:09:32.988 [8d2805328169a515ac13bfe2aaae3807,934fad8fd1146d95] [http-nio-9090-exec-1] INFO  c.b.d.l.p.service.HeartbeatService - name=dainius-lesson11-producer

...
```

## Observe 

```
% chrome http://localhost:3000
...
username: admin
password: grafana
...
```

## Trace

```
% docker logs local-ccs
...
17:53:39.580 [,] [main] INFO  com.balionis.wise.ccs.Application - Started Application in 6.448 seconds (process running for 8.097)
17:53:41.748 [b6a13065135c6b3bbe225de6822f714c,42d77a524d3e5790] [http-nio-9090-exec-1] INFO  c.b.w.ccs.rest.HeartbeatController - alive...
17:53:41.748 [b6a13065135c6b3bbe225de6822f714c,42d77a524d3e5790] [http-nio-9090-exec-1] INFO  c.b.w.ccs.service.HeartbeatService - name=balionis-wise-ccs
...
% chrome http://localhost:16686/trace/b6a13065135c6b3bbe225de6822f714c
...
```

username: admin
password: grafana
...
```
