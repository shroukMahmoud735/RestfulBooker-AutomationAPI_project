package Negative_Scenario;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Negative_TestCases {

  @Test
  public void CreateNewBookingWithInvalidPrice(){
      String body = "{\n" +
              "    \"firstname\" : \"Sh\",\n" +
              "    \"lastname\" : \"M\",\n" +
              "    \"totalprice\" : 5#$3412,\n" +
              "    \"depositpaid\" : true,\n" +
              "    \"bookingdates\" : {\n" +
              "        \"checkin\" : \"2023-11-25\",\n" +
              "        \"checkout\" : \"2023-11-30\"\n" +
              "    },\n" +
              "    \"additionalneeds\" : \"Breakfast\"\n" +
              "}";
      Response res=  given().baseUri("https://restful-booker.herokuapp.com")
              .contentType(ContentType.JSON)
              .body(body)
              .when().post("/booking");
      //res.then().extract();
      // .log().all()
      // 400 is a bad request
      res.then().time(lessThan(10000L));
      Assert.assertEquals(res.statusCode(),400);



  }
    Integer Booking_id;
@Test
    public void UpdateBookingWithoutToken() {
//Creating a new booking first
    String body = "{\n" +
            "    \"firstname\" : \"Sh\",\n" +
            "    \"lastname\" : \"M\",\n" +
            "    \"totalprice\" : 333,\n" +
            "    \"depositpaid\" : true,\n" +
            "    \"bookingdates\" : {\n" +
            "        \"checkin\" : \"2023-11-25\",\n" +
            "        \"checkout\" : \"2023-11-30\"\n" +
            "    },\n" +
            "    \"additionalneeds\" : \"Breakfast\"\n" +
            "}";
    Response res=  given().baseUri("https://restful-booker.herokuapp.com")
            .contentType(ContentType.JSON)
            .body(body)
            .when().post("/booking");
    // .log().all()
    Assert.assertEquals(res.statusCode(),200);
    Booking_id = res.path("bookingid");
    System.out.println("The Booking ID is:"+Booking_id);

    //Trying to update the booking details without logging or token

        String updated_body = "{\n" +
                "    \"firstname\" : \"Updated Firstname\",\n" +
                "    \"lastname\" : \"Updated lastname\",\n" +
                "    \"totalprice\" : 222,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2023-11-28\",\n" +
                "        \"checkout\" : \"2023-11-30\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"super bowls\"\n" +
                "}";
        given().baseUri("https://restful-booker.herokuapp.com")
                .contentType(ContentType.JSON)
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .body(updated_body)


                .when()
                .put("/booking/"+Booking_id)
                .then()
                //.log().all()
                //403 is Forbidden
                .assertThat().statusCode(403)
                .assertThat().time(lessThan(10000L));

    }
    @Test
    public void DeleteBookingWithoutToken(){
        given().baseUri("https://restful-booker.herokuapp.com")
                .contentType(ContentType.JSON)
                .header("Content-Type","application/json")
                //.header("Cookie","token="+token)
                .when().delete("/booking/"+Booking_id)
                .then()
                //403 is Forbidden
                .assertThat().statusCode(403)
                .assertThat().time(lessThan(10000L));

    }

    @Test
    public void Create_NewBooking_With_EmptyFirstName(){
        String body = "{\n" +
                "    \"firstname\" : \"\",\n" +
                "    \"lastname\" : \"M\",\n" +
                "    \"totalprice\" : 123,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2023-11-25\",\n" +
                "        \"checkout\" : \"2023-11-30\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        Response res=  given().baseUri("https://restful-booker.herokuapp.com")
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("/booking");
        //res.then().extract();
        // .log().all()
        // 400 is a bad request
        res.then().time(lessThan(10000L));
        Assert.assertEquals(res.statusCode(),400);

    }
    @Test
    public void Create_NewBooking_With_EmptyLastname(){
        String body = "{\n" +
                "    \"firstname\" : \"Sh\",\n" +
                "    \"lastname\" : \"\",\n" +
                "    \"totalprice\" : 323,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2023-11-25\",\n" +
                "        \"checkout\" : \"2023-11-30\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        Response res=  given().baseUri("https://restful-booker.herokuapp.com")
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("/booking");
        //res.then().extract();
        // .log().all()
        // 400 is a bad request
        res.then().time(lessThan(10000L));
        Assert.assertEquals(res.statusCode(),400);

    }
    @Test
    public void Create_NewBooking_With_EmptyCheckin(){
        String body = "{\n" +
                "    \"firstname\" : \"Sh\",\n" +
                "    \"lastname\" : \"M\",\n" +
                "    \"totalprice\" : 212,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"\",\n" +
                "        \"checkout\" : \"2023-11-30\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        Response res=  given().baseUri("https://restful-booker.herokuapp.com")
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("/booking");
        //res.then().extract();
        // .log().all()
        // 400 is a bad request
        res.then().time(lessThan(10000L));
        Assert.assertEquals(res.statusCode(),400);

    }
    @Test
    public void Create_NewBooking_With_EmptyCheckOut(){
        String body = "{\n" +
                "    \"firstname\" : \"Sh\",\n" +
                "    \"lastname\" : \"M\",\n" +
                "    \"totalprice\" : 212,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2023-11-25\",\n" +
                "        \"checkout\" : \"\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        Response res=  given().baseUri("https://restful-booker.herokuapp.com")
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("/booking");
        //res.then().extract();
        // .log().all()
        // 400 is a bad request
        res.then().time(lessThan(10000L));
        Assert.assertEquals(res.statusCode(),400);

    }
    @Test
    public void Create_NewBooking_With_PreviousCheckIn_Date(){
        String body = "{\n" +
                "    \"firstname\" : \"Sh\",\n" +
                "    \"lastname\" : \"M\",\n" +
                "    \"totalprice\" : 212,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-11-30\",\n" +
                "        \"checkout\" : \"2023-11-30\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        Response res=  given().baseUri("https://restful-booker.herokuapp.com")
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("/booking");
        //res.then().extract();
        // .log().all()
        // 400 is a bad request
        res.then().time(lessThan(10000L));
        Assert.assertEquals(res.statusCode(),400);

    }

    @Test
    public void Create_NewBooking_With_PreviousCheckout_Date(){
        String body = "{\n" +
                "    \"firstname\" : \"Sh\",\n" +
                "    \"lastname\" : \"M\",\n" +
                "    \"totalprice\" : 212,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2023-11-30\",\n" +
                "        \"checkout\" : \"2018-11-30\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        Response res=  given().baseUri("https://restful-booker.herokuapp.com")
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("/booking");
        //res.then().extract();
        // .log().all()
        // 400 is a bad request
        res.then().time(lessThan(10000L));
        Assert.assertEquals(res.statusCode(),400);

    }

    @Test
    public void Create_NewBooking_With_CheckoutDate_Smaller_CheckIn(){
        String body = "{\n" +
                "    \"firstname\" : \"Sh\",\n" +
                "    \"lastname\" : \"M\",\n" +
                "    \"totalprice\" : 212,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2023-11-30\",\n" +
                "        \"checkout\" : \"2023-11-28\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        Response res=  given().baseUri("https://restful-booker.herokuapp.com")
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("/booking");
        //res.then().extract();
        // .log().all()
        // 400 is a bad request
        res.then().time(lessThan(10000L));
        Assert.assertEquals(res.statusCode(),400);

    }

    @Test
    public void Create_NewBooking_With_FirstName_ContainsNumbers(){
        String body = "{\n" +
                "    \"firstname\" : \"2Sh1\",\n" +
                "    \"lastname\" : \"M\",\n" +
                "    \"totalprice\" : 123,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2023-11-25\",\n" +
                "        \"checkout\" : \"2023-11-30\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        Response res=  given().baseUri("https://restful-booker.herokuapp.com")
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("/booking");
        //res.then().extract();
        // .log().all()
        // 400 is a bad request
        res.then().time(lessThan(10000L));
        Assert.assertEquals(res.statusCode(),400);

    }

    @Test
    public void Create_NewBooking_With_LastName_ContainsNumbers(){
        String body = "{\n" +
                "    \"firstname\" : \"SH\",\n" +
                "    \"lastname\" : \"22M11\",\n" +
                "    \"totalprice\" : 123,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2023-11-25\",\n" +
                "        \"checkout\" : \"2023-11-30\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        Response res=  given().baseUri("https://restful-booker.herokuapp.com")
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("/booking");
        //res.then().extract();
        // .log().all()
        // 400 is a bad request
       res.then().time(lessThan(10000L));
        Assert.assertEquals(res.statusCode(),400);


    }

}
