package com.example.androidfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.common.reflect.TypeToken;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;

public class NameAndTeam extends AppCompatActivity {
    FirebaseFirestore db;
    RadioGroup radioGroup;
    Button viewRed;
    TextView players;
    TextView courtDateSlot;
    TextView redPlayerNeeds;
    TextView bluePlayerNeeds;
    Button viewBlue;
    Button confirmButton;
    EditText enterName;
    String nameEntered = "";
    String teamColor = "";
    String courtLength="";
    String courtNumber="";
    String slotTime="";
    String officialRedTeam = "";
    String officialBlueTeam = "";
    String getRedTeam = "";
    String backGround = "";
    String date = "";
    ArrayList<String>enteredNames = new ArrayList<>();
    ArrayList<String>redTeam = new ArrayList<>();
    ArrayList<SavedData> dataEntered = new ArrayList<>();
    Map<String, Object> docSnap;
    int redTimesClicked = 0;
    String playerLimit = "";
    public static final String NAME_KEY = "names";
    public static final String TEAM_KEY = "team";
    public static final String NUMBER_KEY = "number";
    Boolean successfullyEntered = false;
    Boolean isClicked = false;
    int arrayListIndex = -1;
    int limit;
    int redNeed;
    int blueNeed;
    int blueTeamCount = 0;
    int redTeamCount = 0;
    Map<String,Object> data;
    ConstraintLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_and_team);
        db = FirebaseFirestore.getInstance();
        data = new HashMap<String, Object>();
        radioGroup = findViewById(R.id.id_teamColor);
        viewRed = findViewById(R.id.id_viewRed);
        viewBlue = findViewById(R.id.id_viewBlue);
        enterName = findViewById(R.id.id_enterName);
        confirmButton = findViewById(R.id.id_confirm);
        courtDateSlot = findViewById(R.id.id_courtAndDate);
        players = findViewById(R.id.id_playas);
        redPlayerNeeds = findViewById(R.id.id_red_players);
        bluePlayerNeeds = findViewById(R.id.id_blue_players);
        courtLength = getIntent().getStringExtra("Length");
        courtNumber = getIntent().getStringExtra("Court");
        slotTime = getIntent().getStringExtra("Slot");
        date = getIntent().getStringExtra("Date");
        layout = findViewById(R.id.id_conlay);
        backGround = getIntent().getStringExtra("BACK");
        layout.setBackgroundColor(Color.parseColor(backGround));
        playerLimit = getIntent().getStringExtra("Limit");
        limit = Integer.parseInt(playerLimit);
        courtDateSlot.setText(""+courtLength+"\n"+courtNumber+"\n"+date+"\n"+slotTime);

        Log.d("TAG", ""+courtLength+" "+courtNumber+" "+date+" "+slotTime);
        loadList();

        for(int i = 0 ; i < dataEntered.size(); i++){
            if((dataEntered.get(i).getcLength().equals(courtLength) && dataEntered.get(i).getNumber().equals(courtNumber))
                    && (dataEntered.get(i).getDateChosen().equals(date) && dataEntered.get(i).getSlotChosen().equals(slotTime))){
                arrayListIndex = i;
                redTeamCount = dataEntered.get(i).getRedTeamCount();
                blueTeamCount = dataEntered.get(i).getBlueTeamCount();
            }

        }
        if(arrayListIndex == -1){
            dataEntered.add(new SavedData(courtNumber, courtLength, date, slotTime, 0, 0, limit));
            arrayListIndex = dataEntered.size()-1;
            redTeamCount = 0;
            blueTeamCount = 0;
        }
        Log.d("TAG", "RED TEAM: "+redTeamCount);
        Log.d("TAG", "BLUE TEAM: "+blueTeamCount);

        redNeed = limit - redTeamCount;
        if(redNeed == 0){
            redPlayerNeeds.setText("Team is filled");
        }
        else if (redNeed == 1){
            redPlayerNeeds.setText("Needs "+redNeed+" player");
        }
        else {
            redPlayerNeeds.setText("Needs " + redNeed + " players");
        }
        redPlayerNeeds.setTextColor(Color.parseColor("#C4BF0303"));

        blueNeed = limit - blueTeamCount;
        if(blueNeed == 0){
            bluePlayerNeeds.setText("Team is filled");
        }
        else if(blueNeed == 1){
            bluePlayerNeeds.setText("Needs "+blueNeed+" player");
        }
        else {
            bluePlayerNeeds.setText("Needs " + blueNeed + " players");
        }
        bluePlayerNeeds.setTextColor(Color.BLUE);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.id_red){
                    teamColor = "Red";
                    enterName.setTextColor(Color.parseColor("#C4BF0303"));
                    isClicked = true;

                }
                else if (checkedId == R.id.id_blue){
                    teamColor = "Blue";
                    enterName.setTextColor(Color.BLUE);
                    isClicked = true;
                }
            }
        });
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameEntered = enterName.getText().toString();
                int spaceIndex = nameEntered.indexOf(" ");
                if (spaceIndex == -1) {
                    Toast.makeText(NameAndTeam.this, "Invalid entry! Please type your first and last name", Toast.LENGTH_SHORT).show();
                }
                else if (!isClicked){
                    Toast.makeText(NameAndTeam.this, "Invalid entry! Please select a team", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (teamColor.equals("Red") && redTeamCount < limit) {
                        Toast.makeText(NameAndTeam.this, nameEntered+" is officially on the red team", Toast.LENGTH_SHORT).show();
                        redTeamCount++;
                        data.put(NAME_KEY, nameEntered);
                        data.put(TEAM_KEY, teamColor);
                        data.put(NUMBER_KEY, redTeamCount);
                        successfullyEntered = true;
                        dataEntered.set(arrayListIndex, new SavedData(courtNumber, courtLength, date, slotTime, redTeamCount, blueTeamCount, limit));
                        redNeed = limit - redTeamCount;
                        if(redNeed == 0){
                            redPlayerNeeds.setText("Team is filled");
                        }
                        else if (redNeed == 1){
                            redPlayerNeeds.setText("Needs "+redNeed+" player");
                        }
                        else {
                            redPlayerNeeds.setText("Needs " + redNeed + " players");
                        }
                        redPlayerNeeds.setTextColor(Color.parseColor("#C4BF0303"));
                        saveList();
                    } else if (teamColor.equals("Blue") && blueTeamCount < limit) {
                        Toast.makeText(NameAndTeam.this, nameEntered+" is officially on the blue team", Toast.LENGTH_SHORT).show();
                        blueTeamCount++;
                        data.put(NAME_KEY, nameEntered);
                        data.put(TEAM_KEY, teamColor);
                        data.put(NUMBER_KEY, blueTeamCount);
                        successfullyEntered = true;
                        dataEntered.set(arrayListIndex, new SavedData(courtNumber, courtLength, date, slotTime, redTeamCount, blueTeamCount, limit));
                        blueNeed = limit - blueTeamCount;
                        if(blueNeed == 0){
                            bluePlayerNeeds.setText("Team is filled");
                        }
                        else if(blueNeed == 1){
                            bluePlayerNeeds.setText("Needs "+blueNeed+" player");
                        }
                        else {
                            bluePlayerNeeds.setText("Needs " + blueNeed + " players");
                        }
                        bluePlayerNeeds.setTextColor(Color.BLUE);
                        saveList();
                    } else if (redTeamCount == limit && blueTeamCount == limit) {
                        Toast.makeText(NameAndTeam.this, "Sorry! The court is completely booked!", Toast.LENGTH_SHORT).show();
                    } else if (redTeamCount == limit) {
                        redPlayerNeeds.setText("Team is filled");
                        Toast.makeText(NameAndTeam.this, "There are still open spots on the Blue Team!", Toast.LENGTH_SHORT).show();
                    } else if (blueTeamCount == limit) {
                        bluePlayerNeeds.setText("Team is filled");
                        Toast.makeText(NameAndTeam.this, "There are still open spots on the Red Team!", Toast.LENGTH_SHORT).show();
                    }
                    if(successfullyEntered) {
                        db.collection("" + courtLength).document("" + courtNumber).collection("" + date + ": " + slotTime)
                                .add(data)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Log.d("TAG", "DocumentSnapshot successfully uploaded");
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d("TAG", "Error adding document", e);
                                    }
                                });
                        successfullyEntered = false;
                    }
                }
            }
        });

        viewRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("" + courtLength).document("" + courtNumber).collection(""+date+": " + slotTime)
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot snapshot : task.getResult()) {
                                            if (snapshot.getData().get(TEAM_KEY).equals("Red")) {
                                                docSnap = snapshot.getData();
                                                officialRedTeam += (String) snapshot.getData().get(NAME_KEY) + "\n";
                                            }
                                        }
                                        if(officialRedTeam.equals("")){
                                            Toast.makeText(NameAndTeam.this, "No One is on the Red Team", Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            players.setVisibility(View.VISIBLE);
                                            players.setText(officialRedTeam);
                                            players.setTextColor(Color.parseColor("#C4BF0303"));
                                            //players.setBackgroundColor(Color.RED);

                                            officialRedTeam = "";
                                        }

                                    } else {
                                        Log.d("TAG", "Error getting documents: ", task.getException());
                                    }
                                }
                            });

            }
        });

        viewBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection(""+courtLength).document(""+courtNumber).collection(""+date+": "+slotTime)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot snapshot : task.getResult()) {
                                        if (snapshot.getData().get(TEAM_KEY).equals("Blue")) {
                                            officialBlueTeam += (String) snapshot.getData().get(NAME_KEY) + "\n";
                                        }
                                    }
                                    if(officialBlueTeam.equals("")){
                                        Toast.makeText(NameAndTeam.this, "No One is on the Blue Team", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        players.setText(officialBlueTeam);
                                        players.setTextColor(Color.BLUE);
                                        //players.setBackgroundColor(Color.BLUE);
                                        officialBlueTeam = "";
                                    }

                                } else {
                                    //Toast.makeText(NameAndTeam.this, "No One", Toast.LENGTH_SHORT).show();
                                    Log.d("TAG","Error getting documents: ", task.getException());
                                }
                            }

                        });
            }
        });



    }

    public class SavedData{
        private String number;
        private String cLength;
        private String dateChosen;
        private String slotChosen;
        private int blueTeamCount;
        private int redTeamCount;
        private int playerLimit;

        public SavedData(String number, String cLength, String dateChosen, String slotChosen, int redTeamCount, int blueTeamCount, int playerLimit){
            this.number = number;
            this.cLength = cLength;
            this.dateChosen = dateChosen;
            this.slotChosen = slotChosen;
            this.redTeamCount = redTeamCount;
            this.blueTeamCount = blueTeamCount;
        }

        public String getNumber(){
            return number;
        }

        public String getcLength() {
            return cLength;
        }

        public String getDateChosen() {
            return dateChosen;
        }

        public String getSlotChosen() {
            return slotChosen;
        }

        public int getBlueTeamCount() {
            return blueTeamCount;
        }

        public int getRedTeamCount() {
            return redTeamCount;
        }
        public int setRedTeamCount(int newCount){
            redTeamCount = newCount;
            return redTeamCount;
        }
        public int setBlueTeamCount(int newCount){
            blueTeamCount = newCount;
            return blueTeamCount;
        }

        public int getPlayerLimit() {
            return playerLimit;
        }
    }
    private void saveList(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(dataEntered);
        editor.putString("task list", json);
        editor.apply();
    }

    private void loadList() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<SavedData>>() {}.getType();
        dataEntered = gson.fromJson(json, type);
        if (dataEntered == null) {
            dataEntered = new ArrayList<>();
        }
    }
}