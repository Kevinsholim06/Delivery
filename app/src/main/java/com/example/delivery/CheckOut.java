//package com.example.delivery;
//
//import android.annotation.SuppressLint;
//import android.os.Bundle;
//import android.telephony.SmsManager;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ProgressBar;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.delivery.Mpesa.DataModal;
//
//import java.util.UUID;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class CheckOut extends AppCompatActivity {
//
//    public  double Amount;
//    private static final String BASE_URL = "https://tinypesa.com/api/v1/";
//
//    private ProgressBar loadingPB;
//    String account;
//
//
//    Button pay;
//    EditText phone;
//
//    public String resultString;
//    private static final String PHONE_NUMBER = "+254748875966";
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        Amount = Data.total;
//        pay =(Button) findViewById(R.id.idBtnPay);
//        phone =(EditText) findViewById(R.id.phoneid);
//
//        pay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(phone.getText().toString().isEmpty() ){
//                    Toast.makeText(CheckOut.this, "Please enter phone number", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                account = String.valueOf(UUID.randomUUID());
//                payment(phone.getText().toString(), Amount, account);
//            }
//        });
//
//    }
//
//        private void payment(String phone, double amount,String accountNo) {
//
////            loadingPB.setVisibility(View.VISIBLE);
//            Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//
//            RetrofitApi service = retrofit.create(RetrofitApi.class);
//
//            DataModal dataModal = new DataModal(phone, Data.total, accountNo );
//            String acceptHeader = "application/json";
//            String apikey = "UILUeVO6GBD";
//            Call<DataModal> call = service.createPost(apikey,acceptHeader,dataModal);
//
//            call.enqueue(new Callback<DataModal>() {
//                @Override
//                public void onResponse(@NonNull Call<DataModal> call, @NonNull Response<DataModal> response) {
//                    // this method is called when we get response from our api.
//                    Toast.makeText(CheckOut.this, response.message(), Toast.LENGTH_LONG).show();
//                    Log.d("Test",response.message());
//                    // below line is for hiding our progress bar.
////                    loadingPB.setVisibility(View.GONE);
//
//                        SmsManager smsManager = SmsManager.getDefault();
//// Send the SMS message
//                smsManager.sendTextMessage(PHONE_NUMBER, null,Data.email+ " has made an order of ksh"+ Data.total, null, null);
//                        // Show a toast message to indicate that the SMS message was sent
//                        Toast.makeText(CheckOut.this, "SMS message sent", Toast.LENGTH_SHORT).show();
//
//                    // we are getting response from our body
//                    // and passing it to our dataModal class.
//                    DataModal responseFromAPI = response.body();
//                    // on below line we are getting our data from dataModal class and adding it to our string
////                    String responseString = "Response Code : " + response;
////
////                    // below line we are setting our
////                    // string to our text view.
////                    responseTV.setText(responseString);
//                }
//
//                @SuppressLint("SetTextI18n")
//                @Override
//                public void onFailure(@NonNull Call<DataModal> call, @NonNull Throwable t) {
//                    // setting text to our text view when
//                    // we get error response from API.
////                    responseTV.setText("Error found is : " + t.getMessage());
//                }
//            });
//        }
//
//
//
//
//
//
//
////        pay.setOnClickListener(new View.OnClickListener() {
////            @SuppressLint("StaticFieldLeak")
////            @Override
////            public void onClick(View view) {
////                String phone_number = phone.getText().toString();
////
////                new AsyncTask<Void, Void, String>() {
////                    @Override
////                    protected String doInBackground(Void... voids) {
////                        URL url = null;
////                        try {
////                            url = new URL("https://tinybitdaraja.herokuapp.com/");
////                        } catch (MalformedURLException e) {
////                            throw new RuntimeException(e);
////                        }
////                        HttpURLConnection con = null;
////                        try {
////                            con = (HttpURLConnection)url.openConnection();
////                        } catch (IOException e) {
////                            throw new RuntimeException(e);
////                        }
////                        try {
////                            con.setRequestMethod("POST");
////                        } catch (ProtocolException e) {
////                            throw new RuntimeException(e);
////                        }
////                        con.setRequestProperty("Content-Type", "application/json");
////                        con.setRequestProperty("Accept", "application/json");
////                        con.setDoOutput(true);
////                        String jsonInputString = "{" +
////                                "\"amount\": 10," +
////                                "\"number\": 254748875966," +
////                                "\"private_key\": \"baa5ed50-0fa9-4445-b6de-f61150ddc638\"," +
////                                "\"HEADERS\": {" +
////                                "\"Authorization\":\"USsx32xnGiUX5kfVTgrZN\"" +
////                                "}" +
////                                "}";
////                        try(OutputStream os = con.getOutputStream()) {
////                            byte[] input = jsonInputString.getBytes("utf-8");
////                            os.write(input, 0, input.length);
////                        } catch (IOException e) {
////                            throw new RuntimeException(e);
////                        }
////                        try(BufferedReader br = new BufferedReader(
////                                new InputStreamReader(con.getInputStream(), "utf-8"))) {
////                            StringBuilder response = new StringBuilder();
////                            String responseLine = null;
////                            while ((responseLine = br.readLine()) != null) {
////                                response.append(responseLine.trim());
////                            }
////                            return response.toString();
////                        } catch (IOException e) {
////                            throw new RuntimeException(e);
////                        }
////                    }
////
////                    @Override
////                    protected void onPostExecute(String response) {
////                        // Process the response here
////                        System.out.println(response);
////                    }
////                }.execute();
////            }
////        });
//
//
//
//    }
//
//
//
