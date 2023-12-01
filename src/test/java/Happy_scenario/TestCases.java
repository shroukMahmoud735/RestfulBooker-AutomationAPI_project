package Happy_scenario;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

public class TestCases {

    @Test

    public void DisplayAllBookingsId(){

        given().baseUri("https://restful-booker.herokuapp.com")
          .when().get("/booking")
           .then()
                //.log().all()
                .assertThat().statusCode(200)
                .assertThat().time(lessThan(10000L));

        ;
    }

Integer Booking_id;
    @Test
    public void CreateNewBooking(){
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
        //res.then().extract();
        // .log().all()
        assertEquals(res.statusCode(),200);
        res.then().time(lessThan(10000L));

        Booking_id = res.path("bookingid");
       System.out.println("The Booking ID is:"+Booking_id);

    }

    @Test
    public void DisplayBookDetailsById(){
        given().baseUri("https://restful-booker.herokuapp.com")
                .when().get("/booking/"+Booking_id)
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().time(lessThan(10000L))
                .assertThat().body("firstname",equalTo("Sh"))
                .assertThat().body("lastname",equalTo("M"))
                .assertThat().body("totalprice",equalTo(333))
                .assertThat().body("bookingdates.checkin",equalTo("2023-11-25"))
                .assertThat().body("bookingdates.checkout",equalTo("2023-11-30"))
                .assertThat().body("additionalneeds",equalTo("Breakfast"));


    }


    String token;
    @Test (priority = 1)
    public void login(){
        String login_body = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
      Response response = given().baseUri("https://restful-booker.herokuapp.com")
                .contentType(ContentType.JSON)
                .body(login_body)
                .when().post("/auth");
                response.then().extract().response();
                assertEquals(response.statusCode(),200);
        response.then().time(lessThan(10000L));
               token = response.path("token");
               // .log().body()
               // .assertThat().statusCode(200);
        //Response response=RestAssured.get("https://restful-booker.herokuapp.com/auth");
       System.out.println(token);

    }

    @Test (priority = 2)
    public void UpdateBooking() {
        String body = "{\n" +
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
                .header("Cookie","token="+token)
                .header("Authorization ","Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .body(body)


                .when()
                .put("/booking/"+Booking_id)
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().time(lessThan(10000L))
                .assertThat().body("firstname",equalTo("Updated Firstname"))
                .assertThat().body("lastname",equalTo("Updated lastname"))
                .assertThat().body("totalprice",equalTo(222))
                .assertThat().body("bookingdates.checkin",equalTo("2023-11-28"))
                .assertThat().body("bookingdates.checkout",equalTo("2023-11-30"))
                .assertThat().body("additionalneeds",equalTo("super bowls"));

    }

    @Test (priority = 3)
    public void PartialUpdateBooking() {
        String body = "{\n" +
                "    \"firstname\" : \"Partial_Updated_Firstname\",\n" +
                "    \"totalprice\" : 756,\n" +
                "    \"additionalneeds\" : \"partial_updated_needs\"\n" +
                "}";

        given().baseUri("https://restful-booker.herokuapp.com")
                .contentType(ContentType.JSON)
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .header("Cookie","token="+token)
                .header("Authorization ","Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .body(body)


                .when()
                .patch("/booking/"+Booking_id)
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().time(lessThan(10000L))
                .assertThat().body("firstname",equalTo("Partial_Updated_Firstname"))
                .assertThat().body("lastname",equalTo("Updated lastname"))
                .assertThat().body("totalprice",equalTo(756))
                .assertThat().body("bookingdates.checkin",equalTo("2023-11-28"))
                .assertThat().body("bookingdates.checkout",equalTo("2023-11-30"))
                .assertThat().body("additionalneeds",equalTo("partial_updated_needs"));

    }


    @Test (priority = 4)
    public void DeleteBooking(){
        given().baseUri("https://restful-booker.herokuapp.com")
                .contentType(ContentType.JSON)
                .header("Content-Type","application/json")
                .header("Cookie","token="+token)
                .when().delete("/booking/"+Booking_id)
                .then()
                .assertThat().statusCode(201)
                .assertThat().time(lessThan(10000L));

    }
   @Test (priority = 5)
    public void DisplayBookingAfterDeleting(){
        given().baseUri("https://restful-booker.herokuapp.com")
                .when().get("/booking/"+Booking_id)
                .then()
                .log().all()
                .assertThat().statusCode(404)
                .assertThat().time(lessThan(10000L));


    }

    }

