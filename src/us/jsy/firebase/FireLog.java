package us.jsy.firebase;

import java.sql.Timestamp;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.PersistentStorage;
import com.firebase.client.ValueEventListener;

public class FireLog {
	Firebase ref;
	
	public FireLog(Firebase ref) {
		super();
		this.ref = ref;
	}

	public void log(String msg) {
		log(ref, msg);
	}
	
	public static void log(String url, String msg) {
		final Firebase ref = new Firebase(url);
		
		log(ref, msg);
	}
	
	private static void log(final Firebase ref, String msg) {
		ref.push().setValue(new Timestamp(System.currentTimeMillis()) + ":" + msg);
	}

	private static void test(final Firebase firebase) {
		Firebase authRef = firebase.getRoot().child(".info/authenticated");
		authRef.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot snap) {
				boolean isAuthenticated = snap.getValue(Boolean.class);
				System.out.println("isAuth " + isAuthenticated);
			}

			@Override
			public void onCancelled() {
			}
		});
	}

	private static void junk() {
		Firebase.getDefaultConfig().setPersistentStorage(new PersistentStorage() {
			@Override
			public void set(String arg0, String arg1) {
				// TODO Auto-generated method stub
				System.out.println("set");
			}
			
			@Override
			public boolean hasKey(String arg0) {
				// TODO Auto-generated method stub
				System.out.println("set");
				return false;
			}
			
			@Override
			public void get(String arg0) {
				// TODO Auto-generated method stub
				System.out.println("set");
			}
			
			@Override
			public void deleteKey(String arg0) {
				// TODO Auto-generated method stub
				System.out.println("set");
			}
		});
	}
}
