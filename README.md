# readme 

* start the database in the root of the `service` folder: `docker compose up`
* to see the issue with the incomplete event publication, trigger a post to add an order.

```shell
curl -H"content-type: application/json" -d '{ "lineItems" : [  { "product": 2, "quantity": 5 }] }' -XPOST http://localhost:8080/orders
```
* then, within 10 seconds of executing that `curl`, kill the Java process. 
