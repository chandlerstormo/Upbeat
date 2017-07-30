package upbeat.upbeat;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.support.annotation.NonNull;
import android.util.Log;

import com.firebase.client.Firebase;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Chandler on 4/6/16.
 */
public class SongSingleton {
    private Firebase mainReference;
    private Firebase deleteReference;
    private Firebase songsReference;
    private Firebase individualReference;
    private ArrayList<Song> mSongs;
    private ArrayList<Integer> mSongIDS;
    private Context mAppContext;
    private static SongSingleton sSongs;
    private HashMap<String, Song> mKeyMap;

    public static final String TAG = UpActivity.class.getSimpleName();

    private SongSingleton (Context c) {
        mAppContext = c;
        mSongs = new ArrayList<>();
        populateSongs();
    }

    private void populateSongs() {
        String key;
        mKeyMap = new HashMap<String, Song>();
        mainReference = new Firebase("https://dazzling-fire-2122.firebaseio.com");
        deleteReference = new Firebase("https://dazzling-fire-2122.firebaseio.com/songs");
        deleteReference.removeValue();
        songsReference = mainReference.child("songs");
        Field[] fields = R.raw.class.getFields();
        Log.d(TAG, "Hello world: " + fields.length);
        Song tempSong;
        int tempSongID;
        int tempSongUpbeats = 0;
        String tempSongTitle;
        String tempSongFormattedTitle;
        String tempSongArtist;
        for (int i = 0; i < fields.length; i++) {
            Log.d(TAG, fields[i].getName());
            tempSong = new Song();
            tempSongTitle = fields[i].getName();
            tempSongFormattedTitle = getReformattedTitle(tempSongTitle);
            tempSongArtist = getArtistName(tempSongTitle);
            tempSongID = mAppContext.getResources()
                    .getIdentifier(tempSongTitle, "raw", mAppContext.getPackageName());

            tempSong.setSongID(tempSongID);
            tempSong.setTitle(tempSongTitle);
            tempSong.setUpbeats(tempSongUpbeats); // this is where the cloud does cloud things
            tempSong.setFormattedTitle(tempSongFormattedTitle);
            tempSong.setArtistName(tempSongArtist);
            //mSongs.add(tempSong);
            //key =
//          individualReference.push().setValue(tempSong);
            individualReference = songsReference.push();
            individualReference.setValue(tempSong);
            key = individualReference.getKey();
            //tempSong.setReference(individualReference.child("upbeats"));
            tempSong.setKey(key);
            mSongs.add(tempSong);
            mKeyMap.put(key, tempSong);
        }
    }

    private String getReformattedTitle(String tempSongTitle) {
        String formatted = "";
        if (tempSongTitle.equals("anaconda"))
            formatted = "Anaconda";
        if (tempSongTitle.equals("cocoabutterkisses"))
            formatted = "Cocoa Butter Kisses";
        if (tempSongTitle.equals("father"))
            formatted = "Father Stretch My Hands Pt. 1";
        if (tempSongTitle.equals("ishort"))
            formatted = "i";
        if (tempSongTitle.equals("myhumps"))
            formatted = "My Humps";
        if (tempSongTitle.equals("problemsshort"))
            formatted = "F**ckin' Problems";
        if (tempSongTitle.equals("septembershort"))
            formatted = "September";
        if (tempSongTitle.equals("threethousandfive"))
            formatted = "3005";
        if (tempSongTitle.equals("trapqueenshort"))
            formatted = "Trap Queen";
        if (tempSongTitle.equals("ultralight"))
            formatted = "Ultralight Beam";
        if (tempSongTitle.equals("uptownfunkshort"))
            formatted = "Uptown Funk";
        if (tempSongTitle.equals("lightsoutedited"))
            formatted = "Lights Out";
        if (tempSongTitle.equals("rivals"))
            formatted = "Rivals";
        if (tempSongTitle.equals("letsgetitstarted"))
            formatted = "Let's Get It Started";
        if (tempSongTitle.equals("lookatmenow"))
            formatted = "Look At Me Now";
        if (tempSongTitle.equals("loyal"))
            formatted = "Loyal";
        if (tempSongTitle.equals("pompeii"))
            formatted = "Pompeii";
        if (tempSongTitle.equals("strangeclouds"))
            formatted = "Strange Clouds";
        if (tempSongTitle.equals("lockedout"))
            formatted = "Locked Out Of Heaven";
        if (tempSongTitle.equals("diva"))
            formatted = "Diva";
        if (tempSongTitle.equals("drunkinlove"))
            formatted= "Drunk In Love";
        if (tempSongTitle.equals("headlines"))
            formatted= "Headlines";
        if (tempSongTitle.equals("ratherbe"))
            formatted= "Rather Be";
        if (tempSongTitle.equals("dance"))
            formatted = "Dance (A$$)";
        if (tempSongTitle.equals("getlucky"))
            formatted = "Get Lucky";
        if (tempSongTitle.equals("sundaycandy"))
            formatted = "Sunday Candy";
        if (tempSongTitle.equals("bangbang"))
            formatted = "Bang Bang";
        if (tempSongTitle.equals("donttellem"))
            formatted = "Don't Tell 'Em";
        if (tempSongTitle.equals("goodfeeling"))
            formatted= "Good Feeling";
        if (tempSongTitle.equals("holygrail"))
            formatted = "Holy Grail";
        if (tempSongTitle.equals("nohands"))
            formatted = "No Hands";
        if (tempSongTitle.equals("waitingallnight"))
            formatted= "Waiting All Night";
        if (tempSongTitle.equals("wefoundlove"))
            formatted= "We Found Love";
        if (tempSongTitle.equals("loveneverfeltsogood"))
            formatted = "Love Never Felt So Good";
        if (tempSongTitle.equals("low"))
            formatted = "Low";
        if (tempSongTitle.equals("holdinon"))
            formatted = "Holdin' On";
        if (tempSongTitle.equals("yeah"))
            formatted = "Yeah!";
        if (tempSongTitle.equals("talkdirty"))
            formatted = "Talk Dirty";
        if (tempSongTitle.equals("stronger"))
            formatted = "Stronger";
        if (tempSongTitle.equals("onemorenight"))
            formatted = "One More Night";
        if (tempSongTitle.equals("partyrock"))
            formatted = "Party Rock Anthem";
        return formatted;
    }

    private String getArtistName(String tempSongTitle) {
        String artist = "";
        if (tempSongTitle.equals("anaconda"))
            artist = "Nicki Minaj";
        if (tempSongTitle.equals("cocoabutterkisses"))
            artist = "Chance the Rapper";
        if (tempSongTitle.equals("father"))
            artist = "Kanye West";
        if (tempSongTitle.equals("ishort"))
            artist = "Kendrick Lamar";
        if (tempSongTitle.equals("myhumps"))
            artist = "The Black Eyed Peas";
        if (tempSongTitle.equals("problemsshort"))
            artist = "A$AP Rocky";
        if (tempSongTitle.equals("septembershort"))
            artist = "Earth, Wind & Fire";
        if (tempSongTitle.equals("threethousandfive"))
            artist = "Childish Gambino";
        if (tempSongTitle.equals("trapqueenshort"))
            artist = "Fetty Wap";
        if (tempSongTitle.equals("ultralight"))
            artist = "Kanye West";
        if (tempSongTitle.equals("uptownfunkshort"))
            artist = "Bruno Mars & Mark Ronson";
        if (tempSongTitle.equals("lightsoutedited"))
            artist = "Zomboy";
        if (tempSongTitle.equals("rivals"))
            artist = "Barely Alive";
        if (tempSongTitle.equals("letsgetitstarted"))
            artist = "Black Eyed Peas";
        if (tempSongTitle.equals("lookatmenow"))
            artist = "Chris Brown";
        if (tempSongTitle.equals("loyal"))
            artist = "Chris Brown";
        if (tempSongTitle.equals("pompeii"))
            artist = "Bastille";
        if (tempSongTitle.equals("strangeclouds"))
            artist = "B.O.B.";
        if (tempSongTitle.equals("lockedout"))
            artist = "Bruno Mars";
        if (tempSongTitle.equals("diva"))
            artist = "Beyonce";
        if (tempSongTitle.equals("drunkinlove"))
            artist = "Beyonce";
        if (tempSongTitle.equals("headlines"))
            artist = "Drake";
        if (tempSongTitle.equals("ratherbe"))
            artist = "Clean Bandit";
        if (tempSongTitle.equals("dance"))
            artist = "Big Sean";
        if (tempSongTitle.equals("getlucky"))
            artist = "Daft Punk";
        if (tempSongTitle.equals("sundaycandy"))
            artist = "The Social Experiment";
        if (tempSongTitle.equals("bangbang"))
            artist = "Jessie J";
        if (tempSongTitle.equals("donttellem"))
            artist = "Jeremih";
        if (tempSongTitle.equals("goodfeeling"))
            artist = "Flo Rida";
        if (tempSongTitle.equals("holygrail"))
            artist = "Jay-Z";
        if (tempSongTitle.equals("nohands"))
            artist = "Waka Flocka Flame";
        if (tempSongTitle.equals("waitingallnight"))
            artist = "Rudimental";
        if (tempSongTitle.equals("wefoundlove"))
            artist = "Rihanna";
        if (tempSongTitle.equals("loveneverfeltsogood"))
            artist = "Michael Jackson";
        if (tempSongTitle.equals("low"))
            artist = "Flo Rida";
        if (tempSongTitle.equals("holdinon"))
            artist = "Flume";
        if (tempSongTitle.equals("yeah"))
            artist = "Usher";
        if (tempSongTitle.equals("talkdirty"))
            artist = "Jason Derulo";
        if (tempSongTitle.equals("stronger"))
            artist = "Kanye West";
        if (tempSongTitle.equals("onemorenight"))
            artist = "Maroon 5";
        if (tempSongTitle.equals("partyrock"))
            artist = "LMFAO";
        return artist;
    }

    public static SongSingleton get (Context c) {
        if (sSongs == null) {
            sSongs = new SongSingleton(c.getApplicationContext());
        }
        return sSongs;
    }

    public Firebase getReference(Song song) {
        return individualReference;
    }

    public ArrayList<Song> getSongs() {
        return mSongs;
    }

    public Song getSong(int index) {
        return mSongs.get(index);
    }

    public Song getSongFromKey(String key) {
        return mKeyMap.get(key);
    }

    //private ArrayList<Integer> getSongIDS() {return mSongIDS;}

    public void addSong(Song song) {
        mSongs.add(song);
    }

    public void removeSong(int position) {
        mSongs.remove(position);
    }

    public void updateSong(int position, Song song) {
        if (position >= 0 && position < mSongs.size()) {
            mSongs.set(position, song);
        }
    }

}
