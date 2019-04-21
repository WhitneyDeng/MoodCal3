package com.whitney.moodcal3;

import android.support.annotation.NonNull;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.LinkedHashMap;

public class EntryStorage
{
    private LinkedHashMap<String, Entry> entryStorage;
    private int daySinceLastMenstruation;

    //preserved for firebase integration
//    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    public EntryStorage()
    {
        entryStorage = new LinkedHashMap<>();
        daySinceLastMenstruation = 0;

        //this section is preserved for firebase integration
//        mDatabase.child(user.getUid()).child("calendar").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                EntryStorage temp;
//                temp = dataSnapshot.getValue(EntryStorage.class);
//                entryStorage = temp.getEntryStorage();
//                daySinceLastMenstruation = temp.getDaySinceLastMenstruation();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//            }
//        });
    }

    public LinkedHashMap<String, Entry> getEntryStorage()
    {
        return entryStorage;
    }

    public int getDaySinceLastMenstruation()
    {
        return daySinceLastMenstruation;
    }

    //sets daySinceLastMenstruation to 0
    public void firstMenstruationDay()
    {
        daySinceLastMenstruation = 0;
    }
}
