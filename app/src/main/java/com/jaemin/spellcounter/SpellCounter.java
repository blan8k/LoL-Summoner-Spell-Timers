package com.jaemin.spellcounter;

import android.content.res.Configuration;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SpellCounter extends AppCompatActivity {

    //ExpandableListView expandableListView;
    private static long flash = 300000;
    private static long ignite = 180000;
    private static long teleport = 420000;
    private static long exhaust = 210000;
    private static long heal = 240000;
    private static long ghost = 210000;
    private static long barrier = 180000;
    private static long smite = 90000;
    private static long cleanse = 210000;
    private static long clarity = 240000;
    private static long mark = 80000;
    private TextView topSum1;
    private TextView topSum2;
    private TextView midSum1;
    private TextView midSum2;
    private TextView adcSum1;
    private TextView adcSum2;
    private TextView jgSum1;
    private TextView jgSum2;
    private TextView supSum1;
    private TextView supSum2;
    private Button topRestart1;
    private Button topRestart2;
    private Button midRestart1;
    private Button midRestart2;
    private Button adcRestart1;
    private Button adcRestart2;
    private Button jgRestart1;
    private Button jgRestart2;
    private Button supRestart1;
    private Button supRestart2;
    private boolean topRunning1;
    private boolean topRunning2;
    private boolean midRunning1;
    private boolean midRunning2;
    private boolean adcRunning1;
    private boolean adcRunning2;
    private boolean jgRunning1;
    private boolean jgRunning2;
    private boolean supRunning1;
    private boolean supRunning2;
    private long topTimeLeft1 = 0;
    private long topTimeLeft2 = 0;
    private long midTimeLeft1 = 0;
    private long midTimeLeft2 = 0;
    private long adcTimeLeft1 = 0;
    private long adcTimeLeft2 = 0;
    private long jgTimeLeft1 = 0;
    private long jgTimeLeft2 = 0;
    private long supTimeLeft1 = 0;
    private long supTimeLeft2 = 0;
    int topFirstLast = 1;
    int jgFirstLast = 1;
    int midFirstLast = 1;
    int adcFirstLast = 1;
    int supFirstLast = 1;
    private CountDownTimer top1;
    private CountDownTimer top2;
    private CountDownTimer mid1;
    private CountDownTimer mid2;
    private CountDownTimer adc1;
    private CountDownTimer adc2;
    private CountDownTimer jg1;
    private CountDownTimer jg2;
    private CountDownTimer sup1;
    private CountDownTimer sup2;
    private long originalTop1 = 0;
    private long originalTop2 = 0;
    private long originalMid1 = 0;
    private long originalMid2 = 0;
    private long originalADC1 = 0;
    private long originalADC2 = 0;
    private long originalJG1 = 0;
    private long originalJG2 = 0;
    private long originalSup1 = 0;
    private long originalSup2 = 0;
    private TextView topDf;
    private TextView jgDf;
    private TextView midDf;
    private TextView adcDf;
    private TextView supDf;
    static String TAG = "SpellCounter";
    private Switch topcdr1;
    private Switch topcdr2;
    private Switch jgcdr1;
    private Switch jgcdr2;
    private Switch midcdr1;
    private Switch midcdr2;
    private Switch adccdr1;
    private Switch adccdr2;
    private Switch supcdr1;
    private Switch supcdr2;
    private int boots = 12;
    private int cosmicInsight = 18;
    //private int abilityHaste = 0;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell_counter);
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            final ListView topSum = findViewById(R.id.topSum);;
            final ListView jgSum = findViewById(R.id.jgSum);
            final ListView midSum = findViewById(R.id.midSum);
            final ListView adcSum = findViewById(R.id.adcSum);
            final ListView supSum = findViewById(R.id.supSum);
            topcdr1 = findViewById(R.id.topBoot);
            topcdr2 = findViewById(R.id.topRune);
            jgcdr1 = findViewById(R.id.jgBoot);
            jgcdr2 = findViewById(R.id.jgRune);
            midcdr1 = findViewById(R.id.midBoot);
            midcdr2 = findViewById(R.id.midRune);
            adccdr1 = findViewById(R.id.adcBoot);
            adccdr2 = findViewById(R.id.adcRune);
            supcdr1 = findViewById(R.id.supBoot);
            supcdr2 = findViewById(R.id.supRune);
            topDf = findViewById(R.id.topDF);
            jgDf = findViewById(R.id.jgDF);
            midDf = findViewById(R.id.midDF);
            adcDf = findViewById(R.id.adcDF);
            supDf = findViewById(R.id.supDF);
            ArrayList<String> summonerSpells = new ArrayList<>();
            summonerSpells.add("Flash");
            summonerSpells.add("Ignite");
            summonerSpells.add("Teleport");
            summonerSpells.add("Exhaust");
            summonerSpells.add("Heal");
            summonerSpells.add("Ghost");
            summonerSpells.add("Barrier");
            summonerSpells.add("Smite");
            summonerSpells.add("Cleanse");
            summonerSpells.add("Clarity");
            summonerSpells.add("Mark");
            ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,summonerSpells);
            topSum.setAdapter(adapter);
            jgSum.setAdapter(adapter);
            midSum.setAdapter(adapter);
            adcSum.setAdapter(adapter);
            supSum.setAdapter(adapter);
            Log.d(TAG, "it works");
            supSum.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String spell = (String) supSum.getItemAtPosition(position);
                    double abilityHaste = 0;
                    if(supcdr1.isChecked()){
                        abilityHaste += 12;
                    }
                    if(supcdr2.isChecked()){
                        abilityHaste += 18;
                    }
                    if(supFirstLast % 2 == 1){
                        supFirstLast++;
                        supDf.setText("F");
                        if(spell.equals("Flash")){
                            supTimeLeft1 = (long) (flash *(100/(100+abilityHaste)));
                            originalSup1 = (long) (flash *(100/(100+abilityHaste)));
                        }else if(spell.equals("Ignite")){
                            supTimeLeft1 = (long) (ignite *(100/(100+abilityHaste)));
                            originalSup1 = (long) (ignite *(100/(100+abilityHaste)));
                        }else if(spell.equals("Teleport")){
                            supTimeLeft1 = (long) (teleport *(100/(100+abilityHaste)));
                            originalSup1 = (long) (teleport *(100/(100+abilityHaste)));
                        }else if(spell.equals("Exhaust")){
                            supTimeLeft1 = (long) (exhaust *(100/(100+abilityHaste)));
                            originalSup1= (long) (exhaust *(100/(100+abilityHaste)));
                        }else if(spell.equals("Heal")){
                            supTimeLeft1 = (long) (heal *(100/(100+abilityHaste)));
                            originalSup1 = (long) (heal *(100/(100+abilityHaste)));
                        }else if(spell.equals("Ghost")){
                            supTimeLeft1 = (long) (ghost *(100/(100+abilityHaste)));
                            originalSup1 = (long) (ghost *(100/(100+abilityHaste)));
                        }else if(spell.equals("Barrier")){
                            supTimeLeft1 = (long) (barrier *(100/(100+abilityHaste)));
                            originalSup1 = (long) (barrier *(100/(100+abilityHaste)));
                        }else if(spell.equals("Smite")){
                            supTimeLeft1 = (long) (smite *(100/(100+abilityHaste)));
                            originalSup1 = (long) (smite *(100/(100+abilityHaste)));
                        }else if(spell.equals("Cleanse")){
                            supTimeLeft1 = (long) (cleanse *(100/(100+abilityHaste)));
                            originalSup1 = (long) (cleanse *(100/(100+abilityHaste)));
                        }else if(spell.equals("Clarity")){
                            supTimeLeft1 = (long) (clarity *(100/(100+abilityHaste)));
                            originalSup1 = (long) (clarity *(100/(100+abilityHaste)));
                        }else{
                            supTimeLeft1 = (long) (mark *(100/(100+abilityHaste)));
                            originalSup1 = (long) (mark *(100/(100+abilityHaste)));
                        }
                    }else{
                        supFirstLast++;
                        supDf.setText("D");
                        if(spell.equals("Flash")){
                            supTimeLeft2 = (long) (flash *(100/(100+abilityHaste)));
                            originalSup2 = (long) (flash *(100/(100+abilityHaste)));
                        }else if(spell.equals("Ignite")){
                            supTimeLeft2 = (long) (ignite *(100/(100+abilityHaste)));
                            originalSup2 = (long) (ignite *(100/(100+abilityHaste)));
                        }else if(spell.equals("Teleport")){
                            supTimeLeft2 = (long) (teleport *(100/(100+abilityHaste)));
                            originalSup2 = (long) (teleport *(100/(100+abilityHaste)));
                        }else if(spell.equals("Exhaust")){
                            supTimeLeft2 = (long) (exhaust *(100/(100+abilityHaste)));
                            originalSup2 = (long) (exhaust *(100/(100+abilityHaste)));
                        }else if(spell.equals("Heal")){
                            supTimeLeft2 = (long) (heal *(100/(100+abilityHaste)));
                            originalSup2 = (long) (heal *(100/(100+abilityHaste)));
                        }else if(spell.equals("Ghost")){
                            supTimeLeft2 = (long) (ghost *(100/(100+abilityHaste)));
                            originalSup2 = (long) (ghost *(100/(100+abilityHaste)));
                        }else if(spell.equals("Barrier")){
                            supTimeLeft2 = (long) (barrier *(100/(100+abilityHaste)));
                            originalSup2 = (long) (barrier *(100/(100+abilityHaste)));
                        }else if(spell.equals("Smite")){
                            supTimeLeft2 = (long) (smite *(100/(100+abilityHaste)));
                            originalSup2 = (long) (smite *(100/(100+abilityHaste)));
                        }else if(spell.equals("Cleanse")){
                            supTimeLeft2 = (long) (cleanse *(100/(100+abilityHaste)));
                            originalSup2 = (long) (cleanse *(100/(100+abilityHaste)));
                        }else if(spell.equals("Clarity")){
                            supTimeLeft2 = (long) (clarity *(100/(100+abilityHaste)));
                            originalSup2 = (long) (clarity *(100/(100+abilityHaste)));
                        }else{
                            supTimeLeft2 = (long) (mark *(100/(100+abilityHaste)));
                            originalSup2 = (long) (mark *(100/(100+abilityHaste)));
                        }

                    }
                }
            });
            adcSum.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String spell = (String) adcSum.getItemAtPosition(position);
                    double abilityHaste = 0;
                    if(adccdr1.isChecked()){
                        abilityHaste += 12;
                    }
                    if(adccdr2.isChecked()){
                        abilityHaste += 18;
                    }
                    if(adcFirstLast % 2 == 1){
                        adcFirstLast++;
                        adcDf.setText("F");
                        if(spell.equals("Flash")){
                            adcTimeLeft1 = (long) (flash *(100/(100+abilityHaste)));
                            originalADC1 = (long) (flash *(100/(100+abilityHaste)));
                        }else if(spell.equals("Ignite")){
                            adcTimeLeft1 = (long) (ignite *(100/(100+abilityHaste)));
                            originalADC1 = (long) (ignite *(100/(100+abilityHaste)));
                        }else if(spell.equals("Teleport")){
                            adcTimeLeft1 = (long) (teleport *(100/(100+abilityHaste)));
                            originalADC1 = (long)(teleport *(100/(100+abilityHaste)));
                        }else if(spell.equals("Exhaust")){
                            adcTimeLeft1 = (long) (exhaust *(100/(100+abilityHaste)));
                            originalADC1= (long) (exhaust *(100/(100+abilityHaste)));
                        }else if(spell.equals("Heal")){
                            adcTimeLeft1 = (long) (heal *(100/(100+abilityHaste)));
                            originalADC1 = (long) (heal *(100/(100+abilityHaste)));
                        }else if(spell.equals("Ghost")){
                            adcTimeLeft1 = (long) (ghost *(100/(100+abilityHaste)));
                            originalADC1 = (long) (ghost *(100/(100+abilityHaste)));
                        }else if(spell.equals("Barrier")){
                            adcTimeLeft1 = (long) (barrier *(100/(100+abilityHaste)));
                            originalADC1 = (long) (barrier *(100/(100+abilityHaste)));
                        }else if(spell.equals("Smite")){
                            adcTimeLeft1 = (long) (smite *(100/(100+abilityHaste)));
                            originalADC1 = (long) (smite *(100/(100+abilityHaste)));
                        }else if(spell.equals("Cleanse")){
                            adcTimeLeft1 = (long) (cleanse *(100/(100+abilityHaste)));
                            originalADC1 = (long) (cleanse *(100/(100+abilityHaste)));
                        }else if(spell.equals("Clarity")){
                            adcTimeLeft1 = (long) (clarity *(100/(100+abilityHaste)));
                            originalADC1 = (long) (clarity *(100/(100+abilityHaste)));
                        }else{
                            adcTimeLeft1 = (long) (mark *(100/(100+abilityHaste)));
                            originalADC1 = (long) (mark *(100/(100+abilityHaste)));
                        }
                    }else{
                        adcFirstLast++;
                        adcDf.setText("D");
                        if(spell.equals("Flash")){
                            adcTimeLeft2 = (long) (flash *(100/(100+abilityHaste)));
                            originalADC2 = (long) (flash *(100/(100+abilityHaste)));
                        }else if(spell.equals("Ignite")){
                            adcTimeLeft2 = (long) (ignite *(100/(100+abilityHaste)));
                            originalADC2 = (long) (ignite *(100/(100+abilityHaste)));
                        }else if(spell.equals("Teleport")){
                            adcTimeLeft2 = (long) (teleport *(100/(100+abilityHaste)));
                            originalADC2 = (long) (teleport *(100/(100+abilityHaste)));
                        }else if(spell.equals("Exhaust")){
                            adcTimeLeft2 = (long) (exhaust *(100/(100+abilityHaste)));
                            originalADC2 = (long) (exhaust *(100/(100+abilityHaste)));
                        }else if(spell.equals("Heal")){
                            adcTimeLeft2 = (long) (heal *(100/(100+abilityHaste)));
                            originalADC2 = (long) (heal *(100/(100+abilityHaste)));
                        }else if(spell.equals("Ghost")){
                            adcTimeLeft2 = (long) (ghost *(100/(100+abilityHaste)));
                            originalADC2 = (long) (ghost *(100/(100+abilityHaste)));
                        }else if(spell.equals("Barrier")){
                            adcTimeLeft2 = (long) (barrier *(100/(100+abilityHaste)));
                            originalADC2 = (long) (barrier *(100/(100+abilityHaste)));
                        }else if(spell.equals("Smite")){
                            adcTimeLeft2 = (long) (smite *(100/(100+abilityHaste)));
                            originalADC2 = (long) (smite *(100/(100+abilityHaste)));
                        }else if(spell.equals("Cleanse")){
                            adcTimeLeft2 = (long)(cleanse *(100/(100+abilityHaste)));
                            originalADC2 = (long) (cleanse *(100/(100+abilityHaste)));
                        }else if(spell.equals("Clarity")){
                            adcTimeLeft2 = (long) (clarity *(100/(100+abilityHaste)));
                            originalADC2 = (long) (clarity *(100/(100+abilityHaste)));
                        }else{
                            adcTimeLeft2 = (long) (mark *(100/(100+abilityHaste)));
                            originalADC2 = (long) (mark *(100/(100+abilityHaste)));
                        }

                    }
                }
            });
            jgSum.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String spell = (String) jgSum.getItemAtPosition(position);
                    double abilityHaste = 0;
                    if(jgcdr1.isChecked()){
                        abilityHaste += 12;
                    }
                    if(jgcdr2.isChecked()){
                        abilityHaste +=18;
                    }
                    if(jgFirstLast % 2 == 1){
                        jgFirstLast++;
                        jgDf.setText("F");
                        if(spell.equals("Flash")){
                            jgTimeLeft1 = (long) (flash *(100/(100+abilityHaste)));
                            originalJG1 = (long) (flash *(100/(100+abilityHaste)));
                        }else if(spell.equals("Ignite")){
                            jgTimeLeft1 = (long) (ignite *(100/(100+abilityHaste)));
                            originalJG1 = (long) (ignite *(100/(100+abilityHaste)));
                        }else if(spell.equals("Teleport")){
                            jgTimeLeft1 = (long) (teleport *(100/(100+abilityHaste)));
                            originalJG1 = (long) (teleport *(100/(100+abilityHaste)));
                        }else if(spell.equals("Exhaust")){
                            jgTimeLeft1 = (long) (exhaust *(100/(100+abilityHaste)));
                            originalJG1= (long) (exhaust *(100/(100+abilityHaste)));
                        }else if(spell.equals("Heal")){
                            jgTimeLeft1 = (long) (heal *(100/(100+abilityHaste)));
                            originalJG1 = (long) (heal *(100/(100+abilityHaste)));
                        }else if(spell.equals("Ghost")){
                            jgTimeLeft1 = (long) (ghost *(100/(100+abilityHaste)));
                            originalJG1 = (long) (ghost *(100/(100+abilityHaste)));
                        }else if(spell.equals("Barrier")){
                            jgTimeLeft1 = (long) (barrier *(100/(100+abilityHaste)));
                            originalJG1 = (long) (barrier *(100/(100+abilityHaste)));
                        }else if(spell.equals("Smite")){
                            jgTimeLeft1 = (long) (smite *(100/(100+abilityHaste)));
                            originalJG1 = (long) (smite *(100/(100+abilityHaste)));
                        }else if(spell.equals("Cleanse")){
                            jgTimeLeft1 = (long) (cleanse *(100/(100+abilityHaste)));
                            originalJG1 = (long) (cleanse *(100/(100+abilityHaste)));
                        }else if(spell.equals("Clarity")){
                            jgTimeLeft1 = (long) (clarity *(100/(100+abilityHaste)));
                            originalJG1 = (long) (clarity *(100/(100+abilityHaste)));
                        }else{
                            jgTimeLeft1 = (long)(mark *(100/(100+abilityHaste)));
                            originalJG1 = (long) (mark *(100/(100+abilityHaste)));
                        }
                    }else{
                        jgFirstLast++;
                        jgDf.setText("D");
                        if(spell.equals("Flash")){
                            jgTimeLeft2 = (long) (flash *(100/(100+abilityHaste)));
                            originalJG2 = (long) (flash *(100/(100+abilityHaste)));
                        }else if(spell.equals("Ignite")){
                            jgTimeLeft2 = (long) (ignite *(100/(100+abilityHaste)));
                            originalJG2 = (long) (ignite *(100/(100+abilityHaste)));
                        }else if(spell.equals("Teleport")){
                            jgTimeLeft2 = (long) (teleport *(100/(100+abilityHaste)));
                            originalJG2 = (long) (teleport *(100/(100+abilityHaste)));
                        }else if(spell.equals("Exhaust")){
                            jgTimeLeft2 = (long) (exhaust *(100/(100+abilityHaste)));
                            originalJG2 = (long) (exhaust *(100/(100+abilityHaste)));
                        }else if(spell.equals("Heal")){
                            jgTimeLeft2 = (long) (heal *(100/(100+abilityHaste)));
                            originalJG2 = (long) (heal *(100/(100+abilityHaste)));
                        }else if(spell.equals("Ghost")){
                            jgTimeLeft2 = (long) (ghost *(100/(100+abilityHaste)));
                            originalJG2 = (long) (ghost *(100/(100+abilityHaste)));
                        }else if(spell.equals("Barrier")){
                            jgTimeLeft2 = (long) (barrier *(100/(100+abilityHaste)));
                            originalJG2 = (long) (barrier *(100/(100+abilityHaste)));
                        }else if(spell.equals("Smite")){
                            jgTimeLeft2 = (long) (smite *(100/(100+abilityHaste)));
                            originalJG2 = (long)(smite *(100/(100+abilityHaste)));
                        }else if(spell.equals("Cleanse")){
                            jgTimeLeft2 = (long) (cleanse *(100/(100+abilityHaste)));
                            originalJG2 = (long) (cleanse *(100/(100+abilityHaste)));
                        }else if(spell.equals("Clarity")){
                            jgTimeLeft2 = (long) (clarity *(100/(100+abilityHaste)));
                            originalJG2 = (long) (clarity *(100/(100+abilityHaste)));
                        }else{
                            jgTimeLeft2 = (long) (mark *(100/(100+abilityHaste)));
                            originalJG2 = (long) (mark *(100/(100+abilityHaste)));
                        }

                    }
                }
            });
            midSum.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String spell = (String) midSum.getItemAtPosition(position);
                    double abilityHaste = 0;
                    if(midcdr1.isChecked()){
                        abilityHaste += 12;
                    }
                    if(midcdr2.isChecked()){
                        abilityHaste += 18;
                    }
                    if(midFirstLast % 2 == 1){
                        midFirstLast++;
                        midDf.setText("F");
                        if(spell.equals("Flash")){
                            midTimeLeft1 = (long) (flash *(100/(100+abilityHaste)));
                            originalMid1 = (long) (flash *(100/(100+abilityHaste)));
                        }else if(spell.equals("Ignite")){
                            midTimeLeft1 = (long) (ignite *(100/(100+abilityHaste)));
                            originalMid1 = (long) (ignite *(100/(100+abilityHaste)));
                        }else if(spell.equals("Teleport")){
                            midTimeLeft1 = (long) (teleport *(100/(100+abilityHaste)));
                            originalMid1 = (long) (teleport *(100/(100+abilityHaste)));
                        }else if(spell.equals("Exhaust")){
                            midTimeLeft1 = (long) (exhaust *(100/(100+abilityHaste)));
                            originalMid1= (long) (exhaust *(100/(100+abilityHaste)));
                        }else if(spell.equals("Heal")){
                            midTimeLeft1 = (long) (heal *(100/(100+abilityHaste)));
                            originalMid1 = (long) (heal *(100/(100+abilityHaste)));
                        }else if(spell.equals("Ghost")){
                            midTimeLeft1 = (long) (ghost *(100/(100+abilityHaste)));
                            originalMid1 = (long) (ghost *(100/(100+abilityHaste)));
                        }else if(spell.equals("Barrier")){
                            midTimeLeft1 = (long) (barrier *(100/(100+abilityHaste)));
                            originalMid1 = (long) (barrier *(100/(100+abilityHaste)));
                        }else if(spell.equals("Smite")){
                            midTimeLeft1 = (long) (smite *(100/(100+abilityHaste)));
                            originalMid1 = (long) (smite *(100/(100+abilityHaste)));
                        }else if(spell.equals("Cleanse")){
                            midTimeLeft1 = (long) (cleanse *(100/(100+abilityHaste)));
                            originalMid1 = (long) (cleanse *(100/(100+abilityHaste)));
                        }else if(spell.equals("Clarity")){
                            midTimeLeft1 = (long) (clarity *(100/(100+abilityHaste)));
                            originalMid1 = (long) (clarity *(100/(100+abilityHaste)));
                        }else{
                            midTimeLeft1 = (long) (mark *(100/(100+abilityHaste)));
                            originalMid1 = (long) (mark *(100/(100+abilityHaste)));
                        }
                    }else{
                        midFirstLast++;
                        midDf.setText("D");
                        if(spell.equals("Flash")){
                            midTimeLeft2 = (long) (flash *(100/(100+abilityHaste)));
                            originalMid2 = (long) (flash *(100/(100+abilityHaste)));
                        }else if(spell.equals("Ignite")){
                            midTimeLeft2 = (long) (ignite *(100/(100+abilityHaste)));
                            originalMid2 = (long) (ignite *(100/(100+abilityHaste)));
                        }else if(spell.equals("Teleport")){
                            midTimeLeft2 = (long) (teleport *(100/(100+abilityHaste)));
                            originalTop2 = (long) (teleport *(100/(100+abilityHaste)));
                        }else if(spell.equals("Exhaust")){
                            midTimeLeft2 = (long) (exhaust *(100/(100+abilityHaste)));
                            originalMid2 = (long) (exhaust *(100/(100+abilityHaste)));
                        }else if(spell.equals("Heal")){
                            midTimeLeft2 = (long) (heal *(100/(100+abilityHaste)));
                            originalMid2 = (long) (heal *(100/(100+abilityHaste)));
                        }else if(spell.equals("Ghost")){
                            midTimeLeft2 = (long) (ghost *(100/(100+abilityHaste)));
                            originalMid2 = (long) (ghost *(100/(100+abilityHaste)));
                        }else if(spell.equals("Barrier")){
                            midTimeLeft2 = (long) (barrier *(100/(100+abilityHaste)));
                            originalMid2 = (long) (barrier *(100/(100+abilityHaste)));
                        }else if(spell.equals("Smite")){
                            midTimeLeft2 = (long) (smite *(100/(100+abilityHaste)));
                            originalMid2 = (long) (smite *(100/(100+abilityHaste)));
                        }else if(spell.equals("Cleanse")){
                            midTimeLeft2 = (long) (cleanse *(100/(100+abilityHaste)));
                            originalMid2 = (long) (cleanse *(100/(100+abilityHaste)));
                        }else if(spell.equals("Clarity")){
                            midTimeLeft2 = (long) (clarity *(100/(100+abilityHaste)));
                            originalMid2 = (long)(clarity *(100/(100+abilityHaste)));
                        }else{
                            midTimeLeft2 = (long) (mark *(100/(100+abilityHaste)));
                            originalMid2 = (long) (mark *(100/(100+abilityHaste)));
                        }

                    }
                }
            });
            topSum.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String spell = (String) topSum.getItemAtPosition(position);
                    double abilityHaste = 0;
                    if(topcdr1.isChecked()){
                        abilityHaste += 12;
                    }
                    if(topcdr2.isChecked()){
                        abilityHaste += 18;
                    }
                    if(topFirstLast % 2 == 1){
                        topFirstLast++;
                        topDf.setText("F");
                        if(spell.equals("Flash")){
                            topTimeLeft1 = (long) (flash * (100/(100+abilityHaste)));
                            originalTop1 = (long) (flash * (100/(100+abilityHaste)));
                        }else if(spell.equals("Ignite")){
                            topTimeLeft1 = (long) (ignite * (100/(100+abilityHaste)));
                            originalTop1 = (long) (ignite * (100/(100+abilityHaste)));
                        }else if(spell.equals("Teleport")){
                            topTimeLeft1 = (long) (teleport * (100/(100+abilityHaste)));
                            originalTop1 = (long) (teleport * (100/(100+abilityHaste)));
                        }else if(spell.equals("Exhaust")){
                            topTimeLeft1 = (long) (exhaust * (100/(100+abilityHaste)));
                            originalTop1 = (long) (exhaust * ((100/(100+abilityHaste))));
                        }else if(spell.equals("Heal")){
                            topTimeLeft1 = (long) (heal * (100/(100+abilityHaste)));
                            originalTop1 = (long) (heal * (100/(100+abilityHaste)));
                        }else if(spell.equals("Ghost")){
                            topTimeLeft1 = (long) (ghost * (100/(100+abilityHaste)));
                            originalTop1 = (long) (ghost * (100/(100+abilityHaste)));
                        }else if(spell.equals("Barrier")){
                            topTimeLeft1 = (long) (barrier * (100/(100+abilityHaste)));
                            originalTop1 = (long) (barrier * (100/(100+abilityHaste)));
                        }else if(spell.equals("Smite")){
                            topTimeLeft1 = (long) (smite * (100/(100+abilityHaste)));
                            originalTop1 = (long) (smite * (100/(100+abilityHaste)));
                        }else if(spell.equals("Cleanse")){
                            topTimeLeft1 = (long) (cleanse * (100/(100+abilityHaste)));
                            originalTop1 = (long) (cleanse * (100/(100+abilityHaste)));
                        }else if(spell.equals("Clarity")){
                            topTimeLeft1 = (long) (clarity * (100/(100+abilityHaste)));
                            originalTop1 = (long) (clarity * (100/(100+abilityHaste)));
                        }else{
                            topTimeLeft1 = (long) (mark * (100/(100+abilityHaste)));
                            originalTop1 = (long) (mark * (100/(100+abilityHaste)));
                        }
                    }else{
                        topFirstLast++;
                        topDf.setText("D");
                        if(spell.equals("Flash")){
                            topTimeLeft2 = (long) (flash * (100/(100+abilityHaste)));
                            originalTop2 = (long)(flash * (100/(100+abilityHaste)));
                        }else if(spell.equals("Ignite")){
                            topTimeLeft2 = (long) (ignite * (100/(100+abilityHaste)));
                            originalTop2 = (long) (ignite * (100/(100+abilityHaste)));
                        }else if(spell.equals("Teleport")){
                            topTimeLeft2 = (long) (teleport * (100/(100+abilityHaste)));
                            originalTop2 = (long) (teleport * (100/(100+abilityHaste)));
                        }else if(spell.equals("Exhaust")){
                            topTimeLeft2 = (long) (exhaust * (100/(100+abilityHaste)));
                            originalTop2 = (long) ( exhaust * (100/(100+abilityHaste)));
                        }else if(spell.equals("Heal")){
                            topTimeLeft2 = (long) (heal * (100/(100+abilityHaste)));
                            originalTop2 = (long) (heal * (100/(100+abilityHaste)));
                        }else if(spell.equals("Ghost")){
                            topTimeLeft2 =  (long) (ghost * (100/(100+abilityHaste)));
                            originalTop2 = (long) (ghost * (100/(100+abilityHaste)));
                        }else if(spell.equals("Barrier")){
                            topTimeLeft2 = (long) (barrier * (100/(100+abilityHaste)));
                            originalTop2 =  (long) (barrier * (100/(100+abilityHaste)));
                        }else if(spell.equals("Smite")){
                            topTimeLeft2 = (long) (smite * (100/(100+abilityHaste)));
                            originalTop2 =  (long)(smite * (100/(100+abilityHaste)));
                        }else if(spell.equals("Cleanse")){
                            topTimeLeft2 = (long)(cleanse * (100/(100+abilityHaste)));
                            originalTop2 = (long)(cleanse * (100/(100+abilityHaste)));
                        }else if(spell.equals("Clarity")){
                            topTimeLeft2 = (long) (clarity * (100/(100+abilityHaste)));
                            originalTop2 = (long) (clarity * (100/(100+abilityHaste)));
                        }else{
                            topTimeLeft2 = (long)(mark * (100/(100+abilityHaste)));
                            originalTop2 = (long)(mark * (100/(100+abilityHaste)));
                        }

                    }
                }
            });
        }else if(orientation == Configuration.ORIENTATION_PORTRAIT){
            //In portrait
            Log.d(TAG, "Time left is " + topTimeLeft1);
            topSum1 = findViewById(R.id.topSum1);
            topSum2 = findViewById(R.id.topSum2);
            midSum1 = findViewById(R.id.midSum1);
            midSum2 = findViewById(R.id.midSum2);
            jgSum1 = findViewById(R.id.jgSum1);
            jgSum2 = findViewById(R.id.jgSum2);
            adcSum1 = findViewById(R.id.adcSum1);
            adcSum2 = findViewById(R.id.adcSum2);
            supSum1 = findViewById(R.id.supSum1);
            supSum2 = findViewById(R.id.supSum2);
            topRestart1 = findViewById(R.id.topRestart1);
            topRestart2 = findViewById(R.id.topRestart2);
            midRestart1 = findViewById(R.id.midRestart1);
            midRestart2 = findViewById(R.id.midRestart2);
            jgRestart1 = findViewById(R.id.jgRestart1);
            jgRestart2 = findViewById(R.id.jgRestart2);
            adcRestart1 = findViewById(R.id.adcRestart1);
            adcRestart2 = findViewById(R.id.adcRestart2);
            supRestart1 = findViewById(R.id.supRestart1);
            supRestart2 = findViewById(R.id.supRestart2);
            topRestart1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!topRunning1){
                        startTimer();
                    }else{
                        resetTimer();
                    }
                }
            });
            updateCountDownText();
            topRestart2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!topRunning2){
                        topStartTimer();
                    }else{
                        topResetTimer();
                    }
                }
            });
            topUpdateCountDownText();

            midRestart1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!midRunning1){
                        midStartTimer1();
                    }else{
                        midResetTimer1();
                    }
                }
            });
            midUpdateCountDownText1();

            midRestart2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!midRunning2){
                        midStartTimer2();
                    }else{
                        midResetTimer2();
                    }
                }
            });
            midUpdateCountDownText2();

            jgRestart1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!jgRunning1){
                        jgStartTimer1();
                    }else{
                        jgResetTimer1();
                    }
                }
            });
            jgUpdateCountDownText1();

            jgRestart2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!jgRunning2){
                        jgStartTimer2();
                    }else{
                        jgResetTimer2();
                    }
                }
            });
            jgUpdateCountDownText2();

            adcRestart1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!adcRunning1){
                        adcStartTimer1();
                    }else{
                        adcResetTimer1();
                    }
                }
            });
            adcUpdateCountDownText1();

            adcRestart2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!adcRunning2){
                        adcStartTimer2();
                    }else{
                        adcResetTimer2();
                    }
                }
            });
            adcUpdateCountDownText2();

            supRestart1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!supRunning1){
                        supStartTimer1();
                    }else{
                        supResetTimer1();
                    }
                }
            });
            supUpdateCountDownText1();

            supRestart2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!supRunning2){
                        supStartTimer2();
                    }else{
                        supResetTimer2();
                    }
                }
            });
            supUpdateCountDownText2();
        }
    }

    private void startTimer(){
        top1 = new CountDownTimer(topTimeLeft1, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                topTimeLeft1 = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                topRunning1 = false;
                topTimeLeft1 = originalTop1;
                updateCountDownText();
            }
        }.start();

        topRunning1 = true;
    }

    private void topStartTimer(){
        top2 = new CountDownTimer(topTimeLeft2, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                topTimeLeft2 = millisUntilFinished;
                topUpdateCountDownText();
            }

            @Override
            public void onFinish() {
                topRunning2 = false;
                topTimeLeft2 = originalTop2;
                topUpdateCountDownText();
            }
        }.start();

        topRunning2 = true;
    }

    private void midStartTimer1(){
        mid1 = new CountDownTimer(midTimeLeft1,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                midTimeLeft1 = millisUntilFinished;
                midUpdateCountDownText1();
            }

            @Override
            public void onFinish() {
                midRunning1 = false;
                midTimeLeft1 = originalMid1;
                midUpdateCountDownText1();
            }
        }.start();

        midRunning1 = true;
    }

    private void midStartTimer2(){
        mid2 = new CountDownTimer(midTimeLeft2,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                midTimeLeft2 = millisUntilFinished;
                midUpdateCountDownText2();
            }

            @Override
            public void onFinish() {
                midRunning2 = false;
                midTimeLeft2 = originalMid2;
                midUpdateCountDownText2();
            }
        }.start();

        midRunning2 = true;
    }

    private void jgStartTimer1(){
        jg1 = new CountDownTimer(jgTimeLeft1, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                jgTimeLeft1 = millisUntilFinished;
                jgUpdateCountDownText1();
            }

            @Override
            public void onFinish() {
                jgRunning1 = false;
                jgTimeLeft1 = originalJG1;
                jgUpdateCountDownText1();
            }
        }.start();

        jgRunning1 = true;
    }

    private void jgStartTimer2(){
        jg2 = new CountDownTimer(jgTimeLeft2, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                jgTimeLeft2 = millisUntilFinished;
                jgUpdateCountDownText2();
            }

            @Override
            public void onFinish() {
                jgRunning2 = false;
                jgTimeLeft2 = originalJG2;
                jgUpdateCountDownText2();
            }
        }.start();
        jgRunning2 = true;
    }

    private void adcStartTimer1(){
        adc1 = new CountDownTimer(adcTimeLeft1, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                adcTimeLeft1 = millisUntilFinished;
                adcUpdateCountDownText1();
            }

            @Override
            public void onFinish() {
                adcRunning1 = false;
                adcTimeLeft1 = originalADC1;
                adcUpdateCountDownText1();
            }
        }.start();
        adcRunning1 = true;
    }

    private void adcStartTimer2(){
        adc2 = new CountDownTimer(adcTimeLeft2, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                adcTimeLeft2 = millisUntilFinished;
                adcUpdateCountDownText2();
            }

            @Override
            public void onFinish() {
                adcRunning2 = false;
                adcTimeLeft2 = originalADC2;
                adcUpdateCountDownText2();
            }
        }.start();
        adcRunning2 = true;
    }

    private void supStartTimer1(){
        sup1 = new CountDownTimer(supTimeLeft1, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                supTimeLeft1 = millisUntilFinished;
                supUpdateCountDownText1();
            }

            @Override
            public void onFinish() {
                supRunning1 = false;
                supTimeLeft1 = originalSup1;
                supUpdateCountDownText1();
            }
        }.start();
        supRunning1 = true;
    }

    private void supStartTimer2(){
        sup2 = new CountDownTimer(supTimeLeft2, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                supTimeLeft2 = millisUntilFinished;
                supUpdateCountDownText2();
            }

            @Override
            public void onFinish() {
                supRunning2 = false;
                supTimeLeft2 = originalSup2;
                supUpdateCountDownText2();
            }
        }.start();
        supRunning2 = true;
    }

    private void resetTimer(){
        topRunning1 = false;
        top1.cancel();
        topTimeLeft1 = originalTop1;
        updateCountDownText();
    }

    private void topResetTimer(){
        topRunning2 = false;
        top2.cancel();
        topTimeLeft2 = originalTop2;
        topUpdateCountDownText();
    }

    private void midResetTimer1(){
        midRunning1 = false;
        mid1.cancel();
        midTimeLeft1 = originalMid1;
        midUpdateCountDownText1();
    }

    private void midResetTimer2(){
        midRunning2 = false;
        mid2.cancel();
        midTimeLeft2 = originalMid2;
        midUpdateCountDownText2();
    }

    private void jgResetTimer1(){
        jgRunning1 = false;
        jg1.cancel();
        jgTimeLeft1 = originalJG1;
        jgUpdateCountDownText1();
    }

    private void jgResetTimer2(){
        jgRunning2 = false;
        jg2.cancel();
        jgTimeLeft2 = originalJG2;
        jgUpdateCountDownText2();
    }

    private void adcResetTimer1(){
        adcRunning1 = false;
        adc1.cancel();
        adcTimeLeft1 = originalADC1;
        adcUpdateCountDownText1();
    }

    private void adcResetTimer2(){
        adcRunning2 = false;
        adc2.cancel();
        adcTimeLeft2 = originalADC2;
        adcUpdateCountDownText2();
    }

    private void supResetTimer1(){
        supRunning1 = false;
        sup1.cancel();
        supTimeLeft1 = originalSup1;
        supUpdateCountDownText1();
    }

    private void supResetTimer2(){
        supRunning2 = false;
        sup2.cancel();
        supTimeLeft2 = originalSup2;
        supUpdateCountDownText2();
    }

    private void updateCountDownText(){
        int minutes =  (int) topTimeLeft1/1000/60;
        int seconds =  (int) topTimeLeft1/1000%60;
        String timeLeft = String.format(Locale.getDefault(), "%02d:%02d" ,minutes, seconds);
        topSum1.setText(timeLeft);
    }

    private void topUpdateCountDownText(){
        int minutes = (int) topTimeLeft2/1000/60;
        int seconds = (int) topTimeLeft2/1000%60;
        String timeLeft = String.format(Locale.getDefault(), "%02d:%02d" ,minutes, seconds);
        topSum2.setText(timeLeft);
    }

    private void midUpdateCountDownText1(){
        int minutes = (int) midTimeLeft1/1000/60;
        int seconds = (int) midTimeLeft1/1000%60;
        String timeLeft = String.format(Locale.getDefault(), "%02d:%02d" ,minutes, seconds);
        midSum1.setText(timeLeft);
    }

    private void midUpdateCountDownText2(){
        int minutes = (int) midTimeLeft2/1000/60;
        int seconds = (int) midTimeLeft2/1000%60;
        String timeLeft = String.format(Locale.getDefault(), "%02d:%02d" ,minutes, seconds);
        midSum2.setText(timeLeft);
    }

    private void jgUpdateCountDownText1(){
        int minutes = (int) jgTimeLeft1/1000/60;
        int seconds = (int) jgTimeLeft1/1000%60;
        String timeLeft = String.format(Locale.getDefault(), "%02d:%02d" ,minutes, seconds);
        jgSum1.setText(timeLeft);
    }

    private void jgUpdateCountDownText2(){
        int minutes = (int) jgTimeLeft2/1000/60;
        int seconds = (int) jgTimeLeft2/1000%60;
        String timeLeft = String.format(Locale.getDefault(), "%02d:%02d" ,minutes, seconds);
        jgSum2.setText(timeLeft);
    }

    private void adcUpdateCountDownText1(){
        int minutes = (int) adcTimeLeft1/1000/60;
        int seconds = (int) adcTimeLeft1/1000%60;
        String timeLeft = String.format(Locale.getDefault(), "%02d:%02d" ,minutes, seconds);
        adcSum1.setText(timeLeft);
    }

    private void adcUpdateCountDownText2(){
        int minutes = (int) adcTimeLeft2/1000/60;
        int seconds = (int) adcTimeLeft2/1000%60;
        String timeLeft = String.format(Locale.getDefault(), "%02d:%02d" ,minutes, seconds);
        adcSum2.setText(timeLeft);
    }

    private void supUpdateCountDownText1(){
        int minutes = (int) supTimeLeft1/1000/60;
        int seconds = (int) supTimeLeft1/1000%60;
        String timeLeft = String.format(Locale.getDefault(), "%02d:%02d" ,minutes, seconds);
        supSum1.setText(timeLeft);
    }

    private void supUpdateCountDownText2(){
        int minutes = (int) supTimeLeft2/1000/60;
        int seconds = (int) supTimeLeft2/1000%60;
        String timeLeft = String.format(Locale.getDefault(), "%02d:%02d" ,minutes, seconds);
        supSum2.setText(timeLeft);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("key1", topTimeLeft1);
        outState.putLong("key2", originalTop1);
        outState.putLong("key3", topTimeLeft2);
        outState.putLong("key4", originalTop2);
        outState.putLong("key5", midTimeLeft1);
        outState.putLong("key6", originalMid1);
        outState.putLong("key7", midTimeLeft2);
        outState.putLong("key8", originalMid2);
        outState.putLong("key9", jgTimeLeft1);
        outState.putLong("key10", originalJG1);
        outState.putLong("key11", jgTimeLeft2);
        outState.putLong("key12", originalJG2);
        outState.putLong("key13", adcTimeLeft1);
        outState.putLong("key14", originalADC1);
        outState.putLong("key15", adcTimeLeft2);
        outState.putLong("key16", originalADC2);
        outState.putLong("key17", supTimeLeft1);
        outState.putLong("key18", originalSup1);
        outState.putLong("key19", supTimeLeft2);
        outState.putLong("key20", originalSup2);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        topTimeLeft1 = savedInstanceState.getLong("key1");
        originalTop1 = savedInstanceState.getLong("key2");
        topTimeLeft2 = savedInstanceState.getLong("key3");
        originalTop2 = savedInstanceState.getLong("key4");
        midTimeLeft1 = savedInstanceState.getLong("key5");
        originalMid1 = savedInstanceState.getLong("key6");
        midTimeLeft2 = savedInstanceState.getLong("key7");
        originalMid2 = savedInstanceState.getLong("key8");
        jgTimeLeft1 = savedInstanceState.getLong("key9");
        originalJG1 = savedInstanceState.getLong("key10");
        jgTimeLeft2 = savedInstanceState.getLong("key11");
        originalJG2 = savedInstanceState.getLong("key12");
        adcTimeLeft1 = savedInstanceState.getLong("key13");
        originalADC1 = savedInstanceState.getLong("key14");
        adcTimeLeft2 = savedInstanceState.getLong("key15");
        originalADC2 = savedInstanceState.getLong("key16");
        supTimeLeft1 = savedInstanceState.getLong("key17");
        originalSup1 = savedInstanceState.getLong("key18");
        supTimeLeft2 = savedInstanceState.getLong("key19");
        originalSup2 = savedInstanceState.getLong("key20");
    }
}
