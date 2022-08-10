package shihab.diu.com.healthassistantnew;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import shihab.diu.com.healthassistantnew.url_list.Url_Helper;

public class TeethSymptomsInput extends AppCompatActivity {

    CheckBox ONE,TWO,THREE;
    Button submit;
    ListView listView;
    Button btnDoctor;

    RequestQueue queue;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    int sum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teeth_symptoms_input);


        ONE = (CheckBox)findViewById(R.id.checkBox1);
        TWO = (CheckBox)findViewById(R.id.checkBox2);
        THREE = (CheckBox)findViewById(R.id.checkBox3);
        submit = (Button)findViewById(R.id.button1);
        btnDoctor = (Button) findViewById(R.id.btnDoctor);
        btnDoctor.setVisibility(View.INVISIBLE);

        listView = (ListView) findViewById(R.id.lv_result);


        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<String>(TeethSymptomsInput.this, android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);

        queue = Volley.newRequestQueue(TeethSymptomsInput.this);

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(THREE.isChecked() && ONE.isChecked() && TWO.isChecked())
                {
                    sum = 7;
                }
                else if(THREE.isChecked() && TWO.isChecked())
                {
                    sum = 6;

                }else if(ONE.isChecked() && THREE.isChecked())
                {
                    sum = 5;

                }else if(ONE.isChecked() && TWO.isChecked())
                {
                    sum = 4;

                }
                else if(ONE.isChecked())
                {
                    sum = 1;

                }
                else if(TWO.isChecked())
                {
                    sum = 2;

                }
                else if(THREE.isChecked())
                {
                    sum = 3;
                }else {
                    sum = 0;
                }

                switch(sum)
                {
                    case 0:
                        Toast.makeText(TeethSymptomsInput.this, "Please select symptoms", Toast.LENGTH_SHORT).show();
                        arrayList.clear();
                        arrayAdapter.notifyDataSetChanged();
                        btnDoctor.setVisibility(View.INVISIBLE);
                        break;
                    case 1:
                        findForOneInput("swallow the gums or reddish color");
                        btnDoctor.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        findForOneInput("gums pain or fever");
                        btnDoctor.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        findForOneInput("bleeding or ugly smell");
                        btnDoctor.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        findForTwoInput("swallow the gums or reddish color","gums pain or fever");
                        btnDoctor.setVisibility(View.VISIBLE);
                        break;
                    case 5:
                        findForTwoInput("swallow the gums or reddish color","bleeding or ugly smell");
                        btnDoctor.setVisibility(View.VISIBLE);
                        break;
                    case 6:
                        findForTwoInput("gums pain or fever","bleeding or ugly smell");
                        btnDoctor.setVisibility(View.VISIBLE);
                        break;
                    case 7:
                        findForThreeInput("swallow the gums or reddish color","gums pain or fever","bleeding or ugly smell");
                        btnDoctor.setVisibility(View.VISIBLE);
                        break;
                }

            }
        });

    }

    private void findForThreeInput(final String input1, final String input2, final String input3) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url_Helper.TEETH_URL_THREE_INPUT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        arrayList.clear();
                        arrayAdapter.notifyDataSetChanged();

                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            int length = jsonArray.length();
                            for (int i=0; i<length; i++){
                                try {
                                    String name = jsonArray.getJSONObject(i).getString("diseases");
                                    arrayList.add(name);
                                    arrayAdapter.notifyDataSetChanged();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("symptoms1",input1);
                params.put("symptoms2",input2);
                params.put("symptoms3",input3);
                return params;
            }

        };
        queue.add(stringRequest);



    }

    private void findForTwoInput(final String input1, final String input2) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url_Helper.TEETH_URL_TWO_INPUT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        arrayList.clear();
                        arrayAdapter.notifyDataSetChanged();

                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            int length = jsonArray.length();
                            for (int i=0; i<length; i++){
                                try {
                                    String name = jsonArray.getJSONObject(i).getString("diseases");
                                    arrayList.add(name);
                                    arrayAdapter.notifyDataSetChanged();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(TeethSymptomsInput.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("symptoms1",input1);
                params.put("symptoms2",input2);
                return params;
            }

        };
        queue.add(stringRequest);

    }

    private void findForOneInput(final String input) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url_Helper.TEETH_URL_ONE_INPUT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        arrayList.clear();
                        arrayAdapter.notifyDataSetChanged();

                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            int length = jsonArray.length();
                            for (int i=0; i<length; i++){
                                try {
                                    String name = jsonArray.getJSONObject(i).getString("diseases");
                                    arrayList.add(name);
                                    arrayAdapter.notifyDataSetChanged();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("symptoms",input);
                return params;
            }

        };
        queue.add(stringRequest);

    }
}
