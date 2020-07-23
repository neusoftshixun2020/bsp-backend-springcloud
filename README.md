# bsp-backend-springcloud

In the backend, after receiving request from the frontend, zuul gateway will map the request with services that registered in the nacos server. 
Then, the request url will redirect to the service url to get the service API. 
For every services, permission control and exception handler are invoked to handle permission and exception problems.
Then for user and session part, the system will access to cache database redis to get data. 
If the data is not stored in the cache database or for other services, we directly use the MyBatis to connect the database. 
We also use druid database connecting pool provided by alibaba to manager the connecting.
Those databases are embedded in the linux cloud server and are managed by docker.

For the backend services, we have divided all services into three layers. 
The first layer is the user permission layer, which includes User, Permission and Route. 
Then, the second layer is the base information layer, and they are divided into MVO, BVO and Router. 
Finally, the third layer including order and wallet micro services invoke the services provided by the second layer.
