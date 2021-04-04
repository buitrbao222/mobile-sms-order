package com.example.main;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class LandmarksActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landmarks_activity);

        String[] landmarkNames = new String[]{
                "Saigon University",
                "Ho Chi Minh City University of Education",
                "Ho Chi Minh City University of Science",
                "Hoa Sen University"
        };

        String[] landmarkLocations = new String[]{
                "https://www.google.com/maps/place/Saigon+University/@10.7599171,106.6822583,15z/data=!4m5!3m4!1s0x0:0xa06651894598e488!8m2!3d10.7599171!4d106.6822583",
                "https://www.google.com/maps/place/Ho+Chi+Minh+City+University+of+Education/@10.7613832,106.6821711,15z/data=!4m2!3m1!1s0x0:0x2fb4502ebd044212?sa=X&ved=2ahUKEwjAsY3TsuTvAhVrxosBHVFmCqsQ_BIwEnoECB0QBQ",
                "https://www.google.com/maps/place/Vietnam+National+University+Ho+Chi+Minh+City+-+University+of+Science/@10.762913,106.6821717,15z/data=!4m2!3m1!1s0x0:0x43900f1d4539a3d?sa=X&ved=2ahUKEwjX_8udsuTvAhWpyosBHSHkBvYQ_BIwG3oECDIQBQ",
                "https://www.google.com/maps/place/Hoa+Sen+University/@10.7702198,106.6925234,15z/data=!4m2!3m1!1s0x0:0xd7ff822aa9ef6e94?sa=X&ved=2ahUKEwiP--uksuTvAhXiKKYKHbpmDIIQ_BIwHHoECCoQBQ",
        };

        String[] landmarkInfo = new String[]{
                "https://sgu.edu.vn/",
                "https://hcmue.edu.vn/",
                "https://hcmus.edu.vn/",
                "https://www.hoasen.edu.vn/"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.landmark_item, landmarkNames);

        ListView landmarksListView = findViewById(R.id.landmarks_listview);
        landmarksListView.setAdapter(adapter);

        landmarksListView.setOnItemClickListener((parent, view, position, id) -> {
            LandmarkDialog landmarkDialog = new LandmarkDialog();
            Bundle bundle = new Bundle();
            bundle.putString("mapUrl", landmarkLocations[position]);
            bundle.putString("infoUrl", landmarkInfo[position]);
            landmarkDialog.setArguments(bundle);
            landmarkDialog.show(getSupportFragmentManager(), "Landmark Dialog");
        });
    }
}