package shihab.diu.com.healthassistantnew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DoctorsInfo extends AppCompatActivity {

    public Button btn_aurthopedix;
    public Button btn_medicine;
    public Button btn_hematology;
    public Button btn_psychology;
    public Button btn_neurology;
    public Button btn_children;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_info);

        init();


        btn_aurthopedix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorsInfo.this, OrthopedixDoctors.class);
                startActivity(intent);

            }
        });

        btn_medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorsInfo.this, MedicineDoctors.class);
                startActivity(intent);

            }
        });


        btn_hematology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorsInfo.this, HematologyDoctors.class);
                startActivity(intent);

            }
        });


        btn_psychology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorsInfo.this, PsychologyDoctors.class);
                startActivity(intent);

            }
        });


        btn_neurology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorsInfo.this, NeurologyDoctors.class);
                startActivity(intent);

            }
        });


        btn_children.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorsInfo.this, ChildrenDoctors.class);
                startActivity(intent);

            }
        });
    }


    private void init() {
        btn_aurthopedix=(Button) findViewById(R.id.btn_doctor_aurthopedix);
        btn_medicine=(Button) findViewById(R.id.btn_doctor_medicine);
        btn_hematology=(Button) findViewById(R.id.btn_doctor_hematology);
        btn_psychology=(Button) findViewById(R.id.btn_doctor_psychology);
        btn_neurology=(Button) findViewById(R.id.btn_doctor_neurology);
        btn_children=(Button) findViewById(R.id.btn_doctor_Childeren);
    }


}
