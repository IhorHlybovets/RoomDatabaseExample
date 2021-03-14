package com.b3acoc.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    AppDatabase db;
    EmployeeDao employeeDao;
    Employee employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = App.getInstance().getDatabase();
        employeeDao = db.employeeDao();
        employee = new Employee();

        Button insert = (Button) findViewById(R.id.insert);
        Button update = (Button) findViewById(R.id.update);
        Button delete = (Button) findViewById(R.id.delete);
        Button get = (Button) findViewById(R.id.get);




        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                employee.id = 1;
                employee.name = "John Smith";
                employee.salary = 10000;

                employeeDao.insert(employee);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Employee employee = employeeDao.getById(1);
                employee.name = "Ivan";
                employee.salary = 20000;
                employeeDao.update(employee);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Employee employee = employeeDao.getById(1);
                employeeDao.delete(employee);
            }
        });



        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Employee employee = employeeDao.getById(1);
                TextView show = (TextView) findViewById(R.id.text);
                try {
                    show.setText(employee.name + employee.salary);
                } catch (NullPointerException e){
                    show.setText("Nothing to show");
                }
            }
        });
    }
}
