# Spring MVC

## @Controller vs @RestController

In short, `@RestController` is a specialized version of the controller. It includes the `@Controller` and `@ResponseBody` annotations and as a result, simplifies the controller implementation(via Baeldung). The `@ResponseBody` annotation tells a controller that the object returned is automatically **serialized** into JSON and passed back into the HttpResponse object(again, via Baeldung).

But here's something fundamental: when we use `@Controller` we assume that the client is expecting some **human readable** interface, hence it requires both `model` and `view`; `@RestController`, however, is expected to return a json formatted information in response body for client to parse and respond.

## What is Serializable

Serialization is the process of converting an object into a stream of bytes to store the object or transmit it to memory, a database, or a file. Its main purpose is to save the state of an object in order to be able to recreate it when needed. The reverse process is called deserialization.(via MS C# doc)

So how does that affects my usage of Spring MVC? You may ask. The answer is, `@RestController` automatically convert a java `object` into `json`, with a prerequisite: **IT IS SERIALIZABLE**.

## example

```java
@RestController
public class PriceSelectionController {
    private final PriceCalculator priceCalculator;

    public PriceSelectionController(PriceCalculator priceCalculator) {
        this.priceCalculator = priceCalculator;
    }

    @RequestMapping(value = "/price", method = RequestMethod.GET, produces = "application/json")
    public List<PricePlan> prices(@RequestParam(name = "orig", defaultValue = "SFO") String orig,
                               @RequestParam(name = "dest", defaultValue = "SFO") String dest) {
        double dronePrice = priceCalculator.dronePrice(orig, dest);
        double carPrice = priceCalculator.carPrice(orig, dest);
        List<PricePlan> resultList = new ArrayList<>();
        resultList.add(new PricePlan(carPrice,"Car"));
        resultList.add(new PricePlan(dronePrice,"Drone"));
        return resultList;

    }

}

/**
 *  PricePlan, as a returning object(which will be serialized into JSON format),
 *  must be Serializable; what's more, for the default Jackson converter to convert
 *  it into JSON bytes, all its fields must be accessed (either its public accessible,
 *  or, via a public getter)
 */
class PricePlan implements Serializable {
    private double price;
    private String type;

    public PricePlan(double price, String type) {
        this.price = price;
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }
}
```

## Reference

[Baeldung's tutorial about @Controller vs @RestController](https://www.baeldung.com/spring-controller-vs-restcontroller)

[Baeldung's tutorial about @ResponseBody](https://www.baeldung.com/spring-request-response-body)

[Serialization(C#); no, don't read tech details](https://docs.microsoft.com/en-us/dotnet/csharp/programming-guide/concepts/serialization/)
