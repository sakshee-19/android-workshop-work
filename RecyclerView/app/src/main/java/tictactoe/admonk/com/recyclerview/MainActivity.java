package tictactoe.admonk.com.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> names=new ArrayList<>();
    ArrayList<String> ids=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        names.add("Rajat");
        names.add("saku");
        names.add("sid");
        names.add("sanu");

        ids.add("123455");
        ids.add("19071996");
        ids.add("180996");
        ids.add("28143221");

        RecyclerView rvStudents=(RecyclerView) findViewById(R.id.rvStudents);

        rvStudents.setHasFixedSize(true);

        RecyclerView.LayoutManager lm=new LinearLayoutManager(getApplicationContext());

        rvStudents.setLayoutManager(lm);

        StudentAdapter sa=new StudentAdapter(MainActivity.this,names,ids);
        rvStudents.setAdapter(sa);
    }
}
