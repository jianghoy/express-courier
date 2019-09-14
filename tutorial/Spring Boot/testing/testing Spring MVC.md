# Testing Spring MVC

Please refer to `server/expressCourier/src/test/java/com/fcv/expressCourier/controller/` for source code.

## With @WebMVCTest annotation

`@WebMVCTest` is used when a test focuses **only** on Spring MVC components. That means you can't inject `@Service` beans into the Spring MVC `@Controller` beans. This feature makes **unit** tests much easier after all there's no means to inject beans other way around.

Then you may ask, how can I test `@Controller` beans without it's dependencies? The answer is -- mock.

## Mock, Mockito and most importantly, @MockBean

Mock, just like the name implies, is a act of injecting mock beans as dependencies. So unit test truly decouples from other layers; mock beans are self sufficient.

The idea is indeed noval, so how should we do that? Well here we comes a handy library: `Mockito`. Here `Mockito` can provide a mock bean, return desired result on behalf of the injected beans. What's even better is that, `spring-boot-test` has already pack it up and we can just call it with `@MockBean`.

## Example

Here's a copy of `PriceSelectionControllerTest.java`.

```java
@RunWith(SpringRunner.class)
@WebMvcTest(PriceSelectionController.class)
public class PriceSelectionControllerTest {
    // a fake client
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceCalculator mockCalculator;

    @Test
    public void prices() throws Exception {
        /**
         * enforce mocking rules using Mockito
         */
        Mockito.when(mockCalculator.carPrice("SFO", "Dragon's gate"))
                .thenReturn((double) 10);
        Mockito.when(mockCalculator.dronePrice("SFO","Dragon's gate"))
                .thenReturn((double) 100);

        mockMvc
                .perform(
                        // request builder
                        get("/price")
                                .param("orig", "SFO")
                                .param("dest", "Dragon's gate")
                )
                //print output
                .andDo(print());
    }
}
```

## Reference

[WebMVCTest official doc](https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/test/autoconfigure/web/servlet/WebMvcTest.html)

[Mokito official site](https://site.mockito.org/)
